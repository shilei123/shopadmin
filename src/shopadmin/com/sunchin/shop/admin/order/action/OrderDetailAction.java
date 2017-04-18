package com.sunchin.shop.admin.order.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.order.service.OrderDetailService;
import com.sunchin.shop.admin.pojo.ScOrder;

import framework.action.PageAction;

@SuppressWarnings("rawtypes")
public class OrderDetailAction extends PageAction{

	@Resource(name="orderDetailService")
	private OrderDetailService orderDetailService;
	
	private ScOrder order;
	private List<Map> orderGoods;//商品信息
	
	/**
	 * 确认订单（拆分子订单）
	 * @return
	 */
	public String queryConfirmOrder(){
		try {
			orderGoods = orderDetailService.queryConfirmOrder(order.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public ScOrder getOrder() {
		return order;
	}

	public void setOrder(ScOrder order) {
		this.order = order;
	}

	public List<Map> getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(List<Map> orderGoods) {
		this.orderGoods = orderGoods;
	}

}
