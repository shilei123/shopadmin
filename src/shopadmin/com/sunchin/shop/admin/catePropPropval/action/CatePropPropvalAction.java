package com.sunchin.shop.admin.catePropPropval.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.catePropPropval.service.CatePropPropvalService;
import com.sunchin.shop.admin.pojo.ScCatePropPropVal;

@SuppressWarnings("rawtypes")
public class CatePropPropvalAction {

	@Resource(name = "catePropPropvalService")
	private CatePropPropvalService catePropPropvalService;
	private List<Map> trees;
	private List map;
	private ScCatePropPropVal catePropPropVal;
	private String cateId;

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
	public String queryMapByCateId() {
		try {
			map = catePropPropvalService.queryMapByCateId(cateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String saveCatePropPropVal() {
		try {
			catePropPropvalService.saveCatePropPropVal(catePropPropVal);
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

	public ScCatePropPropVal getCatePropPropVal() {
		return catePropPropVal;
	}

	public void setCatePropPropVal(ScCatePropPropVal catePropPropVal) {
		this.catePropPropVal = catePropPropVal;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public List getMap() {
		return map;
	}

	public void setMap(List map) {
		this.map = map;
	}

}
