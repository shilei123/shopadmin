package com.sunchin.shop.admin.dict;

public enum OrderIssplitEnum {

	ORDER_ISSPLIT_Y("1", "是"),
	
	ORDER_ISSPLIT_N("0", "否");

	private String code;

	private String name;

	private OrderIssplitEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String desc) {
		this.name = desc;
	}

}