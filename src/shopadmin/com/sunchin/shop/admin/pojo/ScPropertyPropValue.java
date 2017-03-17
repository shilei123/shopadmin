package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScPropertyPropValue implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String propId;
	private String valId;
	private String flag;
	private String orders;
	private Date createTime;
	private String createPeople;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPropId() {
		return this.propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public String getValId() {
		return this.valId;
	}

	public void setValId(String valId) {
		this.valId = valId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrders() {
		return this.orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
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