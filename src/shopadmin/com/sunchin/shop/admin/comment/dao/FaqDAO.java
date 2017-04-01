package com.sunchin.shop.admin.comment.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScComment;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("faqDAO")
public class FaqDAO extends PageDAO{

	public final String SELECT_SQL = " select t.id,decode(t.type,'1','评论','2','追评') as type,t.content,t.score,to_char(t.comment_time,'yyyy-MM-dd hh24:mm:ss')as create_time from SC_COMMENT t where flag=? ";

	public int queryCommentCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryCommentPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		List<Map<String, Object>> pageData = this.query(sql, params, DBUtil.getInstance(), pageBean);
		return pageData;
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		StringBuffer sql = new StringBuffer(SELECT_SQL);
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String type = pageBean.getQueryParams().get("type");
			if (StringUtils.isNotBlank(type)){
				params.add(type);
				sql.append(" and t.type=? ");
			}
			String content = pageBean.getQueryParams().get("content");
			if (StringUtils.isNotBlank(content)){
				params.add(content+"%");	//内容仅右模糊
				sql.append(" and t.content like ? ");
			}
			String startTime = pageBean.getQueryParams().get("startTime");
			if (StringUtils.isNotBlank(startTime)){
				params.add(startTime);
				sql.append(" and t.comment_time >= to_date(?,'yyyy-MM-dd hh24:mi:ss')   ");
			}
			String endTime = pageBean.getQueryParams().get("endTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59");
				sql.append(" and t.comment_time <= to_date(?,'yyyy-MM-dd hh24:mi:ss')  ");
			}
			/*String commentPeople = pageBean.getQueryParams().get("commentPeople");
			if (StringUtils.isNotBlank(commentPeople)){
				params.add(commentPeople);
				sql.append(" and t.commentPeople=? ");
			}*/
			String score = pageBean.getQueryParams().get("score");
			if (StringUtils.isNotBlank(score)){
				params.add(score);
				sql.append(" and t.score>=? ");
			}
		}
		sql.append(" order by t.comment_time desc ");
		return sql.toString();
	}
	
	@SuppressWarnings("unchecked")
	public ScComment queryCommentById(String id){
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("flag", FlagEnum.ACT.getCode());
		params.put("id", id);
		List<ScComment> list = DBUtil.getInstance().queryByPojo(ScComment.class,params);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
}
