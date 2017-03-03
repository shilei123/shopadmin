package com.sunchin.shop.admin.system.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.system.service.IDictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class DictAction extends PageAction {
	@Resource(name = "dictService")
	private IDictService dictService;
	private ScDictionary dict;
	
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = dictService.queryDictList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 查询单条记录
	 * @return
	 */
	public String queryDict() {
		try {
			dict = dictService.getDict(dict.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String save() {
		try {
			dictService.saveDict(dict);
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
			dictService.deleteDict(dict.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public ScDictionary getDict() {
		return dict;
	}

	public void setDict(ScDictionary dict) {
		this.dict = dict;
	}
	
	
}
