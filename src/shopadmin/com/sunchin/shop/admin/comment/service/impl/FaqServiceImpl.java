package com.sunchin.shop.admin.comment.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.comment.dao.FaqDAO;
import com.sunchin.shop.admin.comment.service.FaqService;
import com.sunchin.shop.admin.pojo.ScFaq;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Service("faqService")
public class FaqServiceImpl implements FaqService {

	@Resource(name="faqDAO")
	private FaqDAO faqDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	
	public PageBean queryFaqList(PageBean pageBean) throws Exception{
		int total = faqDAO.queryFaqCount(pageBean);
		pageBean.setTotal(total);
		List<Map<String, Object>> pageData = faqDAO.queryFaqPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	@Override
	public ScFaq queryFaqById(String id) throws Exception {
		ScFaq faq = new ScFaq();
		Object[] objArr = faqDAO.queryPojoById(id);
		for (int i = 0; i < objArr.length; i++) {
			if(objArr[i]==null)
				objArr[i]="";
		}
		faq.setId(objArr[0].toString());
		faq.setFaqTitle(objArr[1].toString());
		faq.setFaqContent(objArr[2].toString());
		faq.setCategory(objArr[3].toString());
		faq.setParentFaqId(objArr[4].toString());
		faq.setHotQuestion(objArr[5].toString());
		if(!objArr[6].toString().isEmpty()){
			faq.setOrder(((BigDecimal)objArr[6]).intValue());
		}
		if(!objArr[7].toString().isEmpty()){
			faq.setCreateTime((Date)objArr[7]);
		}
		faq.setFaqType(objArr[8].toString());
		faq.setFaqTypeId(objArr[9].toString());
		faq.setFlag(objArr[10].toString());
		faq.setBelong(objArr[11].toString());
		return faq;
	}
    
	@Override
	public void delFaq(String id) throws Exception {
		faqDAO.delFaq(id);
	}
}
