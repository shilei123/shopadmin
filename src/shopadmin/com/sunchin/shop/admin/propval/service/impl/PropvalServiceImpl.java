package com.sunchin.shop.admin.propval.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScPropval;
import com.sunchin.shop.admin.propval.dao.PropvalDAO;
import com.sunchin.shop.admin.propval.service.PropvalService;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Service("propvalService")
public class PropvalServiceImpl implements PropvalService {

	@Resource(name="propvalDAO")
	private PropvalDAO propvalDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	
	@Override
	public PageBean queryPropvalList(PageBean pageBean) throws Exception {
		int total = propvalDAO.queryPropvalCount(pageBean);
		pageBean.setTotal(total);
		List<ScPropval> pageData = propvalDAO.queryPropvalPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	@Override
	public void delPropval(String id) throws Exception {
		List<ScPropval> list = propvalDAO.getPropval(id);
		if(list!=null && !list.isEmpty()){
			propvalDAO.delPropval(id);
		}
	}
	
	@Override
	public void updatePropval(ScPropval propValue) throws Exception {
		ScPropval val = (ScPropval) db.get(ScPropval.class, propValue.getId());
		val.setValName(propValue.getValName());
		val.setValCode(propValue.getValCode());
		val.setOrder(propValue.getOrder());
		val.setFlag(propValue.getFlag());
		db.update(val);
	}
	
	/**
	 * 新增
	 */
	@Override
	public String addPropval(ScPropval propval) throws Exception {
		ScPropval propval_db = propvalDAO.queryPropvalByValName(propval.getValName());
		if(propval_db!=null)
			return "该属性名已经存在，请重新输入！";
		propval.setId(UUID.randomUUID().toString());
		propval.setCreateTime(new Date());
		propval.setFlag(FlagEnum.ACT.getCode());
		db.insert(propval);
		return "";
	}

	@Override
	public ScPropval queryPropval(String id) throws Exception {
		Object obj = db.get(ScPropval.class, id);
		if(obj != null) {
			return (ScPropval) obj;
		}
		return null;
	}

}
