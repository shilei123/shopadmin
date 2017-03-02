<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="framework.bean.UserMsg"%>
<%
request.setAttribute("basePath", request.getContextPath());
request.setAttribute("_title", application.getInitParameter("title"));
UserMsg user = session.getAttribute("user")==null?new UserMsg():(UserMsg)session.getAttribute("user");
request.setAttribute("user",user);
%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript">
<!--
var _basePath = "${basePath}";
//-->
</script>
<link rel="stylesheet" type="text/css" href="${basePath }/js/jquery-easyui-1.4/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath }/js/jquery-easyui-1.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath }/css/default.css">
<script type="text/javascript" src="${basePath }/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath }/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath }/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${basePath }/js/json2.js"></script>
<script type="text/javascript" src="${basePath }/js/default.js"></script>
