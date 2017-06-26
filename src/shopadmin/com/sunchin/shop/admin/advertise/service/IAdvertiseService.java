package com.sunchin.shop.admin.advertise.service;

import java.util.Map;

import com.sunchin.shop.admin.pojo.ScAdvertise;

import framework.bean.PageBean;

public interface IAdvertiseService {

	PageBean queryAdvertiseList(PageBean pageBean) throws Exception;

	void deleteAdvertise(String id) throws Exception;

	void saveAdvertise(ScAdvertise advertise) throws Exception;

	ScAdvertise queryAdvertise(String id)throws Exception;

	Map<String, Object> findAdvertise(String id)throws Exception;

	PageBean queryGoodsList(PageBean pageBean) throws Exception;

}
