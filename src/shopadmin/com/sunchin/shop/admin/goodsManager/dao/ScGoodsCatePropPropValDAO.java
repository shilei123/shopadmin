package com.sunchin.shop.admin.goodsManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.pojo.ScChildGoods;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("scGoodsCatePropPropValDAO")
public class ScGoodsCatePropPropValDAO extends PageDAO {
	
	public int deleteByGoodsId(String goodsId,List<ScChildGoods> list) {
		StringBuffer sb = new StringBuffer();
		String split = "";
		for (ScChildGoods cg : list) {
			sb.append(split).append("'").append(cg.getId()).append("'");
			split = ",";
		}
		return DBUtil.getInstance().executeSQL(" delete from sc_goods_cate_prop_propval t where t.goods_id=? and t.child_goods_id in (?)", goodsId, sb.toString());
	}
}