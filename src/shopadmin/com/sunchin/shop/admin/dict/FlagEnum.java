package com.sunchin.shop.admin.dict;

public enum FlagEnum {

	ACT("0", "有效"),

	HIS("1", "无效");

	private String code;

	private String desc;

	private FlagEnum(String code, String desc) {
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