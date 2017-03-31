package com.sunchin.shop.admin.userManagement.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScIdentity;
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
	private List<ScDictionary> userStatusList; //账户状态
	private List<ScDictionary> identityStatus; //身份证状态
	private List<ScDictionary> userSexList; //性别
	
	private ScUser user;
	private ScIdentity identity;
	
	
	/**
	 * 查询会员基础信息
	 */
	public String queryUserBase(){
		try {
			PageBean resultData = userService.queryUserBaseList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
	
	/**
	 * 查询会员安全信息
	 */
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
	
	/**
	 * 查询会员交易流水
	 */
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
	
	/**
	 * 查询会员身份认证信息
	 */
	public String queryUserIdentity(){
		try {
			PageBean resultData = userService.queryUserIdentityList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	/**
	 * 查询会员钱包余额
	 */
	public String queryUserWallet(){
		try {
			PageBean resultData = userService.queryUserWalletList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return Action.SUCCESS;
	}
	
	/**
	 * 自动认证
	 */
	public String automaticSuccess(){
		//自动认证待讨论
		return Action.SUCCESS;
	}
	
	/**
	 * 认证成功
	 */
	public String authenticationSuccess(){
		try {
			userService.alterStatus(identity.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 *  认证失败 
	 */
	public String saveFailurereason(){
		try {
			userService.saveFailurereason(identity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询身份认证单条记录
	 */
	public String findIdentity(){
		try {
			identity = userService.findIdentity(identity.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	public String resetPassword(){
		try {
			userService.resetPassword(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询数据字典类型
	 * @return
	 */
	public String queryType(){
		try {
			tradeTypeList  = dictService.getDictByType(DictionaryTypeEnum.TRADE_TYPE.getType());
			tradeStateList = dictService.getDictByType(DictionaryTypeEnum.TRADE_STATE.getType());
			optionTypeList = dictService.getDictByType(DictionaryTypeEnum.OPTION_TYPE.getType());
			purseTypeList  = dictService.getDictByType(DictionaryTypeEnum.PURSE_TYPE.getType());
			userStatusList = dictService.getDictByType(DictionaryTypeEnum.USER_STATUS.getType());
			identityStatus  = dictService.getDictByType(DictionaryTypeEnum.IDENTITY_STATUS.getType());
			userSexList  = dictService.getDictByType(DictionaryTypeEnum.USER_SEX.getType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public List<ScDictionary> getUserStatusList() {
		return userStatusList;
	}

	public void setUserStatusList(List<ScDictionary> userStatusList) {
		this.userStatusList = userStatusList;
	}

	public List<ScDictionary> getIdentityStatus() {
		return identityStatus;
	}

	public void setIdentityStatus(List<ScDictionary> identityStatus) {
		this.identityStatus = identityStatus;
	}

	public List<ScDictionary> getUserSexList() {
		return userSexList;
	}

	public void setUserSexList(List<ScDictionary> userSexList) {
		this.userSexList = userSexList;
	}

	public ScIdentity getIdentity() {
		return identity;
	}

	public void setIdentity(ScIdentity identity) {
		this.identity = identity;
	}

}
