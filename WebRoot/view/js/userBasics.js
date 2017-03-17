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
	$.ajax({
		url :path_ + "/view/shop/userManagement/user!queryType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			var html = "<option value='-1'>-请选择-</option>";
			$(data.userSexList).each(function(index) {
				var userSexType = data.userSexList[index];
				html += "<option value='" + userSexType.code + "'>" + userSexType.name + "</option>";
			});
			userSex.append(html);
		}
	});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryUserBcuser();
});





