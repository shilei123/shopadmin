package com.sunchin.shop.admin.propValue.service;

import com.sunchin.shop.admin.pojo.ScPropval;

import framework.bean.PageBean;

public interface PropValueService {

	/**
	 * 查询属性值列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryPropValueList(PageBean pageBean) throws Exception;

	/**
	 * 删除属性值
	 * @param id
	 * @throws Exception
	 */
	public void delPropValue(String id) throws Exception;

	/**
	 * 修改属性值
	 * @param property
	 * @throws Exception
	 */
	public void updatePropValue(ScPropval propValue) throws Exception;

	/**
	 * 新增属性值
	 * @param property
	 * @throws Exception
	 */
	public void addPropValue(ScPropval propValue) throws Exception;

	/**
	 * 查询某个
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScPropval queryPropValue(String id) throws Exception;
}
