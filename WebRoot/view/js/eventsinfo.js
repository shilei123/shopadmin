var eventsinfoTypeHtml = "";

$(function() {
	queryEventsinfo();
	queryEventsinfoType();
});

var queryEventsinfo = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/eventsinfo/eventsinfo!query.action";
	pageData(url, "eventsinfoListTable", data); 
};

var queryEventsinfoType = function() {
	var isuse = $("#isuse");
	isuse.empty();
	var html = "<option value='-1'>请选择</option>";
		html += "<option value='0'>启用</option>";
		html += "<option value='1'>不启用</option>";
		eventsinfoTypeHtml = html;
		isuse.append(html);
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryEventsinfo();
});

var openWin = function(title) {
	$("#titles").text(title);
	showModal("editeEventsinfoModal",600,460);
};

$("#closeBtn").click(function() {
	closeModal("editeEventsinfoModal");
});

//弹出新增窗口
$("#addBtn").click(function() {
	clearForm();
	openWin("新增");
	//重新设置下拉框值
	//$("#couponTypeModal").trigger('changed.selected.amui');
	var isuse = $("#isuse");
	isuse.empty();
	isuse.append(eventsinfoTypeHtml);
});

//选择商品
$("#chooseGoodsBtn").click(function(){
	//var data = formGet("from_query");
	//var url = path_ + "/view/shop/eventsinfo/eventsinfo!query.action";
	//pageData(url, "goodsListTable", data,1,"rowCountBonus","pageBonus");
	//showModal("doc-modal-2",1000, 400);
	$('#doc-modal-2').modal("");
	//var url = path_ + "/view/shop/eventsinfo/eventsinfo!query.action";
	//window.open(url,"","height=500,width=280,status=yes,toolbar=no,menubar=no,location=no"); 
});

//关闭
$("#goodsCloseBtn").click(function() {
	closeModal("doc-modal-2");
});

var goodsFormatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showGoodsEditWin(\""+ row["id"]+ "\",\""+ row["name"]+ "\")'><span class='am-icon-check-square-o'></span>选择</a>";
	html += "</div>";
	return html;
};

//选择商品
var showGoodsEditWin = function(id,name){
	setImgLind(id,name);
	//closeModal("doc-modal-2");
};

//添加商品
var setImgLind = function(id,name){
	var idlist=$("#events_goods_list").val();
	 var namelist=$("#events_goods").html();
	 
	 var ishave=true;
	 if(idlist!=""){
	 	var arraygoods=idlist.split(",");
	 	for(var i = 0;i<arraygoods.length;i++){
			var nowgoods=arraygoods[i];	
			if(nowgoods == id){
				ishave=false;
			}
		}
	 }
	 if(ishave){
	 	idlist=id+","+idlist;
		namelist="<div class='"+id+"'><em>"+gname+"</em>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='removeChoose(\""+id+"\"); return false;' ><span class='am-icon-remove'></span></a><br></div>"+namelist;	
	 }
	 $("#events_goods_list").val(idlist);
	 
	 $("#events_goods").html(namelist)
	
	
};

//表单验证
var checkSumbit = function() {
	var name=$("#name").val();
	if(name.length == 0){
		$("#errorMsg").html("活动名称不能为空！");
		$("#name").focus();
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
	
	var data = formGet("edit_eventsinfo_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/eventsinfo/eventsinfo!save.action",
		data : data,
		dataType : "json",
		success : function(json) {
			queryEventsinfo();
			closeModal("editeEventsinfoModal");
			showAlert("操作成功");
		},
		error : function(e) {
			showAlert("操作失败！");
		}
	});
});

var clearForm = function() {
	$("#vId").val('');
	$("#name").val('');
	$("#memo").val('');
	$("#starttime").val('');
	$("#endtime").val('');
	$("#errorMsg").html('&nbsp;');
};

var setEventsinfoForm = function(data) {
	$("#vId").val(data.eventsinfo.id);
	$("#name").val(data.eventsinfo.name);
	$("#isuse").val(data.eventsinfo.isuse);
	$("#memo").val(data.eventsinfo.memo);
	$("#starttime").val(data.eventsinfo.starttime);
	$("#endtime").val(data.eventsinfo.endtime);
	//商品添加待写
	$("#errorMsg").html("&nbsp;");
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>编辑</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></span>删除</a>";
	html += "</div>";
	return html;
};


//编辑
var showEditWin = function(id) {
	/*var idlist="";
	var namelist="";
	var objlist=json.evegslist;
	if(objlist){
		for(var i=0;i<objlist.length;i++){
			var eventsgoods=objlist[i];
			idlist=eventsgoods.goodsId+","+idlist;
			namelist="<div class='"+eventsgoods.goodsId+"'><em>"+eventsgoods.gname+"</em>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='removeChoose(\""+eventsgoods.goodsId+"\"); return false;' >x</a><br></div>"+namelist;	
		}
	}
	$("#events_goods_list").val(idlist);
	$("#events_goods").html(namelist);*/
	//clearForm();
	//$("#couponTypeModal").trigger('changed.selected.amui');
	clearForm();
	var data = {"eventsinfo.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/eventsinfo/eventsinfo!queryEventsinfo.action",
		data : data,
		dataType : "json",
		success : function(data) {
			console.log(data);
			openWin("编辑");
			var isuse = $("#isuse");
			isuse.empty();
			isuse.append(eventsinfoTypeHtml);
			setEventsinfoForm(data);
		}
	});
};


//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"eventsinfo.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/eventsinfo/eventsinfo!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryEventsinfo();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};