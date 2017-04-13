package com.sunchin.shop.admin.goodsManager.dao;

import org.springframework.stereotype.Repository;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("scGoodsCatePropPropValDAO")
public class ScGoodsCatePropPropValDAO extends PageDAO {
	
	public int deleteByGoodsId(String goodsId) {
		return DBUtil.getInstance().executeSQL(" delete from sc_goods_cate_prop_propval t where t.goods_id=? ", goodsId);
	}
}