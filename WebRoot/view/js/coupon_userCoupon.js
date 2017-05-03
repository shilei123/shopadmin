var couponTypeHtml="";
$(function() {
	queryUserCoupon();
	queryUserCouponType();
});

var queryUserCoupon = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/coupon/userCoupon!queryUserCoupon.action";
	pageData(url, "userCouponListTable", data); 
};

var queryUserCouponType = function() {
	var status = $("#status");
	var sts = $("#sts");
	status.empty();
	sts.empty();
	$.ajax({
		url :path_ + "/view/shop/coupon/userCoupon!queryUserCouponType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			console.log(data);
			var html = "<option value='-1'>-请选择-</option>";
			$(data.dictionaryList).each(function(index) {
				var userCouponType = data.dictionaryList[index];
				html += "<option value='" + userCouponType.code + "'>" + userCouponType.name + "</option>";
			});
			couponTypeHtml = html;
			status.append(html);
			sts.append(html);
		}
	});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryUserCoupon();
});

var openWin = function(title) {
	$("#title").text(title);
	showModal("editUserCouponModal",350,200);
};

$("#closeBtn").click(function() {
	closeModal("editUserCouponModal");
});

//表单验证
var checkSumbit = function() {
	var sts = $("#sts").val();
	if(sts == "-1"){
		$("#errorMsg").html("优惠券状态不能为空！");
		$("#sts").focus();
		return false;
	}
	return true;
};


//保存
$("#saveBtn").click(function() {
	//表单验证
	if(!checkSumbit()) {
		return;
	}
	var data = formGet("edit_user_coupon_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/coupon/userCoupon!save.action",
		data : data,
		dataType : "json",
		success : function(json) {
			queryUserCoupon();
			closeModal("editUserCouponModal");
			showMsg("操作成功");
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

var setUserCouponForm = function(data) {
	$("#sts").val(data.userCoupon.couponStatus);
	$("#errorMsg").html("&nbsp;");
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>编辑</a>";
	html += "</div>";
	return html;
};

//修改
var showEditWin = function(id) {
	$("#userCouponId").val(id);
	var data = {"userCoupon.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/coupon/userCoupon!findUserCoupon.action",
		data : data,
		dataType : "json",
		success : function(data) {
			openWin("编辑");
			var sts = $("#sts");
			sts.empty();
			sts.append(couponTypeHtml);
			setUserCouponForm(data);
		}
	});
};
