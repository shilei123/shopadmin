package com.sunchin.shop.admin.category.service;

import com.sunchin.shop.admin.pojo.ScCategory;

public interface CategoryService {

	/**
	 * 根据类别id查找类别信息
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	public ScCategory queryCategoryInfo(String categoryId) throws Exception;

	/**
	 * 新增商品类别
	 * @param category
	 * @throws Exception
	 */
	public void saveCategory(ScCategory category) throws Exception;
	
}
