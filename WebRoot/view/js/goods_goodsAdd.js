//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('pceditor');
//重置编辑器
var resetEditor = function() {
	if(ue){
   		ue.setContent("");
        ue.reset();
        clearLocalData();
	}
};
//清空编辑器草稿箱
var clearLocalData = function() {
	UE.getEditor('editor').execCommand("clearlocaldata");
};
//判断编辑器是否有内容
var hasContent = function() {
	return UE.getEditor('editor').hasContents();
};

//加载layui的laydate组件
layui.use('laydate', function(){});
$("#publishTime").click(function() {
	layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
});

$(function() {
	//查询类别下的品牌
	
	//查询类别属性属性值
	queryCatePropPropVal();
});

//var cateInfo = new Array(); //类别列表
var propInfo = new Array(); //属性列表
var valInfo = new Array(); //属性值列表
var cppInfo = new Array(); //类别-属性-属性值关系列表

//查询类别属性属性值
var queryCatePropPropVal = function() {
	$.ajax({
		type : "POST",
		url : path_ + "/view/catePropPropval/catePropPropval!queryByCateId.action",
		dataType : "json",
		data: "cateId="+cateId,
		success : function(json) {
			//填充数组
			pushAllArray(json.list);
			//渲染商品属性
			buildPropertyDiv();
		},
		error: function(e){
			console.log("查询类别属性属性值异常");
		} 
	});
};

//填充数组
var pushAllArray = function(list) {
	$(list).each(function(index) {
		var row = list[index];
		//pushJson(cateInfo, "cateId", row.cateId, {"cateId":row.cateId,"cateName":row.cateName,"cateOrder":row.cateOrder});
		pushJson(propInfo, "propId", row.propId, {"cateId":row.cateId,"propId":row.propId,"propName":row.propName,"propCode":row.propCode,"propOrder":row.propOrder});
		pushJson(valInfo, "valId", row.valId, {"cateId":row.cateId,"propId":row.propId,"valId":row.valId,"valName":row.valName,"valCode":row.valCode,"valOrder":row.valOrder});
		cppInfo.push({"cppId":row.cppId,"cateId":row.cateId,"propId":row.propId,"valId":row.valId});
	});
};

//渲染商品属性
var buildPropertyDiv = function() {
	var divHtml = "";
	$(propInfo).each(function(index) {
		var property = propInfo[index];
		
		divHtml = "<div style='display: inline-block;margin-right:10px;'>";
		divHtml += "<input type='checkbox' name='propertyCheck' onclick='buildPropertyValTable(this);' value='"+property.propName+"_"+property.propId+"'/>";
		divHtml += "&nbsp;" + property.propName;
		divHtml += "</div>";
		
		$(divHtml).appendTo("#propertyDiv");
	});
};

$("#addPropertyDiv").click(function() {
	//查询类别属性属性值
	var selectList = $("select[name='select_']");
	var selectLength = selectList.length;
	if(selectLength==0) {
		showLayerMsg("请选择商品属性！");
		return;
	}
	
	var childGoodsValue = "";
	var existsGoodsKey = "";
	var childGoodsLabel = "";
	
	var br = "";
	var split = "";
	for (var i = 0; i < selectLength; i++) {
		var sel = selectList[i];
		var cppId = sel.value;
		var valName = sel.options[sel.selectedIndex].text;
		var propName = $("#propertyEditTable thead tr th").eq(i).text();
		
		existsGoodsKey += split + cppId;
		childGoodsLabel += br + "&nbsp;<strong>" + propName + "</strong>：" + valName + "&nbsp;";
		childGoodsValue += split + cppId + ":" + propName + ":" + valName;
		
		br = "<br/>";
		split = "_";
	}
	
	//根据标识检测是否填过相同的子商品
	if($("#"+existsGoodsKey).length==0) {
		var trHtml = "";
		trHtml += "<tr>";
		trHtml += "    <td style='text-align:left;'><input type='hidden' name='gcpp.catePropPropvalId' id='"+existsGoodsKey+"' value='"+childGoodsValue+"'/><div style='margin-top:5px;margin-bottom:5px;'>"+childGoodsLabel+"</div></td>";
		trHtml += "    <td>&nbsp;<input name='goodsChild.purchasePrice' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "    <td>&nbsp;<input name='goodsChild.marketPrice' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "    <td>&nbsp;<input name='goodsChild.salePrice' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "    <td>&nbsp;<input name='goodsChild.promotionPrice' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "    <td>&nbsp;<input name='' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "    <td>&nbsp;<input name='goodsChild.childNo' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "    <td>&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteGoodChilds(\""+existsGoodsKey+"\")'><span class='am-icon-remove'></i>删除</a>&nbsp;</td>";
		trHtml += "</tr>";
		$("#goodsChildTable tbody").prepend(trHtml);
	} else {
		showLayerMsg("该商品已经添加！");
	}
	//隐藏和显示商品价格、库存、货号信息
	showOrHideGoodsPriceTable();
});

//删除所选子商品
var deleteGoodChilds = function(existsGoodsKey) {
	if($("#"+existsGoodsKey).length > 0) {
		$("#"+existsGoodsKey).parent().parent().remove();
	}
	//隐藏和显示商品价格、库存、货号信息
	showOrHideGoodsPriceTable();
};

//隐藏和显示商品价格、库存、货号信息
var showOrHideGoodsPriceTable = function() {
	var goodsChildCount = $("#goodsChildTable tbody tr").length; //子商品数量
	if(goodsChildCount > 0) {
		$("#goodsPriceTable").hide();
		$("#kcpzSpan").parent().parent().show();
	} else {
		$("#goodsPriceTable").show();
		$("#kcpzSpan").parent().parent().hide();
	}
};

//根据选择的商品属性，构建商品属性值选择table
var buildPropertyValTable = function(thisobj) {
	//清空商品属性值选择table
	$("#propertyEditTable thead tr").empty();
	$("#propertyEditTable tbody tr").empty();
	
	var objs = $("input[name='propertyCheck']");
	for(var i = 0; i < objs.length; i++) {
		var obj = $(objs[i]);
		
		var checked = obj.is(":checked");
		
		var vals = obj.val().split("_");
		var propName = vals[0];
		var propId = vals[1];
		
		if(checked) {
			if($("#select_"+propId).length == 0) {
				$("#propertyEditTable thead tr").append("<th style='text-align:center;'><input type='hidden' id='th_"+propId+"'/>"+propName+"</th>");
				$("#propertyEditTable tbody tr").append("<td align=\"center\">&nbsp;"+buildPropvalSelect(propId)+"&nbsp;</td>");
			}
		}
	}
	//隐藏和显示商品属性值表格
	showOrHidePropertyEditTable();
};

//隐藏和显示商品属性值表格
var showOrHidePropertyEditTable = function() {
	var propValSelectCount = $("#propertyEditTable tbody tr td").length; 
	if(propValSelectCount > 0) {
		$("#propValSpan").parent().parent().show();
	} else {
		$("#propValSpan").parent().parent().hide();
	}
};

//查询属性值并构建select下拉框
var buildPropvalSelect = function(propId) {
	$("#select_"+propId).empty();
	var options = "";
	$(valInfo).each(function(index) {
		var v = valInfo[index];
		if(v.propId==propId) {
			options += "<option value='"+queryCpp(v.cateId, v.propId, v.valId)+"'>"+v.valName+"</option>";
		}
	});
	return "<select name='select_' id='select_"+propId+"' style=\"width:auto;\">"+options+"</select>"
}

//查询类别-属性-属性值的关系ID
var queryCpp = function(cateId, propId, valId) {
	for(var i = 0; i < cppInfo.length; i++) {
		var cpp = cppInfo[i];
		if(cpp.cateId==cateId && cpp.propId==propId && cpp.valId==valId) {
			return cpp.cppId;
		}
	}
};

//保存按钮
$("#saveBtn").click(function() {
	var marketPrices = $("#goodsChild.marketPrice");
	$(marketPrices).each(function(index) {
		console.log(#(marketPrices[index]).val());
	}); 
	return;
	/* if(!checkRequiredField("goodsName")) {
		return;
	}; */
	
	//验证图片
	var imgSrcCount = 0;
	$(".img").each(function(index) {
		if($(this).attr("src") == undefined || $(this).attr("src").length==0) {
			imgSrcCount++;
		}
	}); 
	if(imgSrcCount==$(".img").length) {
		showLayerMsg("请选择商品图片");
		$("#imgPosition").focus();
		$("#imgPosition").blur();
		return;
	}
});

var checkRequiredField = function(fieldId,msg) {
	if(!msg) {
		msg = $("#"+fieldId).attr("placeholder")+"必填";
	}
	var fieldValue = $("#"+fieldId).val();
	if(fieldValue.length==0) {
		showLayerMsg(msg);
		$("#"+fieldId).focus();
		return false;
	};
	return true;
};

$(".addGoodsAttr").click(function() {
	addGoodsAttribute();
});

var addGoodsAttribute = function() {
	$('<div style="padding: 1px;">参数名：<input class="am-form-field" name="attrName" style="width:150px;display: inline-block;"/>&nbsp;&nbsp;参数值：<input class="am-form-field" name="attrValue" style="width:150px;display: inline-block;"/>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="javascript:removeGoodsAttribute(this);"><span class="am-icon-minus-square" style="width: auto;color:red;font-size: 18px;" title="删除"></span></a><!--&nbsp;&nbsp;<a href="javascript:void(0);" onclick="javascript:addGoodsAttribute();">添加</a>--></div>').appendTo("#shuxingDiv");
}

//删除商品参数
var removeGoodsAttribute = function(but) {
	var elem = but.parentNode;
	var elemParent = elem.parentNode
	elemParent.removeChild(elem);
}