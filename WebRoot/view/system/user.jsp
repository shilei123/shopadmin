<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
  	<title>用户管理</title>
	<script type="text/javascript">
		function checkUser(json){
			return false;
		}
		
		function userDetailWinInit(userId){
			$.ajax( {
				type: "POST",	
				url: "user!queryUserEmp.action",	
				data: {"user.userId": userId}, dataType: "json",
				success: function(json){
					formSet("user_datail_table", json.user);
					$("#user_detail_window").window("open");
				},	
				error: function(e) {alert("查询异常");}
			});
		}
		
		function userEditWinInit(userId){
			$.ajax( {
				type: "POST",	
				url: "user!preEditEmp.action",	
				data: {"user.userId": userId}, dataType: "json",
				success: function(json){
					$("#positionCombox").combobox({
				    	required:false,
				    	editable:false,
						data:json.positions,
						valueField:'positionId',
				    	textField:'positionName'
					});
				    $("#userSexCombox").combobox({
				    	editable:false
					});
				    $('#positionCombox').combobox('setValue', json.user.positionId);
				    $('#userSexCombox').combobox('setValue', json.user.sex);
				    formSet("user_edit_table", json.user);
					$("#user_edit_window").window("open");
				},
				error: function(e) {alert("查询异常");}
			});
		}
		
		function userAddWinShow() {
			$.ajax({
				url: "user!preAddEmp.action",
				cache: false,
				dataType:"json",
				success: function(json){
				    $("#positionComboxAdd").combobox({
				    	required:false,
				    	editable:false,
						data:json.positions,
						valueField:'positionId',
				    	textField:'positionName'
					});
				    $("#userSexComboxAdd").combobox({
				    	editable:false
					});
				    formReset("user_add_table");
					$("#user_add_window").window("open");
					//$("#userSexComboxAdd").combobox("setValue","");
					//$("#positionComboxAdd").combobox("setValue",-1);
				}
			});
		}
		
		var selectOrgSourceFlag = null;
		function selectOrgTree(sourceFlag) {
			selectOrgSourceFlag = sourceFlag;
			$.ajax( {
				type : "POST",
				url : "orgTree.action",
				dataType : "json",
				success : function(json) {
					/*var a = $('#user_edit_window').window("options").zIndex;
					alert("user_edit_window:"+a);
					$("#org_tree_window").window({zIndex:a+1});
					var b = $('#org_tree_window').window("options").zIndex;
					alert("org_tree_window:"+b);*/
					var root = json.trees[0];
					root.state = "open";
					$("#ul_org_tree").tree("loadData", json.trees);
					$("#org_tree_window").window("open");
				}
			});
		}
		
		function selectOrg() {
			var node = $("#ul_org_tree").tree("getSelected");
			if(node==null){
				alert("请选择一个机构！");
				return false;
			}
			var msg = getOrgInfo(node);
			if(msg.orgLevel == 0) {
				alert("不能选择根机构！");
			} else {
				if(selectOrgSourceFlag != null) {
					if(selectOrgSourceFlag == 'add') {
						document.getElementById("orgIdForAddWin").value = msg.id;
						document.getElementById("orgPathForAddWin").value = msg.orgPath;
					} else if(selectOrgSourceFlag == 'edit') {
						document.getElementById("orgIdForEditWin").value = msg.id;
						document.getElementById("orgPathForEditWin").value = msg.orgPath;
					}
				}
			}
			$("#org_tree_window").window("close");
		}
		
		function getOrgInfo(node){
			var obj = new Object();
			obj.orgId = node.id;
			obj.orgName = node.text;
			if(node.attributes!=null){
				obj.orgPath = node.attributes.orgPath;
				obj.id = node.attributes.orgPkId;
				obj.order = node.attributes.order;
				obj.orgLevel = node.attributes.orgLevel;
				obj.shortName = node.attributes.shortName;
			}
			var pnode = $('#ul_org_tree').tree('getParent',node.target); 
			if(pnode!=null){
				obj.parentOrgId = pnode.id;
				obj.parentOrgName = pnode.text;
				obj.parentShortName = pnode.attributes.shortName;
				obj.parentOrgPath = pnode.attributes.orgPath;
			}
			return obj;
		}
		
		function userEditSave(json) {
			/*var positionValue = $('#positionComboxAdd').combobox('getValue');
			if(positionValue==-1 || positionValue=='-1') {
				$("#positionComboxAdd").combobox({ required:true });
				return;
			}*/
			if(json==null){
				formSubmit("user_edit_table",userEditSave);
			}else{
				alert("保存成功");
				$('#user_edit_window').window('close');
				queryUser();
			}
		}
		
		function userAddSave(json) {
			/*var positionValue = $('#positionComboxAdd').combobox('getValue');
			if(positionValue==-1 || positionValue=='-1') {
				$("#positionComboxAdd").combobox({ required:true });
				return;
			}*/
			if(json==null){
				formSubmit("user_add_table",userAddSave);
			}else{
				alert("新增成功");
				$('#user_add_window').window('close');
				queryUser();
			}
		}
		
		function queryUser(){
			var data = formGet("from_query");
			$("#user_list").datagrid({"queryParams":data});
		}
		
		function formatterAction(value,rec){
			var formatterStr = "<a href='#' onclick='userDetailWinInit(\""+rec.uId+"\"); return false;'>查看</a>&nbsp;"
			+"<a href='#' onclick='userEditWinInit(\""+rec.uId+"\"); return false;'>编辑</a>&nbsp;";
			//if(rec.uId != 'admin') {
				formatterStr += "<a href='#' onclick='deleteUser(\""+rec.uId+"\",\""+rec.uName+"\"); return false;'>删除</a>";
			//}
			return formatterStr;
		}

		function deleteUser(uId,rname) {
			var name = rname;
			if(uId=="admin") {
				alert("不允许删除超级管理员，此操作可能会引起系统发生异常！");return false;
			}
			if(uId==null){
				uId = ""; var m=""; name="所选用户";
				var rows= $('#user_list').datagrid("getSelections");
				if(rows!=null){
					for(var i=0;i<rows.length;i++){
						uId+= m+rows[i].uId;m=",";
						if(rows[i].uId=="admin") {
							alert("不允许删除超级管理员，此操作可能会引起系统发生异常！");
							return false;
						}
					}
				}
			}
			
			if(uId==""){alert("至少选择一个用户");return false;}
			window.confirm("提示","删除"+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "user!deleteUser.action",  dataType: "json",
					  	data: "uId="+uId,
					  	success: function(json){
							if(json.message=='success'){
								alert("删除用户成功！");
								queryUser();
							}else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		function showUserImportWin(){
			$('#err_msg_window').dialog('close');
			var params = {fileType:'xls'};
			showFileUpload(params, function(id){
				$.ajax( {
					type : "POST",url : "user!importUsers.action",dataType : "text", data:{fileId:id}, error:function(){alert("导入异常");},
					success : function(json) {
						if(json==""){
							//$('#but_import').css('display','none');
							$('#user_list').datagrid("reload");
							alert("导入成功");
						}else{
							//alert("导入失败,错误信息：<br>"+json);
							document.getElementById("err_msg_span").innerHTML = "导入失败,错误信息：<br>"+json;
							$("#err_msg_window").window("open");
						}
					}
				});
			});
		}
		
		function importButIsShow(){
			$('#but_import').css('display','');
			$.ajax( {
				type : "POST",url : "user!queryUserList.action",dataType : "json",
				success : function(json) {
					if(json.rows.length<3){
						$('#but_import').css('display','');
					}
				}
			});
		}
		
		$(function(){
			importButIsShow();
		});
	</script>
  </head>
  
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="人员列表" style="height:100px; ">
  		<table id="from_query"  dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" style="width: 100">姓名:</td>
  				<td><input name="queryParams.userName" /></td>
  				<td align="right">IP</td>
  				<td><input name="queryParams.validateIp" /></td>
  				<td align="right">&nbsp;</td>
  				<td>&nbsp;</td>
  			</tr>
  		</table>
  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="left">
		  			<a href="#" class="easyui-linkbutton" onclick="queryUser();return false;">查询</a>
			    	<a href="#" class="easyui-linkbutton" onclick="userAddWinShow();return false;">新增</a>
			    	<a href="#" class="easyui-linkbutton" onclick="deleteUser(null,null);return false;">删除</a>
			 		<a href="#" class="easyui-linkbutton" onclick="winReload(); return false;">刷新</a>
		 		</td>
		 	</tr>
  		</table>
  	</div>
  	<div id="div_config" region="center" title="用户列表" class="easyui-panel" data-options="collapsible:false">
		<table id="user_list" rownumbers="true" region="center" class="easyui-datagrid" fitColumns="true"
			url="user!queryUserList.action" style="width:auto;height:auto" title="" pagination="true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true" width="5%"></th>
					<th field="uName" width="30%">姓名</th>
					<th field="validateIp" width="15%">IP</th>
					<th field="validateDomain" width="20%">域</th>
					<th field="lrSj" width="15%" formatter='formatterDatetime'>创建时间</th>
					<th field="cl" width="15%" formatter='formatterAction'>操作</th>
				</tr>
			</thead>
		</table>
		
		<div title="用户信息" id="user_detail_window" modal="true" draggable="false" class="easyui-window" style="width: 600px; height: 400px; background-color:#EFEFEF;" 
			resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
			<table id="user_datail_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="right" style="width: 80px">姓名:</td>
	  				<td style="width: 220px"><input name="user.userName" readonly="readonly" style="width: 100%;"/> </td>
	  				<td align="right" style="width: 80px">主机账户:</td>
	  				<td style="width: 220px">
	  					<!-- <input name="user.userId" readonly="readonly" style="width: 100%;"/> --> 
	  					<input name="user.pcusername" readonly="readonly" style="width: 100%;"/> 
	  				</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">域:</td>
	  				<td><input name="user.validateDomain" readonly="readonly" style="width: 100%;"/> </td>
	  				<td align="right" style="width: 80px">IP:</td>
	  				<td><input name="user.validateIp" readonly="readonly" style="width: 100%;"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">用户ID<span style="color: red;"><b>*</b></span>:</td>
	  				<td style="width: 200px"><input name="user.userId" readonly="readonly" style="width: 100%;"/></td>
	  				<td align="right" style="width: 80px">&nbsp;</td>
	  				<td style="width: 185px">&nbsp;</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">性别:</td>
	  				<td><input name="user.sexLabel" readonly="readonly" style="width: 100%;"/> </td>
	  				<td align="right" style="width: 80px">职责:</td>
	  				<td><input name="user.positionName" readonly="readonly" style="width: 100%;"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">所属机构:</td>
	  				<td colspan="3"><input name="user.orgPath" readonly="readonly" style="width: 100%;"/> </td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">办公地址:</td>
	  				<td colspan="3"><input name="user.workAddr" readonly="readonly" style="width: 100%;"/> </td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">固定电话:</td>
	  				<td><input name="user.telphone" readonly="readonly" style="width: 100%;"/> </td>
	  				<td align="right" style="width: 80px">移动电话:</td>
	  				<td><input name="user.mobile" readonly="readonly" style="width: 100%;"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">传真:</td>
	  				<td><input name="user.fax" readonly="readonly" style="width: 100%;"/> </td>
	  				<td align="right" style="width: 80px">邮箱:</td>
	  				<td><input name="user.email" readonly="readonly" style="width: 100%;"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">描述:</td>
	  				<td colspan="3">
	  					<textarea name="user.remark" readonly="readonly" cols="55" rows="3"></textarea>
	  				</td>
	  			</tr>
  			</table>
			<div align="center" class="tablestyle01">
 				<a href="#" class="easyui-linkbutton" onclick="$('#user_detail_window').window('close'); return false;">关闭</a>
			</div>
		</div>
		
		<div title="编辑用户" id="user_edit_window" modal="true" draggable="false" class="easyui-window" 
			style="width: 560px; height: 400px; background-color:#EFEFEF;" 
			resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
			<table id="user_edit_table" action="user!save.action" border="0" dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="right" style="width: 95px">姓名<span style="color: red;"><b>*</b></span>:</td>
	  				<td style="width: 200px">
	  					<input name="user.userName" class="easyui-validatebox" required="true"  style="width: 100%;"/>
	  					<input name="user.id" type="hidden"/> 	
	  				</td>
	  				<td align="right" style="width: 80px">主机账户<span style="color: red;"><b>*</b></span>:</td>
	  				<td style="width: 185px">
	  					<input name="user.pcusername" class="easyui-validatebox" required="true" style="width: 100%;"/> 
	  				</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">域<span style="color: red;"><b>*</b></span>:</td>
	  				<td><input name="user.validateDomain" class="easyui-validatebox" validType="length[0,72]" style="width: 100%;"/> </td>
	  				<td align="right" style="width: 80px">IP<span style="color: red;"><b>*</b></span>:</td>
	  				<td><input name="user.validateIp" class="easyui-validatebox" validType="length[0,100]" style="width: 100%;"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 95px">用户ID<span style="color: red;"><b>*</b></span>:</td>
	  				<td style="width: 200px"><input name="user.userId" class="easyui-validatebox" required="true"  style="width: 100%;"/></td>
	  				<td align="right" style="width: 80px">&nbsp;</td>
	  				<td style="width: 185px">&nbsp;</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">性别:</td>
	  				<td>
		  				<select id="userSexCombox" style="width:100px;" name="user.sex" class="easyui-combobox">
		  					<!-- <option selected="selected" value="">--请选择--</option> -->
		  					<option value="0">男</option>
		  					<option value="1">女</option>
		  				</select>
		  			</td>
	  				<td align="right" style="width: 80px">职责:</td>
	  				<td>
	  					<select id="positionCombox" style="width:185px;" name="user.positionId" class="easyui-combobox"></select></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">所属机构:</td>
	  				<td colspan="3">
	  					<input name="user.orgId" id="orgIdForEditWin" type="hidden" style="width: 100%;"/>
	  					<input name="user.orgPath" id="orgPathForEditWin" readonly="readonly" style="width: 90%;"/>
	  					<a href="#" onclick="selectOrgTree('edit');return false;">选择</a>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">办公地址:</td>
	  				<td colspan="3"><input name="user.workAddr" style="width: 100%;"/> </td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">固定电话:</td>
	  				<td><input name="user.telphone" style="width: 100%;"/> </td>
	  				<td align="right" style="width: 80px">移动电话:</td>
	  				<td><input name="user.mobile" style="width: 100%;"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">传真:</td>
	  				<td><input name="user.fax" style="width: 100%;"/> </td>
	  				<td align="right" style="width: 80px">邮箱:</td>
	  				<td><input name="user.email" style="width: 100%;"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">描述:</td>
	  				<td colspan="3">
	  					<textarea name="user.remark" cols="55" rows="3"></textarea>
	  				</td>
	  			</tr>
  			</table>
			<div align="center" class="tablestyle01">
				<a href="#" class="easyui-linkbutton" onclick="userEditSave(null); return false;">保存</a>
 				<a href="#" class="easyui-linkbutton" onclick="$('#user_edit_window').window('close'); return false;">关闭</a>
			</div>
		</div>
		
		<div title="新增用户" id="user_add_window" modal="true" draggable="false" class="easyui-window" 
			style="width: 560px; height: 400px; background-color:#EFEFEF;" 
			resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
			<table id="user_add_table" action="user!save.action" border="0" dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="right" style="width: 95px">姓名<span style="color: red;"><b>*</b></span>:</td>
	  				<td style="width: 200px">
	  					<input name="user.userName" class="easyui-validatebox" required="true"  style="width: 100%;"/>
	  					<input name="user.id" type="hidden"/>  
	  				</td>
	  				<td align="right" style="width: 80px">主机账户<span style="color: red;"><b>*</b></span>:</td>
	  				<td style="width: 185px">
	  					<input name="user.pcusername" class="easyui-validatebox" required="true" style="width: 100%;"/> 
	  				</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">域<span style="color: red;"><b>*</b></span>:</td>
	  				<td><input name="user.validateDomain" class="easyui-validatebox" required="true" validType="length[0,72]" style="width: 100%;"/> </td>
	  				<td align="right" style="width: 80px">IP<span style="color: red;"><b>*</b></span>:</td>
	  				<td><input name="user.validateIp" class="easyui-validatebox" required="true" validType="length[0,100]" style="width: 100%;"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 95px">用户ID<span style="color: red;"><b>*</b></span>:</td>
	  				<td style="width: 200px"><input name="user.userId" class="easyui-validatebox" required="true"  style="width: 100%;"/></td>
	  				<td align="right" style="width: 80px">&nbsp;</td>
	  				<td style="width: 185px">&nbsp;</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">性别:</td>
	  				<td>
		  				<select id="userSexComboxAdd" style="width:100px;" name="user.sex" class="easyui-combobox">
		  					<!-- <option selected="selected" value="">--请选择--</option> -->
		  					<option value="0">男</option>
		  					<option value="1">女</option>
		  				</select>
		  			</td>
	  				<td align="right" style="width: 80px">职责:</td>
	  				<td>
	  					<select id="positionComboxAdd" style="width:185px;" name="user.positionId" class="easyui-combobox"></select>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">所属机构:</td>
	  				<td colspan="3">
	  					<input name="user.orgId" id="orgIdForAddWin" type="hidden" style="width: 100%;"/>
	  					<input name="user.orgPath" id="orgPathForAddWin" readonly="readonly" style="width: 90%;"/>
	  					<a href="#" onclick="selectOrgTree('add');return false;">选择</a>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">办公地址:</td>
	  				<td colspan="3"><input name="user.workAddr"  style="width: 100%;" maxlength="100"/> </td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">固定电话:</td>
	  				<td><input name="user.telphone"  style="width: 100%;" class="easyui-validetebox" maxlength="14"/> </td>
	  				<td align="right" style="width: 80px">移动电话:</td>
	  				<td><input name="user.mobile"  style="width: 100%;" class="easyui-validetebox" maxlength="14"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">传真:</td>
	  				<td><input name="user.fax"  style="width: 100%;" class="easyui-validetebox" maxlength="14"/> </td>
	  				<td align="right" style="width: 80px">邮箱:</td>
	  				<td><input name="user.email"  style="width: 100%;" class="easyui-validetebox" maxlength="60"/></td>
	  			</tr>
	  			<tr>
	  				<td align="right" style="width: 80px">描述:</td>
	  				<td colspan="3">
	  					<textarea name="user.remark"  cols="55" rows="3" class="easyui-validatebox" validType="length[0,400]"></textarea>
	  				</td>
	  			</tr>
  			</table>
			<div align="center" class="tablestyle01">
				<a href="#" class="easyui-linkbutton" onclick="userAddSave(null); return false;">保存</a>
 				<a href="#" class="easyui-linkbutton" onclick="$('#user_add_window').window('close'); return false;">关闭</a>
			</div>
		</div>
		
		<div title="机构选择" id="org_tree_window" modal="true" draggable="false" class="easyui-dialog" style="width: 400px; height: 340px; background-color:#EFEFEF;" 
			resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true" buttons="#div_org_tree_buttons">
			<ul id="ul_org_tree" class="easyui-tree" ></ul>
			<div id="div_org_tree_buttons" align="center" style="background-color:#EFEFEF;">
				<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
					<tr><td align="center">
						<a href="#" class="easyui-linkbutton" onclick="selectOrg(); return false;">确定</a>
 						<a href="#" class="easyui-linkbutton" onclick="$('#org_tree_window').dialog('close'); return false;">关闭</a>
					</td></tr>
				</table>
			</div>
		</div>
		
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