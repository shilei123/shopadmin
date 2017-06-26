package com.sunchin.shop.admin.category.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.category.service.CatePropService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class CatePropAction extends PageAction{
	
	@Resource(name="catePropService")
	private CatePropService catePropService;
	
	private List<Map<String, Object>> listCheck;
	
	/**
	 * 保存类别属性关系
	 * 处理传过来的串串
	 * @return
	 */
	public String savePropCate(){
		try {
			catePropService.savePropCate(this.getPageBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询类别属性关系
	 * @return
	 */
	public String queryPropCate(){
		try {
			PageBean resultData = catePropService.queryPropCate(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询选中的类别属性关系
	 * @return
	 */
	public String queryPropCateCheck(){
		try {
			listCheck = catePropService.queryPropCateCheck(this.getPageBean());
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
