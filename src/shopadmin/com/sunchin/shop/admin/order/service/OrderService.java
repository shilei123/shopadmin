package com.sunchin.shop.admin.order.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScOrder;

import framework.bean.PageBean;

@SuppressWarnings("rawtypes")
public interface OrderService {

	/**
	 * 分页查询订单（不包括子订单）
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
	public Map queryOrderBasicInfoById(String id) throws Exception;
	
	/**
	 * 删除订单（empty method）
	 * @param id
	 * @throws Exception
	 */
	public void delOrder(String id) throws Exception;

	/**
	 * 查询子订单详情
	 * @param id
	 * @throws Exception
	 */
	public List<ScOrder> querySonOrderById(String id) throws Exception;

	/**
	 * 确认订单（拆分子订单）
	 * @param id
	 * @param splitOrderStr（拆分的子订单之一的字符串）
	 * @throws Exception
	 */
	public String confirmOrder(String id, String splitOrderStr) throws Exception;

	/**
	 * 取消订单（包括子订单）
	 * @param id
	 * @throws Exception
	 */
	public void cancelOrder(String id) throws Exception;

	/**
	 * 修改订单费用
	 * @param id
	 * @param actualPrice
	 * @throws Exception
	 */
	public void changePriceOrder(String id, Double actualPrice) throws Exception;
	

}
