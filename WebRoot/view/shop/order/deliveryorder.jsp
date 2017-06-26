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
<title>发货管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style>
 /*  .orderTable ul li,.prop_li{
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
  	overflow-x: hidden;
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
  } */
  .goodsInfo_table td, .goodsInfo_table th{
  	border:1px solid gray;
  }
  .bigTh{
  	height: 40px;
  	font-size: 16px;
  }
  th, td{
  	padding:5px;
  }
</style>
</head>
<body>
<input type="hidden" id="orderId" value="${orderId }"/>
<div class="am-cf ">
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-cf am-padding am-padding-bottom-0">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">发货管理</strong> / <small>发货</small>
				</div>
			</div>
			<hr>
	<table class="orderTable" style="width:95%; margin:10px;" id="orderDetailTable">
		<tbody>
		<tr>
			<th style="background-color: '#e2e2e2';" class='bigTh'>第一步：确认商品信息</th>
		</tr>
		<tr>
			<td>
				<table style="width:100%; vertical-align:middle;">
					<tbody>
						<!-- <tr>
							<th></th>
							<th>订单编号</th>
							<th>商品名称</th>
							<th>单价</th>
							<th>件数</th>
							<th>小计</th>
						</tr> -->
						<tr>
							<td id="goodsInfo_td"></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
            <th class='bigTh'>第二步：确认收货信息</th>
        </tr>
        <tr>
            <td id='addressInfo_td'></td>
        </tr>
        <tr>
            <th class='bigTh'>第三步：确认发票信息</th>
        </tr>
        <tr>
            <td id='invoiceInfo_td'></td>
        </tr>
        <tr>
            <th class='bigTh'>第四步：填写物流信息<small>（虚拟商品无须填写快递信息）</small></th>
        </tr>
        <tr>
            <td id='logisticsInfo_td'></td>
        </tr>
		</tbody>
	</table>
	<!-- <button class='am-btn am-btn-primary frame-search-button' id='retBtn' style="margin: 20px;">关闭</button> -->
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$(function() {
	initPage();
	initLogisticsInfo();
});

var initPage = function(){
	var data = {"order.id" : $('#orderId').val()};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/order/deliveryRecord!initDeliveryPageByOrderId.action",
		data : data,
		dataType : "json",
		success : function(data) {
			var goodsList = data.goodsList;
			var addressMap = data.addressMap;
			var invoiceList = data.invoiceList;
			console.log(goodsList);
			console.log(addressMap);
			console.log(invoiceList);
			
			setGoodsInfo(data.goodsList);
			setAddressInfo(data.addressMap);
			setInvoiceInfo(data.invoiceList);
		}, error : function(e) {
			showAlert("操作失败！");
		}
	});
}

var initLogisticsInfo = function(){
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/logisticsSetting/logisticsSetting!queryAllCompany.action",
		dataType : "json",
		success : function(data) {
			console.log(data);
		}, error : function(e) {
			//showAlert("操作失败！");
		}
	});
	
	/* <table style="width: 100%;">
	<tr><td style="width: 150px; text-align: center;">物流公司：</td><td><input type='text' id='expressCompany'/></td></tr>
	<tr><td style="width: 150px; text-align: center;">物流单号：</td><td><input type='text' id='expressNum'/></td></tr>
	<tr><td style="width: 150px; text-align: center;"><input type="checkbox" id='isVirtual'/>是否虚拟商品</td><td><button id='submitBtn'>提交</button></td></tr>
	</table> */
}

var setGoodsInfo = function(goodsList){
	var html = "";
	if(goodsList[0]==undefined || goodsList[0]==null){
		html += "<ul><li style='width: 100%;'>未关联到商品信息</li></ul>";
	}else{
		html += "<table style='width:100%; vertical-align:middle;' class='goodsInfo_table'><tbody>"
		for (var i in goodsList) {
			html += "<tr>";
			html += "<th></th>";
			html += "<th>商品名称</th>";
			html += "<th>单价</th>";
			html += "<th>件数</th>";
			html += "<th>小计</th>";
			html += "</tr><tr>";
			html += "<td style='width:15%;'><div><span class=''><a href='javascript:void(0);' target='_blank'><img src=''/>" + "缺少主图字段，暂未关联" + "</a></span></div></td>";
			html += "<td><a href='' target='_blank'>" + goodsList[i].goodsName + "</a></td>";
			html += "<td><span class='red'>￥" + goodsList[i].unitPrice + "</span></td>";
			html += "<td>" + goodsList[i].count + "件</td>";
			html += "<td><span class='red'>￥" + goodsList[i].amount + "</span></td>";
            html += "</tr>"
		}
		html += "</table></tbody>"
	}
	$("#goodsInfo_td").append(html);
}

var setAddressInfo = function(addressMap){
	var html = "";
	if(addressMap==null || addressMap==undefined || addressMap.deliveryRecordId==null || addressMap.deliveryRecordId==undefined){
		html += "<ul><li style='width: 100%;'>未关联到收货信息</li></ul>";
	}else{
		html += "<table style='width:100%;'>";
		html += "<tr>";
		html += "<td style='width:150px;'>收货人姓名：</td><td><span id='recName'>" + addressMap.name + "</span></td>";
		html += "<td style='width:150px;'>收货人手机：</td><td><span id='phone'></span>" + addressMap.phoneNum + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>邮编：</td><td><span id='postCode'>" + addressMap.postNum + "</span></td>";
		html += "<td>收货地址：</td><td><span id='province'>" + addressMap.province + "</span><span id='city'>" + addressMap.city + "</span><span id='county'>" + addressMap.county + "</span></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>详细地址：</td><td><span id='addressDetail'>" + addressMap.addressDetail + "</span></td>";
		html += "</tr>";
		html += "</ul>";
	}
	$("#addressInfo_td").append(html);
}

var setInvoiceInfo = function(invoiceList){
	var html = "";
	if(invoiceList==null || invoiceList==undefined){
		html += "<ul><li style='width: 100%;'>未关联到发票信息</li></ul>";
	}else if(invoiceList.invoice=="0"){
		html += "<ul><li style='width: 100%;'>不开发票</li></ul>";
	}else{
		for (var i in invoiceList) {
			html += "<ul>"
			html += "<li class='prop_li'>发票编号：<span id=''>" + invoiceList[i].invoiceCode + "</span></li>";
			html += "<li class='prop_li'>抬头：<span id=''>" + invoiceList[i].header + "</span></li>";
			html += "<li class='prop_li'>姓名：<span id=''>" + invoiceList[i].invoiceName + "</span></li>";
			html += "<li class='prop_li'>发票内容：<span id=''>" + invoiceList[i].content + "</span></li>";
			var remark = judgeNull(invoiceList[i].remark, "暂无备注");
			html += "<li class='prop_li'>备注：<span id=''>" + remark + "</span></li>";
			html += "</ul>"
		}
	}
	$("#invoiceInfo_td").append(html);
}

$("#retBtn").click(function() {
	closeThisTab();
});

var judgeNull = function(tempVar, tempRet){
	if(tempVar==null || tempVar==undefined)
		return tempRet;
	return tempVar;
}
</script>
</html>