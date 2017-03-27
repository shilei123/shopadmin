package com.sunchin.shop.admin.catePropPropval.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScCatePropPropVal;

@SuppressWarnings("rawtypes")
public interface CatePropPropvalService {

	/**
	 * 新增类别属性属性值关系
	 * @param catePropPropVal
	 * @throws Exception
	 */
	public void saveCatePropPropVal(ScCatePropPropVal catePropPropVal) throws Exception;
	
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
}
