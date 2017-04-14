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
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">订单管理</strong> / <small>订单的查看、修改与删除</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<div class="am-panel am-panel-primary">
							<div class="am-panel-hd am-cf " data-am-collapse="{target: '#collapse-panel-1'}">查询条件<span class="am-icon-chevron-down am-fr"></span></div>
							<div class="am-panel-bd am-collapse frame-search-panel m-panel-collapse"id="collapse-panel-1">
								<table id="from_query" class="frame-query-table" border="0" bordercolor="black">
									<tr>
										<td style="width:100px;">运送方式：</td>
										<td style="width:200px;">
											<select name="queryParams.deliveryMode" id="orderDeliveryMode" style="height:32px; width:157px;"></select>
										</td>
										<td style="width:100px;">订单状态：</td>
										<td style="width:200px;">
											<select name="queryParams.orderStatus" id="orderSts" style="height:32px; width:157px;"></select>
										</td>
										<td style="width:100px;">下单时间：</td>
										<td>
						        			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
											  <input type="text" name="queryParams.startTime" id="startTime" class="am-form-field">
											  <span class="am-input-group-btn am-datepicker-add-on">
											    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
											  </span>
											</div>
											~
											<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
											  <input type="text" name="queryParams.endTime" id="endTime" class="am-form-field">
											  <span class="am-input-group-btn am-datepicker-add-on">
											    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
											  </span>
											</div>
					        			</td>
									</tr>
									<tr>
										<td style="width:100px;">付款方式：</td>
										<td style="width:200px;">
											<select name="queryParams.payMode" id="orderPayMode" style="height:32px; width:157px;"></select>
										</td>
										<td style="width:100px;">是否拆分：</td>
										<td style="width:200px;">
											<select name="queryParams.issplit" id="ordeSplit" style="height:32px; width:157px;"></select>
										</td>
										<td style="width:100px;">订单总价：</td>
										<td>
						        			<div class="am-input-group am-datepicker-date">
											  <input type="text" name="queryParams.startTotalPrice" id="startTotalPrice" class="am-form-field" style="width: 152px;">
											</div>
											~
											<div class="am-input-group am-datepicker-date">
											  <input type="text" name="queryParams.endTotalPrice" id="endTotalPrice" class="am-form-field" style="width: 152px;">
											</div>
					        			</td>
									</tr>
									<tr>
										<td style="width:100px;">是否发票：</td>
										<td style="width:200px;">
											<select name="queryParams.invoice" id="orderInvoice" style="height:32px; width:157px;"></select>
										</td>
										<td style="width:100px;">订单编号：</td>
										<td style="width:200px;"><input name="queryParams.orderCode" id="orderCode" class="am-form-field" placeholder="左右模糊查询"/></td>
										<td style="width:100px;">实付金额：</td>
										<td>
						        			<div class="am-input-group am-datepicker-date">
												<input type="text" name="queryParams.startActualPrice" id="startActualPrice" class="am-form-field" style="width: 152px;">
											</div>
											~
											<div class="am-input-group am-datepicker-date">
											  	<input type="text" name="queryParams.endActualPrice" id="endActualPrice" class="am-form-field" style="width: 152px;">
											</div>
					        			</td>
									</tr>
									<tr>
										<td colspan="6" align="center">
											<button type="button" id="queryBtn" class="am-btn am-btn-primary frame-search-button">查询</button>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="am-g">
					<div class="am-u-sm-12 page-table-main">
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="orderListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="6%" field="orderCode">订单编号</th>
									<th width="8%" field="nickName">买家</th>
									<th width="6%" field="deliverymode">运送方式</th>
									<th width="6%" field="orderstatus">订单状态</th>
									<th width="7%" field="issplit">是否拆分</th>
									<th width="8%" field="invoice">是否需要发票</th>
									<th width="6%" field="paymode">付款方式</th>
									<th width="6%" field="totalPrice">订单总价</th>
									<!-- <th width="6%" field="commisionCharge">手续费</th>
									<th width="6%" field="orderPrice">订单金额</th> -->
									<th width="6%" field="actualPrice">实付金额</th>
									<th width="15%" field="createTime">下单时间</th>
									<th width="16%" formatter="formatterAction">操作</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="am-u-sm-12">
						<div class="am-cf">共<span id="rowCount"></span>条记录<div id="page" class="am-fr"></div></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title"></span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd" style="margin-top: 5px;">
		        <div align="center">
		        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td width="100" class="table_title">订单编号：</td>
			        		<td>
			        			<input id="orderCode1" class="am-form-field" style="width:85%" disabled="disabled"/>
			        			<input id="operate1" type="hidden" disabled="disabled"/>
			        			<input id="orderId1" type="hidden" disabled="disabled"/>
			        		</td>
			        	</tr>
		       	 	</table>
		       	 	<br>
		       	 	<div align="center">
						<button type="button" class="am-btn am-btn-success" id="confirmBtn"><span class="am-icon-check"></span>确认</button>
						<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span>关闭</button>
					</div>
	           	</div>
	    	</div>
		</div>
	</div>
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-2">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span>调整费用</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd" style="margin-top: 5px;">
		        <div align="center">
		        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td width="100" class="table_title">订单编号：</td>
			        		<td>
			        			<input id="orderCode2" class="am-form-field" style="width:85%" disabled="disabled"/>
			        			<input id="orderId2" type="hidden" disabled="disabled"/>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">买家：</td>
			        		<td><input id="nickName2" class="am-form-field" style="width:85%" disabled="disabled"/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">支付总额：</td>
			        		<td><input id="actual_price2" class="am-form-field" style="width:85%"/></td>
			        	</tr>
		       	 	</table>
		       	 	<br>
		       	 	<div align="center">
						<button type="button" class="am-btn am-btn-success" id="changeBtn2"><span class="am-icon-check"></span>确认</button>
						<button type="button" class="am-btn am-btn-default" id="closeBtn2"><span class="am-icon-undo"></span>关闭</button>
					</div>
	           	</div>
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/order_ordermanage.js"></script>
</html>