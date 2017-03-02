package framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	private String loginUrl = null;
	private boolean isLoginValidata = true;
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Object obj = ((HttpServletRequest)request).getSession().getAttribute("user");
		if(isLoginValidata && obj==null){
			//((HttpServletResponse)response).sendRedirect(request.getServletContext().getContextPath()+loginUrl);
			HttpServletResponse resp = (HttpServletResponse) response;
			ServletOutputStream out = resp.getOutputStream();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getServletContext().getContextPath();
			StringBuffer str = new StringBuffer();
			str.append(" <script type='text/javascript'> ");
			str.append(" 	if(window.top) { ");
			str.append(" 		window.top.location.href='").append(basePath).append(loginUrl).append("'");
			str.append(" 	} else { ");
			str.append(" 		window.location.href='").append(basePath).append(loginUrl).append("'");
			str.append(" 	} ");
			str.append(" </script> ");
			out.print(str.toString());
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig config) throws ServletException {
		String url = config.getServletContext().getInitParameter("login_url");
		this.loginUrl = url;
		
		//是否打开登录校验
		String isLoginValidataStr = config.getServletContext().getInitParameter("login_validata_is_open");
		if("false".equals(isLoginValidataStr)){
			this.isLoginValidata = false;
		}
	}

}
