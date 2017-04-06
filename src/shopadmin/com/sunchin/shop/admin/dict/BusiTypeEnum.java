package com.sunchin.shop.admin.dict;

public enum BusiTypeEnum {

	GOODS("1", "商品录入");

	private String code;

	private String desc;

	private BusiTypeEnum(String code, String desc) {
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