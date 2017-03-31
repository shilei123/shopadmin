package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScDirectoryStructure implements java.io.Serializable {

	private String id;
	private String directoryName;
	private String parentDirectoryId;
	private Double cateOrder;
	private String levels;
	private String directoryPath;
	private String isuse;
	private Date createTime;
	private Date updateTime;
	private String flag;
	private String belong;

	public ScDirectoryStructure() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public String getParentDirectoryId() {
		return parentDirectoryId;
	}

	public void setParentDirectoryId(String parentDirectoryId) {
		this.parentDirectoryId = parentDirectoryId;
	}

	public Double getCateOrder() {
		return cateOrder;
	}

	public void setCateOrder(Double cateOrder) {
		this.cateOrder = cateOrder;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}
	
}