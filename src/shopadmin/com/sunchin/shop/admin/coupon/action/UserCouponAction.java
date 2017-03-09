package com.sunchin.shop.admin.coupon.action;

import javax.annotation.Resource;
import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.coupon.service.IUserCouponService;
import com.sunchin.shop.admin.pojo.ScUserCoupon;

import framework.action.PageAction;
import framework.bean.PageBean;

public class UserCouponAction extends PageAction{
	@Resource(name="userCouponService")
	private IUserCouponService userCouponService;
	
	private ScUserCoupon userCoupon;
	
	
	/**
	 * 查询
	 * @return
	 */
	public String queryUserCoupon() {
		try {
			PageBean resultData = userCouponService.queryUserCouponList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询单条记录
	 * @return
	 */
	public String findUserCoupon() {
		try {
			userCoupon = userCouponService.getUserCoupon(userCoupon.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String save() {
		try {
			userCouponService.saveUserCoupon(userCoupon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public ScUserCoupon getUserCoupon() {
		return userCoupon;
	}

	public void setUserCoupon(ScUserCoupon userCoupon) {
		this.userCoupon = userCoupon;
	}
	
	
}
