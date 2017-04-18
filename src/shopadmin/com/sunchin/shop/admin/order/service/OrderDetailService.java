package com.sunchin.shop.admin.order.service;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface OrderDetailService {

	/**
	 * 查询订单商品信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Map> queryOrderDetailByOrderId(String id) throws Exception;
	
	/**
	 * 确认订单（查询商品）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Map> queryConfirmOrder(String id) throws Exception;

}
