<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String curr = sdf.format(new java.util.Date());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
  	<title>工作日管理</title>
  	<style type="text/css">
  	table {
  		font-size: 12px;
  	}
  	
  	.day{
  		margin-top: 10px;
  		text-align: center;
  		border-color: #5EAFFF;
  		background-color: #5EAFFF;
  	}
  	.day td, .day th {
  		text-align: center;
  		width: 50px;
  		height: 30px;
  		cursor: pointer;
  	}
  	.day th {
  		background-color: #5EAFFF;
  	}
  	.tablestyle01 {
  		margin-left: auto;
  	}
  	</style>
	<script type="text/javascript">
	$(function(){
		$("#tb_days td").bind("click",function(){
			var d1 = $(this);
			if(d1.attr('day')==null || d1.attr('day')==''){return false;}
			formSetJson("from_wf",{'workday.day':d1.attr('day'), 'workday.remark':d1.attr('remark') , 'workday.workday':d1.attr('workday')});
			$("#win_workday").dialog('open');
		});
		var d = $("#inp_day");
		if(d.val()==""){
			d.datebox("setValue","<%=curr%>");
		}
		list();
	});
	
	function saveWorkday(){
		var data = formGetJson("from_wf");
		$.ajax({
			type: "POST", url: "workday!save.action", data:data, dataType: "json",error:function(){alert("保存失败");},
			success: function(json){
				$("#win_workday").dialog('close');
				list();
			}
		});
	}
	var nums = {'01':1,'02':2,'03':3,'04':4,'05':5,'06':6,'07':7,'08':8,'09':9,'10':10,'11':11,'12':12};
	function moveToMonth(num){
		var d = $("#inp_day");
		var val = d.datebox("getValue");
		var ds = val.split("-");
		var y = parseInt(ds[0]);
		var m = nums[ds[1]];
		m = m+num;
		if(m>12){
			y=y+1;m=1;
		}
		if(m<1){
			y=y-1;m=12;
		}
		
		d.datebox('setValue',y+'-'+(m<10?'0'+m:m)+'-01');
		list();
	}
	function list(){
		var data = formGetJson("from_query");
		$.ajax({
			type: "POST", url: "workday!list.action", data:data, dataType: "json",error:function(){alert("获取工作日失败");},
			success: function(json){
				var days = json.rows;
				var tds = $("#tb_days td");
				tds.html("&nbsp;");
				tds.css("backgroundColor","white");
				$("#tb_days td").each(function(i,n){
					if(days[i]!=null){
						var node = $(n);
						var row = days[i];
						node.html(row.day.substring(8,10));
						node.attr("day", row.day);
						node.attr("remark", row.remark);
						node.attr("workday", row.workday);
						if(row.workday=='0'){
							node.css("backgroundColor","#FFBB02");
							n.title = row.day+" 节假日"+" "+ (row.remark==null?'':row.remark);
						}else{
							n.title = row.day+" 工作日"+" "+ (row.remark==null?'':row.remark);
						}
					}
				});
			}
		});
	}
	</script>
  </head>
  
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="&nbsp;" style="height:64px; ">
  		<table id="from_query" dataType="text" align="center" border="0" style="font-size: 12px;">
  			<tr>
  				<td align="right">日期:</td>
  				<td>
  					<a href="javascript:void(0);" onclick="moveToMonth(-1)"><<</a> 
  					<input name="queryParams.day" id="inp_day" readonly="readonly" class="easyui-datebox" />
  					<a href="javascript:void(0);" onclick="moveToMonth(1)">>></a> 
  				</td>
  			</tr>
  		</table>
  	</div>
  	<div id="div_config" region="center" title="&nbsp;" class="easyui-panel" data-options="collapsible:false" style="text-align: center;" align="center">
		<div style="text-align: center;" align="center">
			<table border="0" cellpadding="1" cellspacing="1" class="day" align="center">
				<tr>
					<th>周一</th>
					<th>周二</th>
					<th>周三</th>
					<th>周四</th>
					<th>周五</th>
					<th>周六</th>
					<th>周日</th>
				</tr>
				<tbody id="tb_days" >
					<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
					<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
				</tbody>
			</table>
			<table border="0" cellpadding="1" cellspacing="1" align="center" style="background-color: #FFFFFF;">
				<tbody>
					<tr><td>图例：</td><td></td></tr>
					<tr><td style="background-color: #FFBB02;">&nbsp;</td><td>节假日</td></tr>
				</tbody>
			</table>
		</div>
		
		<div id="win_workday" title="工作日编辑" class="easyui-dialog" closed="true" style="width:480; height:194;" modal="true" buttons="#win_workday_buttons">
	  		<div id="form_workday" class="bgColor" style="height: 100%;">
	  			<table id="from_wf"  dataType="text" class="tablestyle01"  style="width:100%">
		  			<tr>
		  				<td align="right" width="18%">日期:</td>
		  				<td><input name="workday.day"  readonly="readonly"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right" width="18%">类型:</td>
		  				<td>
			  				<select name="workday.workday">
					  			<option value="1">工作日</option>
					  			<option value="0">节假日</option>
					  		</select>
			  			 </td>
		  			</tr>
		  			<tr>
		  				<td align="right" width="18%">描述:</td>
		  				<td><input name="workday.remark" maxlength="60"/></td>
		  			</tr>
  				</table>
		  		<div id="win_workday_buttons">
		  			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveWorkday();">保存</a>
		  			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#win_workday').dialog('close');">取消</a>
		  		</div>
	  		</div>
	  	</div>
  	</div>
  	
  	<div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px;" >
    	<a href="#" class="easyui-linkbutton" onclick="list();">查询</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>