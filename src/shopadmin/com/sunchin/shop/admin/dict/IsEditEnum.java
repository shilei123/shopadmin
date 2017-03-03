package com.sunchin.shop.admin.dict;

public enum IsEditEnum {

	EDIT("0", "可编辑"),

	NOEDIT("1", "不可编辑");

	private String code;

	private String desc;

	private IsEditEnum(String code, String desc) {
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