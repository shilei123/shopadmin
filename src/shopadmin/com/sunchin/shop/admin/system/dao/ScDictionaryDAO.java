package com.sunchin.shop.admin.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScDictionary;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("scDictionaryDAO")
public class ScDictionaryDAO extends PageDAO {
	/**
	 * 根据名称查询
	 * @param pageBean
	 * @return pageData
	 */
	public final String SELECT_SQL = " select t.id,t.type,t.code,t.name,t.ename,t.pcode,t.remark,t.isuse, "
			+ " t.isedit,t.sort,to_char(t.create_time,'yyyy-mm-dd hh24:mi:ss') as create_time,t.flag, "
			+ " t2.type parent_type,t2.code parent_code "
			+ " from SC_DICTIONARY t "
			+ " left join SC_DICTIONARY t2 on t.pcode=t2.id "
			+ " where t.flag=? ";
	
	@SuppressWarnings("unchecked")
	public List<ScDictionary> queryDictPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	public int queryDictCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}
	
	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(SELECT_SQL);
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String type = pageBean.getQueryParams().get("type");
			if (StringUtils.isNotBlank(type)){
				params.add("%"+type+"%");
				sql.append(" and t.type like ? ");
			}
			String code = pageBean.getQueryParams().get("code");
			if (StringUtils.isNotBlank(code)){
				params.add("%"+code+"%");
				sql.append(" and t.code like ? ");
			}
			String name = pageBean.getQueryParams().get("name");
			if (StringUtils.isNotBlank(name)){
				params.add("%"+name+"%");
				sql.append(" and t.name like ? ");
			}
			String ename = pageBean.getQueryParams().get("ename");
			if (StringUtils.isNotBlank(ename)){
				params.add("%"+ename+"%");
				sql.append(" and t.ename like ? ");
			}
			String remark = pageBean.getQueryParams().get("remark");
			if (StringUtils.isNotBlank(remark)){
				params.add("%"+ename+"%");
				sql.append(" and t.remark like ? ");
			}
		}
		sql.append(" order by t.create_time desc ");
		return sql.toString();
	}
}
