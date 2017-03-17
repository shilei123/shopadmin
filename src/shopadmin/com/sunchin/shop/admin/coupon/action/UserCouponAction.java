package com.sunchin.shop.admin.coupon.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.coupon.service.IUserCouponService;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScUserCoupon;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class UserCouponAction extends PageAction{
	@Resource(name="userCouponService")
	private IUserCouponService userCouponService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private ScUserCoupon userCoupon;
	private List<ScDictionary> dictionaryList;
	
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

	public String queryUserCouponType(){
		try {
			dictionaryList = dictService.findDictionaryType(DictionaryTypeEnum.COUPON_STATUS.getType());
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

	public List<ScDictionary> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<ScDictionary> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}
	
	
}
