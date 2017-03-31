package com.sunchin.shop.admin.comment.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.comment.service.FaqTypeService;
import com.sunchin.shop.admin.pojo.ScFaqType;

@SuppressWarnings("rawtypes")
public class FaqTypeAction {

	@Resource(name = "faqTypeService")
	private FaqTypeService faqTypeService;
	
	private List<Map> trees;
	private ScFaqType faqType;
	private String msg;

	/**
	 * 查询类别树
	 * @return
	 */
	public String initFaqTypeTree() {
		try {
			trees = faqTypeService.queryFaqTypeTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String saveFaqType(){
		try {
			faqTypeService.saveFaqType(faqType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String updateFaqType(){
		try {
			faqTypeService.updateFaqType(faqType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String delFaqType(){
		try {
			msg = faqTypeService.delFaqType(faqType.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public List<Map> getTrees() {
		return trees;
	}

	public void setTrees(List<Map> trees) {
		this.trees = trees;
	}

	public ScFaqType getFaqType() {
		return faqType;
	}

	public void setFaqType(ScFaqType faqType) {
		this.faqType = faqType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
