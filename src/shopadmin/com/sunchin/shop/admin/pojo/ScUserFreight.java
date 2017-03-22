package com.sunchin.shop.admin.pojo;

import java.util.Date;

public class ScUserFreight implements java.io.Serializable {

	private String id;
	private String freightId;
	private String TransportMode;
	private String TransportRange;
	private String TransportId;
	private Double initialInt;
	private Double initialPrice;
	private Double stackInt;
	private Double stackPrice;
	private Date createTime;
	private Date optionTime;
	private String flag;
	private String belong;

	public ScUserFreight() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFreightId() {
		return freightId;
	}

	public void setFreightId(String freightId) {
		this.freightId = freightId;
	}

	public String getTransportMode() {
		return TransportMode;
	}

	public void setTransportMode(String transportMode) {
		TransportMode = transportMode;
	}

	public String getTransportRange() {
		return TransportRange;
	}

	public void setTransportRange(String transportRange) {
		TransportRange = transportRange;
	}

	public String getTransportId() {
		return TransportId;
	}

	public void setTransportId(String transportId) {
		TransportId = transportId;
	}

	public Double getInitialInt() {
		return initialInt;
	}

	public void setInitialInt(Double initialInt) {
		this.initialInt = initialInt;
	}

	public Double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Double getStackInt() {
		return stackInt;
	}

	public void setStackInt(Double stackInt) {
		this.stackInt = stackInt;
	}

	public Double getStackPrice() {
		return stackPrice;
	}

	public void setStackPrice(Double stackPrice) {
		this.stackPrice = stackPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOptionTime() {
		return optionTime;
	}

	public void setOptionTime(Date optionTime) {
		this.optionTime = optionTime;
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