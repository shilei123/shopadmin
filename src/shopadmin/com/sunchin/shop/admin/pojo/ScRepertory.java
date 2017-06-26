package com.sunchin.shop.admin.pojo;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class ScRepertory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8584010509984128699L;
	private String id;
	private String goodsId;
	private String childGoodsId;
	private Integer availableNum;
	private Integer salesNum;
	private Integer freezeNum;
	private Integer badNum;
	private Integer salesCount;
	private Integer purchaseCount;
	private Date createTime;
	private String createUserId;
	private String flag;
	private String belong;
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getChildGoodsId() {
		return this.childGoodsId;
	}

	public void setChildGoodsId(String childGoodsId) {
		this.childGoodsId = childGoodsId;
	}

	public Integer getAvailableNum() {
		return this.availableNum;
	}

	public void setAvailableNum(Integer availableNum) {
		this.availableNum = availableNum;
	}
	
	public Integer getSalesNum() {
		return this.salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getFreezeNum() {
		return this.freezeNum;
	}

	public void setFreezeNum(Integer freezeNum) {
		this.freezeNum = freezeNum;
	}

	public Integer getBadNum() {
		return this.badNum;
	}

	public void setBadNum(Integer badNum) {
		this.badNum = badNum;
	}

	public Integer getSalesCount() {
		return this.salesCount;
	}

	public void setSalesCount(Integer salesCount) {
		this.salesCount = salesCount;
	}

	public Integer getPurchaseCount() {
		return this.purchaseCount;
	}

	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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