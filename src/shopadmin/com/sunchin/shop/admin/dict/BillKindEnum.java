package com.sunchin.shop.admin.dict;

public enum BillKindEnum {

	APPLY("0", "申请"),
	
	CONFIRM("1","确认");

	private String code;

	private String desc;

	private BillKindEnum(String code, String desc) {
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