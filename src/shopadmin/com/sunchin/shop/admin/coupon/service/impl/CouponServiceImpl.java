package com.sunchin.shop.admin.coupon.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javassist.tools.framedump;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.coupon.dao.CouponDAO;
import com.sunchin.shop.admin.coupon.dao.UserCouponDAO;
import com.sunchin.shop.admin.coupon.service.ICouponService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.StatusEnum;
import com.sunchin.shop.admin.pojo.ScCoupon;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScUserCoupon;
import com.sunchin.shop.admin.system.dao.ScDictionaryDAO;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("couponService")
public class CouponServiceImpl implements ICouponService{

	@Resource(name="couponDAO")
	private CouponDAO couponDAO;
	@Resource(name="userCouponDAO")
	private UserCouponDAO userCouponDAO;
	@Resource(name="scDictionaryDAO")
	private ScDictionaryDAO scDictionaryDAO;
	/**
	 * 查询
	 */
	@Override
	public PageBean queryCouponList(PageBean pageBean) {
		int total = couponDAO.queryCouponCount(pageBean);
		pageBean.setTotal(total);
		List<ScCoupon> pageData = couponDAO.queryCouponPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 查询单条记录
	 */
	@Override
	public ScCoupon getCoupon(String id) {
		Object obj = DBUtil.getInstance().get(ScCoupon.class, id);
		if(obj != null) {
			return (ScCoupon) obj;
		}
		return null;
	}
	
	
	
	/**
	 * 保存
	 */
	@Override
	public void saveCoupon(ScCoupon coupon) {
		if (coupon == null) {
			return;
		}
		DBUtil db = DBUtil.getInstance();
		// 新增
		if (StringUtils.isBlank(coupon.getId())) {
			String id = UUID.randomUUID().toString();
			coupon.setId(id);
			coupon.setCreateTime(new Date());
			coupon.setCouponStatus(StatusEnum.ACT.getCode());
			coupon.setCouponFlag(FlagEnum.ACT.getCode());
			db.insert(coupon);
		} else { // 修改
			ScCoupon vo = (ScCoupon) db.get(ScCoupon.class, coupon.getId());
			vo.setCouponName(coupon.getCouponName());
			vo.setCouponBlance(coupon.getCouponBlance());
			vo.setCouponXfBalance(coupon.getCouponXfBalance());
			vo.setCouponZsBalance(coupon.getCouponZsBalance());
			vo.setCouponType(coupon.getCouponType());
			vo.setCouponExpiryDate(coupon.getCouponExpiryDate());
			vo.setCouponRemark(coupon.getCouponRemark());
			vo.setOptionTime(new Date());
			db.update(vo);
		}
		
	}

	/**
	 *删除
	 */
	@Override
	public void deleteCoupon(String id) {
		DBUtil db = DBUtil.getInstance();
		ScUserCoupon userCoupon = userCouponDAO.findUserCoupon(id);
		ScCoupon coupon = getCoupon(id);
		if(userCoupon != null){
			if(coupon != null) {
				coupon.setCouponFlag(FlagEnum.HIS.getCode());
				db.update(coupon);
			}
		}else{
			if(coupon != null) {
				db.delete(coupon);
			}
		}
	}

	@Override
	public List<ScDictionary> findCouponType(String type) throws Exception {
			return couponDAO.queryCouponType(type);
	}
	
}
