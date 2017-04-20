package com.sunchin.shop.admin.afterManagement.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.afterManagement.dao.AfterDAO;
import com.sunchin.shop.admin.afterManagement.service.IAfterService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScBill;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("afterSerivce")
public class AfterSerivceImpl implements IAfterService{

	@Resource(name="afterDAO")
	private AfterDAO afterDAO;

	/**
	 * 查询
	 */
	@Override
	public PageBean queryBill(PageBean pageBean) throws Exception {
		int total = afterDAO.queryBillCount(pageBean);
		pageBean.setTotal(total);
		List<ScBill> pageData = afterDAO.queryBillPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 查询详情
	 */
	@Override
	public List<Map<String,Object>> findBillTetail(String id) throws Exception {
		List<Map<String,Object>> lists = afterDAO.queryBillById(id);
			if(lists !=null && !lists.isEmpty()){
				return lists;
			}
		return null;
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteBill(String id) throws Exception {
		ScBill bill = afterDAO.findBillById(id);
		bill.setFlag(FlagEnum.HIS.getCode());
		DBUtil.getInstance().update(bill);
	}

	/**
	 * 退货通过
	 */
	@Override
	public void passReturnGoods(String id, String result) throws Exception {
		
		
	}

	/**
	 * 退货不通过
	 */
	@Override
	public void NopassReturnGoods(String id, String result) throws Exception {
		
		
	}
	
	
}
