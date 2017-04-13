package com.sunchin.shop.admin.dict;

public enum OrderInvoiceEnum {

	ORDER_INVOICE_Y("1", "是"),
	
	ORDER_INVOICE_N("0", "否");

	private String code;

	private String name;

	private OrderInvoiceEnum(String code, String name) {
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