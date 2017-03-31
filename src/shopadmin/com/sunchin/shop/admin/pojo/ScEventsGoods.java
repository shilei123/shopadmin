package com.sunchin.shop.admin.pojo;

import java.util.Date;


/**
 * ScEventsinfo entity. @author MyEclipse Persistence Tools
 */

public class ScEventsGoods  implements java.io.Serializable {

     private String Id;
     private String eventsId;
     private String goodsId;
     private String goodsChildId;
     private Double eventsMoney;
     private String scope;

    public ScEventsGoods() {
    }

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getEventsId() {
		return eventsId;
	}

	public void setEventsId(String eventsId) {
		this.eventsId = eventsId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsChildId() {
		return goodsChildId;
	}

	public void setGoodsChildId(String goodsChildId) {
		this.goodsChildId = goodsChildId;
	}

	public Double getEventsMoney() {
		return eventsMoney;
	}

	public void setEventsMoney(Double eventsMoney) {
		this.eventsMoney = eventsMoney;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}