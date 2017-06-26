package com.sunchin.shop.admin.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.order.dao.DeliveryRecordDAO;
import com.sunchin.shop.admin.order.service.DeliveryRecordService;

import framework.bean.PageBean;

@SuppressWarnings("rawtypes")
@Repository("deliveryRecordService")
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

	@Resource(name="deliveryRecordDAO")
	private DeliveryRecordDAO deliveryRecordDAO;
	
	@Override
	public PageBean queryDeliveryOrderList(PageBean pageBean) throws Exception {
		int total = deliveryRecordDAO.queryDeliveryOrderCount(pageBean);
		pageBean.setTotal(total);
		List<Map<String, Object>> pageData = deliveryRecordDAO.queryDeliveryOrderPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	@Override
	public List<Map> queryDeliveryGoodsById(String id) throws Exception {
		List<Map> list = deliveryRecordDAO.queryDeliveryGoodsById(id);
		return list;
	}

	@Override
	public Map queryDeliveryAddressById(String id) throws Exception {
		Map map = deliveryRecordDAO.queryDeliveryAddressById(id);
		return map;
	}
	
	@Override
	public List<Map> queryDeliveryInvoiceById(String id) throws Exception {
		List<Map> list = deliveryRecordDAO.queryDeliveryInvoiceById(id);
		return list;
	}
}
