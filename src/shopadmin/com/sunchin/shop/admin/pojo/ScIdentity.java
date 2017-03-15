package com.sunchin.shop.admin.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * ScCoupon entity. @author MyEclipse Persistence Tools
 */

public class ScIdentity  implements java.io.Serializable {

     private String id;
     private String userId;
     private String userName;
     private String identityCard;
     private Date identityCardValidity;
     private String identityIssuing;
     private String identityStatus;

    public ScIdentity() {
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

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public Date getIdentityCardValidity() {
		return identityCardValidity;
	}

	public void setIdentityCardValidity(Date identityCardValidity) {
		this.identityCardValidity = identityCardValidity;
	}

	public String getIdentityIssuing() {
		return identityIssuing;
	}

	public void setIdentityIssuing(String identityIssuing) {
		this.identityIssuing = identityIssuing;
	}

	public String getIdentityStatus() {
		return identityStatus;
	}

	public void setIdentityStatus(String identityStatus) {
		this.identityStatus = identityStatus;
	}

}