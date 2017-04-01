package com.sunchin.shop.admin.freight.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.freight.service.IFreightService;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScFreight;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class FreightAction extends PageAction{

	@Resource(name="freightService")
	private IFreightService freightService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private List<ScDictionary> isuseList;  //是否默认
	private ScFreight fre;
	List<Map<String, Object>> freightList;
	
	/**
	 * 查询模板信息
	 * @return
	 */
	public String queryFreight(){
		try {
			PageBean resultData = freightService.queryFreightList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 是否默认
	 */
	public String queryIsuse(){
		try {
			isuseList  = dictService.getDictByType(DictionaryTypeEnum.ISUSE.getType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 编辑
	 */
	public String EditFreight(){
		try {
			freightList = freightService.findFreightList(fre.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String save(){
		
		
		return Action.SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		try {
			freightService.deleteFreight(fre.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public ScFreight getFre() {
		return fre;
	}

	public void setFre(ScFreight fre) {
		this.fre = fre;
	}

	public List<ScDictionary> getIsuseList() {
		return isuseList;
	}

	public void setIsuseList(List<ScDictionary> isuseList) {
		this.isuseList = isuseList;
	}

	public List<Map<String, Object>> getFreightList() {
		return freightList;
	}

	public void setFreightList(List<Map<String, Object>> freightList) {
		this.freightList = freightList;
	}

	
}
