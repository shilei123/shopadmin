package com.sunchin.shop.admin.propValue.service;

import com.sunchin.shop.admin.pojo.ScPropValue;
import com.sunchin.shop.admin.pojo.ScProperty;

import framework.bean.PageBean;

public interface PropValueService {

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
	public void updateProperty(ScPropValue propValue) throws Exception;

	/**
	 * 新增属性
	 * @param property
	 * @throws Exception
	 */
	public void addProperty(ScPropValue propValue) throws Exception;

	/**
	 * 查询某个
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScPropValue queryProperty(String id) throws Exception;
}
