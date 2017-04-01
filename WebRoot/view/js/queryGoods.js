var index = parent.layer.getFrameIndex(window.name); 
$(function() {
	initAttachUploadInfo(); //初始化
});

var initAttachUploadInfo = function() {
	queryGoods();//查询商品列表
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryGoods();
});

//查询商品列表
var queryGoods = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/advertise/advertise!queryGoods.action";
	pageData(url, "goodsListTable", data); 
};


var formatterAttachAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='saveGoods(\""+ row.id + "\",\""+row.goodsName+"\")'><span class='am-icon-search'></span>选择</a>";
	html += "</div>";
	return html;
};

var saveGoods = function(goodsId, goodsName) {
	if(parent.selectGoods!=undefined) {
		var obj = {goodsId:goodsId,goodsName:goodsName};
		parent.selectGoods(obj);
		parent.layer.close(index);
	}
};

$("#closeBtn").click(function(){
	parent.layer.close(index);
});

