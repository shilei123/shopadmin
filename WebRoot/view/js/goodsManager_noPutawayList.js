$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/goodsManager/goodsInfoAction!queryNoPutawayList.action";
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
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditGoodsTab(\""+ row["id"]+ "\")'><i class='am-icon-edit'></i>编辑</a>";
	if(row["goodsSts"]=="1") { //入库
		html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-success' onclick='putawayGoods(\""+ row["id"]+ "\")'><i class='am-icon-check-square-o'></i>上架</a>";
	} else if(row["goodsSts"]=="2") { //定时上架
		/*html += "&nbsp;&nbsp;"+row.publishTime==null?"":row.publishTime.replace("T"," ");*/
	}
	html += "</div>";
	return html;
};

var putawayGoods = function(goodsId) {
	var data = {"goods.id":goodsId};
	showConfirm("确认上架？",function() {
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/goodsManager/goodsInfoAction!putaway.action",
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