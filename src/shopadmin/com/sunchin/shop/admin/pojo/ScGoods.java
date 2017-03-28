package com.sunchin.shop.admin.pojo;

import java.util.Date;

@SuppressWarnings("serial")
public class ScGoods implements java.io.Serializable {

	private String id;
	private String goodsName;
	private String goodsBrandId;
	private String goodsDetail;
	private String goodsIsuse;
	private Date stopTime;
	private String goodsFullname;
	private String goodsCode;
	private Date createTime;
	private String createPeople;
	private String noStockSale;
	private String isshelves;
	private String belong;
	private String isVirtualGoods;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsBrandId() {
		return this.goodsBrandId;
	}

	public void setGoodsBrandId(String goodsBrandId) {
		this.goodsBrandId = goodsBrandId;
	}

	public String getGoodsDetail() {
		return this.goodsDetail;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}

	public String getGoodsIsuse() {
		return this.goodsIsuse;
	}

	public void setGoodsIsuse(String goodsIsuse) {
		this.goodsIsuse = goodsIsuse;
	}

	public Date getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String getGoodsFullname() {
		return this.goodsFullname;
	}

	public void setGoodsFullname(String goodsFullname) {
		this.goodsFullname = goodsFullname;
	}

	public String getGoodsCode() {
		return this.goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
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

	public String getNoStockSale() {
		return this.noStockSale;
	}

	public void setNoStockSale(String noStockSale) {
		this.noStockSale = noStockSale;
	}

	public String getIsshelves() {
		return this.isshelves;
	}

	public void setIsshelves(String isshelves) {
		this.isshelves = isshelves;
	}

	public String getBelong() {
		return this.belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getIsVirtualGoods() {
		return this.isVirtualGoods;
	}

	public void setIsVirtualGoods(String isVirtualGoods) {
		this.isVirtualGoods = isVirtualGoods;
	}

}