package com.sunchin.shop.admin.order.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScOrder;
import com.sunchin.shop.admin.system.service.DictService;
import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.order.service.OrderDetailService;
import com.sunchin.shop.admin.order.service.OrderService;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.util.CommonUtils;

@SuppressWarnings("rawtypes")
public class OrderAction extends PageAction{

	@Resource(name="orderService")
	private OrderService orderService;
	@Resource(name="orderDetailService")
	private OrderDetailService orderDetailService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private Map<String, List<ScDictionary>> initMap;
	private Map<String, String> initType;
	private ScOrder order;
	private Map orderMap;//订单基础信息
	private List<ScOrder> sonOrders;//子订单信息
	private List<Map> orderGoods;//商品信息
	
	public String queryOrderList(){
		try {
			PageBean resultData = orderService.queryOrderList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String queryOrderById(){
		try {
			orderMap = orderService.queryOrderById(order.getId());//订单基础信息
			sonOrders = orderService.querySonOrderById(order.getId()); //子订单信息
			orderGoods = orderDetailService.queryByOrderId(order.getId());//商品信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * empty method
	 * @return
	 */
	public String delOrder(){
		try {
			orderService.delOrder(order.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 初始化下拉框
	 * @return
	 */
	public String initDictsByType(){
		try {
			initMap = new HashMap<String, List<ScDictionary>>(5);
			initMap.put("orderDeliveryMode", dictService.getDictByType(CommonUtils.getString(initType.get("orderDeliveryMode"))));
			initMap.put("orderSts", dictService.getDictByType(CommonUtils.getString(initType.get("orderSts"))));
			initMap.put("ordeSplit", dictService.getDictByType(CommonUtils.getString(initType.get("ordeSplit"))));
			initMap.put("orderInvoice", dictService.getDictByType(CommonUtils.getString(initType.get("orderInvoice"))));
			initMap.put("orderPayMode", dictService.getDictByType(CommonUtils.getString(initType.get("orderPayMode"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public Map<String, List<ScDictionary>> getInitMap() {
		return initMap;
	}

	public void setInitMap(Map<String, List<ScDictionary>> initMap) {
		this.initMap = initMap;
	}

	public Map<String, String> getInitType() {
		return initType;
	}

	public void setInitType(Map<String, String> initType) {
		this.initType = initType;
	}

	public ScOrder getOrder() {
		return order;
	}

	public void setOrder(ScOrder order) {
		this.order = order;
	}

	public Map getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map orderMap) {
		this.orderMap = orderMap;
	}

	public List<ScOrder> getSonOrders() {
		return sonOrders;
	}

	public List<Map> getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(List<Map> orderGoods) {
		this.orderGoods = orderGoods;
	}

	public void setSonOrders(List<ScOrder> sonOrders) {
		this.sonOrders = sonOrders;
	}

}
