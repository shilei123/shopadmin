package com.sunchin.shop.admin.property.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScProperty;
import com.sunchin.shop.admin.property.dao.PropertyDAO;
import com.sunchin.shop.admin.property.service.PropertyService;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Service("propertyService")
public class PropertyServiceImpl implements PropertyService {

	@Resource(name="propertyDAO")
	private PropertyDAO propertyDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	
	@Override
	public PageBean queryPropertyList(PageBean pageBean) throws Exception {
		int total = propertyDAO.queryPropertyCount(pageBean);
		pageBean.setTotal(total);
		List<Map<String, Object>> pageData = propertyDAO.queryPropertyPagination(pageBean);

		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	@Override
	public void delProperty(String id) throws Exception {
		List<ScProperty> list = propertyDAO.getProp(id);
		if(list!=null && !list.isEmpty()){
			propertyDAO.delProp(id);
		}
	}
	
	@Override
	public void updateProperty(ScProperty property) throws Exception {
		ScProperty prop = (ScProperty) db.get(ScProperty.class, property.getId());
		prop.setPropName(property.getPropName());
		prop.setPropCode(property.getPropCode());
		prop.setOrder(property.getOrder());
		prop.setFlag(property.getFlag());
		db.update(prop);
	}
	
	/**
	 * 新增
	 */
	@Override
	public void addProperty(ScProperty property) throws Exception {
		property.setId(UUID.randomUUID().toString());
		property.setCreateTime(new Date());
		property.setFlag(FlagEnum.ACT.getCode());
		db.insert(property);
	}

	@Override
	public ScProperty queryProperty(String id) throws Exception {
		Object obj = db.get(ScProperty.class, id);
		if(obj != null) {
			return (ScProperty) obj;
		}
		return null;
	}

}
