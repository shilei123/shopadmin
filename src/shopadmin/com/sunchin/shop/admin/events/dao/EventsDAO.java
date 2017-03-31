package com.sunchin.shop.admin.events.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScEvents;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("eventsDAO")
public class EventsDAO extends PageDAO{

	public int queryEventsCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.COUPON_STATUSS.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScEvents> queryEventsPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.ISUSE.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.name,t2.name isuse, ");
		sql.append(" to_char(t1.starttime,'yyyy-mm-dd') start_time,to_char(t1.endtime,'yyyy-mm-dd') end_time,t1.create_time ");
		sql.append(" from sc_events t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.isuse ");
		sql.append(" where t1.flag=? ");
		sql.append(" and t2.type=?");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String name = pageBean.getQueryParams().get("name");
			if (StringUtils.isNotBlank(name)){
				params.add(name+"%");
				sql.append(" and t1.name like ? ");
			}
			String startTime = pageBean.getQueryParams().get("startRegTime");
			if (StringUtils.isNotBlank(startTime)){
				params.add(startTime);
				sql.append(" and t1.starttime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
			String endTime = pageBean.getQueryParams().get("endRegTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59 ");
				sql.append(" and t1.endtime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
		}
		sql.append(" order by t1.create_time desc ");
		return sql.toString();
	}
	
	@SuppressWarnings("unchecked")
	public ScEvents findScEventsinfoById(String id){
		Map<String, String> params = new HashMap<String, String>(2);
		params.put("id", id);
		params.put("flag", FlagEnum.ACT.getCode());
		List<ScEvents> lists = DBUtil.getInstance().queryByPojo(ScEvents.class, params);
		if(lists != null && !lists.isEmpty())	{
			return lists.get(0);
		}
		return null;
	}
}
