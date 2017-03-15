package com.sunchin.shop.admin.userManagement.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.pojo.ScUser;
import com.sunchin.shop.admin.userManagement.service.IUserService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class UserAction extends PageAction{

	@Resource(name="userService")
	private IUserService userService;
	
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
	
	
	public String resetPassword(){
		userService.resetPassword(user.getId());
		return Action.SUCCESS;
	}
	
	
	
	
	
	
	
	
	public ScUser getUser() {
		return user;
	}


	public void setUser(ScUser user) {
		this.user = user;
	}
}
