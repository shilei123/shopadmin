package com.sunchin.shop.admin.dict;

public enum OrderStsEnum {
	UNPAY("1", "待付款"),
	
	UNCONFIRM("2", "待确认"),
	
	AAPLY_REFUND("3", "申请退款"),
	
	UNDELIVERY("4", "待发货"),
	
	UNRECEIPT("5", "待收货"),
	
	FINISH("6", "已完成"),
	
	CANCEL("7", "已取消"),
	
	APPLY_RETURN_GOODS("8", "申请退货"),
	
	APPLY_EXCHANGE_GOODS("9", "申请换货");

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