<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>会员信息管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">会员信息管理</strong> / <small>会员基础信息的查看</small>
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
										<td class="query_title" width="100px">用户名：</td>
										<td width="200px"><input name="queryParams.userName" class="am-form-field"/></td>
										<td class="query_title" width="100px">姓名：</td>
										<td width="200px"><input name="queryParams.trueName" class="am-form-field"/></td>
										<td class="query_title" width="100px">性别：</td>
										<td width="200px">
											<select id="userSex" name="queryParams.userSex" data-am-selected="{btnWidth: '164px'}">
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="userBaseListTable">
							<thead>
								<tr>
									<th width="2%"  field="index"></th>
									<th width="10%" field="userName">用户名</th>
									<th width="10%" field="trueName">姓名</th>
									<th width="10%" field="sex">性别</th>
									<th width="20%" field="phone">电话</th>
									<th width="20%" field="mail">邮箱</th>
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
<script type="text/javascript" src="${path }/view/js/userManagement_userBase.js"></script>
</html>