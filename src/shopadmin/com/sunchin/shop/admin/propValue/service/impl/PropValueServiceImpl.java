package com.sunchin.shop.admin.propValue.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScPropval;
import com.sunchin.shop.admin.propValue.dao.PropValueDAO;
import com.sunchin.shop.admin.propValue.service.PropValueService;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Service("propValueService")
public class PropValueServiceImpl implements PropValueService {

	@Resource(name="propValueDAO")
	private PropValueDAO propValueDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	
	@Override
	public PageBean queryPropValueList(PageBean pageBean) throws Exception {
		int total = propValueDAO.queryPropValueCount(pageBean);
		pageBean.setTotal(total);
		List<ScPropval> pageData = propValueDAO.queryPropValuePagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	@Override
	public void delPropValue(String id) throws Exception {
		List<ScPropval> list = propValueDAO.getPropValue(id);
		if(list!=null && !list.isEmpty()){
			propValueDAO.delPropValue(id);
		}
	}
	
	@Override
	public void updatePropValue(ScPropval propValue) throws Exception {
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
	public void addPropValue(ScPropval propValue) throws Exception {
		propValue.setId(UUID.randomUUID().toString());
		propValue.setCreateTime(new Date());
		propValue.setFlag(FlagEnum.ACT.getCode());
		db.insert(propValue);
	}

	@Override
	public ScPropval queryPropValue(String id) throws Exception {
		Object obj = db.get(ScPropval.class, id);
		if(obj != null) {
			return (ScPropval) obj;
		}
		return null;
	}

}
