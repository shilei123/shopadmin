package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScCateProp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String propId;
	private String cateId;
	private Integer order;
	private String flag;
	private Date createTime;
	private String createUserId;

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

	public String getCateId() {
		return this.cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

}