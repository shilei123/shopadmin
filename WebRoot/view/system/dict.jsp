<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>银行管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">数据字典管理</strong> / <small>数据字典的新增、修改、查看</small>
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
										<td style="width:50px;">类型：</td>
										<td><input name="queryParams.bankName" class="am-form-field"/></td>
										<td style="width:50px;">代码：</td>
										<td><input name="queryParams.bankDesc" class="am-form-field"/></td>
										<td style="width:80px;">中文名称：</td>
										<td><input name="queryParams.bankDesc" class="am-form-field"/></td>
										<td>
											<button type="button" id="queryBtn" class="am-btn am-btn-primary frame-search-button">查询</button>
											<button type="button" id="openBankBtn" class="am-btn am-btn-primary frame-search-button">新增</button>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="am-g">
					<div class="am-u-sm-12 page-table-main">
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="bankListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="10%" field="bankName" formatter="formatterBankName">类型</th>
									<th width="10%" field="tel">代码</th>
									<th width="13%" field="url" formatter="formatterUrl">中文名称</th>
									<th width="10%" field="bankDesc">英文名称</th>
									<th width="10%" field="bankDesc">父ID</th>
									<th width="10%" field="bankDesc">备注说明</th>
									<th width="5%" field="bankDesc">有效</th>
									<th width="5%" field="bankDesc">可删除</th>
									<th width="5%" field="bankDesc">可编辑</th>
									<th width="10%" field="bankDesc">排序</th>
									<th width="10%" field="bankDesc">创建时间</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-2">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">新增银行</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<input name="bankInfo.id" id="bankId" type="hidden"/>
		        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td width="100" class="table_title">银行名称：</td>
			        		<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:90%" minlength="3" required/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">电话：</td>
			        		<td><input name="bankInfo.tel" id="tel" placeholder="电话" class="am-form-field" style="width:90%" required/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">网址：</td>
			        		<td><input name="bankInfo.url" id="url" placeholder="网址" class="am-form-field" style="width:90%"/></td>
			        	</tr>
			        	<tr>
			        		<td valign="top" class="table_title"><div style="margin-top: 5px;">银行描述：</div></td>
			        		<td valign="top"> 
			        			<textarea rows="" cols="" name="bankInfo.bankDesc" id="bankDesc" placeholder="银行描述" style="width:90%;height:100px;margin-top: 5px;" class="am-form-field"></textarea> 
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
<script type="text/javascript" src="${path }/view/js/system_dict.js"></script>
</html>