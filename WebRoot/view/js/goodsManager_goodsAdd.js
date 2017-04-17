//-----------------------------------------------begin 实例化编辑器------------------------------------------------
var tbr = {toolbars: [[
                       'fullscreen', 'source', '|', 'undo', 'redo', '|',
                       'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                       'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                       'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                       'directionalityltr', 'directionalityrtl', 'indent', '|',
                       'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                       'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                       'simpleupload', 'emotion', 'attachment', 'map', 'pagebreak', 'template', 'background', '|',
                       'horizontal', 'date', 'time', 'spechars', 'wordimage', '|',
                       'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
                       'preview', 'searchreplace', 'help'
                   ]]};
//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('pceditor',tbr);
var mue = UE.getEditor('mobileeditor',tbr);
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
	UE.getEditor('pceditor').execCommand("clearlocaldata");
};

//判断编辑器是否有内容
var hasContent = function() {
	return UE.getEditor('pceditor').hasContents();
};

var getContent = function() {
    return UE.getEditor('pceditor').getContent();
};

UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
	if (action == 'uploadimage') {
        return path_ + "/view/shop/admin/ueImageUpload!upload.action";
    } else {
        return this._bkGetActionUrl.call(this, action);
    }
}
//-----------------------------------------------END 实例化编辑器--------------------------------------------------

//-----------------------------------------------BEGIN 加载layUI的laydate组件-------------------------------------
//加载layui的laydate组件
layui.use('laydate', function(){});
//-----------------------------------------------END 加载layUI的laydate组件---------------------------------------

//-----------------------------------------------BEGIN 业务开始--------------------------------------------------
$(function() {
	//回显接收到的参数
	$("#goodsId").val(goodsId);
	$("#cateId").val(cateId);
	$("#cateName").html(cateName);
	
	if(goodsId=="") { //新增
		initGoodsForAdd();
	} else { //编辑
		initGoodsForEdit();
	}
});

//编辑商品-初始化商品信息
var initGoodsForEdit = function() {
	showLoading();
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/goodsManager/goodsInfoAction!loadGoods.action",
		dataType : "json",
		data: {"goodsVO.id":goodsId},
		success : function(json) {
			cateId = json.goodsMap.cateId;
			//回显页面值
			setPage(json.goodsMap,json.goodsImgList,json.childGoodsList);
			
			//查询类别下的品牌
			
			//查询类别属性属性值
			queryCatePropPropVal();
		},
		error: function(e){
			console.log("查询类别属性属性值异常");
			closeLoading();
		} 
	});
};

//回显页面值
var setPage = function(json,goodsImgList,childGoodsList) {
	//回显基本信息
	$("#cateId").val(json.cateId);
	$("#cateName").html(json.cateName);
	$("#title").val(json.title);
	$("#subTitle").val(json.subTitle);
	$("#purchasePrice").val(json.purchasePrice);
	$("#marketPrice").val(json.marketPrice);
	$("#salePrice").val(json.salePrice);
	$("#promotionPrice").val(json.promotionPrice);
	$("#availableNum").val(json.availableNum);
	$("#goodsNo").val(json.goodsNo);
	$(":radio[name='goods.emptyStore'][value='" + json.emptyStore + "']").attr("checked", "checked"); //attr和prop都有效
	$(":radio[name='goods.freightType'][value='" + json.freightType + "']").attr("checked", "checked"); //attr和prop都有效
	$(":radio[name='goods.publishType'][value='" + json.publishType + "']").prop("checked", "checked"); //attr和prop都有效
	$(":radio[name='goods.virtual'][value='" + json.virtual + "']").attr("checked", "checked"); //attr和prop都有效
	$("#publishTime").val(json.publishTime==null?"":json.publishTime.replace("T"," "));
	
	//回显图片
	var imgIdHiddens = $("input[name='imgIdHidden']");
	for (var i = 0; i < goodsImgList.length; i++) {
		var goodsImg = goodsImgList[i];
		var $img = $(imgIdHiddens[i]);
		$img.val(goodsImg.imageId);
		$img.prev().attr("src",imageServer_+"/"+goodsImg.imgPath+"/"+goodsImg.fileName);
	}
	
	//回显详情富文本控件
	ue.addListener("ready", function() {
    	// editor准备好之后才可以使用
    	ue.setContent(json.detail);
    });
	
	//回显参数列表
	var paramsArr = JSON.parse(json.params);
	if(paramsArr.length > 0) {
		//生成参数input
		for (var i = 1; i < paramsArr.length; i++) {
			addGoodsParams();
		}
		//填充参数
		var paramNames = $("input[name='paramName']");
		var paramVals = $("input[name='paramVal']");
		for (var i = 0; i < paramsArr.length; i++) {
			var p = paramsArr[i];
			$(paramNames[i]).val(p.paramName);
			$(paramVals[i]).val(p.paramVal);
		}
	}
	
	//回显子商品
	if(childGoodsList != undefined && childGoodsList != null && childGoodsList.length > 0) {
		var len = childGoodsList.length;
		for (var i = len-1; i >= 0; i--) {
			var childGoods = childGoodsList[i];
			var propNames = childGoods.propNames.split(",");
			var valNames = childGoods.valNames.split(",");
			
			var childGoodsLabel = "";
			var br = "";
			var split = "";
			for (var j = 0; j < propNames.length; j++) {
				childGoodsLabel += br + "&nbsp;<strong>" + propNames[j] + "</strong>：" + valNames[j] + "&nbsp;";
				br = "<br/>";
				split = "_";
			}
			//添加子商品
			prePendGoodsChildTable(childGoods.existsGoodsKey, childGoods.existsGoodsKey, childGoodsLabel, childGoods);
		}
	} 
	//隐藏和显示商品价格、库存、货号信息
	showOrHideGoodsPriceTable();
	//无库存销售 （等于 是 则隐藏库存必填标志；等于 否 则显示库存必填标志）
	showOrHideRepRequired(json.emptyStore);
	closeLoading();
}

//新增商品-初始化商品信息
var initGoodsForAdd = function() {
	//查询类别下的品牌
	//查询类别属性属性值
	queryCatePropPropVal();
};

//var cateInfo = new Array(); //类别列表
var propInfo = new Array(); //属性列表
var valInfo = new Array(); //属性值列表
var cppInfo = new Array(); //类别-属性-属性值关系列表

//查询类别属性属性值
var queryCatePropPropVal = function() {
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/catePropPropval/catePropPropval!queryByCateId.action",
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
	
	var existsGoodsKey = "";
	var childGoodsLabel = "";
	//var childGoodsValue = "";
	
	var br = "";
	var split = "";
	for (var i = 0; i < selectLength; i++) {
		var sel = selectList[i];
		var cppId = sel.value;
		var valName = sel.options[sel.selectedIndex].text;
		var propName = $("#propertyEditTable thead tr th").eq(i).text();
		
		existsGoodsKey += split + cppId;
		childGoodsLabel += br + "&nbsp;<strong>" + propName + "</strong>：" + valName + "&nbsp;";
		//childGoodsValue += split + cppId + ":" + propName + ":" + valName;
		
		br = "<br/>";
		split = "_";
	}
	
	//根据标识检测是否填过相同的子商品
	if($("#"+existsGoodsKey).length == 0) {
		prePendGoodsChildTable(existsGoodsKey, existsGoodsKey, childGoodsLabel, null);
	} else {
		showLayerMsg("该商品已经添加！");
	}
	//隐藏和显示商品价格、库存、货号信息
	showOrHideGoodsPriceTable();
});

//添加子商品
var prePendGoodsChildTable = function(existsGoodsKey, childGoodsValue, childGoodsLabel, childGoods) {
	var purchasePrice="",marketPrice="",salePrice="",promotionPrice="",availableNum="",childNo="",pkId="";
	if(childGoods!=undefined && childGoods!=null){
		purchasePrice = childGoods.purchasePrice;
		marketPrice = childGoods.marketPrice;
		salePrice = childGoods.salePrice;
		promotionPrice = childGoods.promotionPrice;
		availableNum = childGoods.availableNum;
		childNo = childGoods.childNo;
		pkId = childGoods.id;
	}
	var trHtml = "";
	trHtml += "<tr>";
	trHtml += "    <td style='text-align:left;'><input type='hidden' id='"+existsGoodsKey+"' value='"+childGoodsValue+"'/><div style='margin-top:5px;margin-bottom:5px;'>"+childGoodsLabel+"</div></td>";
	trHtml += "    <td>&nbsp;<input name='purchasePriceInput' onblur='javascript:checkChildGoodsFieldThis(this);' style=\"width:80px;\" value='"+purchasePrice+"'/>&nbsp;</td>";
	trHtml += "    <td>&nbsp;<input name='marketPriceInput' onblur='javascript:checkChildGoodsFieldThis(this);' style=\"width:80px;\" value='"+marketPrice+"'/>&nbsp;</td>";
	trHtml += "    <td>&nbsp;<input name='salePriceInput' onblur='javascript:checkChildGoodsFieldThis(this);' style=\"width:80px;\" value='"+salePrice+"'/>&nbsp;</td>";
	trHtml += "    <td>&nbsp;<input name='promotionPriceInput' onblur='javascript:checkChildGoodsFieldThis(this);' style=\"width:80px;\" value='"+promotionPrice+"'/>&nbsp;</td>";
	trHtml += "    <td>&nbsp;<input name='availableNumInput' onblur='javascript:checkChildGoodsAvailableNumFieldThis(this);' style=\"width:80px;\" value='"+availableNum+"'/>&nbsp;</td>";
	trHtml += "    <td>&nbsp;<input name='childNoInput' style=\"width:80px;\" value='"+childNo+"'/>&nbsp;</td>";
	trHtml += "    <td>&nbsp;<a href='javascript:void(0)' class='am-text-danger' onclick='deleteGoodChilds(\""+existsGoodsKey+"\")'><span class='am-icon-remove'></i>删除</a>&nbsp;";
	trHtml += "    <input type='hidden' value='"+pkId+"'/></td>";
	trHtml += "</tr>";
	$("#goodsChildTable tbody").prepend(trHtml);
};

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

//验证子商品列表中文本框的必填（根据name属性判断一组同名的字段）
var checkChildGoodsFieldsRequired = function(fieldName) {
	var fields = $("input[name='"+fieldName+"']");
	var bool = true;
	for(var i = 0; i < fields.length; i++) {
		var field = $(fields[i]);
		/*var tdIndex = field.parent().index();
		var headText = field.parent().parent().parent().prev().children("tr:eq(0)").children("th:eq("+tdIndex+")").text().replace("*","");*/
		if(field.val().length==0) {
			bool = false;
			field.addClass("inputerror");
			/*showLayerMsg("请输入" + headText);
			field.focus();*/
		} else {
			field.removeClass("inputerror");
		}
	}
	return bool;
}

//验证子商品列表中文本框的是数字（根据name属性判断一组同名的字段，对没填的不验证正确性）
var checkChildGoodsFieldsNumber = function(fieldName) {
	var numReg = /^[1-9]\d*$/;　　//正整数
	
	//如果无库存销售为是，则可以输入非负整数（正整数 + 0）
	var emptyStore = $('input:radio[name="goods.emptyStore"]:checked').val();
	if(emptyStore.length > 0 && emptyStore==$("#emptyStore1").val()) {
		numReg = /^[0-9]\d*$/;
	}
	
	var fields = $("input[name='"+fieldName+"']");
	var bool = true;
	for(var i = 0; i < fields.length; i++) {
		var field = $(fields[i]);
		if(field.val().length > 0) {
			if(!numReg.test(field.val())) {
				bool = false;
				field.addClass("inputerror");
			} else {
				field.removeClass("inputerror");
			}
		}
	}
	return bool;
}

$("#publishTime").click(function() {
	layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
});

//添加商品参数按钮
$(".addGoodsParam").click(function() {
	addGoodsParams();
});

//添加商品参数函数
var addGoodsParams = function() {
	$('<div style="padding: 1px;">参数名：<input class="am-form-field" name="paramName" style="width:150px;display: inline-block;"/>&nbsp;&nbsp;参数值：<input class="am-form-field" name="paramVal" style="width:150px;display: inline-block;"/>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="javascript:removeGoodsParams(this);"><span class="am-icon-minus-square" style="width: auto;color:red;font-size: 18px;" title="删除"></span></a><!--&nbsp;&nbsp;<a href="javascript:void(0);" onclick="javascript:addGoodsParams();">添加</a>--></div>').appendTo("#paramsDiv");
};

//删除商品参数函数
var removeGoodsParams = function(but) {
	var elem = but.parentNode;
	var elemParent = elem.parentNode
	elemParent.removeChild(elem);
};

//验证子商品必填字段
var checkChildGoodsFieldThis = function(obj) {
	var $obj = $(obj);
	if($obj.val().length > 0) {
		$obj.removeClass("inputerror");
	} else {
		$obj.addClass("inputerror");
	}
};

//验证子商品（库存）字段
var checkChildGoodsAvailableNumFieldThis = function(obj) {
	var emptyStore = $('input:radio[name="goods.emptyStore"]:checked').val();
	if(emptyStore.length > 0) {
		//如果无库存销售为否，则验证必填
		if(emptyStore==$("#emptyStore2").val()) {
			checkChildGoodsFieldThis(obj);
		}
		checkChildGoodsFieldsNumber("availableNumInput");
	} else {
		$(obj).removeClass("inputerror");
	}
};

//提交表单
$("#saveBtn").click(function() {
	//表单验证
	if(!checkPageData()) 
		return;
	
	//获取页面数据
	var data = getPageData();
	
	//显示进度条
	showLoading();
	
	//ajax保存
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/goodsManager/goodsInfoAction!saveGoods.action",
		dataType : "json",
		data: data,
		success : function(json) {
			if(json.goods.id != null) {
				//隐藏进度条
				closeLoading();
				
				$("#goodsId").val(json.goods.id);
				showMsg("操作成功！");
				
				if(goodsId != "") { //编辑
					closeTab("editGoodsTabId"+goodsId);
				} else { //关闭新增
					closeThisTab();
				}
			}
		}, error: function(e) {
			console.log("error");
			//隐藏进度条
			closeLoading();
		} 
	});
});

$("#closeBtn").click(function() {
	showConfirm("确定取消？",function(){
		showMsg("取消成功");
		closeThisTab();
	});
});

//选择放入仓库和立即发布时，将发布时间置空
$("#publishType1,publishType2").click(function() {
	$("#publishTime").val("");
});

$("#emptyStore1,#emptyStore2").click(function() {
	showOrHideRepRequired(this.value);
});

//无库存销售 （等于 是 则隐藏库存必填标志；等于 否 则显示库存必填标志）
var showOrHideRepRequired = function(val) {
	if(val==$("#emptyStore1").val()) {
		$("#goodsChildTable thead tr th").eq(5).children("span:eq(0)").hide();
		//去除库存必填样式
		var availableNumInputs = $("input[name='availableNumInput']");
		for(var i = 0; i < availableNumInputs.length; i++) {
			$(availableNumInputs[i]).removeClass("inputerror");
		}
	} else if(val==$("#emptyStore2").val()) {
		$("#goodsChildTable thead tr th").eq(5).children("span:eq(0)").show();
		//验证子商品必填
		checkChildGoodsFieldsRequired("availableNumInput");
	}
	//验证填写的库存值的正确性
	checkChildGoodsFieldsNumber("availableNumInput");
};

var checkRequiredField = function(fieldId,msg) {
	var $field = $("#"+fieldId);
	if(!msg) {
		msg = $field.attr("placeholder")+"必填";
	}
	var fieldValue = $("#"+fieldId).val();
	if(fieldValue.length==0) {
		showLayerMsg(msg);
		$field.focus();
		return false;
	};
	return true;
};

var checkPageData = function() {
	if(!checkRequiredField("title")) {
		return false;
	}; 
	
	var emptyStore = $('input:radio[name="goods.emptyStore"]:checked').val();
	if(emptyStore == undefined) {
		showLayerMsg("请选择无库存销售");
		$("#emptyStore1").focus();
		$("#emptyStore1").blur();
		return false;
	}
	
	//子商品数量
	var goodsChildCount = $("#goodsChildTable tbody tr").length; 
	if(goodsChildCount > 0) { //有子商品，则验证子商品填写的完整性
		//同时验证子商品的价格信息，用css标红
		var b1 = checkChildGoodsFieldsRequired("purchasePriceInput");
		var b2 = checkChildGoodsFieldsRequired("marketPriceInput");
		var b3 = checkChildGoodsFieldsRequired("salePriceInput");
		var b4 = checkChildGoodsFieldsRequired("promotionPriceInput");
		var b5 = true;
		if(emptyStore==$("#emptyStore2").val()) { //无库存销售为否，则验证库存的必填
			b5 = checkChildGoodsFieldsRequired("availableNumInput");
		}
		
		if(!b1 || !b2 || !b3 || !b4 || !b5) {
			showLayerMsg("请输入完整库存配置信息");
			$(".inputerror:first").focus(); 
			return false;
		}
		
		//填写了库存，则验证库存的正确性
		var b6 = checkChildGoodsFieldsNumber("availableNumInput");
		if(!b6) {
			showLayerMsg("请输入正确的库存值");
			$(".inputerror:first").focus(); 
			return false;
		}
	} else { //有子商品，则验证商品价格和库存字段必填
		if(!checkRequiredField("purchasePrice")) {
			return false;
		}; 
		
		if(!checkRequiredField("marketPrice")) {
			return false;
		}; 
		
		if(!checkRequiredField("salePrice")) {
			return false;
		}; 
		
		if(!checkRequiredField("promotionPrice")) {
			return false;
		}; 
		
		if(!checkRequiredField("availableNum")) {
			return false;
		};
	}
	
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
		return false;
	}
	
	if(!hasContent()) {
		showLayerMsg("请填写详情！");
		$("#tab1").focus();
		return false;
	}
	
	var freightType = $('input:radio[name="goods.freightType"]:checked').val();
	if(freightType == undefined) {
		showLayerMsg("请选择运费");
		$("#freightType1").focus();
		$("#freightType1").blur();
		return false;
	}
	
	var publishType = $('input:radio[name="goods.publishType"]:checked').val();
	if(publishType == undefined) {
		showLayerMsg("请选择商品发布");
		$("#publishType1").focus();
		$("#publishType1").blur();
		return false;
	} 
	
	if(publishType==$("#publishType3").val()) {
		var publishTime = $("#publishTime").val();
		if(publishTime.length==0) {
			showLayerMsg("请选择发布时间");
			$("#publishTime").focus();
			$("#publishTime").blur();
			return false;
		}
	}
	
	var virtual = $('input:radio[name="goods.virtual"]:checked').val();
	if(virtual == undefined) {
		showLayerMsg("请选择是否虚拟商品");
		$("#virtual1").focus();
		$("#virtual1").blur();
		return false;
	}
	
	return true;
};

//组装页面数据
var getPageData = function() {
	//得到子商品信息串
	var trs = $("#goodsChildTable tbody tr");
	var childGoods = new Array();
	for (var i = 0; i < trs.length; i++) {
		var $tr = $(trs[i]);
		var tds = $tr.children("td");
		
		var cppvStr = $(tds[0]).children("input:eq(0)").val();
		var purchasePrice = $(tds[1]).children("input:eq(0)").val();
		var marketPrice = $(tds[2]).children("input:eq(0)").val();
		var salePrice = $(tds[3]).children("input:eq(0)").val();
		var promotionPrice = $(tds[4]).children("input:eq(0)").val();
		var availableNum = $(tds[5]).children("input:eq(0)").val();
		var goodsNo = $(tds[6]).children("input:eq(0)").val();
		var pkId = $(tds[7]).children("input:eq(0)").val();
		
		var tempjson = {"cppvStr":cppvStr,"purchasePrice":purchasePrice,
						"marketPrice":marketPrice,"salePrice":salePrice,
						"promotionPrice":promotionPrice,"availableNum":availableNum,
						"goodsNo":goodsNo,"pkId":pkId};
		childGoods.push(tempjson);
	}
	
	//得到图片
	var imgIdHiddens = $("input[name='imgIdHidden']");
	var imgs = "",m = "";
	for (var i = 0; i < imgIdHiddens.length; i++) {
		var $img = $(imgIdHiddens[i]);
		if($img.val().length==0)
			continue;
		imgs += m + $img.val();
		m = ",";
	}
	
	//获得参数
	var params = new Array();
	var paramNames = $("input[name='paramName']");
	var paramVals = $("input[name='paramVal']");
	if(paramNames.length==paramVals.length) {
		for (var i = 0; i < paramNames.length; i++) {
			var $paramName = $(paramNames[i]);
			var $paramVal = $(paramVals[i]);
			if($paramName.val().length > 0 && $paramVal.val().length > 0) {
				params.push({"paramName":$paramName.val(),"paramVal":$paramVal.val()});
			}
		}
	}

	var data = {
		"goods.id" : $("#goodsId").val() ,
		"goods.cateId" : $("#cateId").val() ,
		"goods.brandId" : $("#brandId").val() ,
		"goods.title" : $("#title").val() ,
		"goods.subTitle" : $("#subTitle").val() ,
		"goods.purchasePrice" : $("#purchasePrice").val() ,
		"goods.marketPrice" : $("#marketPrice").val() ,
		"goods.salePrice" : $("#salePrice").val() ,
		"goods.promotionPrice" : $("#promotionPrice").val() ,
		"goods.availableNum" : $("#availableNum").val() ,
		"goods.goodsNo" : $("#goodsNo").val() ,
		"goods.freightType" : $('input:radio[name="goods.freightType"]:checked').val() ,
		"goods.publishType" : $('input:radio[name="goods.publishType"]:checked').val() ,
		"goods.freightId" : $("#freightId").val() ,
		"goods.publishTime" : $("#publishTime").val() ,
		"goods.childGoods" : JSON.stringify(childGoods),
		"goods.imgs" : imgs,
		"goods.params" : JSON.stringify(params),
		"goods.detail" : getContent(),
		"goods.emptyStore" : $('input:radio[name="goods.emptyStore"]:checked').val(),
		"goods.virtual" : $('input:radio[name="goods.virtual"]:checked').val()
	};
	
	return data;
};
//-----------------------------------------------BEGIN 业务结束------------------------------------------------------