package com.sunchin.shop.admin.advertise.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.advertise.dao.AdvertiseDAO;
import com.sunchin.shop.admin.advertise.service.IAdvertiseService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScAdvertise;
import com.sunchin.shop.admin.pojo.ScBcuser;
import com.sunchin.shop.admin.pojo.ScEventsinfo;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("advertiseService")
public class AdvertiseServiceImpl implements IAdvertiseService{

	@Resource(name="advertiseDAO")
	private AdvertiseDAO advertiseDAO;

	/**
	 * 查询
	 */
	@Override
	public PageBean queryAdvertiseList(PageBean pageBean) {
		int total = advertiseDAO.queryAdvertiseCount(pageBean);
		pageBean.setTotal(total);
		List<ScBcuser> pageData = advertiseDAO.queryAdvertisefoPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteAdvertise(String id) {
		DBUtil db = DBUtil.getInstance();
		ScAdvertise advertise = advertiseDAO.findScAdvertiseById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = format.format(advertise.getStartTime());
		String currentTime = format.format(new Date());
		int i = startTime.compareTo(currentTime);
		if(i > 0){
			db.delete(advertise);
		}else{
			advertise.setFlag(FlagEnum.HIS.getCode());
			db.update(advertise);
		}
		
	}

	/**
	 * 保存
	 */
	@Override
	@Transactional
	public void saveAdvertise(ScAdvertise advertise) {
		if (advertise == null) {
			return;
		}
		DBUtil db = DBUtil.getInstance();
		// 新增
		if (StringUtils.isBlank(advertise.getId())) {
			String id = UUID.randomUUID().toString();
			advertise.setId(id);
			advertise.setCreateTime(new Date());
			advertise.setFlag(FlagEnum.ACT.getCode());
			db.insert(advertise);
			//添加商品待写
		} else { // 修改
			ScAdvertise vo = (ScAdvertise) db.get(ScAdvertise.class, advertise.getId());
			vo.setName(advertise.getName());
			vo.setMemo(advertise.getMemo());
			vo.setOrdernumb(advertise.getOrdernumb());
			vo.setType(advertise.getType());
			vo.setIsuse(advertise.getIsuse());
			vo.setStartTime(advertise.getStartTime());
			vo.setEndTime(advertise.getEndTime());
			vo.setKind(advertise.getKind());
			vo.setLinkkind(advertise.getLinkkind());
			vo.setImglink(advertise.getImglink());
			vo.setUpdateTime(new Date());
			db.update(vo);
		}
	}
	
	/**
	 * 查询单条记录
	 */
	@Override
	public ScAdvertise queryAdvertise(String id) {
		Object obj = DBUtil.getInstance().get(ScAdvertise.class, id);
		if(obj != null) {
			return (ScAdvertise) obj;
		}
		return null;
		
	}

	@Override
	public Map<String, Object> findAdvertise(String id) {
		Map<String, Object> map = advertiseDAO.findAdvertiseList(id);
		if(map == null){
			return null;
		}
		return map;
	}
}
