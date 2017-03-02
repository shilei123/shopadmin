<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="framework.util.VerifyCodeUtils"%>
<%
	VerifyCodeUtils image = new VerifyCodeUtils();
	//设置页面不缓存
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	ImageIO.write(image.creatImage(), "JPEG", response.getOutputStream()); //数字
	//ImageIO.write(image.creatImageGB(), "JPEG", response.getOutputStream()); //汉字
	
	//输出图象到页面
	out.clear();
	out = pageContext.pushBody();

	// 将认证码存入SESSION
	session.setAttribute("VerifyCode",image.sRand); //数字
	//session.setAttribute("imgCode", image.sgbRand);//汉字
%>
