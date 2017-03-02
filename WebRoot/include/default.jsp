<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="framework.bean.UserMsg"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
String basePath = request.getContextPath();
String absolutePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+basePath;
request.setAttribute("basePath", basePath);
request.setAttribute("absolutePath", absolutePath);
request.setAttribute("_title", application.getInitParameter("title"));
UserMsg user = session.getAttribute("user")==null?new UserMsg():(UserMsg)session.getAttribute("user");
request.setAttribute("user",user);
 %>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript">
<!--
var basePath = "${basePath}";
//-->
</script>
<link rel="stylesheet" type="text/css" href="${basePath }/js/jquery-easyui-1.4/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath }/js/jquery-easyui-1.4/themes/icon.css">
<script type="text/javascript" src="${basePath }/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath }/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath }/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${basePath }/js/json2.js"></script>
<script type="text/javascript" src="${basePath }/js/tools.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath }/css/default.css">
<script type="text/javascript">
<!--
$(function(){
	setTimeout("resizeBinding()",100);
});
//为easyui组件加上 resizebind="datagrid" 属性 则自动扩展resize
function resizeBinding(){
	$(window).resize(function(){
		$("table[resizebind]").each(function(i,n){
			$(n).datagrid("options").width = getTotalWidth()-2;
			$(n).datagrid("resize");
		});
	});
}

//begin -------机构下拉选择树绑定
function orgComboTreeBind(id,defVal){
	var url = basePath+"/view/system/orgTree.action";
	var el = $('#'+id);
	el.combotree({textField:'text', valueField:'id'});
	
	$.ajax( { type : "POST", url : "${basePath}/view/system/orgTree.action", dataType : "json",error:function(){alert('初始化机构异常');},
		success : function(json) {
			var root = json.trees[0];
			root.state = "open";
			el.combotree("loadData", json.trees);
			if(defVal!=null && defVal!=""){
				el.combotree("setValue",defVal);	
			}
		}
	});
}
//end -------机构下拉选择树绑定

//begin -------机构选择弹出框绑定
var targetLabelId = null;
var targetId = null;
function selectOrgTree() {
	$.ajax( {
		type : "POST",
		url : "${basePath}/view/system/orgTree.action",
		dataType : "json",
		success : function(json) {
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
		if(targetLabelId!=null) {
			document.getElementById(targetLabelId).value = msg.orgPath;
		}
		if(targetId!=null) {
			document.getElementById(targetId).value = msg.id;
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
//在机构框后面添加选择文字按钮
function afterOrgSelectFont(tgtLabId,tgtId) {
	if(tgtLabId){targetLabelId = tgtLabId;}
	if(tgtId){targetId = tgtId;}
	var aHtml = "<a href=\"#\" onclick=\"selectOrgTree();return false;\">选择</a>";
	$('#'+tgtLabId).after(aHtml);
	
	//var aHtml = "<a href=\"#\" onclick=\"selectOrgTree('form_item_1398479681515','form_item_1398479681516');return false;\">选择</a>";
	//$('#form_item_1398479681515').after(aHtml);
	//afterSelectFont('form_item_1398479681515','form_item_1398479681516');
}
//end -------机构选择弹出框绑定
//-->
</script>
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