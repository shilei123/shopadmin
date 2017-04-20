package com.sunchin.shop.admin.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.OrderStsEnum;
import com.sunchin.shop.admin.order.dao.OrderDAO;
import com.sunchin.shop.admin.order.dao.OrderDetailDAO;
import com.sunchin.shop.admin.order.service.OrderDetailService;
import com.sunchin.shop.admin.pojo.ScOrder;

@SuppressWarnings("rawtypes")
@Repository("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

	@Resource(name="orderDetailDAO")
	private OrderDetailDAO orderDetailDAO;
	@Resource(name="orderDAO")
	private OrderDAO orderDAO;

	public List<Map> queryOrderDetailByOrderId(String orderId) throws Exception {
		List<Map> list = orderDetailDAO.queryOrderDetailByOrderId(orderId);
		return list;
	}

	@Override
	public List<Map> queryConfirmOrder(String id) throws Exception {
		List<Map> list = orderDetailDAO.queryConfirmOrder(id);
		return list;
	}

	@Override
	public String confirmOrder(String id) throws Exception {
		ScOrder orderParent = orderDAO.queryOrderById(id);
		//首先确认父订单状态，再做操作
		if(orderParent==null)
			return "未知错误！";
		String orderSts = orderParent.getOrderStatus();
		if(orderSts.isEmpty())
			return "该订单已经确认，无须再次确认！";
		if(!orderSts.equals(OrderStsEnum.UNCONFIRM.getCode()))
			return "该订单状态异常，请检查后处理！";
		String issplit = orderParent.getIssplit();
		
		//修改父订单状态
		orderDAO.confirmOrder(id);
		//增加订单详情的子订单id
		//增加子订单记录
		//增加订单操作历史记录
		return null;
	}
	
}
