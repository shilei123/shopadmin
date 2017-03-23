package com.sunchin.shop.admin.freight.action;

import javax.annotation.Resource;

import com.sunchin.shop.admin.freight.service.IFreightService;

import framework.action.PageAction;

public class FreightAction extends PageAction{

	@Resource(name="freightService")
	private IFreightService freightService;
	
	
	
}
