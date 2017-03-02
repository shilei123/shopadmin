package framework.helper;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import framework.bean.UserMsg;
import framework.db.pojo.TXtUser;


public class RequestHelper {
	public static String getParameter(String name){
		return ServletActionContext.getRequest().getParameter(name);
	}
	
	public static Map getParameterMap(String name){
		return ServletActionContext.getRequest().getParameterMap();
	}
	
	public static Object getAttribute(String name){
		return ServletActionContext.getRequest().getAttribute(name);
	}
	
	public static void getAttribute(String key, Object obj){
		ServletActionContext.getRequest().setAttribute(key, obj);
	}
	
	public static HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public static HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public static ServletContext getServletContext(){
		return ServletActionContext.getRequest().getSession().getServletContext();
	}
	
	public static TXtUser getLoginUser(){
		return (TXtUser)RequestHelper.getSession().getAttribute("user");
	}
	
	public static void wirte(String msg) throws IOException{
		getResponse().getOutputStream().write(msg.getBytes("UTF-8"));
		getResponse().getOutputStream().flush();
		//getResponse().getWriter().write(msg);
		//getResponse().getWriter().flush();
	}
	
	public static void wirte(Map map) throws IOException{
		if(map==null){return ;}
		wirte(JSONObject.fromObject(map).toString());
	}
	
	public static void wirte(Object obj) throws IOException{
		if(obj==null){return ;}
		if(obj instanceof List || obj.getClass().isArray()){
			wirte((List)obj);
		}else{
			wirte(JSONObject.fromObject(obj).toString());
		}
	}
	
	public static UserMsg getUser(){
		return (UserMsg)getSession().getAttribute("user");
	}
	
	public static void setUser(UserMsg user){
		getSession().setAttribute("user", user);
	}
}
