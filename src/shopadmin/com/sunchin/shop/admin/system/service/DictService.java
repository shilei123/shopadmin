package com.sunchin.shop.admin.system.service;

import com.sunchin.shop.admin.pojo.ScDictionary;

import framework.bean.PageBean;

public interface DictService {
	
	/**
	 * 查询
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryDictList(PageBean pageBean) throws Exception; 
	
	/**
	 * 查询单条记录
	 * @param id
	 * @return
	 */
	public ScDictionary getDict(String id) throws Exception;
	
	/**
	 * 保存
	 * @param dict
	 * @return 
	 * @throws Exception
	 */
	public void saveDict(ScDictionary dict) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteDict(String id) throws Exception;
	
}