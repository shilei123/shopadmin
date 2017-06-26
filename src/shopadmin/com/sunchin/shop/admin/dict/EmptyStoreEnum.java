package com.sunchin.shop.admin.dict;

public enum EmptyStoreEnum {

	Y("1", "是"),

	N("0", "否");

	private String code;

	private String desc;

	private EmptyStoreEnum(String code, String desc) {
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