package com.sunchin.shop.admin.logisticsSetting.action;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.logisticsSetting.service.ILogisticsSettingService;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScExpressProvider;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

@SuppressWarnings("rawtypes")
public class LogisticsSettingAction extends PageAction{

	@Resource(name="logisticsSettingService")
	private ILogisticsSettingService logisticsSettingService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private ScExpressProvider logistics;
	private List<Map> companyList;
	
	private List<ScDictionary> dictionaryList;
	
	/**
	 * 查询
	 * @return
	 */
	public String queryLogistics(){
		try {
			PageBean resultData = logisticsSettingService.queryLogisticsList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
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
				logisticsSettingService.saveExpressProvider(logistics);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return Action.SUCCESS;
	}
	
	/**
	 * 修改查询单条数据
	 * @return
	 */
	public String editExpress(){
		try {
			logistics = logisticsSettingService.editExpress(logistics.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 是否设置默认
	 */
	public String saveIsuse(){
		try {
			logisticsSettingService.saveIsuse(logistics.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查看快递类型
	 */
	public String expressType(){
		try {
			dictionaryList = dictService.getDictByType(DictionaryTypeEnum.EXPRESS_TYPE.getType());
		} catch (Exception e) {
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
			logisticsSettingService.deleteExpress(logistics.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 发货页面初始化快递服务商的选择框	modify by aobingcheng 2017/04/28
	 * @return
	 */
	public String queryAllCompany(){
		try {
			companyList = logisticsSettingService.queryAllCompany();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public ScExpressProvider getLogistics() {
		return logistics;
	}

	public void setLogistics(ScExpressProvider logistics) {
		this.logistics = logistics;
	}

	public List<ScDictionary> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<ScDictionary> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public List<Map> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Map> companyList) {
		this.companyList = companyList;
	}

}
