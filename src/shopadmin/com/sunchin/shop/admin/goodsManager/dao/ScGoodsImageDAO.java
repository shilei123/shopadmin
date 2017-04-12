package com.sunchin.shop.admin.goodsManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScGoods;

import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings("rawtypes")
@Repository("scGoodsImageDAO")
public class ScGoodsImageDAO extends PageDAO {
	
	public List queryListByGoodsId(String goodsId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.id, ");
		sql.append("        t.image_id, ");
		sql.append("        t1.img_name, ");
		sql.append("        t1.file_name, ");
		sql.append("        t1.img_path, ");
		sql.append("        t1.img_type, ");
		sql.append("        t1.img_size ");
		sql.append(" from sc_goods_image t ");
		sql.append(" left join sc_image t1 on t.image_id=t1.id ");
		sql.append(" where t.goods_id=? ");
		sql.append(" and   t.flag=? ");
		
		return DBUtil.getInstance().queryBySQL(sql.toString(), goodsId, FlagEnum.ACT.getCode());
	}
	
	public ScGoods queryPojoById(String id) {
		return (ScGoods) DBUtil.getInstance().get(ScGoods.class, id);
	}
	
	public int deleteByGoodsId(String goodsId) {
		return DBUtil.getInstance().executeSQL(" delete from sc_goods_image t where t.goods_id=? ", goodsId);
	}
}