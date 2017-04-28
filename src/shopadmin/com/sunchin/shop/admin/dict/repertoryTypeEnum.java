package com.sunchin.shop.admin.dict;

public enum repertoryTypeEnum {

	AVAILABLE_NUM("1", "可用库存"),
	
	SALES_NUM("2","销售库存"),
	
	FREEZE_NUM("3","冻结库存"),
	
	BAD_NUM("4","报损数量"),
	
	BILL("1","单据"),
	
	DELIVERY("2","发货单");
	
	private String code;

	private String desc;

	private repertoryTypeEnum(String code, String desc) {
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