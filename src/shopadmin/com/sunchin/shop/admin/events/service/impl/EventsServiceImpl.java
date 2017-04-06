package com.sunchin.shop.admin.events.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import com.sunchin.shop.admin.pojo.ScEventsGoods;

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
		ScEvents eventsinfo = eventsDAO.findScEventsById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = format.format(eventsinfo.getStartTime());
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
	 * 编辑
	 */
	@Override
	public List<Map<String, Object>> queryEvents(String id) {
			return eventsDAO.queryEvents(id);
	}
	
	/**
	 * 保存
	 */
	@Override
	@Transactional
	public void saveEvents(ScEvents events,String eventsGoodsList) {
		if (events == null) {
			return;
		}
		List<String> lists = new ArrayList<>();
		String [] news = eventsGoodsList.split(",");
		for(int i=0;i<news.length;i++){
				String goodsId = news[i];
				if(goodsId !=""){
					lists.add(goodsId);
				}
		}
		
		DBUtil db = DBUtil.getInstance();
		// 新增
		if (StringUtils.isBlank(events.getId())) {
			String id = UUID.randomUUID().toString();
			events.setId(id);
			events.setCreateTime(new Date());
			events.setFlag(FlagEnum.ACT.getCode());
			db.insert(events);
			if(lists != null && !lists.isEmpty()){
				for(int i=0;i<lists.size();i++){
					  ScEventsGoods eventsGoods = new ScEventsGoods();
					  String goodsId = lists.get(i);
					  String ID = UUID.randomUUID().toString();
					  eventsGoods.setId(ID);
					  eventsGoods.setEventsId(id);
					  eventsGoods.setGoodsId(goodsId);
					  db.insert(eventsGoods);
				}
			}
		} else { // 修改
			ScEvents vo = (ScEvents) db.get(ScEvents.class, events.getId());
			vo.setName(events.getName());
			vo.setIsuse(events.getIsuse());
			vo.setMemo(events.getMemo());
			vo.setStartTime(events.getStartTime());
			vo.setEndTime(events.getEndTime());
			vo.setUpdateTime(new Date());
			db.update(vo);
			
		}
	}
}
