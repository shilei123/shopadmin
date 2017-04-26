package com.sunchin.shop.admin.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.OrderIssplitEnum;
import com.sunchin.shop.admin.dict.OrderStsEnum;
import com.sunchin.shop.admin.order.dao.DeliveryRecordDAO;
import com.sunchin.shop.admin.order.dao.OrderDAO;
import com.sunchin.shop.admin.order.dao.OrderDetailDAO;
import com.sunchin.shop.admin.order.service.OrderService;
import com.sunchin.shop.admin.pojo.ScDeliveryRecord;
import com.sunchin.shop.admin.pojo.ScOrder;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.CommonUtils;

@SuppressWarnings("rawtypes")
@Repository("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource(name="orderDAO")
	private OrderDAO orderDAO;
	@Resource(name="orderDetailDAO")
	private OrderDetailDAO orderDetailDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	@Resource(name="deliveryRecordDAO")
	private DeliveryRecordDAO deliveryRecordDAO;

	@Override
	public PageBean queryOrderList(PageBean pageBean) throws Exception {
		int total = orderDAO.queryOrderCount(pageBean);
		pageBean.setTotal(total);
		List<Map<String, Object>> pageData = orderDAO.queryOrderPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	@Override
	public Map queryOrderBasicInfoById(String id) throws Exception {
		ScOrder order = orderDAO.queryOrderById(id);
		if(order==null) return null;
		Map map = orderDAO.queryOrderBasicInfoById(id, order);
		return map;
	}

	/**
	 * empty method
	 */
	@Override
	public void delOrder(String id) throws Exception {
		
	}

	@Override
	public List<ScOrder> querySonOrderById(String id) throws Exception {
		ScOrder order = orderDAO.queryOrderById(id);
		if(order==null) return null;
		String invoiceRecordId = CommonUtils.getString(order.getInvoiceRecordId());
		List<ScOrder> list = orderDAO.querySonOrderById(id, invoiceRecordId);
		return list;
	}

	@Override
	public String confirmOrder(String id, String splitOrderStr) throws Exception {
		String msg = "";
		ScOrder orderParent = orderDAO.queryOrderById(id);
		//首先确认父订单状态，再做操作
		if(orderParent==null)
			return "数据库没有找到该订单信息！";
		String orderSts = CommonUtils.getString(orderParent.getOrderStatus());
		if(orderSts.isEmpty())
			return "该订单已经确认，无须再次确认！";
		if(!orderSts.equals(OrderStsEnum.UNCONFIRM.getCode()))
			return "该订单状态异常，请检查后处理！";
		String issplit = orderParent.getIssplit();
		if(issplit.equals(OrderIssplitEnum.NO.getCode())){
			//修改父订单状态
			orderDAO.confirmOrder(id,OrderStsEnum.UNDELIVERY.getCode());
		}else if(issplit.equals(OrderIssplitEnum.YES.getCode())){
			//拆分成子订单
			//修改父订单状态
			orderDAO.confirmOrder(id, null);
			//其他表的操作
			msg = this.dealOrder(orderParent, splitOrderStr);
		}
		return msg;
	}
	
	private String dealOrder(ScOrder order, String splitOrderStr) {
		String orderId = CommonUtils.getString(order.getId());
		//订单表增加子订单记录
		ScOrder order_son1 = new ScOrder();
		ScOrder order_son2 = new ScOrder();
		String orderId_son1 = UUID.randomUUID().toString();
		String orderId_son2 = UUID.randomUUID().toString();
		String deliveryId_son1 = UUID.randomUUID().toString();
		String deliveryId_son2 = UUID.randomUUID().toString();
		order_son1.setId(orderId_son1);
		order_son2.setId(orderId_son2);
		//订单编号生成规则先省略
		order_son1.setDeliveryMode(CommonUtils.getString(order.getDeliveryMode()));
		order_son2.setDeliveryMode(CommonUtils.getString(order.getDeliveryMode()));
		order_son1.setOrderStatus(OrderStsEnum.UNDELIVERY.getCode());
		order_son2.setOrderStatus(OrderStsEnum.UNDELIVERY.getCode());
		order_son1.setIssplit(OrderIssplitEnum.YES.getCode());
		order_son2.setIssplit(OrderIssplitEnum.YES.getCode());
		order_son1.setSplitTime(new Date());
		order_son2.setSplitTime(new Date());
		order_son1.setInvoice(CommonUtils.getString(order.getInvoice()));
		order_son2.setInvoice(CommonUtils.getString(order.getInvoice()));
		//确认订单的时候还没有发票记录吧
		order_son1.setDeliveryRecordId(deliveryId_son1);
		order_son2.setDeliveryRecordId(deliveryId_son2);
		
		db.insert(order_son1);
		db.insert(order_son2);
		
		//发货记录表修改父订单flag
		ScDeliveryRecord  deliveryRecord = deliveryRecordDAO.queryDeliveryByOrderId(orderId);
		if(deliveryRecord==null) return "error";
		deliveryRecordDAO.delDelivery(deliveryRecord.getId());
		//发货记录表增加子订单记录
		
		//订单详情表对应记录增加子订单id
		List<Map> list = orderDetailDAO.queryConfirmOrder(orderId);
		if(list==null) return "error";
		
		//订单操作历史表增加记录
		
		return "";
	}

	@Override
	public void cancelOrder(String id) throws Exception {
		List<ScOrder> list = orderDAO.querySonOrderByParentOrderId(id);
		for (ScOrder order : list) {
			String orderId = order.getId();
			orderDAO.cancelOrder(orderId);
		}
		orderDAO.cancelOrder(id);
	}

	@Override
	public void changePriceOrder(String id, Double actualPrice)throws Exception {
		orderDAO.changePriceOrder(id, actualPrice);
	}
	
}
