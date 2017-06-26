package com.sunchin.shop.admin.order.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.util.UserDefinedException;

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
	
	/**
	 * 发货页面查询是否虚拟商品
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Map> judgeVirtualGoods(String id) throws Exception;
	
	/**
	 * 发货页面发货
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String delivery(String orderId, Map deliveryMap) throws Exception, UserDefinedException;

	/**
	 * 查询发货记录表的发货状态，控制重复发货
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String checkOrderDeliverySts(String id) throws Exception;
}
