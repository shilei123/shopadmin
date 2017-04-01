<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>订单统计</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">订单统计</strong> / <small>订单统计的查看</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<div class="am-panel am-panel-primary">
							<!-- <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}">查询条件<span class="am-icon-chevron-down am-fr"></span></div> -->
							<div class="am-panel-bd am-collapse am-in frame-search-panel"id="collapse-panel-1">
								<table id="from_query" class="frame-query-table" border="0" bordercolor="black">
									<tr>
										<td class="query_title" >用户名：</td>
										<td><input name="queryParams.userName" class="am-form-field"/></td>
										<td class="query_title" >订单状态</td>
										<td>
											<select id="orderStatus" name="queryParams.orderStatus" data-am-selected="{btnWidth: '152px'}">
											</select>
										</td>
										<td class="query_title">下单时间：</td>
					        			<td valign="top">
						        			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
												  <input type="text" name="queryParams.startRegTime" id="startRegTime" class="am-form-field">
												  <span class="am-input-group-btn am-datepicker-add-on">
												    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
												  </span>
												</div>
												~
												<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
												  <input type="text" name="queryParams.endRegTime" id="endRegTime" class="am-form-field">
												  <span class="am-input-group-btn am-datepicker-add-on">
												    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
												  </span>
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
									<th width="2%"  field="index"></th>
									<th width="10%" field="">订单号</th>
									<th width="10%" field="">用户名</th>
									<th width="10%" field="">订单金额</th>
									<th width="10%" field="">支付金额</th>
									<th width="10%" field="">支付方式</th>
									<th width="10%" field="">运送方式</th>
									<th width="10%" field="">订单状态</th>
									<th width="15%" field="">生成时间</th>
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
	<!-- content end -->
</body>
<script type="text/javascript" src="${path }/view/js/order.js"></script>
</html>