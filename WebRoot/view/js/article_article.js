$(function() {
	queryArticle();
	findArticleType();
});

var queryArticle = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/article/article!query.action";
	pageData(url, "articleListTable", data); 
};

var findArticleType = function() {
	var articleType = $("#articleType");
	articleType.empty();
		$.ajax({
			url :path_ + "/view/shop/article/article!queryArticleType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				var html = "<option value='-1'>-请选择-</option>";
				$(data.articleTypeList).each(function(index) {
					var artType = data.articleTypeList[index];
					html += "<option value='" + artType.code + "'>" + artType.name + "</option>";
				});
				articleType.append(html);
			}
		});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryArticle();
});

//弹出新增窗口
$("#addBtn").click(function() {
	window.location.href=path_ + "/view/shop/article/addArticle.jsp";
});

var formatterArticleTitle = function(value, row) {
	return "<a href='javascript:void(0);' onclick='showEditWin(\""+ row["id"]+ "\")'>"+value+"</a>";
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>编辑</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//编辑
var showEditWin = function(id) {
	window.location.href=path_ + "/view/shop/article/addArticle.jsp?articleId="+id;
};


//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"article.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/article/article!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryArticle();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};