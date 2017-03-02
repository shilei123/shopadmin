<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="framework.action.system.FileUploadAction"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
String basePath = request.getContextPath();
request.setAttribute("basePath", basePath);
 %>
<%
request.setAttribute("groupId",request.getParameter("groupId")==null?"":request.getParameter("groupId"));
request.setAttribute("fileSize",request.getParameter("fileSize")==null?"0":request.getParameter("fileSize"));
request.setAttribute("dirName",request.getParameter("dirName")==null?"":request.getParameter("dirName"));
request.setAttribute("fileType",request.getParameter("fileType")==null?"":request.getParameter("fileType"));
request.setAttribute("fileTypeStr",request.getParameter("fileType")==null?"":request.getParameter("fileType"));
if("".equals(request.getAttribute("fileTypeStr"))){
	request.setAttribute("fileTypeStr","无限制");
}
request.setAttribute("fileCount",request.getParameter("fileCount")==null?"1":request.getParameter("fileCount"));

if(request.getParameter("fileSize")!=null && !"".equals(request.getParameter("fileSize"))){
	int fs = Integer.parseInt(request.getParameter("fileSize").toString());
	request.setAttribute("fileSizeStr",FileUploadAction.fileSizeToString(fs));
}else{
	request.setAttribute("fileSizeStr","无限制");
}

request.setAttribute("showBut","true".equals(request.getParameter("showBut"))?true:false);
if(request.getAttribute("fileListJson")==null){
	request.setAttribute("fileListJson", "[]");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>文件上传</title>
    <style type="text/css">
    body{
    	font-size:12px;
    	margin-left: 0px;
    	margin-top: 0px;
    	margin-right: 0px;
    	margin-bottom: 0px;
    }
    </style>
	<script type="text/javascript">
	function addFile(){
		var fileMax = parseInt($("#fileCount").val());
		if($("#div_files input").length>=fileMax){
			alert("最多一次上传"+fileMax+"个");return false;
		}
		var time = new Date().getTime();
		$('<div><input id="file_'+time+'" name="uploadFiles" type="file" style="width: 330" /><a href="#" onclick="removeFile(this); return false;">删除</a></div>').appendTo("#div_files");
		$('#file_'+time).validatebox({required:true,validType:'filetype' });
	}
	
	$(function(){
		var ids = "<c:out value="${ids}"/>";
		if(ids!="" && ids!="null"){//回调父页面
			var files = ${fileListJson};
			window.parent.fileuploadResult_4d1fb735e9074e6b8a3e22ca3a292164(ids,files);
			window.parent.closeFileUpload();//关闭上传页面
		}
	});
	
	function removeFile(but){
		but.parentNode.removeNode(true);
	}
	
	function fileSubmit(){
		if(validate()){
			$('#form1')[0].submit();
		}
	}
	
	function validate(){
		return $('#form1').form("validate");
	}
	
	var fileType = "<%=request.getParameter("fileType")%>".toUpperCase();
	$.extend($.fn.validatebox.defaults.rules, {   
      filetype: {   
          validator: function(value, param){
			  if(fileType=='' || fileType=='null' || fileType=='NULL'){return true;}
			  var ns = value.split(".");
			  var hz = ns[ns.length-1].toUpperCase();
              return fileType.indexOf(hz)>-1;   
          },   
          message: '文件类型不符合.'  
      }   
  	});  
	</script>
  </head>
  
  <body>
  		<span style="color: red"><c:out value="${msg}"></c:out> </span>
     	<form id="form1" action="fileupload.action" method="post" enctype="multipart/form-data" >
     		<input name="groupId" type="hidden" value="<c:out value="${groupId}" />" />
     		<div style="display: none;">
	    		 文件最大设置:<input name="fileSize" type="text" value="<c:out value="${fileSize}" />"  /><br>
				目录名称:<input name="dirName" type="text" value="<c:out value="${dirName}" />" /><br>
				文件类型:<input name="fileType" type="text" value="<c:out value="${fileType}" />" /><br>
				文件最多上传个数:<input id="fileCount" name="fileCount" type="text" value="<c:out value="${fileCount}" />" /><br>
			</div>
    		<b>允许类型：</b><span style="color: blue;"><c:out value="${fileTypeStr}" />&nbsp;&nbsp;</span>
    		<b>最大大小：</b><span style="color: blue;"><c:out value="${fileSizeStr}" /></span>
    		<br>
    
    		<div id="div_files">
    			<div><input name="uploadFiles" type="file" style="width: 330" class="easyui-validatebox" required="true" validType="filetype"/></div>
    		</div>
    		<div align="left">
	    		<c:if test="${showBut}">
		    		&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" onclick="addFile();return false;">添 加</a>
	    		</c:if>
	    		&nbsp;&nbsp;
	    		<a href="#" class="easyui-linkbutton" onclick="fileSubmit();" style="display:none;">提 交</a>
    		</div>
    	</form>
  </body>
</html>
