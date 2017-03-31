package com.sunchin.shop.admin.freight.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScFreight;
import com.sunchin.shop.admin.pojo.ScUserCoupon;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("freightDAO")
public class FreightDAO extends PageDAO{

	public int queryFreightCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.TRANSPORT_MODE.getType());
		params.add(DictionaryTypeEnum.ISUSE.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScFreight> queryFreightPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.TRANSPORT_MODE.getType());
		params.add(DictionaryTypeEnum.ISUSE.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.template_name,t4.name isuse,t3.name transport_mode,t2.transport_range, ");
		sql.append(" t2.initial_int,t2.initial_price,t2.stack_int,t2.stack_price ");
		sql.append(" from sc_freight t1 ");
		sql.append(" left join sc_user_freight t2 on t2.freight_id=t1.id ");
		sql.append(" left join sc_dictionary t3 on t3.code=t2.transport_mode ");
		sql.append(" left join sc_dictionary t4 on t4.code=t1.isuse ");
		sql.append(" where t1.flag=? ");
		sql.append(" and t3.type=? ");
		sql.append(" and t4.type=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String templateName = pageBean.getQueryParams().get("templateName");
			if (StringUtils.isNotBlank(templateName)){
				params.add(templateName+"%");
				sql.append(" and t1.template_name like ? ");
			}
		}
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findFreightList(String id) {
		StringBuffer sql = new StringBuffer(" select t1.*,t2.* ");
		sql.append(" from sc_freight t1 ");
		sql.append(" left join sc_user_freight t2 on t1.id=t2.freight_id ");
		sql.append(" where t1.flag=?");
		sql.append(" and t1.id=?");
		List<Map<String, Object>> lists = DBUtil.getInstance().queryBySQL(sql.toString(),FlagEnum.ACT.getCode(),id);
		if(lists != null && !lists.isEmpty()){
			return lists;
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public ScFreight findFreight(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("flag", FlagEnum.ACT.getCode());
		List<ScFreight> freight= DBUtil.getInstance().queryByPojo(ScFreight.class, params);
		if(freight != null && !freight.isEmpty())	{
			return freight.get(0);
		}
		return null;
	}
	
}
