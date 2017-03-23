package com.sunchin.shop.admin.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * ScBcuser entity. @author MyEclipse Persistence Tools
 */

public class ScBcuser  implements java.io.Serializable {

     private String id;
     private String userId;
     private String trueName;
     private String userPhone;
     private String userMail;
     private String userSex;


    public ScBcuser() {
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getTrueName() {
		return trueName;
	}


	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserMail() {
		return userMail;
	}


	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}


	public String getUserSex() {
		return userSex;
	}


	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}



}