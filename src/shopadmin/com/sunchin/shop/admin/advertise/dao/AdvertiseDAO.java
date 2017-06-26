package com.sunchin.shop.admin.advertise.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.AuditStsEnum;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.PublishTypeEnum;
import com.sunchin.shop.admin.dict.PutawayEnum;
import com.sunchin.shop.admin.pojo.ScAdvertise;
import com.sunchin.shop.admin.pojo.ScUserBase;
import com.sunchin.shop.admin.pojo.ScEvents;
import com.sunchin.shop.admin.pojo.ScGoods;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("advertiseDAO")
public class AdvertiseDAO extends PageDAO{

	public int queryAdvertiseCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.ADVERTISE_LINKKIND.getType());
		params.add(DictionaryTypeEnum.ADVERTISE_ISUSE.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScUserBase> queryAdvertisefoPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.ADVERTISE_LINKKIND.getType());
		params.add(DictionaryTypeEnum.ADVERTISE_ISUSE.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.name,t1.memo,t2.name linkkind,t1.ordernumb,t3.name isuse, ");
		sql.append(" t1.type,t1.kind,to_char(t1.start_time,'yyyy-mm-dd hh24:mi:ss') start_time,to_char(t1.end_time,'yyyy-mm-dd hh24:mi:ss') end_time,t1.create_time ");
		sql.append(" from sc_advertise t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.linkkind ");
		sql.append(" left join sc_dictionary t3 on t3.code=t1.isuse ");
		sql.append(" where t1.flag=? ");
		sql.append(" and t2.type=?");
		sql.append(" and t3.type=?");
		
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String name = pageBean.getQueryParams().get("name");
			if (StringUtils.isNotBlank(name)){
				params.add(name+"%");
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
				sql.append(" and t1.start_time >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
			String endTime = pageBean.getQueryParams().get("endRegTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59 ");
				sql.append(" and t1.end_time <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
		}
		sql.append(" order by t1.create_time desc ");
		return sql.toString();
	}

	
	@SuppressWarnings("unchecked")
	public Map<String, Object> findAdvertiseList(String id) {
		StringBuffer sql = new StringBuffer(" select t1.*,to_char(t1.start_time,'yyyy-mm-dd hh24:mi:ss') start_time,to_char(t1.end_time,'yyyy-mm-dd hh24:mi:ss') end_time,t2.name infoName,t4.goods_name,t5.name events_name,t6.cate_name ");
		sql.append(" from sc_advertise t1 ");
		sql.append(" left join sc_events t2 on t2.id=t1.imglink ");
		sql.append(" left join sc_goods t4 on t4.id=t1.imglink ");
		sql.append(" left join sc_events t5 on t5.id=t1.imglink ");
		sql.append(" left join sc_category t6 on t6.id=t1.imglink ");
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

	public int queryGoodsCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		/*params.add(DictionaryTypeEnum.ISUSE.getType());
		params.add(PutawayEnum.YES.getCode());
		params.add(AuditStsEnum.PASS.getCode());*/
		String sql = this.goodsWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScGoods> queryGoodsPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		/*params.add(DictionaryTypeEnum.ISUSE.getType());
		params.add(PutawayEnum.YES.getCode());
		params.add(AuditStsEnum.PASS.getCode());*/
		String sql = this.goodsWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	private String goodsWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.title,t3.name isuse,t2.brand_name,t1.create_time ");
		sql.append(" from  sc_goods t1 ");
		sql.append(" left join sc_brand t2 on t1.brand_id=t2.id ");
		sql.append(" left join sc_dictionary t3 on t3.code=t1.isuse ");
		sql.append(" where t1.flag=?");
		/*sql.append(" and t3.type=? ");
		sql.append(" and t1.putaway=?");
		sql.append(" and t1.auditSts=?");*/
		
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String goodsName = pageBean.getQueryParams().get("goodsName");
			if (StringUtils.isNotBlank(goodsName)){
				params.add(goodsName+"%");
				sql.append(" and t1.title like ? ");
			}
		}
		sql.append(" order by t1.create_time desc ");
		return sql.toString();
	}
}
