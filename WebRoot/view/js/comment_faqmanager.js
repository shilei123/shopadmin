$(function() {
	initFaqType();
	query();
});

var initFaqType = function() {
	var data = {"dict.type":"FAQ_CATEGORY"};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/admin/dict!queryDictByType.action",
		data : data,
		dataType : "json",
		success : function(data) {
			/*var html = "<option value=''>-请选择-</option>";
			var dicts = data.dicts;
			console.log(dicts);
			for(x in dicts){
				html += "<option value='" + dicts[x].code + "'>" + dicts[x].name + "</option>"
			}
			$("#commentType").html(html);*/
		},
		error : function(e) {
		}
	});
}

var openFaqInfoWin = function(title) {
	$("#title").text(title);
	showModal("doc-modal-2",500,350);
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
	var url = path_ + "/view/comment/comment!queryCommentList.action";
	pageData(url, "contentListTable", data);
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='queryFaqInfoWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看详情</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='delFaq(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

var setFaqInfo = function(data) {
	/*var type = "";
	if(data.comment.type=="1"){
		type="评论";
	}else if(data.comment.type=="2"){
		type="追评";
	}
	var html = "<option>" + type + "</option>";
	$("#commentType2").html(html);
	$("#score2").val(data.comment.score);
	$("#commentPeople2").val(data.comment.commentPeople);
	$("#content2").val(data.comment.content);*/
};

var queryFaqInfoWin = function(id) {
	/*var data = {"comment.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/comment/comment!queryCommentById.action",
		data : data,
		dataType : "json",
		success : function(data) {
			setFaqInfo(data);
			openFaqInfoWin("查看问题");
		}
	});*/
};

var delFaq = function(id) {
	/*showConfirm("确认删除？", function() {
		var data = {"comment.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/comment/comment!delComment.action",
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
	});*/
};

