package com.sunchin.shop.admin.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.pojo.ScAttachments;

import com.sunchin.shop.admin.dict.FlagEnum;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("scAttachmentsDAO")
public class ScAttachmentsDAO extends PageDAO {
	/**
	 * 根据名称查询
	 * @param pageBean
	 * @return pageData
	 */
	public final String SELECT_SQL = " select t.id,t.kind,d.name as kind_name,t.attach_name,t.file_name, "
			+ " t.attach_type,t.abs_path,t.attach_path,t.parent_id,t.create_user_id,t.attach_size,t.belong, "
			+ " t.remark,to_char(t.create_time,'yyyy-mm-dd hh24:mi:ss') create_time,t.flag from sc_attachments t "
			+ " left join sc_dictionary d on t.kind=d.code and d.type='ATTACH_TYPE' "
			+ " where t.flag=? and d.flag=? ";
	
	@SuppressWarnings("unchecked")
	public List<ScAttachments> queryAttachsPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		List<ScAttachments> pageData = this.query(sql, params, DBUtil.getInstance(), pageBean);
		return pageData;
	}
	
	public int queryAttachsCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}
	
	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(SELECT_SQL);
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String bankName = pageBean.getQueryParams().get("bankName");
			if (StringUtils.isNotBlank(bankName)){
				params.add("%"+bankName+"%");
				sql.append(" and t.bank_name like ? ");
			}
			String bankDesc = pageBean.getQueryParams().get("bankDesc");
			if (StringUtils.isNotBlank(bankDesc)){
				params.add("%"+bankDesc+"%");
				sql.append(" and t.bank_desc like ? ");
			}
		}
		sql.append(" order by t.create_time desc ");
		return sql.toString();
	}
}
