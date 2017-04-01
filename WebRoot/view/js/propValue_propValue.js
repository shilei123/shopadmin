$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/admin/propValue/propValue!query.action";
	pageData(url, "propValueListTable", data);
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	/*html += "<a href='javascript:void(0)' onclick='showEditBankWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>查看</a>";*/
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditPropWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>修改</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteProp(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

//弹出新增窗口
$("#addPropValueBtn").click(function() {
	clearPropValueInfo();
	openPropValueWin("新增属性值");
});

var clearPropValueInfo = function() {
	$("#propValueName").val('');
	$("#propValueCode").val('');
	$("#propValueOrder").val('');
	$("#propValueId").val('');
	$("#errorMsg").html('&nbsp;');
};

var openPropValueWin = function(title) {
	$("#title").text(title);
	showModal("doc-modal-2",500,300);
};

$("#closeBtn").click(function() {
	closeModal("doc-modal-2");
});

//保存
$("#saveBtn").click(function() {
	if(!checkPropValueSumbit()) {
		return;
	}
	var propValueId = $("#propValueId").val();
	var propValueFlag = $("#propValueFlag").val();
	var propValueName = $("#propValueName").val();
	var propValueCode = $("#propValueCode").val();
	var propValueOrder = $("#propValueOrder").val();
	var url = "";
	if(propValueId!=null /*&& propValueId!=undefine*/ && propValueId!=""){
		url = path_ + "/view/shop/admin/propValue/propValue!updatePropValue.action"
	}else{
		url = path_ + "/view/shop/admin/propValue/propValue!addPropValue.action"
	}
	var data = { "propValue.id" : propValueId, "propValue.flag" : propValueFlag, 
			"propValue.valName" : propValueName, "propValue.valCode" : propValueCode, "propValue.valOrder" : propValueOrder };
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
var checkPropValueSumbit = function() {
	var propName = $("#propValueName").val();
	if(propName.length==0) {
		$("#errorMsg").html("属性值名称不能为空！");
		$("#propValueName").focus();
		return false;
	}
	var propOrder = $("#propValueOrder").val();
	if(propOrder.length==0) {
		$("#errorMsg").html("属性值排序不能为空！");
		$("#propValueOrder").focus();
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};

//修改
var showEditPropWin = function(id) {
	clearPropValueInfo();
	var data = {"propValue.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/admin/propValue/propValue!queryPropValueById.action",
		data : data,
		dataType : "json",
		success : function(data) {
			console.log(data);
			setPropValueInfo(data);
			openPropValueWin("编辑属性值");
		}
	});
};

var setPropValueInfo = function(data) {
	$("#propValueName").val(data.propValue.valName);
	$("#propValueCode").val(data.propValue.valCode);
	$("#propValueOrder").val(data.propValue.order);
	$("#propValueId").val(data.propValue.id);
	$("#propValueFlag").val(data.propValue.flag);
	$("#errorMsg").html("&nbsp;");
};

//删除
var deleteProp = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"propValue.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/admin/propValue/propValue!delete.action",
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
