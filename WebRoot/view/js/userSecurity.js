$(function() {
	queryUser();
	identityStatus();
});

var queryUser = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/userManagement/user!queryUser.action";
	pageData(url, "userSecutityListTable", data);
};

var identityStatus = function() {
	var identityStatus = $("#identityStatus");
	identityStatus.empty();
	var userStatus = $("#userStatus");
	userStatus.empty();
	$.ajax({
		url :path_ + "/view/shop/userManagement/user!queryType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			//账户状态
			var html = "<option value='-1'>-请选择-</option>";
			$(data.userStatusList).each(function(index) {
				var userStatusType = data.userStatusList[index];
				html += "<option value='" + userStatusType.code + "'>" + userStatusType.name + "</option>";
			});
			userStatus.append(html);
			
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
	queryUser();
});

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>重置密码</a>";
	html += "</div>";
	return html;
};

//重置登陆密码
var showEditWin = function(id) {
	showConfirm("确认重置密码？", function() {
		var data = {"user.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/userManagement/user!resetPassword.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryUser();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};