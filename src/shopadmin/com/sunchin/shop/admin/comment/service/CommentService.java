package com.sunchin.shop.admin.comment.service;

import framework.bean.PageBean;

public interface CommentService {

	/**
	 * 分页查询
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryCommentList(PageBean pageBean) throws Exception;
	
}
