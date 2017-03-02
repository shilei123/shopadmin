package framework.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import framework.db.DBUtil;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class DBStrutsPrepareAndExecuteFilter extends
		StrutsPrepareAndExecuteFilter {
	private static final Logger logger = LoggerFactory.getLogger(DBStrutsPrepareAndExecuteFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try{
			super.doFilter(request, response, chain);
			Exception exception = (Exception)request.getAttribute("javax.servlet.error.exception");
			String errUrl = String.valueOf(request.getAttribute("struts.view_uri"));
			if(exception != null || errUrl.equals("/error.jsp")){
				this.dbrollback(request);//struts执行失败， 回滚数据库操作
				logger.error("异常", exception);
			}else{
				this.dbcommit(request);//执行成功，提交数据库操作
			}
		}catch(Exception ex){
			logger.error("异常", ex);
		}
		this.dbclose(request);//回收资源
	}
	
	private void dbcommit(ServletRequest request){
		dbaction(request, 1);
	}
	
	private void dbrollback(ServletRequest request){
		dbaction(request, -1);
	}
	
	private void dbclose(ServletRequest request){
		dbaction(request, 0);
	}
	
	private void dbaction(ServletRequest request, int actionid){
		/*DBUtil db = (DBUtil)request.getAttribute(DBUtil.DB_SESSION_NAME);
		if(db!=null){
			if(actionid==0){
				//db.close();
			}else if(actionid==1){
				//db.commit();
			}if(actionid==-1){
				//db.rollback();
			}
		}*/
	}
}
