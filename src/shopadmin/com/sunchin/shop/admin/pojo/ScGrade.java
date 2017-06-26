package com.sunchin.shop.admin.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * ScGrade entity. @author MyEclipse Persistence Tools
 */

public class ScGrade  implements java.io.Serializable {

     private String id;
     private String userName;
     private String belong;

    public ScGrade() {
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


	public String getBelong() {
		return belong;
	}


	public void setBelong(String belong) {
		this.belong = belong;
	}

	
	

}