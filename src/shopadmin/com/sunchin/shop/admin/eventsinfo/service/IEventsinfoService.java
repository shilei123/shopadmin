package com.sunchin.shop.admin.eventsinfo.service;

import com.sunchin.shop.admin.pojo.ScEventsinfo;

import framework.bean.PageBean;

public interface IEventsinfoService {

	PageBean queryEventsinfoList(PageBean pageBean) throws Exception;

	void deleteEventsinfo(String id) throws Exception;

	ScEventsinfo queryEventsinfo(String id) throws Exception;

	void saveEventsinfo(ScEventsinfo eventsinfo) throws Exception;

}
