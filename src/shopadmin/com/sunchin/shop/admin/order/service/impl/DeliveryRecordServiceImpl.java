package com.sunchin.shop.admin.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.OrderStsEnum;
import com.sunchin.shop.admin.dict.repertoryTypeEnum;
import com.sunchin.shop.admin.invoice.dao.InvoiceRecordDAO;
import com.sunchin.shop.admin.order.dao.DeliveryRecordDAO;
import com.sunchin.shop.admin.order.dao.OrderDAO;
import com.sunchin.shop.admin.order.dao.OrderDetailDAO;
import com.sunchin.shop.admin.order.service.DeliveryRecordService;
import com.sunchin.shop.admin.order.util.DeliveryRecordStsEnum;
import com.sunchin.shop.admin.pojo.ScDeliveryRecord;
import com.sunchin.shop.admin.pojo.ScOrder;
import com.sunchin.shop.admin.pojo.ScRepertory;
import com.sunchin.shop.admin.pojo.ScRepertoryFlowing;
import com.sunchin.shop.admin.repertory.dao.RepertoryDAO;
import com.sunchin.shop.admin.util.UserDefinedException;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.CommonUtils;

@SuppressWarnings("rawtypes")
@Repository("deliveryRecordService")
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

	@Resource(name="deliveryRecordDAO")
	private DeliveryRecordDAO deliveryRecordDAO;
	@Resource(name="orderDetailDAO")
	private OrderDetailDAO orderDetailDAO;
	@Resource(name="orderDAO")
	private OrderDAO orderDAO;
	@Resource(name="invoiceRecordDAO")
	private InvoiceRecordDAO invoiceRecordDAO;
	@Resource(name="repertoryDAO")
	private RepertoryDAO repertoryDAO;
	
	@Override
	public PageBean queryDeliveryOrderList(PageBean pageBean) throws Exception {
		int total = deliveryRecordDAO.queryDeliveryOrderCount(pageBean);
		pageBean.setTotal(total);
		List<Map<String, Object>> pageData = deliveryRecordDAO.queryDeliveryOrderPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	@Override
	public List<Map> queryDeliveryGoodsById(String id) throws Exception {
		List<Map> list = deliveryRecordDAO.queryDeliveryGoodsById(id);
		return list;
	}

	@Override
	public Map queryDeliveryAddressById(String id) throws Exception {
		Map map = deliveryRecordDAO.queryDeliveryAddressById(id);
		return map;
	}
	
	@Override
	public List<Map> queryDeliveryInvoiceById(String id) throws Exception {
		List<Map> list = deliveryRecordDAO.queryDeliveryInvoiceById(id);
		return list;
	}

	@Override
	public List<Map> judgeVirtualGoods(String id) throws Exception {
		List<Map> list = orderDetailDAO.judgeVirtualGoods(id);
		return list;
	}

	@Transactional
	@Override
	public String delivery(String orderId, Map deliveryMap) throws Exception, UserDefinedException {
		//检查发货记录状态
		ScDeliveryRecord deliveryRecord = deliveryRecordDAO.queryDeliveryByOrderId(orderId);
		if(deliveryRecord==null){
			return "error";
		}
		String sts = CommonUtils.getString(deliveryRecord.getSts());
		if(sts.isEmpty()){
			throw new UserDefinedException("该订单状态异常！");
		}
		if(DeliveryRecordStsEnum.ALDELIVERY.getCode().equals(sts)){
			throw new UserDefinedException("该订单已经发货！");
		}
		
		String invoiceCode = CommonUtils.getString(deliveryMap.get("invoiceCode"));
		String expressId = CommonUtils.getString(deliveryMap.get("expressId"));
		String expressNum = CommonUtils.getString(deliveryMap.get("expressNum"));
		if(orderId==null || orderId.isEmpty() || invoiceCode.isEmpty() || expressId.isEmpty() || expressNum.isEmpty()){
			throw new UserDefinedException("发货功能异常！");
		}
		//订单表的订单状态
		orderDAO.updateOrderStatus(orderId, OrderStsEnum.UNRECEIPT.getCode());
		
		//发票流水记录表的发票编号
		ScOrder order = orderDAO.queryOrderById(orderId);
		if(order==null) throw new UserDefinedException("发货功能异常！");
		String invoiceRecordId = CommonUtils.getString(order.getInvoiceRecordId());
		if(invoiceRecordId.isEmpty()) throw new UserDefinedException("发货功能异常！");
		invoiceRecordDAO.updateInvoiceCode(invoiceCode, invoiceRecordId);
		
		//发货记录表快递单号、快递服务商、发货状态
		//ScDeliveryRecord deliveryRecord = deliveryRecordDAO.queryDeliveryByOrderId(orderId);
		deliveryRecord.setExpressId(expressId);
		deliveryRecord.setExpressNum(expressNum);
		deliveryRecord.setDeliveryTime(new Date());
		deliveryRecord.setDeliveryUserId("adminDelivery");
		deliveryRecord.setSts(DeliveryRecordStsEnum.ALDELIVERY.getCode());
		DBUtil.getInstance().update(deliveryRecord);
		String deliveryRecordId = deliveryRecord.getId();
		
		//库存表的	冻结库存-count，销售+count
		//根据order_id或者child_order_id获取商品库存
		List<Map> list = repertoryDAO.queryRepertoryByOrderId(orderId);
		for (int i = 0; i < list.size(); i++) {
			String repertoryId = CommonUtils.getString(list.get(i).get("repertoryId"));
			ScRepertory repertory = repertoryDAO.queryPojoById(repertoryId);
			if(repertory==null) {
				throw new UserDefinedException("发货功能异常！");
			}
			int count = CommonUtils.getInteger(list.get(i).get("count"));
			//repertory.setAvailableNum(CommonUtils.getInteger(repertory.getAvailableNum())-count);
			repertory.setFreezeNum(CommonUtils.getInteger(repertory.getFreezeNum())-count);
			repertory.setSalesCount(CommonUtils.getInteger(repertory.getSalesCount())+count);
			DBUtil.getInstance().update(repertory);
			//库存流水记录表
			this.addRepertoryFlowing(list.get(i), deliveryRecordId);
		}
		return "";
	}

	/**
	 * 一种商品，库存流水增加表两条记录，分别是冻结库存和累计销售库存
	 * @param map					包含goodsId/childGoodsId/count
	 * @param deliveryRecordId		发货单（关联类型）的id
	 */
	private void addRepertoryFlowing(Map map, String deliveryRecordId) {
		String goodsId = CommonUtils.getString(map.get("goodsId"));
		String childGoodsId = CommonUtils.getString(map.get("childGoodsId"));
		int count = CommonUtils.getInteger(map.get("count"));
		//冻结库存-count
		ScRepertoryFlowing reFlowing1 = new ScRepertoryFlowing();
		reFlowing1.setId(UUID.randomUUID().toString());
		reFlowing1.setGoodsId(goodsId);
		reFlowing1.setChildGoodsId(childGoodsId);
		reFlowing1.setAboutType(repertoryTypeEnum.DELIVERY.getCode());
		reFlowing1.setAboutId(deliveryRecordId);
		reFlowing1.setRepertoryType(repertoryTypeEnum.FREEZE_NUM.getCode());
		reFlowing1.setRepertoryNum(-count);
		reFlowing1.setCreateTime(new Date());
		reFlowing1.setCreateUserId("admin");
		reFlowing1.setFlag(FlagEnum.ACT.getCode());
		DBUtil.getInstance().insert(reFlowing1);
		//累计销售数量+count
		ScRepertoryFlowing reFlowing2 = new ScRepertoryFlowing();
		reFlowing2.setId(UUID.randomUUID().toString());
		reFlowing2.setGoodsId(goodsId);
		reFlowing2.setChildGoodsId(childGoodsId);
		reFlowing2.setAboutType(repertoryTypeEnum.DELIVERY.getCode());
		reFlowing2.setAboutId(deliveryRecordId);
		reFlowing2.setRepertoryType(repertoryTypeEnum.SALES_COUNT.getCode());
		reFlowing2.setRepertoryNum(count);
		reFlowing2.setCreateTime(new Date());
		reFlowing2.setCreateUserId("admin");
		reFlowing2.setFlag(FlagEnum.ACT.getCode());
		DBUtil.getInstance().insert(reFlowing2);
	}

	@Override
	public String checkOrderDeliverySts(String id) throws Exception {
		ScDeliveryRecord deliveryRecord = deliveryRecordDAO.queryDeliveryByOrderId(id);
		if(deliveryRecord==null){
			return "error";
		}
		String sts = CommonUtils.getString(deliveryRecord.getSts());
		if(sts.isEmpty()){
			return "error";
		}
		if(DeliveryRecordStsEnum.ALDELIVERY.getCode().equals(sts)){
			return "error";
		}
		return "";
	}
}
