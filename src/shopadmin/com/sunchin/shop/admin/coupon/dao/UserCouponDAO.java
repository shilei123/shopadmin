package com.sunchin.shop.admin.coupon.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.StatusEnum;
import com.sunchin.shop.admin.pojo.ScUserCoupon;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("userCouponDAO")
public class UserCouponDAO extends PageDAO{
	
	public int queryUserCouponCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		String sql = this.UserCouponWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScUserCoupon> queryUserCouponPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		String sql = this.UserCouponWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	private String UserCouponWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer("select t1.id,t1.user_name,t1.coupon_name,t1.coupon_blance,t1.coupon_xf_balance,t1.order_sn, ");
		sql.append(" to_char(t1.coupon_creatdate,'yyyy-mm-dd hh24:mi:ss') coupon_creatdate,to_char(t1.coupon_expirydate,'yyyy-mm-dd hh24:mi:ss') coupon_expirydate, ");
		sql.append(" decode(t1.coupon_status,'0','未使用','1','已使用','2','作废') coupon_status ");
		sql.append(" from sc_user_coupon t1 ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String sts = pageBean.getQueryParams().get("sts");
			if (StringUtils.isNotBlank(sts)&& !"-1".equals(sts)){
				params.add(sts);
				sql.append(" where t1.coupon_status=? ");
			}else{
				params.add(StatusEnum.ZERO.getCode());
				params.add(StatusEnum.ONE.getCode());
				params.add(StatusEnum.TWO.getCode());
				sql.append(" where (t1.coupon_status=? or t1.coupon_status=? or t1.coupon_status=?) ");
			}
			String userName = pageBean.getQueryParams().get("userName");
			if (StringUtils.isNotBlank(userName)){
				params.add("%"+userName+"%");
				sql.append(" and t1.user_name like ? ");
			}
			String couponName = pageBean.getQueryParams().get("couponName");
			if (StringUtils.isNotBlank(couponName)){
				params.add("%"+couponName+"%");
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
