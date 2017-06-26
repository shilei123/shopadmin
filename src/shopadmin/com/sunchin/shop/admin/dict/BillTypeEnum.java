package com.sunchin.shop.admin.dict;

public enum BillTypeEnum {

	RETURNGOODS("1", "退货"),
	
	CHANGEGOODS("2","换货"),
	
	REFUNDGOODS("3","退款"),
	
	REPAIRGOODS("4","维修");
	

	private String code;

	private String desc;

	private BillTypeEnum(String code, String desc) {
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