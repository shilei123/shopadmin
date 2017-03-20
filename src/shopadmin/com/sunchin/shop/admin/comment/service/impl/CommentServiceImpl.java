package com.sunchin.shop.admin.comment.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.comment.dao.CommentDAO;
import com.sunchin.shop.admin.comment.service.CommentService;

import framework.bean.PageBean;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource(name="commentDAO")
	private CommentDAO commentDAO;
	
	public PageBean queryCommentList(PageBean pageBean) throws Exception{
		int total = commentDAO.queryCommentCount(pageBean);
		pageBean.setTotal(total);
		List<Map<String, Object>> pageData = commentDAO.queryCommentPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
}
