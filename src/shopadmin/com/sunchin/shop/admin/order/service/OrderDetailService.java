package com.sunchin.shop.admin.order.service;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {

	/**
	 * 查询订单商品信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryByOrderId(String id) throws Exception;

}
