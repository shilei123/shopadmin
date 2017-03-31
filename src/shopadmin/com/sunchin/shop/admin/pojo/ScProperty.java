package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScProperty implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String propCode;
	private String propName;
	private int propOrder;
	private String flag;
	private Date createTime;
	private String createPeople;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPropCode() {
		return this.propCode;
	}

	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}

	public String getPropName() {
		return this.propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public int getPropOrder() {
		return this.propOrder;
	}

	public void setPropOrder(int propOrder) {
		this.propOrder = propOrder;
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