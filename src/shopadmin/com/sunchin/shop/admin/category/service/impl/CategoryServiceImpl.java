package com.sunchin.shop.admin.category.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.category.dao.CategoryDAO;
import com.sunchin.shop.admin.category.service.CategoryService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScCategory;

import framework.db.DBUtil;
import framework.util.ComparatorCategoryVO;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Resource(name="categoryDAO")
	private CategoryDAO categoryDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	
	@Override
	public void saveCategory(ScCategory category) throws Exception {
		/*String cateOrder = CommonUtils.getString(category.getCateOrder());
		if(cateOrder.length()==1){
			cateOrder = "0" + cateOrder;
		}
		category.setCateOrder(cateOrder);*/
		
		//根据父id的levels确定新类别的层级levels	前台已经处理
		/*String id = CommonUtils.getString(category.getParentId());
		if(id.equals(""))	return;
		List<ScCategory> list = categoryDAO.queryPojoById(id);
		if(list==null || list.isEmpty()) return;
		String levels = list.get(0).getLevels();
		if(levels==null || levels.isEmpty()) return;
		Integer level = Integer.parseInt(levels) + 1;
		category.setLevels(level.toString());*/
		
		category.setId(UUID.randomUUID().toString());
		category.setFlag(FlagEnum.ACT.getCode());
		category.setCreateTime(new Date());
		db.insert(category);
	}

	@Override
	public void delCategory(String id) throws Exception {
		categoryDAO.delCategory(id);
	}

	@Override
	public List<ScCategory> queryCategory(String id) throws Exception {
		List<ScCategory> list = null;
		if(StringUtils.isNotBlank(id)) {
			list = categoryDAO.querySonCategory(id);
		} else {
			list = categoryDAO.queryFirstCategory();
		}
		return list;
	}

	@Override
	public void updateCategory(ScCategory category) throws Exception {
		ScCategory cate = (ScCategory) db.get(ScCategory.class, category.getId());
		cate.setCateName(category.getCateName());
		cate.setCateOrder(category.getCateOrder());
		cate.setMemo(category.getMemo());
		cate.setLogo(category.getLogo());
		cate.setUrl(category.getUrl());
		cate.setCateCode(category.getCateCode());
		cate.setIsuse(category.getIsuse());
		category.setUpdateTime(new Date());
		db.update(cate);
	}
	
	public List<Map> queryCategoryTree() throws Exception{
		List<Map> list = categoryDAO.queryTreeBySQL();
		Map root = null;
		Map<String,Map> temp = new TreeMap();
		for (Map pojo : list) {
			Map node = new TreeMap();
			node.put("pkId", pojo.get("id")); //类别主键
			node.put("text", pojo.get("cateName")); //类别名称
			node.put("parentId", pojo.get("parentId")); //上级类别编码
			node.put("levels", pojo.get("levels")); //上级类别编码
			Map attributes = new HashMap(5);
			attributes.put("memo", pojo.get("memo")); //类别描述
			attributes.put("cateOrder", pojo.get("cateOrder")); //类别排序
			attributes.put("logo", pojo.get("logo")); //logo
			attributes.put("url", pojo.get("url")); //类别url 
			attributes.put("isuse", pojo.get("isuse")); //是否有效
			node.put("attributes", attributes);
			temp.put(pojo.get("id").toString(), node);
			if("0".equals(pojo.get("levels"))) {
				root = node;
			}
		}
		
		//循环找出层级关系
		for(String key : temp.keySet()) {
			Map node = temp.get(key);
			Object parentId = node.get("parentId");
			if(parentId==null){
				continue;
			}
			Map parentMap = temp.get(parentId);
			if(parentMap != null) {
				if(parentMap.get("children") == null) {
					parentMap.put("children", new ArrayList());
				}
				((ArrayList)parentMap.get("children")).add(node);
			}
		}
		
		//循环判断是否有子节点，是否能展开
		for(String key : temp.keySet()) {
			Map node = temp.get(key);
			if(node.get("children") != null) {
				List childOrgList = (ArrayList) node.get("children");
				if(!childOrgList.isEmpty()) {
					Collections.sort(childOrgList, new ComparatorCategoryVO());
					node.put("state", "closed"); //节点状态：关闭
				}
			} 
		}
		List<Map> trees = new ArrayList(1);
		if(root != null) {
			trees.add(root);
		}
		return trees;
	}

}
