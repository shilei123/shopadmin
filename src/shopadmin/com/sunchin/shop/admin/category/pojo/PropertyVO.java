package com.sunchin.shop.admin.category.pojo;

import java.util.Date;
import java.util.Map;

import framework.util.CommonUtils;

/**
 * @author aobingcheng
 * 2017年3月14日
 * 属性的VO，在类别属性配置时使用
 */
public class PropertyVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String propCode;
	private String propName;
	private String propOrder;
	private String flag;
	private Date createTime;
	private String createPeople;
	private boolean check;

	@SuppressWarnings("rawtypes")
	public PropertyVO(Map map) {
		this.id = CommonUtils.getString(map.get("id"));
		this.propCode = CommonUtils.getString(map.get("prop_code"));
		this.propName = CommonUtils.getString(map.get("propName"));
		this.propOrder = CommonUtils.getString(map.get("propOrder"));
		this.flag = CommonUtils.getString(map.get("flag"));
		this.createTime = CommonUtils.getDate(map.get("createTime"));
		this.createPeople = CommonUtils.getString(map.get("createPeople"));
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPropCode() {
		return this.propCode;
	}

	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}

	public String getPropName() {
		return this.propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropOrder() {
		return this.propOrder;
	}

	public void setPropOrder(String propOrder) {
		this.propOrder = propOrder;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatePeople() {
		return this.createPeople;
	}

	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}