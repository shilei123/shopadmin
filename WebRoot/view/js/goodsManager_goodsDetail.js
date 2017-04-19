//-----------------------------------------------BEGIN 加载layUI的laydate组件-------------------------------------
//加载layui的form组件
layui.use('form', function(){});
//-----------------------------------------------END 加载layUI的laydate组件---------------------------------------

//-----------------------------------------------BEGIN 业务开始--------------------------------------------------
$(function() {
	initGoodsDetail();
});

//编辑商品-初始化商品信息
var initGoodsDetail = function() {
	showLoading();
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/goodsManager/goodsInfoAction!loadGoods.action",
		dataType : "json",
		data: {"goodsVO.id":goodsId},
		success : function(json) {
			//回显页面值
			setPage(json.goodsMap,json.goodsImgList,json.childGoodsList);
			closeLoading();
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
	$("#cateName").html(json.cateName);
	$("#title").html(json.title);
	$("#subTitle").html(json.subTitle);
	var emptyStore = "";
	if(json.emptyStore==$("#emptyStore1").val()) {
		emptyStore = "是";
	} else if(json.emptyStore==$("#emptyStore2").val()) {
		emptyStore = "否";
	}
	$("#emptyStore").html(emptyStore);
	$("#purchasePrice").html(json.purchasePrice);
	$("#marketPrice").html(json.marketPrice);
	$("#salePrice").html(json.salePrice);
	$("#promotionPrice").html(json.promotionPrice);
	$("#availableNum").html(json.availableNum);
	$("#goodsNo").html(json.goodsNo);
	
	var freightType = "";
	if(json.freightType==$("#freightType1").val()) {
		freightType = "卖家承担运费";
	} else if(json.freightType==$("#freightType2").val()) {
		freightType = "买家承担运费";
	}
	$("#freightType").html(freightType);
	
	var publishType = "";
	if(json.publishType==$("#publishType1").val()) {
		publishType = "放入仓库";
	} else if(json.publishType==$("#publishType2").val()) {
		publishType = "立即发布";
	} else if(json.publishType==$("#publishType3").val()) {
		publishType = "发布时间";
	}
	$("#publishType").html(publishType);
	
	var virtual = "";
	if(json.virtual==$("#virtual1").val()) {
		virtual = "是";
	} else if(json.virtual==$("#virtual2").val()) {
		virtual = "否";
	}
	$("#virtual").html(virtual);
	$("#publishTime").html(json.publishTime==null?"":json.publishTime.replace("T"," "));
	
	//回显图片
	for (var i = 0; i < goodsImgList.length; i++) {
		var goodsImg = goodsImgList[i];
		var src = imageServer_+"/"+goodsImg.imgPath+"/"+goodsImg.fileName;
		$("#imagesDiv").append("<div class=\"imgDiv\" style='margin-right:10px;'><img id=\"img1\" alt=\"\" src=\""+src+"\" class=\"img\"></div>");
	}
	
	$("#pcdetail").html(json.detail);
	
	//回显参数列表
	var paramsArr = JSON.parse(json.params);
	if(paramsArr.length > 0) {
		//填充参数
		for (var i = 0; i < paramsArr.length; i++) {
			var p = paramsArr[i];
			$("#paramsDiv").append("<div style='padding: 1px;'><strong>"+p.paramName+"：</strong>"+p.paramVal+"</div>");
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
			prePendGoodsChildTable(childGoodsLabel, childGoods);
		}
	} 
	//隐藏和显示商品价格、库存、货号信息
	showOrHideGoodsPriceTable();
};

var prePendGoodsChildTable = function(childGoodsLabel, childGoods) {
	var purchasePrice="",marketPrice="",salePrice="",promotionPrice="",availableNum="",childNo="",pkId="";
	if(childGoods!=undefined && childGoods!=null){
		purchasePrice = childGoods.purchasePrice;
		marketPrice = childGoods.marketPrice;
		salePrice = childGoods.salePrice;
		promotionPrice = childGoods.promotionPrice;
		availableNum = childGoods.availableNum;
		childNo = childGoods.childNo;
	}
	var trHtml = "";
	trHtml += "<tr>";
	trHtml += "    <td style='text-align:left;'><div style='margin-top:5px;margin-bottom:5px;'>"+childGoodsLabel+"</div></td>";
	trHtml += "    <td>&nbsp;<span>"+purchasePrice+"&nbsp;</td>";
	trHtml += "    <td>&nbsp;<span>"+marketPrice+"&nbsp;</td>";
	trHtml += "    <td>&nbsp;<span>"+salePrice+"&nbsp;</td>";
	trHtml += "    <td>&nbsp;<span>"+promotionPrice+"&nbsp;</td>";
	trHtml += "    <td>&nbsp;<span>"+availableNum+"&nbsp;</td>";
	trHtml += "    <td>&nbsp;<span>"+childNo+"&nbsp;</td>";
	trHtml += "</tr>";
	$("#goodsChildTable tbody").prepend(trHtml);
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

$("#closeBtn").click(function() {
	closeThisTab();
});
//-----------------------------------------------BEGIN 业务结束------------------------------------------------------