package com.sunchin.shop.admin.goodsManager.dao;

import org.springframework.stereotype.Repository;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("scImageUseRecDAO")
public class ScImageUseRecDAO extends PageDAO {
	
	public int deleteByBusiTypeAndBusiId(String busiType, String busiId) {
		return DBUtil.getInstance().executeSQL(" delete from sc_image_use_rec t where t.busi_type=? and t.busi_id=? ", busiType, busiId);
	}
}