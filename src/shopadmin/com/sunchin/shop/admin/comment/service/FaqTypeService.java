package com.sunchin.shop.admin.comment.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScFaqType;

@SuppressWarnings("rawtypes")
public interface FaqTypeService {

	/**
	 * 查询常见问题类别树
	 * @return
	 * @throws Exception
	 */
	public List<Map> queryFaqTypeTree() throws Exception;

	/**
	 * 新增类别
	 * @param faqType
	 * @throws Exception
	 */
	public void saveFaqType(ScFaqType faqType) throws Exception;
	
	/**
	 * 修改类别
	 * @param faqType
	 * @throws Exception
	 */
	public void updateFaqType(ScFaqType faqType) throws Exception;
	
	/**
	 * 删除类别
	 * @param id
	 * @throws Exception
	 */
	public String delFaqType(String id) throws Exception;
	
	/**
	 * 查询该类别是否有子类
	 * @param id
	 * @throws Exception
	 */
	/*public List<ScFaqType> queryFaqType(String id) throws Exception;*/
	
}
