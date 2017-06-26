package com.sunchin.shop.admin.userManagement.service;

import com.sunchin.shop.admin.pojo.ScIdentity;

import framework.bean.PageBean;

public interface IUserService {

	PageBean queryUserBcuserList(PageBean pageBean) throws Exception;

	PageBean queryUserList(PageBean pageBean) throws Exception;

	void resetPassword(String id) throws Exception;

	PageBean queryUserPurseList(PageBean pageBean) throws Exception;

	PageBean queryUserIdentityList(PageBean pageBean) throws Exception;

	void alterStatus(String id) throws Exception;

	ScIdentity findIdentity(String id) throws Exception;

	void saveFailurereason(ScIdentity identity) throws Exception;

	PageBean queryUserWalletList(PageBean pageBean) throws Exception;;

}
