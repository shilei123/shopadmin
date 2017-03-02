package framework.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import framework.bean.PageBean;

@SuppressWarnings({"unchecked","rawtypes"})
@Component("pageDAO")
public class PageDAO {
	protected List query(String sql, PageBean pageBean) {
		return query(sql, DBUtil.getInstance(), "", pageBean);
	}
	
	protected List query(String sql, List params, PageBean pageBean) {
		return query(sql, params, DBUtil.getInstance(), pageBean);
	}
	
	protected List query(String sql, List params, DBUtil db, PageBean pageBean) {
		//计算起始行
		int startRowIndex = pageBean.getPage() * pageBean.getRows() - pageBean.getRows();
		//查询数据并返回
		List resultData = db.queryBySQL(sql, params, startRowIndex, pageBean.getRows());
		return resultData;
	}
	
	protected List query(String sql, String addSql, PageBean pageBean) {
		return query(sql, DBUtil.getInstance(), addSql, pageBean);
	}
	
	protected List queryByHql(String hql, Map params, PageBean pageBean) {
		return queryByHql(hql, params, DBUtil.getInstance(), pageBean);
	}
	
	protected List queryByHql(String hql, Map params, DBUtil db, PageBean pageBean) {
		//计算起始行
		int startRowIndex = pageBean.getPage() * pageBean.getRows() - pageBean.getRows();
		//查询数据并返回
		List resultData = db.queryByHql(hql, params, startRowIndex, pageBean.getRows());
		return resultData;
	}
	
	protected List queryByHql(String hql, List params, PageBean pageBean) {
		return queryByHql(hql, params, DBUtil.getInstance(), pageBean);
	}
	
	protected List queryByHql(String hql, List params, DBUtil db, PageBean pageBean) {
		//计算起始行
		int startRowIndex = pageBean.getPage() * pageBean.getRows() - pageBean.getRows();
		//查询数据并返回
		List resultData = db.queryByHql(hql, params, startRowIndex, pageBean.getRows());
		return resultData;
	}
	
	protected List queryByName(String name, Map params, PageBean pageBean) {
		return queryByName(name, params, DBUtil.getInstance(), pageBean);
	}
	
	protected List queryByName(String name, Map params, DBUtil db, PageBean pageBean) {
		//计算起始行
		int startRowIndex = pageBean.getPage() * pageBean.getRows() - pageBean.getRows();
		//查询数据并返回
		List resultData = db.queryByName(name, params, startRowIndex, pageBean.getRows());
		return resultData;
	}
	
	/**
	 * 根据条件生成sql
	 * @param sql
	 * @param pageBean
	 * @return
	 */
	protected String generateQuerySql(String sql, PageBean pageBean) {
		StringBuffer plsql = new StringBuffer(sql);
		Map<String, String> queryParams = pageBean.getQueryParams();
		List params = new ArrayList(queryParams==null?0:queryParams.size());
		if(queryParams!=null){
			for(String key :queryParams.keySet()){
				if(queryParams.get(key)!=null && !queryParams.get(key).equals("")){
					plsql.append(" and ").append(key).append("=?");
					params.add(queryParams.get(key));
				}
			}
		}
		return plsql.toString();
	}
	
	protected List query(String sql, DBUtil db, String addSql, PageBean pageBean) {
		StringBuffer plsql = new StringBuffer(sql);
		Map<String, String> queryParams = pageBean.getQueryParams();
		List params = new ArrayList(queryParams==null?0:queryParams.size());
		if(queryParams!=null){
			for(String key :queryParams.keySet()){
				if(queryParams.get(key)!=null && !queryParams.get(key).equals("")){
					plsql.append(" and ").append(key).append("=?");
					params.add(queryParams.get(key));
				}
			}
		}
		plsql.append(addSql);
		return query(plsql.toString(), params, db, pageBean);
	}
}