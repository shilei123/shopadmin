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