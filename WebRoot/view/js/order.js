$(function() {
	queryOrder();
	queryOrderStatus();
});

var queryUserBcuser = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/StatisticsManage/order!queryOrder.action";
	pageData(url, "orderListTable", data); 
};

var queryOrderStatus = function() {
	var orderStatus = $("#orderStatus");
	orderStatus.empty();
	$.ajax({
		url :path_ + "/view/shop/StatisticsManage/order!queryOrderStatus.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			var html = "<option value='-1'>-请选择-</option>";
			$(data.userSexList).each(function(index) {
				var userSexType = data.userSexList[index];
				html += "<option value='" + userSexType.code + "'>" + userSexType.name + "</option>";
			});
			orderStatus.append(html);
		}
	});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryOrder();
});





