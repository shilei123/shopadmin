package com.sunchin.shop.admin.category.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.category.service.CategoryService;
import com.sunchin.shop.admin.pojo.ScCategory;

import framework.config.SysDict;
import framework.db.DBUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CategoryAction {
	
	@Resource(name="categoryService")
	private CategoryService categoryService; 
	private List<Map> trees;
	private String msg;
	private ScCategory category;
	
	private static final String CATEGORY_SQL = " select o.id,o.cate_name,o.memo,o.cate_order,o.levels,o.logo,o.url,o.isuse,o.parent_id from sc_category o where o.flag=? ";
	
	public String categoryTree() {
		categoryTreeQuery();
		return Action.SUCCESS;
	}
	
	private void categoryTreeQuery() {
		DBUtil db = DBUtil.getInstance();
		List<Map> list = db.queryBySQL(CATEGORY_SQL, SysDict.FLAG_ACT);
		Map root = null;
		Map<String,Map> temp = new TreeMap();
		for (Map pojo : list) {
			Map node = new TreeMap();
			node.put("pkId", pojo.get("id")); //类别主键
			node.put("text", pojo.get("cateName")); //类别名称
			node.put("parentId", pojo.get("parentId")); //上级类别编码
			node.put("levels", pojo.get("levels")); //上级类别编码
			Map attributes = new HashMap(5);
			attributes.put("memo", pojo.get("memo")); //类别描述
			attributes.put("cateOrder", pojo.get("cateOrder")); //类别排序
//			Integer levels = new Integer(CommonUtils.getString(pojo.get("levels")));
//			attributes.put("levels", levels.toString()); //类别级别
			attributes.put("logo", pojo.get("logo")); //logo
			attributes.put("url", pojo.get("url")); //类别url 
			attributes.put("isuse", pojo.get("isuse")); //是否有效
			node.put("attributes", attributes);
			temp.put(pojo.get("id").toString(), node);
			if("0".equals(pojo.get("levels"))) {
				root = node;
			}
		}
		
		//循环找出层级关系
		for(String key : temp.keySet()) {
			Map node = temp.get(key);
			Object parentId = node.get("parentId");
			if(parentId==null){
				continue;
			}
			Map parentMap = temp.get(parentId);
			if(parentMap != null) {
				if(parentMap.get("children") == null) {
					parentMap.put("children", new ArrayList());
				}
				((ArrayList)parentMap.get("children")).add(node);
			}
		}
		
		//循环判断是否有子节点，是否能展开
		for(String key : temp.keySet()) {
			Map node = temp.get(key);
			if(node.get("children") != null) {
				List childOrgList = (ArrayList) node.get("children");
				if(!childOrgList.isEmpty()) {
//					Collections.sort(childOrgList, new ComparatorOrgVO());
					node.put("state", "closed"); //节点状态：关闭
				}
			} 
		}
		
		trees = new ArrayList(1);
		if(root != null) {
			trees.add(root);
		}
	}
	
	public String saveCategory(){
		try {
			categoryService.saveCategory(category);
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

}
