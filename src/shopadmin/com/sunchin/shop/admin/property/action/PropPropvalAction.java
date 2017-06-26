package com.sunchin.shop.admin.property.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.property.service.PropPropvalService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class PropPropvalAction extends PageAction{
	
	@Resource(name="propPropvalService")
	private PropPropvalService propPropvalService;
	
	private List<Map<String, Object>> listCheck;
	
	/**
	 * 保存属性属性值关系
	 * @return
	 */
	public String savePropPropval(){
		try {
			propPropvalService.savePropPropval(this.getPageBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询属性值（分页）
	 * @return
	 */
	public String queryPropPropval(){
		try {
			PageBean resultData = propPropvalService.queryPropPropval(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询属性对应的属性值
	 * @return
	 */
	public String queryPropPropvalCheck(){
		try {
			listCheck = propPropvalService.queryPropPropvalCheck(this.getPageBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public List<Map<String, Object>> getListCheck() {
		return listCheck;
	}

	public void setListCheck(List<Map<String, Object>> listCheck) {
		this.listCheck = listCheck;
	}

}
