package com.sunchin.shop.admin.category.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScCategory;

import framework.config.SysDict;
import framework.db.DBUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository("categoryDAO")
public class CategoryDAO {

	/**
	 * 查询树结构
	 * @return
	 */
	public List<Map> queryTreeBySQL(){
		String sql = " select o.id,o.cate_name,o.memo,o.cate_order,o.levels,o.logo,o.url,o.isuse,o.parent_id from sc_category o where o.flag=? ";
		return DBUtil.getInstance().queryBySQL(sql, FlagEnum.ACT.getCode());
	}
	
	/**
	 * 查询该id是否有子类别
	 * @param id
	 * @return
	 */
	public List<ScCategory> querySonCategory(String id) {
		Map params = new HashMap<>(2);
		params.put("parentId", id);
		params.put("flag", FlagEnum.ACT.getCode());
		List<ScCategory> list = DBUtil.getInstance().queryByPojo(ScCategory.class, params);
		return list;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public List<ScCategory> queryPojoById(String id) {
		Map params = new HashMap<>(2);
		params.put("id", id);
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
	
	public void delCategory(String id) {
		String hql = " update ScCategory set flag=? where id=? ";
		DBUtil.getInstance().executeHql(hql, SysDict.FLAG_HIS, id);
	}
}
