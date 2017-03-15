package com.sunchin.shop.admin.eventsinfo.service;

import com.sunchin.shop.admin.pojo.ScEventsinfo;

import framework.bean.PageBean;

public interface IEventsinfoService {

	PageBean queryEventsinfoList(PageBean pageBean);

	void deleteEventsinfo(String id);

	ScEventsinfo queryEventsinfo(String id);

	void saveEventsinfo(ScEventsinfo eventsinfo);

}
