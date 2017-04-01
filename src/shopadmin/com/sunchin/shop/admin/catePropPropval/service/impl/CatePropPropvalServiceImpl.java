package com.sunchin.shop.admin.catePropPropval.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.dict.FlagEnum;
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
	 * 该类别的所有属性属性值关系
	 * @param cateId
	 */
	public List queryListByCateId(String cateId){
		List<Map> list = catePropPropvalDAO.queryMapByCateId(cateId);
		List map = this.generateData(list);
		return map;
	}
	
	private List generateData(List<Map> list) {
		Map<String, Object> map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Map row = list.get(i);
			String propId = CommonUtils.getString(row.get("propId"));
			String propName = CommonUtils.getString(row.get("propName"));
			String propPropvalId = CommonUtils.getString(row.get("propPropvalId"));
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
				if(StringUtils.isNotBlank(propPropvalId) && StringUtils.isNotBlank(valName)) {
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
				row.remove("propId");
				row.remove("propName");
				row.remove("cateName");
			}
			
			node.put("vals", vals);
			nodes.add(node);
		}
		
		return nodes;
	}

	/**
	 *	@param propPropValIds	所有选中的属性属性值关系
	 */
	public void saveCatePropPropVal(String cateId, String propPropValIds) throws Exception {
		List<Map> list = catePropPropvalDAO.queryCatePropPropValByCateId(cateId);//该类别对应的所有 类别-属性属性值关系
		//数据传入异常、传入和数据库都为空
		if(cateId==null || propPropValIds==null
				|| ("".equals(propPropValIds) && (list==null || list.isEmpty())) ) return;
		
		//如果分类-属性属性值关系有被引用就逻辑删除(商品录入可能会用到)
		
		//传入为空，全删
		if(propPropValIds.isEmpty()){
			this.delAllCatePropPropValue(cateId);
			return;
		}
		String[] arr = propPropValIds.split(",");
		//数据库为空，全增
		if(list==null || list.isEmpty()){
			for (int i = 0; i < arr.length; i++) {
				this.addCatePropPropValue(cateId, arr[i]);
			}
			return;
		}
		
		//传入和数据库都不为空（传入有，数据库无，增）
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].isEmpty() || arr[i]==null) continue;
			boolean addFlag = true;
			for (int j = 0; j < list.size(); j++) {
				String proppropvalId = CommonUtils.getString(list.get(j).get("propPropvalId"));
				if(arr[i].equals(proppropvalId)){
					addFlag = false;
					break;
				}
			}
			if(addFlag){
				this.addCatePropPropValue(cateId, arr[i]);
			}
		}
		//传入无，数据库有，删
		for (int i = 0; i < list.size(); i++) {
			boolean delFlag = true;
			String proppropvalId = CommonUtils.getString(list.get(i).get("propPropvalId"));
			for (int j = 0; j < arr.length; j++) {
				if(proppropvalId.equals(arr[j])){
					delFlag = false;
					break;
				}
			}
			if(delFlag){
				this.delCatePropPropValue(cateId, proppropvalId);
			}
		}
	}
	
	private void delAllCatePropPropValue(String cateId ){
		String hql = " update ScCatePropPropVal set flag=? where cateId=? ";
		db.executeHql(hql, FlagEnum.HIS.getCode(), cateId);
	}
	
	private void delCatePropPropValue(String cateId, String proppropvalId){
		if(!proppropvalId.isEmpty() || proppropvalId!=null){
			String hql = " update ScCatePropPropVal set flag=? where cateId=? and proppropvalId=? ";
			db.executeHql(hql, FlagEnum.HIS.getCode(), cateId, proppropvalId);
		}
	}
	
	private void addCatePropPropValue(String cateId, String propPropValId){
		ScCatePropPropVal catePropPropVal = new ScCatePropPropVal();
		catePropPropVal.setId(UUID.randomUUID().toString());
		catePropPropVal.setCreateTime(new Date());
		catePropPropVal.setFlag(FlagEnum.ACT.getCode());
		catePropPropVal.setCateId(cateId);
		catePropPropVal.setProppropvalId(propPropValId);
		//catePropPropVal.setOrders(i);
		db.insert(catePropPropVal);
	}

	@Override
	public List queryListCheck(String cateId) throws Exception {
		List<ScCatePropPropVal> list = catePropPropvalDAO.findPojo(cateId);
		return list;
	}

	/**
	 * 根据类别查询类别-属性-属性值关系 add by yangchaowen
	 * @param cateId
	 * @return
	 * @date 2017-03-30
	 */
	@Override
	public List queryByCateId(String cateId) throws Exception {
		return catePropPropvalDAO.queryByCateId(cateId);
	}

}
