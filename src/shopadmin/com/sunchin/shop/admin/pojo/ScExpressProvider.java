package com.sunchin.shop.admin.pojo;

import java.util.Date;


/**
 * ScExpressProvider entity. @author MyEclipse Persistence Tools
 */

public class ScExpressProvider  implements java.io.Serializable {

     private String id;
     private String expressType;
     private String code;
     private String forShort;
     private String ename;
     private String fullName;
     private String url;
     private String isuse;
     private Date createTime;
     private Date updateTime;
     private String flag;
     private String belong;

    public ScExpressProvider() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExpressType() {
		return expressType;
	}

	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getForShort() {
		return forShort;
	}

	public void setForShort(String forShort) {
		this.forShort = forShort;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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