$(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/goodsManager/goodsInfoAction!queryAllGoodsList.action";
	pageData(url, "dataListTable", data);
};

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

var formatterTitle = function(value, row) {
	return "<a href='javascript:void(0);' onclick='showGoodsDetailTab(\""+ row["id"]+ "\")'>"+value+"</a>";
};

var formatterAction = function(value, row) {
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showRepEditWin(\""+ row["id"]+ "\")'><i class='am-icon-edit'></i>修改库存</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showPriceEditWin(\""+ row["id"]+ "\")'><i class='am-icon-edit'></i>修改价格</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showPriceHistoryWin(\""+ row["id"]+ "\")'><i class='am-icon-edit'></i>价格历史</a>";
	html += "</div>";
	return html;
};

$("#closeBtn1").click(function() {
	closeModal("priceEditModel");
});

$("#closeBtn2").click(function() {
	closeModal("childGoodsPriceEditModel");
});

$("#closeBtn3").click(function() {
	closeModal("repEditModel");
});

$("#closeBtn4").click(function() {
	closeModal("childGoodsRepEditModel");
});

$("#closeBtn5").click(function() {
	closeModal("priceHistoryModal");
});

var emptyStore="";
var showRepEditWin = function(goodsId) {
	var data = {"goods.id":goodsId};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/goodsManager/goodsInfoAction!queryGoodsRep.action",
		data : data,
		dataType : "json",
		success : function(json) {
			if(json.repList!=null && json.repList.length > 0) {
				emptyStore = json.repList[0].emptyStore;
				if(json.repList.length==1 && json.repList[0].childGoodsId==null) {
					$("#pkId").val(json.repList[0].id);
					$("#availableNum").val(json.repList[0].availableNum);
					$("#salesNum").val(json.repList[0].salesNum);
					showModal("repEditModel", 300, 240);
				} else {
					$("#goodsChildTable tbody").empty();
					var len = json.repList.length;
					for (var i = len-1; i >= 0; i--) {
						var childGoods = json.repList[i];
						//添加子商品
						prePendGoodsChildTable(childGoods);
					}
					showModal("childGoodsRepEditModel", 420);
				}
			}
		},
		error : function(e) {
		}
	});
};

//添加子商品
var prePendGoodsChildTable = function(childGoods) {
	var childGoodsLabel="",availableNum="",salesNum="",pkId="";
	if(childGoods!=undefined && childGoods!=null){
		var childNames = childGoods.childName.split(",");
		var br = "";
		for (var i = 0; i < childNames.length; i++) {
			var childName = childNames[i].split(":");
			var valName = childName[1];
			var propName = childName[0];
			childGoodsLabel += br + "&nbsp;<strong>" + propName + "</strong>：" + valName + "&nbsp;";
			br = "<br/>";
		}
		availableNum = childGoods.availableNum;
		salesNum = childGoods.salesNum==null?"":childGoods.salesNum;
		pkId = childGoods.id;
	}
	var trHtml = "";
	trHtml += "<tr>";
	trHtml += "    <td style='text-align:left;'><div style='margin-top:5px;margin-bottom:5px;'>"+childGoodsLabel+"</div></td>";
	trHtml += "    <td>&nbsp;<input name='availableNum' onblur='javascript:checkChildGoodsFieldThis(this);' style=\"width:80px;\" value='"+availableNum+"'/>&nbsp;</td>";
	trHtml += "    <td>&nbsp;<input name='salesNum' onblur='javascript:checkChildGoodsFieldThis(this);' style=\"width:80px;\" value='"+salesNum+"'/>&nbsp;</td>";
	trHtml += "    <input type='hidden' value='"+pkId+"'/></td>";
	trHtml += "</tr>";
	
	if(emptyStore="0") {
		$("#goodsChildTable thead tr th").eq(1).children("span:eq(0)").show();
		$("#goodsChildTable thead tr th").eq(2).children("span:eq(0)").show();
	} else {
		$("#goodsChildTable thead tr th").eq(1).children("span:eq(0)").hide();
		$("#goodsChildTable thead tr th").eq(2).children("span:eq(0)").hide();
	}
	
	$("#goodsChildTable tbody").prepend(trHtml);
};

//验证
var checkChildGoodsFieldThis = function(obj) {
	var $obj = $(obj);
	if($obj.val().length > 0) {
		$obj.removeClass("inputerror");
	} else {
		$obj.addClass("inputerror");
	}
};

var showPriceEditWin = function(id) {
	showModal("priceEditModel", 300, 300);
};

var showPriceHistoryWin = function(id) {
	showModal("priceHistoryModal", 800);
};