package com.sunchin.shop.admin.category.service;

import java.util.List;
import java.util.Map;

import framework.bean.PageBean;

public interface CateBrandService {

	/**
	 * 保存类别品牌关系
	 * @param cateId
	 * @param checkBrandIds	用逗号隔开的字符串
	 * @throws Exception
	 */
	public void saveCateBrand(PageBean pageBean) throws Exception;
	
	/**
	 * 查询该类别的所有品牌
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryCateBrandCheck(PageBean pageBean) throws Exception;
	
}
