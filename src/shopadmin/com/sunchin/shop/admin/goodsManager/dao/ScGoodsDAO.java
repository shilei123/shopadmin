package com.sunchin.shop.admin.goodsManager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScGoods;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings("rawtypes")
@Repository("goodsDAO")
public class ScGoodsDAO extends PageDAO {
	
	public List queryGoodsPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		String sql = this.buildWhereSql(pageBean, params);
		List pageData = this.query(sql, params, DBUtil.getInstance(), pageBean);
		return pageData;
	}
	
	public int queryGoodsCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}
	
	private String buildWhereSql(PageBean pageBean, List<String> params) {
		params.add(FlagEnum.ACT.getCode());
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t.*,c.cate_name from sc_goods t left join sc_category c on t.cate_id=c.id where t.flag=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			DBUtil.bind(sql, params, " and t.audit_sts=? ", pageBean.getQueryParams().get("auditSts"));
		}
		sql.append(" ORDER BY t.create_time desc ");
		return sql.toString();
	}
	
	public Map queryMapById(String id) {
		StringBuffer sql = new StringBuffer(" select t.*,c.cate_name from sc_goods t left join sc_category c on t.cate_id=c.id where t.id=? ");
		List list = DBUtil.getInstance().queryBySQL(sql.toString(), id);
		if(list != null && !list.isEmpty()) {
			return (Map) list.get(0);
		}
		return null;
	}
	
	public ScGoods queryPojoById(String id) {
		return (ScGoods) DBUtil.getInstance().get(ScGoods.class, id);
	}
}