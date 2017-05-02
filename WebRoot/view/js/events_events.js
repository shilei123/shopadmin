//加载layui的laydate组件
layui.use('laydate', function(){});
$("#startTime").click(function() {
	layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
});
$("#endTime").click(function() {
	layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
});	
$(function() {
	queryEvents();
});

var queryEvents = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/events/events!query.action";
	pageData(url, "eventsListTable", data); 
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryEvents();
});

var openWin = function(title) {
	$("#titles").text(title);	
	showModal("editeEventsModal",895,460);
};

//弹出新增窗口
$("#addBtn").click(function() {
	var eventsId = "";
	$('#eventsParamsFrame').attr('src', path_ + '/view/shop/events/events_property.jsp?eventsId='+eventsId);
	openWin("新增");
});

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>编辑</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></span>删除</a>";
	html += "</div>";
	return html;
};

//编辑
var showEditWin = function(id) {
	var eventsId = id;
	$('#eventsParamsFrame').attr('src', path_ + '/view/shop/events/events_property.jsp?eventsId='+eventsId);
	openWin("编辑");
};

//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"events.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/events/events!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryEvents();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};
