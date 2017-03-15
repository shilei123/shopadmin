var couponTypeHtml = "";

$(function() {
	queryCoupon();
	queryCouponType();
});

var queryCoupon = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/coupon/coupon!query.action";
	pageData(url, "couponListTable", data); 
};

var queryCouponType = function() {
	var couponType = $("#couponType");
	var couponTypeModal = $("#couponTypeModal");
	couponType.empty();
	couponTypeModal.empty();
	$.ajax({
		url :path_ + "/view/shop/coupon/coupon!couponType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			console.log(data);
			var html = "<option value='-1'>-请选择-</option>";
			$(data.dictionaryList).each(function(index) {
				var couponType = data.dictionaryList[index];
				html += "<option value='" + couponType.code + "'>" + couponType.name + "</option>";
			});
			couponTypeHtml = html;
			couponType.append(html);
			couponTypeModal.append(html);
		}
	});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryCoupon();
});

var openWin = function(title) {
	$("#title").text(title);
	showModal("editCouponModal",500,380);
	
};

$("#closeBtn").click(function() {
	closeModal("editCouponModal");
});

//弹出新增窗口
$("#addBtn").click(function() {
	clearForm();
	openWin("新增");
	//重新设置下拉框值
	//$("#couponTypeModal").trigger('changed.selected.amui');
	var couponTypeModal = $("#couponTypeModal");
	couponTypeModal.empty();
	couponTypeModal.append(couponTypeHtml);
});

//表单验证
var checkSumbit = function() {
	var couponName=$("#couponName").val();
	if(couponName.length == 0){
		$("#errorMsg").html("优惠券名称不能为空！");
		$("#couponName").focus();
		return false;
	}
	
	var couponTypeModal = $("#couponTypeModal").val();
	if(couponTypeModal == "-1"){
		$("#errorMsg").html("优惠券类型不能为空！");
		$("#couponTypeModal").focus();
		return false;
	}
	
	var rel =/^[0-9]*$/;
	if(!rel.test($("#couponExpiryDate").val())){
		$("#errorMsg").html("有效期天数必须为数字");
		$("#couponExpiryDate").focus();
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
	
	var data = formGet("edit_coupon_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/coupon/coupon!save.action",
		data : data,
		dataType : "json",
		success : function(json) {
			queryCoupon();
			closeModal("editCouponModal");
			showAlert("操作成功");
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

var clearForm = function() {
	$("#couponTypeModal").val('');
	$("#couponName").val('');
	$("#couponZsBalance").val('');
	$("#couponBlance").val('');
	$("#couponXfBalance").val('');
	$("#couponExpiryDate").val('');
	$("#couponRemark").val('');
	$("#errorMsg").html('&nbsp;');
};

var setCouponForm = function(data) {
	$("#couponId").val(data.coupon.id);
	$("#couponName").val(data.coupon.couponName);
	$("#couponTypeModal").val(data.coupon.couponType);
	$("#couponZsBalance").val(data.coupon.couponZsBalance);
	$("#couponBlance").val(data.coupon.couponBlance);
	$("#couponXfBalance").val(data.coupon.couponXfBalance);
	$("#couponExpiryDate").val(data.coupon.couponExpiryDate);
	$("#couponRemark").val(data.coupon.couponRemark);
	$("#errorMsg").html("&nbsp;");
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>修改</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//修改
var showEditWin = function(id) {
	clearForm();
	//$("#couponTypeModal").trigger('changed.selected.amui');
	var data = {"coupon.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/coupon/coupon!queryCoupon.action",
		data : data,
		dataType : "json",
		success : function(data) {
			openWin("编辑");
			var couponTypeModal = $("#couponTypeModal");
			couponTypeModal.empty();
			couponTypeModal.append(couponTypeHtml);
			setCouponForm(data);
		}
	});
};


//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"coupon.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/coupon/coupon!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryCoupon();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};

