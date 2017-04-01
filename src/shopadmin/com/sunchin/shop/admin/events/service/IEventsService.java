package com.sunchin.shop.admin.events.service;

import com.sunchin.shop.admin.pojo.ScEvents;

import framework.bean.PageBean;

public interface IEventsService {

	PageBean queryEventsList(PageBean pageBean) throws Exception;

	void deleteEvents(String id) throws Exception;

	ScEvents queryEvents(String id) throws Exception;

	void saveEvents(ScEvents eventsinfo) throws Exception;

}
