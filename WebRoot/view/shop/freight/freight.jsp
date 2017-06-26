<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>运费设置</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">运费设置</strong> / <small>运费模板的新增、编辑、查看、删除</small>
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
										<td class="query_title" width="100px">模板名称：</td>
										<td width="200px"><input name="queryParams.templateName" class="am-form-field"/></td>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="freightListTable">
							<thead>
									<tr>
										<th width="2%"   field="index"></th>
										<th width="12%"  field="templateName">模板名称</th>
										<th width="6%"  field="transportMode">运送方式</th>
										<th width="20%"  field="transportRange">运送范围</th>
										<th width="6%"  field="initialInt">首件(个)</th>
										<th width="6%"  field="initialPrice">运费(元)</th>
										<th width="6%"  field="stackInt">续件(个)</th>
										<th width="6%"  field="stackPrice">续费(元)</th>
										<th width="6%"  field="isuse">是否默认</th>
										<th width="25%"  formatter="formatterAction">操作</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editeFreightModal">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd" ><span id="titles">新增</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <iframe id="freightParamsFrame" scrolling="no" frameborder="0" style="width:720px; height: 380px;overflow: hidden;overflow-x:hidden;overflow-y:hidden;"></iframe>
	    	</div>
		</div>
	</div>
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="modalTitle">运费服务商配置</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
	      		<iframe id="freAndExpParamsFrame" scrolling="no" frameborder="0" style="width:100%; height: 360px;overflow: hidden;overflow-x:hidden;overflow-y:hidden;"></iframe>
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/freight_freight.js"></script>
</html>