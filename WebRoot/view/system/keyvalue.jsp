<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>代码表管理</title>
    
	<script type="text/javascript">
		var lastId = "";
		var lastIndex = -1;
		var isNew = false;
		var isEditing = false;
		$(function(){
			$("#tab_list").datagrid({onDblClickRow: clickTabRow});
		});
		
		function addTabRow(){
			if(isEditing){
				alert("有未编辑完成的内容");
				return false;
			}
			isNew = true;
			isEditing = true;
			$('#tab_list').datagrid('appendRow',{kvKey:'', kvValue:'', lrSj:''});
			lastIndex = $('#tab_list').datagrid('getRows').length-1;
			$('#tab_list').datagrid('selectRow', lastIndex);
			$('#tab_list').datagrid('beginEdit', lastIndex);
	    }
    	
    	function clickTabRow(index, data){
    		isNew = false;
    		var editorts = $('#tab_list').datagrid('getEditors', lastIndex);
    		if(editorts.length>0){alert("有内容可未保存");return false;}
	    	if (lastIndex != index){
				$('#tab_list').datagrid('endEdit', lastIndex);
			}
			$('#tab_list').datagrid('beginEdit', index);
			lastId = data.id;
			lastIndex = index;
			isEditing = true;
	    }
    
    	function save(){
    		var editorts = $('#tab_list').datagrid('getEditors', lastIndex);
    		if(editorts.length==0){alert("无内容可保存");return false;}
    		var key = editorts[0].target.val();
    		var value = editorts[1].target.val();
    		var label = editorts[2].target.val();
    		var remark = editorts[3].target.val();
    		if(key==''||value==''||label==''){alert("请填写完整后保存");return false;}
    		var data = {"queryParams.kv_key":key,"queryParams.kv_value":value};
    		$.ajax( {
				type: "POST",	url: "keyvalue!query.action", data: data, dataType: "json",
				success: function(json){
					if(isNew && json.rows.length>0){
						alert("已存在该键值对");
					}else{
						data = {"kv.id":lastId,"kv.type":key,"kv.code":value,"kv.label":label,"kv.remark":remark};
						$.ajax( {
							type: "POST",	url: "keyvalue!save.action",	data: data, dataType: "json",
							success: function(json){
								$('#tab_list').datagrid('endEdit', lastIndex);
			    				isEditing = false;
			    				alert("保存成功");
			    				query();
							},	
							error: function(e) {alert("保存异常");}
						});
					}
				},	
				error: function(e) {alert("保存异常");}
			});
    	}
    	
    	function deleteKv(){
    		if(isEditing) {
    			alert("有未编辑完成的内容");
    			return false;
    		}
    		var sels = $('#tab_list').datagrid("getSelections");
    		var ids = "";
    		var bz = "";
    		$(sels).each(function(i,n){
    			ids += bz+n.id;
    			bz = ",";
    		});
    		window.confirm("提示","确认删除?",function(r){
    			if(r){
					$.ajax( {
						type: "POST",	url: "keyvalue!delete.action",	data: {"ids":ids}, dataType: "json",
						success: function(json){
							query();
						},	
						error: function(e) {alert("删除异常");}
					});
				}
			});
    	}
    	
		function query(){
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
			//$('#tab_list').datagrid({queryParams:{"queryParams.key":$('#queryParams_key').val()}});
			
		}
		
		function cancel() {
			if(lastIndex>-1) {
				$('#tab_list').datagrid('cancelEdit', lastIndex);
				if(isNew) {
					$('#tab_list').datagrid('deleteRow', lastIndex);
					isNew = false;
					isEditing = false;
				}
			}
		}
		
		function timeFormatter(val){
			return val.replace("T"," ");
		}
	</script>
  </head>
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="代码表管理" style="height:100px; ">
  		<table id="from_query"  dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" style="width: 100">键：</td>
  				<td><input id="queryParams_key" name="queryParams.key" maxlength="30" /> </td>
  				<td align="right">值：</td>
  				<td><input id="queryParams_code" name="queryParams.code" maxlength="30" /> </td>
  				<td align="right">显示名称：</td>
  				<td><input id="queryParams_label" name="queryParams.label" maxlength="30" /> </td>
  				<td align="right">描述：</td>
  				<td><input id="queryParams_remark" name="queryParams.remark" maxlength="30" /> </td>
  			</tr>
  		</table>
  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="left">
		  			<a href="#" class="easyui-linkbutton" onclick="query(); return false;">查询</a>
					<a href="#" class="easyui-linkbutton" onclick="addTabRow(); return false;">新建</a>
					<a href="#" class="easyui-linkbutton" onclick="save(); return false;">保存</a>
					<a href="#" class="easyui-linkbutton" onclick="deleteKv(); return false;">删除</a>
			 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		<a href="#" class="easyui-linkbutton" onclick="cancel();">取消</a>
			 		<!-- <a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a> -->
		 		</td>
		 	</tr>
  		</table>
  	</div>
  	
  	<div region="center">
	    <table id="tab_list" rownumbers="true" region="center" class="easyui-datagrid" 
	    	url="keyvalue!list.action" style="width:auto;height:auto" title="" pagination="true">
			<thead>
				<tr>
					<th field="id" width="200">id</th>
					<th field="type" width="200" editor="{type:'validatebox',options:{required:true}}">键</th>
					<th field="code" width="220" editor="{type:'validatebox',options:{required:true}}">值</th>
					<th field="label" width="120" editor="{type:'validatebox',options:{required:true}}">显示名称</th>
					<th field="remark" width="120" editor="{type:'validatebox',options:{required:false}}">描述</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- <div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
		<a href="#" class="easyui-linkbutton" onclick="query(); return false;">查询</a>
		<a href="#" class="easyui-linkbutton" onclick="addTabRow(); return false;">新建</a>
		<a href="#" class="easyui-linkbutton" onclick="save(); return false;">保存</a>
		<a href="#" class="easyui-linkbutton" onclick="deleteKv(); return false;">删除</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div> -->
  </body>
</html>
