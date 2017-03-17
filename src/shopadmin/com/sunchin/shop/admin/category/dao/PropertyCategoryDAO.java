package com.sunchin.shop.admin.category.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("propCateDAO")
public class PropertyCategoryDAO extends PageDAO{
	
	public final String SELECT_SQL = " select t.id,t.PROP_ID,t.CATE_ID from SC_PROPERTY_CATEGORY t where t.flag=? ";
	
	/**
	 * 查询当前页且和类别相关的属性
	 * @param pageBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryPropCate(String cateId, String[] propIds) {
		StringBuffer sql = new StringBuffer(SELECT_SQL);
		
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		DBUtil.bind(sql, params, " and t.cate_id=? ", cateId);
		DBUtil.bindIn(sql, params, " and t.prop_id ", propIds);
		
		return DBUtil.getInstance().queryBySQL(sql.toString(), params);
	}

	/**
	 * 查询该类别的类别-属性关系
	 * @param cateId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryPropCateByCateId(String cateId) {
		StringBuffer sql = new StringBuffer(" select t.* from SC_PROPERTY_CATEGORY t where t.flag=? and t.cate_id=? ");
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(cateId);
		return DBUtil.getInstance().queryBySQL(sql.toString(), params);
	}

}
