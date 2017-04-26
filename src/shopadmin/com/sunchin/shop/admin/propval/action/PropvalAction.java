package com.sunchin.shop.admin.propval.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.pojo.ScPropval;
import com.sunchin.shop.admin.propval.service.PropvalService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class PropvalAction extends PageAction {
	
	@Resource(name="propvalService")
	private PropvalService propvalService; 
	
	private ScPropval propValue;
	private String msg;
	
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = propvalService.queryPropvalList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		try {
			propvalService.delPropval(propValue.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 修改
	 * @return
	 */
	public String updatePropValue() {
		try {
			propvalService.updatePropval(propValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 新增
	 * @return
	 */
	public String addPropValue() {
		try {
			msg = propvalService.addPropval(propValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 修改的查询
	 * @return
	 */
	public String queryPropValueById() {
		try {
			propValue = propvalService.queryPropval(propValue.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public ScPropval getPropValue() {
		return propValue;
	}

	public void setPropValue(ScPropval propValue) {
		this.propValue = propValue;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
