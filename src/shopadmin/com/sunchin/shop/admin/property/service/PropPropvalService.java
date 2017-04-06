package com.sunchin.shop.admin.property.service;

import java.util.List;
import java.util.Map;

import framework.bean.PageBean;

public interface PropPropvalService {

	/**
	 * 保存属性属性值关系
	 * @param propertyId
	 * @param checkPropPropValueIds	用逗号隔开的字符串
	 * @throws Exception
	 */
	public void savePropPropval(PageBean pageBean) throws Exception;
	
	/**
	 * 分页查询所有属性值
	 * @param pageBean
	 * @throws Exception
	 */
	public PageBean queryPropPropval(PageBean pageBean) throws Exception;
	
	/**
	 * 查询该属性的所有属性值
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryPropPropvalCheck(PageBean pageBean) throws Exception;
	
	/**
	 * 查询该类别下的所有属性和属性值
	 * @param cateId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List queryPropPropValByCateId(String cateId) throws Exception;
}
