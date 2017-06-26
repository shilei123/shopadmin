$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/category/brand!queryBrandList.action";
	pageData(url, "brandListTable", data);
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showQueryBrandWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看详情</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditPropWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>修改</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='delBrand(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

var showQueryBrandWin = function(id){
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/category/brand!queryBrandById.action",
		data : {"brand.id":id},
		dataType : "json",
		success : function(json) {
			setBrandInfo(json);
			showModal("doc-modal-1",550,430);
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
}

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

//弹出新增窗口
$("#addBrandBtn").click(function() {
	clearBrandInfo();
	openBrandWin("新增品牌");
});

var clearBrandInfo = function() {
	$("#brandId").val('');
	$("#brandName").val('');
	$("#brandCode").val('');
	$("#brandLogo").val('');
	$("#brandDesc").val('');
	$("#errorMsg").html('&nbsp;');
};

var openBrandWin = function(title) {
	$("#title").text(title);
	showModal("doc-modal-1",550,430);
};

$("#closeBtn").click(function() {
	closeModal("doc-modal-1");
});

//保存
$("#saveBtn").click(function() {
	if(!checkBrandSumbit()) {
		return;
	}
	var brandId = $('#brandId').val();
	var brandName = $('#brandName').val();
	var brandCode = $('#brandCode').val();
	var brandLogo = $('#brandLogo').val();
	var brandDesc = $('#brandDesc').val();
	var url = "";
	var data = null;
	if(brandId!=null && brandId!=undefined && brandId!=""){
		url = path_ + "/view/shop/category/brand!updateBrand.action";
		data = {"brand.id" : brandId, "brand.brandName" : brandName, "brand.brandCode" : brandCode, "brand.brandLogo" : brandLogo, "brand.brandDesc" : brandDesc };
	}else{
		url = path_ + "/view/shop/category/brand!addBrand.action";
		data = {"brand.brandName" : brandName, "brand.brandCode" : brandCode, "brand.brandLogo" : brandLogo, "brand.brandDesc" : brandDesc };
	}
	$.ajax({
		type : "POST",
		url : url,
		data : data,
		dataType : "json",
		success : function(json) {
			if(json.msg!=""){
				$('#errorMsg').text(json.msg);
				$('#brandName').focus();
			}else{
				query();
				$('#errorMsg').text("");
				closeModal("doc-modal-1");
				showAlert("操作成功");
			}
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

//表单验证
var checkBrandSumbit = function() {
	var brandName = $("#brandName").val();
	if(brandName.length==0) {
		$("#errorMsg").html("品牌名称不能为空！");
		$("#brandName").focus();
		return false;
	}
	/*var brandCode = $("#brandCode").val();
	if(brandCode.length==0) {
		$("#errorMsg").html("品牌编码不能为空！");
		$("#brandCode").focus();
		return false;
	}*/
	$("#errorMsg").html("&nbsp;");
	return true;
};

//修改
var showEditPropWin = function(id) {
	clearBrandInfo();
	var data = {"brand.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/category/brand!queryBrandById.action",
		data : data,
		dataType : "json",
		success : function(data) {
			//console.log(data);
			setBrandInfo(data);
			openBrandWin("编辑品牌");
		}
	});
};

var setBrandInfo = function(data) {
	$("#brandName").val(data.brand.brandName);
	$("#brandCode").val(data.brand.brandCode);
	$("#brandLogo").val(data.brand.brandLogo);
	$("#brandDesc").val(data.brand.brandDesc);
	$("#brandId").val(data.brand.id);
	$("#errorMsg").html("&nbsp;");
};

//删除
var delBrand = function(id) {
	showConfirm("确认删除？", function() {
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/category/brand!delBrand.action",
			data : {"brand.id" : id},
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
