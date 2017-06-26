package com.sunchin.shop.admin.comment.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.comment.dao.CommentDAO;
import com.sunchin.shop.admin.comment.service.CommentService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScComment;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.CommonUtils;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource(name="commentDAO")
	private CommentDAO commentDAO;
	
	public PageBean queryCommentList(PageBean pageBean) throws Exception{
		int total = commentDAO.queryCommentCount(pageBean);
		pageBean.setTotal(total);
		List<Map<String, Object>> pageData = commentDAO.queryCommentPagination(pageBean);
		pageData = this.dealPageData(pageData);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	private List<Map<String, Object>> dealPageData(List<Map<String, Object>> pageData) {
		if(pageData!=null && !pageData.isEmpty()){
			for (Map<String, Object> map : pageData) {
				String content = CommonUtils.getString(map.get("content"));
				if(content.length()>20){
					content = content.substring(0, 20) + "... ...";
					map.put("content", content);
				}
			}
		}
		return pageData;
	}

	@Override
	public ScComment queryCommentById(String id) throws Exception {
		return commentDAO.queryCommentById(id);
	}

	@Override
	public void delComment(String id) throws Exception {
		ScComment comment = commentDAO.queryCommentById(id);
		if(comment!=null){
			String hql = " update ScComment set flag=? where id=? ";
			DBUtil.getInstance().executeHql(hql, FlagEnum.HIS.getCode(), id);
		}
	}
}
