package com.sunchin.shop.admin.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.order.dao.DeliveryManageDAO;
import com.sunchin.shop.admin.order.service.DeliveryManageService;

import framework.bean.PageBean;

@Repository("deliveryManageService")
public class DeliveryManageServiceImpl implements DeliveryManageService {

	@Resource(name="deliveryManageDAO")
	private DeliveryManageDAO deliveryManageDAO;
	
	@Override
	public PageBean queryDeliveryOrderList(PageBean pageBean) throws Exception {
		int total = deliveryManageDAO.queryDeliveryOrderCount(pageBean);
		pageBean.setTotal(total);
		List<Map<String, Object>> pageData = deliveryManageDAO.queryDeliveryOrderPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
}
