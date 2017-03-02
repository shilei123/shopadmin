<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
  	<title>缓存表管理</title>
	<script type="text/javascript">
	$(function(){
		setStatus();
	});
	function save(){
		formSubmit("from_cacheTable",function(json){
			if(json.pojo.id!=null && json.pojo.id!=""){
				$('#cacheTable_window').dailog('close');
				$("#cacheTable_list").datagrid("reload");
				alert("保存成功");
			}else{
				alert("保存失败");
			}
		});
	}
	
	function info(id){
		
	}
	
	function deleteCacheTable(id){
		window.confirm("提示","删除所选项",function(r){
			var url = "${basePath}/view/system/cacheTable!delete.action";
			var data = {"pojo":{id:id}};
			$.ajax( {type: "POST",	url: url,	data: data, dataType: "json"
				,error: function(e) {alert("删除失败");}
				,success: function(json){
					$("#cacheTable_list").datagrid("reload");
					alert("删除成功");
				}
			});
		});
	}
	
	function setStatus(){
		var type = $("#cacheType").val();
		var sql = $("#sql");
		var table = $("#table");
		if(type=="table"){
			sql.val("");
			sql.validatebox({required:false});
			table.validatebox({required:true});
			$("#tr_sql").css("display","none");
			$("#tr_table").css("display","");
		}else if(type=="sql"){
			table.val("");
			table.validatebox({required:false});
			sql.validatebox({required:true});
			$("#tr_sql").css("display","");
			$("#tr_table").css("display","none");
		}
	}
	</script>
  </head>
  
   <body class="easyui-layout">
   <div region="north" class="easyui-panel bgColor" title="缓存表管理" style="height:68px; ">
  		<table id="from_query"  dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" style="width: 100">用户名称:</td>
  				<td >
  					<input name="queryParams.userName" />
  				</td>
  				
  				<td align="right">&nbsp;</td>
  				<td >&nbsp;</td>
  				<td align="right">&nbsp;</td>
  				<td >&nbsp;</td>
  			</tr>
  		</table>
  	</div>
  	<div id="div_config" region="center" title="查询列表" class="easyui-panel" data-options="collapsible:false">
		<table id="cacheTable_list" rownumbers="true" region="center" class="easyui-datagrid" url="cacheTable!list.action" style="width:auto;height:auto" title=""   pagination="true">
			<thead>
				<tr>
					<th field="id" width="120"  >ID</th>
					<th field="cacheType" width="80"  >缓存类型</th>
					<th field="tableName" width="80">表名</th>
					<th field="querySql" width="80" >sql</th>
					<th field="createTime" width="80" formatter="formatterDatetime">创建时间</th>
					<th field="updateTime" width="80" formatter="formatterDatetime">修改时间</th>
					<th field="cl" width="80" >操作</th>
				</tr>
			</thead>
		</table>
		
		<div id="cacheTable_window" closed="true" minimizable="false"  maximizable="false" collapsible="false" title="缓存表配置" class="easyui-dialog bgColor" buttons="#div_buttons" style="width: 440px; height: 228px;">
			<div class="bgColor" style="height: 100%;">
			<table id="from_cacheTable" action="${basePath}/view/system/cacheTable!save.action"  dataType="json" class="tablestyle01" style="width:100%">
  			<tr style="display: none1;">
  				<td align="right" style="width: 100">ID:</td>
  				<td><input name="pojo.id" class="easyui-validatebox" readonly="readonly" maxlength="36" /> </td>
  			</tr>
  			<tr>
  				<td align="right" style="width: 100">缓存类型:</td>
  				<td>
  					<select name="pojo.cacheType" id="cacheType" onchange="setStatus();">
  						<option value="table">表</option>
  						<option value="sql">sql</option>
  					</select>
  				</td>
  			</tr>
  			<tr id="tr_table">
  				<td align="right" style="width: 100">表名:</td>
  				<td><input name="pojo.tableName" id="table" class="easyui-validatebox" required="true" maxlength="60" /> </td>
  			</tr>
  			<tr id="tr_sql">
  				<td align="right" style="width: 100">sql:</td>
  				<td>
  					<textarea name="pojo.querySql" id="sql" class="easyui-validatebox" validType="length[0,2000]" required="true" style="height: 80px; width: 100%;"></textarea>
  				</td>
  			</tr>
  			</table>	
			<div id="div_buttons" align="right" class="tablestyle01">
				<a href="#" class="easyui-linkbutton" onclick="save();">保存</a>
 				<a href="#" class="easyui-linkbutton" onclick="$('#cacheTable_window').dailog('close');">关闭</a>
			</div>
			</div>
		</div>
  	</div>
    <div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
    	<a href="#" class="easyui-linkbutton" onclick="">查询</a>
    	<a href="#" class="easyui-linkbutton" onclick="$('#cacheTable_window').dialog('open');">新增</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>