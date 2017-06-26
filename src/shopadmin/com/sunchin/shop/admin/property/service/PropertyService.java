package com.sunchin.shop.admin.property.service;

import com.sunchin.shop.admin.pojo.ScProperty;

import framework.bean.PageBean;

public interface PropertyService {

	/**
	 * 查询属性列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryPropertyList(PageBean pageBean) throws Exception;

	/**
	 * 删除属性
	 * @param id
	 * @throws Exception
	 */
	public void delProperty(String id) throws Exception;

	/**
	 * 修改属性
	 * @param property
	 * @throws Exception
	 */
	public void updateProperty(ScProperty property) throws Exception;

	/**
	 * 新增属性
	 * @param property
	 * @throws Exception
	 */
	public void addProperty(ScProperty property) throws Exception;

	/**
	 * 查询某个
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScProperty queryProperty(String id) throws Exception;
}
