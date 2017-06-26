package com.sunchin.shop.admin.comment.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository("faqDAO")
public class FaqDAO extends PageDAO{
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	public int queryFaqCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.FAQ_TYPE.getType());
		params.add(DictionaryTypeEnum.FAQ_CATEGORY.getType());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	public List<Map<String, Object>> queryFaqPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(DictionaryTypeEnum.FAQ_TYPE.getType());
		params.add(DictionaryTypeEnum.FAQ_CATEGORY.getType());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		List<Map<String, Object>> pageData = this.query(sql, params, DBUtil.getInstance(), pageBean);
		return pageData;
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.id, t.faq_title, t.faq_content, t.parent_faq_id, t.order_, ");
		sql.append(" decode(t.hot_question,'1','是','0','否')as hot_question, ");
		sql.append(" to_char(t.create_time, 'yyyy-MM-dd hh24:mm:ss') as create_time, ");
		sql.append(" t1.name faq_type,t2.name faq_category ");
		sql.append(" from SC_FAQ t ");
		sql.append(" left join sc_dictionary t1 on t1.code=t.faq_type and t1.type=? ");//'FAQ_TYPE'
		sql.append(" left join sc_dictionary t2 on t2.code=t.category and t2.type=? ");//'FAQ_CATEGORY'
		sql.append(" where t.flag=? ");
		sql.append(" and t1.flag=? ");
		sql.append(" and t2.flag=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String faqTitle = pageBean.getQueryParams().get("faqTitle");
			if (StringUtils.isNotBlank(faqTitle)){
				params.add("%" + faqTitle + "%");
				sql.append(" and t.faq_title like ? ");
			}
			String faqType = pageBean.getQueryParams().get("faqType");
			if (StringUtils.isNotBlank(faqType)){
				params.add(faqType);
				sql.append(" and t.faq_type=? ");
			}
			String category = pageBean.getQueryParams().get("category");
			if (StringUtils.isNotBlank(category)){
				params.add(category);
				sql.append(" and t.category=? ");
			}
			String hotQuestion = pageBean.getQueryParams().get("hotQuestion");
			if (StringUtils.isNotBlank(hotQuestion)){
				params.add(hotQuestion);
				sql.append(" and t.hot_question=? ");
			}
			String startTime = pageBean.getQueryParams().get("startTime");
			if (StringUtils.isNotBlank(startTime)){
				params.add(startTime);
				sql.append(" and t.create_time >= to_date(?,'yyyy-MM-dd hh24:mi:ss')   ");
			}
			String endTime = pageBean.getQueryParams().get("endTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59");
				sql.append(" and t.create_time <= to_date(?,'yyyy-MM-dd hh24:mi:ss')  ");
			}
		}
		sql.append(" order by t.create_time desc ");
		return sql.toString();
	}
	
	public Object[] queryPojoById(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.id,t.faq_title,t.faq_content,t2.name faq_category,t.parent_faq_id,t3.name as hot_question,t.order_,t.create_time ,t1.name faq_type,t.faq_type_id,t.flag,t.belong from SC_FAQ t ");
		sql.append(" left join sc_dictionary t1 on t1.code = t.faq_type and t1.type=? ");
		sql.append(" left join sc_dictionary t2 on t2.code = t.category and t2.type=?");
		sql.append(" left join sc_dictionary t3 on t3.code = t.hot_question and t3.type=?");
		sql.append(" where t.flag=? ");
		sql.append(" and t1.flag=? ");
		sql.append(" and t2.flag=? ");
		sql.append(" and t3.flag=? ");
		sql.append(" and t.id=? ");
		List<String> params = new ArrayList<String>(8);
		params.add(DictionaryTypeEnum.FAQ_TYPE.getType());
		params.add(DictionaryTypeEnum.FAQ_CATEGORY.getType());
		params.add(DictionaryTypeEnum.HOT_QUESTION.getType());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		params.add(FlagEnum.ACT.getCode());
		params.add(id);
		
		List<Object[]> list = queryBySql(sql, params);
		if(list!=null && !list.isEmpty()){
			//getClob(list.get(0));
			return list.get(0);
		}
		return null;
	}

	/**
	 * Clob转String
	 * @param objArr
	 */
	/*private void getClob(Object[] objArr) {
		SerializableClob c = null;
		StringBuffer sb = null;
		for (int i = 0; i < objArr.length; i++) {
			if(objArr[i]==null || !objArr[i].getClass().getName().trim().equals("org.hibernate.lob.SerializableClob")) continue;
			try {
				c = (SerializableClob)objArr[i];
				Reader reader = c.getCharacterStream();
				BufferedReader br = new BufferedReader(reader);
				String temp = null;
				while ((temp=br.readLine()) != null) {
					sb = new StringBuffer();
					sb.append(temp);
					objArr[i] = sb.toString();
				}
			  	}catch (Exception e) {
			  		e.printStackTrace();
			  	} 
		}
	}*/

	private List<Object[]> queryBySql(final StringBuffer sql, final List<String> params) {
		return hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
        	   Query query = session.createSQLQuery(sql.toString());
        	   if(params!=null && params.size()>0){
	       			for (int i = 0; i < params.size(); i++) {
	       				query.setParameter(i, params.get(i));
	       			}
	       		}
	       		return query.list();
           }
		});
	}
	
	public void delFaq(String id){
		String hql = " update ScFaq set flag=? where id=? ";
		DBUtil.getInstance().executeHql(hql, FlagEnum.HIS.getCode(), id);
	}
}
