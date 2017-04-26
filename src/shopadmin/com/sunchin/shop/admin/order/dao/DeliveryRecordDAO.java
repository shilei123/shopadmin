package com.sunchin.shop.admin.order.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.OrderStsEnum;
import com.sunchin.shop.admin.pojo.ScDeliveryRecord;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings("unchecked")
@Repository("deliveryRecordDAO")
public class DeliveryRecordDAO extends PageDAO{

	public String DELIVERY_SQL;
	public List<String> deliveryParams;
	
	@Resource(name="dbUtil")
	private DBUtil db;
	
	/**
	 * 初始化发货管理订单查询分页
	 */
	private void initDeliverySqlParams(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.id, t.order_code, t.split_time, t.remark, t.total_price, t.order_price ");
		sql.append(" ,decode(t.num,t.num,t.num||'件')as num ");
		sql.append(" ,decode(t.commision_charge,'0','免运费',t.commision_charge,t.commision_charge) as commision_charge ");
		sql.append(" ,t.actual_price, to_char(t.create_time, 'yyyy-MM-dd hh24:mm:ss') as create_time, t1.name as payMode ");
		sql.append(" ,t2.name as invoice, t3.name as issplit, t4.name as orderStatus, t5.name as deliveryMode ");
		sql.append(" ,t6.nick_Name ");
		sql.append(" from SC_ORDER t ");
		sql.append(" left join Sc_Dictionary t1 on t1.code=t.pay_mode and t1.type=? ");
		sql.append(" left join Sc_Dictionary t2 on t2.code=t.invoice and t2.type=? ");
		sql.append(" left join Sc_Dictionary t3 on t3.code=t.issplit and t3.type=? ");
		sql.append(" left join Sc_Dictionary t4 on t4.code=t.order_status and t4.type=? ");
		sql.append(" left join Sc_Dictionary t5 on t5.code=t.delivery_mode and t5.type=? ");
		sql.append(" left join Sc_User t6 on t6.id=t.user_id ");
		sql.append(" where t.flag = ? "); 
		sql.append(" and t1.flag = ? ");
		sql.append(" and t2.flag = ? ");
		sql.append(" and t3.flag = ? ");
		sql.append(" and t4.flag = ? ");
		sql.append(" and t5.flag = ? ");
		sql.append(" and t6.flag = ? ");
		sql.append(" and t.parent_order_id is null ");
		sql.append(" and t.parent_order_id is null ");
		sql.append(" and t4.code in (?,?,?) ");
		DELIVERY_SQL = sql.toString();
		
		deliveryParams = new ArrayList<String>(15);
		deliveryParams.add(DictionaryTypeEnum.ORDER_PAY_MODE.getType());
		deliveryParams.add(DictionaryTypeEnum.ORDER_INVOICE.getType());
		deliveryParams.add(DictionaryTypeEnum.ORDER_SPLIT.getType());
		deliveryParams.add(DictionaryTypeEnum.ORDER_STS.getType());
		deliveryParams.add(DictionaryTypeEnum.ORDER_DELIVERY_MODE.getType());
		deliveryParams.add(FlagEnum.ACT.getCode());
		deliveryParams.add(FlagEnum.ACT.getCode());
		deliveryParams.add(FlagEnum.ACT.getCode());
		deliveryParams.add(FlagEnum.ACT.getCode());
		deliveryParams.add(FlagEnum.ACT.getCode());
		deliveryParams.add(FlagEnum.ACT.getCode());
		deliveryParams.add(FlagEnum.ACT.getCode());
		deliveryParams.add(OrderStsEnum.UNDELIVERY.getCode());
		deliveryParams.add(OrderStsEnum.UNRECEIPT.getCode());
		deliveryParams.add(OrderStsEnum.FINISH.getCode());
	}

	public int queryDeliveryOrderCount(PageBean pageBean) {
		this.initDeliverySqlParams();
		String sql = this.buildDeliverySql(pageBean, deliveryParams);
		return db.queryCountBySQL(sql, deliveryParams);
	}

	public List<Map<String, Object>> queryDeliveryOrderPagination(PageBean pageBean) {
		this.initDeliverySqlParams();
		String sql = this.buildDeliverySql(pageBean, deliveryParams);
		List<Map<String, Object>> pageData = this.query(sql, deliveryParams, db, pageBean);
		return pageData;
	}

	private String buildDeliverySql(PageBean pageBean, List<String> params) {
		StringBuffer sql = new StringBuffer(DELIVERY_SQL);
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String orderStatus = pageBean.getQueryParams().get("orderStatus");
			if (StringUtils.isNotBlank(orderStatus)){
				params.add(orderStatus);
				sql.append(" and t.order_status=? ");
			}
			String orderCode = pageBean.getQueryParams().get("orderCode");
			if (StringUtils.isNotBlank(orderCode)){
				params.add("%" + orderCode + "%");
				sql.append(" and t.order_code like ? ");
			}
		}
		sql.append(" order by t.create_time desc ");
		return sql.toString();
	}
	
	public ScDeliveryRecord queryDeliveryByOrderId(String orderId){
		String hql = " from ScDeliveryRecord where flag=? and orderId=? ";
		List<ScDeliveryRecord> list = db.queryByHql(hql, FlagEnum.HIS.getCode(), orderId);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	public void delDelivery(String id){
		String hql = " update ScDeliveryRecord set flag=? where id=? ";
		db.executeHql(hql, FlagEnum.HIS.getCode(), id);
	}
	
}
