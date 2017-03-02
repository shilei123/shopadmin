<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <jsp:include page="/include/default.jsp"></jsp:include>
    <title>日志</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript">
  
  	function queryLogInfo(){
		var data = formGet("from_query");
		$("#tab_list").datagrid({"queryParams":data});
	}
  	
  	function formatterAction(date){
		var d = date.split("T");
		return d[0]+" "+d[1];
	} 
  	
  </script>
  </head>
  
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="日志列表" style="height:100px; width:100%">
  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="left" style="width: 100">操作人员:<input name="queryParams.loginname"/>
  				 操作时间:
  				<input name="queryParams.beginDate" class="easyui-datebox" value="${currBeginDate }" editable="false"/>~
				<input name="queryParams.endDate" class="easyui-datebox" value="${currEndDate }" editable="false"/>
				</td>
  			</tr>
  		</table>
  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="left">
  					<a href="#" class="easyui-linkbutton" onclick="queryLogInfo(); return false;">查询</a>
			 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
		 		</td>
		 	</tr>
  		</table>
  	</div>
    
  	<div region="center" style="width: 100%">
	     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
	    	url="queryLog.action" style="width:auto;height:auto" title="" 
	    	pagination="true" singleSelect="true">
			<thead>
				<tr>
					<!-- <th style="display: none;" checkbox="true" field="id" width="5%">ID</th> -->
					<!-- <th field="id" width="17%">序号</th> -->
					<th field="loginname" width="10%">操作人员</th>
					<th field="logdetail" width="74%">操作详情</th>
					<th field="createdate" width="15%" formatter="formatterAction">操作时间</th>	
				</tr>
			</thead>
		</table>
	</div>
	
  </body>
</html>
