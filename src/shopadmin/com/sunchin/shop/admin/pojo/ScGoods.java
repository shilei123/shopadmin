package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScGoods implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1215697287918126549L;
	private String id;
	private String cateId;
	private String brandId;
	private String title;
	private String subTitle;
	private String goodsName;
	private String detail;
	private String params;
	private Date stopTime;
	private String goodsCode;
	private String isuse;
	private String emptyStore;
	private String putaway;
	private String virtual;
	private Double purchasePrice;
	private Double marketPrice;
	private Double salePrice;
	private Double promotionPrice;
	private String goodsNo;
	private String freightType;
	private String freightId;
	private String publishType;
	private Date publishTime;
	private String goodsSts;
	private String auditSts;
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

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getBrandId() {
		return this.brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return this.subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Date getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String getGoodsCode() {
		return this.goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getIsuse() {
		return this.isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public String getEmptyStore() {
		return this.emptyStore;
	}

	public void setEmptyStore(String emptyStore) {
		this.emptyStore = emptyStore;
	}

	public String getPutaway() {
		return this.putaway;
	}

	public void setPutaway(String putaway) {
		this.putaway = putaway;
	}

	public String getVirtual() {
		return this.virtual;
	}

	public void setVirtual(String virtual) {
		this.virtual = virtual;
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

	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
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

	public String getFreightType() {
		return freightType;
	}

	public void setFreightType(String freightType) {
		this.freightType = freightType;
	}

	public String getFreightId() {
		return freightId;
	}

	public void setFreightId(String freightId) {
		this.freightId = freightId;
	}

	public String getPublishType() {
		return publishType;
	}

	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getGoodsSts() {
		return goodsSts;
	}

	public void setGoodsSts(String goodsSts) {
		this.goodsSts = goodsSts;
	}

	public String getAuditSts() {
		return auditSts;
	}

	public void setAuditSts(String auditSts) {
		this.auditSts = auditSts;
	}

}