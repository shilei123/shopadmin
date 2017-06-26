package com.sunchin.shop.admin.category.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.category.service.CategoryService;
import com.sunchin.shop.admin.pojo.ScCategory;

@SuppressWarnings("rawtypes")
public class CategoryAction {
	
	@Resource(name="categoryService")
	private CategoryService categoryService; 
	private List<Map> trees;
	private String msg;
	private ScCategory category;
	private List<ScCategory> categorys;
	
	public String categoryTree() {
		try {
			trees = categoryService.queryCategoryTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String saveCategory(){
		try {
			msg = categoryService.saveCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String updateCategory(){
		try {
			categoryService.updateCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String delCategory(){
		try {
			List<ScCategory> list = categoryService.queryCategory(category.getId());
			if(list!=null && !list.isEmpty()){
				this.msg = "该类别下已有子类别，无法删除！";
			}else{
				categoryService.delCategory(category.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String queryGategory() {
		try {
			this.categorys =  this.categoryService.queryCategory(category.getId());
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ScCategory getCategory() {
		return category;
	}

	public void setCategory(ScCategory category) {
		this.category = category;
	}

	public List<ScCategory> getCategorys() {
		return categorys;
	}
}
