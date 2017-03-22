package com.sunchin.shop.admin.freight.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.freight.dao.FreightDAO;
import com.sunchin.shop.admin.freight.service.IFreightService;

@Repository("freightService")
public class FreightServiceImpl implements IFreightService{

	@Resource(name="freightDAO")
	private FreightDAO freightDAO;
	
	
	
	
}
