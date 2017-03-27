package com.sunchin.shop.admin.catePropPropval.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

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
	public Map queryMapByCateId(String cateId){
		List<Map> list = catePropPropvalDAO.queryMapByCateId(cateId);
		Map map = this.generateData(list);
		return map;
	}
	
	private Map generateData(List<Map> list) {
		Map<String, Object> map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Map row = list.get(i);
			String propName = CommonUtils.getString(row.get("propName"));
			String valName = CommonUtils.getString(row.get("valName"));
			String propId = CommonUtils.getString(row.get("propid"));
			String valId = CommonUtils.getString(row.get("valid"));
			Object tempMap = map.get(propName);
			if(tempMap!=null) {
				Map childMap = (HashMap)tempMap;
				childMap.put("propName", propName);
				childMap.put("propId", propId);
				Object tempValName = childMap.get("valNames");
				List tempValIdList = (ArrayList)childMap.get("valIds");
				if(tempValName!=null/* && tempValIdList!=null*/){
					List<String> valList = (ArrayList)tempValName;
					valList.add(valName);
					List<String> idList = (ArrayList)tempValIdList;
					idList.add(valId);
				}else{
					List<String> valList = new ArrayList<String>();
					valList.add(valName);
					childMap.put("valNames", valList);
					List<String> idList = new ArrayList<String>();
					idList.add(valId);
					childMap.put("valIds", idList);
				}
			} else {
				Map childMap = new HashMap();
				childMap.put("propId", propId);
				childMap.put("propName", propName);
				map.put(propName, childMap);
				List tempValNameList = (ArrayList)childMap.get("valNames");
				List tempValIdList = (ArrayList)childMap.get("valIds");
				if(tempValNameList!=null && tempValIdList!=null){
					List<String> valList = (ArrayList)tempValNameList;
					valList.add(valName);
					List<String> idList = (ArrayList)tempValIdList;
					idList.add(valId);
				}else{
					List<String> valList = new ArrayList<String>();
					valList.add(valName);
					childMap.put("valNames", valList);
					List<String> idList = new ArrayList<String>();
					idList.add(valId);
					childMap.put("valIds", idList);
				}
			}
		}
		System.out.println(map);
		return map;
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
