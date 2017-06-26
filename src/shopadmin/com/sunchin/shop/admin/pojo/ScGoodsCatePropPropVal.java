package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScGoodsCatePropPropVal implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String goodsId;
	private String childGoodsId;
	private String cppvId;
	private String type;
	private int order;
	private String flag;
	private Date createTime;
	private String createUserId;

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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public String getChildGoodsId() {
		return childGoodsId;
	}

	public void setChildGoodsId(String childGoodsId) {
		this.childGoodsId = childGoodsId;
	}

	public String getCppvId() {
		return cppvId;
	}

	public void setCppvId(String cppvId) {
		this.cppvId = cppvId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
}