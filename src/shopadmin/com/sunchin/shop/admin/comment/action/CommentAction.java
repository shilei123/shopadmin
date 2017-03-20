package com.sunchin.shop.admin.comment.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.comment.service.CommentService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class CommentAction extends PageAction{

	@Resource(name="commentService")
	private CommentService commentService;
	
	public String queryCommentList(){
		try {
			PageBean resultData = commentService.queryCommentList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
}
