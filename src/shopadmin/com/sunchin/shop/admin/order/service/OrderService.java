package com.sunchin.shop.admin.order.service;

import java.util.List;

import com.sunchin.shop.admin.pojo.ScOrder;

import framework.bean.PageBean;

public interface OrderService {

	/**
	 * 分页查询订单
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryOrderList(PageBean pageBean) throws Exception;
	
	/**
	 * 查询某条订单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ScOrder> queryOrderById(String id) throws Exception;
	
	/**
	 * 删除订单
	 * @param id
	 * @throws Exception
	 */
	public void delOrder(String id) throws Exception;

}
