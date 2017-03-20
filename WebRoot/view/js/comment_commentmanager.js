$(function() {
	query();
});

var openBankInfoWin = function(title) {
	$("#title").text(title);
	showModal("doc-modal-2",500,400);
};

$("#closeBtn").click(function() {
	closeModal("doc-modal-2");
});

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/comment/comment!queryCommentList.action";
	pageData(url, "contentListTable", data);
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditBankWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteBank(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//表单验证
var checkBankSumbit = function() {
	var bankName = $("#bankName").val();
	if(bankName.length==0) {
		$("#errorMsg").html("银行名称不能为空！");
		$("#bankName").focus();
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};

//保存
$("#saveBtn").click(function() {
	if(!checkBankSumbit()) {
		return;
	}

	var bankid = $("#bankId").val();
	var bankName = $("#bankName").val();
	var url = $("#url").val();
	var tel = $("#tel").val();
	var bankDesc = $("#bankDesc").val();
	var data = { "bankInfo.id" : bankid, "bankInfo.bankName" : bankName, "bankInfo.url" : url, "bankInfo.tel" : tel, "bankInfo.bankDesc" : bankDesc };
	$.ajax({
		type : "POST",
		url : path_ + "/view/bank/bankQuery!saveAgency.action",
		data : data,
		dataType : "json",
		success : function(json) {
			query();
			closeModal("doc-modal-2");
			showAlert("操作成功");
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

var clearBankInfo = function() {
	$("#bankName").val('');
	$("#tel").val('');
	$("#url").val('');
	$("#bankDesc").val('');
	$("#bankId").val('');
	$("#errorMsg").html('&nbsp;');
};

var setBankInfo = function(data) {
	$("#bankName").val(data.bankInfo.bankName);
	$("#tel").val(data.bankInfo.tel);
	$("#url").val(data.bankInfo.url);
	$("#bankDesc").val(data.bankInfo.bankDesc);
	$("#bankId").val(data.bankInfo.id);
	$("#errorMsg").html("&nbsp;");
};

var showEditBankWin = function(id) {
	clearBankInfo();
	var data = {"bankInfo.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/bank/bankQuery!queryBankInfo.action",
		data : data,
		dataType : "json",
		success : function(data) {
			setBankInfo(data);
			openBankInfoWin("查看银行");
		}
	});
};

var deleteBank = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"bankInfo.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/bank/bankQuery!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				query();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};

