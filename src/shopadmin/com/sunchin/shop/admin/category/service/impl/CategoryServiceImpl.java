package com.sunchin.shop.admin.category.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.category.dao.CategoryDAO;
import com.sunchin.shop.admin.category.service.CategoryService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScCategory;

import framework.config.SysDict;
import framework.db.DBUtil;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Resource(name="categoryDAO")
	private CategoryDAO categoryDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	
	@Override
	public void saveCategory(ScCategory category) throws Exception {
		DBUtil db = DBUtil.getInstance();
		/*String cateOrder = CommonUtils.getString(category.getCateOrder());
		if(cateOrder.length()==1){
			cateOrder = "0" + cateOrder;
		}
		category.setCateOrder(cateOrder);*/
		category.setId(UUID.randomUUID().toString());
		category.setFlag(FlagEnum.ACT.getCode());
		category.setCreateTime(new Date());
		db.insert(category);
	}

	@Override
	public void delCategory(String id) throws Exception {
		String hql = " update ScCategory set flag=? where id=? ";
		db.executeHql(hql, SysDict.FLAG_HIS, id);
	}

	@Override
	public List<ScCategory> queryCategory(String parentId) throws Exception {
		List<ScCategory> list = categoryDAO.queryCategoryByParentId(parentId);
		return list;
	}

	@Override
	public void updateCategory(ScCategory category) throws Exception {
		ScCategory cate = (ScCategory) db.get(ScCategory.class, category.getId());
		cate.setCateName(category.getCateName());
		cate.setCateOrder(category.getCateOrder());
		cate.setMemo(category.getMemo());
		cate.setLogo(category.getLogo());
		cate.setUrl(category.getUrl());
		cate.setCateCode(category.getCateCode());
		cate.setIsuse(category.getIsuse());
		category.setUpdateTime(new Date());
		db.update(cate);
	}

}
