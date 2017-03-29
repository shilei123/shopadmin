package com.sunchin.shop.admin.category.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScCategory;

import framework.db.DBUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository("categoryDAO")
public class CategoryDAO {

	public List<ScCategory> queryCategory(String param, String id) {
		Map params = new HashMap<>(2);
		params.put(param, id);
		params.put("flag", FlagEnum.ACT.getCode());
		List<ScCategory> list = DBUtil.getInstance().queryByPojo(ScCategory.class, params);
		return list;
	}
	
	/**
	 * 查询一级分类
	 * @return
	 * @author yangchaowen add by 201703-23
	 */
	public List<ScCategory> queryFirstCategory() {
		Map params = new HashMap<>(2);
		params.put("levels", "1");
		params.put("flag", FlagEnum.ACT.getCode());
		return DBUtil.getInstance().queryByPojo(ScCategory.class, params, " cateOrder ");
	}
}
