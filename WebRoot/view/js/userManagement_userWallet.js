$(function() {
	queryUserWallet();
	queryPurseType();
});

var queryUserWallet = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/userManagement/user!queryUserWallet.action";
	pageData(url, "userWalletListTable", data); 
};

var queryPurseType = function() {
	var purseType = $("#purseType");
	purseType.empty();
	$.ajax({
		url :path_ + "/view/shop/userManagement/user!queryType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			var html = "<option value='-1'>-请选择-</option>";
			$(data.purseTypeList).each(function(index) {
				var userPurseType = data.purseTypeList[index];
				html += "<option value='" + userPurseType.code + "'>" + userPurseType.name + "</option>";
			});
			purseType.append(html);
		}
	});
};


var formatterMoney = function(value, row) {
	var html = row.userMoney + "(" + row.unit + ")";
    return html;
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryUserWallet();
});





