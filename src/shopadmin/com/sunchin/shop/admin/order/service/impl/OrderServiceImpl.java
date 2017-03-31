package com.sunchin.shop.admin.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.order.dao.OrderDAO;
import com.sunchin.shop.admin.order.service.IOrderService;

@Repository("orderService")
public class OrderServiceImpl implements IOrderService {

	@Resource(name="orderDAO")
	private OrderDAO orderDAO;
	
	
	
	
}
