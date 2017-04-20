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
	$("#mail").val("");
	$("#sex").val("");
	$("#goodsName").val("");
	$("#goodsPrice").val("");
	$("#numbs").val("");
	$("#goodsNo").val("");
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
	$("#userName").val(data.billList[0].userName);
	$("#phone").val(data.billList[0].phone);
	$("#mail").val(data.billList[0].mail);
	$("#sex").val(data.billList[0].sex);
	$("#goodsName").val(data.billList[0].goodsName);
	$("#goodsPrice").val(data.billList[0].donePrice);
	$("#numbs").val(data.billList[0].numbs);
	$("#goodsNo").val(data.billList[0].goodsNo);
	$("#kind").val(data.billList[0].kind);
	$("#status").val(data.billList[0].stsus);
	$("#name").val(data.billList[0].name);
	$("#startTime").val(data.billList[0].createTime);
	$("#code").val(data.billList[0].code);
	$("#orderCode").val(data.billList[0].orderCode);
	$("#reason").val(data.billList[0].reason);
	$("#content").val(data.billList[0].content);
	$("#result").val(data.billList[0].result);
}

//关闭窗口
var closeCurrentWin = function() {
	window.parent.closeModal("editBillModal");
};

$("#closeBtn").click(closeCurrentWin);
