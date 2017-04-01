package com.sunchin.shop.admin.catePropPropval.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.pojo.ScCatePropPropval;

import com.sunchin.shop.admin.dict.FlagEnum;

import framework.db.DBUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository("catePropPropvalDAO")
public class CatePropPropvalDAO {
	
	/**
	 * 查询该类别的类别-属性属性值关系
	 * @param cateId
	 * @return
	 */
	public List<Map> queryCatePropPropValByCateId(String cateId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.cate_name,t2.id as prop_propval_id,t3.prop_name,t4.val_name from sc_cate_prop_propval t ");
		sql.append(" left join sc_category t1 on t1.id=t.cate_id ");
		sql.append(" left join sc_prop_propval t2 on t2.id=t.ppv_id ");
		sql.append(" left join sc_property t3 on t3.id=t2.prop_id ");
		sql.append(" left join sc_propval t4 on t4.id=t2.val_id ");
		sql.append(" where t1.id=? ");
		sql.append(" and t.flag=? ");
		sql.append(" and t1.flag=? ");
		sql.append(" and t2.flag=? ");
		sql.append(" and t3.flag=? ");
		sql.append(" and t4.flag=? ");
		List params = new ArrayList(6);
		params.add(cateId);
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		return DBUtil.getInstance().queryBySQL(sql.toString(), params);
	}
	
	/**
	 * 找该类别对应的属性和属性值
	 * @param cateId
	 * @return
	 */
	public List<ScCatePropPropval> findPojo(String cateId){
		Map params = new HashMap<String, String>();
		params.put("flag", FlagEnum.ACT.getCode());
		params.put("cateId", cateId);
		List<ScCatePropPropval> list = DBUtil.getInstance().queryByPojo(ScCatePropPropval.class, params);
		return list;
	}
	
	/**
	 * 根据类别查询类别-属性-属性值关系 add by yangchaowen
	 * @param cateId
	 * @return
	 * @date 2017-03-30
	 */
	public List queryByCateId(String cateId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT cpp.id cpp_id, ");
		sql.append(" 		c.id cate_id, ");
		sql.append(" 		c.cate_name, ");
		sql.append(" 		c.order_ as cate_order, ");
		sql.append(" 		p.id prop_id, ");
		sql.append(" 		p.prop_name, ");
		sql.append(" 		p.prop_code, ");
		sql.append(" 		p.order_ as prop_order, ");
		sql.append(" 		pv.id val_id, ");
		sql.append(" 		pv.val_name, ");
		sql.append(" 		pv.val_code, ");
		sql.append(" 		pv.order_ as val_order ");
		sql.append(" FROM sc_cate_prop_propval cpp ");
		sql.append(" LEFT JOIN sc_category c ON cpp.cate_id=c.id ");
		sql.append(" LEFT JOIN sc_prop_propval pp ON cpp.ppv_id=pp.id ");
		sql.append(" LEFT JOIN sc_property p ON pp.prop_id=p.id ");
		sql.append(" LEFT JOIN sc_propval pv ON pv.id=pp.val_id ");
		sql.append(" WHERE cpp.cate_id=? ");
		sql.append(" AND   cpp.flag=? ");
		return DBUtil.getInstance().queryBySQL(sql.toString(), cateId, FlagEnum.ACT.getCode());
	}
	
	public void delAllCatePropPropValue(String cateId){
		String hql = " update ScCatePropPropVal set flag=? where cateId=? ";
		DBUtil.getInstance().executeHql(hql, FlagEnum.HIS.getCode(), cateId);
	}
	
	public void delCatePropPropValue(String cateId, String proppropvalId){
		String hql = " update ScCatePropPropVal set flag=? where cateId=? and proppropvalId=? ";
		DBUtil.getInstance().executeHql(hql, FlagEnum.HIS.getCode(), cateId, proppropvalId);
	}
}
