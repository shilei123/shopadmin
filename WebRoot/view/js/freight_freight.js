$(function() {
	queryFreight();
});

var queryFreight = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/freight/freight!queryFreight.action";
	pageData(url, "freightListTable", data);
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryFreight();
});

var openWin = function(title) {
	$("#titles").text(title);
	showModal("editeFreightModal",740,450);
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showOrHideDefault(\""+ row["id"]+ "\")'><span class='am-icon-check-square-o'></span>是否设置默认</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='ShowContactLogistics(\""+ row["id"]+ "\")'><span class='am-icon-check-square-o'></span>设置物流</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>编辑</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//弹出新增窗口
$("#addBtn").click(function() {
	var freightId = "";
	$('#freightParamsFrame').attr('src', path_ + '/view/shop/freight/freight_property.jsp?freightId='+freightId);
	openWin("新增");
});

//编辑
var showEditWin = function(id) {
	var freightId = id;
	$('#freightParamsFrame').attr('src', path_ + '/view/shop/freight/freight_property.jsp?freightId='+freightId);
	openWin("编辑");
};

var ShowContactLogistics = function(id){
	closeTab("contactLogisticsTabId");
	openTab("contactLogisticsTabId","关联物流",path_+"/view/shop/order/orderdetail.jsp?tabId=contactLogisticsTabId&freightId="+id);
}

var showOrHideDefault = function(id){
	showConfirm("确认设为默认？", function() {
		var data = {"fre.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/freight/freight!saveIsuse.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryFreight();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
}

//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"fre.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/freight/freight!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryFreight();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};