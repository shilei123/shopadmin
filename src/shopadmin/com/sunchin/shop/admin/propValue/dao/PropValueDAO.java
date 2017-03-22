package com.sunchin.shop.admin.propValue.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScPropValue;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("propValueDAO")
public class PropValueDAO extends PageDAO{
	
	public final String SELECT_SQL = " select t.id,t.val_code,t.val_name,t.val_order,to_char(t.create_time,'yyyy-MM-dd hh24:mm:ss')as create_time from SC_PROPVALUE t where flag=? ";

	public int queryPropValueCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>(2);
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScPropValue> queryPropValuePagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>(2);
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		List<ScPropValue> pageData = this.query(sql, params, DBUtil.getInstance(), pageBean);
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
<<<<<<< HEAD
	public List<ScPropValue> getPropValue(String id) {
=======
>>>>>>> refs/remotes/origin/yangchaowen
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("flag", FlagEnum.ACT.getCode());
		return DBUtil.getInstance().queryByPojo(ScPropValue.class,params);
	}
}
