/*$(function() {
	test();
});*/

/*function test() {
	var $form = $('#ue-form');
	$form.validator({submit: function() {
	  var formValidity = this.isFormValid();

	  $.when(formValidity).then(function() {
	        // done, submit form
		  console.log("submit");
	   }, function() {
	        // fail
		   console.log("fail");
	   });
	  return false;
	}});
	$form.validator({
		submit: function() {
			var formValidity = this.isFormValid();
			console.log(formValidity);
			if(formValidity) {
				query();
			}
			return false;
		}
	});
}*/

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
	var url = path_ + "/view/bank/bankQuery!query.action";
	pageData(url, "bankListTable", data);
};

var formatterBankName = function(value, row) {
	return "<a href='javascript:void(0);' onclick='showEditBankWin(\""+ row["id"]+ "\")'>"+value+"</a>";
};

var formatterUrl = function(value, row) {
	return "<a href='"+value+"' target='_blank'>"+value+"</a>";
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditBankWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditBankWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>修改</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteBank(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger'><span class='am-icon-sign-out'></i>清退</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger'><span class='am-icon-user-times'></span>冻结</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='pay(\""+ row["id"]+ "\")'><span class='am-icon-check-square-o'></span>支付</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='nopay(\""+ row["id"]+ "\")'><span class='am-icon-ban'></i>拒绝支付</a>";
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
	//表单验证
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

//弹出新增窗口
$("#openBankBtn").click(function() {
	clearBankInfo();
	openBankInfoWin("新增银行");
});

//修改
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
			openBankInfoWin("编辑银行");
		}
	});
};

//删除
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

$("#closeBtn1").click(function() {
	closeModal("doc-modal-1");
});

var pay = function(id) {
	showConfirm("确认支付？", function() {
		showAlert("操作成功");
	});
};

var nopay = function(id) {
	$("#withdrawAppId").val(id);
	$("#acconutNote").val('');
	$("#errorMsg").html('&nbsp');
	showModal("doc-modal-1", 400, 300);
};