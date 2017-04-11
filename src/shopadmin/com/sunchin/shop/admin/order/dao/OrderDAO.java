package com.sunchin.shop.admin.order.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScOrder;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("orderDAO")
public class OrderDAO extends PageDAO{

	public String PAGE_SQL;
	public List<String> pageParams;
	public String DETAIL_SQL;
	public List<String> detailParams;
	
	private void initPageSqlParams(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.id, t.order_code, t.num, t.split_time, t.remark, t.commision_charge, t.total_price, t.order_price, ");
		sql.append(" t.actual_price, to_char(t.create_time, 'yyyy-MM-dd hh24:mm:ss') as create_time, t1.name as payMode, ");
		sql.append(" t2.name as invoice, t3.name as issplit, t4.name as orderStatus, t5.name as deliveryMode ");
		sql.append(" from SC_ORDER t ");
		sql.append(" left join Sc_Dictionary t1 on t1.code=t.pay_mode and t1.type=? ");
		sql.append(" left join Sc_Dictionary t2 on t2.code=t.invoice and t2.type=? ");
		sql.append(" left join Sc_Dictionary t3 on t3.code=t.issplit and t3.type=? ");
		sql.append(" left join Sc_Dictionary t4 on t4.code=t.order_status and t4.type=? ");
		sql.append(" left join Sc_Dictionary t5 on t5.code=t.delivery_mode and t5.type=? ");
		sql.append(" where t.flag = ? "); 
		sql.append(" and t1.flag = ? ");
		sql.append(" and t2.flag = ? ");
		sql.append(" and t3.flag = ? ");
		sql.append(" and t4.flag = ? ");
		sql.append(" and t5.flag = ? ");
		PAGE_SQL = sql.toString();
		
		pageParams = new ArrayList<String>(11);
		pageParams.add(DictionaryTypeEnum.ORDER_PAY_MODE.getType());
		pageParams.add(DictionaryTypeEnum.ORDER_INVOICE.getType());
		pageParams.add(DictionaryTypeEnum.ORDER_SPLIT.getType());
		pageParams.add(DictionaryTypeEnum.ORDER_STS.getType());
		pageParams.add(DictionaryTypeEnum.ORDER_DELIVERY_MODE.getType());
		pageParams.add(FlagEnum.ACT.getCode());
		pageParams.add(FlagEnum.ACT.getCode());
		pageParams.add(FlagEnum.ACT.getCode());
		pageParams.add(FlagEnum.ACT.getCode());
		pageParams.add(FlagEnum.ACT.getCode());
		pageParams.add(FlagEnum.ACT.getCode());
	}

	public int queryOrderCount(PageBean pageBean) {
		this.initPageSqlParams();
		String sql = this.buildWhereSql(pageBean, pageParams);
		return DBUtil.getInstance().queryCountBySQL(sql, pageParams);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryOrderPagination(PageBean pageBean) {
		this.initPageSqlParams();
		String sql = this.buildWhereSql(pageBean, pageParams);
		List<Map<String, Object>> pageData = this.query(sql, pageParams, DBUtil.getInstance(), pageBean);
		return pageData;
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		StringBuffer sql = new StringBuffer(PAGE_SQL);
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String deliveryMode = pageBean.getQueryParams().get("deliveryMode");
			if (StringUtils.isNotBlank(deliveryMode)){
				params.add(deliveryMode);
				sql.append(" and t.delivery_mode=? ");
			}
			String orderStatus = pageBean.getQueryParams().get("orderStatus");
			if (StringUtils.isNotBlank(orderStatus)){
				params.add(orderStatus);
				sql.append(" and t.order_status=? ");
			}
			String payMode = pageBean.getQueryParams().get("payMode");
			if (StringUtils.isNotBlank(payMode)){
				params.add(payMode);
				sql.append(" and t.pay_mode=? ");
			}
			String issplit = pageBean.getQueryParams().get("issplit");
			if (StringUtils.isNotBlank(issplit)){
				params.add(issplit);
				sql.append(" and t.issplit=? ");
			}
			String invoice = pageBean.getQueryParams().get("invoice");
			if (StringUtils.isNotBlank(invoice)){
				params.add(invoice);
				sql.append(" and t.invoice=? ");
			}
			String orderCode = pageBean.getQueryParams().get("orderCode");
			if (StringUtils.isNotBlank(orderCode)){
				params.add("%" + orderCode + "%");
				sql.append(" and t.order_code like ? ");
			}
			String startTotalPrice = pageBean.getQueryParams().get("startTotalPrice");
			if (StringUtils.isNotBlank(startTotalPrice)){
				params.add(startTotalPrice);
				sql.append(" and t.total_price >= ? ");
			}
			String endTotalPrice = pageBean.getQueryParams().get("endTotalPrice");
			if (StringUtils.isNotBlank(endTotalPrice)){
				params.add(endTotalPrice);
				sql.append(" and t.total_price <= ? ");
			}
			String startActualPrice = pageBean.getQueryParams().get("startActualPrice");
			if (StringUtils.isNotBlank(startActualPrice)){
				params.add(startActualPrice);
				sql.append(" and t.actual_price >= ? ");
			}
			String endActualPrice = pageBean.getQueryParams().get("endActualPrice");
			if (StringUtils.isNotBlank(endActualPrice)){
				params.add(endActualPrice);
				sql.append(" and t.actual_price <= ? ");
			}
			String startTime = pageBean.getQueryParams().get("startTime");
			if (StringUtils.isNotBlank(startTime)){
				params.add(startTime);
				sql.append(" and t.create_time >= to_date(?,'yyyy-MM-dd hh24:mi:ss')   ");
			}
			String endTime = pageBean.getQueryParams().get("endTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59");
				sql.append(" and t.create_time <= to_date(?,'yyyy-MM-dd hh24:mi:ss')  ");
			}
		}
		sql.append(" order by t.create_time desc ");
		return sql.toString();
	}
	
	@SuppressWarnings("unchecked")
	public ScOrder queryOrderById(String id){
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("flag", FlagEnum.ACT.getCode());
		params.put("id", id);
		List<ScOrder> list = DBUtil.getInstance().queryByPojo(ScOrder.class,params);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ScOrder> queryOrderDetailById(String id, String invoiceId, String parentOrderId){
		this.initDetailSqlParams(id, invoiceId, parentOrderId);
		List<ScOrder> list = DBUtil.getInstance().queryBySQL(DETAIL_SQL, detailParams);
		return list;
	}

	private void initDetailSqlParams(String id, String invoiceId, String parentOrderId){
//		ISSPLIT				是否拆分
//		SPLIT_TIME			拆分时间
//		INVOICE				是否需要发票
//		INVOICE_RECORD_ID	发票流水id
//		PARENT_ORDER_ID		父订单id
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.order_code ,t.total_price ,t.actual_price ,t.commision_charge ,t1.name as order_status ");//订单状态
		sql.append(" ,t.num ,t.create_time ,t.remark ,t2.name as pay_mode ,t3.name as issplit ,t4.nick_name ");//订单信息
		sql.append("  ,t5.name as rec_name ,t5.phone ,t5.post_code ,t5.province ,t5.city ,t5.county ,t5.address_detail ");//收货信息
		sql.append("  ");//发票信息
		sql.append("  ");//商品信息
		sql.append("  ");//操作历史
		sql.append(" from sc_order t ");
		sql.append(" left join sc_dictionary t1 on t1.code=t.order_status and t1.type=? ");
		sql.append(" left join sc_dictionary t2 on t2.code=t.pay_mode and t2.type=? ");
		sql.append(" left join sc_dictionary t3 on t3.code=t.issplit and t3.type=? ");
		sql.append(" left join sc_user t4 on t4.id=t.user_id ");
		sql.append(" left join sc_address t5 on t5.id=t.address_id ");
		sql.append(" where t.flag = ? "); 
		sql.append(" and t1.flag = ? ");
		sql.append(" and t2.flag = ? ");
		sql.append(" and t3.flag = ? ");
		sql.append(" and t4.flag = ? ");
		sql.append(" and t5.flag = ? ");
		sql.append(" and t.id = ? ");
		DETAIL_SQL = sql.toString();
		
		detailParams = new ArrayList<String>(10);
		detailParams.add(DictionaryTypeEnum.ORDER_STS.getType());
		detailParams.add(DictionaryTypeEnum.ORDER_PAY_MODE.getType());
		detailParams.add(DictionaryTypeEnum.ORDER_SPLIT.getType());
		detailParams.add(FlagEnum.ACT.getCode());
		detailParams.add(FlagEnum.ACT.getCode());
		detailParams.add(FlagEnum.ACT.getCode());
		detailParams.add(FlagEnum.ACT.getCode());
		detailParams.add(FlagEnum.ACT.getCode());
		detailParams.add(FlagEnum.ACT.getCode());
		detailParams.add(id);
//		detailParams.add(invoiceId);
//		detailParams.add(parentOrderId);
	}
	
}
