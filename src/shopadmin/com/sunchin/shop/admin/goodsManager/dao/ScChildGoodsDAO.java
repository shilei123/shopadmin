package com.sunchin.shop.admin.goodsManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;

import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings("rawtypes")
@Repository("childGoodsDAO")
public class ScChildGoodsDAO extends PageDAO {
	
	public List queryListByGoodsId(String goodsId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" with sc_child_goods_temp as ( ");
		sql.append(" 	 select gcpp.goods_id, ");
		sql.append("     		gcpp.child_goods_id, ");
		sql.append("     		replace(wm_concat(gcpp.cppv_id),',','_') exists_goods_key, ");
		sql.append("    		wm_concat(p.prop_name) prop_names, ");
		sql.append("     		wm_concat(pv.val_name) val_names ");
		sql.append("     from sc_goods_cate_prop_propval gcpp ");
		sql.append("     left join sc_cate_prop_propval cppv on cppv.id=gcpp.cppv_id ");
		sql.append("     left join sc_prop_propval ppv on ppv.id=cppv.ppv_id ");
		sql.append("     left join sc_property p on ppv.prop_id=p.id ");
		sql.append("     left join sc_propval pv on ppv.val_id=pv.id ");
		sql.append("     where gcpp.goods_id=? ");
		sql.append("     group by gcpp.goods_id,gcpp.child_goods_id ");
		sql.append(" ) ");
		sql.append(" select cg.*, ");
		sql.append("        r.available_num, ");
		sql.append("        cgt.exists_goods_key, ");
		sql.append("        cgt.prop_names, ");
		sql.append("        cgt.val_names ");
		sql.append(" from sc_child_goods cg  ");
		sql.append(" left join sc_repertory r on cg.id=r.child_goods_id ");
		sql.append(" left join sc_child_goods_temp cgt on cg.id=cgt.child_goods_id ");
		sql.append(" where cg.goods_id=? ");
		sql.append(" and   cg.flag=? ");
		List list = DBUtil.getInstance().queryBySQL(sql.toString(), goodsId, goodsId, FlagEnum.ACT.getCode());
		return list;
	}
}