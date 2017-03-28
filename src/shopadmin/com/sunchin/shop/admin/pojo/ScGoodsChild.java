package com.sunchin.shop.admin.pojo;

@SuppressWarnings("serial")
public class ScGoodsChild implements java.io.Serializable {

	private String id;
	private String goodsChildCode;
	private String goodsId;
	private String goodsChildName;
	private String goodsChildMemo;
	private Double purchasePrice;
	private Double marketPrice;
	private Double salePrice;
	private Double promotionPrice;
	private String goodsNo;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsChildCode() {
		return this.goodsChildCode;
	}

	public void setGoodsChildCode(String goodsChildCode) {
		this.goodsChildCode = goodsChildCode;
	}

	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsChildName() {
		return this.goodsChildName;
	}

	public void setGoodsChildName(String goodsChildName) {
		this.goodsChildName = goodsChildName;
	}

	public String getGoodsChildMemo() {
		return this.goodsChildMemo;
	}

	public void setGoodsChildMemo(String goodsChildMemo) {
		this.goodsChildMemo = goodsChildMemo;
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

}