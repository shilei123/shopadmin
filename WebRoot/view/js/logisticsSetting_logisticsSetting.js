var expressTypeHtml="";
$(function() {
	queryLogistics();
	queryExpressType();
});

var queryLogistics = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/logisticsSetting/logisticsSetting!queryLogistics.action";
	pageData(url, "logisticsListTable", data);
};

var queryExpressType = function() {
	var type = $("#type");
	var type2 = $("#type2");
	type.empty();
	type2.empty();
	$.ajax({
		url :path_ + "/view/shop/logisticsSetting/logisticsSetting!expressType.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			console.log(data);
			var html = "<option value='-1'>-请选择-</option>";
			$(data.dictionaryList).each(function(index) {
				var expressType = data.dictionaryList[index];
				html += "<option value='" + expressType.code + "'>" + expressType.name + "</option>";
			});
			expressTypeHtml = html;
			type.append(html);
			type2.append(html);
		}
	});
};

//打开图片上传的窗口
$("#img1").click(function() {
		showImgUploadModal();
});

//选择图片的回调函数
var selectImg = function(obj) {
		if($("#img1"+obj.id).length==0) {
			var imgObj = $('#img1');
			imgObj.attr("src", obj.src);
			currentImgId = null;
			//给隐藏域赋值
			imgObj.parent().children("input:eq(0)").val(obj.src);
		}
};

$(".imgDiv").mouseover(function(){
	var src = $(this).children("img:eq(0)").attr("src");
	if(src!=undefined && src.length>0) {
		$(this).children("div:eq(0)").show();
	}
}).mouseout(function(){
	$(this).children("div:eq(0)").hide();
});

//删除
$(".am-icon-close").click(function(){
	$("#imgIdHidden").val('');
	$("#img1").attr("src","");
	$(this).hide();
});

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryLogistics();
});

var openWin = function(title) {
	$("#title").text(title);
	showModal("editeLogisticsModal",500,450);
};

$("#closeBtn").click(function() {
	closeModal("editeLogisticsModal");
});

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showOrHideDefault(\""+ row["id"]+ "\")'><span class='am-icon-check-square-o'></span>是否设置默认</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>编辑</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//弹出新增窗口
$("#addBtn").click(function() {
	clearForm();
	openWin("新增");
});

//表单验证
var checkSumbit = function() {
	var type = $("#type").val();
	if(type == "-1"){
		$("#errorMsg").html("快递类型不能为空！");
		$("#type").focus();
		return false;
	}
	
	var code = $("#code").val();
	if(code.length == 0){
		$("#errorMsg").html("代码不能为空!");
		$("#code").focus();
		return false;
	}
	
	var forShort=$("#forShort").val();
	if(forShort.length == 0){
		$("#errorMsg").html("名称不能为空！");
		$("#forShort").focus();
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
	
	var data = formGet("logistics_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/logisticsSetting/logisticsSetting!save.action",
		data : data,
		dataType : "json",
		success : function(json) {
			queryLogistics();
			closeModal("editeLogisticsModal");
			showAlert("操作成功");
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});


var clearForm = function() {
	$("#logisticsId").val('');
	$("#code").val('');
	$("#forShort").val('');
	$("#ename").val('');
	$("#fullName").val('');
	$("#imgIdHidden").val('');
	$("#img1").attr("src","");
	$("#errorMsg").html('&nbsp;');
};

var setExpressForm = function(data) {
	$("#logisticsId").val(data.logistics.id);
	$("#code").val(data.logistics.code);
	$("#forShort").val(data.logistics.forShort);
	$("#ename").val(data.logistics.ename);
	$("#fullName").val(data.logistics.fullName);
	$("#type").val(data.logistics.expressType);
	//回显图片
	$("#imgIdHidden").val(data.logistics.url);
	$("#img1").attr("src",data.logistics.url);
	$("#errorMsg").html("&nbsp;");
};

//编辑
var showEditWin = function(id) {
	clearForm();
	var data = {"logistics.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/logisticsSetting/logisticsSetting!editExpress.action",
		data : data,
		dataType : "json",
		success : function(data) {
			openWin("编辑");
			var type = $("#type");
			type.empty();
			type.append(expressTypeHtml);
			setExpressForm(data);
		}
	});
};

var showOrHideDefault = function(id){
	showConfirm("确认设为默认？", function() {
		var data = {"logistics.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/logisticsSetting/logisticsSetting!saveIsuse.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryLogistics();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
	
}

//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"logistics.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/logisticsSetting/logisticsSetting!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryLogistics();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};