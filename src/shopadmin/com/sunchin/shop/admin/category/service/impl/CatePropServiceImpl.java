package com.sunchin.shop.admin.category.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.category.dao.CatePropDAO;
import com.sunchin.shop.admin.category.service.CatePropService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScCateProp;
import com.sunchin.shop.admin.property.dao.PropertyDAO;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.CommonUtils;

@Service("catePropService")
public class CatePropServiceImpl implements CatePropService {

	@Resource(name="dbUtil")
	private DBUtil db;
	@Resource(name="propertyDAO")
	private PropertyDAO propertyDAO;
	@Resource(name="catePropDAO")
	private CatePropDAO catePropDAO;
	
	@Override
	public void savePropCate(PageBean pageBean) throws Exception {
		String cateId = CommonUtils.getString(pageBean.getQueryParams().get("cateId"));
		String checkPropIds = CommonUtils.getString(pageBean.getQueryParams().get("checkPropIds"));
		List<Map<String, Object>> list = catePropDAO.queryPropCateByCateId(cateId);//查询和类别相关的所有属性
		if( checkPropIds==null || cateId==null
				|| ("".equals(checkPropIds) && (list==null || list.isEmpty())) ) {//异常情况
			return;
		}
		
		//这里待补充：需要查询该条类别-属性关系是否被引用， 如果被引用就逻辑删除
		
		
		//传入为空，全删
		if("".equals(checkPropIds)){
			for (int i = 0; i < list.size(); i++) {
				String propId = CommonUtils.getString(list.get(i).get("propId"));
				this.delPropCate(cateId, propId);
			}
			return;
		}
		//结果集为空，全加
		String[] arr = checkPropIds.split(",");
		if(list==null || list.isEmpty()){
			for (int i = 0; i < arr.length; i++) {
				this.addPropCate(cateId, arr[i]);
			}
			return;
		}
		//传入和结果集都不为空（传入有，结果集无）
		for (int i = 0; i < arr.length; i++) {
			boolean addFlag = true;
			for (int j = 0; j < list.size(); j++) {
				String propId = CommonUtils.getString(list.get(j).get("propId"));
				if(arr[i].equals(propId)){
					addFlag = false;
					break;
				}
			}
			if(addFlag){
				this.addPropCate(cateId, arr[i]);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			boolean delFlag = true;
			String propId = CommonUtils.getString(list.get(i).get("propId"));
			for (int j = 0; j < arr.length; j++) {
				if(propId.equals(arr[j])){
					delFlag = false;
					break;
				}
			}
			if(delFlag){
				this.delPropCate(cateId, propId);
			}
		}
	}
	
	private void addPropCate(String cateId, String propId){
		ScCateProp propCate = new ScCateProp();
		propCate.setId(UUID.randomUUID().toString());
		propCate.setCateId(cateId);
		propCate.setPropId(propId);
		propCate.setCreateTime(new Date());
		propCate.setFlag(FlagEnum.ACT.getCode());
		db.insert(propCate);
	}
	
	private void delPropCate(String cateId, String propId){
		catePropDAO.delPropCate(cateId, propId);
	}

	/**
	 * 用于类别-属性配置的查询
	 * 
	 */
	/*@Override
	public PageBean queryPropCate(PageBean pageBean) throws Exception {
		String cateId = CommonUtils.getString(pageBean.getQueryParams().get("cateId"));
		
		int total = propertyDAO.queryPropertyCount(pageBean);
		List<Map<String, Object>> listProp = propertyDAO.queryPropertyPagination(pageBean);//未加上check的属性
		
		//数据加工处理
		listProp = this.checkResult(cateId, listProp);
		
		pageBean.setTotal(total);
		pageBean.setPageData(listProp);
		return pageBean;
	}
	
	private List<Map<String, Object>> checkResult(String cateId, List<Map<String, Object>> listProp) {
		//强化结果集
		List<Map<String, Object>> listCheck = null;
		if (listProp != null && !listProp.isEmpty()) {
			String[] propIds = new String[listProp.size()];
			for (int i = 0; i < listProp.size(); i++) {
				Map<String, Object> propMap = listProp.get(i);
				if(propMap == null || propMap.get("id")==null)	continue;
				propIds[i] = propMap.get("id").toString();
			}
			listCheck = propCateDAO.queryPropCate(cateId, propIds);//查询当前页且和类别相关的属性
		}
		
		if(listCheck == null) 	return listProp;
		
		for (int i = 0; i < listProp.size(); i++) {
			Map<String, Object> mapPropTemp = listProp.get(i);
			String propId1 = CommonUtils.getString(mapPropTemp.get("id"));
			for (int j = 0; j < listCheck.size(); j++) {
				String propId2 = CommonUtils.getString( listCheck.get(j).get("propId") );
				if(propId1.equals(propId2)){
					mapPropTemp.put("check", true);
					break;
				} else {
					mapPropTemp.put("check", false);
				}
			}
		}
		return listProp;
	}*/

	@Override
	public PageBean queryPropCate(PageBean pageBean) throws Exception {
		int total = propertyDAO.queryPropertyCount(pageBean);
		List<Map<String, Object>> listProp = propertyDAO.queryPropertyPagination(pageBean);//未加上check的属性
		pageBean.setTotal(total);
		pageBean.setPageData(listProp);
		return pageBean;
	}
	@Override
	public List<Map<String, Object>> queryPropCateCheck(PageBean pageBean) throws Exception {
		//该类别选中的属性
		String cateId = CommonUtils.getString(pageBean.getQueryParams().get("cateId"));
		return catePropDAO.queryPropCateByCateId(cateId);
	}

}
