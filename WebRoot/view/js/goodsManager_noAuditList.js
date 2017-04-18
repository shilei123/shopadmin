$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/goodsManager/goodsInfoAction!queryNoAuditList.action";
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
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditGoodsTab(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>修改</a>";
	if(row["auditSts"]=="1") {
		html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-success' onclick='passGoods(\""+ row["id"]+ "\")'><span class='am-icon-check-square-o'></i>通过</a>";
		html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-warning' onclick='noPassGoods(\""+ row["id"]+ "\")'><span class='am-icon-minus-square-o'></i>不通过</a>";
	}
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteGoods(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

var showEditGoodsTab = function(goodsId) {
	openTab("editGoodsTabId"+goodsId,"编辑商品",path_+"/view/shop/goodsManager/goodsAdd.jsp?tabId=editGoodsTabId"+goodsId+"&goodsId="+goodsId);
};

var passGoods = function(goodsId) {
	var data = {"goods.id":goodsId};
	showConfirm("确认审核通过？",function() {
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/goodsManager/goodsInfoAction!pass.action",
			data : data,
			dataType : "json",
			success : function(json) {
				query();
				showMsg("操作成功！");
			},
			error : function(e) {
				showAlert("操作失败！");
			}
		});
		
	});
};

var noPassGoods = function(goodsId) {
	var data = {"goods.id":goodsId};
	showConfirm("确认审核不通过？",function() {
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/goodsManager/goodsInfoAction!noPass.action",
			data : data,
			dataType : "json",
			success : function(json) {
				query();
				showMsg("操作成功！");
			},
			error : function(e) {
				showAlert("操作失败！");
			}
		});
		
	});
};

var deleteGoods = function(goodsId) {
	var data = {"goods.id":goodsId};
	showConfirm("确认删除？",function() {
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/goodsManager/goodsInfoAction!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				query();
				showMsg("操作成功！");
			},
			error : function(e) {
				showAlert("操作失败！");
			}
		});
		
	});
};