<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>会员消费流水查询</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">会员消费流水查询</strong> / <small>会员消费流水的查询</small>
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
										<td class="query_title" style="width:100px;">交易状态：</td>
										<td>
										<select id="tradeState" name="queryParams.tradeState" data-am-selected="{btnWidth: '164px'}">
										</select>
										</td>
										<td class="query_title">有效期时间：</td>
					        			<td valign="top" colspan="2">
					        			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
										  <input type="text" name="queryParams.startRegTime" id="startRegTime" class="am-form-field" style="width: 122px">
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
										<td class="query_title" style="width:100px;">交易类型：</td>
										<td>
										<select id="tradeType" name="queryParams.tradeType" data-am-selected="{btnWidth: '164px'}">
										</select>
										</td>
										<td class="query_title" style="width:50px;">钱包类型：</td>
										<td>
										<select id="purseType" name="queryParams.purseType" data-am-selected="{btnWidth: '164px'}">
										</select>
										</td>
										<td class="query_title" style="width:100px;">操作类型：</td>
										<td>
										<select id="optionType" name="queryParams.optionType" data-am-selected="{btnWidth: '164px'}">
										</select>
										</td>
										<td colspan="1" >
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="userPurseListTable">
							<thead>
								<tr>
									<th width="2%"  field="index"></th>
									<th width="5%" field="userName">用户名</th>
									<th width="6%"  field="tradeType">交易类型</th>
									<th width="6%"  field="tradeAmount">交易金额</th>
									<th width="9%"  field="tradeSn">交易流水号</th>
									<th width="6%"  field="tradeState">交易状态</th>
									<th width="6%"  field="optionType">操作类型</th>
									<th width="6%" field="userAmount">可用金额</th>
									<th width="7%" field="purseType">钱包类型</th>
									<th width="9%" field="payAccount">支付账户</th>
									<th width="12%" field="payOpenBank">支付银行卡</th>
									<th width="13%" field="createTime">有效期起</th>
									<th width="13%" field="optionTime">有效期止</th>
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
<script type="text/javascript" src="${path }/view/js/userPurse.js"></script>
</html>