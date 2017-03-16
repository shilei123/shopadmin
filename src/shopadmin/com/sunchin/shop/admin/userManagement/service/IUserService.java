package com.sunchin.shop.admin.userManagement.service;

import framework.bean.PageBean;

public interface IUserService {

	PageBean queryUserBcuserList(PageBean pageBean) throws Exception;

	PageBean queryUserList(PageBean pageBean) throws Exception;

	void resetPassword(String id) throws Exception;

	PageBean queryUserPurseList(PageBean pageBean) throws Exception;

}
