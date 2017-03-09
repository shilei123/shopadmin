<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>优惠券记录查询</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">优惠券记录查询</strong> / <small>用户优惠券的修改、查看</small>
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
										<td class="query_title">用户名：</td>
										<td><input name="queryParams.userName" class="am-form-field"/></td>
										<td class="query_title">优惠券名称：</td>
										<td><input name="queryParams.couponName" class="am-form-field"/></td>
										<td>有效期：</td>
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
									<td>优惠券状态：</td>
										<td>
										<select id="status" name="queryParams.sts" data-am-selected="{btnWidth: '164px'}">
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="userCouponListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="10%" field="userName">用户名</th>
									<th width="10%" field="couponName">优惠券名称</th>
									<th width="10%" field="couponBlance">优惠券金额</th>
									<th width="10%" field="couponXfBalance">满足金额使用</th>
									<th width="10%" field="couponCreatdate">优惠券发放时间</th>
									<th width="10%" field="couponExpirydate">有效期</th>
									<th width="10%" field="orderSn">使用订单号</th>
									<th width="10%" field="couponStatus">优惠券状态</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editUserCouponModal">
		<div class="am-modal-dialog">
		<div class="am-modal-hd"><span id="title">编辑</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<table id="edit_user_coupon_table" class="frame-modal-table" border="0" bordercolor="black">
			        	<input type="hidden" id="userCouponId" name="userCoupon.id" class="am-form-field"/></td>
			        	<tr>
			        		<td width="100" class="table_title">优惠券状态：</td>
			        		<td>
								<select id="sts" name="userCoupon.couponStatus" data-am-selected="{btnWidth: '157px'}">
								</select>
							</td>
			        	</tr>
		       	 	</table>
		       	 	<div align="center" id="errorMsg" style="color: red;margin-top: 5px;margin-bottom: 10px;">&nbsp;</div>
		       	 	<div align="center">
						<button type="button" class="am-btn am-btn-success" id="saveBtn"><span class="am-icon-save"></span> 保存</button>
						<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
					</div>
	           	</div>
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/user_coupon.js"></script>
</html>