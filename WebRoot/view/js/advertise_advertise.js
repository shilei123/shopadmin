var AdvertiseLinkkindHtml = "";
var AdvertiseTypeHtml = "";
var AdvertiseIsuseHtml = "";
//加载layui的laydate组件
layui.use('laydate', function(){});
$("#starttime").click(function() {
	layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
});
$("#endtime").click(function() {
	layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
});	
$(function() {
	queryAdvertise();
	findAdvertiseType();
	findAdvertiseIsuse();
});

var queryAdvertise = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/advertise/advertise!query.action";
	pageData(url, "advertiseListTable", data); 
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
	var linkkind = $("#linkkind");
	var linkkinds = $("#linkkinds");
	linkkind.empty();
	linkkinds.empty();
		$.ajax({
			url :path_ + "/view/shop/advertise/advertise!AdvertiseType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				console.log(data);
				var html = "<option value='-1'>-请选择-</option>";
				$(data.isuseList).each(function(index) {
					var isuseinfoType = data.isuseList[index];
					html += "<option value='" + isuseinfoType.code + "'>" + isuseinfoType.name + "</option>";
				});
				AdvertiseIsuseHtml = html;
				isuse.append(html);
				
				var html = "<option value='-1'>-请选择-</option>";
				$(data.dictionaryList).each(function(index) {
					var advertiseType = data.dictionaryList[index];
					html += "<option value='" + advertiseType.code + "'>" + advertiseType.name + "</option>";
				});
				AdvertiseLinkkindHtml = html;
				linkkind.append(html);
				linkkinds.append(html);
			}
		});
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

var editInit=false;
$("#linkkinds").change(function(){
	showBtn();
	if(!editInit) {
		$("#imglink").val("");
	$("#imglinkLabel").val("");
	}
	editInit = false;
});

var showBtn = function() {
	var linkkind = $("#linkkinds").val();
	if(linkkind=="-1"){
		$("#imglinkLabel").attr("readonly",true);
		$("#cateBtn").css("display","none");
		$("#goodsBtn").css("display","none");
		$("#eventBtn").css("display","none");
	}
	else if(linkkind=="0") {
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
	showBtn();
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
	
	var imglinkLabel = $("#imglinkLabel").val();
	if(imglinkLabel.length == 0){
		$("#errorMsg").html("广告对象不能为空！");
		$("#imglinkLabel").focus();
		return false;
	}
	
	var advType = $("#advType").val();
	if(advType == "-1"){
		$("#errorMsg").html("广告位置不能为空！");
		$("#advType").focus();
		return false;
	}
	
	var isuse = $("#isuse").val();
	if(isuse == "-1"){
		$("#errorMsg").html("是否启用不能为空！");
		$("#isuse").focus();
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
	var objId = data.map.linkkind
	if(objId =="0"){
		$("#imglinkLabel").val(data.map.goodsName);
	}
	if(objId =="1"){
		$("#imglinkLabel").val(data.map.eventsName);
	}
	if(objId =="2"){
		$("#imglinkLabel").val(data.map.imglink);
	}
	if(objId =="3"){
		$("#imglinkLabel").val(data.map.cateName);
	}
	editInit = true;
	$("#errorMsg").html("&nbsp;");
};


//选择商品
var showQueryGoodsWin = function(){
	//打开商品列表窗口
	var url=path_ +"/view/shop/advertise/queryGoods.jsp";
	showLayerModal("选择商品",url,700,450);
}

//选择商品的回调函数
var selectGoods = function(obj) {
	$('#imglink').val(obj.goodsId);
	$('#imglinkLabel').val(obj.goodsName);
};

//选择活动
var showQueryEventsWin = function(){
	//打开活动列表窗口
	var url=path_ +"/view/shop/advertise/queryEvents.jsp";
	showLayerModal("选择商品",url,700,450);
}

//选择活动的回调函数
var selectEvents = function(obj) {
	$('#imglink').val(obj.eventsId);
	$('#imglinkLabel').val(obj.name);
};

//选择分类
var showQueryCateInfoWin = function(){
	//打开活动列表窗口
	var url=path_ +"/view/shop/advertise/queryCategory.jsp";
	showLayerModal("选择分类",url,700,450);
}

//选择分类的回调函数
var selectCategocy = function(obj) {
	$('#imglink').val(obj.CategocyId);
	$('#imglinkLabel').val(obj.name);
};

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