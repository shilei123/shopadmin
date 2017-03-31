$(function() {
	queryFreight();
	freightIsuse();
});

var queryFreight = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/freight/freight!queryFreight.action";
	pageData(url, "freightListTable", data);
};

var freightIsuse = function() {
	var isuse = $("#isuse");
	isuse.empty();
	$.ajax({
		url :path_ + "/view/shop/freight/freight!queryIsuse.action",
		type : 'POST',
		data : null,
		dataType: "json",
		success : function(data) {
			//是否默认
			var html = "<option value='-1'>-请选择-</option>";
			$(data.isuseList).each(function(index) {
				var isuseType = data.isuseList[index];
				html += "<option value='" + isuseType.code + "'>" + isuseType.name + "</option>";
			});
			isuse.append(html);
		}
	});
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	queryFreight();
});

var openWin = function(title) {
	$("#titles").text(title);
	showModal("editeFreightModal",720,1000);
	
};

$("#closeBtn").click(function() {
	closeModal("editeFreightModal");
});


var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showEditWin(\""+ row["id"]+ "\")'><span class='am-icon-edit'></span>编辑</a>";
	html += "<a href='javascript:void(0)' class='am-text-danger' onclick='deleteDict(\""+ row["id"]+ "\")'><span class='am-icon-remove'></i>删除</a>";
	html += "</div>";
	return html;
};

var showHide =function(){
	$("#mails").hide();
	$("#expresl").hide();
	$("#emss").hide();
};


var setHtml = function(){
	html  = "<td colspan=\"4\">";
	html += "<div>";
	html += "<table width=\"100%\" frame=\"box\" border=\"0\">";
	html += "<tr>";
	html += "<td colspan=\"4\">";
	html +=	"<table width=\"100%\" border=\"1\" id=\"tbl\">";
	html +=	"<tr>";
	html += "<th colspan=\"6\">默认运费";
	html += "<input name=\"freight.initialInt\" type=\"text\"  value=\"1\"  maxlength=\"10\" style=\"width: 50px\"><span class=\"initialInts\">件内,</span>";
	html += "<input name=\"freight.initialPrice\" type=\"text\" value=\"\" maxlength=\"10\" style=\"width: 50px\">元,每增加";
	html += "<input name=\"freight.stackInt\" type=\"text\" value=\"1\" maxlength=\"10\" style=\"width: 50px\"><span class=\"initialInts\">件,增加运费</span>";
	html += "<input name=\"freight.stackPrice\" type=\"text\" value=\"\" maxlength=\"10\" style=\"width: 50px\">元";
	html += "</th></tr>";
	html +=	"<tr>";
	html +=	"<td width=\"40%\" align=\"center\">运送到</td>";
	html +=	"<td width=\"10%\"><span class=\"initialInt\">首件数(件)</span></td>";
	html +=	"<td width=\"10%\">首费(元)</td>";
	html +=	"<td width=\"10%\"><span class=\"stackInt\">续件数(件)</span></td>";
	html +=	"<td width=\"10%\">续费(元)</td>";
	html +=	"<td width=\"10%\">操作</td>";
	html +=	"</tr>";
	html +=	"</table>";
	html += "</td>";
	html += "</tr>";
	html +=	"<tr><td colspan=\"4\">";
	html +=	"<div style=\"padding-left: 10px; padding-top: 10px;\">";
	html +=	"<a href=\"#\" onclick='pop()'>为制定城市地区设置运费</a>";
	html +=	"</div>";
	html += "</td></tr>";
	html +=	"</table>";
	html += "</div>";
	html += "</td>";
	return html;
	
}

var pop = function(){
	html =	"<tr><td>";
	html +=	"<div style=\"text-align: left;\" >";
	html += "<span id=\"divts\"></span>";
	html += "<input type=\"hidden\" id=\"cityName\">";
	html +=	"</div>";
	html +=	"<div style=\"text-align: right;\">";
	html +=	"<a href=\"#\" onclick='edit()'>编辑</a>";
	html +=	"</div>";
	html +=	"</td>";
	html +=	"<td><input name=\"freight.initialInt\" type=\"text\"  value=\"1\"  maxlength=\"10\" style=\"width: 50px\"></td>";		
	html +=	"<td><input name=\"freight.initialPrice\" type=\"text\" value=\"\" maxlength=\"10\" style=\"width: 50px\"></td>";
	html +=	"<td><input name=\"freight.stackInt\" type=\"text\" value=\"1\" maxlength=\"10\" style=\"width: 50px\"></td>";
	html +=	"<td><input name=\"freight.stackPrice\" type=\"text\" value=\"\" maxlength=\"10\" style=\"width: 50px\"></td>";
	html +=	"<td><a href=\"#\" onclick='deletetr(this)'>删除</a></td>";
	html +=	"</tr>";
	$("#tbl").append(html);
	if($("#cityName").val().length == 0){
		$("#divts").html("未添加地区");
	}
	//resetSequenceNum();
};

var resetSequenceNum = function(){
	 var num=0;  
	    $("#tbl tr").each(function(index,dom){  
	        if(index!=0){
	            $(dom).find("td").first().html(num);  
	            num++;  
	        }
	    });  
}

var deletetr =function(obj){
	$(obj).parent().parent().remove();
	//resetSequenceNum();
}

var edit = function(){
	var url=path_ +"/view/shop/freight/city.jsp";
	showLayerModal("选择区域",url,500,400);
}

$("#piece").click(function(){
	$(".initialInts").html("件内,");
	$(".initialInts").html("件,增加运费")
	$(".initialInt").html("首件数(件)");
	$(".stackInt").html("续件数(件)");
});

$("#weight").click(function(){
	/*showConfirm("切换计价方式后，所设置当前模板的运输信息将被清空，确定继续么？？", function() {
	var expresl = $("#expresl");
	expresl.empty();*/
	$(".initialInts").html("kg内,");
	$(".initialInts").html("kg,增加运费")
	$(".initialInt").html("首重量(kg)");
	$(".stackInt").html("续重量(kg)");
	//});
});

$("#volume").click(function(){
	$(".initialInts").html("m³内,");
	$(".initialInts").html("m³,增加运费")
	$(".initialInt").html("首体积(m³)");
	$(".stackInt").html("续体积(m³)");
});

//事件触发判断单选按钮是否被选中
$("#mail").click(function(){
	if($("#mail").is(':checked') == true){
		$("#mails").show();
		var mails = $("#mails");
		mails.empty();
		var html = setHtml();
		mails.append(html);
	} else{
		$("#mails").hide();
	}
	
});

//事件触发判断单选按钮是否被选中
$("#express").click(function(){
	if($("#express").is(':checked') == true){
		$("#expresl").show();
		var expresl = $("#expresl");
		expresl.empty();
		var html = setHtml();
		expresl.append(html);
	}else{
		$("#expresl").hide();
	}
	
});

//事件触发判断单选按钮是否被选中
$("#ems").click(function(){
	if($("#ems").is(':checked') == true){
		$("#emss").show();
		var emss = $("#emss");
		emss.empty();
		var html = setHtml();
		emss.append(html);
	}else{
		$("#emss").hide();
	}
	
});

//弹出新增窗口
$("#addBtn").click(function() {
	clearForm();
	showHide();
	openWin("新增");
});

//表单验证
var checkSumbit = function() {
	var name=$("#templateName").val();
	if(name.length == 0){
		$("#errorMsg").html("模板名称不能为空！");
		$("#templateName").focus();
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
	
	var data = formGet("edit_fre_table");
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/freight/freight!save.action",
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
	$("#templateName").val('');
	$("[type='checkbox']").removeAttr("checked");
	var mails = $("#mails");
	mails.empty();
	var expresl = $("#expresl");
	expresl.empty();
	var emss = $("#emss");
	emss.empty();
	$("#errorMsg").html('&nbsp;');
	
};

var setFreightForm = function(data) {
	
	$("#errorMsg").html("&nbsp;");
};


//编辑
var showEditWin = function(id) {
	clearForm();
	//$("#couponTypeModal").trigger('changed.selected.amui');
	var data = {"fre.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/freight/freight!EditFreight.action",
		data : data,
		dataType : "json",
		success : function(data) {
			showHide();
			openWin("编辑");
			setFreightForm(data);
		}
	});
};


//删除
var deleteDict = function(id) {
	showConfirm("确认删除？", function() {
		var data = {"fre.id" : id};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/freight/freight!delete.action",
			data : data,
			dataType : "json",
			success : function(json) {
				showAlert("操作成功");
				queryFreight();
			},
			error : function(e) {
				showAlert("操作失败");
			}
		});
	});
};