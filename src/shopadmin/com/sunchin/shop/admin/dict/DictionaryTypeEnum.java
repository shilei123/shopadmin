package com.sunchin.shop.admin.dict;

public enum DictionaryTypeEnum {

	TRADE_TYPE("TRADE_TYPE"), //交易类型

	PURSE_TYPE("PURSE_TYPE"), //钱包类型

	TRADE_STATE("TRADE_STATE"), //交易状态
	
	OPTION_TYPE("OPTION_TYPE"), //操作类型
	
	ADVERTISE_LINKKIND("ADVERTISE_LINKKIND"),//广告类型
	
	ADVERTISE_ISUSE("ADVERTISE_ISUSE"), //是否启用
	
	COUPON_TYPE("COUPON_TYPE"), //优惠券类型
	
	USER_STATUS("USER_STATUS"), //账户状态
	
	COUPON_STATUS("COUPON_STATUS"),//优惠券状态
	
	USER_SEX("USER_SEX"),//性别
	
	COUPON_STATUSS("COUPON_STATUSS"),//优惠券是否有效
	
	IDENTITY_STATUS("IDENTITY_STATUS");//身份证状态
	
	
	
	private String type;

	private DictionaryTypeEnum(String type) {
		this.type = type;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}