package com.sunchin.shop.admin.dict;

public enum StatusEnum {

	ACT("0", "有效"),

	HIS("1", "作废"),
	
	ZERO("0","未使用"),
	
	ONE("1","已使用"),
	
	TWO("2","作废");
	

	private String code;

	private String desc;

	private StatusEnum(String code, String desc) {
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