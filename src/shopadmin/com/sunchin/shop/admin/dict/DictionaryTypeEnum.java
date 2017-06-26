package com.sunchin.shop.admin.dict;

public enum DictionaryTypeEnum {
	
	ORDER_PAY_MODE("ORDER_PAY_MODE"), //订单支付方式
	
	ORDER_INVOICE("ORDER_INVOICE"), //订单是否需要发票
	
	ORDER_SPLIT("ORDER_SPLIT"), //订单是否拆分
	
	ORDER_STS("ORDER_STS"), //订单状态（待付款、待收货、待评价）
	
	ORDER_DELIVERY_MODE("ORDER_DELIVERY_MODE"), //订单送货方式（自提、平邮、快递等）

	HOT_QUESTION("HOT_QUESTION"), //是否热点问题
	
	FAQ_TYPE("FAQ_TYPE"), //常见问题种类（包括商品和问题分类）
	
	FAQ_CATEGORY("FAQ_CATEGORY"), //常见问题的类型（包括问题、回答、追问）

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
	
	IDENTITY_STATUS("IDENTITY_STATUS"),//身份证状态
	
	UNIT("UNIT"), //钱包单位  元   分
	
	TRANSPORT_MODE("TRANSPORT_MODE"), //运送方式
	
	UNITS("UNITS"),//运费单位 件 kg 
	
	ISUSE("ISUSE"), //是否默认
	
	ARTICLE_TYPE("ARTICLE_TYPE"),//文章分类
	
	USER_GRADE("USER_GRADE"),//会员等级
	
	SCOPE("SCOPE");
	
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