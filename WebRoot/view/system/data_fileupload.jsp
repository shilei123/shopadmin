<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
  	<title>数据管理</title>
	<script type="text/javascript">
	$(function(){
		var msg = "${msg}";
		if(msg=="error"){
			$("#msg2").html("*导入失败,请检查数据文件");
		}else if(msg=="success"){
			$("#msg2").html("*导入成功");
			try{window.parent.dataFileuploadSuccess();}catch(e){
				try{window.opener.dataFileuploadSuccess();}catch(e){}
			}
		}
	});
	function submitForm(){
		$("#msg2").html("");
		if($("#dataFile").val()==""){
			$("#msg").css("display","");
			return false;
		}$("#msg").css("display","none");
		$('#form_import').submit();
	}
	</script>
	<style type="text/css">
	body{
		margin: 0px 0px 0px 0px;
		padding: 0px 0xpx 0px 0px;
	}
	</style>
  </head>
  
  <body >
  	<form id="form_import"  action="${basePath}/view/system/data!importXml.action" method="POST" enctype="multipart/form-data">
		<span style="font-size: 12px; font-weight: bold;">数据文件:</span> 
		<input name="dataFile" type="file" id="dataFile" />
		<span id="msg" style="color: red; font-size: 12px; display: none;">*不能为空</span>
		<div id="msg2" style="color: red; font-size: 12px;"></div>
	</form>
  </body>
</html>