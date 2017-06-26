package com.sunchin.shop.admin.propval.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScPropval;

import framework.bean.PageBean;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("propvalDAO")
public class PropvalDAO extends PageDAO{
	
	public final String SELECT_SQL = " select t.id,t.val_code,t.val_name,t.order_,to_char(t.create_time,'yyyy-MM-dd hh24:mm:ss')as create_time from SC_PROPVAL t where flag=? ";

	public int queryPropvalCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>(2);
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScPropval> queryPropvalPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>(2);
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		List<ScPropval> pageData = this.query(sql, params, DBUtil.getInstance(), pageBean);
		return pageData;
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(SELECT_SQL);
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String propValueName = pageBean.getQueryParams().get("propValueName");
			if (StringUtils.isNotBlank(propValueName)){
				params.add("%"+propValueName+"%");
				sql.append(" and t.val_name like ? ");
			}
			String propValueCode = pageBean.getQueryParams().get("propValueCode");
			if (StringUtils.isNotBlank(propValueCode)){
				params.add("%"+propValueCode+"%");
				sql.append(" and t.val_code like ? ");
			}
		}
		sql.append(" order by t.create_time desc ");
		return sql.toString();
	}
	
	/**
	 *	获得属性值信息
	 */
	@SuppressWarnings("unchecked")
	public List<ScPropval> getPropval(String id) {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("flag", FlagEnum.ACT.getCode());
		return DBUtil.getInstance().queryByPojo(ScPropval.class,params);
	}
	
	public void delPropval(String id){
		String hql = " update ScPropval set flag=? where id=? ";
		DBUtil.getInstance().executeHql(hql, SysDict.FLAG_HIS, id);
	}
	
	@SuppressWarnings("unchecked")
	public ScPropval queryPropvalByValName(String valName){
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("valName",valName);
		params.put("flag", FlagEnum.ACT.getCode());
		List<ScPropval> list = DBUtil.getInstance().queryByPojo(ScPropval.class,params);
		if(list!=null && !list.isEmpty())
			return list.get(0);
		return null;
	}
}
