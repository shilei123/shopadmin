package com.sunchin.shop.admin.pojo;

import java.util.Date;


/**
 * ScBillDetail entity. @author MyEclipse Persistence Tools
 */

public class ScBillDetail  implements java.io.Serializable {

     private String id;
     private String billCode;
     private String goodsId;
     private String goodsDetailId;
     private Double numbs;
     private Double purchasePrice;
     private Double donePrice;
     private String exGoodsId;
     private String exGoodsDetailId;
     private Double exPrice;
     private Double order;
     private Date	createTime;
     private String flag;
     private String belong;

    public ScBillDetail() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsDetailId() {
		return goodsDetailId;
	}

	public void setGoodsDetailId(String goodsDetailId) {
		this.goodsDetailId = goodsDetailId;
	}

	public Double getNumbs() {
		return numbs;
	}

	public void setNumbs(Double numbs) {
		this.numbs = numbs;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getDonePrice() {
		return donePrice;
	}

	public void setDonePrice(Double donePrice) {
		this.donePrice = donePrice;
	}

	public String getExGoodsId() {
		return exGoodsId;
	}

	public void setExGoodsId(String exGoodsId) {
		this.exGoodsId = exGoodsId;
	}

	public String getExGoodsDetailId() {
		return exGoodsDetailId;
	}

	public void setExGoodsDetailId(String exGoodsDetailId) {
		this.exGoodsDetailId = exGoodsDetailId;
	}

	public Double getExPrice() {
		return exPrice;
	}

	public void setExPrice(Double exPrice) {
		this.exPrice = exPrice;
	}

	public Double getOrder() {
		return order;
	}

	public void setOrder(Double order) {
		this.order = order;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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