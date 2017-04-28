package com.sunchin.shop.admin.dict;

public enum DeleveryTypeEnum {

	CUSTOMER("0", "售后"),
	
	ORDER("1","订单");
	
	private String code;

	private String desc;

	private DeleveryTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}