package com.sunchin.shop.admin.dict;

public enum AuditStsEnum {

	WAIT("1", "待审核"),

	PASS("2", "通过"),
	
	NO_PASS("3", "不通过");

	private String code;

	private String desc;

	private AuditStsEnum(String code, String desc) {
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