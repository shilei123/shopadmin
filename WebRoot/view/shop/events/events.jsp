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
					        			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
										  <input type="text" name="queryParams.startRegTime" id="startRegTime" class="am-form-field">
										  <span class="am-input-group-btn am-datepicker-add-on">
										    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
										  </span>
										</div>
										</td>
										<td class="query_title">活动结束时间：</td>
										<td>
											<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
											  <input type="text" name="queryParams.endRegTime" id="endRegTime" class="am-form-field">
											  <span class="am-input-group-btn am-datepicker-add-on">
											    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
											  </span>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="eventsinfoListTable">
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
				
				<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-2">
				<div class="am-modal-dialog">
			    	<div class="am-modal-hd"><span id="title">商品选择</span>
			      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
			    	</div>
			    	<hr>
	    			<div class="am-modal-bd frame-am-modal-bd">
	    			<table id="from_query" class="frame-query-table" border="0" bordercolor="black">
									<tr>
										<td class="query_title" >活动名称：</td>
										<td><input name="queryParams.name" class="am-form-field"/></td>
									</tr>
								</table>
						<div>
							<table class="am-table am-table-striped am-table-hover table-main" id="goodsListTable">
								<thead>
									<tr>
										<th width="2%" field="index"></th>
										<th width="18%" field="name">活动名称</th>
										<th width="10%" field="starttime">活动开始时间</th>
										<th width="10%" field="endtime">活动结束时间</th>
										<th width="10%" field="isuse">是否启用</th>
										<th width="10%" formatter="goodsFormatterAction">操作</th>
									</tr>
								</thead>
							</table>
							<div class="am-cf">共<span id="rowCountBonus"></span>条记录<div id="pageBonus" class="am-fr"></div></div>
						</div>
			    	</div>
			    	<hr>
			    	<div align="center">
			    		<button type="button" id="goodsFindBtn" class="am-btn am-btn-primary frame-search-button"><span class='am-icon-search'></span>查询</button>
						<button type="button" class="am-btn am-btn-default" id="goodsCloseBtn"><span class="am-icon-undo"></span> 关闭</button>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>
	<!-- content end -->
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editeEventsinfoModal">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="titles">新增</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<table id="edit_eventsinfo_table" class="frame-modal-table" border="0" bordercolor="black">
		        		<input type="hidden" name="eventsinfo.id" id="vId"/>
			        	<tr>
			        		<td width="100px" class="table_title">活动名称：</td>
			        		<td><input name="eventsinfo.name" maxlength="50" id="name" placeholder="活动名称" class="am-form-field" style="width:78%"/></td>
			        	<td width="100px" class="table_title">是否启用：</td>
			        	<td>
			        		<select name="eventsinfo.isuse" id="isuse" data-am-selected="{btnWidth: '152px'}">
			        	</td>
			        	</tr>
			        	<tr>
			        		<td>活动开始时间：</td>
					   		<td valign="top">
							    <div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
								   <input type="text" name="eventsinfo.starttime" id="startTime" class="am-form-field">
							       <span class="am-input-group-btn am-datepicker-add-on">
								   <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
								</span>
								</div>
							</td>
							<td>活动结束时间：</td>
							<td>
								<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
							       <input type="text" name="eventsinfo.endtime" id="endTime" class="am-form-field">
								   <span class="am-input-group-btn am-datepicker-add-on">
								   <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
								</span>
						    	</div>
	        				</td>
			        	</tr>
			        	<tr>
			        		<td valign="top" class="table_title"><div style="margin-top: 5px;">活动介绍：</div></td>
			        		<td valign="top" colspan="3"> 
		        				<textarea name="eventsinfo.memo" id="memo" placeholder="活动介绍" style="width:94%;height:150px;margin-top: 5px;" class="am-form-field"></textarea> 
		        		</td>
		        		</tr>
		        		<tr>
			        	<td width="100px" class="table_title">活动商品：</td>
			        	<td colspan="3">
			        	<input type="hidden" name="events_goods_list" id="events_goods_list" />
			  			<input type="hidden" name="events_id" id="events_id"/>
			        	</td>
			        	</tr>
			        	<tr>
			        	<td colspan="4"><a id="chooseGoodsBtn" style="margin-left: 120px">选择商品</a></td>
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
<script type="text/javascript" src="${path }/view/js/events.js"></script>
</html>