package com.sunchin.shop.admin.dict;

public enum FreightTypeEnum {

	BIZ("1", "卖家承担运费"),
	
	BUYER("2", "买家承担运费");

	private String code;

	private String desc;

	private FreightTypeEnum(String code, String desc) {
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