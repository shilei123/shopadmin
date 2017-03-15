var AdvertiseLinkkindHtml = "";
var AdvertiseTypeHtml = "";
var AdvertiseIsuseHtml = "";

$(function() {
	queryAdvertise();
	queryAdvertiseType();
	findAdvertiseType();
	findAdvertiseIsuse();
});

var queryAdvertise = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/advertise/advertise!query.action";
	pageData(url, "advertiseListTable", data); 
};

var queryAdvertiseType = function() {
	var linkkind = $("#linkkind");
	var linkkinds = $("#linkkinds");
	linkkind.empty();
	linkkinds.empty();
	var html = "<option value='-1'>请选择</option>";
		html += "<option value='0'>商品</option>";
		html += "<option value='1'>活动</option>";
		html += "<option value='2'>其他URL</option>";
		html += "<option value='3'>类别</option>";
		AdvertiseLinkkindHtml = html;
		linkkind.append(html);
		linkkinds.append(html);
};

var findAdvertiseType = function() {
	var advType = $("#advType");
	advType.empty();
	var html = "<option value='-1'>请选择</option>";
		html +=	"<option value='0'>首页头部</option>"
		html +=	"<option value='1'>其它位置</option>"
		AdvertiseTypeHtml = html;
		advType.append(html);
};

var findAdvertiseIsuse = function() {
	var isuse = $("#isuse");
	isuse.empty();
	//var html = "<option value='-1'>请选择</option>";
	var	html =	"<option value='0'>启用</option>"
		html +=	"<option value='1'>不启用</option>"
		AdvertiseIsuseHtml = html;
		isuse.append(html);
};

var formatterType = function(value, row){
	return value=="0"?"首页头部":"其他位置";
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryAdvertise();
});

var openWin = function(title) {
	$("#title").text(title);
	showModal("editAdvertiseModal",720,530);
	
};

$("#closeBtn").click(function() {
	closeModal("editAdvertiseModal");
});

var changeLinkkind = function() {
	showBtn();
	$("#imglink").val("");
	$("#imglinkLabel").val("");
}


var showBtn = function() {
	var linkkind = $("#linkkinds").val();
	if(linkkind=="0") {
		$("#imglinkLabel").attr("readonly",true);
		$("#cateBtn").css("display","none");
		$("#goodsBtn").css("display","");
		$("#eventBtn").css("display","none");
	} else if(linkkind=="1") {
		$("#imglinkLabel").attr("readonly",true);
		$("#cateBtn").css("display","none");
		$("#goodsBtn").css("display","none");
		$("#eventBtn").css("display","");
	} else if(linkkind=="2") {
		$("#imglinkLabel").attr("readonly",false);
		$("#cateBtn").css("display","none");
		$("#goodsBtn").css("display","none");
		$("#eventBtn").css("display","none");
		$("#imglinkLabel").change(function(var_){
			var inf_=$("#imglinkLabel").val();
			$("#imglink").val(inf_);
		});
	} else if(linkkind=="3") {
		$("#imglinkLabel").attr("readonly",true);
		$("#cateBtn").css("display","");
		$("#goodsBtn").css("display","none");
		$("#eventBtn").css("display","none");
	}
}

var showType = function(){
	var linkkinds = $("#linkkinds");
	linkkinds.empty();
	linkkinds.append(AdvertiseLinkkindHtml);
	var advType = $("#advType");
	advType.empty();
	advType.append(AdvertiseTypeHtml);
	var isuse = $("#isuse");
	isuse.empty();
	isuse.append(AdvertiseIsuseHtml);
}

//弹出新增窗口
$("#addBtn").click(function() {
	clearForm();
	openWin("新增");
	showType();
});

//表单验证
var checkSumbit = function() {
	var name=$("#name").val();
	if(name.length == 0){
		$("#errorMsg").html("广告名称不能为空！");
		$("#name").focus();
		return false;
	}
	
	var linkkinds = $("#linkkinds").val();
	if(linkkinds == "-1"){
		$("#errorMsg").html("广告类型不能为空！");
		$("#linkkinds").focus();
		return false;
	}
	
	var advType = $("#advType").val();
	if(advType == "-1"){
		$("#errorMsg").html("广告位置不能为空！");
		$("#advType").focus();
		return false;
	}
	
	/*var isuse = $("#isuse").val();
	if(isuse == "-1"){
		$("#errorMsg").html("是否启用不能为空！");
		$("#isuse").focus();
		return false;
	}*/
	$("#errorMsg").html("&nbsp;");
	return true;
};

//保存
$("#saveBtn").click(function() {
	//表单验证
	if(!checkSumbit()) {
		return;
	}
	
	var data = formGet("edit_adv_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/advertise/advertise!save.action",
		data : data,
		dataType : "json",
		success : function(json) {
			queryAdvertise();
			closeModal("editAdvertiseModal");
			showAlert("操作成功");
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

var clearForm = function() {
	$("#id").val('');
	$("#name").val('');
	$("#imglinkLabel").val('');
	$("#imglink").val('');
	$("#ordernumb").val('');
	$("#starttime").val('');
	$("#endtime").val('');
	$("#kind").val('');
	$("#memo").val('');
	$("#errorMsg").html('&nbsp;');
};

var setAdvertiseForm = function(data) {
	$("#id").val(data.map.id);
	$("#name").val(data.map.name);
	$("#imglink").val(data.map.imglink);
	$("#ordernumb").val(data.map.ordernumb);
	$("#memo").val(data.map.memo);
	$("#starttime").val(data.map.startTime);
	$("#endtime").val(data.map.endTime);
	$("#linkkinds").val(data.map.linkkind);
	$("#advType").val(data.map.type);
	$("#isuse").val(data.map.isuse);
	$("#kind").val(data.map.kind);
	var objId = data.map.imglink
	if(objId =="2"){
		$("#imglinkLabel").val(data.map.imglink);
	}
	if(objId =="1"){
		$("#imglinkLabel").val(data.map.infoname);
	}
	$("#errorMsg").html("&nbsp;");
};

var showQueryGoodsWin = function(){
	
	
	
}

var showQueryEventsWin = function(){
	
	
}

var showQueryCateInfoWin = function(){
	
	
}

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>编辑</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

//编辑
var showEditWin = function(id) {
	clearForm();
	//$("#couponTypeModal").trigger('changed.selected.amui');
	var data = {"advertise.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/advertise/advertise!findAdvertise.action",
		data : data,
		dataType : "json",
		success : function(data) {
			console.log(data);
			openWin("编辑");
			showType();
			setAdvertiseForm(data);
		}
	});
};


//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"advertise.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/advertise/advertise!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryAdvertise();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};