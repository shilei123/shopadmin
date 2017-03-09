package com.sunchin.shop.admin.coupon.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScCoupon;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScUserCoupon;

import framework.bean.PageBean;

public interface ICouponService {

	PageBean queryCouponList(PageBean pageBean);

	ScCoupon getCoupon(String id);

	void saveCoupon(ScCoupon coupon);

	void deleteCoupon(String id);

	List<ScDictionary> findCouponType(String type) throws Exception;

}
