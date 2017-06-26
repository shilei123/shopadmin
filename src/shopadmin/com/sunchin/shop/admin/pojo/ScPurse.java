package com.sunchin.shop.admin.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * ScPurse entity. @author MyEclipse Persistence Tools
 */

public class ScPurse  implements java.io.Serializable {

     private String id;
     private String tradeType;
     private Double tradeAmount;
     private String tradeSn;
     private String tradeState;
     private String optionType;
     private String orderId;
     private String userId;
     private Double userAmount;
     private String purseType;
     private String payAccount;
     private String payOpenBank;
     private Date createTime;
     private Date optionTime;
     private String optionAdminid;
     private String optionAdminname;
     private String optionRemark;
     private String belong;
     

    public ScPurse() {
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}


	public Double getTradeAmount() {
		return tradeAmount;
	}


	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}


	public String getTradeSn() {
		return tradeSn;
	}


	public void setTradeSn(String tradeSn) {
		this.tradeSn = tradeSn;
	}


	public String getTradeState() {
		return tradeState;
	}


	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}


	public String getOptionType() {
		return optionType;
	}


	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Double getUserAmount() {
		return userAmount;
	}


	public void setUserAmount(Double userAmount) {
		this.userAmount = userAmount;
	}


	public String getPurseType() {
		return purseType;
	}


	public void setPurseType(String purseType) {
		this.purseType = purseType;
	}


	public String getPayAccount() {
		return payAccount;
	}


	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}


	public String getPayOpenBank() {
		return payOpenBank;
	}


	public void setPayOpenBank(String payOpenBank) {
		this.payOpenBank = payOpenBank;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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


	public String getOptionAdminname() {
		return optionAdminname;
	}


	public void setOptionAdminname(String optionAdminname) {
		this.optionAdminname = optionAdminname;
	}


	public String getOptionRemark() {
		return optionRemark;
	}


	public void setOptionRemark(String optionRemark) {
		this.optionRemark = optionRemark;
	}


	public String getBelong() {
		return belong;
	}


	public void setBelong(String belong) {
		this.belong = belong;
	}

	
}