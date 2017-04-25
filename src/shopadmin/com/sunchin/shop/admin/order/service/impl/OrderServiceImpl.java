package com.sunchin.shop.admin.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.OrderIssplitEnum;
import com.sunchin.shop.admin.dict.OrderStsEnum;
import com.sunchin.shop.admin.invoice.dao.InvoiceRecordDAO;
import com.sunchin.shop.admin.order.dao.DeliveryRecordDAO;
import com.sunchin.shop.admin.order.dao.OrderDAO;
import com.sunchin.shop.admin.order.dao.OrderDetailDAO;
import com.sunchin.shop.admin.order.service.OrderService;
import com.sunchin.shop.admin.order.util.DeliveryRecordStsEnum;
import com.sunchin.shop.admin.order.util.GenerateDeliveryRecordCodeUtil;
import com.sunchin.shop.admin.order.util.GenerateOrderCodeUtil;
import com.sunchin.shop.admin.pojo.ScDeliveryRecord;
import com.sunchin.shop.admin.pojo.ScInvoiceRecord;
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
	@Resource(name="invoiceRecordDAO")
	private InvoiceRecordDAO invoiceRecordDAO;

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

	@Transactional
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
			orderDAO.confirmOrder(id,OrderStsEnum.UNDELIVERY.getCode());
		}else if(issplit.equals(OrderIssplitEnum.YES.getCode())){
			orderDAO.confirmOrder(id, null);
			msg = this.dealOrder(orderParent, splitOrderStr);//拆分成子订单
		}
		return msg;
	}
	
	/**
	 * 订单拆分对数据库的操作
	 * @param order
	 * @param splitOrderStr
	 * @return
	 */
	private String dealOrder(ScOrder order, String splitOrderStr) {
		String invoiceRecordId_son1 = UUID.randomUUID().toString();
		String invoiceRecordId_son2 = UUID.randomUUID().toString();
		String deliveryId_son1 = UUID.randomUUID().toString();
		String deliveryId_son2 = UUID.randomUUID().toString();
		String orderId_son1 = UUID.randomUUID().toString();
		String orderId_son2 = UUID.randomUUID().toString();
		
		String orderId = CommonUtils.getString(order.getId());
		//发票流水记录表，修改原发票的flag，增加两条发票记录
		String invoiceRecordId = CommonUtils.getString(order.getInvoiceRecordId());
		invoiceRecordDAO.delInvoiceRecordById(invoiceRecordId);
		this.addInvoiceRecord(invoiceRecordId_son1, invoiceRecordId);
		this.addInvoiceRecord(invoiceRecordId_son2, invoiceRecordId);
		
		//发货记录表修改父订单flag，增加子发货记录
		ScDeliveryRecord deliveryRecord = deliveryRecordDAO.queryDeliveryByOrderId(orderId);
		deliveryRecordDAO.delDelivery(deliveryRecord.getId());
		this.addDeliveryRecord(deliveryId_son1, deliveryRecord, orderId_son1);
		this.addDeliveryRecord(deliveryId_son2, deliveryRecord, orderId_son2);
		
		//订单表修改父订单状态（已处理），增加子订单记录
		List<Map> list = orderDetailDAO.queryOrderGoodsByOrderId(orderId);
		List<Map> listA = new ArrayList<Map>();//子订单A的商品信息
		List<Map> listB = new ArrayList<Map>();
		String[] arr = splitOrderStr.split(",");
//		查到的商品信息比对splitOrderStr，拆分成两个子订单
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].isEmpty()) continue;
			for (int j = 0; j < list.size(); j++) {
				String childGoodsId = CommonUtils.getString(list.get(i).get("childGoodsId"));
				String goodsId = CommonUtils.getString(list.get(i).get("goodsId"));
				boolean isOrderA = false;
				if(arr[i].equals(childGoodsId) || arr[i].equals(goodsId)){
					isOrderA = true;
				}
				if(isOrderA){
					listA.add(list.get(j));
				}else{
					listB.add(list.get(j));
				}
			}
		}
		this.dealOrder(orderId_son1, order, invoiceRecordId_son1, deliveryId_son1);
		
		//订单详情表对应记录增加子订单id
		this.updateOrderDetail(orderId, orderId_son1, orderId_son2);
		
		//订单操作历史表增加记录
		//this.addOrderRecord();
		return "";
	}

	private void updateOrderDetail(String orderId, String orderId_son1, String orderId_son2) {
		//splitCheckIds和商品比对，拆分成子订单A和子订单B,订单表生成子订单A、B记录
		//几个商品就几条详情
		List<Map> list = orderDetailDAO.queryConfirmOrder(orderId);
		for (int i = 0; i < list.size(); i++) {
			
		}
	}

	private void addDeliveryRecord(String deliveryId_son, ScDeliveryRecord deliveryRecord, String orderId_son) {
		ScDeliveryRecord deliveryRecord_son = new ScDeliveryRecord();
		deliveryRecord_son.setId(deliveryId_son);
		deliveryRecord_son.setDeliveryType(CommonUtils.getString(deliveryRecord.getDeliveryType()));
		deliveryRecord_son.setAfterSalesType(CommonUtils.getString(deliveryRecord.getAfterSalesType()));
		deliveryRecord_son.setCode(GenerateDeliveryRecordCodeUtil.generateCode());
		deliveryRecord_son.setOrderId(orderId_son);
		deliveryRecord_son.setSts(DeliveryRecordStsEnum.UNDELIVERY.getCode());
		deliveryRecord_son.setAddressId(CommonUtils.getString(deliveryRecord.getAddressId()));
		deliveryRecord_son.setProvince(CommonUtils.getString(deliveryRecord.getProvince()));
		deliveryRecord_son.setCity(CommonUtils.getString(deliveryRecord.getCity()));
		deliveryRecord_son.setCounty(CommonUtils.getString(deliveryRecord.getCounty()));
		deliveryRecord_son.setAddressDetail(CommonUtils.getString(deliveryRecord.getAddressDetail()));
		deliveryRecord_son.setName(CommonUtils.getString(deliveryRecord.getName()));
		deliveryRecord_son.setPostNum(CommonUtils.getString(deliveryRecord.getPostNum()));
		deliveryRecord_son.setPhoneNum(CommonUtils.getString(deliveryRecord.getPhoneNum()));
		deliveryRecord_son.setTelphoneNum(CommonUtils.getString(deliveryRecord.getTelphoneNum()));
		deliveryRecord_son.setEmail(CommonUtils.getString(deliveryRecord.getEmail()));
		deliveryRecord_son.setCreateTime(new Date());
		deliveryRecord_son.setFlag(FlagEnum.ACT.getCode());
		db.insert(deliveryRecord_son);
	}

	private void dealOrder(String orderId_son, ScOrder order, String invoiceRecordId_son, String deliveryId_son) {
		/*ScOrder order_son = new ScOrder();
		order_son.setId(orderId_son);
		order_son.setOrderCode(GenerateOrderCodeUtil.generateOrderCode());
		order_son.setNum();
		order_son.setDeliveryMode(CommonUtils.getString(order.getDeliveryMode()));
		order_son.setOrderStatus(OrderStsEnum.UNDELIVERY.getCode());
		order_son.setIssplit(OrderIssplitEnum.YES.getCode());
		order_son.setSplitTime(new Date());
		order_son.setInvoice(CommonUtils.getString(order.getInvoice()));
		order_son.setInvoiceRecordId(invoiceRecordId_son);
		order_son.setDeliveryRecordId(deliveryId_son);
		order_son.setRemark(CommonUtils.getString(order.getRemark()));
		order_son.setPayMode(CommonUtils.getString(order.getPayMode()));
		//订单拆分、手续费、数量、总价、订单金额、实付金额！！！！暂时未加
		order_son.setCommisionCharge();
		order_son.set
		db.insert(order_son);*/
	}

	private void addInvoiceRecord(String invoiceRecordId_son, String invoiceRecordId) {
		ScInvoiceRecord invoiceRecord = invoiceRecordDAO.queryById(invoiceRecordId);
		ScInvoiceRecord invoiceRecord_son = new ScInvoiceRecord();
		invoiceRecord_son.setId(invoiceRecordId_son);
		invoiceRecord_son.setUserId(CommonUtils.getString(invoiceRecord.getUserId()));
		invoiceRecord_son.setInvoiceId(CommonUtils.getString(invoiceRecord.getInvoiceId()));
		invoiceRecord_son.setInvoiceHeaderId(CommonUtils.getString(invoiceRecord.getInvoiceHeaderId()));
		invoiceRecord_son.setContent(CommonUtils.getString(invoiceRecord.getContent()));
		invoiceRecord_son.setRemark(CommonUtils.getString(invoiceRecord.getRemark()));
		invoiceRecord_son.setCreateTime(new Date());
		invoiceRecord_son.setBelong(CommonUtils.getString(invoiceRecord.getBelong()));
		invoiceRecord_son.setFlag(FlagEnum.ACT.getCode());
		db.insert(invoiceRecord_son);
 	}

	@Override
	public void cancelOrder(String id) throws Exception {
		/*List<ScOrder> list = orderDAO.querySonOrderByParentOrderId(id);
		for (ScOrder order : list) {
			String orderId = order.getId();
			orderDAO.cancelOrder(orderId);
		}
		orderDAO.cancelOrder(id);*/
	}

	@Override
	public void changePriceOrder(String id, Double actualPrice)throws Exception {
		orderDAO.changePriceOrder(id, actualPrice);
	}
	
}
