package com.sunchin.shop.admin.eventsinfo.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.eventsinfo.service.IEventsinfoService;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScEventsinfo;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class EventsinfoAction extends PageAction{

	@Resource(name="eventsinfoService")
	private IEventsinfoService eventsinfoService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private ScEventsinfo eventsinfo;
	private List<ScDictionary> dictionaryList;
	
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

	public String queryEventsinfoType(){
		try {
			dictionaryList = dictService.findDictionaryType(DictionaryTypeEnum.ADVERTISE_ISUSE.getType());
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

	public List<ScDictionary> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<ScDictionary> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	
}
