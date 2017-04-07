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
										<td class="query_title" >模板名称：</td>
										<td><input name="queryParams.templateName" class="am-form-field"/></td>
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
										<th width="10%"  field="templateName">模板名称</th>
										<th width="10%"  field="transportMode">运送方式</th>
										<th width="10%"  field="transportRange">运送范围</th>
										<th width="10%"  field="initialInt">首件(个)</th>
										<th width="10%"  field="initialPrice">运费(元)</th>
										<th width="10%"  field="stackInt">续件(个)</th>
										<th width="10%"  field="stackPrice">续费(元)</th>
										<th width="10%"  field="isuse">是否默认</th>
										<th width="15%"  formatter="formatterAction">操作</th>
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
		        <div align="center">
		        	<table id="edit_fre_table" class="frame-modal-table" border="0" bordercolor="black">
		        		<input type="hidden" name="freight.id" id="vId"/>
			        	<tr>
			        		<td width="100px" class="table_title">模板名称：</td>
			        		<td><input name="freight.templateName" maxlength="50" id="templateName" placeholder="模板名称" class="am-form-field" style="width:78%"/></td>
			        		<td width="100px" class="table_title">是否默认：</td>
			        		<td>
			        			<select name="freight.isuse" id="isuse" data-am-selected="{btnWidth: '152px'}">
			        		</td>
			        	</tr>
			        	<tr>
				        	<td width="100px" class="table_title">计价方式：</td>
				        	<td colspan="3">
				        	<input id="piece" name="freight.valuation" checked="checked" value="0" type="radio"/><label for="piece">按件数</label>
			        		<input id="weight" name="freight.valuation" value="1" type="radio"/><label for="weight">按重量</label>
			        		<input id="volume" name="freight.valuation" value="2" type="radio"/><label for="volume">按体积</label>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td style="padding-left:30px;" colspan="4">运送方式：除指定地区外，其余地区的运费采用"默认运费"</td>
						</tr>
						<tr>
							<td style="padding-left:130px;" colspan="4">
								<div class="postage-tpl" id="py">
									<p class="trans-line">
										<input id="mail" type="checkbox" value="0" name="freight.TransportMode"> <label for="mail">平邮</label>
									</p>
								</div>
							</td>
		        		</tr>
		        		<tr id="mails"></tr>
		        		<tr>
			        		<td  style="padding-left:130px;" colspan="4">
				        		<div class="postage-tpl" id="kd">
									<p class="trans-line">
										<input id="express" type="checkbox" value="1" name="freight.TransportMode"> <label for="express">快递</label>
									</p>
								</div>
								</td>
		        		</tr>
		        		<tr id="expresl"></tr>
		        		<tr>
			        		<td style="padding-left:130px;" colspan="4">
				        		<div class="postage-tpl" id="es">
									<p class="trans-line">
										<input id="ems" type="checkbox" value="2" name="freight.TransportMode"> <label for="ems">EMS</label>
									</p>
								</div>
			        		</td>
		        		</tr>
		        		<tr id="emss"></tr>
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
<script type="text/javascript" src="${path }/view/js/freight_freight.js"></script>
</html>