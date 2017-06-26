package com.sunchin.shop.admin.logisticsSetting.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.IsuseEnum;
import com.sunchin.shop.admin.logisticsSetting.dao.LogisticsSettingDAO;
import com.sunchin.shop.admin.logisticsSetting.service.ILogisticsSettingService;
import com.sunchin.shop.admin.pojo.ScExpressProvider;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("logisticsSettingService")
public class LogisticsSettingServiceImpl implements ILogisticsSettingService{

	@Resource(name="logisticsSettingDAO")
	private LogisticsSettingDAO logisticsSettingDAO;

	@Override
	public PageBean queryLogisticsList(PageBean pageBean) throws Exception {
		int total = logisticsSettingDAO.queryLogisticsCount(pageBean);
		pageBean.setTotal(total);
		List<ScExpressProvider> pageData = logisticsSettingDAO.queryLogisticsPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	@Override
	@Transactional
	public void saveExpressProvider(ScExpressProvider logistics) throws Exception {
		if(logistics == null){
			return;
		}
		DBUtil db = DBUtil.getInstance();
		if(StringUtils.isBlank(logistics.getId())){
			String id = UUID.randomUUID().toString();
			logistics.setId(id);
			logistics.setIsuse(IsuseEnum.INVALID.getCode());
			logistics.setFlag(FlagEnum.ACT.getCode());
			logistics.setCreateTime(new Date());
			db.insert(logistics);
		}else{
			ScExpressProvider vo = (ScExpressProvider)db.get(ScExpressProvider.class,logistics.getId());
			vo.setExpressType(logistics.getExpressType());
			vo.setForShort(logistics.getForShort());
			vo.setCode(logistics.getCode());
			vo.setEname(logistics.getEname());
			vo.setUrl(logistics.getUrl());
			vo.setFullName(logistics.getFullName());
			vo.setUpdateTime(new Date());
			db.update(vo);
		}
	}

	/**
	 * 查询单条记录
	 */
	@Override
	public ScExpressProvider editExpress(String id) throws Exception {
		Object obj = DBUtil.getInstance().get(ScExpressProvider.class, id);
		if(obj != null) {
			return (ScExpressProvider) obj;
		}
		return null;
	}

	/**
	 * 是否设置默认
	 */
	@Override
	@Transactional
	public void saveIsuse(String id) throws Exception {
		DBUtil db = DBUtil.getInstance();
		List<ScExpressProvider> expressList = logisticsSettingDAO.queryLogisticsList();
		if(expressList != null && !expressList.isEmpty()){
			for(int i=0;i<expressList.size();i++){
				ScExpressProvider experess = expressList.get(i);
				experess.setIsuse(IsuseEnum.INVALID.getCode());
				db.update(experess);
			}
		}
		if(id != null && !id.equals("")){
			ScExpressProvider vo = (ScExpressProvider)db.get(ScExpressProvider.class, id);
			vo.setIsuse(IsuseEnum.VALID.getCode());
			db.update(vo);
		}
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteExpress(String id) throws Exception {
		ScExpressProvider express = logisticsSettingDAO.queryExpressById(id);
		express.setFlag(FlagEnum.HIS.getCode());
		DBUtil.getInstance().update(express);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryAllCompany() throws Exception {
		List<Map> list = logisticsSettingDAO.queryAllCompany();
		return list;
	}
}

