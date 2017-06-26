package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScCategory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String cateName;
	private String remark;
	private Integer order;
	private String level;
	private String logo;
	private String url;
	private String isuse;
	private String parentId;
	private String cateCode;
	private Date createTime;
	private Date updateTime;
	private String flag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return this.cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsuse() {
		return this.isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCateCode() {
		return this.cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}