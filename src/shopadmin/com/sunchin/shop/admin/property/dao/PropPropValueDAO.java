package com.sunchin.shop.admin.property.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("propPropValueDAO")
public class PropPropValueDAO extends PageDAO{
	
	//public final String SELECT_SQL = " select t.id,t.PROP_ID,t.CATE_ID from SC_PROPERTY_CATEGORY t where t.flag=? ";
	/*
	*//**
	 * 查询所有属性值
	 * @param pageBean
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryPropCate(String cateId, String[] propIds) {
		StringBuffer sql = new StringBuffer(SELECT_SQL);
		
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		DBUtil.bind(sql, params, " and t.cate_id=? ", cateId);
		DBUtil.bindIn(sql, params, " and t.prop_id ", propIds);
		
		return DBUtil.getInstance().queryBySQL(sql.toString(), params);
	}*/

	/**
	 * 查询该属性的属性-属性值关系
	 * @param cateId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryPropPropValueByPropId(String propId) {
		StringBuffer sql = new StringBuffer(" select t.* from SC_PROPERTY_PROPVALUE t where t.flag=? and t.prop_id=? ");
		List<String> params = new ArrayList<String>(2);
		params.add(FlagEnum.ACT.getCode());
		params.add(propId);
		return DBUtil.getInstance().queryBySQL(sql.toString(), params);
	}

}
