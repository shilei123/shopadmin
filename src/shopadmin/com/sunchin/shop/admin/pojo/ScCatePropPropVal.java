package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScCatePropPropVal implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String cateId;
	private String proppropvalId;
	private String orders;
	private String flag;
	private Date createTime;
	private String createPeople;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateId() {
		return this.cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getProppropvalId() {
		return this.proppropvalId;
	}

	public void setProppropvalId(String proppropvalId) {
		this.proppropvalId = proppropvalId;
	}

	public String getOrders() {
		return this.orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatePeople() {
		return this.createPeople;
	}

	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

}