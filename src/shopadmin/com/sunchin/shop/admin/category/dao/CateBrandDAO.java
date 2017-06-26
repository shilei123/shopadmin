package com.sunchin.shop.admin.category.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("cateBrandDAO")
public class CateBrandDAO extends PageDAO{
	
	/**
	 * 查询该类别的类别-品牌关系
	 * @param cateId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryCateBrandByCateId(String cateId) {
		StringBuffer sql = new StringBuffer(" select t.* from SC_CATE_BRAND t where t.flag=? and t.cate_id=? ");
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(cateId);
		return DBUtil.getInstance().queryBySQL(sql.toString(), params);
	}
	
	public void delPropBrand(String cateId, String brandId){
		String hql = " update ScCateBrand set flag=? where cateId=? and brandId=? ";
		DBUtil.getInstance().executeHql(hql, FlagEnum.HIS.getCode(), cateId, brandId);
	}

}
