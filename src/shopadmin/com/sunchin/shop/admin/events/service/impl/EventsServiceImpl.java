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
import org.springframework.jdbc.core.metadata.Db2CallMetaDataProvider;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.StatusEnum;
import com.sunchin.shop.admin.events.dao.EventsDAO;
import com.sunchin.shop.admin.events.service.IEventsService;
import com.sunchin.shop.admin.pojo.ScChildGoods;
import com.sunchin.shop.admin.pojo.ScCoupon;
import com.sunchin.shop.admin.pojo.ScEvents;
import com.sunchin.shop.admin.pojo.ScEventsGoods;
import com.sunchin.shop.admin.pojo.ScGoods;
import com.sunchin.shop.admin.pojo.ScGoodsCatePropPropVal;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.CommonUtils;

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
		ScEvents events = eventsDAO.findScEventsById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = format.format(events.getStartTime());
		String currentTime = format.format(new Date());
		int i = startTime.compareTo(currentTime);
		if(i > 0){
			db.delete(events);
		}else{
			events.setFlag(FlagEnum.HIS.getCode());
			db.update(events);
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
		if(StringUtils.isBlank(events.getId())) {
			this.save(events,eventsGoodsList);
		} else {
			this.update(events,eventsGoodsList);
		}
	}
	
	public void save(ScEvents events,String eventsGoodsList){
		JSONArray childGoodsArr = null;
		if(StringUtils.isNotBlank(eventsGoodsList)){
			childGoodsArr = JSON.parseArray(eventsGoodsList);
		}
		
		DBUtil db = DBUtil.getInstance();
		String id = UUID.randomUUID().toString();
		events.setId(id);
		events.setCreateTime(new Date());
		events.setFlag(FlagEnum.ACT.getCode());
		db.insert(events);
		
		if(childGoodsArr != null && !childGoodsArr.isEmpty()) {
			List<ScEventsGoods> childGoodsList = new ArrayList<ScEventsGoods>(childGoodsArr.size());
            
			for(int i = 0; i < childGoodsArr.size(); i++) {
	            JSONObject goodsChildJson = (JSONObject) childGoodsArr.get(i);
	            String childGoodsId = CommonUtils.getString(goodsChildJson.get("childGoodsId"));
	            Double goodsPrice = CommonUtils.getDouble(goodsChildJson.get("goodsPrice"));
	            String goodsRange = CommonUtils.getString(goodsChildJson.get("goodsRange"));
	            ScEventsGoods childEventsGoodsVo = new ScEventsGoods();
	            childEventsGoodsVo.setGoodsId(childGoodsId);
	            childEventsGoodsVo.setEventsMoney(goodsPrice);
	            childEventsGoodsVo.setScope(goodsRange);
	            childGoodsList.add(childEventsGoodsVo);
			}
			
			for(int i=0;i<childGoodsList.size();i++){
				ScEventsGoods eventsGoods = new ScEventsGoods();
				ScEventsGoods  vo = (ScEventsGoods) childGoodsList.get(i);
				String ID = UUID.randomUUID().toString();
				eventsGoods.setId(ID);
				eventsGoods.setEventsId(id);
				eventsGoods.setGoodsId(vo.getGoodsId());
				eventsGoods.setGoodsChildId(vo.getGoodsChildId());
				eventsGoods.setEventsMoney(vo.getEventsMoney());
				eventsGoods.setScope(vo.getScope());
				DBUtil.getInstance().insert(eventsGoods);
			}
		}
	}
	
	public void update(ScEvents events,String eventsGoodsList){
		ScEvents vo = (ScEvents) DBUtil.getInstance().get(ScEvents.class, events.getId());
		vo.setName(events.getName());
		vo.setIsuse(events.getIsuse());
		vo.setMemo(events.getMemo());
		vo.setStartTime(events.getStartTime());
		vo.setEndTime(events.getEndTime());
		vo.setUpdateTime(new Date());
		DBUtil.getInstance().update(vo);
		
		JSONArray childGoodsArr = null;
		if(StringUtils.isNotBlank(eventsGoodsList)){
			childGoodsArr = JSON.parseArray(eventsGoodsList);
		}
		
		if(childGoodsArr != null && !childGoodsArr.isEmpty()) {
			List<ScEventsGoods> childGoodsList = new ArrayList<ScEventsGoods>(childGoodsArr.size());
            
			for(int i = 0; i < childGoodsArr.size(); i++) {
	            JSONObject goodsChildJson = (JSONObject) childGoodsArr.get(i);
	            String childGoodsId = CommonUtils.getString(goodsChildJson.get("childGoodsId"));
	            Double goodsPrice = CommonUtils.getDouble(goodsChildJson.get("goodsPrice"));
	            String goodsRange = CommonUtils.getString(goodsChildJson.get("goodsRange"));
	            String eventsGoodsId = CommonUtils.getString(goodsChildJson.get("eventsGoodsId"));
	            ScEventsGoods childEventsGoodsVo = new ScEventsGoods();
	            if(!eventsGoodsId.equals("")){
	            	 childEventsGoodsVo.setId(eventsGoodsId);
	            }
	            childEventsGoodsVo.setEventsId(events.getId());
	            childEventsGoodsVo.setGoodsId(childGoodsId);
	            childEventsGoodsVo.setEventsMoney(goodsPrice);
	            childEventsGoodsVo.setScope(goodsRange);
	            childGoodsList.add(childEventsGoodsVo);
			}
			for(int i=0;i<childGoodsList.size();i++){
				ScEventsGoods eventsGoods = new ScEventsGoods();
				ScEventsGoods  vo1 = (ScEventsGoods) childGoodsList.get(i);
				if(vo1.getId() != null){
					DBUtil.getInstance().update(vo1);
				}else{
					String ID = UUID.randomUUID().toString();
					eventsGoods.setId(ID);
					eventsGoods.setEventsId(events.getId());
					eventsGoods.setGoodsId(vo1.getGoodsId());
					eventsGoods.setGoodsChildId(vo1.getGoodsChildId());
					eventsGoods.setEventsMoney(vo1.getEventsMoney());
					eventsGoods.setScope(vo1.getScope());
					DBUtil.getInstance().insert(eventsGoods);
				}
			}
		}
	}

	/**
	 * 删除活动商品
	 */
	@Override
	public void deleteEventsGoods(String id) throws Exception {
		ScEventsGoods vo = (ScEventsGoods) DBUtil.getInstance().get(ScEventsGoods.class, id);
		DBUtil.getInstance().delete(vo);
	}
}
