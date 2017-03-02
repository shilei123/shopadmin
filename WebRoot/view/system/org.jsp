<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
	<title>机构管理</title>
	<script type="text/javascript">
		$(function(){
			$("#ul_org_tree").tree({
				onDblClick: function(node){
					$("#div_config input").each(function(i,n){n.value = "";});
					var msg = getOrgInfo(node);
					for(var key in msg){
						var inp = document.getElementById("inp_"+key);
						if(inp != null){
							inp.value = msg[key];
						}
					}
				}
			});//初始化机构信息面板
			
			initOrgTree();//初始化全体机构树
		});
		
		//初始化全体机构树
		function initOrgTree() {
			$.ajax( {
				type : "POST",
				url : "orgTree.action",
				dataType : "json",
				success : function(json) {
					var root = json.trees[0];
					root.state = "open";
					$("#ul_org_tree").tree("loadData", json.trees);
				}
			});
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
		
		function addOrg(){
			var node = $("#ul_org_tree").tree("getSelected");
			if(node==null){
				alert("请选择一个机构");
				return false;
			}
			var msg = getOrgInfo(node);
			if(msg.orgLevel < 2) {//2是部门，部门下面不允许创建机构 
				$("#div_config input").each(function(i,n){n.value = "";});
				document.getElementById("inp_parentOrgId").value = msg.orgId;
				document.getElementById("inp_parentOrgName").value = msg.orgName;
				document.getElementById("inp_parentShortName").value = msg.shortName;
				document.getElementById("inp_parentOrgPath").value = msg.orgPath;
			}else{
				alert("所选机构下不允许新建子机构！");
				return false;
			}
		}
		
		function saveOrg(json){
			if(json==null){
				$("#div_config").attr("action","orgSave.action");
				formSubmit('div_config',saveOrg);
			}else{
				document.getElementById("inp_orgId").value = json.tree.orgId;
				document.getElementById("inp_parentOrgId").value = json.tree.parentOrgId;
				initOrgTree();//初始化菜单
				alert("保存成功");
			}
		}
		
		function deleteTree(json){
			if(json==null){
				window.confirm("提示","确认删除?", function(r){
					if(r){
						$("#div_config").attr("action","orgDel.action");
						formSubmit('div_config',deleteTree);
					}
				});
			}else{
				$("#div_config input").each(function(i,n){n.value = "";});
				initOrgTree();//初始化菜单
				alert("删除成功");
			}
		}
		
	</script>
  </head>
  
   <body class="easyui-layout">
  	<div region="west" class="easyui-panel bgColor" split="true" title="全体机构" style="width:300px;" >
  		<ul id="ul_org_tree" ></ul>
  	</div>
  	<div id="div_config" region="center" title="机构信息" class="easyui-panel bgColor" style="">
		<table class="tablestyle01" style="margin-top:5px;">
			<tr>
				<td width="100">上级机构编码：</td>
				<td><input name="tree.parentOrgId" id="inp_parentOrgId" readonly="readonly" class="easyui-validatebox" style="width: 300px;" /></td>
			</tr>
			<tr>
				<td>上级机构名称：</td>
				<td><input id="inp_parentOrgName" readonly="readonly" class="easyui-validatebox" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td>上级机构简称：</td>
				<td><input id="inp_parentShortName" readonly="readonly" class="easyui-validatebox" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td>上级机构全路径：</td>
				<td><input id="inp_parentOrgPath" readonly="readonly" class="easyui-validatebox" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td>机构编码：</td>
				<td>
					<input type="hidden" name="tree.id" id="inp_id">
					<input name="tree.orgId" id="inp_orgId" readonly="readonly" style="width: 100%;" />
				</td>
			</tr>
			<tr>
				<td>机构名称：</td>
				<td><input name="tree.orgName" id="inp_orgName" class="easyui-validatebox" required="true" maxlength="100" style="width: 220px;"  /></td>
			</tr>
			<tr>
				<td>机构简称</td>
				<td><input name="tree.shortName" id="inp_shortName" class="easyui-validatebox" required="true" maxlength="100" style="width: 220px;"  /></td>
			</tr>
			<tr>
				<td>机构全路径：</td>
				<td><input name="tree.orgPath" id="inp_orgPath" class="easyui-validatebox" readonly="readonly" maxlength="2000" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td>排序序号：</td>
				<td><input name="tree.order" id="inp_order" class="easyui-validatebox" required="true" validType="number" maxlength="3" style="width: 50px;"  /></td>
			</tr>
			<tr>
				<td>注释：</td>
				<td style="color: red;">机构名称、机构简称，排序序号可输入，其它字段系统自动生成！</td>
			</tr>
			<tr>
				<td>操作说明：</td>
				<td style="color: red;">
					保存：则双击某机构，输入正确信息后点击保存按钮！<br/>
					新增：在菜单下添加子菜单，先单击菜单，点击新增按钮，输入信息后点击保存！<br/>
					删除：双击选择要删除的菜单，点击删除按钮！<br/>
				</td>
			</tr>
		</table>
  	</div>
    <div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
 		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="addOrg();">添加机构</a>
 		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveOrg();">保存</a>
 		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteTree();">删除</a>
 		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>