package com.sunchin.shop.admin.userManagement.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.pojo.ScBcuser;
import com.sunchin.shop.admin.pojo.ScPurse;
import com.sunchin.shop.admin.pojo.ScUser;
import com.sunchin.shop.admin.userManagement.dao.UserDAO;
import com.sunchin.shop.admin.userManagement.service.IUserService;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.MD5Utils;

@Repository("userService")
public class UserServiceImpl implements IUserService{

	@Resource(name="userDAO")
	private UserDAO userDAO;

	@Override
	public PageBean queryUserBcuserList(PageBean pageBean) {
		int total = userDAO.queryUserBcuserCount(pageBean);
		pageBean.setTotal(total);
		List<ScBcuser> pageData = userDAO.queryUserBcuserPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	@Override
	public PageBean queryUserList(PageBean pageBean) {
		int total = userDAO.queryUserCount(pageBean);
		pageBean.setTotal(total);
		List<ScUser> pageData = userDAO.queryUserPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	@Override
	@Transactional
	public void resetPassword(String id) {
		ScUser user = userDAO.queryUserById(id);
		user.setLoginPwd(MD5Utils.getMD5("123456"));
		user.setOptionTime(new Date());
		DBUtil.getInstance().update(user);
	}

	@Override
	public PageBean queryUserPurseList(PageBean pageBean) throws Exception {
		int total = userDAO.queryUserPurseCount(pageBean);
		pageBean.setTotal(total);
		List<ScPurse> pageData = userDAO.queryUserPursePagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
}
