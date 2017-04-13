package com.sunchin.shop.admin.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.order.dao.OrderDetailDAO;
import com.sunchin.shop.admin.order.service.OrderDetailService;

@Repository("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

	@Resource(name="orderDetailDAO")
	private OrderDetailDAO orderDetailDAO;

	@SuppressWarnings("rawtypes")
	public List<Map> queryByOrderId(String orderId) throws Exception {
		List<Map> list = orderDetailDAO.queryByOrderId(orderId);
		return list;
	}
	
}
