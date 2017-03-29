package com.sunchin.shop.admin.catePropPropval.service;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface CatePropPropvalService {

	/**
	 * 新增类别属性属性值关系
	 * @param cateId
	 * @param catePropPropValIds
	 * @throws Exception
	 */
	public void saveCatePropPropVal(String cateIds, String propPropValIds) throws Exception;
	
	/**
	 * 查询类别树
	 * @return
	 * @throws Exception
	 */
	public List<Map> queryCategoryTree() throws Exception;
	
	/**
	 * 查询该类别下的所有属性和属性值
	 * @param cateId
	 * @return
	 * @throws Exception
	 */
	public List queryListByCateId(String cateId) throws Exception;

	/**
	 * 查询该类别所有选中的属性和属性值
	 * @param cateId
	 * @return
	 * @throws Exception
	 */
	public List queryListCheck(String cateId)throws Exception;
}
