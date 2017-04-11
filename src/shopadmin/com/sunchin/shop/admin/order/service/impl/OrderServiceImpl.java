package com.sunchin.shop.admin.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.order.dao.OrderDAO;
import com.sunchin.shop.admin.order.service.OrderService;
import com.sunchin.shop.admin.pojo.ScOrder;

import framework.bean.PageBean;

@Repository("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource(name="orderDAO")
	private OrderDAO orderDAO;

	@Override
	public PageBean queryOrderList(PageBean pageBean) throws Exception {
		int total = orderDAO.queryOrderCount(pageBean);
		pageBean.setTotal(total);
		List<Map<String, Object>> pageData = orderDAO.queryOrderPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	@Override
	public List<ScOrder> queryOrderById(String id) throws Exception {
		ScOrder order = orderDAO.queryOrderById(id);
		if(order==null) return null;
		List<ScOrder> list = orderDAO.queryOrderDetailById(id, order.getInvoiceRecordId(), order.getParentOrderId());
		return list;
	}

	/**
	 * empty method
	 */
	@Override
	public void delOrder(String id) throws Exception {
		
	}
	
}
