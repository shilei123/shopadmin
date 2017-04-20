$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/propValue/propval!query.action";
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
	var data = null;
	if(propValueId!=null && propValueId!=undefined && propValueId!=""){
		url = path_ + "/view/shop/propValue/propval!updatePropValue.action"
		data = { "propValue.id" : propValueId, "propValue.flag" : propValueFlag, 
				"propValue.valName" : propValueName, "propValue.valCode" : propValueCode, "propValue.order" : propValueOrder };
	}else{
		url = path_ + "/view/shop/propValue/propval!addPropValue.action"
		data = {"propValue.valName" : propValueName, "propValue.valCode" : propValueCode, "propValue.order" : propValueOrder };
	}
	$.ajax({
		type : "POST",
		url : url,
		data : data,
		dataType : "json",
		success : function(json) {
			if(json.msg!="" && json.msg!=null){
				$('#errorMsg').text(json.msg);
				$('#propValueName').focus();
			}else {
				query();
				$('#errorMsg').text("");
				closeModal("doc-modal-2");
				showAlert("操作成功");
			}
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

//表单验证
var checkPropValueSumbit = function() {
	var propvalName = $("#propValueName").val();
	if(propvalName.length==0) {
		$("#errorMsg").html("属性值名称不能为空！");
		$("#propValueName").focus();
		return false;
	}
	var propvalOrder = $("#propValueOrder").val();
	if(propvalOrder.length==0) {
		$("#errorMsg").html("属性值排序不能为空！");
		$("#propValueOrder").focus();
		return false;
	}
	if(!isInteger(propvalOrder)){
		$("#errorMsg").html("属性值排序必须为整数！");
		$("#propValueOrder").focus();
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};

var isInteger = function(obj){
	var re = /^[1-9]*[1-9][0-9]*$/;  
    return re.test(obj);
}

//修改
var showEditPropWin = function(id) {
	clearPropValueInfo();
	var data = {"propValue.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/propValue/propval!queryPropValueById.action",
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
			url : path_ + "/view/shop/propValue/propval!delete.action",
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
