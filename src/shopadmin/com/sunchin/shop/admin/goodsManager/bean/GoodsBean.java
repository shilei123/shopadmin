package com.sunchin.shop.admin.goodsManager.bean;

public class GoodsBean {
	private String id;
	private String title;
	private String subTitle;
	private String brandId;
	private String detail;
	private String params;
	private String goodsCode;
	private String isuse;
	private String emptyStore;
	private String putaway;
	private String virtual;
	private String purchasePrice;
	private String marketPrice;
	private String salePrice;
	private String promotionPrice;
	private String availableNum; //可用库存
	private String goodsNo;
	private String freightType;
	private String freightId;
	private String publishType;
	private String publishTime; 
	
	//子商品信息
	private String childGoods; 
	private String childAvailableNum;//可用库存
	
	//图片信息
	private String imgs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getChildAvailableNum() {
		return childAvailableNum;
	}
	public void setChildAvailableNum(String childAvailableNum) {
		this.childAvailableNum = childAvailableNum;
	}
	public String getChildGoods() {
		return childGoods;
	}
	public void setChildGoods(String childGoods) {
		this.childGoods = childGoods;
	}
	public String getAvailableNum() {
		return availableNum;
	}
	public void setAvailableNum(String availableNum) {
		this.availableNum = availableNum;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getIsuse() {
		return isuse;
	}
	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}
	public String getEmptyStore() {
		return emptyStore;
	}
	public void setEmptyStore(String emptyStore) {
		this.emptyStore = emptyStore;
	}
	public String getPutaway() {
		return putaway;
	}
	public void setPutaway(String putaway) {
		this.putaway = putaway;
	}
	public String getVirtual() {
		return virtual;
	}
	public void setVirtual(String virtual) {
		this.virtual = virtual;
	}
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
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
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
}