package com.sunchin.shop.admin.property.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScProperty;

import framework.bean.PageBean;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("propertyDAO")
public class PropertyDAO extends PageDAO{
	
	public final String SELECT_SQL = " select t.id,t.prop_code,t.prop_name,t.order_,to_char(t.create_time,'yyyy-MM-dd hh24:mm:ss')as create_time from SC_PROPERTY t where flag=? ";

	public int queryPropertyCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")

	public List<Map<String, Object>> queryPropertyPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		List<Map<String, Object>> pageData = this.query(sql, params, DBUtil.getInstance(), pageBean);

		return pageData;
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(SELECT_SQL);
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String propName = pageBean.getQueryParams().get("propName");
			if (StringUtils.isNotBlank(propName)){
				params.add("%"+propName+"%");
				sql.append(" and t.prop_name like ? ");
			}
			String propCode = pageBean.getQueryParams().get("propCode");
			if (StringUtils.isNotBlank(propCode)){
				params.add("%"+propCode+"%");
				sql.append(" and t.prop_code like ? ");
			}
		}
		sql.append(" order by t.create_time desc ");
		return sql.toString();
	}
	
	/**
	 *	获得属性信息
	 */
	@SuppressWarnings("unchecked")
	public List<ScProperty> getProp(String id) {
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("flag", FlagEnum.ACT.getCode());
		params.put("id", id);
		return DBUtil.getInstance().queryByPojo(ScProperty.class,params);
	}
	
	public void delProp(String id) {
		String hql = " update ScProperty set flag=? where id=? ";
		DBUtil.getInstance().executeHql(hql, SysDict.FLAG_HIS, id);
	}
	
	@SuppressWarnings("unchecked")
	public ScProperty getPropByPropName(String propName){
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("flag", FlagEnum.ACT.getCode());
		params.put("propName", propName);
		List<ScProperty> list = DBUtil.getInstance().queryByPojo(ScProperty.class,params);
		if(list!=null && !list.isEmpty())
			return list.get(0);
		return null;
	}
}
