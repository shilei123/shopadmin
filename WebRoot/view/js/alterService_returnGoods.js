$(function() {
	queryBill();
	findBillType();
});

var queryBill = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/alterService/after!queryBill.action";
	pageData(url, "billListTable", data); 
};

var findBillType = function() {
	var kind = $("#kind");
	kind.empty();
		$.ajax({
			url :path_ + "/view/shop/alterService/after!queryBillType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				var html = "<option value='-1'>-请选择-</option>";
				$(data.kindList).each(function(index) {
					var billType = data.kindList[index];
					html += "<option value='" + billType.code + "'>" + billType.name + "</option>";
				});
				kind.append(html);
			}
		});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryBill();
});

var openWin = function(title) {
	$("#title").text(title);
	showModal("editBillModal",740,450);
};

var openPassWin = function(title){
	$("#titles").text(title);
	showModal("noPassModal",450,250);
}

$("#closePassBtn").click(function(){
	closeModal("noPassModal");
});

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showqueryBillWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看详情</a>";
	if(row.kind != "确认"){
		html += "&nbsp;&nbsp;<a href='javascript:void(0)'  onclick='pass(\""+ row["id"]+ "\")'><span class='am-icon-check-square-o'></i>通过</a>";
		html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='noPass(\""+ row["id"]+ "\")'><span class='am-icon-ban'></i>不通过</a>";
	}
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteBill(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//通过
var pass = function(id){
	openPassWin("审核通过");
	$("#billId").val(id);
	$('#saveNoPassBtn').attr('style','display:none');
	$('#savePassBtn').attr('style','display:inline');
	$("#result").val("");
	$("#errorMsg").html("&nbsp;");
	
}

//不通过
var noPass = function(id){
	openPassWin("审核不通过");
	$("#billId").val(id);
	$('#savePassBtn').attr('style','display:none');
	$('#saveNoPassBtn').attr('style','display:inline');
	$("#result").val("");
	$("#errorMsg").html("&nbsp;");
}

//通过保存
$("#savePassBtn").click(function(){
	//表单验证
	if(!checkSumbit()) {
		return;
	}
	var result = $("#result").val();
	var id = $("#billId").val();
	data = {"bill.result":result,"bill.id":id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/alterService/after!passReturnGoods.action",
		data : data,
		dataType : "json",
		success : function(json) {
			closeModal("noPassModal");
			showAlert("操作成功");
			queryBill();
			
		},
		error : function(e) {
			showAlert("操作失败");
		}
	});
});


//不通过保存
$("#saveNoPassBtn").click(function(){
	//表单验证
	if(!checkSumbit()) {
		return;
	}
	var result = $("#result").val();
	var id = $("#billId").val();
	data = {"bill.result":result,"bill.id":id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/alterService/after!noPassReturnGoods.action",
		data : data,
		dataType : "json",
		success : function(json) {
			closeModal("noPassModal");
			showAlert("操作成功");
			queryBill();
		},
		error : function(e) {
			showAlert("操作失败");
		}
	});
});
	
//表单验证
var checkSumbit = function() {
	var result = $("#result").val();
	if(result == null || result=="") {
		$("#errorMsg").html("处理结果不能为空！");
		$("#result").focus();
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};


//编辑
var showqueryBillWin = function(id) {
	$('#billParamsFrame').attr('src', path_ + '/view/shop/alterService/returnGoodsTetail.jsp?billId='+id);
	openWin();
};

//删除
var deleteBill = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"bill.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/alterService/after!deleteBill.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryBill();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};

