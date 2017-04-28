package com.sunchin.shop.admin.order.service;

import java.util.List;
import java.util.Map;

import framework.bean.PageBean;

@SuppressWarnings("rawtypes")
public interface DeliveryRecordService {

	/**
	 * 发货页面的分页查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PageBean queryDeliveryOrderList(PageBean pageBean) throws Exception;

	/**
	 * 发货页面查询订单商品信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Map> queryDeliveryGoodsById(String id) throws Exception;

	/**
	 * 发货页面查询收货地址信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map queryDeliveryAddressById(String id) throws Exception;
	
	/**
	 * 发货页面查询发票信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Map> queryDeliveryInvoiceById(String id) throws Exception;
}
