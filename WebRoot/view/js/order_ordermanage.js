$(function() {
	initOrderType();
	query();
});

var initOrderType = function() {
	var data = {"initType.orderDeliveryMode":"ORDER_DELIVERY_MODE",
				"initType.orderSts":"ORDER_STS",
				"initType.ordeSplit":"ORDER_SPLIT",
				"initType.orderInvoice":"ORDER_INVOICE",
				"initType.orderPayMode":"ORDER_PAY_MODE"};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/order/order!initDictsByType.action",
		data : data,
		dataType : "json",
		success : function(data) {
			var initMap = data.initMap;
			//console.log(initMap);
			initSelect(initMap.orderDeliveryMode, "orderDeliveryMode");
			initSelect(initMap.orderSts, "orderSts");
			initSelect(initMap.ordeSplit, "ordeSplit");
			initSelect(initMap.orderInvoice, "orderInvoice");
			initSelect(initMap.orderPayMode, "orderPayMode");
			actualPrice = document.getElementById("endActualPrice");
		},
		error : function(e) {
		}
	});
}

var initSelect = function(data, id){
	var html = "<option value=''>-请选择-</option>";
	for(x in data){
		html += "<option value='" + data[x].code + "'>" + data[x].name + "</option>"
	}
	$("#" + id).html(html);
}

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

$('#closeBtn').click(function() {
	closeModal("doc-modal-1");
});
$('#closeBtn2').click(function() {
	closeModal("doc-modal-2");
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/order/order!queryOrderList.action";
	pageData(url, "orderListTable", data);
};

var formatterAction = function(value, row) {
	var orderstatus = row["orderstatus"];
	var paymode = row["paymode"];
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a id='a1' href='javascript:void(0)' onclick='showQueryOrderWin(\""+ row["id"]+ "\",\""+ row["orderCode"]+ "\")'><span class='am-icon-search'></span><span id='a1span'>查看详情</span></a>";
	if(paymode=="货到付款"){
		if(orderstatus=="已提交"){
			//"货到付款"且"已提交"加上确认订单功能
			html += "<a id='a2' href='javascript:void(0)' onclick='showConfirmOrderWin(\""+ row["id"]+ "\",\""+ row["orderCode"]+ "\")'><span class='am-icon-check'></span>确认订单</a>";
		}else if(orderstatus=="已确认"){
			//"货到付款"且"已确认"加上取消订单功能
			html += "<a id='a2' href='javascript:void(0)' onclick='showCancelOrderWin(\""+ row["id"]+ "\",\""+ row["orderCode"]+ "\")'><span class='am-icon-remove'></span>取消订单</a>";
		}
	}else{//线上支付
		if(orderstatus=="待付款"){
			//"线上支付或支付宝微信"且"待付款"加上调整费用功能
			html += "<a id='a2' href='javascript:void(0)' onclick='showChangePriceOrderWin(\""+ row["id"]+ "\",\""+ row["orderCode"]+ "\",\""+ row["nickName"]+ "\",\""+ row["actualPrice"]+ "\")'><span class='am-icon-adjust'></span>调整费用</a>";
		}else if(orderstatus=="待发货"){
			//"线上支付或支付宝微信"且"待发货"加上取消订单功能
			html += "<a id='a2' href='javascript:void(0)' onclick='showCancelOrderWin(\""+ row["id"]+ "\",\""+ row["orderCode"]+ "\")'><span class='am-icon-remove'></span>取消订单</a>";
		}
	}
	html += "</div>";
	return html;
};

var showQueryOrderWin = function(id, orderCode) {
	closeTab("queryOrderDetailTabId");
	openTab("queryOrderDetailTabId","订单详情("+orderCode+")",path_+"/view/shop/order/orderdetail.jsp?tabId=queryOrderDetailTabId&orderId="+id);
};

var showConfirmOrderWin = function(id, orderCode) {
	$('#title').text("确认货到付款订单");
	$('#orderCode1').val(orderCode);
	$('#orderId1').val(id);
	$('#operate1').val("confirm");
	showModal("doc-modal-1",400,190);
};

var showCancelOrderWin = function(id, orderCode) {
	$('#title').text("取消订单");
	$('#orderCode1').val(orderCode);
	$('#orderId1').val(id);
	$('#operate1').val("cancel");
	//取消订单、修改该订单以及子订单的order_status
	showModal("doc-modal-1",400,190);
};

var showChangePriceOrderWin = function(id, orderCode, nickName, actualPrice) {
	//调整费用、修改该订单以及子订单的actual_price
	$('#orderId2').val(id);
	$('#orderCode2').val(orderCode);
	$('#nickName2').val(nickName);
	$('#actual_price2').val(actualPrice);
	showModal("doc-modal-2",400,270);
};

//确认订单和取消订单（修改该订单以及子订单的orderStatus）
$('#confirmBtn').click(function() {
	var val = $('#operate1').val();
	var data = {"order.id":$('#orderId1').val()};
	var url = "";
	if(val=="confirm"){
		url = path_ + "/view/shop/order/order!confirmOrder.action";
		modifyOrderField(data, url);
	}else if(val=="cancel"){
		url = path_ + "/view/shop/order/order!cancelOrder.action";
		modifyOrderField(data, url);
	}
});

var modifyOrderField = function (data, url){
	$.ajax({
		type : "POST",
		url : url,
		data : data,
		dataType : "json",
		success : function(json) {
			closeModal("doc-modal-1");
			showAlert("操作成功");
			query();
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
};

//修改订单价格
$('#changeBtn2').click(function() {
	var actualPrice = $('#actual_price2').val();
	if(isNaN(actualPrice)){
		alert("请输入数字!");
		return;
	}
	var data = {"order.id":$('#orderId2').val(),"order.actualPrice":actualPrice};
	var url = path_ + "/view/shop/order/order!changePriceOrder.action";
	$.ajax({
		type : "POST",
		url : url,
		data : data,
		dataType : "json",
		success : function(json) {
			closeModal("doc-modal-2");
			showAlert("操作成功");
			query();
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});