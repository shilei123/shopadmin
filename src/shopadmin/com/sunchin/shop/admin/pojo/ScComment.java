package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScComment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String comments;
	private Date createTime;
	private Byte score;
	private String ishidden;
	private String orderid;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Byte getScore() {
		return this.score;
	}

	public void setScore(Byte score) {
		this.score = score;
	}

	public String getIshidden() {
		return this.ishidden;
	}

	public void setIshidden(String ishidden) {
		this.ishidden = ishidden;
	}

	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}