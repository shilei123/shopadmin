package com.sunchin.shop.admin.order.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.order.service.IOrderService;

import framework.action.PageAction;

public class OrderAction extends PageAction{

	@Resource(name="orderService")
	private IOrderService orderService;
	
	
	public String queryOrder(){
	
			
	
		return Action.SUCCESS;
	}	
	
	
}
