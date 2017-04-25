<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String orderId = request.getParameter("orderId");
request.setAttribute("orderId", orderId);
String orderCode = request.getParameter("orderCode");
request.setAttribute("orderCode", orderCode);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>确认订单</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style>
  /* th{
	text-align:center;
  } */
</style>
</head>
<body>
<div class="am-cf">
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-g">
      			<div class="am-fl am-cf">
      				<table>
      					<tr>
      						<td>${orderCode }：</td>
      						<td>根据库存是否足够拆分子订单<input type='hidden' id='checkOrders'/></td>
      					</tr>
      				</table>
				</div>
   				<div style="width: 100%;height: 310px;overflow: auto;">
   					<input id="operate" type="hidden" disabled="disabled"/>
        			<input id="orderId" type="hidden" disabled="disabled" value='${orderId }'/>
        			<input id="orderCode" type="hidden" disabled="disabled" value='${orderCode }'/>
		       		<table class="am-table am-table-bordered am-table-striped am-table-hover" id="dataList">
		        		<thead>
			          		<tr>
			            		<th id="index" style="">序号</th>
			            		<th id="checkbox">选择</th>
								<th id="goodsName">商品名称</th>
								<th id="count">数量</th>
								<!-- <th id="propNames">属性</th>
								<th id="valNames">属性值</th> -->
								<th id="availableNum">库存</th>
			          		</tr>
		              	</thead>
		              	<tbody>
				        </tbody>
	       	 		</table>
	            </div>
	            <!-- <div class="am-u-sm-12">
					<div class="am-cf">共<span id="rowCount"></span>条记录<div id="page" class="am-fr"></div></div>
				</div> -->
            	<div align="center">
					<button type="button" class="am-btn am-btn-success" id="confirmBtn"><span class="am-icon-check"></span>确认</button>
					<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span>关闭</button>
				</div>
      		</div>
    	</div>
  	</div>
</div>
</body>
<script type="text/javascript">
	$(function() {
		//$('#modalTitle').text("确认订单");
		//$('#orderId').val(id);
		//$('#operate').val("confirm");
		queryOrderGoods();
	});
	var queryOrderGoods = function(){
		var data = {"order.id":$('#orderId').val()};
		$.ajax({
			url : path_ + "/view/shop/order/orderDetail!queryConfirmOrder.action",
			type : 'POST',
			data : data,
			dataType: "json",
			success : function(data) {
				console.log(data.orderGoods);
				appendHtml(data.orderGoods);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert('e:' + XMLHttpRequest.responseText);
			}
		});
	}

	var appendHtml = function(orderGoods){
		var tbody = $('#dataList tbody');
		var html = "";
		for (var i in orderGoods) {
			html += "<tr>"
			html += "<td style='vertical-align: middle;text-align:center'>" + (parseInt(i)+1) + "</td>";
			html += "<td style='vertical-align: middle;text-align:center'>";
			var str = "";
			if(orderGoods[i].childGoodsId==null){
				str = orderGoods[i].goodsId;
			}else if(orderGoods[i].childGoodsId!=null){
				str = orderGoods[i].childGoodsId;
			}
			html += "<input id='checkbox"+i+"' name='goodsCheck' type='checkbox' onclick='checkOrder(\""+str+"\",\"checkbox"+i+"\")'/></td>";
			var valName = orderGoods[i].valNames==null?"":"</br>[" + orderGoods[i].valNames + "]";
			html += "<td>" + orderGoods[i].goodsName + valName + "</td>";
			html += "<td style='vertical-align: middle;text-align:center'>" + orderGoods[i].count + "</td>";
			html += "<td style='vertical-align: middle;text-align:center'>" + orderGoods[i].availableNum + "</td>";
			html += "</tr>"
		}
		tbody.append(html);
	}
	
	var checkOrder = function(str, checkboxId){
		var checkOrders = $("#checkOrders").val();
		var arr = checkOrders.split(",");
		var checkOrdersNew = "";
		var isCheck = $("#"+checkboxId).attr('checked');
		if(isCheck=="checked"){
			checkOrdersNew += str + ",";
		}
		for(x in arr){
			if(arr[x]!="" && arr[x]!=null && arr[x]!=undefined && arr[x]!=str){
				checkOrdersNew += arr[x];
				checkOrdersNew += ",";
			}
		};
		$("#checkOrders").val(checkOrdersNew);
	}

	//确认订单（修改该订单以及子订单的orderStatus、拆分子订单）
	$('#confirmBtn').click(function() {
		var data = {"order.id":$('#orderId').val(),"splitOrderStr":$('#checkOrders').val()};
		var url = path_ + "/view/shop/order/order!confirmOrder.action";
		$.ajax({
			type : "POST",
			url : url,
			data : data,
			dataType : "json",
			success : function(data) {
				if(data.msg!=null && data.msg!=""){
					showAlert(data.msg);
					closeCurrentWin();
				}else{
					showAlert("操作成功");
					closeCurrentWin();
				}
			},
			error : function(e) {
				showAlert("操作失败！");
			}
		});
	});
	
	var closeCurrentWin = function() {
		window.parent.closeParamsModal();
	};
	
	$("#closeBtn").click(closeCurrentWin);
</script>
</html>