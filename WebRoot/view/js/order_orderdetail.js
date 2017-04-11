$(function() {
//	query();
});

var openOrderInfoWin = function(title) {
	$("#title").text(title);
	showModal("doc-modal-2",500,350);
};

$("#closeBtn").click(function() {
	closeModal("doc-modal-2");
});

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

var query = function() {
	var data = formGet2("from_query");
	var url = path_ + "/view/shop/order/order!queryOrderList.action";
	pageData2(url, "orderListTable", data);
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a id='a1' href='javascript:void(0)' onclick='showQueryOrderWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span><span id='a1span'>查看</span></a>";
	/*html += "<a id='a2' href='javascript:void(0)' onclick='showEditOrderWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>编辑</a>";*/
	html += "</div>";
	return html;
};

var setOrderInfo = function(order) {
	$("#orderCode2").val(order.orderCode);
	$("#deliveryMode2").val(order.deliveryMode);
	$("#orderStatus2").val(order.orderStatus);
	//$("#content2").val(order.content);
};

var showQueryOrderWin = function(id) {
	var data = {"order.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/order/order!queryOrderById.action",
		data : data,
		dataType : "json",
		success : function(data) {
			console.log(data.orderList);
			setOrderInfo(data.order);
			openOrderInfoWin("查看订单");
		}
	});
};
