package com.sunchin.shop.admin.dict;

public enum PublishTypeEnum {

	IN_STORE("1", "放入仓库"),
	
	PUBLISH("2", "立即发布"),
	
	DELAY("3", "定时发布");

	private String code;

	private String desc;

	private PublishTypeEnum(String code, String desc) {
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