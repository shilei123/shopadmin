package com.sunchin.shop.admin.events.service.impl;

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
import com.sunchin.shop.admin.events.dao.EventsDAO;
import com.sunchin.shop.admin.events.service.IEventsService;
import com.sunchin.shop.admin.pojo.ScCoupon;
import com.sunchin.shop.admin.pojo.ScEvents;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("eventsService")
public class EventsServiceImpl implements IEventsService{

	@Resource(name="eventsDAO")
	private EventsDAO eventsDAO;

	/**
	 * 查询
	 */
	@Override
	public PageBean queryEventsList(PageBean pageBean) {
		int total = eventsDAO.queryEventsCount(pageBean);
		pageBean.setTotal(total);
		List<ScEvents> pageData = eventsDAO.queryEventsPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteEvents(String id) {
		DBUtil db = DBUtil.getInstance();
		ScEvents eventsinfo = eventsDAO.findScEventsinfoById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = format.format(eventsinfo.getStart_time());
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
	public ScEvents queryEvents(String id) {
		Object obj = DBUtil.getInstance().get(ScEvents.class, id);
		if(obj != null) {
			return (ScEvents) obj;
		}
		return null;
	}
	
	/**
	 * 保存
	 */
	@Override
	@Transactional
	public void saveEvents(ScEvents eventsinfo) {
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
			ScEvents vo = (ScEvents) db.get(ScEvents.class, eventsinfo.getId());
			vo.setName(eventsinfo.getName());
			vo.setIsuse(eventsinfo.getIsuse());
			vo.setMemo(eventsinfo.getMemo());
			vo.setStart_time(eventsinfo.getStart_time());
			vo.setEnd_time(eventsinfo.getEnd_time());
			vo.setUpdateTime(new Date());
			db.update(vo);
		}
		
	}
}
