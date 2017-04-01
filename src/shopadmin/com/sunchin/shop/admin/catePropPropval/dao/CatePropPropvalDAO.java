package com.sunchin.shop.admin.catePropPropval.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.pojo.ScCatePropPropVal;

import com.sunchin.shop.admin.dict.FlagEnum;

import framework.db.DBUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository("catePropPropvalDAO")
public class CatePropPropvalDAO {
	
	/**
	 * 查询树结构
	 * @return
	 */
	public List<Map> queryTreeBySQL(){
		String sql = " select o.id,o.cate_name,o.memo,o.cate_order,o.levels,o.logo,o.url,o.isuse,o.parent_id from sc_category o where o.flag=? ";
		return DBUtil.getInstance().queryBySQL(sql, FlagEnum.ACT.getCode());
	}
	
	/**
	 * 查询该类别的所有属性对应的属性属性值关系
	 * @param cateId
	 * @return
	 */
	public List<Map> queryMapByCateId(String cateId){
		//需要查出类别下的所有属性，暂不加上t4.flag=1 and t5.flag=1
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.cate_name,t3.id as prop_id,t3.prop_name,t4.id as prop_propval_id,t5.id as val_id,t5.val_name from sc_category t1 ");
		sql.append(" left join sc_property_category t2 on t2.cate_id=t1.id ");
		sql.append(" left join sc_property t3 on t3.id=t2.prop_id ");
		sql.append(" left join sc_property_propvalue t4 on t4.prop_id=t2.prop_id ");
		sql.append(" left join sc_propvalue t5 on t5.id=t4.val_id ");
		sql.append(" where t1.id=? ");
		sql.append(" and t1.flag=? ");
		sql.append(" and t2.flag=? ");
		sql.append(" and t3.flag=? order by t3.prop_name ");
		List params = new ArrayList(4);
		params.add(cateId);
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		return DBUtil.getInstance().queryBySQL(sql.toString(), params);
	}
	
	/**
	 * 查询该类别的类别-属性属性值关系
	 * @param cateId
	 * @return
	 */
	public List<Map> queryCatePropPropValByCateId(String cateId){
		//需要查出类别下的所有属性，暂不加上t4.flag=1 and t5.flag=1
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.cate_name,t2.id as prop_propval_id,t3.prop_name,t4.val_name from sc_cate_prop_propval t ");
		sql.append(" left join sc_category t1 on t1.id=t.cate_id ");
		sql.append(" left join sc_property_propvalue t2 on t2.id=t.proppropval_id ");
		sql.append(" left join sc_property t3 on t3.id=t2.prop_id ");
		sql.append(" left join sc_propvalue t4 on t4.id=t2.val_id ");
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
	public List<ScCatePropPropVal> findPojo(String cateId){
		Map params = new HashMap<String, String>();
		params.put("flag", FlagEnum.ACT.getCode());
		params.put("cateId", cateId);
		List<ScCatePropPropVal> list = DBUtil.getInstance().queryByPojo(ScCatePropPropVal.class, params);
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
		sql.append(" 		c.cate_order, ");
		sql.append(" 		p.id prop_id, ");
		sql.append(" 		p.prop_name, ");
		sql.append(" 		p.prop_code, ");
		sql.append(" 		p.prop_order, ");
		sql.append(" 		pv.id val_id, ");
		sql.append(" 		pv.val_name, ");
		sql.append(" 		pv.val_code, ");
		sql.append(" 		pv.val_order ");
		sql.append(" FROM sc_cate_prop_propval cpp ");
		sql.append(" LEFT JOIN sc_category c ON cpp.cate_id=c.id ");
		sql.append(" LEFT JOIN sc_property_propvalue pp ON cpp.proppropval_id=pp.id ");
		sql.append(" LEFT JOIN sc_property p ON pp.prop_id=p.id ");
		sql.append(" LEFT JOIN sc_propvalue pv ON pv.id=pp.val_id ");
		sql.append(" WHERE cpp.cate_id=? ");
		sql.append(" AND   cpp.flag=? ");
		return DBUtil.getInstance().queryBySQL(sql.toString(), cateId, FlagEnum.ACT.getCode());
	}
}