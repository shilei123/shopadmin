//初始化
$(function() {
	var freightId =$("#freightId").val();
	if(freightId != ""){
		showEditWin(freightId);
	}else{
		clearForm();
	}
});

//编辑
var showEditWin = function(freightId) {
	clearForm();
	var data = {"fre.id" : freightId};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/freight/freight!EditFreight.action",
		data : data,
		dataType : "json",
		success : function(data) {
			console.log(data);
			showHide();
			setFreightForm(data);
		}
	});
};


var showHide =function(){
	$("#mails").hide();
	$("#express").hide();
	$("#emss").hide();
};


var key = 0;
//追加一个子节点
var pop = function(obj){ 
	key++;
var html = "";
	html +=	"<tr id='"+key+"'><td>";
	html += "<input type=\"hidden\" id='cityName"+key+"'><span id='divts"+key+"' class='divts'>未添加地区</span></input>";
	html += "<input type=\"hidden\" id='mode"+key+"' name='mode'>";
	html +=	"<div style=\"text-align: right;\">";
	html +=	"<a href=\"#\" onclick='edit(\""+key+"\")'>编辑</a>";
	html +=	"</div>";
	html +=	"</td>";
	html +=	"<td><input name=\"initialInts\" onblur='javascript:checkChildFreightThis(this);' type=\"text\"  value=\"1\"  maxlength=\"10\" style=\"width: 50px;\"></td>";		
	html +=	"<td><input name=\"initialPrices\" onblur='javascript:checkChildFreightThis(this);' type=\"text\" value=\"\" maxlength=\"10\" style=\"width: 50px\"></td>";
	html +=	"<td><input name=\"stackInts\" onblur='javascript:checkChildFreightThis(this);' type=\"text\" value=\"1\" maxlength=\"10\" style=\"width: 50px\"></td>";
	html +=	"<td><input name=\"stackPrices\" onblur='javascript:checkChildFreightThis(this);' type=\"text\" value=\"\" maxlength=\"10\" style=\"width: 50px\"></td>";
	html +=	"<td><input type=\"hidden\"  name='userFreightId' \><a href=\"#\" onclick='deletetr(this)'>删除</a></td>";
	html +=	"</tr>";
	
	if(obj == "py"){
		$("#frePyCheckTable tbody").append(html);
	}
	if(obj == "kd"){
		$("#freKdCheckTable tbody").append(html);
	}
	if(obj == "es"){
		$("#freEsCheckTable tbody").append(html);
	}
	
};


var edit = function(obj){
	var url=path_ +"/view/shop/freight/city.jsp?key="+obj;
	showLayerModal("选择区域",url,500,400);
}

//删除添加的节点
var deletetr =function(obj){
	$(obj).parent().parent().remove();
}

var delUserFre = function(obj){
	var $tr = $(obj).parent().parent();
	var freightCheckTableTd = $tr.children("td");
	var userFreightId =  $(freightCheckTableTd[5]).children("input:eq(0)").val();
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/freight/freight!delUserFreight.action",
		data :{"userFre.id":userFreightId},
		dataType : "json",
		success : function(data) {
			window.parent.showMsg("删除成功!");
		}
	});
	$(obj).parent().parent().remove();
}

$("#piece").click(function(){
	$(".span_init").html("件内,");
	$(".span_stack").html("件,增加运费")
	$(".span_initialInt").html("首件数(件)");
	$(".span_stackInt").html("续件数(件)");
});

$("#weight").click(function(){
	$(".span_init").html("kg内,");
	$(".span_stack").html("kg,增加运费")
	$(".span_initialInt").html("首重量(kg)");
	$(".span_stackInt").html("续重量(kg)");
});

$("#volume").click(function(){
	$(".span_init").html("m³内,");
	$(".span_stack").html("m³,增加运费")
	$(".span_initialInt").html("首体积(m³)");
	$(".span_stackInt").html("续体积(m³)");
});

//事件触发判断复选按钮是否被选中
var showOrHideCheckBox = function(obj){
	var $obj = $("#"+obj);
	var $objs = $("#"+obj+"s")
	if($obj.is(':checked') == true){
		$objs.show();
	}else{
		$objs.hide();
	}
}


var checkChildFreightThis = function(obj){
	var $obj = $(obj);
	if($obj.val().length>0) {
		$obj.removeClass("inputerror");
	} else {
		$obj.addClass("inputerror");
	}
}

//指定运费验证
var checkChildFregiht = function(fieldName) {
	var fields = $("input[name='"+fieldName+"']");
	var bool = true;
	for(var i = 0; i < fields.length; i++) {
		var field = $(fields[i]);
		if(field.val().length==0) {
			bool = false;
			field.addClass("inputerror");
		} else {
			field.removeClass("inputerror");
		}
	}
	return bool;
}

//表单验证
var checkSumbit = function() {
	var name=$("#templateName").val();
	if(name.length == 0){
		$("#errorMsg").html("模板名称不能为空！");
		$("#templateName").focus();
		return false;
	}
	
	var a = "";
	var b = $("input[name='transportMode']");
	for(var i=0;i<b.length;i++){
		var c = $(b[i]);
		var checked = c.is(":checked");
		if(checked) {
			var a = $(c).val();
		}
	}
	if(a == ""){
		$("#errorMsg").html("请选择一种运送方式！");
		return false;
	}
	
	if($("#mail").is(":checked") == true){
		var a1 = $("input[name='inp_initialInt']").eq(0).val();
		var a2 = $("input[name='inp_initialPrice']").eq(0).val();
		var a3 = $("input[name='inp_stackInt']").eq(0).val();
		var a4 = $("input[name='inp_stackPrice']").eq(0).val();
		if(!a1 || !a2 || !a3 || !a4) {
			$("#errorMsg").html("请输入完整运费信息！");
			return false;
		}
	}
	
	if($("#expres").is(":checked") == true){
		var b1 = $("input[name='inp_initialInt']").eq(1).val();
		var b2 = $("input[name='inp_initialPrice']").eq(1).val();
		var b3 = $("input[name='inp_stackInt']").eq(1).val();
		var b4 = $("input[name='inp_stackPrice']").eq(1).val();
		if(!b1 || !b2 || !b3 || !b4) {
			$("#errorMsg").html("请输入完整运费信息！");
			return false;
		}
	}
	
	if($("#ems").is(":checked") == true){
		var c1 = $("input[name='inp_initialInt']").eq(2).val();
		var c2 = $("input[name='inp_initialPrice']").eq(2).val();
		var c3 = $("input[name='inp_stackInt']").eq(2).val();
		var c4 = $("input[name='inp_stackPrice']").eq(2).val();
		if(!c1 || !c2 || !c3 || !c4) {
			$("#errorMsg").html("请输入完整运费信息！");
			return false;
		}
	}
	
	if(b.is(":checked") == true){
		var frePyCheckCount = $("#frePyCheckTable tbody tr").length;
		var freKdCheckCount = $("#freKdCheckTable tbody tr").length;
		var freEsCheckCount = $("#freEsCheckTable tbody tr").length;
		if(frePyCheckCount > 0 || freKdCheckCount>0 || freEsCheckCount>0) { //有指定运费，则验证指定运费填写的完整性
			var mode = checkChildFregiht("mode");
			var b1 = checkChildFregiht("initialInts");
			var b2 = checkChildFregiht("initialPrices");
			var b3 = checkChildFregiht("stackInts");
			var b4 = checkChildFregiht("stackPrices");
			if(!b1 || !b2 || !b3 || !b4) {
				$("#errorMsg").html("请输入完整运费信息！");
				$(".inputerror:first").focus(); 
				return false;
			}
			if(!mode){
				$("#errorMsg").html("请点编辑按钮添加具体地区！");
				$("input[name='mode']:first").focus(); 
				return false;
			}
		}
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
	var data = getDate();
	
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/freight/freight!save.action",
		data : data,
		dataType : "json",
		success : function(json) {
			closeCurrentWin();
			window.parent.showMsg("操作成功!");
			window.parent.queryFreight();
		},
		error : function(e) {
			window.parent.showAlert("操作失败！");
		}
	});
});

//新增清空表单
var clearForm = function() {
	$("#templateName").val('');
	$("[type='checkbox']").removeAttr("checked");
	$("#piece").attr("checked","checked");
	$("#errorMsg").html('&nbsp;');
};

//编辑回显
var setFreightForm = function(data) {
	$("#templateName").val(data.freightList[0].templateName);
	var valuation = data.freightList[0].valuation;
	$("input[type='radio']").attr("disabled","disabled");
	var a = $("#piece").val();
	if(a == valuation){
		$("#piece").attr("checked","checked");
		$(".span_init").html("件内,");
		$(".span_stack").html("件,增加运费")
		$(".span_initialInt").html("首件数(件)");
		$(".span_stackInt").html("续件数(件)");
	}
	var b = $("#weight").val();
	if(b == valuation){
		$("#weight").attr("checked","checked");
		$(".span_init").html("kg内,");
		$(".span_stack").html("kg,增加运费")
		$(".span_initialInt").html("首重量(kg)");
		$(".span_stackInt").html("续重量(kg)");
	}
	var c = $("#volume").val();
	if(c == valuation){
		$("#volume").attr("checked","checked");
		$(".span_init").html("m³内,");
		$(".span_stack").html("m³,增加运费")
		$(".span_initialInt").html("首体积(m³)");
		$(".span_stackInt").html("续体积(m³)");
	}
	for(var i=0;i<data.freightList.length;i++){
		var mode = data.freightList[i].transportMode;
		if(mode == "0" && $("#mail").is(":checked") == false){
			$("#mail").attr("checked","checked");
			$("#mails").show();
		}
		if(mode == "1" && $("#expres").is(":checked") == false){
			$("#expres").attr("checked","checked");
			$("#express").show();
		}
		if(mode == "2" && $("#ems").is(":checked") == false){
			$("#ems").attr("checked","checked");
			$("#emss").show();
		}
	}
	
	if($("#mail").is(":checked") == true){
		var chebox = $("#mail").val();
		setCheckedTableList(data,chebox,"frePy","frePyCheckTable");
		
	}
	if($("#expres").is(":checked") == true){
		var chebox = $("#expres").val();
		setCheckedTableList(data,chebox,"freKd","freKdCheckTable");
		
	}
	if($("#ems").is(":checked") == true){
		var chebox = $("#ems").val();
	    setCheckedTableList(data,chebox,"freEs","freEsCheckTable");
		
	}
	
	$("#errorMsg").html("&nbsp;");
};

var setCheckedTableList = function(data,chebox,freTable,checkTable){
	var num = 0;
	for(var i=0;i<data.freightList.length;i++){
		var mode = data.freightList[i].transportMode;
		if(mode == chebox){
			num++;
		}
	}
	
	for(var i=0;i<num-1;i++){
		if(num != 0){
			key++;
			var html = "";
				html +=	"<tr id='"+key+"'><td>";
				html += "<input type=\"hidden\" id='cityName"+key+"'><span id='divts"+key+"' class='divts'>未添加地区</span></input>";
				html += "<input type=\"hidden\" id='mode"+key+"' name='mode'>";
				html +=	"<div style=\"text-align: right;\">";
				html +=	"<a href=\"#\" onclick='edit(\""+key+"\")'>编辑</a>";
				html +=	"</div>";
				html +=	"</td>";
				html +=	"<td><input name=\"initialInts\" onblur='javascript:checkChildFreightThis(this);' type=\"text\"  value=\"1\"  maxlength=\"10\" style=\"width: 50px;\"></td>";		
				html +=	"<td><input name=\"initialPrices\" onblur='javascript:checkChildFreightThis(this);' type=\"text\" value=\"\" maxlength=\"10\" style=\"width: 50px\"></td>";
				html +=	"<td><input name=\"stackInts\" onblur='javascript:checkChildFreightThis(this);' type=\"text\" value=\"1\" maxlength=\"10\" style=\"width: 50px\"></td>";
				html +=	"<td><input name=\"stackPrices\" onblur='javascript:checkChildFreightThis(this);' type=\"text\" value=\"\" maxlength=\"10\" style=\"width: 50px\"></td>";
				html +=	"<td><input type=\"hidden\"  name='userFreightId' \><a href=\"#\" onclick='delUserFre(this)'>删除</a></td>";
				html +=	"</tr>";
				$("#"+checkTable+ " tbody").append(html);
		}
	}
	
	var frePy = $("#"+freTable+" thead tr");
	var frePyTd = frePy.children("td");
	var freightCheckTable = $("#"+checkTable+" tbody tr");
	var j=0;
	for(var i=0;i<data.freightList.length;i++){
			var mode = data.freightList[i].transportMode;
			var range = data.freightList[i].transportRange;
			var ranges = "全国";
			if(mode == chebox && range == ranges){
				$(frePyTd[0]).children("input:eq(0)").val(data.freightList[i].initialInt);
				$(frePyTd[0]).children("input:eq(1)").val(data.freightList[i].initialPrice);
				$(frePyTd[0]).children("input:eq(2)").val(data.freightList[i].stackInt);
				$(frePyTd[0]).children("input:eq(3)").val(data.freightList[i].stackPrice);
				$(frePyTd[0]).children("input:eq(4)").val(data.freightList[i].userFreightId);
			}
			if(mode == chebox && range != ranges) {
				for(j;j<freightCheckTable.length;){
				var $tr = $(freightCheckTable[j]);
				var freightCheckTableTd = $tr.children("td");
				$(freightCheckTableTd[0]).children(".divts").text(data.freightList[i].transportRange);
				$(freightCheckTableTd[0]).children("input:eq(1)").val(data.freightList[i].transportRange);
				$(freightCheckTableTd[1]).children("input:eq(0)").val(data.freightList[i].initialInt);
				$(freightCheckTableTd[2]).children("input:eq(0)").val(data.freightList[i].initialPrice);
				$(freightCheckTableTd[3]).children("input:eq(0)").val(data.freightList[i].stackInt);
				$(freightCheckTableTd[4]).children("input:eq(0)").val(data.freightList[i].stackPrice);
				$(freightCheckTableTd[5]).children("input:eq(0)").val(data.freightList[i].userFreightId);
				j++;
				break;
				}
			}
	 }
}

var childUserFreight = new Array();
//获取页面数据
var getDate = function(){
	var freightType = $("input:radio[name='rad_valuation']:checked").val();
	
	//得到运费子表信息
	if($("#mail").is(":checked") == true){
		var mail = $("#mail").val();
		 childUserFreight = getCheckedTableList("frePy","frePyCheckTable",childUserFreight,mail);
	}
	
	if($("#expres").is(":checked") == true){
		var expres = $("#expres").val();
		 childUserFreight = getCheckedTableList("freKd","freKdCheckTable",childUserFreight,expres);
	}
	
	if($("#ems").is(":checked") == true){
		var ems = $("#ems").val();
		 childUserFreight = getCheckedTableList("freEs","freEsCheckTable",childUserFreight,ems);
	}
	
	var data = {
		"fre.id" :$("#freightId").val(),
		"fre.templateName" : $("#templateName").val(),
		"fre.valuation" : freightType,
		"childUserFreightList" : JSON.stringify(childUserFreight),
	};
	return data;
}

//组装json数据
var getCheckedTableList = function(tableName,tableNames,childUserFreight,obj){
	var frePy = $("#"+tableName+" thead tr");
	var frePyTd = frePy.children("td");
	var initialInt = $(frePyTd[0]).children("input:eq(0)").val();
	var initialPrice = $(frePyTd[0]).children("input:eq(1)").val();
	var stackInt = $(frePyTd[0]).children("input:eq(2)").val();
	var stackPrice = $(frePyTd[0]).children("input:eq(3)").val();
	var userFreight = $(frePyTd[0]).children("input:eq(4)").val();
	var tempjson={"userFreightId":userFreight,"mode":obj,"range":"全国","initialInt":initialInt,"initialPrice":initialPrice,"stackInt":stackInt,"stackPrice":stackPrice};
	childUserFreight.push(tempjson);
	var freightCheckTable = $("#"+tableNames+" tbody tr");
	for(var i=0;i<freightCheckTable.length;i++){
		var $tr = $(freightCheckTable[i]);
		var freightCheckTableTd = $tr.children("td");
		var range = $(freightCheckTableTd[0]).children("input:eq(1)").val();
		var initialInt = $(freightCheckTableTd[1]).children("input:eq(0)").val();
		var initialPrice = $(freightCheckTableTd[2]).children("input:eq(0)").val();
		var stackInt = $(freightCheckTableTd[3]).children("input:eq(0)").val();
		var stackPrice = $(freightCheckTableTd[4]).children("input:eq(0)").val();
		var userFreightId = $(freightCheckTableTd[5]).children("input:eq(0)").val();
		var tempjson = {"userFreightId":userFreightId,"mode":obj,"range":range,"initialInt":initialInt,"initialPrice":initialPrice,"stackInt":stackInt,"stackPrice":stackPrice};
		childUserFreight.push(tempjson);
	}
	return childUserFreight;
}

//关闭窗口
var closeCurrentWin = function() {
	window.parent.closeModal("editeFreightModal");
};
$("#closeBtn").click(closeCurrentWin);

