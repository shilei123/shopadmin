<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>活动选择</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">活动选择</strong> / <small>活动的选择、查询</small>
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
										<td class="query_title" >活动名称：</td>
										<td><input name="queryParams.name" class="am-form-field"/></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="am-g">
					<div class="am-u-sm-12 page-table-main">
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="eventsListTable">
				     		<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="20%" field="name">活动名称</th>
									<th width="15%" field="startTime">活动开始时间</th>
									<th width="15%" field="endTime">活动结束时间</th>
									<th width="10%" field="isuse">状态</th>
									<th width="10%" formatter="formatterAttachAction">操作</th>
								</tr>
							</thead>
				     	</table>
					</div>
					<div class="am-u-sm-12">
						<div class="am-cf">共<span id="rowCount"></span>条记录<div id="page" class="am-fr"></div></div>
					</div>
				</div>
				<div align="center">
					<button type="button" id="queryBtn" class="am-btn am-btn-primary frame-search-button"><span class="am-icon-search"></span>查询</button>
			     	<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
			    </div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/advertise_queryEvents.js"></script>
</html>