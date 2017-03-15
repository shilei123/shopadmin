package com.sunchin.shop.admin.advertise.action;

import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.advertise.service.IAdvertiseService;
import com.sunchin.shop.admin.pojo.ScAdvertise;

import framework.action.PageAction;
import framework.bean.PageBean;

public class AdvertiseAction extends PageAction{

	@Resource(name="advertiseService")
	private IAdvertiseService advertiseService;
	
	private ScAdvertise advertise;
	
	private Map<String, Object> map;
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = advertiseService.queryAdvertiseList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询单条记录
	 */
	public String queryAdvertise(){
		advertise = advertiseService.queryAdvertise(advertise.getId());
		return Action.SUCCESS;
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String findAdvertise(){
		map = advertiseService.findAdvertise(advertise.getId());
		return Action.SUCCESS;
	}
	
	/**
	 *保存 
	 */
	public String save(){
		advertiseService.saveAdvertise(advertise);
		return Action.SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		advertiseService.deleteAdvertise(advertise.getId());
		return Action.SUCCESS;
	}
	public ScAdvertise getAdvertise() {
		return advertise;
	}
	public void setAdvertise(ScAdvertise advertise) {
		this.advertise = advertise;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
