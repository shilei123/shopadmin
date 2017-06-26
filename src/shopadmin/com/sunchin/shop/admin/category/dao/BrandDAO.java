package com.sunchin.shop.admin.category.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScBrand;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("brandDAO")
public class BrandDAO extends PageDAO{
	
	public final String SELECT_SQL = " select t.id,t.brand_code,t.brand_name,to_char(t.create_time,'yyyy-MM-dd hh24:mm:ss')as create_time from SC_brand t where flag=? ";

	public int queryBrandCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>(2);
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScBrand> queryBrandPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>(2);
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		List<ScBrand> pageData = this.query(sql, params, DBUtil.getInstance(), pageBean);
		return pageData;
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(SELECT_SQL);
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String brandName = pageBean.getQueryParams().get("brandName");
			if (StringUtils.isNotBlank(brandName)){
				params.add("%" + brandName + "%");
				sql.append(" and t.brand_name like ? ");
			}
			String brandCode = pageBean.getQueryParams().get("brandCode");
			if (StringUtils.isNotBlank(brandCode)){
				params.add("%" + brandCode + "%");
				sql.append(" and t.brand_code like ? ");
			}
		}
		sql.append(" order by t.create_time desc ");
		return sql.toString();
	}
	
	/**
	 *	获得品牌信息
	 */
	@SuppressWarnings("unchecked")
	public List<ScBrand> getBrand(String id) {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("flag", FlagEnum.ACT.getCode());
		return DBUtil.getInstance().queryByPojo(ScBrand.class,params);
	}
	
	public void delBrand(String id){
		String hql = " update ScBrand set flag=? where id=? ";
		DBUtil.getInstance().executeHql(hql, FlagEnum.HIS.getCode(), id);
	}
	
	@SuppressWarnings("unchecked")
	public ScBrand queryBrandByBrandName(String brandName) {
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("brandName",brandName);
		params.put("flag", FlagEnum.ACT.getCode());
		List<ScBrand> list = DBUtil.getInstance().queryByPojo(ScBrand.class,params);
		if(list!=null && !list.isEmpty())
			return list.get(0);
		return null;
	}
	
}
