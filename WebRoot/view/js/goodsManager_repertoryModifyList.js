$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/goodsManager/goodsInfoAction!queryAllGoodsList.action";
	pageData(url, "dataListTable", data);
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

var formatterTitle = function(value, row) {
	return "<a href='javascript:void(0);' onclick='showGoodsDetailTab(\""+ row["id"]+ "\")'>"+value+"</a>";
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showRepEditWin(\""+ row["id"]+ "\")'><i class='am-icon-edit'></i>修改库存</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showPriceEditWin(\""+ row["id"]+ "\")'><i class='am-icon-edit'></i>修改价格</a>";
	html += "</div>";
	return html;
};

$("#closeBtn1").click(function() {
	closeModal("priceEditModel");
});

$("#closeBtn2").click(function() {
	closeModal("repEditModel");
});

var showRepEditWin = function(id) {
	showModal("repEditModel", 300, 300);
};

var showPriceEditWin = function(id) {
	showModal("priceEditModel", 300, 300);
};