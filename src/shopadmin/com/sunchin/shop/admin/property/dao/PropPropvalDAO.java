package com.sunchin.shop.admin.property.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;

import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository("propPropvalDAO")
public class PropPropvalDAO extends PageDAO{

	/**
	 * 查询该类别的所有属性对应的属性属性值关系
	 * @param cateId
	 * @return
	 */
	public List<Map> queryPropPropvalByCateId(String cateId){
		//需要查出类别下的所有属性，暂不加上t4.flag=1 and t5.flag=1
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.cate_name,t3.id as prop_id,t3.prop_name,t4.id as prop_propval_id,t5.id as val_id,t5.val_name from sc_category t1 ");
		sql.append(" left join sc_cate_prop t2 on t2.cate_id=t1.id ");
		sql.append(" left join sc_property t3 on t3.id=t2.prop_id ");
		sql.append(" left join sc_prop_propval t4 on t4.prop_id=t2.prop_id ");
		sql.append(" left join sc_propval t5 on t5.id=t4.val_id ");
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
	 * 查询该属性的属性-属性值关系
	 * @param cateId
	 * @return
	 */
	public List<Map<String, Object>> queryPropPropvalByPropId(String propId) {
		StringBuffer sql = new StringBuffer(" select t.* from SC_PROP_PROPVAL t where t.flag=? and t.prop_id=? ");
		List<String> params = new ArrayList<String>(2);
		params.add(FlagEnum.ACT.getCode());
		params.add(propId);
		return DBUtil.getInstance().queryBySQL(sql.toString(), params);
	}
	
	public void delAllPropPropval(String propId){
		String hql = " update ScPropPropval set flag=? where propId=? ";
		DBUtil.getInstance().executeHql(hql, FlagEnum.HIS.getCode(), propId);
	}

	public void delPropPropval(String propId, String valId){
		String hql = " update ScPropPropval set flag=? where propId=? and valId=? ";
		DBUtil.getInstance().executeHql(hql, FlagEnum.HIS.getCode(), propId, valId);
	}
}
