package com.sunchin.shop.admin.coupon.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.coupon.service.ICouponService;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.pojo.ScCoupon;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class CouponAction extends PageAction{

	@Resource(name="couponService")
	private ICouponService couponService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private ScCoupon coupon;
	
	private List<ScDictionary> dictionaryList;
	
	
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = couponService.queryCouponList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 修改
	 * @return
	 */
	public String queryCoupon() {
		try {
			coupon = couponService.getCoupon(coupon.getId());
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
			couponService.saveCoupon(this.coupon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查看优惠券类型
	 */
	public String couponType(){
		try {
			dictionaryList = dictService.findDictionaryType(DictionaryTypeEnum.COUPON_TYPE.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	

	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		try {
			couponService.deleteCoupon(coupon.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public ScCoupon getCoupon() {
		return coupon;
	}

	public void setCoupon(ScCoupon coupon) {
		this.coupon = coupon;
	}

	public List<ScDictionary> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<ScDictionary> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

}
