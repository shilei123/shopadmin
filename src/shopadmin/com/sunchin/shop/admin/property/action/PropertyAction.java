package com.sunchin.shop.admin.property.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.pojo.ScProperty;
import com.sunchin.shop.admin.property.service.PropertyService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class PropertyAction extends PageAction {
	
	@Resource(name="propertyService")
	private PropertyService propertyService; 
	
	private ScProperty property;
	
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = propertyService.queryPropertyList(this.getPageBean());
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
			propertyService.delProperty(property.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 修改
	 * @return
	 */
	public String updateProperty() {
		try {
			propertyService.updateProperty(property);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 新增
	 * @return
	 */
	public String addProperty() {
		try {
			propertyService.addProperty(property);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 修改的查询
	 * @return
	 */
	public String queryPropertyById() {
		try {
			property = propertyService.queryProperty(property.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public ScProperty getProperty() {
		return property;
	}

	public void setProperty(ScProperty property) {
<<<<<<< HEAD
		this.property = property;
=======
>>>>>>> refs/remotes/origin/yangchaowen
	}

}
