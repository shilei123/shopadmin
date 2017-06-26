package com.sunchin.shop.admin.comment.service;

import com.sunchin.shop.admin.pojo.ScComment;

import framework.bean.PageBean;

public interface CommentService {

	/**
	 * 分页查询评论
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryCommentList(PageBean pageBean) throws Exception;
	
	/**
	 * 查询某条评论
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScComment queryCommentById(String id) throws Exception;
	
	/**
	 * 删除评论
	 * @param id
	 * @throws Exception
	 */
	public void delComment(String id) throws Exception;
}
