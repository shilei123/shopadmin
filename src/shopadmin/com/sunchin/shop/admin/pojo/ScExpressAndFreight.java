package com.sunchin.shop.admin.pojo;

import java.util.Date;


/**
 * ScExpressAndFreight entity. @author MyEclipse Persistence Tools
 */

public class ScExpressAndFreight  implements java.io.Serializable {

     private String id;
     private String freightId;
     private String expressId;
     private Date createTime;
     private String createUserId;
     private String flag;

    public ScExpressAndFreight() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFreightId() {
		return freightId;
	}

	public void setFreightId(String freightId) {
		this.freightId = freightId;
	}

	public String getExpressId() {
		return expressId;
	}

	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}