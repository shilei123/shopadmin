package com.sunchin.shop.admin.coupon.service;

import com.sunchin.shop.admin.pojo.ScUserCoupon;

import framework.bean.PageBean;

public interface IUserCouponService {


	PageBean queryUserCouponList(PageBean pageBean) throws Exception;

	ScUserCoupon getUserCoupon(String id) throws Exception;

	void saveUserCoupon(ScUserCoupon userCoupon) throws Exception;

}
