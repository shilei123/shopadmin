package com.sunchin.shop.admin.article.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.smartcardio.ATR;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.article.dao.ArticleDAO;
import com.sunchin.shop.admin.article.service.IArticleService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dirStruct.dao.DirStructDAO;
import com.sunchin.shop.admin.freight.dao.FreightDAO;
import com.sunchin.shop.admin.pojo.ScArticle;
import com.sunchin.shop.admin.pojo.ScDirStruct;
import com.sunchin.shop.admin.pojo.ScEvents;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Repository("articleService")
public class ArticleServiceImpl implements IArticleService{

	@Resource(name="acticleDAO")
	private ArticleDAO acticleDAO;
	@Resource(name="dirStructDAO")
	private DirStructDAO dirStructDAO;

	/**
	 * 查询
	 */
	@Override
	public PageBean queryArticleList(PageBean pageBean) throws Exception {
		int total = acticleDAO.queryArticleCount(pageBean);
		pageBean.setTotal(total);
		List<ScArticle> pageData = acticleDAO.queryArticlePagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	/**
	 * 删除
	 */
	@Override
	public void deleteArticle(String id) throws Exception {
		ScArticle article = acticleDAO.queryArticleById(id);
		article.setFlag(FlagEnum.HIS.getCode());
		DBUtil.getInstance().update(article);
	}

	/**
	 * 查询单条记录
	 */
	@Override
	public ScArticle queryArticle(String id) throws Exception {
		Object obj = acticleDAO.queryArticleById(id);
		if(obj != null) {
			return (ScArticle) obj;
		}
		return null;
	}
	
	/**
	 * 查询文章目录
	 */
	@Override
	public List<ScDirStruct> queryDirectoryType() throws Exception {
		List<ScDirStruct> directoryList = dirStructDAO.queryDirectory();
		return directoryList;
	}

	@Override
	public void saveArticle(ScArticle article) throws Exception {
		if (article == null) {
			return;
		}
		DBUtil db = DBUtil.getInstance();
		// 新增
		if (StringUtils.isBlank(article.getId())) {
			String id = UUID.randomUUID().toString();
			article.setId(id);
			article.setCreateTime(new Date());
			article.setFlag(FlagEnum.ACT.getCode());
			db.insert(article);
		} else { // 修改
			ScArticle vo = queryArticle(article.getId());
			vo.setArticleTitle(article.getArticleTitle());
			vo.setAuthor(article.getAuthor());
			vo.setSource(article.getSource());
			vo.setUrl(article.getUrl());
			vo.setArticleType(article.getArticleType());
			vo.setDirectoryId(article.getDirectoryId());
			vo.setPicture(article.getPicture());
			vo.setAbstracts(article.getAbstracts());
			vo.setIscontent(article.getIscontent());
			vo.setMemo(article.getMemo());
			vo.setUpdateTime(new Date());
			db.update(vo);
		}
	}
}
