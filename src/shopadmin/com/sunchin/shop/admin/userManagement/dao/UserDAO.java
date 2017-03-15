package com.sunchin.shop.admin.userManagement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScAdvertise;
import com.sunchin.shop.admin.pojo.ScBcuser;
import com.sunchin.shop.admin.pojo.ScUser;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("userDAO")
public class UserDAO extends PageDAO{

	public int queryUserBcuserCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScBcuser> queryUserBcuserPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer("   select t1.id,t1.user_id,t1.user_name,t1.true_name,t1.user_phone,t1.user_mail,decode(t1.user_sex,'0','男','1','女') user_sex from sc_bcuser t1 ");
		sql.append(" where 1=1 ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String userName = pageBean.getQueryParams().get("userName");
			if (StringUtils.isNotBlank(userName)){
				params.add("%"+userName+"%");
				sql.append(" and t1.user_name like ? ");
			}
			String trueName = pageBean.getQueryParams().get("trueName");
			if (StringUtils.isNotBlank(trueName)){
				params.add("%"+trueName+"%");
				sql.append(" and t1.true_name like ? ");
			}
			String userSex = pageBean.getQueryParams().get("userSex");
			if (StringUtils.isNotBlank(userSex) && !"-1".equals(userSex)){
				params.add(userSex);
				sql.append(" and t1.user_sex=? ");
			}
		}
		return sql.toString();
	}

	public int queryUserCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		String sql = this.userWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScUser> queryUserPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		String sql = this.userWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	
	private String userWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.user_name,t1.nick_name,decode(t1.tixian_status,'0','无','1','申请中') tixian_status, ");
		sql.append(" to_char(t1.create_time,'yyyy-mm-dd') create_time,decode(t1.user_status,'0','注销','1','正常','2','冻结') user_status,t2.identity_card,to_char(t2.identity_card_validity,'yyyy-mm-dd') identity_card_validity, ");
		sql.append(" decode(t2.identity_status,'0','未认证','1','申请中','2','已认证') identity_status ");
		sql.append(" from sc_user t1 ");
		sql.append(" left join sc_identity t2 on t1.id=t2.user_id ");
		sql.append(" where 1=1 ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String userName = pageBean.getQueryParams().get("userName");
			if (StringUtils.isNotBlank(userName)){
				params.add("%"+userName+"%");
				sql.append(" and t1.user_name like ? ");
			}
			String nickName = pageBean.getQueryParams().get("nickName");
			if (StringUtils.isNotBlank(nickName)){
				params.add("%"+nickName+"%");
				sql.append(" and t1.nick_name like ? ");
			}
			String userStatus = pageBean.getQueryParams().get("userStatus");
			if (StringUtils.isNotBlank(userStatus) && !"-1".equals(userStatus)){
				params.add(userStatus);
				sql.append(" and t1.user_status=? ");
			}
			String tixianStatus = pageBean.getQueryParams().get("tixianStatus");
			if (StringUtils.isNotBlank(tixianStatus) && !"-1".equals(tixianStatus)){
				params.add(tixianStatus);
				sql.append(" and t1.tixian_status=? ");
			}
			String identityStatus = pageBean.getQueryParams().get("identityStatus");
			if (StringUtils.isNotBlank(identityStatus) && !"-1".equals(identityStatus)){
				params.add(identityStatus);
				sql.append(" and t2.identity_status=? ");
			}
			String startTime = pageBean.getQueryParams().get("startRegTime");
			if (StringUtils.isNotBlank(startTime)){
				params.add(startTime);
				sql.append(" and t1.create_time >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
			String endTime = pageBean.getQueryParams().get("endRegTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59 ");
				sql.append(" and t1.create_time <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
		}
		return sql.toString();
	}

	public ScUser queryUserById(String id) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id",id);
		List<ScUser> lists = DBUtil.getInstance().queryByPojo(ScUser.class, params);
		if(lists != null && !lists.isEmpty()){
			return lists.get(0);
		}
		return null;
	}

	
}
