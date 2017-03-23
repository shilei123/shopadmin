package com.sunchin.shop.admin.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * ScCoupon entity. @author MyEclipse Persistence Tools
 */

public class ScUser  implements java.io.Serializable {

     private String id;
     private String userName;
     private String nickName;
     private String loginPwd;
     private String payPwd;
     private String userGrade;
     private String tixianStatus;
     private Date createTime;
     private Date optionTime;
     private String userStatus;


    public ScUser() {
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getLoginPwd() {
		return loginPwd;
	}


	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}


	public String getPayPwd() {
		return payPwd;
	}


	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}


	public String getUserGrade() {
		return userGrade;
	}


	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}


	public String getTixianStatus() {
		return tixianStatus;
	}


	public void setTixianStatus(String tixianStatus) {
		this.tixianStatus = tixianStatus;
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


	public String getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}




}