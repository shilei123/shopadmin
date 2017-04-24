package com.sunchin.shop.admin.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.order.dao.OrderDAO;
import com.sunchin.shop.admin.order.dao.OrderDetailDAO;
import com.sunchin.shop.admin.order.service.OrderDetailService;

@SuppressWarnings("rawtypes")
@Repository("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

	@Resource(name="orderDetailDAO")
	private OrderDetailDAO orderDetailDAO;
	@Resource(name="orderDAO")
	private OrderDAO orderDAO;

	public List<Map> queryOrderGoodsByOrderId(String orderId) throws Exception {
		List<Map> list = orderDetailDAO.queryOrderGoodsByOrderId(orderId);
		return list;
	}

	@Override
	public List<Map> queryConfirmOrder(String id) throws Exception {
		List<Map> list = orderDetailDAO.queryConfirmOrder(id);
		return list;
	}
	
}
