package com.sunchin.shop.admin.system.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class DictAction extends PageAction {
	@Resource(name = "dictService")
	private DictService dictService;
	private ScDictionary dict;
	private List<ScDictionary> dicts;
	
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
	 * 根据类型查询
	 * @return
	 */
	public String queryDictByType() {
		try {
			dicts = dictService.getDictByType(dict.getType());
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
	
	public List<ScDictionary> getDicts() {
		return dicts;
	}

	public void setDicts(List<ScDictionary> dicts) {
		this.dicts = dicts;
	}
}
