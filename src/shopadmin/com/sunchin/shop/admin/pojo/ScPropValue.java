package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScPropValue implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String valCode;
	private String valName;
	private String valOrder;
	private String flag;
	private Date createTime;
	private String createPeople;
	private Date updateTime;
	private String updatePeople;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValCode() {
		return this.valCode;
	}

	public void setValCode(String valCode) {
		this.valCode = valCode;
	}

	public String getValName() {
		return this.valName;
	}

	public void setValName(String valName) {
		this.valName = valName;
	}

	public String getValOrder() {
		return this.valOrder;
	}

	public void setValOrder(String valOrder) {
		this.valOrder = valOrder;
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

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatePeople() {
		return this.updatePeople;
	}

	public void setUpdatePeople(String updatePeople) {
		this.updatePeople = updatePeople;
	}

}