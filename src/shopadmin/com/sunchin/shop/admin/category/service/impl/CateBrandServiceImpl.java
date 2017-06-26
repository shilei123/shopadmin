package com.sunchin.shop.admin.category.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.category.dao.CateBrandDAO;
import com.sunchin.shop.admin.category.service.CateBrandService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScCateBrand;
import com.sunchin.shop.admin.property.dao.PropertyDAO;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.CommonUtils;

@Service("cateBrandService")
public class CateBrandServiceImpl implements CateBrandService {

	@Resource(name="dbUtil")
	private DBUtil db;
	@Resource(name="propertyDAO")
	private PropertyDAO propertyDAO;
	@Resource(name="cateBrandDAO")
	private CateBrandDAO cateBrandDAO;
	
	@Override
	public void saveCateBrand(PageBean pageBean) throws Exception {
		String cateId = CommonUtils.getString(pageBean.getQueryParams().get("cateId"));
		String checkBrandIds = CommonUtils.getString(pageBean.getQueryParams().get("checkBrandIds"));
		List<Map<String, Object>> list = cateBrandDAO.queryCateBrandByCateId(cateId);//查询和类别相关的所有品牌
		if( checkBrandIds==null || cateId==null
				|| ("".equals(checkBrandIds) && (list==null || list.isEmpty())) ) {//异常情况
			return;
		}
		
		//这里待补充：需要查询该条类别-品牌关系是否被引用， 如果被引用就逻辑删除
		
		
		//传入为空，全删
		if("".equals(checkBrandIds)){
			for (int i = 0; i < list.size(); i++) {
				String propId = CommonUtils.getString(list.get(i).get("brandId"));
				this.delPropBrand(cateId, propId);
			}
			return;
		}
		//结果集为空，全加
		String[] arr = checkBrandIds.split(",");
		if(list==null || list.isEmpty()){
			for (int i = 0; i < arr.length; i++) {
				this.addPropBrand(cateId, arr[i]);
			}
			return;
		}
		//传入和结果集都不为空（传入有，结果集无）
		for (int i = 0; i < arr.length; i++) {
			boolean addFlag = true;
			for (int j = 0; j < list.size(); j++) {
				String propId = CommonUtils.getString(list.get(j).get("brandId"));
				if(arr[i].equals(propId)){
					addFlag = false;
					break;
				}
			}
			if(addFlag){
				this.addPropBrand(cateId, arr[i]);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			boolean delFlag = true;
			String propId = CommonUtils.getString(list.get(i).get("brandId"));
			for (int j = 0; j < arr.length; j++) {
				if(propId.equals(arr[j])){
					delFlag = false;
					break;
				}
			}
			if(delFlag){
				this.delPropBrand(cateId, propId);
			}
		}
	}
	
	private void addPropBrand(String cateId, String brandId){
		ScCateBrand propBrand = new ScCateBrand();
		propBrand.setId(UUID.randomUUID().toString());
		propBrand.setCateId(cateId);
		propBrand.setBrandId(brandId);
		propBrand.setCreateTime(new Date());
		propBrand.setFlag(FlagEnum.ACT.getCode());
		db.insert(propBrand);
	}
	
	private void delPropBrand(String cateId, String propId){
		cateBrandDAO.delPropBrand(cateId, propId);
	}


	@Override
	public List<Map<String, Object>> queryCateBrandCheck(PageBean pageBean) throws Exception {
		//该类别选中的品牌
		String cateId = CommonUtils.getString(pageBean.getQueryParams().get("cateId"));
		return cateBrandDAO.queryCateBrandByCateId(cateId);
	}

}
