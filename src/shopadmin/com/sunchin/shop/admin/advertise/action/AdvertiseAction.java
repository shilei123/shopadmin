package com.sunchin.shop.admin.advertise.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.advertise.service.IAdvertiseService;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.pojo.ScAdvertise;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class AdvertiseAction extends PageAction{

	@Resource(name="advertiseService")
	private IAdvertiseService advertiseService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private ScAdvertise advertise;
	private List<ScDictionary> dictionaryList; //广告类型
	private List<ScDictionary> isuseList; //是否启用
	private Map<String, Object> map;
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = advertiseService.queryAdvertiseList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询单条记录
	 */
	public String queryAdvertise(){
		try {
			advertise = advertiseService.queryAdvertise(advertise.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String findAdvertise(){
		try {
			map = advertiseService.findAdvertise(advertise.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 *保存 
	 */
	public String save(){
		try {
			advertiseService.saveAdvertise(advertise);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			advertiseService.deleteAdvertise(advertise.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String AdvertiseType(){
		try {
			dictionaryList = dictService.getDictByType(DictionaryTypeEnum.ADVERTISE_LINKKIND.getType());
			isuseList = dictService.getDictByType(DictionaryTypeEnum.ADVERTISE_ISUSE.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
	
	
	public ScAdvertise getAdvertise() {
		return advertise;
	}
	public void setAdvertise(ScAdvertise advertise) {
		this.advertise = advertise;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<ScDictionary> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<ScDictionary> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public List<ScDictionary> getIsuseList() {
		return isuseList;
	}

	public void setIsuseList(List<ScDictionary> isuseList) {
		this.isuseList = isuseList;
	}
	
}
