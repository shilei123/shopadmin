var gradeTypeHtml = "";
var isuseTypeHtml = "";
$(function() {
	queryUserGrade();
	queryGradeType();
	queryIsuseType();
});

var queryUserGrade = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/userManagement/user!queryUserGrade.action";
	pageData(url, "userGradeListTable", data); 
};

var queryGradeType = function() {
	var gradeType = $("#gradeType");
	gradeType.empty();
	var gradeTypeModal = $("#gradeTypeModal");
	gradeTypeModal.empty();
	$.ajax({
		url :path_ + "/view/shop/userManagement/user!queryType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			var html = "<option value='-1'>-请选择-</option>";
			$(data.userGradeList).each(function(index) {
				var userGradeType = data.userGradeList[index];
				html += "<option value='" + userGradeType.code + "'>" + userGradeType.name + "</option>";
			});
			gradeTypeHtml = html;
			gradeType.append(html);
			gradeTypeModal.append(html);
		}
	});
};

var queryIsuseType = function() {
	var isuse = $("#isuse");
	isuse.empty();
	$.ajax({
		url :path_ + "/view/shop/userManagement/user!queryType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			var html = "<option value='-1'>-请选择-</option>";
			$(data.isuseList).each(function(index) {
				var isuseType = data.isuseList[index];
				html += "<option value='" + isuseType.code + "'>" + isuseType.name + "</option>";
			});
			isuseTypeHtml = html;
			isuse.append(html);
		}
	});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryUserGrade();
});

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>编辑</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//打开模态窗口
var openWin = function(title) {
	$("#title").text(title);
	showModal("editGradeModal",500,380);
};

//取消
$("#closeBtn").click(function() {
	closeModal("editGradeModal");
});


var clearForm = function(){
	$("#gradeId").val('');
	$("#needInt").val('');
	$("#remark").val('');
	$("#errorMsg").html('&nbsp;');
}


var setGradeForm = function(data){
	$("#gradeId").val(data.grade.id);
	$("#gradeTypeModal").val(data.grade.userGrade);
	$("#needInt").val(data.grade.needIntegral);
	$("#isuse").val(data.grade.isuse);
	$("#remark").val(data.grade.remark);
}

var setType = function(){
	var gradeTypeModal = $("#gradeTypeModal");
	gradeTypeModal.empty();
	gradeTypeModal.append(gradeTypeHtml);
	var isuse = $("#isuse");
	isuse.empty();
	isuse.append(isuseTypeHtml);
}

$("#addBtn").click(function(){
	clearForm();
	openWin("新增");
	setType();
});

//表单验证
var checkSumbit = function() {
	var needInt=$("#needInt").val();
	if(needInt.length == 0){
		$("#errorMsg").html("所需积分不能为空！");
		$("#needInt").focus();
		return false;
	}
	var gradeTypeModal = $("#gradeTypeModal").val();
	if(gradeTypeModal == "-1"){
		$("#errorMsg").html("会员等级不能为空！");
		$("#gradeTypeModal").focus();
		return false;
	}
	var isuse = $("#isuse").val();
	if(isuse == "-1"){
		$("#errorMsg").html("是否启用不能为空！");
		$("#isuse").focus();
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};

//保存
$("#saveBtn").click(function() {
	//表单验证
	if(!checkSumbit()) {
		return;
	}
	
	var data = formGet("edit_grade_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/userManagement/user!saveGrade.action",
		data : data,
		dataType : "json",
		success : function(json) {
			queryUserGrade();
			closeModal("editGradeModal");
			showAlert("操作成功");
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

//编辑
var showEditWin = function(id){
	var data = {"grade.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/userManagement/user!findGrade.action",
		data : data,
		dataType : "json",
		success : function(data) {
			openWin("编辑");
			setType();
			setGradeForm(data);
		}
	});
	
}

//删除
var deleteDict = function(id){
	showConfirm("确认删除？", function() {
		var data = {"grade.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/userManagement/user!deleteUserGrade.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryUserGrade();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};

