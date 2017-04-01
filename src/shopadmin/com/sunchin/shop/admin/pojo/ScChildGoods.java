package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScChildGoods implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4714603831806798167L;
	private String id;
	private String childCode;
	private String goodsId;
	private String childName;
	private String remark;
	private Double purchasePrice;
	private Double marketPrice;
	private Double salePrice;
	private Double promotionPrice;
	private String childNo;
	private Date createTime;
	private String createUserId;
	private String flag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChildCode() {
		return this.childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getChildName() {
		return this.childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getPromotionPrice() {
		return this.promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getChildNo() {
		return this.childNo;
	}

	public void setChildNo(String childNo) {
		this.childNo = childNo;
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

}