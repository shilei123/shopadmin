package com.sunchin.shop.admin.propValue.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScPropValue;
import com.sunchin.shop.admin.propValue.dao.PropValueDAO;
import com.sunchin.shop.admin.propValue.service.PropValueService;

import framework.bean.PageBean;
import framework.config.SysDict;
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
		List<ScPropValue> pageData = propValueDAO.queryPropValuePagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	@Override
	public void delPropValue(String id) throws Exception {
<<<<<<< HEAD
		List<ScPropValue> list = propValueDAO.getPropValue(id);
=======
>>>>>>> refs/remotes/origin/yangchaowen
		if(list!=null && !list.isEmpty()){
			String hql = " update ScPropValue set flag=? where id=? ";
			db.executeHql(hql, SysDict.FLAG_HIS, id);
		}
	}
	
	@Override
	public void updatePropValue(ScPropValue propValue) throws Exception {
		ScPropValue val = (ScPropValue) db.get(ScPropValue.class, propValue.getId());
		val.setValName(propValue.getValName());
		val.setValCode(propValue.getValCode());
		val.setValOrder(propValue.getValOrder());
		val.setFlag(propValue.getFlag());
		db.update(val);
	}
	
	/**
	 * 新增
	 */
	@Override
	public void addPropValue(ScPropValue propValue) throws Exception {
		propValue.setId(UUID.randomUUID().toString());
		propValue.setCreateTime(new Date());
		propValue.setFlag(FlagEnum.ACT.getCode());
		db.insert(propValue);
	}

	@Override
	public ScPropValue queryPropValue(String id) throws Exception {
		Object obj = db.get(ScPropValue.class, id);
		if(obj != null) {
			return (ScPropValue) obj;
		}
		return null;
	}

}
