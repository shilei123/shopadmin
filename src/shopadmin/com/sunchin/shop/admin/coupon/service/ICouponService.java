package com.sunchin.shop.admin.coupon.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScCoupon;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScUserCoupon;

import framework.bean.PageBean;

public interface ICouponService {


	PageBean queryCouponList(PageBean pageBean) throws Exception;

	ScCoupon getCoupon(String id) throws Exception;

	void saveCoupon(ScCoupon coupon) throws Exception;

	void deleteCoupon(String id) throws Exception;


}
