$(function() {
	queryBill();
	findBillType();
});

var queryBill = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/alterService/after!queryBill.action";
	pageData(url, "billListTable", data); 
};

var findBillType = function() {
	var kind = $("#kind");
	kind.empty();
		$.ajax({
			url :path_ + "/view/shop/alterService/after!queryBillType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				var html = "<option value='-1'>-请选择-</option>";
				$(data.kindList).each(function(index) {
					var billType = data.kindList[index];
					html += "<option value='" + billType.code + "'>" + billType.name + "</option>";
				});
				kind.append(html);
			}
		});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryBill();
});

var openWin = function(title) {
	$("#title").text(title);
	showModal("editBillModal",740,450);
	
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showqueryBillWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看详情</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteBill(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};


//查看详情
var showqueryBillWin = function(id) {
	$('#billParamsFrame').attr('src', path_ + '/view/shop/alterService/returnGoodsTetail.jsp?billId='+id);
	openWin();
};

//删除
var deleteBill = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"bill.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/alterService/after!deleteBill.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryBill();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};

