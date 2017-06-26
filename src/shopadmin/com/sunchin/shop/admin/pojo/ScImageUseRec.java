package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScImageUseRec implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4064121295320705176L;
	private String id;
	private String busiType;
	private String busiId;
	private String imgId;
	private Date createTime;
	private String createUserId;
	private String flag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusiType() {
		return this.busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getBusiId() {
		return this.busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getImgId() {
		return this.imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
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

}