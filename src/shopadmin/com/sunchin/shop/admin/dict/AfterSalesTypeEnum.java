package com.sunchin.shop.admin.dict;

public enum AfterSalesTypeEnum {

	CHANGE_GOODS("0", "换货"),
	
	REPAIR_GOODS("1","维修");
	
	private String code;

	private String desc;

	private AfterSalesTypeEnum(String code, String desc) {
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