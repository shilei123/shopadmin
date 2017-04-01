package com.sunchin.shop.admin.article.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScArticle;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("acticleDAO")
public class ArticleDAO extends PageDAO{

	public int queryArticleCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.ARTICLE_TYPE.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScArticle> queryArticlePagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.ARTICLE_TYPE.getType());
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.article_title,t1.author,t1.source,to_char(t1.create_time,'yyyy-mm-dd') create_time,t2.name article_type ");
		sql.append(" from sc_article  t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.article_type ");
		sql.append(" where t1.flag=? ");
		sql.append(" and t2.type=?");
		
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String articleTitle = pageBean.getQueryParams().get("articleTitle");
			if (StringUtils.isNotBlank(articleTitle)){
				params.add(articleTitle+"%");
				sql.append(" and t1.article_title like ? ");
			}
			String author = pageBean.getQueryParams().get("author");
			if (StringUtils.isNotBlank(author)){
				params.add(author+"%");
				sql.append(" and t1.author like ? ");
			}
			String source = pageBean.getQueryParams().get("source");
			if (StringUtils.isNotBlank(source)){
				params.add(source+"%");
				sql.append(" and t1.source like ? ");
			}
			String articleType = pageBean.getQueryParams().get("articleType");
			if (StringUtils.isNotBlank(articleType) && !"-1".equals(articleType)){
				params.add(articleType);
				sql.append(" and t1.article_type=? ");
			}
			String startTime = pageBean.getQueryParams().get("startRegTime");
			if (StringUtils.isNotBlank(startTime)){
				params.add(startTime);
				sql.append(" and t1.create_time >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
			String endTime = pageBean.getQueryParams().get("endRegTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59 ");
				sql.append(" and t1.create_time <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
		}
		sql.append(" order by t1.create_time desc ");
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public ScArticle queryArticleById(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("flag",FlagEnum.ACT.getCode());
		List<ScArticle> lists = DBUtil.getInstance().queryByPojo(ScArticle.class, params);
		if(lists != null && !lists.isEmpty()){
			return lists.get(0);
		}
		return null;
	}
}
