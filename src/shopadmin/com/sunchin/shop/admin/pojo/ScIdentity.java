package com.sunchin.shop.admin.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * ScIdentity entity. @author MyEclipse Persistence Tools
 */

public class ScIdentity  implements java.io.Serializable {

     private String id;
     private String userId;
     private String identityCard;
     private Date identityCardValidity;
     private String identityIssuing;
     private String identityStatus;
     private String identityFrontal;
     private String identityBack;
     private String identityHoldFrontal;
     private Date applicationTime;
     private String applicant;
     private Date authenticationTime;
     private String authenticator;
     private String failureReason;
     private String belong;

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

	public String getIdentityFrontal() {
		return identityFrontal;
	}

	public void setIdentityFrontal(String identityFrontal) {
		this.identityFrontal = identityFrontal;
	}

	public String getIdentityBack() {
		return identityBack;
	}

	public void setIdentityBack(String identityBack) {
		this.identityBack = identityBack;
	}

	public String getIdentityHoldFrontal() {
		return identityHoldFrontal;
	}

	public void setIdentityHoldFrontal(String identityHoldFrontal) {
		this.identityHoldFrontal = identityHoldFrontal;
	}

	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getAuthenticationTime() {
		return authenticationTime;
	}

	public void setAuthenticationTime(Date authenticationTime) {
		this.authenticationTime = authenticationTime;
	}

	public String getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	

}