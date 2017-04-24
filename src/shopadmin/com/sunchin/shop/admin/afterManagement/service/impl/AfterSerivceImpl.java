package com.sunchin.shop.admin.afterManagement.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.afterManagement.dao.AfterDAO;
import com.sunchin.shop.admin.afterManagement.service.IAfterService;
import com.sunchin.shop.admin.dict.BillKindEnum;
import com.sunchin.shop.admin.dict.BillStatusEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScBill;
import com.sunchin.shop.admin.pojo.ScBillHistory;
import com.sunchin.shop.admin.pojo.ScDeliveryRecord;
import com.sunchin.shop.admin.pojo.ScRepertory;
import com.sunchin.shop.admin.pojo.ScUserBase;
import com.sunchin.shop.admin.userManagement.dao.UserDAO;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("afterSerivce")
public class AfterSerivceImpl implements IAfterService{

	@Resource(name="afterDAO")
	private AfterDAO afterDAO;
	@Resource(name="userDAO")
	private UserDAO userDAO;

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
	 * 退货通过
	 */
	@Override
	@Transactional
	public void passReturnGoods(String id, String result,String relus) throws Exception {
		if(id != null){
			ScBill bill = afterDAO.findBillById(id);//查询单据信息
			bill.setResult(result);
			bill.setBillStatus(BillStatusEnum.PASS_STATUS.getCode());
			bill.setOptionTime(new Date());
			DBUtil.getInstance().update(bill);
			ScBillHistory history = new ScBillHistory();
			history.setId(UUID.randomUUID().toString());
			history.setBillId(id);
			history.setRemark(result);
			history.setCreateTime(new Date());
			history.setFlag(FlagEnum.ACT.getCode());
			history.setBillStatus(BillStatusEnum.PASS_STATUS.getCode());
			DBUtil.getInstance().insert(history);
		}
		
		Map<String, Object> billDetail = afterDAO.queryBillTetailById(id);//查询单据详情
		String goodsId = billDetail.get("goodsId").toString();
		String goodsChildId = billDetail.get("goodsDetailId").toString();
		if(goodsChildId != null){
			ScRepertory  repertory = afterDAO.queryRepertoryByGoodsChildId(goodsChildId);//根据商品子表id,查询库存表
			if(relus.equals("0")){ //如果损坏
				if(repertory.getBadNum() != null && repertory.getSalesCount() != null){
					repertory.setBadNum(repertory.getBadNum()+1);//破损+1
					repertory.setSalesCount(repertory.getSalesCount()-1);//累计销售+1
				}else{
					repertory.setBadNum(1);
					repertory.setSalesCount(0);
				}
				
			}else if(relus.equals("1")){//如果二次销售
				if(repertory.getAvailableNum() != null && repertory.getBadNum() != null && repertory.getSalesCount() != null){
					repertory.setAvailableNum(repertory.getAvailableNum()+1);//可用+1
					repertory.setSalesNum(repertory.getSalesNum()+1);//销售+1
					repertory.setSalesCount(repertory.getSalesCount()-1);//累计销售+1
				}else{
					repertory.setAvailableNum(1);//可用+1
					repertory.setSalesNum(1);//销售+1
					repertory.setSalesCount(0);//累计销售+1
				}
			}
			DBUtil.getInstance().update(repertory);
		}else{
			ScRepertory repertory =	afterDAO.queryRepertoryByGoodsId(goodsId);//根据商品id,查询库存表
			if(relus.equals("0")){ //如果损坏
				if(repertory.getBadNum() != null && repertory.getSalesCount() != null){
					repertory.setBadNum(repertory.getBadNum()+1);
					repertory.setSalesCount(repertory.getSalesCount()-1);
				}else{
					repertory.setBadNum(1);
					repertory.setSalesCount(0);
				}
			}else if(relus.equals("1")){
				if(repertory.getAvailableNum() != null && repertory.getBadNum() != null){
					repertory.setAvailableNum(repertory.getAvailableNum()+1);
					repertory.setSalesNum(repertory.getSalesNum()+1);
				}else{
					repertory.setAvailableNum(1);
					repertory.setSalesNum(1);
				}
			}
			DBUtil.getInstance().update(repertory);
		}
}

	/**
	 * 退货不通过
	 */
	@Override
	@Transactional
	public void NopassReturnGoods(String id, String result) throws Exception {
			if(id != null){
			ScBill 	bill = afterDAO.findBillById(id);
			bill.setResult(result);
			bill.setBillStatus(BillStatusEnum.NOPASS_STATUS.getCode());
			bill.setKind(BillKindEnum.CONFIRM.getCode());
			bill.setOptionTime(new Date());
			DBUtil.getInstance().update(bill);
			ScBillHistory history = new ScBillHistory();
			history.setId(UUID.randomUUID().toString());
			history.setBillId(id);
			history.setRemark(result);
			history.setCreateTime(new Date());
			history.setFlag(FlagEnum.ACT.getCode());
			history.setBillStatus(BillStatusEnum.NOPASS_STATUS.getCode());
			DBUtil.getInstance().insert(history);
			}
	}

	/**
	 * 换货通过
	 */
	@Override
	@Transactional
	public void passChangeGoods(String id, String result, String relus) {
		if(id != null){
			ScBill bill = afterDAO.findBillById(id);//查询单据信息
			bill.setResult(result);
			bill.setBillStatus(BillStatusEnum.PASS_STATUS.getCode());
			bill.setOptionTime(new Date());
			DBUtil.getInstance().update(bill);
			ScBillHistory history = new ScBillHistory();
			history.setId(UUID.randomUUID().toString());
			history.setBillId(id);
			history.setRemark(result);
			history.setCreateTime(new Date());
			history.setFlag(FlagEnum.ACT.getCode());
			history.setBillStatus(BillStatusEnum.PASS_STATUS.getCode());
			DBUtil.getInstance().insert(history);
		}
		Map<String, Object> billDetail = afterDAO.queryBillTetailById(id);//查询单据详情
		String goodsId = billDetail.get("goodsId").toString();
		String goodsChildId = billDetail.get("goodsDetailId").toString();
		if(goodsChildId != null){
			ScRepertory  repertory = afterDAO.queryRepertoryByGoodsChildId(goodsChildId);//根据商品子表id,查询库存表
			if(relus.equals("0")){ //如果损坏
				if(repertory.getBadNum() != null && repertory.getSalesCount() != null){
					repertory.setBadNum(repertory.getBadNum()+1);//破损+1
					repertory.setSalesCount(repertory.getSalesCount()-1);//累计销售+1
				}else{
					repertory.setBadNum(1);
					repertory.setSalesCount(0);
				}
				
			}else if(relus.equals("1")){//如果二次销售
				if(repertory.getAvailableNum() != null && repertory.getBadNum() != null && repertory.getSalesCount() != null){
					repertory.setAvailableNum(repertory.getAvailableNum()+1);//可用+1
					repertory.setSalesNum(repertory.getSalesNum()+1);//销售+1
					repertory.setSalesCount(repertory.getSalesCount()-1);//累计销售+1
				}else{
					repertory.setAvailableNum(1);//可用+1
					repertory.setSalesNum(1);//销售+1
					repertory.setSalesCount(0);//累计销售+1
				}
			}
			DBUtil.getInstance().update(repertory);
		}else{
			ScRepertory repertory =	afterDAO.queryRepertoryByGoodsId(goodsId);//根据商品id,查询库存表
			if(relus.equals("0")){ //如果损坏
				if(repertory.getBadNum() != null && repertory.getSalesCount() != null){
					repertory.setBadNum(repertory.getBadNum()+1);
					repertory.setSalesCount(repertory.getSalesCount()-1);
				}else{
					repertory.setBadNum(1);
					repertory.setSalesCount(0);
				}
			}else if(relus.equals("1")){//如果二次销售
				if(repertory.getAvailableNum() != null && repertory.getBadNum() != null){
					repertory.setAvailableNum(repertory.getAvailableNum()+1);
					repertory.setSalesNum(repertory.getSalesNum()+1);
				}else{
					repertory.setAvailableNum(1);
					repertory.setSalesNum(1);
				}
			}
			DBUtil.getInstance().update(repertory);
		}
		//发货记录插入记录
		ScDeliveryRecord record = new ScDeliveryRecord();
		record.setId(UUID.randomUUID().toString());
		record.setOrderId(id);
		record.setFlag(FlagEnum.ACT.getCode());
		record.setCreateTime(new Date());
		record.setSts(FlagEnum.HIS.getCode());
		//类型待添加
		DBUtil.getInstance().insert(record);
	}

	/**
	 * 维修通过
	 */
	@Override
	@Transactional
	public void passRepairGoods(String id, String result) throws Exception {
		if(id != null){
			ScBill bill = afterDAO.findBillById(id);//查询单据信息
			bill.setResult(result);
			bill.setBillStatus(BillStatusEnum.PASS_STATUS.getCode());
			bill.setOptionTime(new Date());
			DBUtil.getInstance().update(bill);
			ScBillHistory history = new ScBillHistory();
			history.setId(UUID.randomUUID().toString());
			history.setBillId(id);
			history.setRemark(result);
			history.setCreateTime(new Date());
			history.setFlag(FlagEnum.ACT.getCode());
			history.setBillStatus(BillStatusEnum.PASS_STATUS.getCode());
			DBUtil.getInstance().insert(history);
		}
	}

	/**
	 * 退款通过
	 */
	@Override
	@Transactional
	public void passRefundGoods(String id, String result, String relus) throws Exception {
		if(id != null){
			ScBill bill = afterDAO.findBillById(id);//查询单据信息
			bill.setResult(result);
			bill.setBillStatus(BillStatusEnum.PASS_STATUS.getCode());
			bill.setOptionTime(new Date());
			DBUtil.getInstance().update(bill);
			ScBillHistory history = new ScBillHistory();
			history.setId(UUID.randomUUID().toString());
			history.setBillId(id);
			history.setRemark(result);
			history.setCreateTime(new Date());
			history.setFlag(FlagEnum.ACT.getCode());
			history.setBillStatus(BillStatusEnum.PASS_STATUS.getCode());
			DBUtil.getInstance().insert(history);
		}
		
		
	}

	/**
	 * 查询会员信息
	 */
	@Override
	public ScUserBase queryUserBase(String userId) throws Exception {
		 return userDAO.queryUserBaseByUserId(userId);
	}

	/**
	 * 查询商品信息
	 */
	@Override
	public List<Map<String, Object>> queryGoodsDetail(String id)throws Exception {
		return afterDAO.queryGoodsDetailList(id);
	}
	
}
