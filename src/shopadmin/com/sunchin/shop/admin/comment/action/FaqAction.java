package com.sunchin.shop.admin.comment.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.comment.service.FaqService;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.pojo.ScFaq;
import com.sunchin.shop.admin.system.service.DictService;

import framework.action.PageAction;
import framework.bean.PageBean;

/**
 * @author aobingcheng
 * 2017年4月1日
 * 
 */
public class FaqAction extends PageAction {
	
	@Resource(name="faqService")
	private FaqService faqService;
	@Resource(name = "dictService")
	private DictService dictService;
	
	private List<ScDictionary> dict1;
	private List<ScDictionary> dict2;
	private List<ScDictionary> dict3;
	private String type1;
	private String type2;
	private String type3;
	private ScFaq faq;
	
	public String queryFaqList(){
		try {
			PageBean resultData = faqService.queryFaqList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String delFaq(){
		try {
			 faqService.delFaq(faq.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String queryFaqById(){
		try {
			faq = faqService.queryFaqById(faq.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String queryDictsByType(){
		try {
			dict1 = dictService.getDictByType(type1);
			dict2 = dictService.getDictByType(type2);
			dict3 = dictService.getDictByType(type3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public ScFaq getFaq() {
		return faq;
	}

	public void setFaq(ScFaq faq) {
		this.faq = faq;
	}

	public List<ScDictionary> getDict1() {
		return dict1;
	}

	public void setDict1(List<ScDictionary> dict1) {
		this.dict1 = dict1;
	}

	public List<ScDictionary> getDict2() {
		return dict2;
	}

	public void setDict2(List<ScDictionary> dict2) {
		this.dict2 = dict2;
	}

	public List<ScDictionary> getDict3() {
		return dict3;
	}

	public void setDict3(List<ScDictionary> dict3) {
		this.dict3 = dict3;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}
	
}
