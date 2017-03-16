$(function() {
	queryUser();
	userStatus();
	tixianStatus();
	identityStatus();
});

var queryUser = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/userManagement/user!queryUser.action";
	pageData(url, "userSecutityListTable", data);
};

//账户状态
var userStatus = function() {
	var userStatus = $("#userStatus");
	userStatus.empty();
	var html = "<option value='-1'>-请选择-</option>";
		html += "<option value='0'>注销</option>";
		html += "<option value='1'>正常</option>";
		html += "<option value='2'>冻结</option>";
		userStatus.append(html);
};

//提现状态
var tixianStatus = function() {
	var tixianStatus = $("#tixianStatus");
	tixianStatus.empty();
	var html = "<option value='-1'>-请选择-</option>";
	html += "<option value='0'>无</option>";
	html += "<option value='1'>申请中</option>";
		tixianStatus.append(html);
};

//身份证状态
var identityStatus = function() {
	var identityStatus = $("#identityStatus");
	identityStatus.empty();
	var html = "<option value='-1'>-请选择-</option>";
		html += "<option value='0'>未认证</option>";
		html += "<option value='1'>申请中</option>";
		html += "<option value='2'>已认证</option>";
		identityStatus.append(html);
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