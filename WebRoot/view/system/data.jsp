<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
  	<title>数据管理</title>
	<script type="text/javascript">
	$(function(){
		
	});
	
	function dataFileuploadSuccess(){
		$('#import_win').dialog('close');
		alert("导入成功");
	}
	
	function query(){
		var data = {name:$("#inp_name").val()};
		$('#pojo_list').datagrid("load",data);
	}
	
	function exportData(id){
		var url = "${basePath}/view/system/data!exportXml.action";
		url += "?pojos="+id;
		url += "&where="+  encodeURIComponent($("#inp_where").val());
		window.open(url);
	}
	
	function exportDataBut(){
		var data = $('#pojo_list').datagrid("getSelections");
		if(data==null || data.length==0){alert('请选择需要导出的数据对象');return false;}
		var ids = "";
		var m = "";
		for(var i=0;i<data.length;i++){
			ids += m+data[i].name;m=",";
		}
		exportData(ids);
		return false;
	}
	
	function formatterAction(value,rec){
			return "<a href='#' onclick='exportData(\""+rec.name+"\"); return false;'>导出</a>";
	}
	
	function showImportWin(){
		$("#iframe_import").attr("src","${basePath}/view/system/data_fileupload.jsp?timestamp="+(new Date().getTime()));
		$('#import_win').dialog('open');
	}
	</script>
  </head>
  
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="数据管理" style="height:64px; ">
  		<table id="from_query"  dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" style="width: 100">对象名称:</td>
  				<td>
  					<input name="name" id="inp_name" maxlength="200" />
  				</td>
  				<td align="right">导出附加条件:</td>
  				<td ><input name="where" id="inp_where" maxlength="500"  size="30" /></td>
  				<td align="right">&nbsp;</td>
  				<td >&nbsp;</td>
  			</tr>
  		</table>
  	</div>
  	<div id="div_config" region="center" title="数据对象列表" class="easyui-panel" data-options="collapsible:false">
		<table id="pojo_list" rownumbers="true" region="center" class="easyui-datagrid" fitColumns="true"
			url="${basePath}/view/system/data!list.action" style="width:auto;height:320" title="" >
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true" width="5%"></th>
					<th field="name" width="85%">数据对象名称</th>
					<th field="cl" width="10%" formatter='formatterAction'>操作</th>
				</tr>
			</thead>
		</table>
		
		<div title="导入数据" id="import_win" class="easyui-dialog" style="width: 380px; height: 120px; background-color:#EFEFEF;" 
			resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true" buttons="#import_buttons">
				<iframe id="iframe_import" style="width: 100%; height: 100%;" src=""></iframe>
			<div id="import_buttons">
				<a href="#" class="easyui-linkbutton" onclick="$('#iframe_import')[0].contentWindow.submitForm();">提交</a>
 				<a href="#" class="easyui-linkbutton" onclick="$('#import_win').dialog('close');">关闭</a>
			</div>
		</div>
  	</div>
  	
  	<div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px;" >
    	<a href="#" class="easyui-linkbutton" onclick="query();">查询</a>
    	<a href="#" class="easyui-linkbutton" onclick="exportDataBut();">导出</a>
    	<a href="#" class="easyui-linkbutton" onclick="showImportWin();">导入</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>