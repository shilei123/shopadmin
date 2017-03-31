var index = parent.layer.getFrameIndex(window.name); 
$(function() {
	initAttachUploadInfo(); //初始化
});

var initAttachUploadInfo = function() {
	queryEvents();//查询商品列表
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryEvents();
});

//查询商品列表
var queryEvents = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/events/events!query.action";
	pageData(url, "eventsListTable", data); 
};


var formatterAttachAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='saveEvents(\""+ row.id + "\",\""+row.name+"\")'><span class='am-icon-search'></span>选择</a>";
	html += "</div>";
	return html;
};

var saveEvents = function(eventsId, name) {
	if(parent.selectEvents!=undefined) {
		var obj = {eventsId:eventsId,name:name};
		parent.selectEvents(obj);
		parent.layer.close(index);
	}
};

$("#closeBtn").click(function(){
	parent.layer.close(index);
});

