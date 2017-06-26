package com.sunchin.shop.admin.invoice.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScInvoiceRecord;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("invoiceRecordDAO")
public class InvoiceRecordDAO extends PageDAO {
	
	@Resource(name="dbUtil")
	private DBUtil db;
	
	@SuppressWarnings("unchecked")
	public ScInvoiceRecord queryById(String id){
		List<ScInvoiceRecord> list = db.queryByHql(" from ScInvoiceRecord where id=? and flag=? ", id, FlagEnum.ACT.getCode());
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 订单拆分
	 * @param id
	 */
	public void delInvoiceRecordById(String id){
		db.executeHql(" update ScInvoiceRecord set flag=? where id=? ", FlagEnum.ACT.getCode(), id);
	}
	
	/**
	 * 确认订单插入发票税务编号
	 * @param invoiceCode
	 * @param id
	 */
	public void updateInvoiceCode(String invoiceCode, String id) {
		String hql = " update ScInvoiceRecord set invoiceCode=? where flag=? and id=? ";
		db.executeHql(hql, invoiceCode, FlagEnum.ACT.getCode(), id);
	}
}