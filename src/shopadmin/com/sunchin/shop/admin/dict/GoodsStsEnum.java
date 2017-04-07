package com.sunchin.shop.admin.dict;

public enum GoodsStsEnum {

	NO_ON("1", "未上架"),
	
	ON("2", "已上架"),
	
	OUT("3", "已下架");

	private String code;

	private String desc;

	private GoodsStsEnum(String code, String desc) {
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