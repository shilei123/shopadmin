package com.sunchin.shop.admin.freight.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScFreight;

import framework.bean.PageBean;

public interface IFreightService {

	PageBean queryFreightList(PageBean pageBean) throws Exception;

	List<Map<String, Object>> findFreightList(String id) throws Exception;

	void deleteFreight(String id) throws Exception;


}
