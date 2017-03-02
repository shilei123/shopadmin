<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>用户组</title>
	<script type="text/javascript">
		var url_save = "";
		var url_delete = "";
		var url_query = "";
		var url_load = "";
		var url = {save:'${basePath}/view/system/group!saveGroup.action', 
		'delete':'${basePath}/view/system/group!deleteGroup.action', 
		load:'${basePath}/view/system/group!loadGroup.action', 
		query:'${basePath}/view/system/group!findGroup.action'};
		var title = "用户组管理";
		var pojoName = "group";
		
		$(function(){
			$('#tab_list').datagrid({
				title:'查询列表',url:url['query']
			});
			$("#div_window_edit").dialog({title:title});
		});
		
		function removeEditForm(id){
			confirm("提示","删除所选"+title+"?",function(r){
				if(r){
					var data = {};
					data[pojoName+".id"] = id;
					$.ajax( {
						type: "POST",	url: url['delete'],	data: data, dataType: "json",error: function(e) {alert("系统交互异常");},
						success: function(json){
							alert('删除成功');
							query();
						}	
					});
				}
			});
		}
		
		function loadEditForm(id){
			var data = {};
			data[pojoName+".id"] = id;
			$.ajax( {
				type: "POST",	url: url['load'],	data: data, dataType: "json",error: function(e) {alert("系统交互异常");},
				success: function(json){
					json[pojoName+"."+'businessType'] = "comment";
					setEditForm('form_edit',json, pojoName);
					showEditWin();
				}	
			});
		}
		
		function saveEditForm(){
			if(!$('#form_edit').form("validate")){return false;}
			var data = getEditForm('form_edit', pojoName);
			data[pojoName+"."+'businessType'] = "comment";
			$.ajax( {
				type: "POST",	url: url['save'],	data: data, dataType: "json",error: function(e) {alert("系统交互异常");},
				success: function(json){
					alert("保存成功");
					setEditForm('form_edit',json, pojoName);
					query();
				}	
			});
		}
		
		function clearEditForm(){
			$('#form_edit').form('clear');
		}
		
		function showEditWin(){
			$('#div_window_edit').dialog('open');
		}
		
		function query(){
			var data = formGet("from_query");
			data['queryParams.businessType'] = "comment";
			$("#tab_list").datagrid("load", data);
		}
	
		function formatterAction(value,rec){
			return "<a href='#' onclick='loadEditForm(\""+rec.id+"\"); return false;'>查看</a>&nbsp;"
			+"<a href='#' onclick='removeEditForm(\""+rec.id+"\"); return false;'>删除</a>";
		} 
	</script>
  </head>
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="" style="height:42px;">
  		<table id="from_query"  dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" style="width: 100">用户组名称:</td>
  				<td ><input name="queryParams.groupName" maxlength="30" /> </td>
  				<td align="right">录入时间:</td>
  				<td >
  					<input name="queryParams.beginDate" class="easyui-datebox" style="width: 90"/>至
  					<input name="queryParams.endDate" class="easyui-datebox" style="width: 90"/>
  				</td>
  				<td align="right">&nbsp;</td>
  				<td >&nbsp;</td>
  			</tr>
  		</table>
  	</div>
  	
  	<div region="center">
	    <table id="tab_list" rownumbers="true" region="center"  style="width:auto;height:auto" pagination="true">
			<thead>
				<tr>
					<th field="groupName" width="160"  >用户组名称</th>
					<th field="createTime" width="120" formatter="formatterDatetime" >用户组名称</th>
					<th field="cl" width="80" formatter='formatterAction'>操作</th>
				</tr>
			</thead>
		</table>
		
		<div id="div_window_edit" style="width: 460px; height: 120px; " 
			resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true" buttons="#div_window_edit_buts">
			<div class="bgColor" style="height: 100%;">
			<form action="" id="form_edit">
				<table id="form_role_edit" action="roleSave.action" class="tablestyle01" style="width:100%">
		  			<tr style="display: none;">
		  				<td align="right" style="width:80px;">ID:</td>
		  				<td ><input readonly="readonly" id="inp_id" name="id" maxlength="240" style="width: 96%;" /></td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width:80px;">用户组名称:</td>
		  				<td ><input class="easyui-validatebox" required="true" name="groupName" maxlength="30" style="width: 96%;" /> </td>
		  			</tr>
		  		</table>
			</form>
			</div>
	  		<div id="div_window_edit_buts">
	  			<a href="#" class="easyui-linkbutton" onclick="saveEditForm(); return false;">提交</a>
	  			<a href="#" class="easyui-linkbutton" onclick="$('#div_window_edit').dialog('close');return false;">关闭</a>
	  		</div>
		</div>
	</div>
	
	<div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
		<a href="javascript:query();" class="easyui-linkbutton" >查询</a>
		<a href="javascript:clearEditForm();showEditWin();" class="easyui-linkbutton" >新建</a>
 		<a href="javascript:winReload();" class="easyui-linkbutton">刷新</a>
 		<a href="javascript:winClose();" class="easyui-linkbutton" >关闭</a>
    </div>
  </body>
</html>
