package com.sunchin.shop.admin.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;







import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.coupon.dao.UserCouponDAO;
import com.sunchin.shop.admin.coupon.service.IUserCouponService;
import com.sunchin.shop.admin.pojo.ScCoupon;
import com.sunchin.shop.admin.pojo.ScUserCoupon;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("userCouponService")
public class UserCouponServiceImpl implements IUserCouponService{

	@Resource(name="userCouponDAO")
	private UserCouponDAO userCouponDAO;
	
	/**
	 * 删除
	 */
	@Override
	public PageBean queryUserCouponList(PageBean pageBean) {
		int total = userCouponDAO.queryUserCouponCount(pageBean);
		pageBean.setTotal(total);
		List<ScUserCoupon> pageData = userCouponDAO.queryUserCouponPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	/**
	 * 查询单条记录
	 */
	@Override
	public ScUserCoupon getUserCoupon(String id) {
		Object obj = DBUtil.getInstance().get(ScUserCoupon.class, id);
		if(obj != null) {
			return (ScUserCoupon) obj;
		}
		return null;
	}
	
	/**
	 * 保存
	 */
	@Override
	@Transactional
	public void saveUserCoupon(ScUserCoupon userCoupon) {
		if(userCoupon == null){
			return;
		}
		DBUtil db = DBUtil.getInstance();
		ScUserCoupon vo = getUserCoupon(userCoupon.getId());
		vo.setCouponStatus(userCoupon.getCouponStatus());
		db.update(vo);
	}
	
}
