package com.sunchin.shop.admin.eventsinfo.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.eventsinfo.service.IEventsinfoService;
import com.sunchin.shop.admin.pojo.ScEventsinfo;

import framework.action.PageAction;
import framework.bean.PageBean;

public class EventsinfoAction extends PageAction{

	@Resource(name="eventsinfoService")
	private IEventsinfoService eventsinfoService;
	
	private ScEventsinfo eventsinfo;
	
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = eventsinfoService.queryEventsinfoList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查看单条记录
	 */
	public String queryEventsinfo(){
		try {
			eventsinfo = eventsinfoService.queryEventsinfo(eventsinfo.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 保存
	 */
	public String save(){
		try {
			eventsinfoService.saveEventsinfo(eventsinfo);
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
	public String delete() {
		try {
			eventsinfoService.deleteEventsinfo(eventsinfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public ScEventsinfo getEventsinfo() {
		return eventsinfo;
	}

	public void setEventsinfo(ScEventsinfo eventsinfo) {
		this.eventsinfo = eventsinfo;
	}

}
