package com.sunchin.shop.admin.userManagement.service;

import framework.bean.PageBean;

public interface IUserService {

	PageBean queryUserBcuserList(PageBean pageBean);

	PageBean queryUserList(PageBean pageBean);

	void resetPassword(String id);

}
