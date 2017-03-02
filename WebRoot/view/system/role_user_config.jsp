<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>角色用户配置管理</title>
	<script type="text/javascript">
		var currRoleId = null;
		$(function(){
			initApp();
			initOrgTree();
			initPositionSelect();
		});
		
		//初始化全体机构树
		function initOrgTree() {
			$.ajax( { type : "POST", url : "${basePath}/view/system/orgTree.action", dataType : "json",
				success : function(json) {
					var root = json.trees[0];
					root.state = "open";
					json.trees.push({id:'',text:'请选择'});
					$("#orgId").combotree("loadData", json.trees.reverse());
				}
			});
		}
		
		//初始化职责下拉框
		function initPositionSelect() {
			var url = "${basePath}/view/system/user!queryPositions.action";
			$.ajax( {
				type: "POST",	
				url: url,
				dataType: "json",
				success: function(json){
					//json.positions.unshift({positionId:'',positionName:'请选择'});
					json.positions.splice(0,0,{positionId:'',positionName:'请选择'});
					$("#positionCombox").combobox({
				    	required:false,
				    	editable:false,
						data:json.positions,
						valueField:'positionId',
				    	textField:'positionName'
					});
				}
			});
		}
		
		function roleMenuSave(){
			if(currRoleId==null){alert("未选择相应的角色");return false;}
			var ids = "";
			if(ids.length==0){alert("未选择相应的菜单");return false;}
			var data = $.param({"role.roleId":currRoleId,"treeIds":ids}, true);
			$.ajax({
			  type: "POST", url: "roleMenuSave.action", data:data, dataType: "json",
			  success: function(json){
			  	alert("保存成功");
			  },error:function(){
			  	alert("保存失败");
			  }
			});
		}
		
		
		function initApp(){
			var url = "${basePath}/view/system/comboboxRoles.action";
			$.ajax({
			  type: "POST", url: url, dataType: "json",
			  success: function(json){
			  	var data = [];
			  	$(json.roles).each(function(i,node){
			  		data.push({"id":node.roleId,"text":node.roleName});
			  	});
			  	$('#ul_role_tree').tree('append',{data:data});
			  }
			});//初始化角色功能树
			
		}
		
		function roleTreeClick(){
			var node = $('#ul_role_tree').tree("getSelected");
			currRoleId = node.id;
			//alert(currRoleId);
			var data = {"roleUser.id.roleId":currRoleId};
			$("#user_list").datagrid({"queryParams":data});
		}
		
		function addRolePersonWinShow() {
			if(currRoleId == null || currRoleId == '') {
				alert("请选择一个角色！");
			} else {
	        	$('#user_select_table').datagrid({   
					url:'user!queryUserList.action?queryParams.roleId='+currRoleId
	        	});
	        	$('#user_select_window').dialog('open');
        	}
		}
		
		function queryRoleUserList() {
			$('#user_list').datagrid({   
				url:'user!queryRoleUser.action'
        	});
		}
		
		function addRoleUser(uId) {
			if(uId==null){
				var rows= $('#user_select_table').datagrid("getSelections");
				if(rows.length==0){alert("至少选中一个人员");return false;}
				uId = ""; var m = "";
				if(rows!=null){
					for(var i=0;i<rows.length;i++){
						uId+= m+rows[i].uId;m=",";
					}
				}
			}
			$.ajax({
			  type: "POST", 
			  url: "user!insert.action", 
			  dataType: "json",
			  data: "uId="+uId+"&roleId="+currRoleId,
			  success: function(json){
				if(json.roleUserMsg=='error'){
					alert("该人员已经添加！");
				} else {
				  	alert("添加人员成功！");
					$('#user_select_window').dialog('close');
					queryRoleUserList();
				}
			  }
			});
		}
		
		function queryUser() {
			var data = formGet("form_user_query");
			data['queryParams.roleId'] = currRoleId;
			//$('#user_select_table').datagrid('options').url="user!queryUserList.action";
			$('#user_select_table').datagrid('load',data);
		}
		
		function deleteRoleUser(uId,rname) {
			var name = rname;
			if(currRoleId=='e97b1283-8fc2-44d5-9627-18795097df31' && uId=="admin") {
				alert("不允许删除超级管理员，此操作可能会引起系统发生异常！");
				return;
			}
			if(uId==null){
				uId = ""; var m=""; name="所选用户";
				var rows= $('#user_list').datagrid("getSelections");
				if(rows!=null){
					for(var i=0;i<rows.length;i++){
						uId+= m+rows[i].uId;m=",";
						if(currRoleId=='e97b1283-8fc2-44d5-9627-18795097df31' && rows[i].uId=="admin") {
							alert("不允许删除超级管理员，此操作可能会引起系统发生异常！");
							return;
						}
					}
				}
			}
			
			if(uId==""){alert("至少选择一个用户");return false;}
			window.confirm("提示","删除"+name+"?",function(r){
				if(r){
					$.ajax({
						type: "POST", 
					  	url: "user!delete.action", 
					  	dataType: "json",
					  	data: "uId="+uId+"&roleId="+currRoleId,
					  	success: function(json){
							if(json.roleUserMsg=='ok'){
								alert("删除角色人员成功！");
								queryRoleUserList();
							} 
					  	}
					});
				}
			});
		}
		
		function formatterAction(value,rec){
			return "<a href='#' onclick='addRoleUser(\""+rec.uId+"\"); return false;'>选择</a>&nbsp;";
		}
		
		function formatterOrgPath(val,rec){
			return val==null?"":val.replace("中国工程物理研究院应用电子学研究所/","");;
		}
		
		function formatterRoleUserListAction(value,rec){
			return "<a href='#' onclick='deleteRoleUser(\""+rec.uId+"\",\""+rec.uName+"\"); return false;'>删除</a>&nbsp;";
		}
	</script>
  </head>
  <body class="easyui-layout">
  	<div region="west" style="width:200px; "  title="角色列表">
	   <ul id="ul_role_tree" class="easyui-tree" ondblclick="roleTreeClick()"></ul>
	</div>
  	<div region="center" title="用户列表">
  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="left">
		  			<a href="#" class="easyui-linkbutton" onclick="roleMenuSave();return false;">保存</a>
		  			<a href="#" class="easyui-linkbutton" onclick="addRolePersonWinShow();return false;">添加人员</a>
		  			<a href="#" class="easyui-linkbutton" onclick="deleteRoleUser(null,null);return false;">删除</a>
			 		<a href="#" class="easyui-linkbutton" onclick="winReload(); return false;">刷新</a>
			 		<a href="#" class="easyui-linkbutton" onclick="winClose(); return false;">关闭</a>
		 		</td>
		 	</tr>
  		</table>
		<table id="user_list" rownumbers="true" region="center" class="easyui-datagrid" fitColumns="true"
			url="user!queryRoleUser.action" style="width:auto;height:auto" title="" pagination="true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true" width="5%"></th>
					<th field="uName" width="25%">姓名</th>
					<th field="orgPath" width="40%">所属机构</th>
					<th field="positionName" width="20%">职责</th>
					<th field="cl" width="10%" formatter="formatterRoleUserListAction">操作</th>
				</tr>
			</thead>
		</table>
		
		<div title="人员选择" id="user_select_window" modal="true" draggable="false" class="easyui-dialog" 
			style="width: 750px; height: 450px; background-color:#EFEFEF;"  buttons="#user_select_buts"
			resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
			<table id="form_user_query"  dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="right" style="width: 60">用户姓名:</td>
	  				<td><input name="queryParams.userName" /></td>
	  				<td align="right">职责:</td>
	  				<td><input id="positionCombox" style="width:185px;" name="queryParams.positionId" class="easyui-combobox"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right">所属机构:</td>
	  				<td colspan="3"><input name="queryParams.orgId" style="width:300px;" 
	  				id="orgId" class="easyui-combotree" filedText="text" filedValue="id" /> </td>
	  			</tr>
	  		</table>
			<table id="user_select_table" rownumbers="true" region="center" class="easyui-datagrid" fitColumns="true"
				style="width:auto;height:auto" title="" pagination="true" singleSelect="false">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="uName" width="90">用户姓名</th>
						<th field="orgPath" width="460" formatter='formatterOrgPath'>所属机构</th>
						<th field="positionName" width="150">职责</th>
						<th field="cl" width="50" formatter='formatterAction'>操作</th>
					</tr>
				</thead>
			</table>
			
			<div id="user_select_buts" align="center" style="background-color:#EFEFEF;">
				<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
					<tr><td align="center">
 						<a href="#" class="easyui-linkbutton" onclick="queryUser(); return false;">查询</a>
						<a href="#" class="easyui-linkbutton" onclick="addRoleUser(); return false;">选择</a>
		 				<a href="#" class="easyui-linkbutton" onclick="$('#user_select_window').dialog('close'); return false;">关闭</a>
					</td></tr>
				</table>
			</div>
		</div>
	</div>
  </body>
</html>