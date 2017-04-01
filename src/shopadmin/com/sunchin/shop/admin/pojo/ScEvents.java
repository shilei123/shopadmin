package com.sunchin.shop.admin.pojo;

import java.util.Date;


/**
 * ScEvents entity. @author MyEclipse Persistence Tools
 */

public class ScEvents  implements java.io.Serializable {

     private String id;
     private String name;
     private String isuse;
     private String memo;
     private Date startTime;
     private Date endTime;
     private Date createTime;
     private Date updateTime;
     private String flag;
     private String belong;

    public ScEvents() {
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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