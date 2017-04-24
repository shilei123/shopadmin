package com.sunchin.shop.admin.dict;

public enum BillStatusEnum {

	WAIT_STATUS("1", "待审核"),
	
	PASS_STATUS("2","通过"),
	
	NOPASS_STATUS("3","不通过");
	
	

	private String code;

	private String desc;

	private BillStatusEnum(String code, String desc) {
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