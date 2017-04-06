package com.sunchin.shop.admin.events.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.events.service.IEventsService;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScEvents;
import com.sunchin.shop.admin.pojo.ScEventsGoods;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class EventsAction extends PageAction{

	@Resource(name="eventsService")
	private IEventsService eventsService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private ScEvents events;
	private List<ScDictionary> dictionaryList;
	private String eventsGoodsList;
	private List<Map<String,Object>> eventsList;
	private ScEventsGoods eventsGoods;
	
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = eventsService.queryEventsList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 编辑
	 */
	public String queryEvents(){
		try {
			eventsList = eventsService.queryEvents(events.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 保存
	 */
	public String save(){
		try {
			eventsService.saveEvents(events,eventsGoodsList);
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
			eventsService.deleteEvents(events.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String queryEventsType(){
		try {
			dictionaryList = dictService.getDictByType(DictionaryTypeEnum.ADVERTISE_ISUSE.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public ScEvents getEvents() {
		return events;
	}

	public void setEvents(ScEvents events) {
		this.events = events;
	}

	public List<ScDictionary> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<ScDictionary> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public String getEventsGoodsList() {
		return eventsGoodsList;
	}

	public void setEventsGoodsList(String eventsGoodsList) {
		this.eventsGoodsList = eventsGoodsList;
	}

	public List<Map<String, Object>> getEventsList() {
		return eventsList;
	}

	public void setEventsList(List<Map<String, Object>> eventsList) {
		this.eventsList = eventsList;
	}

	public ScEventsGoods getEventsGoods() {
		return eventsGoods;
	}

	public void setEventsGoods(ScEventsGoods eventsGoods) {
		this.eventsGoods = eventsGoods;
	}
	
}
