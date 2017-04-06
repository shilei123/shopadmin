package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScGoodsImage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8020118859875966244L;
	private String id;
	private String goodsId;
	private String childGoodsId;
	private String imageId;
	private Date createTime;
	private String createUserId;
	private String flag;

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

	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
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