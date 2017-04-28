package com.sunchin.shop.admin.pojo;

import java.util.Date;


/**
 * ScRepertoryFlowing entity. @author MyEclipse Persistence Tools
 */

public class ScRepertoryFlowing  implements java.io.Serializable {

     private String id;
     private String goodsId;
     private String childGoodsId;
     private String aboutType;
     private String aboutId;
     private String repertoryType;
     private Integer repertoryNum;
     private Date createTime;
     private String createUserId;
     private String flag;
     private String belong;

    public ScRepertoryFlowing() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getChildGoodsId() {
		return childGoodsId;
	}

	public void setChildGoodsId(String childGoodsId) {
		this.childGoodsId = childGoodsId;
	}

	public String getAboutType() {
		return aboutType;
	}

	public void setAboutType(String aboutType) {
		this.aboutType = aboutType;
	}

	public String getAboutId() {
		return aboutId;
	}

	public void setAboutId(String aboutId) {
		this.aboutId = aboutId;
	}

	public String getRepertoryType() {
		return repertoryType;
	}

	public void setRepertoryType(String repertoryType) {
		this.repertoryType = repertoryType;
	}

	public Integer getRepertoryNum() {
		return repertoryNum;
	}

	public void setRepertoryNum(Integer repertoryNum) {
		this.repertoryNum = repertoryNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}
    
}