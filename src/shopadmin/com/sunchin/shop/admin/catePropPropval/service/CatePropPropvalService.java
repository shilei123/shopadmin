package com.sunchin.shop.admin.catePropPropval.service;

import java.util.List;

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
	 * 根据类别查询类别-属性-属性值关系 add by yangchaowen
	 * @param cateId
	 * @return
	 * @date 2017-03-30
	 */
	public List queryByCateId(String cateId) throws Exception;
	
	/**
	 * 查询该类别所有选中的属性和属性值
	 * @param cateId
	 * @return
	 * @throws Exception
	 */
	public List queryListCheck(String cateId)throws Exception;
}
