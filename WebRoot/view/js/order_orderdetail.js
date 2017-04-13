$(function() {
	initOrder();
});

var initOrder = function(){
	var data = {"order.id" : $('#orderId').val()};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/order/order!queryOrderById.action",
		data : data,
		dataType : "json",
		success : function(data) {
			clearOrderInfo();
			setOrderInfo(data);
		}
	});
}

/*$("#closeBtn").click(function() {
	closeModal("doc-modal-2");
});*/

var clearOrderInfo = function() {
	var liArr = $('ul li span');
	liArr.each(function(){
		$(this).text(" ");
	});
};

var setOrderInfo = function(data) {
	var orderMap = data.orderMap;
	$('ul li span').each(function(){
		$("#" + this.id).text(orderMap[this.id]);
	});
	setSonOrdersInfo(data);
};

//拼子订单信息和发票信息
var setSonOrdersInfo = function(data){
	var orderMap = data.orderMap;
	var sonOrders = data.sonOrders;
	var orderGoods = data.orderGoods;
	console.log(orderMap);
	console.log(sonOrders);
	console.log(orderGoods);
	if(sonOrders[0]==undefined){
		var temp = "<ul><li style='width: 100%;'>无子订单</li></ul>";
		$('#sonOrdersInfo_td').append(temp);
	}
	var invoice = orderMap.invoice;
	var invoiceRecordId = orderMap.invoiceRecordId;
	var html = "";
	//invoice==0	不开发票
	if(invoice=='0'){
		html += "<ul><li style='width: 100%;'>不开发票</li></ul>";
	}
	//异常情况。关联不到发票信息。
	if(invoice=='1' && sonOrders[0]==undefined && invoiceRecordId==null){
		html += "<ul><li style='width: 100%;'>未关联到发票信息</li></ul>";
	}
	//invoice==1	且	子订单sonOrders为空		且	父订单invoiceRecordId不为空	开单张发票
	if(invoice=='1' && sonOrders[0]==undefined && invoiceRecordId!=null){
		html += "<ul>"
		html += "<li class='prop_li'>订单编号：<span id=''>" + orderMap.orderCode + "</span></li>";
		html += "<li class='prop_li'>发票编号：<span id='invoiceCode'>" + orderMap.invoiceCode + "</span></li>";
		html += "<li class='prop_li'>抬头：<span id='header'>" + orderMap.header + "</span></li>";
		html += "<li class='prop_li'>姓名：<span id='invoiceName'>" + orderMap.invoiceName + "</span></li>";
		html += "<li class='prop_li'>发票内容：<span id='content'>" + orderMap.content + "</span></li>";
		html += "<li class='prop_li'>备注：<span id='remark'>" + orderMap.remark + "</span></li>";
		html += "</ul>"
	}
	//invoice==1	且	子订单sonOrders不为空	且	父订单invoiceRecordId为空	开多张发票
	if(invoice=='1' && sonOrders[0]!=undefined && invoiceRecordId==null){
		var temp = "";
		for (var i in sonOrders) {
			temp += "<ul>"
			temp += "<li class='prop_li'>订单编号：<span id=''>" + sonOrders[i].orderCode + "</span></li>";
			temp += "<li class='prop_li'>订单状态：<span id=''>" + sonOrders[i].orderStatus + "</span></li>";
			temp += "<li class='prop_li'>订单金额：<span id=''>" + sonOrders[i].totalPrice + "</span></li>";
			temp += "<li class='prop_li'>运费：<span id=''>" + sonOrders[i].commisionCharge + "</span></li>";
			temp += "<li class='prop_li'>支付金额：<span id=''>" + sonOrders[i].actualPrice + "</span></li>";
			temp += "<li class='prop_li'>件数：<span id=''>" + sonOrders[i].num + "</span></li>";
			temp += "</ul>"
			
			html += "<ul>"
			html += "<li class='prop_li'>订单编号：<span id=''>" + sonOrders[i].orderCode + "</span></li>";
			html += "<li class='prop_li'>发票编号：<span id='invoiceCode'>" + sonOrders[i].invoiceCode + "</span></li>";
			html += "<li class='prop_li'>抬头：<span id='header'>" + sonOrders[i].header + "</span></li>";
			html += "<li class='prop_li'>姓名：<span id='invoiceName'>" + sonOrders[i].invoiceName + "</span></li>";
			html += "<li class='prop_li'>发票内容：<span id='content'>" + sonOrders[i].content + "</span></li>";
			html += "<li class='prop_li'>备注：<span id='remark'>" + sonOrders[i].remark + "</span></li>";
			html += "</ul>"
			if(i!=sonOrders.length-1){
				temp += "<hr>";
				html += "<hr>";
			}
		}
		$('#sonOrdersInfo_td').append(temp);
	}
	$('#invoiceInfo_td').append(html);
	setGoodsInfo(orderGoods);
}

//拼商品信息
var setGoodsInfo = function(orderGoods){
	var html = "";
	if(orderGoods[0]==undefined || orderGoods[0]==null){
		html += "<ul><li style='width: 100%;'>未关联到商品信息</li></ul>";
	}else{
		html += "<table style='width:100%; vertical-align:middle;'><tbody>"
		for (var i in orderGoods) {
			html += "<tr>";
			html += "<th></th>";
			html += "<th>商品名称</th>";
			html += "<th>单价</th>";
			html += "<th>件数</th>";
			html += "<th>小计</th>";
			html += "</tr><tr>";
			html += "<td style='width:10%;'><div><span class=''><a href='javascript:void(0);' target='_blank'><img src=''/>" + orderGoods[i].imgPath + orderGoods[i].fileName + "</a></span></div></td>";
			html += "<td><a href='' target='_blank'>" + orderGoods[i].goodsName + "</a></td>";
			html += "<td><span class='red_common'>￥" + orderGoods[i].unitPrice + "</span></td>";
    		/*<script type="text/javascript">
				var price = number_format(10488,2);
				document.write(price);
			</script>*/
			html += "<td>" + orderGoods[i].count + "</td>";
			html += "<td><span class='red_common'>￥" + orderGoods[i].amount + "</span></td>";
			/*<script type="text/javascript">
				var payprice = number_format(20976,2);
				document.write(payprice);
			</script>*/
            html += "</tr>"
		}
		html += "</table></tbody>"
	}
	$('#goodsInfo_td').append(html);
}
