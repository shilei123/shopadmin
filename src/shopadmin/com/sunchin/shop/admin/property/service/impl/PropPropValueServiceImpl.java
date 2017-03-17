package com.sunchin.shop.admin.property.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScPropertyPropValue;
import com.sunchin.shop.admin.propValue.dao.PropValueDAO;
import com.sunchin.shop.admin.property.dao.PropPropValueDAO;
import com.sunchin.shop.admin.property.service.PropPropValueService;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.CommonUtils;

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
			for (int i = 0; i < list.size(); i++) {
				String valId = CommonUtils.getString(list.get(i).get("valId"));
				this.delPropPropValue(propertyId, valId);
			}
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
		ScPropertyPropValue propPropValue = new ScPropertyPropValue();
		propPropValue.setId(UUID.randomUUID().toString());
		propPropValue.setPropId(propId);
		propPropValue.setValId(valId);
		propPropValue.setCreateTime(new Date());
		propPropValue.setFlag(FlagEnum.ACT.getCode());
		db.insert(propPropValue);
	}
	
	private void delPropPropValue(String propId, String valId){
		String hql = " update ScPropertyPropValue set flag=? where propId=? and valId=? ";
		db.executeHql(hql, FlagEnum.HIS.getCode(), propId, valId);
	}

	@SuppressWarnings("rawtypes")
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
