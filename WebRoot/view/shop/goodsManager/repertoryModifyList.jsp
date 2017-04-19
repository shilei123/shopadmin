<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>待审核商品</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">待审核商品</strong> / <small>待审核商品</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<div class="am-panel am-panel-primary">
							<div class="am-panel-bd am-collapse am-in frame-search-panel" id="collapse-panel-1">
								<table id="from_query" class="frame-query-table" border="0" bordercolor="black">
									<tr>
										<td class="query_title" style="width:80px;">商品标题：</td>
										<td><input name="queryParams.bankName" class="am-form-field"/></td>
										<td class="query_title" style="width:80px;">&nbsp;商品货号：</td>
										<td><input name="queryParams.bankDesc" class="am-form-field"/></td>
										<td class="query_title" style="width:120px;">&nbsp;商品审核状态：</td>
										<td><input name="queryParams.bankDesc" class="am-form-field"/></td>
									</tr>
									<tr>
										<td class="query_title">分类：</td>
										<td><input name="queryParams.bankName" class="am-form-field"/></td>
										<td class="query_title">&nbsp;品牌：</td>
										<td><input name="queryParams.bankDesc" class="am-form-field"/></td>
										<td colspan="2" align="center">&nbsp;<button type="button" id="queryBtn" class="am-btn am-btn-primary frame-search-button">查询</button></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="am-g">
					<div class="am-u-sm-12 page-table-main">
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="dataListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="10%" field="goodsNo">货号</th>
									<th width="40%" field="title" formatter="formatterTitle">标题</th>
									<th width="8%" field="cateName">分类</th>
									<th width="8%" field="brandName">品牌</th>
									<th width="6%" field="goodsSts" formatter="formatterGoodsSts">商品状态</th>
									<th width="6%" field="auditSts" formatter="formatterAuditSts">审核状态</th>
									<th width="20%" formatter="formatterAction">操作</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="priceEditModel">
		<div class="am-modal-dialog">
		 	<div class="am-modal-hd"><span id="title">编辑价格</span>
		    	<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
		    </div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td valign="top" style="width:60px;" class="table_title"><div style="margin-top: 5px;">采购价：</div></td>
			        		<td valign="top"> 
			        			<input  class="am-form-field" type="text" value=""/>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td valign="top" style="width:60px;" class="table_title"><div style="margin-top: 5px;">市场价：</div></td>
			        		<td valign="top"> 
			        			<input  class="am-form-field" type="text" value=""/>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td valign="top" style="width:60px;" class="table_title"><div style="margin-top: 5px;">销售价：</div></td>
			        		<td valign="top"> 
			        			<input  class="am-form-field" type="text" value=""/>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td valign="top" style="width:60px;" class="table_title"><div style="margin-top: 5px;">促销价：</div></td>
			        		<td valign="top"> 
			        			<input  class="am-form-field" type="text" value=""/>
			        		</td>
			        	</tr>
		       	 	</table>
		       	 	<div align="center" id="errorMsg" style="color: red;margin-top: 5px;margin-bottom: 10px;">&nbsp;</div>
		       	 	<div align="center">
		       	 		<button type="button" class="am-btn am-btn-success" id="saveBtn1"><span class="am-icon-save"></span> 保存</button>
						<button type="button" class="am-btn am-btn-default" id="closeBtn1"><span class="am-icon-undo"></span> 取消</button>
					</div>
	           	</div>
	    	</div>
		</div>
	</div>
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="repEditModel">
		<div class="am-modal-dialog">
		 	<div class="am-modal-hd"><span id="title">编辑库存</span>
		    	<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
		    </div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td valign="top" style="width:80px;" class="table_title"><div style="margin-top: 5px;">可用库存：</div></td>
			        		<td valign="top"> 
			        			<input  class="am-form-field" type="text" value=""/>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td valign="top" class="table_title"><div style="margin-top: 5px;">销售库存：</div></td>
			        		<td valign="top"> 
			        			<input  class="am-form-field" type="text" value=""/>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td valign="top" class="table_title"><div style="margin-top: 5px;">冻结库存：</div></td>
			        		<td valign="top"> 
			        			<input  class="am-form-field" type="text" value=""/>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td valign="top" class="table_title"><div style="margin-top: 5px;">报损数量：</div></td>
			        		<td valign="top"> 
			        			<input  class="am-form-field" type="text" value=""/>
			        		</td>
			        	</tr>
		       	 	</table>
		       	 	<div align="center" id="errorMsg" style="color: red;margin-top: 5px;margin-bottom: 10px;">&nbsp;</div>
		       	 	<div align="center">
		       	 		<button type="button" class="am-btn am-btn-success" id="saveBtn2"><span class="am-icon-save"></span> 保存</button>
						<button type="button" class="am-btn am-btn-default" id="closeBtn2"><span class="am-icon-undo"></span> 取消</button>
					</div>
	           	</div>
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/shop/goodsManager/goodsManager_formatter.js"></script>
<script type="text/javascript" src="${path }/view/js/goodsManager_repertoryModifyList.js"></script>
</html>