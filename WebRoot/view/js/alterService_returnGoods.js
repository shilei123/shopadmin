$(function() {
	queryBill();
	findBillType();
	findBillStatus();
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

var findBillStatus = function() {
	var status = $("#status");
	status.empty();
		$.ajax({
			url :path_ + "/view/shop/alterService/after!queryBillType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				var html = "<option value='-1'>-请选择-</option>";
				$(data.statusList).each(function(index) {
					var statusType = data.statusList[index];
					html += "<option value='" + statusType.code + "'>" + statusType.name + "</option>";
				});
				status.append(html);
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
	showModal("noPassModal",450,300);
}

$("#closePassBtn").click(function(){
	closeModal("noPassModal");
});

var openOrderCode = function(){
	showModal("orderCodeModal",500,280);
	$("#goodsName").text("");
	$("#goodsPrice").text("");
	$("#numbs").text("");
	$("#goodsNo").text("");
}

$("#closeCodeBtn").click(function(){
	closeModal("orderCodeModal");
})

var openUserName = function(){
	showModal("userNameModal",500,280);
	$("#userName").text("");
	$("#phone").text("");
	$("#mail").text("");
	$("#sex").text("");
}

$("#closeUserBtn").click(function(){
	showModal("userNameModal");
});

var formatterOrderCode = function(value,row){
	return "<a href='javascript:void(0);' onclick='showOrderCodeWin(\""+ row["id"]+ "\")'>"+value+"</a>";
	
}

var formatterUserName = function(value,row){
	return "<a href='javascript:void(0);' onclick='showUserNameWin(\""+ row["createUserId"]+ "\")'>"+value+"</a>";
}

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showqueryBillWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看详情</a>";
	if(row.billStatus == "待审核"){
		html += "&nbsp;&nbsp;<a href='javascript:void(0)'  onclick='pass(\""+ row["id"]+ "\")'><span class='am-icon-check-square-o'></i>通过</a>";
		html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='noPass(\""+ row["id"]+ "\")'><span class='am-icon-ban'></i>不通过</a>";
	}
	html += "</div>";
	return html;
};

var showOrderCodeWin = function(id){
	openOrderCode();
	$.ajax({
		type:'POST',
		url : path_ + "/view/shop/alterService/after!queryGoodsDetail.action",
		data:{"bill.id":id},
		dataType:"json",
		success : function(data){
			if(data.billList[0].goodsName != null){
				$("#goodsName").text(data.billList[0].goodsName);
			}else{
				$("#goodsName").text("");
			}
			if(data.billList[0].donePrice != null){
				$("#goodsPrice").text("￥"+data.billList[0].donePrice);
			}else{
				$("#goodsPrice").text("");
			}
			if(data.billList[0].numbs != null){
				$("#numbs").text(data.billList[0].numbs);
			}else{
				$("#numbs").text("");
			}
			if(data.billList[0].goodsNo != null){
				$("#goodsNo").text(data.billList[0].goodsNo);
			}else{
				$("#goodsNo").text("");
			}
		}
	});
}

var showUserNameWin = function(userId){
	openUserName();
	$.ajax({
		type:'POST',
		url : path_ + "/view/shop/alterService/after!queryUserBase.action",
		data:{"userBase.userId":userId},
		dataType:"json",
		success : function(data){
			if(data.userBase.userName != null){
				$("#userName").text(data.userBase.userName);
			}else{
				$("#userName").text("");
			}
			if(data.userBase.phone != null){
				$("#phone").text(data.userBase.phone);
			}else{
				$("#phone").text("");
			}
			if(data.userBase.mail != null){
				$("#mail").text(data.userBase.mail);
			}else{
				$("#mail").text("");
			}
			var sex = data.userBase.sex;
			if(sex != null && sex == "0"){
				$("#sex").text("男");
			}else if(sex != null && sex == "1"){
				$("#sex").text("女");
			}else{
				$("#sex").text("");
			}
		}
	});
}

//通过
var pass = function(id){
	openPassWin("审核通过");
	$("#billId").val(id);
	$('#saveNoPassBtn').attr('style','display:none');
	$('#savePassBtn').attr('style','display:inline');
	$("#result").val("");
	$("#sales").show();
	$("#damage").attr("checked","checked");
	$("#errorMsg").html("&nbsp;");
}

//不通过
var noPass = function(id){
	$("#title").text("审核不通过");
	showModal("noPassModal",450,260);
	$("#billId").val(id);
	$('#savePassBtn').attr('style','display:none');
	$('#saveNoPassBtn').attr('style','display:inline');
	$("#result").val("");
	$("#sales").hide();
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
	var relus = $("input:radio[name='sales_inventory']:checked").val();
	data = {"bill.result":result,"bill.id":id,"relus":relus};
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


//查看详情
var showqueryBillWin = function(id) {
	$('#billParamsFrame').attr('src', path_ + '/view/shop/alterService/returnGoodsTetail.jsp?billId='+id);
	openWin();
};

