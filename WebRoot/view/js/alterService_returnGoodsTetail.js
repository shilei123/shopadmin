$(function() {
	var id = $("#billId").val();
	if(id != ""){
		queryBill(id);
	}else{
		clearForm();
	}
});

var queryBill = function(id) {
	clearForm();
		$.ajax({
			url :path_ + "/view/shop/alterService/after!findBillTetail.action",
			type : 'POST',
			data : {"bill.id" : id},
			dataType: "json",
			success : function(data) {
				setBillForm(data);
			}
		});
};

var clearForm = function(){
	$("#userName").val("");
	$("#phone").val("");
	$("#kind").val("");
	$("#status").val("");
	$("#name").val("");
	$("#startTime").val("");
	$("#code").val("");
	$("#orderCode").val("");
	$("#reason").val("");
	$("#content").val("");
	$("#result").val("");
}

var setBillForm = function(data){
	if(data.billList[0].userName != null){
		$("#userName").text(data.billList[0].userName);
	}else{
		$("#userName").text("");
	}
	if(data.billList[0].kind != null){
		$("#kind").text(data.billList[0].kind);
	}else{
		$("#kind").text("");
	}
	if(data.billList[0].stsus != null){
		$("#status").text(data.billList[0].stsus);
	}else{
		$("#status").text("");
	}
	if(data.billList[0].name != null){
		$("#name").text(data.billList[0].name);
	}else{
		$("#name").text("");
	}
	if(data.billList[0].createTime != null){
		$("#startTime").text(data.billList[0].createTime);
	}else{
		$("#startTime").text("");
	}
	if(data.billList[0].code != null){
		$("#code").text(data.billList[0].code);
	}else{
		$("#code").text("");
	}
	if(data.billList[0].orderCode != null){
		$("#orderCode").text(data.billList[0].orderCode);
	}else{
		$("#orderCode").text("");
	}
	if(data.billList[0].phone != null){
		$("#phone").text(data.billList[0].phone);
	}else{
		$("#phone").text("");
	}
	$("#reason").val(data.billList[0].reason);
	$("#content").val(data.billList[0].content);
	$("#result").val(data.billList[0].result);
}

//关闭窗口
var closeCurrentWin = function() {
	window.parent.closeModal("editBillModal");
};

$("#closeBtn").click(closeCurrentWin);
