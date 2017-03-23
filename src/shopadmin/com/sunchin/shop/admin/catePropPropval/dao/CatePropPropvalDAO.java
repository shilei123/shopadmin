package com.sunchin.shop.admin.catePropPropval.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;

import framework.db.DBUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository("catePropPropvalDAO")
public class CatePropPropvalDAO {
	
	/**
	 * 查询树结构
	 * @return
	 */
	public List<Map> queryBySQL(){
		String sql = " select o.id,o.cate_name,o.memo,o.cate_order,o.levels,o.logo,o.url,o.isuse,o.parent_id from sc_category o where o.flag=? ";
		return DBUtil.getInstance().queryBySQL(sql, FlagEnum.ACT.getCode());
	}
	
}
