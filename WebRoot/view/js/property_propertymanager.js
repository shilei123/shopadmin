$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/property/property!query.action";
	pageData(url, "propertyListTable", data);
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	/*html += "<a href='javascript:void(0)' onclick='showEditBankWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看</a>";*/
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditPropWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>修改</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showConfigModel(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>属性与属性值配置</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteProp(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

//弹出新增窗口
$("#addPropertyBtn").click(function() {
	clearPropertyInfo();
	openPropertyWin("新增属性");
});

var clearPropertyInfo = function() {
	$("#propName").val('');
	$("#propCode").val('');
	$("#propOrder").val('');
	$("#propId").val('');
	$("#errorMsg").html('&nbsp;');
};

var openPropertyWin = function(title) {
	$("#title").text(title);
	showModal("doc-modal-2",500,300);
};

$("#closeBtn").click(function() {
	closeModal("doc-modal-2");
});

//保存
$("#saveBtn").click(function() {
	if(!checkPropertySumbit()) {
		return;
	}
	var propId = $("#propId").val();
	var propFlag = $("#propFlag").val();
	var propName = $("#propName").val();
	var propCode = $("#propCode").val();
	var propOrder = $("#propOrder").val();
	var url = "";
	if(propId!=null && propId!=undefined && propId!=""){
		url = path_ + "/view/property/property!updateProperty.action"
	}else{
		url = path_ + "/view/property/property!addProperty.action"
	}
	var data = { "property.id" : propId, "property.flag" : propFlag, "property.propName" : propName, "property.propCode" : propCode, "property.propOrder" : propOrder };
	$.ajax({
		type : "POST",
		url : url,
		data : data,
		dataType : "json",
		success : function(json) {
			query();
			closeModal("doc-modal-2");
			showAlert("操作成功");
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

//表单验证
var checkPropertySumbit = function() {
	var propName = $("#propName").val();
	if(propName.length==0) {
		$("#errorMsg").html("属性名称不能为空！");
		$("#propName").focus();
		return false;
	}
	var propOrder = $("#propOrder").val();
	if(propOrder.length==0) {
		$("#errorMsg").html("属性排序不能为空！");
		$("#propOrder").focus();
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};

//修改
var showEditPropWin = function(id) {
	clearPropertyInfo();
	var data = {"property.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/property/property!queryPropertyById.action",
		data : data,
		dataType : "json",
		success : function(data) {
			console.log(data);
			setPropertyInfo(data);
			openPropertyWin("编辑属性");
		}
	});
};

var setPropertyInfo = function(data) {
	$("#propName").val(data.property.propName);
	$("#propCode").val(data.property.propCode);
	$("#propOrder").val(data.property.propOrder);
	$("#propId").val(data.property.id);
	$("#propFlag").val(data.property.flag);
	$("#errorMsg").html("&nbsp;");
};

//删除
var deleteProp = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"property.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/property/property!delete.action",
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
	});
};

//属性属性值配置
var showConfigModel = function(id) {
	alert("待补充功能" + id);
	/*var data = {"property.id" : id};
	$('#categoryParamsFrame').attr('src', path_ + '/view/shop/category/category_property.jsp.jsp?categoryId='+obj.categoryId);
	showModal("doc-modal-1", 600, 450);*/
}



