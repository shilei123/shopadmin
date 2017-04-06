<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>实名认证审核</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">实名认证审核</strong> / <small>实名认证的手动认证、查看</small>
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
										<td class="query_title" >身份证号码：</td>
										<td><input name="queryParams.identityCard" class="am-form-field"/></td>
										<td class="query_title">申请认证时间：</td>
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
										<td class="query_title" style="width:100px;">身份证状态：</td>
										<td>
											<select id="identityStatus" name="queryParams.identityStatus" data-am-selected="{btnWidth: '164px'}">
											</select>
										</td>
										<td colspan="4" align="center">
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="userIdentityListTable">
							<thead>
								<tr>
									<th width="2%"  field="index"></th>
									<th width="5%"  field="userName">用户名</th>
									<th width="7%"  field="identityCard">身份证号码</th>
									<th width="8%"  field="identityStatus">身份证状态</th>
									<th width="10%" field="identityFrontal">身份证正面照</th>
									<th width="10%" field="identityBack">身份证背面照</th>
									<th width="11%" field="identityHoldFrontal">手持身份证正面</th>
									<th width="12%" field="applicationTime">申请认证时间</th>
									<th width="5%"  field="applicant">申请人</th>
									<th width="10%" field="failureReason">失败理由</th>
									<th width="13%" formatter="formatterAction">操作</th>
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
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editUserIdentityModal">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">认证失败</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<table id="edit_user_identity_table" class="frame-modal-table" border="0" bordercolor="black">
		        		<input type="hidden" name="identity.id" id="identityId"/>
			        	<tr>
		        		<td valign="top" class="table_title"><div style="margin-top: 5px;">失败理由：</div></td>
		        		<td valign="top"> 
		        			<textarea name="identity.failureReason" id="failureReason" placeholder="失败理由" style="width:96%;height:100px;margin-top: 5px;" class="am-form-field"></textarea> 
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
<script type="text/javascript" src="${path }/view/js/userManagement_userIdentity.js"></script>
</html>