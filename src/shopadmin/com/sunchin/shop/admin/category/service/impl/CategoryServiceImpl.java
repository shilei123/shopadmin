package com.sunchin.shop.admin.category.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.category.service.CategoryService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScCategory;

import framework.db.DBUtil;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Override
	public ScCategory queryCategoryInfo(String categoryId) throws Exception {
		
		return null;
	}

	@Override
	public void saveCategory(ScCategory category) throws Exception {
		DBUtil db = DBUtil.getInstance();
		category.setId(UUID.randomUUID().toString());
		category.setFlag(FlagEnum.ACT.getCode());
		category.setCreateTime(new Date());
		db.insert(category);
	}

}
