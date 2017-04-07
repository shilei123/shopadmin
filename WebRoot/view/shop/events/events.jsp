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
		        <div align="center">
		        	<table id="edit_events_table" class="frame-modal-table" border="1" bordercolor="black">
		        		<input type="hidden" name="events.id" id="vId"/>
			        	<tr>
			        		<td width="100px" class="table_title">活动名称：</td>
			        		<td colspan="3"><input name="events.name" maxlength="50" id="name" placeholder="活动名称" class="am-form-field" style="width:78%"/></td>
				        	<td width="100px" class="table_title" colspan="2">是否启用：</td>
				        	<td colspan="3">
				        		<select name="events.isuse" id="isuse" data-am-selected="{btnWidth: '152px'}">
				        	</td>
			        	</tr>
			        	<tr>
			        		<td>活动开始时间：</td>
					   		<td valign="top" colspan="3">
							    <div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
								   <input type="text" name="events.startTime" id="startTime" class="am-form-field">
							       <span class="am-input-group-btn am-datepicker-add-on">
								   <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
								</span>
								</div>
							</td>
							<td colspan="2" align="right">活动结束时间：</td>
							<td colspan="3">
								<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
							       <input type="text" name="events.endTime" id="endTime" class="am-form-field">
								   <span class="am-input-group-btn am-datepicker-add-on">
								   <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
								</span>
						    	</div>
	        				</td>
			        	</tr>
			        	<tr>
			        		<td valign="top" class="table_title"><div style="margin-top: 5px;">活动介绍：</div></td>
			        		<td valign="top" colspan="8"> 
		        				<textarea name="events.memo" id="memo" placeholder="活动介绍" style="width:97%;height:150px;margin-top: 5px;" class="am-form-field"></textarea> 
		        		</td>
		        		</tr>
		        		<tr>
			        	<td width="100px" class="table_title">活动商品：</td>
			        	<input type="hidden" name="eventsGoodsList" id="eventsGoodsList" />
			  			<input type="hidden" name="eventsId" id="eventsId"/>
			  			<input type="hidden" name="eventsName" id="eventsName"/>
			  			<td>商品名称：</td>
			        	<td>
                           <span><input id="mansong_price" type="text" class="am-form-field" style="width:100px"></span>
                        </td>
                        <td><a id="chooseGoodsBtn">选择商品</a></td>
                        <td>商品活动价格：</td>
                        <td>
                           <span><input id="mansong_price" type="text" class="am-form-field" style="width:100px"></span>
                        </td>
                        <td>生效范围：</td>
                        <td>
                           <span>
	                           <select name="eventsGoods.scope" id="scope" data-am-selected="{btnWidth: '98px'}">
								<option value="1">所有</option>
								<option value="0">活动</option>
	                           </select>
                           </span>
			        	</td>
			        	<td>s
			        	
			        	</td>
			        	<!-- <td colspan="3">
			  			 <div id="events_goods"> 
				  			<ul id="mansong_rule_list" class="mansong-rule-list">
	                    	</ul>
		                    <div id="div_add_rule" style="display:none;">
		                        <div class="sc-mansong-error">
                                    <span id="mansong_price_error" style="display:none;"><i class="icon-exclamation-sign"></i><font size="1"color="red">商品活动价格不能为空且必须为数字</font></span>
		                        </div>
		                        <div class="sc-mansong-rule">
                                    
                                    <span>商品活动价格&nbsp;<input id="mansong_price" type="text" class="text w50"><em class="add-on"><i class="icon-renminbi"></i></em></span>
                                    <span>生效范围
                                    <select name="eventsGoods.scope" id="scope" data-am-selected="{btnWidth: '152px'}">
									<option value="1">所有</option>
									<option value="0">活动</option>
                                    </select>
                                    <em class="add-on"><i class="icon-renminbi"></i></em></span>
		                        </div>
		                        <div id="mansong_rule_error" style="display:none;">请至少选择一件商品</div>
		                        <div id="div_confirmOrCancel" class="mt10" style="display:none;">
		                            
		                            <a href="javascript:void(0);" id="btn_save_rule" class="sc-btn sc-btn-acidblue"><i
		                                    class="icon-ok-circle"></i>确定商品</a>
		                            <a href="javascript:void(0);" id="btn_cancel_add_rule" class="sc-btn sc-btn-orange"><i
		                                    class="icon-ban-circle"></i>取消</a></div>
		                    </div>
			  			 </div>
			        	</td> -->
			        	</tr>
			        	<tr>
			        	<td colspan="9">
			        	<!-- <a id="chooseGoodsBtn" style="margin-left: 120px" style="display:none;">选择商品</a> -->
			        	<!-- <a href="javascript:void(0);" style="margin-left: 120px" id="btn_add_rule"class="sc-btn sc-btn-acidblue"><i class="icon-plus-sign"></i>添加商品</a> -->
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
<script type="text/javascript" src="${path }/view/js/events_events.js"></script>
</html>