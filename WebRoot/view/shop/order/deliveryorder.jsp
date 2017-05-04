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
  .goodsInfo_table td, .goodsInfo_table th, .table_border{
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
            <th class='bigTh'>第四步：填写发票和物流信息<small>（虚拟商品无须填写快递信息）</small></th>
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
});

var initPage = function(){
	var data = {"order.id" : $('#orderId').val()};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/order/deliveryRecord!initDeliveryPageByOrderId.action",
		data : data,
		dataType : "json",
		success : function(data) {
			/* var goodsList = data.goodsList;
			var addressMap = data.addressMap;
			var invoiceList = data.invoiceList;
			console.log(goodsList);
			console.log(addressMap);
			console.log(invoiceList); */
			setGoodsInfo(data.goodsList);
			setAddressInfo(data.addressMap);
			setInvoiceInfo(data.invoiceList);
			judgeVirtualGoods();
		}, error : function(e) {
			showAlert("操作失败！");
		}
	});
}

var judgeVirtualGoods = function(){
	var data = {"order.id" : $('#orderId').val()};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/order/deliveryRecord!judgeVirtualGoods.action",
		data : data,
		dataType : "json",
		success : function(data) {
			//是虚拟商品就不用填写物流信息
			if(data.isVirtualGoodsList==null || data.isVirtualGoodsList==undefined){
				var html = "";
				html += "该订单为虚拟商品，无须填写物流信息！";
				$("#logisticsInfo_td").html(html);
			}else{
				initLogisticsInfo();
			}
		}, error : function(e) {
			//showAlert("操作失败！");
		}
	});
}

var initLogisticsInfo = function(){
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/logisticsSetting/logisticsSetting!queryAllCompany.action",
		dataType : "json",
		success : function(data) {
			var html = "";
			var companyList = data.companyList;
			html += "<table style='width: 100%;' class='table_border'>";
			html += "<tr><td style='width: 150px; text-align: center;'>发票税务编号：</td>";
			html += "<td><input class='am-form-field' type='text' id='invoiceCode' style='width:150px;' placeholder='请填写发票税务编号'/></td></tr>";
			html += "<tr><td style='width: 150px; text-align: center;'>物流公司：</td><td>";
			html += "<select class='am-form-field' style='width:150px;' id='expressId'>";
			for (var x in companyList) {
				html += "<option value='" + companyList[x].id + "'>" + companyList[x].forShort + "</option>";
			}
			html += "</select>";
			html += "</td></tr>";
			html += "<tr><td style='width: 150px; text-align: center;'>物流单号：</td>";
			html += "<td><input class='am-form-field' type='text' id='expressNum' style='width:150px;' placeholder='请填写发物流单号'/></td></tr>";
			html += "<tr><td style='width: 150px; text-align: center;'></td>";
			html += "<td><button id='submitBtn' class='am-btn am-btn-primary frame-search-button' onclick='submitLogistics();'>提交</button></td></tr>";
			html += "</table>";
			$("#logisticsInfo_td").html(html);
		}, error : function(e) {
			//showAlert("操作失败！");
		}
	});
}

var submitLogistics = function(){
	if(!checkSubmitPass()){
		return;
	}
	//发票流水记录表	发票税务编号
	//发货记录表	快递服务商id、快递单号、发货状态
	var data = {"order.id":$("#orderId").val(),
			"deliveryMap.invoiceCode":$("#invoiceCode").val(),
			"deliveryMap.expressId":$("#expressId").val(),
			"deliveryMap.expressNum":$("#expressNum").val()};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/order/deliveryRecord!delivery.action",
		data : data,
		dataType : "json",
		success : function(data) {
			if(data.msg!=null && data.msg!=undefined && data.msg!=""){
				showAlert(data.msg);
			}else{
				showAlert("操作成功！");
			}
			closeThisTab();
			//回到原页面并刷新
		}, error : function(e) {
			//showAlert("操作失败！");
		}
	});
}
var checkSubmitPass = function(){
	var invoiceCode = $("#invoiceCode").val();
	var expressId = $("#expressId").val();
	var expressNum = $("#expressNum").val();
	if(invoiceCode=="" || expressId=="" || expressNum==""){
		return false;
	}
	return true;
}

var setGoodsInfo = function(goodsList){
	var html = "";
	if(goodsList[0]==undefined || goodsList[0]==null){
		html += "未关联到商品信息！";
	}else{
		html += "<table style='width:100%; vertical-align:middle;' class='goodsInfo_table'><tbody>"
		html += "<tr>";
		html += "<th></th>";
		html += "<th>商品名称</th>";
		html += "<th>单价</th>";
		html += "<th>件数</th>";
		html += "<th>小计</th>";
		html += "</tr>";
		for (var i in goodsList) {
			html += "<tr>";
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
		html += "未关联到收货信息！";
	}else{
		html += "<table style='width:100%;' class='table_border'>";
		html += "<tr>";
		html += "<td style='width:150px;'>收货人姓名：</td><td><span id='recName'>" + addressMap.name + "</span></td>";
		html += "<td style='width:150px;'>收货人手机：</td><td><span id='phone'>" + addressMap.phoneNum + "</span></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>邮编：</td><td><span id='postCode'>" + addressMap.postNum + "</span></td>";
		html += "<td>收货地址：</td><td><span id='province'>" + addressMap.province + "</span><span id='city'>" + addressMap.city + "</span><span id='county'>" + addressMap.county + "</span></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>详细地址：</td><td><span id='addressDetail'>" + addressMap.addressDetail + "</span></td>";
		html += "<td></td><td></td></tr>";
		html += "</table>";
	}
	$("#addressInfo_td").append(html);
}

var setInvoiceInfo = function(invoiceList){
	var html = "";
	if(invoiceList==null || invoiceList==undefined){
		html += "未关联到发票信息！";
	}else if(invoiceList.invoice=="0"){
		html += "不开发票！";
	}else{
		for (var i in invoiceList) {
			html += "<table style='width:88%;' class='table_border'>";
			html += "<tr>";
			html += "<td style='width:150px;'>发票系统编号：</td><td><span id=''>" + invoiceList[i].invoiceId + "</span></td>";
			html += "<td style='width:150px;'>抬头：</td><td><span id=''>" + invoiceList[i].header + "</span></td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td>姓名：</td><td><span id=''>" + invoiceList[i].invoiceName + "</span></td>";
			html += "<td>发票内容：</td><td><span id=''>" + invoiceList[i].content + "</span></td>";
			html += "</tr>";
			html += "<tr>";
			var remark = judgeNull(invoiceList[i].remark, "暂无备注");
			html += "<td>备注：</td><td><span id=''>" + remark + "</span></td>";
			html += "<td></td><td></td>";
			html += "</tr>";
			html += "</table>"
		}
	}
	$("#invoiceInfo_td").append(html);
}

$("#retBtn").click(function() {
	closeThisTab();
});

//对空字符串进行转换处理
var judgeNull = function(tempVar, tempRet){
	if(tempVar==null || tempVar==undefined || tempVar=="")
		return tempRet;
	return tempVar;
}
</script>
</html>