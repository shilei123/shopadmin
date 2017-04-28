package com.sunchin.shop.admin.dict;

public enum IsuseEnum {

	VALID("1", "有效"),

	INVALID("0", "无效");

	private String code;

	private String desc;

	private IsuseEnum(String code, String desc) {
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