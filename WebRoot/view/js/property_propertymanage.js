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
	var data = { "property.id" : propId, "property.flag" : propFlag, "property.propName" : propName, "property.propCode" : propCode, "property.order" : propOrder };
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
	$("#propOrder").val(data.property.order);
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
/*---------------------------------------------------属性属性值配置分割线------------------------------------------------------------*/
/*---------------------------------------------------属性属性值配置分割线------------------------------------------------------------*/
/*---------------------------------------------------属性属性值配置分割线------------------------------------------------------------*/
/*---------------------------------------------------属性属性值配置分割线------------------------------------------------------------*/
//属性属性值配置
var showConfigModel = function(id) {
	$("#propertyId").val(id);
	//var data = {"property.id" : id};
	//$('#categoryParamsFrame').attr('src', path_ + '/view/shop/category/category_property.jsp.jsp?categoryId='+obj.categoryId);
	showModal("doc-modal-1", 600, 450);
	queryPropValueCheck();
}
var queryPropValueCheck = function(){
	$.ajax({
		url : path_ + "/view/propPropValue/propPropValue!queryPropPropValueCheck.action",
		type : 'POST',
		data : formGetCurrentJsp("form1"),
		dataType: "json",
		success : function(data) {
			//console.log(data);
			writeHidden(data);
			queryPropValue();//属性值分页
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert('e:' + XMLHttpRequest.responseText);
		}
	});
};
function formGetCurrentJsp(formId){
	var obj = new Object();
	$("#"+ formId +" :input").each(function(i, n){
		if(n.name!=null && n.name!=''){
			obj[n.name] = n.value;
		}else if(n.id!=null && n.id!=''){
			obj[n.id] = n.value;
		}
	});
	return obj;
}
//写到hidden
var writeHidden = function(data){
	var checkPropValueIds = "";
	for(i in data.listCheck){
		checkPropValueIds += data.listCheck[i].valId;
		checkPropValueIds += ",";
	};
	$("#checkPropValueIds").val(checkPropValueIds);
};
var queryPropValue = function() {
	var data = formGetCurrentJsp("form1");
	var url = path_ + "/view/propPropValue/propPropValue!queryPropPropValue.action";
	pageDataCurrentJsp(url, "propValueTable", data);
};
function pageDataCurrentJsp(url, targetId, params, currPageNum, rowCount, page) {
	params["page"] = (currPageNum || 1);
	rowCount = rowCount || "rowCount1";
	page = page || "page1";
	$.ajax({
		url : url,
		type : 'POST',
		data : params,
		dataType: "json",
		success : function(data) {
			//console.log(data);
			$('#'+targetId+' tbody').empty();
			var html = createTrCurrentJsp(data, targetId);
			$("#"+targetId).append(html);
			$("#"+rowCount).text(data.total);
			//显示分页
			var pages = Math.ceil(data.total / data.rowCount);
			laypage({
				cont : page, //容器。值支持id名、原生dom对象，jquery对象。
				pages : pages, //通过后台拿到的总页数
				curr : currPageNum || 1, //当前页
				groups : 5, //连续显示分页数
				jump : function(obj, first) { //触发分页后的回调
					if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr//first一个Boolean，检测页面是否初始加载
						pageDataCurrentJsp(url, targetId, params, obj.curr, rowCount, page);
					};
				}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert('e:' + XMLHttpRequest.responseText);
		}
	});
};
var createTrCurrentJsp = function(data, targetId) {
	var tableTh = $("#"+targetId+" thead tr th");
	if(data.rows != null && data.rows == 0 && tableTh != null && tableTh.length > 0) {
		return "<tr><td colspan='"+tableTh.length+"' style='text-align:center;height:50px;vertical-align: middle;'>&nbsp;暂无记录!</td></tr>";
	}
	var html = "";
	$(data.rows).each(function(index) {
		var row = data.rows[index];
		var index2 = index+1;
		html += "<tr >";
		$(tableTh).each(function(i) {
			var fieldName = $(tableTh[i]).attr("field");
			var formatter = $(tableTh[i]).attr("formatter");
			var wid = $(tableTh[i]).attr("width");
			html += "<td width='" + wid + "'>";
			if(fieldName==undefined && formatter==undefined) { //字段名为空，则返回空列
				html += "&nbsp;";
			} else {
				if(fieldName=="index") {//字段名为index,返回序号列
					html += "<div style='text-align: center;'>" + index2 + "</div>";
				} else if(fieldName=="checkbox"){
					if(checkProp(row)){
						html += " <input name='property' id='checkbox" + index2 + "' onclick='checkIt(\""+ row.id + "\",\"checkbox"+ index2 + "\")' type='checkbox' value='" + row.id + "' checked='checked'/> ";
					}else{
						html += " <input name='property' id='checkbox" + index2 + "' onclick='checkIt(\""+ row.id + "\",\"checkbox"+ index2 + "\")' type='checkbox' value='" + row.id + "'/> ";
					};
				} else{
					if(formatter==undefined) { //格式化为空，则返回文本列
						html += getColumnValue(row[fieldName]);
					} else { //格式化不为空，则调用格式化函数
						html += window[formatter](getColumnValueCurrentJsp(row[fieldName]), row);
					};
				};
			}
			html += "</td>";
		});
		html += "</tr>";
	});
	html += "";
	return html;
};
var checkProp = function(row){
	var checkPropValueIds = $("#checkPropValueIds").val();
	var arr = checkPropValueIds.split(",");
	for(x in arr){
		if(row.id==arr[x]){
			return true;
		}
	};
	return false;
};
//check或者uncheck在修改hidden
var checkIt = function(propValueId, checkboxId){
	var checkPropValueIds = $("#checkPropValueIds").val();
	var arr = checkPropValueIds.split(",");
	var checkPropValueIdsNew = "";
	var isCheck = $("#"+checkboxId).attr('checked');
	if(isCheck=="checked"){
		checkPropValueIdsNew += propValueId;
		checkPropValueIdsNew += ",";
	}
	for(x in arr){
		if(arr[x]!="" && arr[x]!=null && arr[x]!=propValueId){
			checkPropValueIdsNew += arr[x];
			checkPropValueIdsNew += ",";
		}
	};
	$("#checkPropValueIds").val(checkPropValueIdsNew);
};
//保存类别-属性配置
$("#saveBtn1").click(function() {
	$("#form1").validate({
		submitHandler:function(form) {
            $("#form1").form('submit', {
	    		url:path_ + "/view/propPropValue/propPropValue!savePropPropValue.action",
	    		success:function(data) {
	    			window.parent.showAlert("操作成功！");
	    			closeModal("doc-modal-1");
	    		}
	    	});
        }
    }); 
	$("#form1").submit();
});

//关闭窗口
$("#closeBtn1").click(function() {
	closeModal("doc-modal-1");
});
