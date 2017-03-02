package framework.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import framework.db.DBUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class FenyeBase {
	protected int page = 1;//当前页数
	protected int total;//返回数据行数
	protected int rows = 14;//请求页行数
	protected List dataRows;//数据行 返回名称为rows
	protected Map<String,String> queryParams;//查询参数
	
	public List query(String sql, List params , DBUtil db){
		int firstResult = this.getPage() * this.getRowCount() - this.getRowCount();
		this.dataRows = db.queryBySQL(sql, params, firstResult, this.getRowCount());
		return dataRows;
	}
	
	public List query(String sql, List params ){
		return query(sql, params, DBUtil.getInstance());
	}
	
	public List query(String sql){
		return query(sql, DBUtil.getInstance(), "");
	}
	
	public List query(String sql,String addSql){
		return query(sql, DBUtil.getInstance(), addSql);
	}
	
	public List query(String sql, DBUtil db, String addSql){
		StringBuffer plsql = new StringBuffer(sql);
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
		return query(plsql.toString(), params, db);
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getRowCount(){
		return this.rows;
	}
	
	@JSON(name="rows")
	public List getDataRows() {
		return dataRows;
	}
	
	public void setDataRows(List dataRows){
		this.dataRows = dataRows;
	}

	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}
}