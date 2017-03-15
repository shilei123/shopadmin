$(function() {
	queryUserBcuser();
	queryUserSex();
});

var queryUserBcuser = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/userManagement/user!queryUserBcuser.action";
	pageData(url, "userBcuserListTable", data); 
};

var queryUserSex = function() {
	var userSex = $("#userSex");
	userSex.empty();
	var html = "<option value='-1'>-请选择-</option>";
		html += "<option value='0'>男</option>";
		html += "<option value='1'>女</option>";
		userSex.append(html);
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryUserBcuser();
});





