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
			        	<legend>会员信息</legend>
			        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
				  				<td width="100px" class="table_title">姓名：</td>
				  				<td>
				  					<input id="userName" disabled="disabled"/> 
				  				</td>
				  				<td class="table_title">电话：</td>
						   		<td>
						   		<div>
						   		<input id="phone" disabled="disabled"/>
						   		</div>
								</td>
				  		</tr>
				  		<tr>
				  				<td width="100px" class="table_title">邮箱：</td>
				  				<td>
				  					<input id="mail" disabled="disabled"/> 
				  				</td>
				  				<td class="table_title">性别：</td>
						   		<td>
									<select id="sex" disabled="disabled">
									<option value="">请选择</option>
									<option value="0">男</option>
									<option value="1">女</option>
									</select>
								</td>
				  		</tr>
			        	</table>
			        	<legend>商品信息</legend>
			        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
				  				<td width="100px" class="table_title">商品名称：</td>
				  				<td>
				  					<input id="goodsName" disabled="disabled"/> 
				  				</td>
				  				<td class="table_title">商品价格：</td>
						   		<td>
						   		<div>
						   		<input id="goodsPrice" disabled="disabled"/>
						   		</div>
								</td>
				  		</tr>
				  		<tr>
				  				<td width="100px" class="table_title">商品数量：</td>
				  				<td>
				  					<input id="numbs" disabled="disabled"/> 
				  				</td>
				  				<td class="table_title">货号：</td>
						   		<td>
						   		<div>
						   		<input id="goodsNo" disabled="disabled"/>
						   		</div>
								</td>
				  		</tr>
			        	</table>
			        	<legend>单据信息</legend>
			        	<table id="edit_adv_table" class="frame-modal-table" border="0" bordercolor="black">
			        		<tr>
				  				<td width="100px" class="table_title">名称：</td>
				  				<td>
				  					<input id="name" disabled="disabled"/> 
				  				</td>
				  				<td class="table_title">申请时间：</td>
						   		<td>
						   		<div>
						   		<input id="startTime" disabled="disabled"/>
						   		</div>
								</td>
				  			</tr>
				  			<tr>
				        		<td class="table_title">类型：</td>
								<td>
									<input id="kind"  disabled="disabled"/>
								</td>
								<td class="table_title">状态：</td>
								<td>
									<input id="status"  disabled="disabled"/>
								</td>
				        	</tr>
				  			<tr>
				        		<td class="table_title">单据编号：</td>
								<td>
									<input id="code"  disabled="disabled"/>
								</td>
								<td class="table_title">订单编号：</td>
								<td>
									<input id="orderCode"  disabled="disabled"/>
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