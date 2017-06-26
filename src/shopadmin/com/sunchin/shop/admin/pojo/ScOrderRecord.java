package com.sunchin.shop.admin.pojo;

import java.util.Date;

@SuppressWarnings("serial")
public class ScOrderRecord implements java.io.Serializable {

	private String id;
	private String orderId;
	private String orderChangeBefore;
	private String orderChangeAfter;
	private String changeType;
	private String reason;
	private Date createTime;
	private String createUserId;
	private String flag;
	private String belong;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderChangeBefore() {
		return this.orderChangeBefore;
	}

	public void setOrderChangeBefore(String orderChangeBefore) {
		this.orderChangeBefore = orderChangeBefore;
	}

	public String getOrderChangeAfter() {
		return this.orderChangeAfter;
	}

	public void setOrderChangeAfter(String orderChangeAfter) {
		this.orderChangeAfter = orderChangeAfter;
	}

	public String getChangeType() {
		return this.changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBelong() {
		return this.belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

}