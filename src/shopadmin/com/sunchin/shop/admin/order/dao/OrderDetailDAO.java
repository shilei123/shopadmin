package com.sunchin.shop.admin.order.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;

import framework.db.DBUtil;

@Repository("orderDetailDAO")
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class OrderDetailDAO{

	private String sql;
	private List<String> params;
	
	private String confirmOrderSql;
	private List<String> confirmOrderParams;
	
	/**
	 * 订单详情对应的商品信息查询
	 * @param orderId
	 * @return
	 */
	public List<Map> queryOrderDetailByOrderId(String orderId){
		this.initParams(orderId);
		List<Map> list = DBUtil.getInstance().queryBySQL(sql, params);
		return list;
	}

	private void initParams(String orderId) {
		StringBuffer tempSql = new StringBuffer();
		tempSql.append("select t.id,t.order_id,t.child_order_id,t.goods_id,t.child_goods_id, t.unit_price,t.amount ");
		tempSql.append(" ,decode(t.count,t.count,t.count||'件')as count ");
		tempSql.append(" ,nvl(t4.img_path,'暂无图片')as img_path ");
		tempSql.append(" ,t1.goods_name,t4.file_name ");
		tempSql.append(" from sc_order_detail t ");
		tempSql.append(" left join sc_goods t1 on t1.id=t.goods_id ");
		tempSql.append(" left join sc_child_goods t2 on t2.id=t.child_goods_id ");
		tempSql.append(" left join sc_goods_image t3 on t3.goods_id=t1.id ");
		tempSql.append(" left join sc_image t4 on t4.id=t3.image_id ");
		tempSql.append(" where t.flag = ? "); 
		tempSql.append(" and t1.flag = ? ");
//		tempSql.append(" and t2.flag = ? ");
//		tempSql.append(" and t3.flag = ? ");
//		tempSql.append(" and t4.flag = ? ");
		tempSql.append(" and t.order_id = ? ");
		sql = tempSql.toString();
		params = new ArrayList<String>(6);
		String flag = FlagEnum.ACT.getCode();
		params.add(flag);
		params.add(flag);
//		params.add(flag);
//		params.add(flag);
//		params.add(flag);
		params.add(orderId);
	}
	
	/**
	 * 确认订单（查询子订单）
	 * @param orderId
	 * @return
	 */
	public List<Map> queryConfirmOrder(String orderId){
		this.initConfirmParams(orderId);
		List<Map> list = DBUtil.getInstance().queryBySQL(confirmOrderSql, confirmOrderParams);
		return list;
	}
	
	private void initConfirmParams(String orderId) {
		StringBuffer tempSql = new StringBuffer();
		tempSql.append("  ");
		tempSql.append("  ");
		tempSql.append("  ");
		tempSql.append("  ");
		tempSql.append("  ");
		tempSql.append("  ");
		tempSql.append("  ");
		tempSql.append("  ");
		tempSql.append("  ");
		tempSql.append("  "); 
		tempSql.append("  ");
		tempSql.append("  ");
		confirmOrderSql = tempSql.toString();
		params = new ArrayList<String>(6);
		String flag = FlagEnum.ACT.getCode();
		confirmOrderParams.add(flag);
		confirmOrderParams.add(flag);
		confirmOrderParams.add(orderId);
	}
	
}
