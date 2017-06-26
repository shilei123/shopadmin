package com.sunchin.shop.admin.pojo;

import java.util.Date;

@SuppressWarnings("serial")
public class ScOrder implements java.io.Serializable {

	private String id;
	private String orderCode;
	private Integer num;
	private String deliveryMode;
	private String orderStatus;
	private String issplit;
	private Date splitTime;
	private String invoice;
	private String invoiceRecordId;
	private String deliveryRecordId;
	private String remark;
	private String payMode;
	private Double commisionCharge;
	private Double totalPrice;
	private Double orderPrice;
	private Double actualPrice;
	private String parentOrderId;
	private Date createTime;
	private String createUserId;
	private String userId;
	private String flag;
	private String belong;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDeliveryMode() {
		return this.deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getIssplit() {
		return this.issplit;
	}

	public void setIssplit(String issplit) {
		this.issplit = issplit;
	}

	public Date getSplitTime() {
		return this.splitTime;
	}

	public void setSplitTime(Date splitTime) {
		this.splitTime = splitTime;
	}

	public String getInvoice() {
		return this.invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getInvoiceRecordId() {
		return this.invoiceRecordId;
	}

	public void setInvoiceRecordId(String invoiceRecordId) {
		this.invoiceRecordId = invoiceRecordId;
	}

	public String getDeliveryRecordId() {
		return deliveryRecordId;
	}

	public void setDeliveryRecordId(String deliveryRecordId) {
		this.deliveryRecordId = deliveryRecordId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPayMode() {
		return this.payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Double getCommisionCharge() {
		return commisionCharge;
	}

	public void setCommisionCharge(Double commisionCharge) {
		this.commisionCharge = commisionCharge;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getParentOrderId() {
		return this.parentOrderId;
	}

	public void setParentOrderId(String parentOrderId) {
		this.parentOrderId = parentOrderId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}