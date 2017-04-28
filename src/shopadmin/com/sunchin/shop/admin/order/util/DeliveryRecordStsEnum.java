package com.sunchin.shop.admin.order.util;

public enum DeliveryRecordStsEnum {
	UNDELIVERY("0", "待发货"),
	
	ALDELIVERY("1", "已发货");
	
	private String code;

	private String name;

	private DeliveryRecordStsEnum(String code, String name) {
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