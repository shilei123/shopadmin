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
		try {
			advertise = advertiseService.queryAdvertise(advertise.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String findAdvertise(){
		try {
			map = advertiseService.findAdvertise(advertise.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 *保存 
	 */
	public String save(){
		try {
			advertiseService.saveAdvertise(advertise);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		try {
			advertiseService.deleteAdvertise(advertise.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
