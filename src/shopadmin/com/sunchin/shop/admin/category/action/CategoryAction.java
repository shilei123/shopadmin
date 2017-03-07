package com.sunchin.shop.admin.category.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.util.ComparatorOrgVO;

public class CategoryAction {
	
	@Resource(name="categoryService")
	private List<Map> trees;
	
	private static final String AGENCY_SQL = " select o.id,o.agency_id,o.agency_name,o.short_name,o.parent_agency_id,o.agency_path,o.agency_level,o.order_,o.root,o.sts,o.init_flag from t_agency o where o.flag=? ";
	
	public String agencyTree() {
		agencyTreeQuery();
		return Action.SUCCESS;
	}
	
	private void agencyTreeQuery() {
		DBUtil db = DBUtil.getInstance();
		List<Map> list = db.queryBySQL(AGENCY_SQL, SysDict.FLAG_ACT);
		Map root = null;
		Map<String,Map> temp = new TreeMap();
		for (Map pojo : list) {
			Map node = new TreeMap();
			node.put("id", pojo.get("agencyId")); //机构编码
			node.put("text", pojo.get("agencyName")); //机构名称
			node.put("parentId", pojo.get("parentAgencyId")); //上机机构编码
			Map attributes = new HashMap(7);
			attributes.put("agencyPkId", pojo.get("id")); //机构主键
			attributes.put("shortName", pojo.get("shortName")); //机构简称
			attributes.put("agencyPath", pojo.get("agencyPath")); //机构全路径
			attributes.put("order", pojo.get("order_")); //排序序号
			attributes.put("agencyLevel", pojo.get("agencyLevel")); //机构等级 
			attributes.put("sts", pojo.get("sts")); //状态
			attributes.put("initFlag", pojo.get("initFlag")); //是否初始化
			node.put("attributes", attributes);
			temp.put(pojo.get("agencyId").toString(), node);
			if("1".equals(pojo.get("root"))) {
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
					Collections.sort(childOrgList, new ComparatorOrgVO());
					node.put("state", "closed"); //节点状态：关闭
				}
			} 
		}
		
		trees = new ArrayList(1);
		if(root != null) {
			trees.add(root);
		}
	}

}
