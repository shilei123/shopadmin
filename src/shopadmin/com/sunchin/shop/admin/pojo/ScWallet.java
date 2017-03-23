package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScWallet implements java.io.Serializable {

	private String id;
	private String userId;
	private String purseType;
	private String unit;
	private String userMoney;
	private Date createTime;
	private Date updateTime;
	private String remark;
	private String belong;

	public ScWallet() {
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

	public String getPurseType() {
		return purseType;
	}

	public void setPurseType(String purseType) {
		this.purseType = purseType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(String userMoney) {
		this.userMoney = userMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}


}