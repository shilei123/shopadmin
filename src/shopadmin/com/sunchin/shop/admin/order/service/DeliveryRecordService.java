package com.sunchin.shop.admin.order.service;

import framework.bean.PageBean;

public interface DeliveryRecordService {

	/**
	 * 分页查询需要发货管理订单信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PageBean queryDeliveryOrderList(PageBean pageBean) throws Exception;

}
