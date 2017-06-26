package com.sunchin.shop.admin.repertory.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScRepertory;

import framework.db.DBUtil;

@Repository("repertoryDAO")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RepertoryDAO {
	
	/**
	 * 查询商品库存信息
	 * @param id	orderId或者childOrderId
	 * @return
	 */
	public List<Map> queryRepertoryByOrderId(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select d.*,c.id as repertory_id ");
		sql.append(" from (select b.* from sc_order a ,sc_order_detail b where (a.id=b.order_id or a.id=b.child_order_id) and a.id=?) d,sc_repertory c ");
		sql.append(" where d.goods_id=c.goods_id and c.child_goods_id is null ");
		sql.append(" union all ");
		sql.append(" select d.*,c.id as repertory_id ");
		sql.append(" from (select b.* from sc_order a ,sc_order_detail b where (a.id=b.order_id or a.id=b.child_order_id) and a.id=?) d,sc_repertory c ");
		sql.append(" where d.goods_id=c.goods_id and d.child_goods_id=c.child_goods_id and c.child_goods_id is not null ");
		
		List params = new ArrayList();
		params.add(id);
		params.add(id);
		List<Map> list = DBUtil.getInstance().queryBySQL(sql.toString(), params);
		return list;
	}
	
	public ScRepertory queryPojoById(String id){
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("flag", FlagEnum.ACT.getCode());
		params.put("id", id);
		List<ScRepertory> list = DBUtil.getInstance().queryByPojo(ScRepertory.class,params);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
