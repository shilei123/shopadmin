package com.sunchin.shop.admin.pojo;

import java.util.Date;


/**
 * ScBill entity. @author MyEclipse Persistence Tools
 */

public class ScBill  implements java.io.Serializable {

     private String id;
     private String kind;
     private String billType;
     private String code;
     private String name;
     private String orderCode;
     private String reason;
     private String content;
     private String result;
     private Date createTime;
     private String createUserId;
     private Date optionTime;
     private String optionAdminid;
     private String billStatus;
     private String flag;
     private String belong;

    public ScBill() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public Date getOptionTime() {
		return optionTime;
	}

	public void setOptionTime(Date optionTime) {
		this.optionTime = optionTime;
	}

	public String getOptionAdminid() {
		return optionAdminid;
	}

	public void setOptionAdminid(String optionAdminid) {
		this.optionAdminid = optionAdminid;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
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