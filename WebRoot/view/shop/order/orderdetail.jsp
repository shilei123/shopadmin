<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>订单管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style>
  #vld-tooltip {
    position: absolute;
    z-index: 1000;
    padding: 5px 10px;
    background: #F37B1D;
    min-width: 150px;
    color: #fff;
    transition: all 0.15s;
    box-shadow: 0 0 5px rgba(0,0,0,.15);
    display: ;
  }

  #vld-tooltip:before {
    position: absolute;
    top: -8px;
    left: 50%;
    width: 0;
    height: 0;
    margin-left: -8px;
    content: "";
    border-width: 0 8px 8px;
    border-color: transparent transparent #F37B1D;
    border-style: none inset solid;
  }
</style>
</head>
<body>
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-g">
					<div class="am-u-sm-12 page-table-main">
						<table class="am-table am-table-bordered am-table-striped am-table-hover">
							<tr>
								<td colspan="4" style="background-color: '#e2e2e2';">订单状态</td>
							</tr>
							<tr>
				        		<td class="table_title">订单编号：</td>
				        		<td>
				        			<!-- <input id="orderCode2" class="am-form-field" style="width:90%" disabled="disabled"/> -->
				        			<span id='span_order_code'></span>
				        		</td>
				        		<td class="table_title">订单状态：</td>
				        		<td>
				        			<!-- <input id="deliveryMode2" class="am-form-field" style="width:90%" disabled="disabled"/> -->
				        			<span id='span_order_sts'></span>
				        		</td>
				        	</tr>
				        	<tr>
				        		<td class="table_title">订单金额：</td>
				        		<td>
				        			<span id='span_total_price'></span>
				        			<!-- <input id="orderStatus2" class="am-form-field" style="width:90%" disabled="disabled"/> -->
				        		</td>
				        		<td class="table_title">运费：</td>
				        		<td>
				        			<span id='span_commision_charge'></span>
				        			<!-- <input id="" class="am-form-field" style="width:90%" disabled="disabled" placeholder="暂未关联"/> -->
				        		</td>
				        	</tr>
				        	<tr>
				        		<td class="table_title">支付金额：</td>
				        		<td>
				        			<span id='span_actual_price'></span>
				        		</td>
				        		<td class="table_title"></td>
				        		<td>
				        			<!-- <span id='span_commision_charge'></span> -->
				        			<!-- <input id="" class="am-form-field" style="width:90%" disabled="disabled" placeholder="暂未关联"/> -->
				        		</td>
				        	</tr>
						</table>
						<table class="am-table am-table-bordered am-table-striped am-table-hover">
							<tr>
								<td colspan="4" style="background-color: '#e2e2e2';">订单状态</td>
							</tr>
							<tr>
				        		<td class="table_title">订单编号：</td>
				        		<td>
				        			<!-- <input id="orderCode2" class="am-form-field" style="width:90%" disabled="disabled"/> -->
				        			<span id='span_order_code'></span>
				        		</td>
				        		<td class="table_title">订单状态：</td>
				        		<td>
				        			<!-- <input id="deliveryMode2" class="am-form-field" style="width:90%" disabled="disabled"/> -->
				        			<span id='span_order_sts'></span>
				        		</td>
				        	</tr>
				        	<tr>
				        		<td class="table_title">订单金额：</td>
				        		<td>
				        			<span id='span_total_price'></span>
				        			<!-- <input id="orderStatus2" class="am-form-field" style="width:90%" disabled="disabled"/> -->
				        		</td>
				        		<td class="table_title">运费：</td>
				        		<td>
				        			<span id='span_commision_charge'></span>
				        			<!-- <input id="" class="am-form-field" style="width:90%" disabled="disabled" placeholder="暂未关联"/> -->
				        		</td>
				        	</tr>
				        	<tr>
				        		<td class="table_title">支付金额：</td>
				        		<td>
				        			<span id='span_actual_price'></span>
				        		</td>
				        		<td class="table_title"></td>
				        		<td>
				        			<!-- <span id='span_commision_charge'></span> -->
				        			<!-- <input id="" class="am-form-field" style="width:90%" disabled="disabled" placeholder="暂未关联"/> -->
				        		</td>
				        	</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/order_orderdetail.js"></script>
</html>