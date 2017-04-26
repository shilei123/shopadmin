package com.sunchin.shop.admin.category.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.category.dao.BrandDAO;
import com.sunchin.shop.admin.category.service.BrandService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScBrand;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Service("brandService")
public class BrandServiceImpl implements BrandService {

	@Resource(name="brandDAO")
	private BrandDAO brandDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	
	@Override
	public PageBean queryBrandList(PageBean pageBean) throws Exception {
		int total = brandDAO.queryBrandCount(pageBean);
		pageBean.setTotal(total);
		List<ScBrand> pageData = brandDAO.queryBrandPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	@Override
	public void delBrand(String id) throws Exception {
		List<ScBrand> list = brandDAO.getBrand(id);
		if(list!=null && !list.isEmpty()){
			brandDAO.delBrand(id);
		}
	}
	
	@Override
	public void updateBrand(ScBrand brand) throws Exception {
		if(brand.getId().isEmpty())
			return;
		ScBrand brand_db = (ScBrand) db.get(ScBrand.class, brand.getId());
		brand_db.setBrandName(brand.getBrandName());
		brand_db.setBrandCode(brand.getBrandCode());
		brand_db.setBrandLogo(brand.getBrandLogo());
		brand_db.setBrandDesc(brand.getBrandDesc());
		db.update(brand_db);
	}
	
	/**
	 * 新增
	 */
	@Override
	public String addBrand(ScBrand brand) throws Exception {
		ScBrand brand_db = brandDAO.queryBrandByBrandName(brand.getBrandName());
		if(brand_db!=null)
			return "该品牌名已存在，请重新输入！";
		brand.setId(UUID.randomUUID().toString());
		brand.setCreateTime(new Date());
		brand.setFlag(FlagEnum.ACT.getCode());
		db.insert(brand);
		return "";
	}

	@Override
	public ScBrand queryBrandById(String id) throws Exception {
		Object obj = db.get(ScBrand.class, id);
		if(obj != null) {
			return (ScBrand) obj;
		}
		return null;
	}

}
