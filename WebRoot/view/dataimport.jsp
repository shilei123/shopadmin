<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
  	<title>数据导入</title>
	<script type="text/javascript">
		$(function(){
		});
		
		var showImportWin = function() {
			$('#err_msg_window').dialog('close');
			var params = {fileType:'xls'};
			showFileUpload(params, function(id){
				$.ajax( {
					type : "POST",url : "${basePath }/view/dataimport/dataImportAction!importData.action",dataType : "json", data:{fileId:id}, error:function(){alert("导入异常");},
					success : function(json) {
						if(json==null){
							$('#user_list').datagrid("reload");
							alert("导入成功");
						}else{
							console.log(json.errorMsg);
							document.getElementById("err_msg_span").innerHTML = "导入失败,错误信息：<br>"+json.errorMsg;
							$("#err_msg_window").window("open");
						}
					}
				});
			});
		};
	</script>
  </head>
  
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="数据导入" style="height:65px; ">
  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="left">
		  			<a href="#" class="easyui-linkbutton" onclick="showImportWin();return false;">&nbsp;导&nbsp;入&nbsp;</a>
		 		</td>
		 	</tr>
  		</table>
  	</div>
  	<div id="div_config" region="center" title="导入记录" class="easyui-panel" data-options="collapsible:false">
		<table id="user_list" rownumbers="true" region="center" class="easyui-datagrid" fitColumns="true"
			url="${basePath }/view/system/user!queryUserList.action" style="width:auto;height:auto" title="" pagination="true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true" width="5%"></th>
					<th field="uName" width="25%">文件名称</th>
					<th field="uId" width="20%">导入时间</th>
					<th field="agencyName" width="20%">记录数量</th>
					<th field="lrSj" width="15%">导入状态</th>
					<th field="cl" width="15%">操作</th>
				</tr>
			</thead>
		</table>
		
		<div title="错误信息" id="err_msg_window" modal="true" draggable="false" class="easyui-dialog" style="width: 400px; height: 300px; background-color:#EFEFEF;" 
			resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true" buttons="#err_msg_buttons">
			<span id="err_msg_span"></span>
			<div id="err_msg_buttons" align="center" style="background-color:#EFEFEF;">
				<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
					<tr><td align="center">
 						<a href="#" class="easyui-linkbutton" onclick="$('#err_msg_window').dialog('close'); return false;">关闭</a>
					</td></tr>
				</table>
			</div>
		</div>
  	</div>
  </body>
</html>