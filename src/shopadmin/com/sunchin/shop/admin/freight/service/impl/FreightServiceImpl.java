package com.sunchin.shop.admin.freight.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.freight.dao.FreightDAO;
import com.sunchin.shop.admin.freight.service.IFreightService;
import com.sunchin.shop.admin.pojo.ScBcuser;
import com.sunchin.shop.admin.pojo.ScFreight;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("freightService")
public class FreightServiceImpl implements IFreightService{

	@Resource(name="freightDAO")
	private FreightDAO freightDAO;

	@Override
	public PageBean queryFreightList(PageBean pageBean) throws Exception {
		int total = freightDAO.queryFreightCount(pageBean);
		pageBean.setTotal(total);
		List<ScFreight> pageData = freightDAO.queryFreightPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	@Override
	public List<Map<String, Object>> findFreightList(String id) throws Exception {
		List<Map<String, Object>> lists = freightDAO.findFreightList(id);
		return lists;
	}

	
	@Override
	@Transactional
	public void deleteFreight(String id) throws Exception {
		ScFreight freight =freightDAO.findFreight(id);
		freight.setFlag(FlagEnum.HIS.getCode());
		DBUtil.getInstance().update(freight);
	}

	
	
	
}
