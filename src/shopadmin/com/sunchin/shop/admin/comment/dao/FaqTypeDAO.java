package com.sunchin.shop.admin.comment.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScFaqType;

import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository("faqTypeDAO")
public class FaqTypeDAO extends PageDAO{
	
	/**
	 * 查询常见问题分类的树结构
	 * @return
	 */
	public List<Map> queryTreeBySQL(){
		String sql = " select o.* from sc_faq_type o where o.flag=? ";
		return DBUtil.getInstance().queryBySQL(sql, FlagEnum.ACT.getCode());
	}

	/**
	 * 查询该问题类别有没有子类别
	 * @param id
	 * @return
	 */
	public List<ScFaqType> querySonFaqType(String id) {
		Map params = new HashMap<>(2);
		params.put("parentTypeId", id);
		params.put("flag", FlagEnum.ACT.getCode());
		return DBUtil.getInstance().queryByPojo(ScFaqType.class, params);
	}

	/**
	 * 逻辑删除常见问题分类
	 * @param id
	 */
	public void delFaqType(String id) {
		String hql = " update ScFaqType set flag=? where id=? ";
		DBUtil.getInstance().executeHql(hql, FlagEnum.HIS.getCode(), id);
	}
	
}
