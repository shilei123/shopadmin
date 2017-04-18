package com.sunchin.shop.admin.goodsManager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScGoods;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings("rawtypes")
@Repository("scGoodsDAO")
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
			String auditSts = pageBean.getQueryParams().get("auditSts");
			if(StringUtils.isNotBlank(auditSts)) {
				String[] auditStss = auditSts.split(",");
				StringBuffer temp = new StringBuffer(" and (");
				String m = "";
				for (String str : auditStss) {
					temp.append(m).append(" t.audit_sts=? ");
					params.add(str);
					m = " or ";
				}
				temp.append(")");
				sql.append(temp);
			}
			
			String goodsSts = pageBean.getQueryParams().get("goodsSts");
			if(StringUtils.isNotBlank(goodsSts)) {
				String[] goodsStss = goodsSts.split(",");
				StringBuffer temp = new StringBuffer(" and (");
				String m = "";
				for (String str : goodsStss) {
					temp.append(m).append(" t.goods_sts=? ");
					params.add(str);
					m = " or ";
				}
				temp.append(")");
				sql.append(temp);
			}
		}
		sql.append(" ORDER BY t.create_time desc ");
		return sql.toString();
	}
	
	public Map queryMapById(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*, c.cate_name,r.available_num ");
		sql.append(" from sc_goods t ");
		sql.append(" left join sc_category c on t.cate_id = c.id ");
		sql.append(" left join sc_repertory r on r.goods_id = t.id ");
		sql.append(" where t.id = ? ");
		List list = DBUtil.getInstance().queryBySQL(sql.toString(), id);
		if(list != null && !list.isEmpty()) {
			return (Map) list.get(0);
		}
		return null;
	}
	
	public ScGoods queryPojoById(String id) {
		return (ScGoods) DBUtil.getInstance().get(ScGoods.class, id);
	}
	
	public int updateAuditStsById(String id, String auditSts) {
		return DBUtil.getInstance().executeHql(" update ScGoods t set t.auditSts=? where t.id=? ", auditSts, id);
	}
	
	public int updateGoodsStsById(String id, String goodsSts) {
		return DBUtil.getInstance().executeHql(" update ScGoods t set t.goodsSts=? where t.id=? ", goodsSts, id);
	}
}