package com.sunchin.shop.admin.events.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScEvents;
import com.sunchin.shop.admin.pojo.ScEventsGoods;

import framework.bean.PageBean;

public interface IEventsService {

	PageBean queryEventsList(PageBean pageBean) throws Exception;

	void deleteEvents(String id) throws Exception;

	List<Map<String,Object>> queryEvents(String id) throws Exception;

	void saveEvents(ScEvents events,String eventsGoodsList) throws Exception;

	void deleteEventsGoods(String id) throws Exception;

}
