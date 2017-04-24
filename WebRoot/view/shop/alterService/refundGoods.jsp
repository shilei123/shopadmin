<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>退款审核</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">退款审核</strong> / <small>退款审核的查看</small>
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
										<td class="query_title" width="100px">名称：</td>
										<td width="200px">
										<input type="hidden" name="queryParams.billType" value="<%=com.sunchin.shop.admin.dict.BillTypeEnum.REFUNDGOODS.getCode()%>"/>
										<input name="queryParams.name" class="am-form-field"/>
										</td>
										<td class="query_title" width="100px">类型：</td>
										<td width="200px">
											<select id="kind" name="queryParams.kind" data-am-selected="{btnWidth: '164px'}">
											</select>
										</td>
										<td class="query_title" width="100px">申请时间：</td>
					        			<td valign="top" colspan="2">
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
										<td class="query_title">单据编号：</td>
										<td>
											<input name="queryParams.code" class="am-form-field"/>
										</td>
										<td class="query_title">订单编号：</td>
										<td>
											<input name="queryParams.orderCode" class="am-form-field"/>
										</td>
										<td class="query_title" width="100px">状态：</td>
										<td width="200px">
											<select id="status" name="queryParams.status" data-am-selected="{btnWidth: '152px'}">
											</select>
										</td>
										<td>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="billListTable">
							<thead>
								<tr>
									<th width="2%"  field="index"></th>
									<th width="8%"  field="name">名称</th>
									<th width="6%"  field="kind">类型</th>
									<th width="8%"  field="code">单据编号</th>
									<th width="8%"  field="orderCode">订单编号</th>
									<th width="12%" field="createTime">申请时间</th>
									<th width="8%"  field="userName">申请人</th>
									<th width="6%"  field="billStatus">状态</th>
									<th width="15%" formatter="formatterAction">操作</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editBillModal">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd" ><span id="title">详情</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <iframe id="billParamsFrame" scrolling="no" frameborder="0" style="width:720px; height: 380px;overflow: hidden;overflow-x:hidden;overflow-y:hidden;"></iframe>
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/alterService_refundGoods.js"></script>
</html>