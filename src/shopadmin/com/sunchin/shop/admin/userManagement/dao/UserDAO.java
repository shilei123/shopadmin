package com.sunchin.shop.admin.userManagement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.chart.PlotAreaRecord;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScAdvertise;
import com.sunchin.shop.admin.pojo.ScGrade;
import com.sunchin.shop.admin.pojo.ScUserBase;
import com.sunchin.shop.admin.pojo.ScIdentity;
import com.sunchin.shop.admin.pojo.ScPurse;
import com.sunchin.shop.admin.pojo.ScUser;
import com.sunchin.shop.admin.pojo.ScWallet;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("userDAO")
public class UserDAO extends PageDAO{

	public int queryUserBaseCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.USER_SEX.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScUserBase> queryUserBasePagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.USER_SEX.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer("   select t1.id,t1.user_id,t3.user_name,t1.user_name true_name,t1.phone,t1.mail,t2.name sex from sc_user_base t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.sex ");
		sql.append(" left join sc_user t3 on t3.id=t1.user_id");
		sql.append(" where t2.type=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String userName = pageBean.getQueryParams().get("userName");
			if (StringUtils.isNotBlank(userName)){
				params.add(userName+"%");
				sql.append(" and t3.user_name like ? ");
			}
			String trueName = pageBean.getQueryParams().get("trueName");
			if (StringUtils.isNotBlank(trueName)){
				params.add("%"+trueName+"%");
				sql.append(" and t1.user_name like ? ");
			}
			String userSex = pageBean.getQueryParams().get("userSex");
			if (StringUtils.isNotBlank(userSex) && !"-1".equals(userSex)){
				params.add(userSex);
				sql.append(" and t1.sex=? ");
			}
		}
		return sql.toString();
	}

	public int queryUserCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.IDENTITY_STATUS.getType());
		params.add(DictionaryTypeEnum.USER_STATUS.getType());
		params.add(FlagEnum.ACT.getCode());
		String sql = this.userWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScUser> queryUserPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.IDENTITY_STATUS.getType());
		params.add(DictionaryTypeEnum.USER_STATUS.getType());
		params.add(FlagEnum.ACT.getCode());
		String sql = this.userWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	
	private String userWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.user_name,t1.nick_name, ");
		sql.append(" to_char(t1.create_time,'yyyy-mm-dd') create_time,t4.name user_status,t2.identity_card,to_char(t2.identity_card_validity,'yyyy-mm-dd') identity_card_validity, ");
		sql.append(" t3.name identity_status ");
		sql.append(" from sc_user t1 ");
		sql.append(" left join sc_identity t2 on t1.id=t2.user_id ");
		sql.append(" left join sc_dictionary t3 on t3.code=t2.identity_status ");
		sql.append(" left join sc_dictionary t4 on t4.code=t1.user_status ");
		sql.append(" where t3.type=? ");
		sql.append(" and t4.type=? ");
		sql.append(" and t1.flag=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String userName = pageBean.getQueryParams().get("userName");
			if (StringUtils.isNotBlank(userName)){
				params.add(userName+"%");
				sql.append(" and t1.user_name like ? ");
			}
			String nickName = pageBean.getQueryParams().get("nickName");
			if (StringUtils.isNotBlank(nickName)){
				params.add(nickName+"%");
				sql.append(" and t1.nick_name like ? ");
			}
			String userStatus = pageBean.getQueryParams().get("userStatus");
			if (StringUtils.isNotBlank(userStatus) && !"-1".equals(userStatus)){
				params.add(userStatus);
				sql.append(" and t1.user_status=? ");
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

	
	public int queryUserPurseCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.TRADE_TYPE.getType());
		params.add(DictionaryTypeEnum.TRADE_TYPE.getType());
		params.add(DictionaryTypeEnum.OPTION_TYPE.getType());
		params.add(DictionaryTypeEnum.PURSE_TYPE.getType());
		String sql = this.userPurseWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScPurse> queryUserPursePagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.TRADE_TYPE.getType());
		params.add(DictionaryTypeEnum.TRADE_TYPE.getType());
		params.add(DictionaryTypeEnum.OPTION_TYPE.getType());
		params.add(DictionaryTypeEnum.PURSE_TYPE.getType());
		String sql = this.userPurseWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}

	private String userPurseWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.trade_amount,t1.user_amount,t1.trade_sn,t1.pay_account,t1.pay_open_bank,t2.user_name, ");
		sql.append(" t3.name trade_type,t4.name trade_state, ");
		sql.append(" t5.name option_type,t6.name purse_type, ");
		sql.append(" to_char(t1.create_time,'yyyy-MM-dd hh24:mi:ss') create_time,to_char(t1.option_time,'yyyy-MM-dd hh24:mi:ss') option_time ");
		sql.append(" from sc_purse t1 ");
		sql.append(" left join sc_user t2 on t2.id=t1.user_id ");
		sql.append(" left join sc_dictionary t3 on t3.code=t1.trade_type ");
		sql.append(" left join sc_dictionary t4 on t4.code=t1.trade_state ");
		sql.append(" left join sc_dictionary t5 on t5.code=t1.option_type ");
		sql.append(" left join sc_dictionary t6 on t6.code=t1.purse_type ");
		sql.append(" where t1.flag=? ");
		sql.append(" and t3.type=? ");
		sql.append(" and t4.type=? ");
		sql.append(" and t5.type=? ");
		sql.append(" and t6.type=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String userName = pageBean.getQueryParams().get("userName");
			if (StringUtils.isNotBlank(userName)){
				params.add(userName+"%");
				sql.append(" and t2.user_name like ? ");
			}
			String tradeState = pageBean.getQueryParams().get("tradeState");
			if (StringUtils.isNotBlank(tradeState) && !"-1".equals(tradeState)){
				params.add(tradeState);
				sql.append(" and t1.trade_state=? ");
			}
			String tradeType = pageBean.getQueryParams().get("tradeType");
			if (StringUtils.isNotBlank(tradeType) && !"-1".equals(tradeType)){
				params.add(tradeType);
				sql.append(" and t1.trade_type=? ");
			}
			String purseType = pageBean.getQueryParams().get("purseType");
			if (StringUtils.isNotBlank(purseType) && !"-1".equals(purseType)){
				params.add(purseType);
				sql.append(" and t1.purse_type=? ");
			}
			String optionType = pageBean.getQueryParams().get("optionType");
			if (StringUtils.isNotBlank(optionType) && !"-1".equals(optionType)){
				params.add(optionType);
				sql.append(" and t1.option_type=? ");
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
	
	public int queryUserIdentityCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.IDENTITY_STATUS.getType());
		String sql = this.userIdentityWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScIdentity> queryUserIdentityPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.IDENTITY_STATUS.getType());
		String sql = this.userIdentityWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	private String userIdentityWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t3.user_name,t1.identity_card,t2.name identity_status,t1.identity_frontal,t1.identity_back,t1.identity_hold_frontal, ");
		sql.append(" to_char(t1.application_time,'yyyy-MM-dd hh24:mi:ss') application_time,t1.applicant,t1.failure_reason ");
		sql.append(" from sc_identity t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.identity_status ");
		sql.append(" left join sc_user t3 on t3.id=t1.user_id");
		sql.append(" where t2.type=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String userName = pageBean.getQueryParams().get("userName");
			if (StringUtils.isNotBlank(userName)){
				params.add(userName+"%");
				sql.append(" and t3.user_name like ? ");
			}
			String identityCard = pageBean.getQueryParams().get("identityCard");
			if (StringUtils.isNotBlank(identityCard)){
				params.add(identityCard+"%");
				sql.append(" and t1.identity_card like ? ");
			}
			String identityStatus = pageBean.getQueryParams().get("identityStatus");
			if (StringUtils.isNotBlank(identityStatus) && !"-1".equals(identityStatus)){
				params.add(identityStatus);
				sql.append(" and t1.identity_status=? ");
			}
			String startTime = pageBean.getQueryParams().get("startRegTime");
			if (StringUtils.isNotBlank(startTime)){
				params.add(startTime);
				sql.append(" and t1.application_time >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
			String endTime = pageBean.getQueryParams().get("endRegTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59 ");
				sql.append(" and t1.application_time <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
		}
		return sql.toString();
	}

	public int queryUserWalletCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.PURSE_TYPE.getType());
		params.add(DictionaryTypeEnum.UNIT.getType());
		String sql = this.userWalletWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScWallet> queryUserWalletPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.PURSE_TYPE.getType());
		params.add(DictionaryTypeEnum.UNIT.getType());
		String sql = this.userWalletWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	private String userWalletWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select  t1.id,t2.name purse_type,t4.user_name,t1.remark,t1.user_money,t3.name unit ");
		sql.append(" from sc_wallet t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.purse_type ");
		sql.append(" left join sc_dictionary t3 on t3.code=t1.unit ");
		sql.append(" left join sc_user t4 on t4.id=t1.user_id ");
		sql.append(" where t1.flag=? ");
		sql.append(" and t2.type=? ");
		sql.append(" and t3.type=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String userName = pageBean.getQueryParams().get("userName");
			if (StringUtils.isNotBlank(userName)){
				params.add(userName+"%");
				sql.append(" and t4.user_name like ? ");
			}
			String purseType = pageBean.getQueryParams().get("purseType");
			if (StringUtils.isNotBlank(purseType) && !"-1".equals(purseType)){
				params.add(purseType);
				sql.append(" and t1.purse_type=? ");
			}
		}
		return sql.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	public ScUser queryUserById(String id) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id",id);
		List<ScUser> lists = DBUtil.getInstance().queryByPojo(ScUser.class, params);
		if(lists != null && !lists.isEmpty()){
			return lists.get(0);
		}
		return null;
	}

	public int queryUserGradeCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.USER_GRADE.getType());
		params.add(DictionaryTypeEnum.ISUSE.getType());
		String sql = this.userGradeWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScGrade> queryUserGradePagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.USER_GRADE.getType());
		params.add(DictionaryTypeEnum.ISUSE.getType());
		String sql = this.userGradeWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}

	private String userGradeWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.need_integral,t2.name user_grade,t3.name isuse,t1.remark ");
		sql.append(" from sc_grade t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.user_grade ");
		sql.append(" left join sc_dictionary t3 on t3.code=t1.isuse ");
		sql.append(" where t1.flag=? ");
		sql.append(" and t2.type=? ");
		sql.append(" and t3.type=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String gradeType = pageBean.getQueryParams().get("gradeType");
			if (StringUtils.isNotBlank(gradeType) && !"-1".equals(gradeType)){
				params.add(gradeType);
				sql.append(" and t1.user_grade=? ");
			}
		}
		return sql.toString();
	}
	
	@SuppressWarnings("unchecked")
	public ScGrade queryGradeById(String id) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id",id);
		params.put("flag",FlagEnum.ACT.getCode());
		List<ScGrade> lists = DBUtil.getInstance().queryByPojo(ScGrade.class, params);
		if(lists != null && !lists.isEmpty()){
			return lists.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ScUserBase queryUserBaseByUserId(String userId){
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId",userId);
		List<ScUserBase> lists = DBUtil.getInstance().queryByPojo(ScUserBase.class, params);
		if(lists != null && !lists.isEmpty()){
			return lists.get(0);
		}
		return null;
	}
}
