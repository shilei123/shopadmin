<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String billId = request.getParameter("billId")==null?"":request.getParameter("billId");
request.setAttribute("billId", billId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>详情</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf">
	<div class="admin-content">
    	<div class="admin-content-body">
			<div class="am-g">
      				<input type="hidden" name="vo.id" id="billId" value="${billId }"/>
			        <div align="center">
			        	<legend>单据信息</legend>
			        	<table id="edit_adv_table" class="frame-modal-table" border="0" bordercolor="black">
			        		<tr>
				  				<td width="100px" class="table_title">名称：</td>
				  				<td>
				  					<div><span id="name"></span></div> 
				  				</td>
				  				<td class="table_title">申请时间：</td>
						   		<td>
						   		<div><span id="startTime"></span></div> 
								</td>
				  			</tr>
				  			<tr>
				        		<td class="table_title">类型：</td>
								<td>
									<div><span id="kind"></span></div> 
								</td>
								<td class="table_title">状态：</td>
								<td>
									<div><span id="status"></span></div> 
								</td>
				        	</tr>
				  			<tr>
				        		<td class="table_title">单据编号：</td>
								<td>
									<div><span id="code"></span></div> 
								</td>
								<td class="table_title">订单编号：</td>
								<td>
									<div><span id="orderCode"></span></div> 	
								</td>
				        	</tr>
				        	<tr>
				  				<td width="100px" class="table_title">申请人姓名：</td>
				  				<td>
				  					<div><span id="userName"></span></div>
				  				</td>
				  				<td class="table_title">电话：</td>
						   		<td>
						   		<div>
						   			<div><span id="phone"></span></div>
						   		</div>
								</td>
				  			</tr>
				  			<tr>
				  				<td class="table_title">原因：</td>
				  				<td valign="top" colspan="4">
				  					<textarea  id="reason" rows="5"  style="width:83%;height:50px;margin-top: 5px;"  disabled="disabled" ></textarea>
				  				</td>
				  			</tr>
				  			<tr>
				  				<td class="table_title">描述：</td>
				  				<td valign="top" colspan="4">
				  					<textarea  id="content" rows="5"  style="width:83%;height:50px;margin-top: 5px;"  disabled="disabled" ></textarea>
				  				</td>
				  			</tr>
				  			<tr>
				  				<td class="table_title">处理结果：</td>
				  				<td valign="top" colspan="4">
				  					<textarea  id="result" rows="5"  style="width:83%;height:50px;margin-top: 5px;"   disabled="disabled" ></textarea>
				  				</td>
				  			</tr>
			       	 	</table>
			       	 	<div align="center" style="padding-top: 10px;">
							<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 关闭</button>
						</div>
		           	</div>
		    	</div>
      		</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${path }/view/js/alterService_returnGoodsTetail.js"></script>
</html>