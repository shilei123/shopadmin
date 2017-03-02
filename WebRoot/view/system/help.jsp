<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript">
  		window.UMEDITOR_HOME_URL = "${basePath}/ueditor/";
  	</script>
  	<jsp:include page="/include/default.jsp"></jsp:include>
	<title>帮助管理</title>
	
     <link href="${basePath}/ueditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" charset="utf-8" src="${basePath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="${basePath}/ueditor/lang/zh-cn/zh-cn.js"></script>
    
    
	<script type="text/javascript">
		var ueditorObj = null;
		$(function(){
		 	 ueditorObj = UE.getEditor('content');
		 	
			$("#ul_menu_tree").tree({
				onDblClick: function(node){
					 ueditorObj.setContent("");
					 $("#menuId").val(node.id);
					 var id = $("#menuId").val();
					 var type = $("#userType").val();
					 var data = {"pojo.id.menuId":id, "pojo.id.userType":type};
					 $.ajax( {
						type : "POST",url : "help!findHelp.action",dataType : "json",data:data,
						success : function(json) {
							 ueditorObj.setContent(json.pojo.content);
						}
						,error:function(){
							alert("初始化异常");
						}
					});
				}
			});//初始化菜单
			
			initMenuTree();//初始化菜单
		});
		
		function saveHelp(){
			if(!$("#menuId").validatebox("isValid")){return false;}
			var id = $("#menuId").val();
			var type = $("#userType").val();
			var content = ueditorObj.getContent();
			var data = {"pojo.id.menuId":id, "pojo.id.userType":type, "pojo.content":content};
			$.ajax( {
				type : "POST",url : "help!saveHelp.action",dataType : "json",data:data,
				success : function(json) {
					alert("保存成功");
				}
				,error:function(){
					alert("保存异常");
				}
			});
		}
		
		function getNodeMsg(node){
			var obj = new Object();
			obj.menuId = node.id;
			obj.menuName = node.text;
			if(node.attributes!=null){
				obj.url = node.attributes.url;
				obj.pxXh = node.attributes.pxXh;
				obj.menuParentIds = node.attributes.menuParentIds;
			}
			var pnode = $('#ul_menu_tree').tree('getParent',node.target); 
			if(pnode!=null){
				obj.menuParentId = pnode.id;
				obj.menuParentName = pnode.text;
			}
			return obj;
		}
		
		//初始化功能菜单
		function initMenuTree() {
			$.ajax( {
				type : "POST",
				url : "menuTree.action",
				dataType : "json",
				success : function(json) {
					var root = json.trees[0];
					root.state = "open";
					$("#ul_menu_tree").tree("loadData", json.trees);
				}
			});
		}
		
	</script>
  </head>
  
   <body class="easyui-layout">
  	<div region="west" class="easyui-panel bgColor" split="true" title="功能菜单" style="width:200px;" >
  		<ul id="ul_menu_tree" ></ul>
  	</div>
  	<div id="div_config" region="center" title="配置" class="easyui-panel bgColor" style="">
		<table class="tablestyle01" style="margin-top:5px; width: 100%;">
			<tr>
				<td style="width: 80px;">菜单ID</td>
				<td ><input name="pojo.id.menuId" id="menuId" readonly="readonly" class="easyui-validatebox" required="true" /></td>
			</tr>
			<tr>
				<td>用户类型</td>
				<td><input name="pojo.id.userType" id="userType" value="default" readonly="readonly"  /></td>
			</tr>
			<tr>
				<td valign="top">帮助内容</td>
				<td>
					<textarea name="pojo.content" id="content" style="width:520px;height:240px;"></textarea>
				</td>
			</tr>
		</table>
  	</div>
    <div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
    	<a href="#" class="easyui-linkbutton" onclick="saveHelp();">保存</a>
    	<a href="#" class="easyui-linkbutton" onclick="saveTree();">预览</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>