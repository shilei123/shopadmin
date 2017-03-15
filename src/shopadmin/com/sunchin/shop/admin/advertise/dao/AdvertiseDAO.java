package com.sunchin.shop.admin.advertise.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScAdvertise;
import com.sunchin.shop.admin.pojo.ScBcuser;
import com.sunchin.shop.admin.pojo.ScEventsinfo;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("advertiseDAO")
public class AdvertiseDAO extends PageDAO{

	public int queryAdvertiseCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScBcuser> queryAdvertisefoPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.name,t1.memo,decode(t1.linkkind,'0','商品','1','活动','2','其他URL','3','类别') linkkind,t1.ordernumb,decode(t1.isuse,'0','是','1','否') isuse, ");
		sql.append(" t1.type,t1.kind,to_char(t1.start_time,'yyyy-mm-dd') start_time,to_char(t1.end_time,'yyyy-mm-dd') end_time,t1.create_time ");
		sql.append(" from sc_advertise t1 ");
		sql.append(" where t1.flag=? ");
		
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String name = pageBean.getQueryParams().get("name");
			if (StringUtils.isNotBlank(name)){
				params.add("%"+name+"%");
				sql.append(" and t1.name like ? ");
			}
			String linkkind = pageBean.getQueryParams().get("linkkind");
			if (StringUtils.isNotBlank(linkkind) && !"-1".equals(linkkind)){
				params.add(linkkind);
				sql.append(" and t1.linkkind=? ");
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
	public Map<String, Object> findAdvertiseList(String id) {
		StringBuffer sql = new StringBuffer(" select t1.*,t2.name infoName from sc_advertise t1 ");
		sql.append(" left join sc_eventsinfo t2 on t2.id=t1.imglink ");
		sql.append(" where t1.flag=?");
		sql.append(" and t1.id=?");
		//待完善
		List<Map<String,Object>> lists= DBUtil.getInstance().queryBySQL(sql.toString(),FlagEnum.ACT.getCode(),id);
		if(lists != null && !lists.isEmpty()){
			return lists.get(0);
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public ScAdvertise findScAdvertiseById(String id) {
		Map<String, String> params = new HashMap<String, String>(2);
		params.put("id", id);
		params.put("flag", FlagEnum.ACT.getCode());
		List<ScAdvertise> lists = DBUtil.getInstance().queryByPojo(ScAdvertise.class, params);
		if(lists != null && !lists.isEmpty())	{
			return lists.get(0);
		}
		return null;
	}
}
