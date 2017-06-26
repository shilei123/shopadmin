package com.sunchin.shop.admin.comment.service;

import com.sunchin.shop.admin.pojo.ScFaq;

import framework.bean.PageBean;

public interface FaqService {

	/**
	 * 分页查询常见问题
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryFaqList(PageBean pageBean) throws Exception;
	
	/*
	 * 查询某条评论
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScFaq queryFaqById(String id) throws Exception;
	
	/**
	 * 删除评论
	 * @param id
	 * @throws Exception
	 */
	public void delFaq(String id) throws Exception;
}
