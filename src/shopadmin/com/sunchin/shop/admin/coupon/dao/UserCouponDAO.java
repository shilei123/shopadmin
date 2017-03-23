package com.sunchin.shop.admin.coupon.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;


import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.StatusEnum;
import com.sunchin.shop.admin.pojo.ScUserCoupon;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("userCouponDAO")
public class UserCouponDAO extends PageDAO{
	
	public int queryUserCouponCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.COUPON_STATUS.getType());
		String sql = this.UserCouponWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScUserCoupon> queryUserCouponPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.COUPON_STATUS.getType());
		String sql = this.UserCouponWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	private String UserCouponWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer("select t1.id,t3.user_name,t1.coupon_name,t1.coupon_blance,t1.coupon_xf_balance,t1.order_sn, ");
		sql.append(" to_char(t1.coupon_creatdate,'yyyy-mm-dd') coupon_creatdate,to_char(t1.coupon_expirydate,'yyyy-mm-dd') coupon_expirydate, ");
		sql.append(" t2.name coupon_status ");
		sql.append(" from sc_user_coupon t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.coupon_status ");
		sql.append(" left join sc_user t3 on t1.user_id=t3.id");
		sql.append(" where t2.type=?");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String sts = pageBean.getQueryParams().get("sts");
			if (StringUtils.isNotBlank(sts)&& !"-1".equals(sts)){
				params.add(sts);
				sql.append(" and t1.coupon_status=? ");
			}
			String userName = pageBean.getQueryParams().get("userName");
			if (StringUtils.isNotBlank(userName)){
				params.add(userName+"%");
				sql.append(" and t3.user_name like ? ");
			}
			String couponName = pageBean.getQueryParams().get("couponName");
			if (StringUtils.isNotBlank(couponName)){
				params.add(couponName+"%");
				sql.append(" and t1.coupon_name like ? ");
			}
			String startTime = pageBean.getQueryParams().get("startRegTime");
			if (StringUtils.isNotBlank(startTime)){
				params.add(startTime);
				sql.append(" and t1.coupon_expirydate >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
			String endTime = pageBean.getQueryParams().get("endRegTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59 ");
				sql.append(" and t1.coupon_expirydate <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
		}
		return sql.toString();
	}
	
	/**
	 * 查询用户优惠券是否发行
	 */
	
	@SuppressWarnings("unchecked")
	public ScUserCoupon findUserCoupon(String couponId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("couponId", couponId);
		List<ScUserCoupon> userCoupon= DBUtil.getInstance().queryByPojo(ScUserCoupon.class, params);
		if(userCoupon != null && !userCoupon.isEmpty())	{
			return userCoupon.get(0);
		}
		return null;
	}
}
