$(function() {
	initFaqType();
	query();
});

var initFaqType = function() {
	var data = {"type1":"FAQ_TYPE","type2":"FAQ_CATEGORY","type3":"HOT_QUESTION"};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/comment/faq!queryDictsByType.action",
		data : data,
		dataType : "json",
		success : function(data) {
			var html = "<option value=''>-请选择-</option>";
			var dict1 = data.dict1;
			for(x in dict1){
				html += "<option value='" + dict1[x].code + "'>" + dict1[x].name + "</option>"
			}
			$("#faqType").html(html);
			
			html = "<option value=''>-请选择-</option>";
			var dict2 = data.dict2;
			for(x in dict2){
				html += "<option value='" + dict2[x].code + "'>" + dict2[x].name + "</option>"
			}
			$("#category").html(html);
			
			html = "<option value=''>-请选择-</option>";
			var dict3 = data.dict3;
			for(x in dict3){
				html += "<option value='" + dict3[x].code + "'>" + dict3[x].name + "</option>"
			}
			$("#hotQuestion").html(html);
		},
		error : function(e) {
		}
	});
}

var openFaqInfoWin = function(title) {
	$("#title").text(title);
	showModal("doc-modal-2",600,400);
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
	var url = path_ + "/view/shop/comment/faq!queryFaqList.action";
	pageData(url, "contentListTable", data);
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='queryFaqInfoWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看详情</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='delFaq(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

var queryFaqInfoWin = function(id) {
	var data = {"faq.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/comment/faq!queryFaqById.action",
		data : data,
		dataType : "json",
		success : function(data) {
			setFaqInfo(data.faq);
			openFaqInfoWin("查看问题");
		}
	});
};

var setFaqInfo = function(faq) {
	console.log(faq);
	$("#faqType2").html("<option value='1'>" + faq.faqType + "</option>");
	$("#category2").html("<option value='1'>" + faq.category + "</option>");
	$("#hotQuestion2").html("<option value='1'>" + faq.hotQuestion + "</option>");
	$("#order2").val(faq.order);
	$("#faqContent2").val(faq.faqContent);
};

var delFaq = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"faq.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/comment/faq!delFaq.action",
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

