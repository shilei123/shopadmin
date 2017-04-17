<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>活动管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">活动管理</strong> / <small>活动的新增、编辑、查看、删除</small>
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
										<td class="query_title">活动开始时间：</td>
					        			<td valign="top">
						        			<div>
						        				<input class="am-form-field" name="queryParams.startRegTime" id="startTime" style="width:152px"/>
						        			</div>
										</td>
										<td class="query_title">活动结束时间：</td>
										<td>
											<div>
											<input class="am-form-field" name="queryParams.endRegTime" id="endTime" style="width:152px"/>
											</div>
					        			</td>
					        			<td>
											<button type="button" id="addBtn" class="am-btn am-btn-primary frame-search-button">新增</button>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="eventsListTable">
							<thead>
								<tr>
									<th width="2%"  field="index"></th>
									<th width="18%" field="name">活动名称</th>
									<th width="10%" field="startTime">活动开始时间</th>
									<th width="10%" field="endTime">活动结束时间</th>
									<th width="10%" field="isuse">活动状态</th>
									<th width="10%" formatter="formatterAction">操作</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editeEventsModal">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="titles">新增</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
	    		<iframe id="eventsParamsFrame" scrolling="no" frameborder="0" style="width:875px; height: 400px;overflow: hidden;overflow-x:hidden;overflow-y:hidden;"></iframe>
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/events_events.js"></script>
</html>