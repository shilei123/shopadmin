package com.sunchin.shop.admin.order.util;

public enum OrderRecordChangeTypeEnum {
	
	ORDER_STATUS_CHANGE("1", "订单状态变动"),
	
	ORDER_PRICE_CHANGE("2", "订单价格变动"),
	
	OTHER_CHANGE("3", "其他变动");
	
	private String code;

	private String name;

	private OrderRecordChangeTypeEnum(String code, String name) {
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