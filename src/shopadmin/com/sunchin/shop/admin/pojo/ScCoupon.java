package com.sunchin.shop.admin.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * ScCoupon entity. @author MyEclipse Persistence Tools
 */

public class ScCoupon  implements java.io.Serializable {

     private String id;
     private String couponName;
     private String couponType;
     private Double couponZsBalance;
     private Double couponBlance;
     private Double couponXfBalance;
     private Integer couponExpiryDate;
     private String couponRemark;
     private String couponStatus;
     private String couponFlag;
     private Date createTime;
     private String optionAdminid;
     private Date optionTime;
     private String belong;


    public ScCoupon() {
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCouponName() {
		return couponName;
	}


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}


	public String getCouponType() {
		return couponType;
	}


	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}


	public Double getCouponZsBalance() {
		return couponZsBalance;
	}


	public void setCouponZsBalance(Double couponZsBalance) {
		this.couponZsBalance = couponZsBalance;
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


	public Integer getCouponExpiryDate() {
		return couponExpiryDate;
	}


	public void setCouponExpiryDate(Integer couponExpiryDate) {
		this.couponExpiryDate = couponExpiryDate;
	}


	public String getCouponRemark() {
		return couponRemark;
	}


	public void setCouponRemark(String couponRemark) {
		this.couponRemark = couponRemark;
	}


	public String getCouponStatus() {
		return couponStatus;
	}


	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}


	public String getCouponFlag() {
		return couponFlag;
	}


	public void setCouponFlag(String couponFlag) {
		this.couponFlag = couponFlag;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getOptionAdminid() {
		return optionAdminid;
	}


	public void setOptionAdminid(String optionAdminid) {
		this.optionAdminid = optionAdminid;
	}


	public Date getOptionTime() {
		return optionTime;
	}


	public void setOptionTime(Date optionTime) {
		this.optionTime = optionTime;
	}


	public String getBelong() {
		return belong;
	}


	public void setBelong(String belong) {
		this.belong = belong;
	}
	
	


}
