package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScComment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String content;
	private Date contentTime;
	private Double score;
	private String ishidden;
	private String parentId;
	private String contentPeople;
	private String flag;

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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getContentTime() {
		return this.contentTime;
	}

	public void setContentTime(Date contentTime) {
		this.contentTime = contentTime;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getIshidden() {
		return this.ishidden;
	}

	public void setIshidden(String ishidden) {
		this.ishidden = ishidden;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getContentPeople() {
		return this.contentPeople;
	}

	public void setContentPeople(String contentPeople) {
		this.contentPeople = contentPeople;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}