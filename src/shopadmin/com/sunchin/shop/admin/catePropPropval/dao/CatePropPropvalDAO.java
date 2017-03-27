package com.sunchin.shop.admin.catePropPropval.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

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
	
	/**类别-属性属性值管理修改
	 * 查询该类别的所有属性和属性值
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
	
	/*public ScCatePropPropVal findPojo(String cateId, String proppropvalId){
		Map params = new HashMap<String, String>();
		params.put("flag", FlagEnum.ACT.getCode());
		params.put("cateId", cateId);
		params.put("proppropvalId", proppropvalId);
		List<ScCatePropPropVal> list = DBUtil.getInstance().queryByPojo(ScCatePropPropVal.class, params);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}*/
}
