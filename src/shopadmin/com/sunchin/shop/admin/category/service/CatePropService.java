package com.sunchin.shop.admin.category.service;

import java.util.List;
import java.util.Map;

import framework.bean.PageBean;

public interface CatePropService {

	/**
	 * 保存类别属性关系
	 * @param cateId
	 * @param checkPropIds	用逗号隔开的字符串
	 * @throws Exception
	 */
	public void savePropCate(PageBean pageBean) throws Exception;
	
	/**
	 * 分页查询所有属性
	 * @param pageBean
	 * @throws Exception
	 */
	public PageBean queryPropCate(PageBean pageBean) throws Exception;
	
	/**
	 * 查询该类别的所有属性
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryPropCateCheck(PageBean pageBean) throws Exception;
	
}
