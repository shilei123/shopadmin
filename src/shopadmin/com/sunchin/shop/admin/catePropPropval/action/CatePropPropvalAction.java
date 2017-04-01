package com.sunchin.shop.admin.catePropPropval.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.catePropPropval.service.CatePropPropvalService;

@SuppressWarnings("rawtypes")
public class CatePropPropvalAction {

	@Resource(name = "catePropPropvalService")
	private CatePropPropvalService catePropPropvalService;
	private List<Map> trees;
	private List list;		//该类别所有属性和属性值
	private List listCheck;	//该类别已经选中的属性属性值
	private String cateId;
	private String cateIds;
	private String propPropValIds;

	/**
	 * 查询类别树
	 * @return
	 */
	public String queryCategoryTree() {
		try {
			trees = catePropPropvalService.queryCategoryTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 该类别的所有属性和属性值
	 * @return
	 */
	public String queryListByCateId() {
		try {
			list = catePropPropvalService.queryListByCateId(cateId);
			listCheck = catePropPropvalService.queryListCheck(cateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 根据类别查询类别-属性-属性值关系 add by yangchaowen
	 * @return
	 * @date 2017-03-30
	 */
	public String queryByCateId() {
		try {
			list = catePropPropvalService.queryByCateId(cateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 保存类别-属性属性值
	 * @return
	 */
	public String saveCatePropPropVal() {
		try {
			catePropPropvalService.saveCatePropPropVal(cateId, propPropValIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public List<Map> getTrees() {
		return trees;
	}

	public void setTrees(List<Map> trees) {
		this.trees = trees;
	}

	public String getCateIds() {
		return cateIds;
	}

	public void setCateIds(String cateIds) {
		this.cateIds = cateIds;
	}

	public void setPropPropValIds(String propPropValIds) {
		this.propPropValIds = propPropValIds;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getListCheck() {
		return listCheck;
	}

	public void setListCheck(List listCheck) {
		this.listCheck = listCheck;
	}

}
