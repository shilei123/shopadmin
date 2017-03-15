package com.sunchin.shop.admin.advertise.service;

import java.util.Map;

import com.sunchin.shop.admin.pojo.ScAdvertise;

import framework.bean.PageBean;

public interface IAdvertiseService {

	PageBean queryAdvertiseList(PageBean pageBean);

	void deleteAdvertise(String id);

	void saveAdvertise(ScAdvertise advertise);

	ScAdvertise queryAdvertise(String id);

	Map<String, Object> findAdvertise(String id);

}
