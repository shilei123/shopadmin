package com.sunchin.shop.admin.freight.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScFreight;

import framework.bean.PageBean;

public interface IFreightService {

	PageBean queryFreightList(PageBean pageBean) throws Exception;

	List<Map<String, Object>> findFreightList(String id) throws Exception;

	void deleteFreight(String id) throws Exception;

	void save(ScFreight fre, String childUserFreightList) throws Exception;

	void delUserFreight(String id) throws Exception;

	void saveIsuse(String id) throws Exception;

	PageBean queryFreAndExpList(PageBean pageBean) throws Exception;

	List<Map<String, Object>> queryFreAndExpCheck(PageBean pageBean) throws Exception;

	void saveFreightAndExpress(PageBean pageBean) throws Exception;

}
