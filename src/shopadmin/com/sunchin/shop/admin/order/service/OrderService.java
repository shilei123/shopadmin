package com.sunchin.shop.admin.order.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScOrder;

import framework.bean.PageBean;

@SuppressWarnings("rawtypes")
public interface OrderService {

	/**
	 * 分页查询订单
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryOrderList(PageBean pageBean) throws Exception;
	
	/**
	 * 查询订单基础信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map queryOrderById(String id) throws Exception;
	
	/**
	 * 删除订单
	 * @param id
	 * @throws Exception
	 */
	public void delOrder(String id) throws Exception;

	/**
	 * 查询子订单
	 * @param id
	 * @throws Exception
	 */
	public List<ScOrder> querySonOrderById(String id) throws Exception;

}
