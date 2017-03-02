<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
  		window.UMEDITOR_HOME_URL = "/framework/ueditor/";
  	</script>
  	<jsp:include page="/include/default.jsp"></jsp:include>
  	
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>UMEDITOR 文本域渲染编辑器</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=path %>/ueditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<%=path %>/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/umeditor.config.js"></script>

    <script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/umeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ueditor/lang/zh-cn/zh-cn.js"></script>
    <style type="text/css">
        h1{
            font-family: "微软雅黑";
            font-weight: normal;
        }
    </style>
</head>
<body>
<h1>UMEDITOR 文本域渲染编辑器</h1>

<!--style给定宽度可以影响编辑器的最终宽度-->
<textarea id="myEditor"  style="width:800px;height:540px;">这里是原始的textarea中的内容，可以从数据中读取</textarea>
<br/>
<input type="button" onclick="render()" value="渲染编辑器">

<script type="text/javascript">
    //渲染编辑器
    function render(){
        UM.getEditor('myEditor')
    }
</script>

</body>
</html>
