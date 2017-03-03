package framework.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import framework.bean.PageBean;

@SuppressWarnings("unchecked")
public class PageAction {
	protected int page = 1;//当前页数
	
	protected int total;//返回数据行数
	
	protected int rows = 10;//请求页行数
	
	protected List dataRows;//数据行 返回名称为rows
	
	protected Map<String,String> queryParams;//查询参数
	
	protected PageBean pageBean; //作为单独的一个对象存放便于将分页参数传入service层和dao层
	
	//更新数据结果集和总数
	/*private void setPageResult(PageBean pageResult) {
		this.dataRows = pageResult.getPageData();
		this.total = pageResult.getTotal();
	}*/
	
	//将分页参数以PageBean的形式获取出来
	public PageBean getPageBean() {
		if(pageBean == null) {
			pageBean = new PageBean();
		}
		this.pageBean.setPage(this.page);
		this.pageBean.setTotal(this.total);
		this.pageBean.setRows(this.rows);
		this.pageBean.setQueryParams(this.queryParams);
		return this.pageBean;
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