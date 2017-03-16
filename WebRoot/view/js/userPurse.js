$(function() {
	queryUserPurse();
	queryUserPurseType();
});

var queryUserPurse = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/userManagement/user!queryUserPurse.action";
	pageData(url, "userPurseListTable", data); 
};


var queryUserPurseType = function() {
	$.ajax({
		url :path_ + "/view/shop/userManagement/user!queryType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			console.log(data);
			//交易状态
			var tradeState = $("#tradeState");
			tradeState.empty();
			var html = "<option value='-1'>-请选择-</option>";
			$(data.tradeStateList).each(function(index) {
				var tradeStates = data.tradeStateList[index];
				html += "<option value='" + tradeStates.code + "'>" + tradeStates.name + "</option>";
			});
			tradeState.append(html);
			
			//交易类型
			var tradeType = $("#tradeType");
			tradeType.empty();
			var html = "<option value='-1'>-请选择-</option>";
			$(data.tradeTypeList).each(function(index) {
				var tradeTypes = data.tradeTypeList[index];
				html += "<option value='" + tradeTypes.code + "'>" + tradeTypes.name + "</option>";
			});
			tradeType.append(html);
			
			//钱包类型
			var purseType = $("#purseType");
			purseType.empty();
			var html = "<option value='-1'>-请选择-</option>";
			$(data.purseTypeList).each(function(index) {
				var purseTypes = data.purseTypeList[index];
				html += "<option value='" + purseTypes.code + "'>" + purseTypes.name + "</option>";
			});
			purseType.append(html);
			
			//操作类型
			var optionType = $("#optionType");
			optionType.empty();
			var html = "<option value='-1'>-请选择-</option>";
			$(data.optionTypeList).each(function(index) {
				var optionTypes = data.optionTypeList[index];
				html += "<option value='" + optionTypes.code + "'>" + optionTypes.name + "</option>";
			});
			optionType.append(html);
		}
	});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryUserPurse();
});

