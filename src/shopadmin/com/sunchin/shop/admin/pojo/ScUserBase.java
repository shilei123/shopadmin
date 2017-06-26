package com.sunchin.shop.admin.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * ScBcuser entity. @author MyEclipse Persistence Tools
 */

public class ScUserBase  implements java.io.Serializable {

     private String id;
     private String userId;
     private String userName;
     private String phone;
     private String mail;
     private String sex;


    public ScUserBase() {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}




}