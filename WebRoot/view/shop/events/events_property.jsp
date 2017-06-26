<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String eventsId = request.getParameter("eventsId")==null?"":request.getParameter("eventsId");
request.setAttribute("eventsId", eventsId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>活动管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf">
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-g">
	            <div align="center">
		        	<table id="edit_events_table" class="frame-modal-table" border="0" bordercolor="black">
		        		<input type="hidden" name="events.id" id="vId" value="${eventsId}"/>
			        	<tr>
			        		<td width="100px" class="table_title">活动名称：</td>
			        		<td><input name="events.name" maxlength="50" id="name" placeholder="活动名称" class="am-form-field" style="width:152px"/></td>
				        	<td width="100px" class="table_title">是否启用：</td>
				        	<td>
				        		<select name="events.isuse" id="isuse" data-am-selected="{btnWidth: '152px'}">
				        	</td>
			        	</tr>
			        	<tr>
			        		<td>活动开始时间：</td>
					   		<td valign="top">
					   		<div>
								<input class="am-form-field" name="events.startTime" id="startTime" style="width:152px"/>
					   		</div>
							</td>
							<td>活动结束时间：</td>
							<td>
							<div>
							 <input class="am-form-field" name="events.endTime" id="endTime" style="width:152px"/>
							</div>
	        				</td>
			        	</tr>
			        	<tr>
			        		<td valign="top" class="table_title"><div style="margin-top: 5px;">活动介绍：</div></td>
			        		<td valign="top" colspan="3"> 
		        				<textarea name="events.memo" id="memo" placeholder="活动介绍" style="width:97%;height:150px;margin-top: 5px;" class="am-form-field"></textarea> 
		        		</td>
		        		</tr>
		        		<tr>
			        	<td width="100px" class="table_title" valign="top"><div style="margin-top: 13px;">活动商品：</div></td>
			        	<input type="hidden" name="eventsGoodsList" id="eventsGoodsList" />
			  			<td colspan="3">
							<div style="margin-top: 10px;" id="paramsDiv">
								<div style="padding: 1px;margin-left: 4px">
									<strong>商品名称：</strong><input class="am-form-field" name="goodsName" id="goodsName" readonly="readonly" style="width:150px;display: inline;"/>&nbsp;
									<input type="hidden" name="goodsId" id="goodsId"/>
									<a id="chooseGoodsBtn">选择商品</a>&nbsp;
									<strong>商品活动价格：</strong><input class="am-form-field" name="eventsMoney" id="eventsMoney" style="width:150px;display: inline;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<strong>生效范围：</strong><select name="eventsGoods.scope" id="scope" data-am-selected="{btnWidth: '78px'}">
											<option value="1">所有</option>
											<option value="0">活动</option>
				                          	</select>
									&nbsp;&nbsp;&nbsp;&nbsp;<a class="addGoodsParam" href="javascript:void(0);"><span class='am-icon-plus-square' style="width: auto;color: green;font-size: 18px;" title="添加"></span></a>
								</div>
							</div>
						</td>
			        	</tr>
			        	<tr style="display: none;">
								<td class="table_title frame-required" valign="top"></td>
								<td valign="top" colspan="3">
									<div id="goodsChildDiv">
									<table id="goodsChildTable" class="frame-modal-table" border="0" bordercolor="black">
											<tbody></tbody>
										</table>
									</div>
								</td>
							</tr>
		       	 	</table>
		       	 	<div align="center" id="errorMsg" style="color: red;margin-top: 5px;margin-bottom: 10px;">&nbsp;</div>
		       	 	<div align="center">
						<button type="button" class="am-btn am-btn-success" id="saveBtn"><span class="am-icon-save"></span> 保存</button>
						<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
					</div>
	           	</div>
      		</div>
    	</div>
  	</div>
</div>
</body>
<script type="text/javascript" src="${path }/view/js/events_eventsProperty.js"></script>
</html>