package com.sunchin.shop.admin.order.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sunchin.shop.admin.order.service.DeliveryRecordService;
import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.OrderStsEnum;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScOrder;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.DBUtil;

@SuppressWarnings({"unchecked", "rawtypes"})
public class DeliveryRecordAction extends PageAction{

	@Resource(name="deliveryRecordService")
	private DeliveryRecordService deliveryRecordService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private ScOrder order;
	private List<ScDictionary> dicts;
	private List<Map> goodsList;//需要发货的订单对应的商品信息
	private Map addressMap;//收货地址信息
	private List<Map> invoiceList;//发票信息
	
	public String initDeliveryManage(){
		//待发货、发货中、已完成
		//等merge了dict模块后迁移
		String sql = " select t.* from SC_DICTIONARY t where t.type=? and t.flag=? and t.code in (?,?,?) ";
		List<String> params = new ArrayList<>();
		params.add(DictionaryTypeEnum.ORDER_STS.getType());
		params.add(FlagEnum.ACT.getCode());
		params.add(OrderStsEnum.UNDELIVERY.getCode());
		params.add(OrderStsEnum.UNRECEIPT.getCode());
		params.add(OrderStsEnum.FINISH.getCode());
		dicts = DBUtil.getInstance().queryBySQL(sql, params);
		return Action.SUCCESS;
	}
	
	/**
	 * 发货分页查询 
	 * @return
	 */
	public String queryDeliveryOrder(){
		try {
			PageBean resultData = deliveryRecordService.queryDeliveryOrderList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 发货初始化信息
	 * @return
	 */
	public String initDeliveryPageByOrderId(){
		try {
			goodsList = deliveryRecordService.queryDeliveryGoodsById(order.getId());//商品信息
			addressMap = deliveryRecordService.queryDeliveryAddressById(order.getId());//收货信息
			invoiceList = deliveryRecordService.queryDeliveryInvoiceById(order.getId()); //发票信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 发货
	 * @return
	 */
	public String delivery(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public List<ScDictionary> getDicts() {
		return dicts;
	}

	public void setDicts(List<ScDictionary> dicts) {
		this.dicts = dicts;
	}

	public ScOrder getOrder() {
		return order;
	}

	public void setOrder(ScOrder order) {
		this.order = order;
	}

	public List<Map> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Map> goodsList) {
		this.goodsList = goodsList;
	}

	public Map getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map addressMap) {
		this.addressMap = addressMap;
	}

	public List<Map> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Map> invoiceList) {
		this.invoiceList = invoiceList;
	}

}
