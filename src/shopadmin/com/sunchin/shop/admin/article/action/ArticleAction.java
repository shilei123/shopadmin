package com.sunchin.shop.admin.article.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.article.service.IArticleService;
import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.pojo.ScArticle;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScDirectoryStructure;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

public class ArticleAction extends PageAction{

	@Resource(name="articleService")
	private IArticleService articleService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private List<ScDictionary> articleTypeList;
	private ScArticle article;
	private List<Map<String, Object>> articleList;
	private List<ScDirectoryStructure> directoryTypeList;
	
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = articleService.queryArticleList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 编辑
	 * @return
	 */
	public String queryArticle(){
		try {
			article = articleService.queryArticle(article.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String save(){
		try {
			articleService.saveArticle(this.article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	/**
	 * 删除
	 */
	public String delete(){
		try {
			articleService.deleteArticle(article.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询文章分类
	 * @return
	 */
	public String queryArticleType(){
		try {
			articleTypeList = dictService.getDictByType(DictionaryTypeEnum.ARTICLE_TYPE.getType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询文章目录
	 * @return
	 */
	public String queryDirectoryType(){
		try {
			directoryTypeList = articleService.queryDirectoryType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	public List<ScDictionary> getArticleTypeList() {
		return articleTypeList;
	}

	public void setArticleTypeList(List<ScDictionary> articleTypeList) {
		this.articleTypeList = articleTypeList;
	}

	public ScArticle getArticle() {
		return article;
	}

	public void setArticle(ScArticle article) {
		this.article = article;
	}

	public List<Map<String, Object>> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Map<String, Object>> articleList) {
		this.articleList = articleList;
	}

	public List<ScDirectoryStructure> getDirectoryTypeList() {
		return directoryTypeList;
	}

	public void setDirectoryTypeList(List<ScDirectoryStructure> directoryTypeList) {
		this.directoryTypeList = directoryTypeList;
	}
	
}
