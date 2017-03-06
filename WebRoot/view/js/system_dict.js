$(function() {
	queryDict();
});

var queryDict = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/dict/dict!query.action";
	pageData(url, "dictListTable", data); 
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryDict();
});

var openWin = function(title) {
	$("#title").text(title);
	showModal("editDictModal",500,430);
};

$("#closeBtn").click(function() {
	closeModal("editDictModal");
});

//弹出新增窗口
$("#addBtn").click(function() {
	clearForm();
	$("#pcode").attr("readonly","readonly");
	openWin("新增");
});

//表单验证
var checkSumbit = function() {
	var type = $("#type").val();
	if(type.length==0) {
		$("#errorMsg").html("类型不能为空！");
		$("#type").focus();
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};

//保存
$("#saveBtn").click(function() {
	//表单验证
	if(!checkSumbit()) {
		return;
	}
	
	var data = formGet("edit_dict_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/dict/dict!save.action",
		data : data,
		dataType : "json",
		success : function(json) {
			queryDict();
			closeModal("editDictModal");
			showAlert("操作成功");
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

var clearForm = function() {
	$("#dictId").val('');
	$("#type").val('');
	$("#code").val('');
	$("#name").val('');
	$("#ename").val('');
	$("#pcode").val('');
	$("#remark").val('');
	$("#errorMsg").html('&nbsp;');
};

var setDictForm = function(data) {
	$("#type").val(data.dict.type);
	$("#code").val(data.dict.code);
	$("#name").val(data.dict.name);
	$("#ename").val(data.dict.ename);
	$("#pcode").val(data.dict.pcode);
	$("#dictId").val(data.dict.id);
	$("#remark").val(data.dict.remark);
	$("#errorMsg").html("&nbsp;");
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>修改</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showAddWin(\""+ row["id"]+ "\")'><span class='am-icon-plus-square-o'></i>新增</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

var formatterIsuse = function(value, row) {
	return value=="0"?"是":"否";
};

var formatterIsEdit = function(value, row){
	return value=="0"?"是":"否";
};

var formatterParent = function(value, row){
	return row["parentType"]==null?"":row["parentType"]+"("+row["parentCode"]+")";
};

//修改
var showEditWin = function(id) {
	clearForm();
	var data = {"dict.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/dict/dict!queryDict.action",
		data : data,
		dataType : "json",
		success : function(data) {
			setDictForm(data);
			openWin("编辑");
		}
	});
};

//修改
var showAddWin = function(id) {
	clearForm();
	$("#pcode").attr("readonly","readonly");
	$("#pcode").val(id);
	openWin("新增");
};

//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"dict.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/dict/dict!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryDict();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};