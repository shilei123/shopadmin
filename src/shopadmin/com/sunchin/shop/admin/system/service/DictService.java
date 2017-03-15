package com.sunchin.shop.admin.system.service;

import java.util.List;

import com.sunchin.shop.admin.pojo.ScDictionary;

import framework.bean.PageBean;

public interface DictService {
	
	/**
	 * 查询
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	PageBean queryDictList(PageBean pageBean) throws Exception; 
	
	/**
	 * 查询单条记录
	 * @param id
	 * @return
	 */
	List<ScDictionary> getDictByType(String type) throws Exception;
	
	/**
	 * 查询单条记录
	 * @param id
	 * @return
	 */
	ScDictionary getDict(String id) throws Exception;
	
	/**
	 * 保存
	 * @param dict
	 * @return 
	 * @throws Exception
	 */
	void saveDict(ScDictionary dict) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteDict(String id) throws Exception;
}