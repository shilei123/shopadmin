<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>角色管理</title>
	<script type="text/javascript">
		$(function(){
			
		});
		
		function roleEditWinShow(){
			fromReset("form_role_edit");
			$('#div_window_new').window({title:"角色新增"});
			$("#div_window_new").dialog("open");
		}
		
		function fromReset(fromId){
			$("#"+fromId+" :input").each(function(i,n){
				if(n.tagName=='SELECT'){
					n.selectedIndex = 0;
				}else{
					n.value = "";
				}
			});
		}
		
		function roleEditSubmit(json){
			if(json==null){
				if($('#inp_role_id').val().indexOf('_')>-1){
			 		alert("特殊角色不允许修改");
			 		return false;
			 	}
				formSubmit("form_role_edit",roleEditSubmit);
			}else{
				alert("保存成功");
				$('#div_window_new').dialog('close');
				queryRole();
			}
		}
		
		function deleteRole(roleId,rname){
		 	if(roleId.indexOf('_')>-1){
		 		alert("特殊角色不允许删除");
		 		return false;
		 	}
			window.confirm("提示","删除"+rname+"?",function(r){
				if(r){
					$.ajax( {
						type: "POST",	url: "roleDelete.action",	data: {"role.roleId": roleId}, dataType: "json",
						success: function(json){
							if(json.message!=null && json.message!=""){
								alert(json.message);
								return false;
							}
							alert("删除成功");
							queryRole();
						},	
						error: function(e) {alert("删除角色异常");}
					});
				}
			});
		}
		
		function roleEditWinInit(roleId){
			$.ajax( {
				type: "POST",	url: "roleSingle.action",	data: {"role.roleId": roleId}, dataType: "json",
				success: function(json){
					formSet("form_role_edit", json.role);
					$('#div_window_new').window({title:"角色信息"});
					$("#div_window_new").dialog("open");
				},	
				error: function(e) {alert("查询角色异常");}
			});
		}
		
		function queryRole(){
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		function formatterAction(value,rec){
			return "<a href='#' onclick='roleEditWinInit(\""+rec.roleId+"\"); return false;'>查看</a>&nbsp;"
			+"<a href='#' onclick='deleteRole(\""+rec.roleId+"\",\""+rec.roleName+"\"); return false;'>删除</a>";
		} 
		
		var roleTypeMap = {'1':'工作流角色','0':'系统角色'};
		function formatterRoleType(value,rec){
			if(value==null){return "";}
			return roleTypeMap[value];
		} 
	</script>
  </head>
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="角色列表" style="height:68px;">
  		<table id="from_query" dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" style="width: 100">角色名称:</td>
  				<td><input name="queryParams.roleName" maxlength="20" /> </td>
  				<!--<td align="right">角色类型:</td>
  				 <td>
  					<select name="queryParams.roleType">
  						<option value="">全部</option>
	  					<option value="0">系统角色</option>
	  					<option value="1">工作流角色</option>
	  				</select>
  				</td> -->
  				<td align="right">&nbsp;</td>
  				<td>&nbsp;</td>
  			</tr>
  		</table>
  	</div>
  	
  	<div region="center">
	    <table id="tab_list" rownumbers="true" region="center" class="easyui-datagrid" fitColumns="true"
	    	url="roleQuery.action" style="width:auto;height:auto" title="" pagination="true">
			<thead>
				<tr>
					<!-- <th field="roleId" width="40%">角色ID</th> -->
					<th field="roleName" width="40%">角色名称</th>
					<!-- <th field="roleType" formatter="formatterRoleType" width="20%">角色类型</th> -->
					<th field="roleRemark" width="39%">角色说明</th>
					<th field="cl" formatter='formatterAction'width="20%">操作</th>
				</tr>
			</thead>
		</table>
		
		<div title="角色新增" id="div_window_new" class="easyui-dialog bgColor" modal="true" draggable="false" 
			style="width: 460px; height: 240px; " buttons="#buts_window_new"
			resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
			<div style="height: 100%;" class="bgColor">
			<table id="form_role_edit" action="roleSave.action" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="right" style="width:80px;">角色ID:</td>
	  				<td ><input readonly="readonly" id="inp_role_id" name="role.roleId" maxlength="240" style="width: 96%;" /></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width:80px;">角色名称:</td>
	  				<td ><input class="easyui-validatebox" required="true" name="role.roleName" maxlength="240" style="width: 96%;" /> </td>
	  			</tr>
	  			<!-- <tr>
	  				<td align="right" style="width:80px;">角色类型:</td>
	  				<td>
	  					<select name="role.roleType">
	  						<option value="0">系统角色</option>
	  						<option value="1">工作流角色</option>
	  					</select>
	  				</td>
	  			</tr> -->
	  			<tr style="display: none;">
	  				<td align="right" style="width:80px;">动态映射:</td>
	  				<td>
	  					<select name="role.roleStateValue">
	  						<option value="">无映射</option>
	  					</select>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width:80px;">角色说明:</td>
	  				<td ><textarea  class="easyui-validatebox" name="role.roleRemark" validType="maxLength[2000]" style="width: 100%; height:50;"></textarea> </td>
	  			</tr>
	  		</table>
	  		<div id="buts_window_new" align="right" >
	  			<a href="#" class="easyui-linkbutton" onclick="roleEditSubmit(null); return false;">提交</a>
	  			<a href="#" class="easyui-linkbutton" onclick="$('#div_window_new').dialog('close');return false;">关闭</a>
	  		</div>
	  		</div>
		</div>
	</div>
	
	<div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
		<a href="#" class="easyui-linkbutton" onclick="queryRole(); return false;">查询</a>
		<a href="#" class="easyui-linkbutton" onclick="roleEditWinShow(); return false;">新增</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>
