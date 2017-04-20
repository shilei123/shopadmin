$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/goodsManager/goodsInfoAction!queryPutawayList.action";
	pageData(url, "dataListTable", data);
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

var formatterTitle = function(value, row) {
	return "<a href='javascript:void(0);' onclick='showEditGoodsTab(\""+ row["id"]+ "\")'>"+value+"</a>";
};

var formatterAuditSts = function(value, row) {
	var html = "";
	if(value=="1") {
		html = "待审核";
	} else if(value=="2") {
		html = "通过";
	} else if(value=="3") {
		html = "不通过";
	}
	return html;
};

var formatterGoodsSts = function(value, row) {
	var html = "";
	if(value=="1") {
		html = "入库";
	} else if(value=="2") {
		html = "定时上架";
	} else if(value=="3") {
		html = "上架";
	} else if(value=="4") {
		html = "下架";
	}
	return html;
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditGoodsTab(\""+ row["id"]+ "\")'><i class='am-icon-edit'></i>编辑</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='soldOutGoods(\""+ row["id"]+ "\")'><i class='am-icon-remove'></i>下架</a>";
	html += "</div>";
	return html;
};

var soldOutGoods = function(goodsId) {
	var data = {"goods.id":goodsId};
	showConfirm("确认下架？",function() {
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/goodsManager/goodsInfoAction!soldOut.action",
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
