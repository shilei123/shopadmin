package com.sunchin.shop.admin.eventsinfo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import oracle.sql.NUMBER;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.StatusEnum;
import com.sunchin.shop.admin.eventsinfo.dao.EventsinfoDAO;
import com.sunchin.shop.admin.eventsinfo.service.IEventsinfoService;
import com.sunchin.shop.admin.pojo.ScCoupon;
import com.sunchin.shop.admin.pojo.ScEventsinfo;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("eventsinfoService")
public class EventsinfoServiceImpl implements IEventsinfoService{

	@Resource(name="eventsinfoDAO")
	private EventsinfoDAO eventsinfoDAO;

	/**
	 * 查询
	 */
	@Override
	public PageBean queryEventsinfoList(PageBean pageBean) {
		int total = eventsinfoDAO.queryEventsinfoCount(pageBean);
		pageBean.setTotal(total);
		List<ScEventsinfo> pageData = eventsinfoDAO.queryEventsinfoPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteEventsinfo(String id) {
		DBUtil db = DBUtil.getInstance();
		ScEventsinfo eventsinfo = eventsinfoDAO.findScEventsinfoById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = format.format(eventsinfo.getStarttime());
		String currentTime = format.format(new Date());
		int i = startTime.compareTo(currentTime);
		if(i > 0){
			db.delete(eventsinfo);
		}else{
			eventsinfo.setFlag(FlagEnum.HIS.getCode());
			db.update(eventsinfo);
		}
	}

	/**
	 * 查询单条记录
	 */
	@Override
	public ScEventsinfo queryEventsinfo(String id) {
		Object obj = DBUtil.getInstance().get(ScEventsinfo.class, id);
		if(obj != null) {
			return (ScEventsinfo) obj;
		}
		return null;
	}
	
	/**
	 * 保存
	 */
	@Override
	@Transactional
	public void saveEventsinfo(ScEventsinfo eventsinfo) {
		if (eventsinfo == null) {
			return;
		}
		DBUtil db = DBUtil.getInstance();
		// 新增
		if (StringUtils.isBlank(eventsinfo.getId())) {
			String id = UUID.randomUUID().toString();
			eventsinfo.setId(id);
			eventsinfo.setCreateTime(new Date());
			eventsinfo.setFlag(FlagEnum.ACT.getCode());
			db.insert(eventsinfo);
			//添加商品待写
		} else { // 修改
			ScEventsinfo vo = (ScEventsinfo) db.get(ScEventsinfo.class, eventsinfo.getId());
			vo.setName(eventsinfo.getName());
			vo.setIsuse(eventsinfo.getIsuse());
			vo.setMemo(eventsinfo.getMemo());
			vo.setStarttime(eventsinfo.getStarttime());
			vo.setEndtime(eventsinfo.getEndtime());
			vo.setUpdateTime(new Date());
			db.update(vo);
		}
		
	}
}
