<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="framework.bean.UserMsg"%>
<%@ page import="framework.util.ConfigUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
request.setAttribute("path", path);
request.setAttribute("basePath", basePath);
request.setAttribute("title", application.getInitParameter("title"));
UserMsg user = session.getAttribute("user")==null?new UserMsg():(UserMsg)session.getAttribute("user");
request.setAttribute("user",user);

String imageServer = ConfigUtil.getInstance().getImageServer();
String attachServer = ConfigUtil.getInstance().getAttachServer();
request.setAttribute("imageServer", imageServer);
request.setAttribute("attachServer", attachServer);
%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript">
<!--
var path_ = "${path}";
var basePath_ = "${basePath}";
var imageServer_ = "${imageServer}";
var attachServer_ = "${attachServer}";
//-->
</script>
<script type="text/javascript" src="${path }/js/common.js"></script>
<script type="text/javascript" src="${path }/js/jquery-1.8.0.min.js"></script>
<!-- laypage -->
<script type="text/javascript" src="${path }/js/laypage-v1.3/laypage/laypage.js"></script>
<!-- easyui1.4 -->
<link rel="stylesheet" type="text/css" href="${path }/js/jquery-easyui-1.4/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/js/jquery-easyui-1.4/themes/icon.css">
<script type="text/javascript" src="${path }/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<!-- amazeui2.7.2 -->
<link rel="stylesheet" href="${path }/AmazeUI-2.7.2/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="${path }/AmazeUI-2.7.2/assets/css/admin.css">
<link rel="stylesheet" href="${path }/css/frame.css">
<script src="${path }/AmazeUI-2.7.2/assets/js/amazeui.min.js"></script>
<script type="text/javascript" src="${path }/js/framePage.js"></script>
<script type="text/javascript" src="${path }/js/json2.js"></script>
<!-- jQuery validate -->
<script type="text/javascript" src="${path }/js/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${path }/js/validation/messages_zh.js"></script>
<!-- jQuery ajaxfileupload -->
<script type="text/javascript" src="${path }/js/ajaxfileupload.js"></script>