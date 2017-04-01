package com.sunchin.shop.admin.article.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScArticle;
import com.sunchin.shop.admin.pojo.ScDirStruct;

import framework.bean.PageBean;

public interface IArticleService {

	PageBean queryArticleList(PageBean pageBean) throws Exception;

	void deleteArticle(String id) throws Exception;

	ScArticle queryArticle(String id) throws Exception;

	List<ScDirStruct> queryDirectoryType() throws Exception;

	void saveArticle(ScArticle article) throws Exception;

}
