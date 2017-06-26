package com.sunchin.shop.admin.dict;

public enum GoodsStsEnum {

	IN_STORE("1", "入库"),
	
	TIMER_PUTAWAY("2", "定时上架"),
	
	PUTAWAY("3", "上架"),
	
	OUT("4", "下架");

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