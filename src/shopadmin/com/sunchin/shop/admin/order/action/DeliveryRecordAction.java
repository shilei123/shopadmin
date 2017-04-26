package com.sunchin.shop.admin.order.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.sunchin.shop.admin.order.service.DeliveryRecordService;
import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.OrderStsEnum;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.DBUtil;

@SuppressWarnings("unchecked")
public class DeliveryRecordAction extends PageAction{

	@Resource(name="deliveryRecordService")
	private DeliveryRecordService deliveryRecordService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private List<ScDictionary> dicts;
	
	public String initDeliveryManage(){
		//待发货、发货中、已完成
		//周一merge了dict模块后迁移
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

	public List<ScDictionary> getDicts() {
		return dicts;
	}

	public void setDicts(List<ScDictionary> dicts) {
		this.dicts = dicts;
	}

}
