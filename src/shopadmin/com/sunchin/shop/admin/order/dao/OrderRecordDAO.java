package com.sunchin.shop.admin.order.dao;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScOrderRecord;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("orderRecordDAO")
public class OrderRecordDAO extends PageDAO{

	@Resource(name="dbUtil")
	private DBUtil db;
	
	public void addOrderRecord(ScOrderRecord orderRecord){
		orderRecord.setId(UUID.randomUUID().toString());
		orderRecord.setCreateTime(new Date());
		orderRecord.setFlag(FlagEnum.ACT.getCode());
		db.insert(orderRecord);
	}
	
}
