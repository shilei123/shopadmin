package com.sunchin.shop.admin.afterManagement.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScBill;
import com.sunchin.shop.admin.pojo.ScUserBase;

import framework.bean.PageBean;

public interface IAfterService {

	PageBean queryBill(PageBean pageBean) throws Exception;

	List<Map<String,Object>> findBillTetail(String id) throws Exception;

	void passReturnGoods(String id, String result,String relus) throws Exception;

	void NopassReturnGoods(String id, String result) throws Exception;

	void passChangeGoods(String id, String result, String relus) throws Exception;

	void passRepairGoods(String id, String result) throws Exception;

	void passRefundGoods(String id, String result) throws Exception;

	ScUserBase queryUserBase(String userId) throws Exception;

	List<Map<String, Object>> queryGoodsDetail(String id) throws Exception;
	
}
