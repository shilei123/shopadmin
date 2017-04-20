package com.sunchin.shop.admin.afterManagement.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScBill;

import framework.bean.PageBean;

public interface IAfterService {

	PageBean queryBill(PageBean pageBean) throws Exception;

	List<Map<String,Object>> findBillTetail(String id) throws Exception;

	void deleteBill(String id) throws Exception;

	void passReturnGoods(String id, String result) throws Exception;

	void NopassReturnGoods(String id, String result) throws Exception;

	
}
