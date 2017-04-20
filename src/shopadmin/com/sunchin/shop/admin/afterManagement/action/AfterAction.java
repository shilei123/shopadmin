package com.sunchin.shop.admin.afterManagement.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.afterManagement.service.IAfterService;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.pojo.ScBill;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class AfterAction extends PageAction{

		@Resource(name="afterSerivce")
		private IAfterService afterSerivce;
		@Resource(name = "dictService")
		private DictService dictService;
		
		private List<ScDictionary> kindList;  //类型
		private ScBill bill;
		private List<Map<String,Object>> billList;
		
		/**
		 * 查询
		 * @return
		 */
		public String queryBill(){
			try {
				PageBean resultData = afterSerivce.queryBill(this.getPageBean());
				this.setTotal(resultData.getTotal());
				this.setDataRows(resultData.getPageData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Action.SUCCESS;
		}
		
		/**
		 * 查询详情
		 * @return
		 */
		public String findBillTetail(){
			try {
				billList = afterSerivce.findBillTetail(bill.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Action.SUCCESS;
		}
		
		/**
		 *退货通过
		 * @return
		 */
		public String passReturnGoods(){
			try {
				afterSerivce.passReturnGoods(bill.getId(),bill.getResult());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Action.SUCCESS;
		}
		
		/**
		 * 退货不通过
		 */
		public String noPassReturnGoods(){
			try {
				afterSerivce.NopassReturnGoods(bill.getId(),bill.getResult());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Action.SUCCESS;
		}
		
		/**
		 * 查询单据类型 
		 * @return
		 */
		public String queryBillType(){
			try {
				kindList = dictService.getDictByType(DictionaryTypeEnum.BILL_KIND.getType());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Action.SUCCESS;
		}

		/**
		 * 删除
		 * @return
		 */
		public String deleteBill(){
			try {
				afterSerivce.deleteBill(bill.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Action.SUCCESS;
		}
		
		
		
		public List<ScDictionary> getKindList() {
			return kindList;
		}

		public void setKindList(List<ScDictionary> kindList) {
			this.kindList = kindList;
		}

		public ScBill getBill() {
			return bill;
		}

		public void setBill(ScBill bill) {
			this.bill = bill;
		}

		public List<Map<String, Object>> getBillList() {
			return billList;
		}

		public void setBillList(List<Map<String, Object>> billList) {
			this.billList = billList;
		}
		
}
