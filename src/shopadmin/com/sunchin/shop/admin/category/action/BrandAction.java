package com.sunchin.shop.admin.category.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.category.service.BrandService;
import com.sunchin.shop.admin.pojo.ScBrand;

import framework.action.PageAction;
import framework.bean.PageBean;

public class BrandAction extends PageAction {
	
	@Resource(name="brandService")
	private BrandService brandService; 
	
	private ScBrand brand;
	private String msg;
	
	/**
	 * 查询
	 * @return
	 */
	public String queryBrandList() {
		try {
			PageBean resultData = brandService.queryBrandList(this.getPageBean());
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
	public String delBrand() {
		try {
			brandService.delBrand(brand.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 修改
	 * @return
	 */
	public String updateBrand() {
		try {
			brandService.updateBrand(brand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 新增
	 * @return
	 */
	public String addBrand() {
		try {
			msg = brandService.addBrand(brand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询某个品牌详情
	 * @return
	 */
	public String queryBrandById(){
		try {
			brand = brandService.queryBrandById(brand.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public ScBrand getBrand() {
		return brand;
	}

	public void setBrand(ScBrand brand) {
		this.brand = brand;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
