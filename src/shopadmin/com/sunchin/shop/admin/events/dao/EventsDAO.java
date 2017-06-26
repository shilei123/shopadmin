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
		sql.append(" to_char(t1.start_time,'yyyy-mm-dd hh24:mi:ss') start_time,to_char(t1.end_time,'yyyy-mm-dd hh24:mi:ss') end_time,t1.create_time ");
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
				sql.append(" and t1.start_time >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
			String endTime = pageBean.getQueryParams().get("endRegTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime);
				sql.append(" and t1.end_time <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
		}
		sql.append(" order by t1.create_time desc ");
		return sql.toString();
	}
	
	@SuppressWarnings("unchecked")
	public ScEvents findScEventsById(String id){
		Map<String, String> params = new HashMap<String, String>(2);
		params.put("id", id);
		params.put("flag", FlagEnum.ACT.getCode());
		List<ScEvents> lists = DBUtil.getInstance().queryByPojo(ScEvents.class, params);
		if(lists != null && !lists.isEmpty())	{
			return lists.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryEvents(String id) {
		StringBuffer sql = new StringBuffer(" select t1.*,to_char(t1.start_time,'yyyy-mm-dd hh24:mi:ss') start_time,to_char(t1.end_time,'yyyy-mm-dd hh24:mi:ss') end_time,t2.id eventsGoodsId,t2.events_id,t2.goods_id,t2.goods_child_id,t2.events_money,t2.scope,t3.goods_name ");
		sql.append(" from sc_events t1 ");
		sql.append(" left join sc_events_goods t2 on t1.id=t2.events_id ");
		sql.append(" left join sc_goods t3 on t3.id = t2.goods_id ");
		sql.append(" where t1.id=? ");
		sql.append(" and t1.flag=? ");
		List<Map<String,Object>> lists =DBUtil.getInstance().queryBySQL(sql.toString(),id,FlagEnum.ACT.getCode());
		if(lists != null && !lists.isEmpty()){
			return lists;
		}
		return null;
	}
}
