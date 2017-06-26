package com.sunchin.shop.admin.comment.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.comment.dao.FaqTypeDAO;
import com.sunchin.shop.admin.comment.service.FaqTypeService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScFaqType;

import framework.db.DBUtil;
import framework.util.ComparatorFaqTypeVO;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service("faqTypeService")
public class FaqTypeServiceImpl implements FaqTypeService {

	@Resource(name="faqTypeDAO")
	private FaqTypeDAO faqTypeDAO;
	@Resource(name="dbUtil")
	private DBUtil db;

	public List<Map> queryFaqTypeTree() throws Exception{
		List<Map> list = faqTypeDAO.queryTreeBySQL();
		Map root = null;
		Map<String,Map> temp = new TreeMap();
		for (Map pojo : list) {
			Map node = new TreeMap();
			node.put("pkId", pojo.get("id")); //类别主键
			node.put("text", pojo.get("typeName")); //类别名称
			node.put("typeDesc", pojo.get("typeDesc")); //类别描述
			node.put("typeCode", pojo.get("typeCode")); //类别编码
			node.put("parentId", pojo.get("parentTypeId")); //上级类别id
			node.put("typeLevel", pojo.get("level_")); //上级类别层级
			Map attributes = new HashMap();
			attributes.put("typeOrder", pojo.get("order_")); //类别当前层级顺序
			node.put("attributes", attributes);
			temp.put(pojo.get("id").toString(), node);
			if("0".equals(pojo.get("level_"))) {
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
					Collections.sort(childOrgList, new ComparatorFaqTypeVO());
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

	@Override
	public void saveFaqType(ScFaqType faqType) throws Exception {
		faqType.setId(UUID.randomUUID().toString());
		faqType.setFlag(FlagEnum.ACT.getCode());
		faqType.setCreateTime(new Date());
		db.insert(faqType);
	}

	@Override
	public void updateFaqType(ScFaqType faqType) throws Exception {
		ScFaqType temp = (ScFaqType) db.get(ScFaqType.class, faqType.getId());
		temp.setTypeName(faqType.getTypeName());
		temp.setOrder(faqType.getOrder());
		temp.setTypeDesc(faqType.getTypeDesc());
		temp.setTypeCode(faqType.getTypeCode());
		db.update(temp);
	}

	@Override
	public String delFaqType(String id) throws Exception {
		List<ScFaqType> list = faqTypeDAO.querySonFaqType(id);
		if(list!=null && !list.isEmpty()){
			return "该问题类别已有子类别，无法直接删除！";
		} else {
			faqTypeDAO.delFaqType(id);
			return "";
		}
	}
}
