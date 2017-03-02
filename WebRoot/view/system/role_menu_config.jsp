<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>角色菜单配置管理</title>
	<script type="text/javascript">
		var currRoleId = null;
		$(function(){
			initApp();
		});
		
		function roleMenuSave(){
			if(currRoleId==null){alert("未选择相应的角色");return false;}
			var ids = getCheckIds();
			//if(ids.length==0){alert("未选择相应的菜单");return false;}
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
		
		//获取选择的节点id集合
		function getCheckIds(){
			var root = $('#ul_menu_tree').tree("getRoot");
			var checkNodes = $('#ul_menu_tree').tree("getChecked");
			var parentNodes = new Array();
			var parentNodeMap = new Object();
			
			for(var i=0; i<checkNodes.length ;i++){
				if(checkNodes[i].state!=null){
					parentNodeMap[checkNodes[i].id] = "1";
				}
			}
			for(var i=0; i<checkNodes.length ;i++){
				var tmp = checkNodes[i];
				while(tmp.id!=root.id){
					//var pnode = $(tmp).tree("getParent",tmp.target);
					var pnode = $('#ul_menu_tree').tree("getParent",tmp.target);
					if(parentNodeMap[pnode.id]==null){
						parentNodeMap[pnode.id] = "1";
						parentNodes.push(pnode);
					}
					tmp = pnode;
				}
			}
			checkNodes = checkNodes.concat(parentNodes);
			var ids = new Array();
			$(checkNodes).each(function(i,node){
				ids.push(node.id);
			});
			return ids;
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
			
			$.ajax({
			  type: "POST", url: "menuTree.action", dataType: "json",
			  success: function(json){
			  	$('#ul_menu_tree').tree('loadData',json.trees);
			  	var root = $('#ul_menu_tree').tree("getRoot");
			  	$('#ul_menu_tree').tree("expand",root.target);
			  }
			});//初始功能树
		}
		
		function roleTreeClick(){
			var node = $('#ul_role_tree').tree("getSelected");
			currRoleId = node.id;
			var root = $('#ul_menu_tree').tree("getRoot");
			$('#ul_menu_tree').tree("uncheck", root.target);
			
			$.ajax({
			  type: "POST", url: "roleMenuQuery.action", data:{"role.roleId":currRoleId}, dataType: "json",
			  success: function(json){
			  	$(json.treeIds).each(function(i,n){
			  		var node = $('#ul_menu_tree').tree("find",n);
			  		//if(node != null && node.state==null){
				  	if(node != null && !node.attributes.url=="") {
			  			$('#ul_menu_tree').tree("check",node.target);
			  		}
			  	});
			  },error:function(){
			  	alert("角色菜单初始化失败");
			  }
			});
		}
	</script>
  </head>
  <body class="easyui-layout">
  	<div region="west" style="width:200px; "  title="角色列表">
	   <ul id="ul_role_tree" class="easyui-tree" ondblclick="roleTreeClick()"></ul>
	</div>
  	<div region="center" title="功能树">
	   <ul id="ul_menu_tree" class="easyui-tree" checkbox="true"></ul>
	</div>
	
	<div region="south" class="easyui-panel tablestyle01" align="center" style="height:32px; padding-top: 3px; ">
	<a href="#" class="easyui-linkbutton" onclick="roleMenuSave();return false;">保存</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>
