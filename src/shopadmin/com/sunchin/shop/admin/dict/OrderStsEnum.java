package com.sunchin.shop.admin.dict;

public enum OrderStsEnum {
	
	SUBMIT("1", "已提交"),
	
	CONFIRM("2", "已确认"),
	
	UNRECEIPT("3", "待收货"),
	
	FINISH("4", "已完成"),
	
	CANCEL("5", "已取消"),
	
	UNPAY("6", "待付款"),
	
	UNDELIVERY("7", "待发货");

	private String code;

	private String name;

	private OrderStsEnum(String code, String name) {
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