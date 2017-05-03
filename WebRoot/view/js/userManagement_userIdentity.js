$(function() {
	queryUserIdentity();
	identityStatus();
});

var queryUserIdentity = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/userManagement/user!queryUserIdentity.action";
	pageData(url, "userIdentityListTable", data);
};

var identityStatus = function() {
	var identityStatus = $("#identityStatus");
	identityStatus.empty();
	$.ajax({
		url :path_ + "/view/shop/userManagement/user!queryType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			//身份证状态
			var html = "<option value='-1'>-请选择-</option>";
			$(data.identityStatus).each(function(index) {
				var identityStatusType = data.identityStatus[index];
				html += "<option value='" + identityStatusType.code + "'>" + identityStatusType.name + "</option>";
			});
			identityStatus.append(html);
		}
	});
};


//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryUserIdentity();
});

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-success' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-check-square-o'></span>通过</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-warning' onclick='showFailWin(\""+ row["id"]+ "\")'><span class='am-icon-minus-square-o'></i>不通过</a>";
	html += "</div>";
	return html;
};

//认证成功
var showEditWin = function(id) {
	showConfirm("确认认证通过？", function() {
		var data = {"identity.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/userManagement/user!authenticationSuccess.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showMsg("操作成功");
				queryUserIdentity();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};

//表单验证
var checkSumbit = function() {
	var failureReason=$("#failureReason").val();
	if(failureReason.length == 0){
		$("#errorMsg").html("不通过,理由不能为空！");
		$("#failureReason").focus();
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};

//保存认证失败结果
$("#saveBtn").click(function() {
	//表单验证
	if(!checkSumbit()) {
		return;
	}
	
	var data = formGet("edit_user_identity_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/userManagement/user!saveFailurereason.action",
		data : data,
		dataType : "json",
		success : function(json) {
			closeModal("editUserIdentityModal");
			showMsg("操作成功");
			queryUserIdentity();
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

var openWin = function() {
	showModal("editUserIdentityModal",380,260);
	
};

$("#closeBtn").click(function() {
	closeModal("editUserIdentityModal");
});

var clearForm = function(){
	$("#failureReason").val('');
	$("#errorMsg").html('&nbsp;');
};

var setUserIdentityForm = function(data){
	$("#identityId").val(data.identity.id);
	$("#failureReason").val(data.identity.failureReason);
	$("#errorMsg").html('&nbsp;');
}

//认证失败
var showFailWin = function(id){
	clearForm();
	var data = {"identity.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/userManagement/user!findIdentity.action",
		data : data,
		dataType : "json",
		success : function(data) {
			openWin();
			setUserIdentityForm(data);
		}
	});
}