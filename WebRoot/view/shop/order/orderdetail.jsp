<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String orderId = request.getParameter("orderId")==null?"":request.getParameter("orderId");
request.setAttribute("orderId", orderId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>订单管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style>
  .orderTable ul li,.prop_li{
	width:50%;
	float:left;
	padding:0px;
  }
  #a_connet:hover{
  	cursor: pointer;
  }
  th{
  	height:30px;
  }
  body{
  	overflow-y: scroll;
  }
  .bigTh{
  	height: 40px;
  	font-size: 16px;
  	background-color: #fbfbfb;
  }
  th{
  	border-top: #fbfbfb solid 1px;
  	border-bottom: #fbfbfb solid 1px;
  }
  .red{
  	color:red;
  }
</style>
</head>
<body>
	<input type="hidden" id="orderId" value="${orderId }"/>
	<table class="am-tabl am-table-hover orderTable" style="width:100%; margin:10px;" id="orderDetailTable">
		<tbody>
		<tr>
			<th style="background-color: '#e2e2e2';" class='bigTh'>订单状态</th>
		</tr>
		<tr>
			<td>
				<ul>
					<li>订单编号：<span id='orderCode'></span></li>
					<li>订单状态：<span id='orderStatus'></span></li>
					<li>订单金额：<span id='totalPrice' class='red'></span></li>
					<li>运费：<span id='commisionCharge'></span></li>
					<li>支付金额：<span id='actualPrice' class='red'></span></li>
				</ul>
			</td>
		</tr>
		<tr>
            <th class='bigTh'>订单详情</th>
        </tr>
        <tr><td></td></tr>
        <tr>
            <th style="border-top: none;">订单信息</th>
        </tr>
        <tr>
            <td>
            	<ul>
	                <li>买家：<span id='nickName'></span></li>
					<li>件数：<span id='num'></span></li>
					<li>支付方式：<span id='payMode'></span></li>
					<li>下单时间：<span id='createTime'></span></li>
					<li>是否拆分：<span id='issplit'></span></li>
					<!-- <li><strong id='strong_connect'>关联订单：<span id='connectOrder'></span></li> -->
            	</ul>
            </td>
        </tr>
        <tr>
            <th>子订单信息</th>
        </tr>
        <tr>
            <td id='sonOrdersInfo_td'></td>
        </tr>
        <tr>
            <th>发票信息</th>
        </tr>
        <tr>
            <td id='invoiceInfo_td'></td>
        </tr>
        <tr>
            <th>商品信息</th>
        </tr>
        <tr>
            <td id='goodsInfo_td'></td>
        </tr>
        <tr>
            <th>收货信息</th>
        </tr>
         <tr>
            <td>
            	<ul>
	                <li>收货人姓名：<span id='recName'></span></li>
					<li>收货人手机：<span id='phoneNum'></span></li>
					<li>邮编：<span id='postNum'></span></li>
					<li>收货地址：<span id='province'></span><span id='city'></span><span id='county'></span></li>
					<li>详细地址：<span id='addressDetail'></span></li>
            	</ul>
            </td>
        </tr>
		</tbody>
	</table>
	<button class='am-btn am-btn-primary frame-search-button' id='retBtn' style="margin: 20px;">关闭</button>
</body>
<script type="text/javascript" src="${path }/view/js/order_orderdetail.js"></script>
</html>