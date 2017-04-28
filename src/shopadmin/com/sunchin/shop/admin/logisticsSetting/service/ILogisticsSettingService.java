package com.sunchin.shop.admin.logisticsSetting.service;

import com.sunchin.shop.admin.pojo.ScExpressProvider;

import framework.bean.PageBean;

public interface ILogisticsSettingService {

	PageBean queryLogisticsList(PageBean pageBean) throws Exception;

	void saveExpressProvider(ScExpressProvider logistics) throws Exception;

	ScExpressProvider editExpress(String id) throws Exception;

	void saveIsuse(String id) throws Exception;

	void deleteExpress(String id) throws Exception;
}
