package com.sunchin.shop.admin.property.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScPropPropval;
import com.sunchin.shop.admin.propValue.dao.PropValueDAO;
import com.sunchin.shop.admin.property.dao.PropPropValueDAO;
import com.sunchin.shop.admin.property.service.PropPropValueService;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.CommonUtils;

@SuppressWarnings("rawtypes")
@Service("propPropValueService")
public class PropPropValueServiceImpl implements PropPropValueService {

	@Resource(name="dbUtil")
	private DBUtil db;
	@Resource(name="propValueDAO")
	private PropValueDAO propValueDAO;
	@Resource(name="propPropValueDAO")
	private PropPropValueDAO propPropValueDAO;
	
	@Override
	public void savePropPropValue(PageBean pageBean) throws Exception {
		String propertyId = CommonUtils.getString(pageBean.getQueryParams().get("propertyId"));
		String checkPropValueIds = CommonUtils.getString(pageBean.getQueryParams().get("checkPropValueIds"));
		List<Map<String, Object>> list = propPropValueDAO.queryPropPropValueByPropId(propertyId);//查询和属性相关的所有属性值
		if( checkPropValueIds==null || propertyId==null
				|| ("".equals(checkPropValueIds) && (list==null || list.isEmpty())) ) {//异常情况
			return;
		}
		
		//这里待补充：需要查询该条 属性-属性值关系是否被引用， 如果被引用就逻辑删除
		
		
		//传入为空，全删
		if("".equals(checkPropValueIds)){
			/*for (int i = 0; i < list.size(); i++) {
				String valId = CommonUtils.getString(list.get(i).get("valId"));
				this.delPropPropValue(propertyId, valId);
			}*/
			this.delAllPropPropValue(propertyId);
			return;
		}
		//结果集为空，全加
		String[] arr = checkPropValueIds.split(",");
		if(list==null || list.isEmpty()){
			for (int i = 0; i < arr.length; i++) {
				this.addPropPropValue(propertyId, arr[i]);
			}
			return;
		}
		//传入和结果集都不为空（传入有，结果集无）
		for (int i = 0; i < arr.length; i++) {
			boolean addFlag = true;
			for (int j = 0; j < list.size(); j++) {
				String valId = CommonUtils.getString(list.get(j).get("valId"));
				if(arr[i].equals(valId)){
					addFlag = false;
					break;
				}
			}
			if(addFlag){
				this.addPropPropValue(propertyId, arr[i]);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			boolean delFlag = true;
			String valId = CommonUtils.getString(list.get(i).get("valId"));
			for (int j = 0; j < arr.length; j++) {
				if(valId.equals(arr[j])){
					delFlag = false;
					break;
				}
			}
			if(delFlag){
				this.delPropPropValue(propertyId, valId);
			}
		}
	}
	
	private void addPropPropValue(String propId, String valId){
		if(!valId.isEmpty() && valId!=null){
			ScPropPropval propPropValue = new ScPropPropval();
			propPropValue.setId(UUID.randomUUID().toString());
			propPropValue.setPropId(propId);
			propPropValue.setValId(valId);
			propPropValue.setCreateTime(new Date());
			propPropValue.setFlag(FlagEnum.ACT.getCode());
			db.insert(propPropValue);
		}
	}
	
	private void delAllPropPropValue(String propId){
		propPropValueDAO.delAllPropPropValue(propId);
	}
	
	private void delPropPropValue(String propId, String valId){
		propPropValueDAO.delPropPropValue(propId, valId);
	}
	
	/**
	 * 该类别的所有属性属性值关系
	 * @param cateId
	 */
	public List queryPropPropValByCateId(String cateId){
		List<Map> list = propPropValueDAO.queryPropPropValByCateId(cateId);
		List map = this.generateData(list);
		return map;
	}
	
	@SuppressWarnings("unchecked")
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

	@Override
	public PageBean queryPropPropValue(PageBean pageBean) throws Exception {
		int total = propValueDAO.queryPropValueCount(pageBean);
		List list = propValueDAO.queryPropValuePagination(pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}
	
	@Override
	public List<Map<String, Object>> queryPropPropValueCheck(PageBean pageBean) throws Exception {
		String propertyId = CommonUtils.getString(pageBean.getQueryParams().get("propertyId"));
		return propPropValueDAO.queryPropPropValueByPropId(propertyId);
	}

}
