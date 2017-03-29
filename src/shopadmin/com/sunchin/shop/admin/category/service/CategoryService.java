package com.sunchin.shop.admin.category.service;

import java.util.List;

import com.sunchin.shop.admin.pojo.ScCategory;

public interface CategoryService {

	/**
	 * 新增类别
	 * @param category
	 * @throws Exception
	 */
	public void saveCategory(ScCategory category) throws Exception;
	
	/**
	 * 修改类别
	 * @param category
	 * @throws Exception
	 */
	public void updateCategory(ScCategory category) throws Exception;
	
	/**
	 * 删除类别
	 * @param id
	 * @throws Exception
	 */
	public void delCategory(String categoryId) throws Exception;
	
	/**
	 * 查询该类别是否有子类
	 * @param id
	 * @throws Exception
	 */
	public List<ScCategory> queryCategory(String id) throws Exception;
	
}
