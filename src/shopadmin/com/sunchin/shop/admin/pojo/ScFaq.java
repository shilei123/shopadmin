package com.sunchin.shop.admin.pojo;

import java.util.Date;

@SuppressWarnings("serial")
public class ScFaq implements java.io.Serializable {

	private String id;
	private String faqTitle;
	private String faqContent;
	private String faqTypeId;
	private String type;
	private String parentId;
	private String hotQuestion;
	private int faqOrder;
	private Date createTime;
	private String createPeople;
	private String flag;
	private String belong;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFaqTitle() {
		return this.faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getFaqContent() {
		return this.faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public String getFaqTypeId() {
		return this.faqTypeId;
	}

	public void setFaqTypeId(String faqTypeId) {
		this.faqTypeId = faqTypeId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getHotQuestion() {
		return this.hotQuestion;
	}

	public void setHotQuestion(String hotQuestion) {
		this.hotQuestion = hotQuestion;
	}

	public int getFaqOrder() {
		return this.faqOrder;
	}

	public void setFaqOrder(int faqOrder) {
		this.faqOrder = faqOrder;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatePeople() {
		return this.createPeople;
	}

	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBelong() {
		return this.belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

}