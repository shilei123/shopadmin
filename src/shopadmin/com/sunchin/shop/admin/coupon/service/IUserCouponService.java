package com.sunchin.shop.admin.coupon.service;

import com.sunchin.shop.admin.pojo.ScUserCoupon;

import framework.bean.PageBean;

public interface IUserCouponService {

	PageBean queryUserCouponList(PageBean pageBean);

	ScUserCoupon getUserCoupon(String id);

	void saveUserCoupon(ScUserCoupon userCoupon);
}
