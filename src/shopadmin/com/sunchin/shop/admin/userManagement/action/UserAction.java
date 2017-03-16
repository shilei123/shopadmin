package com.sunchin.shop.admin.userManagement.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScUser;
import com.sunchin.shop.admin.system.service.DictService;
import com.sunchin.shop.admin.userManagement.service.IUserService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class UserAction extends PageAction{

	@Resource(name="userService")
	private IUserService userService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private List<ScDictionary> tradeTypeList;  //交易类型
	private List<ScDictionary> tradeStateList; //交易状态
	private List<ScDictionary> optionTypeList; //操作类型
	private List<ScDictionary> purseTypeList;  //钱包类型
	
	private ScUser user;

	public String queryUserBcuser(){
		try {
			PageBean resultData = userService.queryUserBcuserList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
	
	
	public String queryUser(){
		try {
			PageBean resultData = userService.queryUserList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String queryUserPurse(){
		try {
			PageBean resultData = userService.queryUserPurseList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
	
	public String resetPassword(){
		try {
			userService.resetPassword(user.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String queryType(){
		tradeTypeList  = dictService.findDictionaryType("TRADE_TYPE");
		tradeStateList = dictService.findDictionaryType("TRADE_STATE");
		optionTypeList = dictService.findDictionaryType("OPTION_TYPE");
		purseTypeList  = dictService.findDictionaryType("PURSE_TYPE");
		return Action.SUCCESS;
	}
	
	public ScUser getUser() {
		return user;
	}


	public void setUser(ScUser user) {
		this.user = user;
	}


	public List<ScDictionary> getTradeTypeList() {
		return tradeTypeList;
	}


	public void setTradeTypeList(List<ScDictionary> tradeTypeList) {
		this.tradeTypeList = tradeTypeList;
	}


	public List<ScDictionary> getTradeStateList() {
		return tradeStateList;
	}


	public void setTradeStateList(List<ScDictionary> tradeStateList) {
		this.tradeStateList = tradeStateList;
	}


	public List<ScDictionary> getOptionTypeList() {
		return optionTypeList;
	}


	public void setOptionTypeList(List<ScDictionary> optionTypeList) {
		this.optionTypeList = optionTypeList;
	}


	public List<ScDictionary> getPurseTypeList() {
		return purseTypeList;
	}


	public void setPurseTypeList(List<ScDictionary> purseTypeList) {
		this.purseTypeList = purseTypeList;
	}
	
	
}
