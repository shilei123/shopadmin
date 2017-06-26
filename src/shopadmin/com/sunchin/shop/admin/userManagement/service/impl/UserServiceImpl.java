package com.sunchin.shop.admin.userManagement.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.dict.IdentityStatusEnum;
import com.sunchin.shop.admin.pojo.ScBcuser;
import com.sunchin.shop.admin.pojo.ScCoupon;
import com.sunchin.shop.admin.pojo.ScIdentity;
import com.sunchin.shop.admin.pojo.ScPurse;
import com.sunchin.shop.admin.pojo.ScUser;
import com.sunchin.shop.admin.pojo.ScWallet;
import com.sunchin.shop.admin.userManagement.dao.UserDAO;
import com.sunchin.shop.admin.userManagement.service.IUserService;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.MD5Utils;

@Repository("userService")
public class UserServiceImpl implements IUserService{

	@Resource(name="userDAO")
	private UserDAO userDAO;

	/**
	 * 查询会员基础信息
	 */
	@Override
	public PageBean queryUserBcuserList(PageBean pageBean) {
		int total = userDAO.queryUserBcuserCount(pageBean);
		pageBean.setTotal(total);
		List<ScBcuser> pageData = userDAO.queryUserBcuserPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 查询会员安全信息
	 */
	@Override
	public PageBean queryUserList(PageBean pageBean) {
		int total = userDAO.queryUserCount(pageBean);
		pageBean.setTotal(total);
		List<ScUser> pageData = userDAO.queryUserPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 重置密码 默认密码：123456
	 */
	@Override
	@Transactional
	public void resetPassword(String id) {
		ScUser user = userDAO.queryUserById(id);
		user.setLoginPwd(MD5Utils.getMD5("123456"));
		user.setOptionTime(new Date());
		DBUtil.getInstance().update(user);
	}
	
	/**
	 * 查询会员交易流水
	 */
	@Override
	public PageBean queryUserPurseList(PageBean pageBean) throws Exception {
		int total = userDAO.queryUserPurseCount(pageBean);
		pageBean.setTotal(total);
		List<ScPurse> pageData = userDAO.queryUserPursePagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	/**
	 * 查询会员身份认证信息
	 */
	@Override
	public PageBean queryUserIdentityList(PageBean pageBean) {
		int total = userDAO.queryUserIdentityCount(pageBean);
		pageBean.setTotal(total);
		List<ScIdentity> pageData = userDAO.queryUserIdentityPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 查询会员钱包余额
	 */
	@Override
	public PageBean queryUserWalletList(PageBean pageBean) throws Exception {
		int total = userDAO.queryUserWalletCount(pageBean);
		pageBean.setTotal(total);
		List<ScWallet> pageData = userDAO.queryUserWalletPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	/**
	 * 认证成功
	 */
	@Override
	@Transactional
	public void alterStatus(String id) throws Exception {
		ScIdentity identity  = findIdentity(id);
		identity.setAuthenticationTime(new Date());
		identity.setAuthenticator("admin");
		identity.setIdentityStatus(IdentityStatusEnum.Authentication.getCode());
		identity.setFailureReason("");
		DBUtil.getInstance().update(identity);
	}

	/**
	 * 查询身份认证单条记录
	 */
	@Override
	public ScIdentity findIdentity(String id) throws Exception {
		Object obj = DBUtil.getInstance().get(ScIdentity.class, id);
		if(obj != null) {
			return (ScIdentity) obj;
		}
		return null;
	}

	/**
	 * 认证失败
	 */
	@Override
	@Transactional
	public void saveFailurereason(ScIdentity identity) throws Exception {
		 ScIdentity vo = findIdentity(identity.getId());
		 vo.setIdentityStatus(IdentityStatusEnum.AuthenticationFail.getCode());
		 vo.setFailureReason(identity.getFailureReason());
		 DBUtil.getInstance().update(vo);
	}
	
}
