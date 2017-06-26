var formatterAuditSts = function(value, row) {
	var html = "";
	if(value=="1") {
		html = "待审核";
	} else if(value=="2") {
		html = "通过";
	} else if(value=="3") {
		html = "不通过";
	}
	return html;
};

var formatterGoodsSts = function(value, row) {
	var html = "";
	if(value=="1") {
		html = "入库";
	} else if(value=="2") {
		html = "定时上架";
	} else if(value=="3") {
		html = "上架";
	} else if(value=="4") {
		html = "下架";
	}
	return html;
};

var showEditGoodsTab = function(goodsId) {
	openTab("editGoodsTabId_"+goodsId,"编辑商品",path_+"/view/shop/goodsManager/goodsAdd.jsp?tabId=editGoodsTabId_"+goodsId+"&goodsId="+goodsId);
};

var showGoodsDetailTab = function(goodsId) {
	openTab("detailGoodsTabId_"+goodsId,"商品详情",path_+"/view/shop/goodsManager/goodsDetail.jsp?tabId=detailGoodsTabId_"+goodsId+"&goodsId="+goodsId);
};