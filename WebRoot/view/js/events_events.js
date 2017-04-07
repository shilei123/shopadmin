var eventsTypeHtml = "";

$(function() {
	queryEventsinfo();
	queryEventsType();
});

var queryEventsinfo = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/events/events!query.action";
	pageData(url, "eventsListTable", data); 
};

var queryEventsType = function() {
	var isuse = $("#isuse");
	isuse.empty();
		$.ajax({
			url :path_ + "/view/shop/events/events!queryEventsType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				var html = "<option value='-1'>-请选择-</option>";
				$(data.dictionaryList).each(function(index) {
					var eventsType = data.dictionaryList[index];
					html += "<option value='" + eventsType.code + "'>" + eventsType.name + "</option>";
				});
				eventsTypeHtml = html;
				isuse.append(html);
			}
		});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryEventsType();
});

var openWin = function(title) {
	$("#titles").text(title);
	showModal("editeEventsModal",800,460);
};

$("#closeBtn").click(function() {
	closeModal("editeEventsModal");
});

//弹出新增窗口
$("#addBtn").click(function() {
	clearForm();
	openWin("新增");
	//重新设置下拉框值
	//$("#couponTypeModal").trigger('changed.selected.amui');
	var isuse = $("#isuse");
	isuse.empty();
	isuse.append(eventsTypeHtml);
});

//选择商品
$("#chooseGoodsBtn").click(function(){
	//打开商品列表窗口
	var url=path_ +"/view/shop/advertise/queryGoods.jsp";
	showLayerModal("选择商品",url,700,450);
});

//选择商品的回调函数
var selectGoods = function(obj) {
	$('#eventsId').val(obj.goodsId);
	$('#eventsName').val(obj.goodsName);
	var events_id = $('#eventsId').val();
	var events_name = $('#eventsName').val();
	setImgLind(events_id,events_name);
};

//关闭
$("#goodsCloseBtn").click(function() {
	closeModal("doc-modal-2");
});

//添加商品
$("#btn_add_rule").click(function () {
    $(".error-message").empty();
    $('#mansong_price').val('');
    $('#mansong_discount').val('');
    $('#mansong_price_error').hide();
    $('#mansong_rule_error').hide();
    $('#div_add_rule').show();
    $('#div_confirmOrCancel').show();
    $('#btn_add_rule').hide();
    $("label[for=mansong_rule_count]").hide();
});

//取消添加商品
$('#btn_cancel_add_rule').on('click', function () {
    close_div_add_rule();
});

function close_div_add_rule() {
    var rule_count = $('#mansong_rule_list').find('[nctype="mansong_rule_item"]').length;
    if (rule_count >= 10) {
        $('#btn_add_rule').hide();
    } else {
        $('#btn_add_rule').show();
    }
    $('#div_add_rule').hide();
    $('#mansong_rule_count').val(rule_count);
}

//确定商品
$('#btn_save_rule').on('click', function () {
    var mansong = {};
    var rulesObj = $("#mansong_rule_list").children("li:last-child").children("input[name='activity_rule']");
    var maxPrice = 0;
    if (rulesObj.size() > 0) {
        maxPrice = rulesObj.val().split(";")[0];
    }
    mansong.price = Number($('#mansong_price').val());
    if (isNaN(mansong.price) || mansong.price <= 0) {
        $('#mansong_price_error').show();
        return false;
    } else {
        $('#mansong_price_error').hide();
    }
    mansong.scope = $('#scope').val();
    $('#mansong_rule_list').append("<li nctype='mansong_rule_item'>"
    		+"<span>活动价格&nbsp;<strong>"
            + mansong.price + " </strong>&nbsp;元,<span>生效范围<select name='eventsGoods.scope' id='scope' data-am-selected='{btnWidth: '152px'}'>"
            +"<option value='1'>所有</option>"
            +"<option value='0'>活动</option>"
            +"</select>"
            +"<em class='add-on'><i class='icon-renminbi'></i></em></span>"
            + "<input type='hidden' name='activity_rule' value='" + mansong.price + ";" + mansong.scope + "'>"
            + " <a nctype='btn_del_mansong_rule' href='javascript:void(0);'class='sc-btn-mini sc-btn-red'><i class='icon-trash'></i>删除</a></li>");
    $("#scope option[value='"+mansong.scope+"']").attr("selected",true);
    close_div_add_rule();
});

// 删除已添加的规则
$('#mansong_rule_list').on('click', '[nctype="btn_del_mansong_rule"]', function () {
    $(this).parents('[nctype="mansong_rule_item"]').remove();
    close_div_add_rule();
});


//添加商品
var setImgLind = function(id,name){
	var idlist=$("#eventsGoodsList").val();
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
		namelist="<div class='"+id+"'><em>"+name+"</em>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='removeChoose(\""+id+"\"); return false;' ><span class='am-icon-remove'>删除</span></a><br></div>"+namelist;	
	 }
	 $("#eventsGoodsList").val(idlist);
	 
	 $("#events_goods").html(namelist)
};

var removeChoose = function(id){
	var events_goods_list = $("#eventsGoodsList").val();
	var idlist="";
	var strs = events_goods_list.split(",");
	for(var i = 0;i<strs.length;i++){
		var str=strs[i];
		if(str == id || str == ""){
			continue;
		}
		idlist = str+","+idlist
	}
	$("#eventsGoodsList").val(idlist);
	$("."+id).remove();
}


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
	
	var data = formGet("edit_events_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/events/events!save.action",
		data : data,
		dataType : "json",
		success : function(json) {
			queryEventsinfo();
			closeModal("editeEventsModal");
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
	$("#startTime").val('');
	$("#endTime").val('');
	$("#events_goods").html('');
	$("#eventsGoodsList").val('');
	$("#errorMsg").html('&nbsp;');
};

var setEventsForm = function(data) {
	$("#vId").val(data.eventsList[0].id);
	$("#name").val(data.eventsList[0].name);
	$("#isuse").val(data.eventsList[0].isuse);
	$("#memo").val(data.eventsList[0].memo);
	$("#startTime").val(data.eventsList[0].startTime);
	$("#endTime").val(data.eventsList[0].endTime);
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
	clearForm();
	var data = {"events.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/events/events!queryEvents.action",
		data : data,
		dataType : "json",
		success : function(data) {
			openWin("编辑");
			var isuse = $("#isuse");
			isuse.empty();
			isuse.append(eventsTypeHtml);
			setEventsForm(data);
		}
	});
};


//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"events.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/events/events!delete.action",
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