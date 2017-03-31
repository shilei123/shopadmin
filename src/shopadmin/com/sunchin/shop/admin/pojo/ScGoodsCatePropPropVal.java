package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScGoodsCatePropPropVal implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String goodsId;
	private String goodsChildId;
	private String catePropPropvalId;
	private String type;
	private int orders;
	private String flag;
	private Date createTime;
	private String createPeople;

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

	public String getCatePropPropvalId() {
		return this.catePropPropvalId;
	}

	public void setCatePropPropvalId(String catePropPropvalId) {
		this.catePropPropvalId = catePropPropvalId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOrders() {
		return this.orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getGoodsChildId() {
		return goodsChildId;
	}
	
	public void setGoodsChildId(String goodsChildId) {
		this.goodsChildId = goodsChildId;
	}
}