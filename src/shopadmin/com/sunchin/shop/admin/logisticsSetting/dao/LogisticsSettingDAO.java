package com.sunchin.shop.admin.logisticsSetting.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.IsuseEnum;
import com.sunchin.shop.admin.pojo.ScExpressProvider;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("logisticsSettingDAO")
public class LogisticsSettingDAO extends PageDAO{

	public int queryLogisticsCount(PageBean pageBean) {
		List<String> params = new ArrayList<>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.EXPRESS_TYPE.getType());
		params.add(DictionaryTypeEnum.ISUSE.getType());
		String sql = this.buildWhereSql(pageBean,params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScExpressProvider> queryLogisticsPagination(PageBean pageBean) {
		List<String> params = new ArrayList<>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.EXPRESS_TYPE.getType());
		params.add(DictionaryTypeEnum.ISUSE.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
			StringBuffer sql = new StringBuffer(" select t1.id,t1.code,t1.for_short,t1.ename,t1.full_name,t2.name express_type,t1.create_time,t3.name isuse ");
			sql.append(" from sc_express_provider t1 ");
			sql.append(" left join sc_dictionary t2 on t2.code=t1.express_type ");
			sql.append(" left join sc_dictionary t3 on t3.code=t1.isuse ");
			sql.append(" where t1.flag=? ");
			sql.append(" and t2.type=? ");
			sql.append(" and t3.type=? ");
			if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()){
				String forShort = pageBean.getQueryParams().get("forShort");
				if(StringUtils.isNotBlank(forShort)){
					params.add(forShort+"%");
					sql.append(" and t1.for_short like ? ");
				}
				String type = pageBean.getQueryParams().get("type");
				if(StringUtils.isNotBlank(type) && !"-1".equals(type)){
					params.add(type);
					sql.append(" and t1.express_type=? ");
				}
			}
			sql.append(" order by t1.create_time desc ");
		return sql.toString();
	}

	
	@SuppressWarnings("unchecked")
	public List<ScExpressProvider> queryLogisticsList() {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("flag",FlagEnum.ACT.getCode());
		params.put("isuse",IsuseEnum.VALID.getCode());
		List<ScExpressProvider> lists = DBUtil.getInstance().queryByPojo(ScExpressProvider.class, params);
		if(lists != null && !lists.isEmpty()){
			return lists;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ScExpressProvider queryExpressById(String id) {
		Map<String,Object> params = new HashMap<>();
		params.put("id",id);
		params.put("flag",FlagEnum.ACT.getCode());
		List<ScExpressProvider> lists = DBUtil.getInstance().queryByPojo(ScExpressProvider.class, params);
		if(lists != null && !lists.isEmpty()){
			return lists.get(0);
		}
		return null;
	}

	/**
	 * 发货页面初始化快递服务商的选择框 modify by aobingcheng 2017/04/28
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> queryAllCompany() {
		String hql = " from ScExpressProvider where flag=? ";
		return DBUtil.getInstance().queryByHql(hql, FlagEnum.ACT.getCode());
	}
}
