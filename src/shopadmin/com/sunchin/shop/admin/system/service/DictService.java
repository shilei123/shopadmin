package com.sunchin.shop.admin.system.service;

import java.util.List;

import com.sunchin.shop.admin.pojo.ScDictionary;

import framework.bean.PageBean;

public interface DictService {
	
	/**
	 * 鏌ヨ
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	PageBean queryDictList(PageBean pageBean) throws Exception; 
	
	/**
	 * 鏌ヨ鍗曟潯璁板綍
	 * @param id
	 * @return
	 */
	List<ScDictionary> getDictByType(String type) throws Exception;
	
	/**
	 * 鏌ヨ鍗曟潯璁板綍
	 * @param id
	 * @return
	 */
	ScDictionary getDict(String id) throws Exception;
	
	/**
	 * 淇濆瓨
	 * @param dict
	 * @return 
	 * @throws Exception
	 */
	void saveDict(ScDictionary dict) throws Exception;
	
	/**
	 * 鍒犻櫎
	 * @param id
	 * @throws Exception
	 */

	void deleteDict(String id) throws Exception;

}
