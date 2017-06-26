package com.sunchin.shop.admin.dict;

public enum PutawayEnum {

	YES("0", "是"),

	NO("1", "否");

	private String code;

	private String desc;

	private PutawayEnum(String code, String desc) {
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