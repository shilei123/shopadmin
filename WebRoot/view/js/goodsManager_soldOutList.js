$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/goodsManager/goodsInfoAction!querySoldOutList.action";
	pageData(url, "dataListTable", data);
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

var formatterTitle = function(value, row) {
	return "<a href='javascript:void(0);' onclick='showEditGoodsTab(\""+ row["id"]+ "\")'>"+value+"</a>";
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditGoodsTab(\""+ row["id"]+ "\")'><i class='am-icon-edit'></span>编辑</a>";
	if(row["auditSts"]=="1") {
		html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteBank(\""+ row["id"]+ "\")'><i class='am-icon-remove'></i>通过</a>";
		html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteBank(\""+ row["id"]+ "\")'><i class='am-icon-remove'></i>不通过</a>";
	}
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteBank(\""+ row["id"]+ "\")'><i class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};
