package framework.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.config.Config;
import framework.task.RubbishClearTask;

public class ConfigServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		Map config = new HashMap();
		Enumeration<String> en = servletContext.getInitParameterNames();
		while(en.hasMoreElements()){
			String key = en.nextElement();
			config.put(key, servletContext.getInitParameter(key));
		}
		config.put(Config.FILEUPLOAD_PATH_NAME, config.get(String.valueOf(config.get("system"))+".fileupload.path"));	
		Config.initConfigMap(config);
		
		new RubbishClearTask().start();//启动垃圾清理
	}
	public ConfigServlet() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
