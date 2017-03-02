<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
  	<title>数据源管理</title>
	<script type="text/javascript">
	var whereTab = null;
	$(function(){
		whereTab = $("#tab_where");
	});
	
function copyToClipboard(txt) {   
    if(window.clipboardData) {   
            window.clipboardData.clearData();   
            window.clipboardData.setData("Text", txt);   
    } else if(navigator.userAgent.indexOf("Opera") != -1) {   
         window.location = txt;   
    } else if (window.netscape) {   
         try {   
              netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");   
          } catch (e) {   
               alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");   
          }   
          var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);   
          if (!clip)   
               return;   
          var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);   
          if (!trans)   
               return;   
          trans.addDataFlavor('text/unicode');   
          var str = new Object();   
          var len = new Object();   
          var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);   
          var copytext = txt;   
          str.data = copytext;   
          trans.setTransferData("text/unicode",str,copytext.length*2);   
          var clipid = Components.interfaces.nsIClipboard;   
          if (!clip)   
               return false;   
          clip.setData(trans,null,clipid.kGlobalClipboard);   
          alert("复制成功！")   
     }   
}
	
	
	function query(){
		var data = formGetJson("from_query");
		$("#source_list").datagrid("load",data);
	}
	
	function save(){
		formSubmit("from_source",function(json){
			if(json.message=="success"){
				$('#source_window').dialog('close');
				$("#source_list").datagrid("reload");
				alert("保存成功");
			}else{
				alert("保存失败,"+json.message);
			}
		});
	}
	
	function sourceInfo(){
		whereTab.datagrid('loadData',{total:0,rows:[]});
		var row = $("#source_list").datagrid("getSelected");
		if(row==null){alert('请选中一行');return false;}
		var url = "${basePath}/view/system/datasource!info.action";
		var data = {"source.id":row.id};
		$.ajax( {type: "POST",	url: url,	data: data, dataType: "json",error: function(a) {alert("初始化失败");}
			,success: function(json){
				formSet("from_source", json.source);
				$('#source_window').dialog('open');
				initWhereRow();
			}
		});
	}
	
	function deleteSource(){
		var row = $("#source_list").datagrid("getSelected");
		if(row==null){alert('请选中一行');return false;}
		
		window.confirm("提示","删除所选项",function(r){
			var url = "${basePath}/view/system/datasource!delete.action";
			var data = {"source.id":row.id};
			$.ajax( {type: "POST",	url: url,	data: data, dataType: "json"
				,error: function(e) {alert("删除失败");}
				,success: function(json){
					$("#source_list").datagrid("reload");
					alert("删除成功");
				}
			});
		});
	}
	
	function initWhereRow(){
		var json = $("#whereSql").val();
		var rows = [];
		if(json!="" && json!="[]"){
			rows = eval(json);
		}
		var data = {total:0, rows:rows};
		whereTab.datagrid('loadData', data); 
	}
	
	function addWhereRow(){
		if(!endWhereRow()){ return false; }
		whereTab.datagrid('appendRow', {}); 
		whereTab.attr("lastIndex", (whereTab.datagrid('getRows').length-1))
		whereTab.datagrid('selectRow', whereTab.attr("lastIndex"));
		whereTab.datagrid('beginEdit', whereTab.attr("lastIndex"));
	}
	
	function editWhereRow(){
		var row = whereTab.datagrid('getSelected');
		if(row==null || row.length==0){return false;}
		var index = whereTab.datagrid('getRowIndex',row);
		
		if (whereTab.attr("lastIndex")!=null && whereTab.attr("lastIndex") != index){
			whereTab.datagrid('endEdit', whereTab.attr("lastIndex"));
		}
		whereTab.datagrid('beginEdit', index);
		whereTab.attr("lastIndex",index);
	}
	
	function delWhereRow(){
		var row = whereTab.datagrid('getSelected');
		if (row){
			var index = whereTab.datagrid('getRowIndex', row);
			whereTab.datagrid('deleteRow', index);
			infoWhereValue();
		}
	}
	
	function endWhereRow(){
		if(whereTab.attr("lastIndex")!=null){
			if(!whereTab.datagrid('validateRow', whereTab.attr("lastIndex"))){
	    		return false;
	    	}
			whereTab.datagrid('endEdit', whereTab.attr("lastIndex"));
			infoWhereValue();
		}
		return true;
	}
	
	function infoWhereValue(){
		var data = whereTab.datagrid("getData");
		if(data.rows==null || data.rows.length==0){
			$("#whereSql").val("[]");
			return false;
		}
		$("#whereSql").val(JSON.stringify(data.rows));
	}
	
	function formatterAction(val, data, index){
		var url = basePath+'/view/public/datasource.action?source.id='+data.id;
		return "<a href='javascript:void(0);' onclick='copyToClipboard(\""+url+"\");'>复制链接</a>";	
	}
	</script>
  </head>
  
   <body class="easyui-layout">
   <div region="north" class="easyui-panel bgColor" title="数据源管理" style="height:68px; ">
  		<table id="from_query"  dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" style="width: 100">数据源名称:</td>
  				<td >
  					<input name="queryParams.name" maxlength="32" />
  				</td>
  				
  				<td align="right">&nbsp;</td>
  				<td >&nbsp;</td>
  				<td align="right">&nbsp;</td>
  				<td >&nbsp;</td>
  			</tr>
  		</table>
  	</div>
  	<div id="div_config" region="center" title="查询列表" class="easyui-panel" data-options="collapsible:false">
		<table id="source_list" rownumbers="true" region="center" class="easyui-datagrid" fitColumns="true"
			url="${basePath}/view/system/datasource!list.action" singleSelect="true" style="width:auto;height:auto"
			title=""  data-options="onDblClickRow:sourceInfo" pagination="true">
			<thead>
				<tr>
					<th field="id" width="30%">ID</th>
					<th field="sourceName" width="30%">数据源名称</th>
					<th field="sourceRemark" width="30%">数据源描述</th>
					<th field="id2" width="10%" formatter="formatterAction">操作</th>
				</tr>
			</thead>
		</table>
		
		<div id="source_window" modal="true" draggable="false" closed="true" minimizable="false"  maximizable="false" collapsible="false" title="数据源配置" class="easyui-dialog bgColor" buttons="#div_buttons" style="width: 560px; height: 428px;">
			<div class="bgColor" style="height: 100%;">
			<table id="from_source" action="${basePath}/view/system/datasource!save.action"  dataType="json" class="tablestyle01" style="width:100%">
  			<tr id="tr_table">
  				<td align="right" style="width: 100">名称:</td>
  				<td>
  					<input name="source.id" type="hidden" class="easyui-validatebox" readonly="readonly" maxlength="36" />
  					<input name="source.sourceName" id="table" class="easyui-validatebox" required="true" maxlength="40" style="width: 360" /> 
  				</td>
  			</tr>
  			<tr id="tr_table">
  				<td align="right" style="width: 100">描述:</td>
  				<td>
  					<input name="source.sourceRemark" id="table" class="easyui-validatebox" maxlength="100" style="width: 360"  /> 
  				</td>
  			</tr>
  			<tr id="tr_sql">
  				<td align="right" style="width: 100" valign="top">查询语句:</td>
  				<td>
  					<textarea name="source.sourceSql" id="sql" class="easyui-validatebox" validType="length[0,4000]" required="true" style="height: 80px; width: 96%;"></textarea>
  					<div style="color: red;">可选替换参数：@userId@&nbsp;,&nbsp;@deptId@&nbsp;,&nbsp;@orgId@&nbsp;,&nbsp;@orgPath@&nbsp;,&nbsp;@orgCode@&nbsp;,&nbsp;@role@</div>
  				</td>
  			</tr>
  			<tr id="tr_table">
  				<td colspan="2">
  					<textarea id="whereSql" name="source.whereSql" style="width: 100%; display: none;"></textarea>
  					<table id="tab_where" title="查询条件" class="easyui-datagrid"  style="width:500; height:280;" rownumbers="false" singleSelect="true"
				  	 	data-options="toolbar:[{text:'添加', handler: addWhereRow},{text:'编辑', handler: editWhereRow},{text:'删除', handler: delWhereRow},{text:'完成', handler: endWhereRow}]">
				    	<thead>
					    	<tr>
					    		<th field="name" width="120" editor="{type:'validatebox',options:{required:true}}">参数</th>
					    		<th field="sql" width="330" editor="{type:'validatebox',options:{required:true}}">语句</th>
					    	</tr>
				    	</thead>
				    </table>
  				</td>
  			</tr>
  			</table>	
			<div id="div_buttons" align="right" class="tablestyle01">
				<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center">
 							<a href="#" class="easyui-linkbutton" onclick="save(); return false">保存</a>
 							<a href="#" class="easyui-linkbutton" onclick="$('#source_window').dialog('close'); return false;">关闭</a>
						</td>
					</tr>
				</table>
			</div>
			</div>
		</div>
  	</div>
    <div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
    	<a href="#" class="easyui-linkbutton" onclick="query();">查询</a>
    	<a href="#" class="easyui-linkbutton" onclick="formSetJson('from_source',{}); whereTab.datagrid('loadData',{total:0,rows:[]}); $('#source_window').dialog('open');">新增</a>
    	<a href="#" class="easyui-linkbutton" onclick="sourceInfo();">修改</a>
    	<a href="#" class="easyui-linkbutton" onclick="deleteSource();">删除</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>