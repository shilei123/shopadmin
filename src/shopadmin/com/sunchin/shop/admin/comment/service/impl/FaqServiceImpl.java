package com.sunchin.shop.admin.comment.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.comment.dao.FaqDAO;
import com.sunchin.shop.admin.comment.service.FaqService;

@Service("faqService")
public class FaqServiceImpl implements FaqService {

	@Resource(name="faqDAO")
	private FaqDAO faqDAO;
	
	/*public PageBean queryCommentList(PageBean pageBean) throws Exception{
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
	}*/
}
