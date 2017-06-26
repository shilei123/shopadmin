package com.sunchin.shop.admin.comment.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.comment.service.CommentService;
import com.sunchin.shop.admin.pojo.ScComment;

import framework.action.PageAction;
import framework.bean.PageBean;

public class CommentAction extends PageAction{

	@Resource(name="commentService")
	private CommentService commentService;
	
	private ScComment comment;
	
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
	
	public String queryCommentById(){
		try {
			comment = commentService.queryCommentById(comment.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String delComment(){
		try {
			commentService.delComment(comment.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public ScComment getComment() {
		return comment;
	}

	public void setComment(ScComment comment) {
		this.comment = comment;
	}
	
}
