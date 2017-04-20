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
		tempSql.append(" with sc_temp as ( ");
		tempSql.append(" select t.order_id ");
		tempSql.append(" ,t.goods_id ");
		tempSql.append(" ,t.child_goods_id ");
		tempSql.append(" ,wm_concat(t5.prop_name) prop_names ");
		tempSql.append(" ,wm_concat(t6.val_name) val_names ");
		tempSql.append(" from sc_order_detail t ");
		tempSql.append(" left join sc_goods_cate_prop_propval t2 on t2.goods_id=t.goods_id and t.child_goods_id=t2.child_goods_id ");
		tempSql.append(" left join sc_cate_prop_propval t3 on t3.id=t2.cppv_id ");
		tempSql.append(" left join sc_prop_propval t4 on t4.id=t3.ppv_id "); 
		tempSql.append(" left join sc_property t5 on t5.id=t4.prop_id ");
		tempSql.append(" left join sc_propval t6 on t6.id=t4.val_id ");
		tempSql.append(" where t.order_id=? ");
		tempSql.append(" group by t.order_id,t.goods_id,t.child_goods_id ");
		tempSql.append(" ) ");
		tempSql.append(" select tod.count,tod.order_id "); 
		tempSql.append(" ,tg.goods_name ");
		tempSql.append(" ,temp.prop_names ");
		tempSql.append(" ,temp.val_names ");
		tempSql.append(" ,tr.available_num ");
		tempSql.append(" from sc_order_detail tod ");
		tempSql.append(" left join sc_goods tg on tg.id=tod.goods_id ");
		tempSql.append(" left join sc_temp temp on temp.goods_id=tod.goods_id and temp.child_goods_id=tod.child_goods_id ");
		tempSql.append(" left join sc_repertory tr on tr.goods_id=tod.goods_id and tr.child_goods_id=tod.child_goods_id ");
		tempSql.append(" where tod.order_id=? and tod.flag=? ");
		confirmOrderSql = tempSql.toString();
		confirmOrderParams = new ArrayList<String>();
		confirmOrderParams.add(orderId);
		confirmOrderParams.add(orderId);
		confirmOrderParams.add(FlagEnum.ACT.getCode());
	}
	
}
