package com.sunchin.shop.admin.catePropPropval.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.catePropPropval.dao.CatePropPropvalDAO;
import com.sunchin.shop.admin.catePropPropval.service.CatePropPropvalService;
import com.sunchin.shop.admin.pojo.ScCatePropPropVal;

import framework.db.DBUtil;
import framework.util.CommonUtils;
import framework.util.ComparatorCategoryVO;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service("catePropPropvalService")
public class CatePropPropvalServiceImpl implements CatePropPropvalService {

	@Resource(name="catePropPropvalDAO")
	private CatePropPropvalDAO catePropPropvalDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	
	public List<Map> queryCategoryTree() throws Exception{
		List<Map> list = catePropPropvalDAO.queryTreeBySQL();
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
	
	/**
	 * 该类别的所有属性和属性值
	 * @param cateId
	 */
	public List queryMapByCateId(String cateId){
		List<Map> list = catePropPropvalDAO.queryMapByCateId(cateId);
		List map = this.generateData(list);
		return map;
	}
	
	private List generateData(List<Map> list) {
		Map<String, Object> map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Map row = list.get(i);
			String propId = CommonUtils.getString(row.get("propid"));
			String propName = CommonUtils.getString(row.get("propName"));
			String valId = CommonUtils.getString(row.get("valid"));
			String valName = CommonUtils.getString(row.get("valName"));
			String propInfo = propId+"_"+propName;
			
			Object vals = map.get(propInfo);
			List valList = null;
			if(vals != null) {
				valList = (ArrayList) vals;
				valList.add(row);
			} else {
				valList = new ArrayList();
				//如果没有属性值的则不添加进去
				if(StringUtils.isNotBlank(valId) && StringUtils.isNotBlank(valName)) {
					valList.add(row);
				}
				map.put(propInfo, valList);
			}
		}
		
		//转换成json格式数据
		List nodes = new ArrayList(map.size());
		Iterator iter = map.keySet().iterator();
		while(iter.hasNext()) {
			Map node = new HashMap(3);
			String key = iter.next().toString();
			String[] keys = key.split("_");
			node.put("propId", keys[0]);
			node.put("propName", keys[1]);
			
			//去掉无关信息 
			List vals = (ArrayList) map.get(key);
			for(int i = 0; i < vals.size();i++) {
				Map row = (Map) vals.get(i);
				row.remove("propid");
				row.remove("propName");
				row.remove("cateName");
			}
			
			node.put("vals", vals);
			nodes.add(node);
		}
		
		return nodes;
	}

	public void saveCatePropPropVal(ScCatePropPropVal catePropPropVal) throws Exception {
		/*String cateOrder = CommonUtils.getString(category.getCateOrder());
		if(cateOrder.length()==1){
			cateOrder = "0" + cateOrder;
		}
		category.setCateOrder(cateOrder);
		category.setId(UUID.randomUUID().toString());
		category.setFlag(FlagEnum.ACT.getCode());
		category.setCreateTime(new Date());
		db.insert(category);*/
	}

}
