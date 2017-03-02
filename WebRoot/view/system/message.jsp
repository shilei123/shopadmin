<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>信息管理</title>
    
	<script type="text/javascript">
	function query(){
		var data = formGetJson("from_query");
		$("#datagrid").datagrid("load",data);
	}
	
	function messageDbClick(i,data){
		if(data){infoMessage(data.id);}
	}
	
	function infoMessage(id){
		window.parent.infoMessage(id);
	}
	
	var formatterMessageType = function(val, data, index){
		return val=='1'?"私信":'系统消息';
	}
	var messageLevelMap = {'1':'紧急', '2':'一般', '3':'普通'};
	var formatterMessageLevel = function(val, data, index){
		return messageLevelMap[val];
	}
	var formatterMessageStatus = function(val, data, index){
		return val=='1'?'已读':'未读';
	}
	
	</script>
  </head>
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="信息管理" style="height:100px; ">
  		<table id="from_query"  dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" >关键字:</td>
  				<td ><input id="queryParams_key" name="queryParams.keyword" size="12" maxlength="30" /> </td>
  				<td align="right">消息状态:</td>
  				<td >
  					<select name="queryParams.status">
  						<option value="all">全部</option>
  						<option value="1">已读</option>
  						<option value="0">未读</option>
  					</select>
  				</td>
  				<td align="right">消息类型:</td>
  				<td >
  					<select name="queryParams.type">
  						<option value="">全部</option>
  						<option value="1">私信</option>
  						<option value="2">系统信息</option>
  					</select>
  				</td>
  			</tr>
  			<tr>
  				<td align="right" >发信人:</td>
  				<td ><input name="queryParams.sender" size="12" maxlength="30" /> </td>
  				<td align="right">收信人:</td>
  				<td ><input  name="queryParams.receiver" size="12" maxlength="30" /></td>
  				<td align="right">发送时间:</td>
  				<td >
  					<input name="queryParams.beginDate" size="10" class="easyui-datebox" >至
  					<input name="queryParams.endDate" size="10" class="easyui-datebox" >
  				</td>
  			</tr>
  		</table>
  	</div>
  	
  	<div region="center">
	    <table id="datagrid" rownumbers="true"  region="center" class="easyui-datagrid" url="${basePath}/view/public/message!list.action" 
	    	style="width:auto;height:auto" title="查询列表"   pagination="true" singleSelect="true"
	    	 data-options="queryParams:{'queryParams.status':'all'}, onDblClickRow:messageDbClick">
			<thead>
		    	<tr>
		    		<th field="title" width="130">消息标题</th>
		    		<th field="messageType" width="60" formatter='formatterMessageType'>消息类型</th>
		    		<th field="messageStatus" width="60" formatter='formatterMessageStatus'>消息状态</th>
		    		<th field="createUserName" width="60">发信人</th>
		    		<th field="toUserName" width="60">收信人</th>
		    		<th field="createTime" width="120"  formatter='formatterDatetime'>发送时间</th>
		    		<th field="messageLevel" width="40" formatter='formatterMessageLevel'>等级</th>
		  		</tr>
			</thead>
		</table>
	</div>
	
	<div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
		<a href="#" class="easyui-linkbutton" onclick="query(); return false;">查询</a>
		<a href="#" class="easyui-linkbutton" onclick="window.parent.newMessage();">新建</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>
