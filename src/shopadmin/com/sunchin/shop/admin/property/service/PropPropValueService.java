package com.sunchin.shop.admin.property.service;

import java.util.List;
import java.util.Map;

import framework.bean.PageBean;

public interface PropPropValueService {

	/**
	 * 保存属性属性值关系
	 * @param propertyId
	 * @param checkPropPropValueIds	用逗号隔开的字符串
	 * @throws Exception
	 */
	public void savePropPropValue(PageBean pageBean) throws Exception;
	
	/**
	 * 分页查询所有属性值
	 * @param pageBean
	 * @throws Exception
	 */
	public PageBean queryPropPropValue(PageBean pageBean) throws Exception;
	
	/**
	 * 查询该属性的所有属性值
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryPropPropValueCheck(PageBean pageBean) throws Exception;
	
}
