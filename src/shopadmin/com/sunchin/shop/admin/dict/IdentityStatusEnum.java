package com.sunchin.shop.admin.dict;

public enum IdentityStatusEnum {

	NoAuthentication("0", "未认证"),

	Application("1", "申请中"),
	
	Authentication("2", "已认证"),
	
	AuthenticationFail("3", "认证失败");

	private String code;

	private String desc;

	private IdentityStatusEnum(String code, String desc) {
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