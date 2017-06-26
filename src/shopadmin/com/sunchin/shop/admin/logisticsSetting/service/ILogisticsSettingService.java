package com.sunchin.shop.admin.logisticsSetting.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScExpressProvider;

import framework.bean.PageBean;

public interface ILogisticsSettingService {

	PageBean queryLogisticsList(PageBean pageBean) throws Exception;

	void saveExpressProvider(ScExpressProvider logistics) throws Exception;

	ScExpressProvider editExpress(String id) throws Exception;

	void saveIsuse(String id) throws Exception;

	void deleteExpress(String id) throws Exception;

	/**
	 * 发货页面初始化快递服务商的选择框	modify by aobingcheng 2017/04/28
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	List<Map> queryAllCompany() throws Exception;
}
