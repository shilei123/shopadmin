$(function() {
	initOrderType();
	query();
});

var initOrderType = function() {
	var data = {"initType.orderDeliveryMode":"ORDER_DELIVERY_MODE",
				"initType.orderSts":"ORDER_STS",
				"initType.ordeSplit":"ORDER_SPLIT",
				"initType.orderInvoice":"ORDER_INVOICE",
				"initType.orderPayMode":"ORDER_PAY_MODE"};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/order/order!initDictsByType.action",
		data : data,
		dataType : "json",
		success : function(data) {
			var initMap = data.initMap;
			//console.log(initMap);
			initSelect(initMap.orderDeliveryMode, "orderDeliveryMode");
			initSelect(initMap.orderSts, "orderSts");
			initSelect(initMap.ordeSplit, "ordeSplit");
			initSelect(initMap.orderInvoice, "orderInvoice");
			initSelect(initMap.orderPayMode, "orderPayMode");
			actualPrice = document.getElementById("endActualPrice");
		},
		error : function(e) {
		}
	});
}

var initSelect = function(data, id){
	var html = "<option value=''>-请选择-</option>";
	for(x in data){
		html += "<option value='" + data[x].code + "'>" + data[x].name + "</option>"
	}
	$("#" + id).html(html);
}

var openOrderInfoWin = function(title) {
	$("#title").text(title);
	showModal("doc-modal-2",500,350);
};

$("#closeBtn").click(function() {
	closeModal("doc-modal-2");
});

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

var query = function() {
	var data = formGet2("from_query");
	var url = path_ + "/view/shop/order/order!queryOrderList.action";
	pageData2(url, "orderListTable", data);
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a id='a1' href='javascript:void(0)' onclick='showQueryOrderWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span><span id='a1span'>查看</span></a>";
	/*html += "<a id='a2' href='javascript:void(0)' onclick='showEditOrderWin(\""+ row["id"]+ "\")'><span class='am-icon-search'></span>编辑</a>";*/
	html += "</div>";
	return html;
};

var setOrderInfo = function(order) {
	$("#orderCode2").val(order.orderCode);
	$("#deliveryMode2").val(order.deliveryMode);
	$("#orderStatus2").val(order.orderStatus);
	//$("#content2").val(order.content);
};

var showQueryOrderWin = function(id) {
	var data = {"order.id" : id};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/order/order!queryOrderById.action",
		data : data,
		dataType : "json",
		success : function(data) {
			console.log(data.orderList);
			setOrderInfo(data.order);
			openOrderInfoWin("查看订单");
		}
	});
};

/*=========================================================分页方法======================================================*/

var createTr2 = function(data, targetId) {
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
			var w = $(tableTh[i]).attr("width");
			html += "<td width='"+w+"'>";
			if(fieldName==undefined && formatter==undefined) { //字段名为空，则返回空列
				html += "&nbsp;";
			} else {
				if(fieldName=="index") {//字段名为index,返回序号列
					html += "<div style='text-align: center;'>" + index2 + "</div>";
				} else{
					if(formatter==undefined) { //格式化为空，则返回文本列
						html += getColumnValue2(row[fieldName]);
					} else { //格式化不为空，则调用格式化函数
						html += window[formatter](getColumnValue2(row[fieldName]), row);
						//"货到付款"且"已提交"加上确认订单功能
						
						//"货到付款"且"已确认"加上取消订单功能
						
						//"线上支付或支付宝微信"且"待付款"加上调整费用功能
						
						//"线上支付或支付宝微信"且"待发货"加上取消订单功能
						
						//可以挪到发货管理做？方便权限管理
					}
				}
			}
			html += "</td>";
		});
		html += "</tr>";
	});
	html += "";
	return html;
};

var getColumnValue2 = function(fieldValue) {
	if(fieldValue==null)
		return "&nbsp;";
	return fieldValue;
};

function pageData2(url, targetId, params, currPageNum, rowCount, page) {
	params["page"] = (currPageNum || 1);
	rowCount = rowCount || 'rowCount';
	page = page || 'page';
	$.ajax({
		url : url,
		type : 'POST',
		data : params,
		dataType: "json",
		success : function(data) {
			//console.log(data);
			$('#'+targetId+' tbody').empty();
			var html = createTr2(data, targetId);
			$("#"+targetId).append(html);
			$("#"+rowCount).text(data.total);
			//显示分页
			var pages = Math.ceil(data.total / data.rowCount);
			laypage({
				cont : page, //容器。值支持id名、原生dom对象，jquery对象。
				pages : pages, //通过后台拿到的总页数
				curr : currPageNum || 1, //当前页
				groups : 5, //连续显示分页数
				//skin: 'yahei',//皮肤
				//skip: true, //是否开启跳页
				jump : function(obj, first) { //触发分页后的回调
					if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr//first一个Boolean，检测页面是否初始加载
						pageData2(url, targetId, params, obj.curr, rowCount, page);
					}
				}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert('e:' + XMLHttpRequest.responseText);
		}
	});
};

function formGet2(formId){
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
