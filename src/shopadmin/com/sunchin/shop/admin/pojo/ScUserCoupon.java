package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScUserCoupon  implements java.io.Serializable {

     private String id;
     private String userId;
     private String userName;
     private String couponId;
     private String couponName;
     private Double couponBlance;
     private Double couponXfBalance;
     private Date couponCreatdate;
     private Date couponExpirydate;
     private String orderSn;
     private String couponStatus;
     private Date optionTime;
     private String optionAdminid;
     private String optionRemark;


    public ScUserCoupon() {
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getCouponId() {
		return couponId;
	}


	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}


	public String getCouponName() {
		return couponName;
	}


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}


	public Double getCouponBlance() {
		return couponBlance;
	}


	public void setCouponBlance(Double couponBlance) {
		this.couponBlance = couponBlance;
	}


	public Double getCouponXfBalance() {
		return couponXfBalance;
	}


	public void setCouponXfBalance(Double couponXfBalance) {
		this.couponXfBalance = couponXfBalance;
	}


	public Date getCouponCreatdate() {
		return couponCreatdate;
	}


	public void setCouponCreatdate(Date couponCreatdate) {
		this.couponCreatdate = couponCreatdate;
	}


	public Date getCouponExpirydate() {
		return couponExpirydate;
	}


	public void setCouponExpirydate(Date couponExpirydate) {
		this.couponExpirydate = couponExpirydate;
	}


	public String getOrderSn() {
		return orderSn;
	}


	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}


	public String getCouponStatus() {
		return couponStatus;
	}


	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}


	public Date getOptionTime() {
		return optionTime;
	}


	public void setOptionTime(Date optionTime) {
		this.optionTime = optionTime;
	}


	public String getOptionAdminid() {
		return optionAdminid;
	}


	public void setOptionAdminid(String optionAdminid) {
		this.optionAdminid = optionAdminid;
	}


	public String getOptionRemark() {
		return optionRemark;
	}


	public void setOptionRemark(String optionRemark) {
		this.optionRemark = optionRemark;
	}
    
}