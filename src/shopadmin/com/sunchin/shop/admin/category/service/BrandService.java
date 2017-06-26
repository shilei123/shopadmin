package com.sunchin.shop.admin.category.service;

import com.sunchin.shop.admin.pojo.ScBrand;

import framework.bean.PageBean;

public interface BrandService {

	/**
	 * 查询品牌列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryBrandList(PageBean pageBean) throws Exception;

	/**
	 * 删除品牌
	 * @param id
	 * @throws Exception
	 */
	public void delBrand(String id) throws Exception;

	/**
	 * 修改品牌
	 * @param brand
	 * @throws Exception
	 */
	public void updateBrand(ScBrand brand) throws Exception;

	/**
	 * 新增品牌
	 * @param brand
	 * @throws Exception
	 */
	public String addBrand(ScBrand brand) throws Exception;

	/**
	 * 查询某个品牌
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScBrand queryBrandById(String id) throws Exception;
}
