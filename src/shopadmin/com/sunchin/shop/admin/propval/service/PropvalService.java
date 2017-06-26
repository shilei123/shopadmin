package com.sunchin.shop.admin.propval.service;

import com.sunchin.shop.admin.pojo.ScPropval;

import framework.bean.PageBean;

public interface PropvalService {

	/**
	 * 查询属性值列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryPropvalList(PageBean pageBean) throws Exception;

	/**
	 * 删除属性值
	 * @param id
	 * @throws Exception
	 */
	public void delPropval(String id) throws Exception;

	/**
	 * 修改属性值
	 * @param propval
	 * @throws Exception
	 */
	public void updatePropval(ScPropval propval) throws Exception;

	/**
	 * 新增属性值
	 * @param propval
	 * @throws Exception
	 */
	public String addPropval(ScPropval propval) throws Exception;

	/**
	 * 查询某个
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScPropval queryPropval(String id) throws Exception;
}
