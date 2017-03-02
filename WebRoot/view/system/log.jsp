<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>日志管理</title>
    
	<script type="text/javascript">
	function showLog(name,size){
		if((size/1024)>512){
			alert("日志大于512kb,建议下载查看");return false;
		}
		var data = {"logName":name};
		$.ajax({
			type: "POST", url: "log!query.action", data:data, dataType: "text",
			success: function(text){
				$("#log_content").val(text);
			  	$("#log_window").window("open");
			},error:function(){
			  	alert("获取日志失败");
			}
		});
	}
	function formatterAction(value,rec){
		return "<a href='#' onclick='showLog(\""+rec.name+"\","+rec.size+"); return false;'>查看</a>&nbsp;"+"<a href='#' onclick='filedown(\""+rec.name+"\",\"logname\"); return false;'>下载</a>&nbsp;";
	} 	
	function formatterKB(value,rec){
		return parseInt(value/1024)+"kb"
	}
	</script>
  </head>
  
   <body class="easyui-layout">
  	<div id="div_config" region="center" title="日志列表" class="easyui-panel bgColor" >
		<table id="log_list" rownumbers="true" region="center" class="easyui-datagrid" url="log!list.action" style="width:auto;height:auto" title=""   pagination="true">
			<thead>
				<tr>
					<th field="name" width="220"  >日志名称</th>
					<th field="time" width="140"  >修改日期</th>
					<th field="size" width="90" align="right" formatter='formatterKB'>文件大小</th>
					<th field="cl" width="80" formatter='formatterAction'>查看</th>
				</tr>
			</thead>
		</table>
		
		<div id="log_window" closed="true" minimizable="false"  maximizable="false" collapsible="false" title="日志内容" class="easyui-window" style="width: 640px; height: 380px;">
			<textarea id="log_content" style="width: 100%; height: 99%;"></textarea>
		</div>
  	</div>
    <div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px;" >
    	<a href="#" class="easyui-linkbutton" onclick="saveTree();">查询</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>

