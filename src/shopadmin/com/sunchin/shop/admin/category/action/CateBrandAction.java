package com.sunchin.shop.admin.category.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.category.service.CateBrandService;

import framework.action.PageAction;

public class CateBrandAction extends PageAction{
	
	
	@Resource(name="cateBrandService")
	private CateBrandService cateBrandService;
	
	private List<Map<String, Object>> listCheck;
	
	/**
	 * 保存类别品牌关系
	 * 处理传过来的串串
	 * @return
	 */
	public String saveCateBrand(){
		try {
			cateBrandService.saveCateBrand(this.getPageBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询选中的类别品牌关系
	 * @return
	 */
	public String queryCateBrandCheck(){
		try {
			listCheck = cateBrandService.queryCateBrandCheck(this.getPageBean());
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
