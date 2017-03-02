<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>用户组</title>
	<script type="text/javascript">
		var url = {save:'${basePath}/view/system/group!saveGroupUser.action', 
		'delete':'${basePath}/view/system/group!deleteGroupUser.action', 
		load:'${basePath}/view/system/group!loadGroupUser.action', 
		query:'${basePath}/view/system/group!findGroupUser.action'};
		var title = "用户组用户管理";
		var pojoName = "groupUser";
		
		$(function(){
			$('#tab_list').datagrid({
				title:'查询列表',url:url['query']
			});
			$("#div_window_edit").dialog({title:title});
			initGroup();
			initOrg();
		});
		
		function initGroup(){
			var data = {};
			data['queryParams.businessType'] = "comment";
			var url = "${basePath}/view/system/group!findGroup.action";
			$.ajax( {
				type: "POST",	url: url,	data: data, dataType: "json",error: function(e) {alert("系统交互异常");},
				success: function(json){
					json.rows.splice(0,0,{groupName:'--非用户组--',id:'noGroup'});
					json.rows.splice(0,0,{groupName:'--请选择--',id:''});
					$('#groupId').combobox('loadData',json.rows);
					var rows = CopyArray(json.rows,2);
					rows.splice(0,0,{groupName:'--非用户组--',id:''});
					$('#formGroup').combobox('loadData',rows);
				}	
			});
		}
		
		function initOrg(){
			var url = "${basePath}/view/system/orgTree.action";
			$.ajax( { type : "POST", url : url, dataType : "json",error: function(e) {alert("系统交互异常");},
				success : function(json) {
					var root = json.trees[0];
					root.state = "open";
					$("#formOrg").combotree("loadData", json.trees);
				}
			});
		}
		
		function orgSelect(rec){
			var url = "${basePath}/view/system/user!queryUserList.action";
			var data = {rows:999, 'queryParams.orgId':rec.id};
			$.ajax( { type:"POST", url:url, dataType:"json", data:data, error: function(e) {alert("系统交互异常");},
				success : function(json) {
					$("#formUser").combobox("loadData", json.rows);
				}
			});
			
		}
		
		function removeEditForm(id){
			confirm("提示","删除所选"+title+"?",function(r){
				if(r){
					var data = {};
					data[pojoName+".id"] = id;
					$.ajax( { type: "POST",	url: url['delete'],	data: data, dataType: "json",error: function(e) {alert("系统交互异常");},
						success: function(json){
							alert('删除成功');
							query();
						}	
					});
				}
			});
		}
		
		function loadEditForm(id, orgId){
			$('#form_edit').form("clear");
			var data = {};
			data[pojoName+".id"] = id;
			$.ajax( {
				type: "POST",	url: url['load'],	data: data, dataType: "json",error: function(e) {alert("系统交互异常");},
				success: function(json){
					json[pojoName+"."+'businessType'] = "comment";
					setEditForm('form_edit',json, pojoName);
					$('#formGroup').combobox("setValue",json[pojoName+"."+'groupId']);
					$('#formOrg').combotree("setValue",orgId);
					$('#formUser').combobox("setValue",json[pojoName+"."+'userId']);
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
		
		function formatterUserType(value,rec){
			return value=='1'?"用户":(value=="2"?"用户组":"");
		}
	
		function formatterAction(value,rec){
			//return "<a href='#' onclick='loadEditForm(\""+rec.id+"\", \""+rec.orgId+"\"); return false;'>查看</a>&nbsp;"
			//+"<a href='#' onclick='removeEditForm(\""+rec.id+"\"); return false;'>删除</a>";
			return "<a href='#' onclick='removeEditForm(\""+rec.id+"\"); return false;'>删除</a>";
		} 
	</script>
  </head>
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="" style="height:42px;">
  		<table id="from_query"  dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" style="width: 80">用户组:</td>
  				<td ><input id="groupId" name="queryParams.groupId" class="easyui-combobox" textField="groupName" valueField="id" /> </td>
  				<td align="right" style="width: 80">用户名称:</td>
  				<td ><input name="queryParams.userName" maxlength="30" size="10" /> </td>
  				<td align="right">录入时间:</td>
  				<td >
  					<input name="queryParams.beginDate" class="easyui-datebox" style="width: 90"/>至
  					<input name="queryParams.endDate" class="easyui-datebox" style="width: 90"/>
  				</td>
  			</tr>
  		</table>
  	</div>
  	
  	<div region="center">
	    <table id="tab_list" rownumbers="true" region="center"  style="width:auto;height:auto" pagination="true"  singleSelect="true">
			<thead>
				<tr>
					<th field="userType" width="60" formatter="formatterUserType" >用户类型</th>
					<th field="groupName" width="100"  >用户组</th>
					<th field="userName" width="120"  >用户名称</th>
					<th field="orgName" width="200"  >用户机构</th>
					<th field="createTime" width="120" formatter="formatterDatetime" >创建时间</th>
					<th field="cl" width="60" formatter='formatterAction'>操作</th>
				</tr>
			</thead>
		</table>
		
		<div id="div_window_edit" style="width: 460px; height: 190px; " resizable="false" collapsible="false" maximizable="false"
				 minimizable="false" closed="true" buttons="#div_window_edit_buts">
			<div class="bgColor" style="height: 100%;">
			<form action="" id="form_edit">
				<table id="form_role_edit" action="roleSave.action" class="tablestyle01" style="width:100%">
		  			<tr style="display: none;">
		  				<td align="right" style="width:80px;">ID:</td>
		  				<td ><input readonly="readonly" id="inp_id" name="id" maxlength="240" style="width: 96%;" /></td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width:80px;">用户组:</td>
		  				<td ><input id="formGroup" required="true" name="groupId" maxlength="30" class="easyui-combobox"  textField="groupName" valueField="id" style="width:320" /> </td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width:80px;">机构:</td>
		  				<td ><input class="easyui-combotree" id="formOrg" maxlength="30"  textField="text" valueField="id" style="width:320" data-options="onSelect:orgSelect" /> </td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width:80px;">用户:</td>
		  				<td ><input class="easyui-combobox" required="true" id="formUser" name="userId" maxlength="30" style="width: 320;" textField="uName" valueField="uId" /> </td>
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
