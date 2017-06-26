package com.sunchin.shop.admin.pojo;

import java.util.Date;


/**
 * ScBillHistory entity. @author MyEclipse Persistence Tools
 */

public class ScBillHistory  implements java.io.Serializable {

     private String id;
     private String billStatus;
     private String remark;
     private String billId;
     private Date createTime;
     private String optionAdminid;
     private String flag;
     private String belong;

    public ScBillHistory() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOptionAdminid() {
		return optionAdminid;
	}

	public void setOptionAdminid(String optionAdminid) {
		this.optionAdminid = optionAdminid;
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