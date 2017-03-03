package framework.bean;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@Component("pageBean")
public class PageBean {
	private int page = 1;// 当前页数
	private int total;// 返回数据行数
	private int rows = 14;// 请求页行数
	
	private Map<String, String> queryParams;// 查询参数
	private List pageData; //查询结果
	
	public List<Object> getPageData() {
		return pageData;
	}
	public void setPageData(List pageData) {
		this.pageData = pageData;
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
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Map<String, String> getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}
}
