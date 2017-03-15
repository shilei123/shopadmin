<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>数据字典管理</title>
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
										<td class="query_title" style="width:50px;">类型：</td>
										<td><input name="queryParams.type" class="am-form-field"/></td>
										<td class="query_title" style="width:50px;">代码：</td>
										<td><input name="queryParams.code" class="am-form-field"/></td>
										<td class="query_title" style="width:80px;">中文名称：</td>
										<td><input name="queryParams.name" class="am-form-field"/></td>
									</tr>
									<tr>
										<td class="query_title" style="width:80px;">英文名称：</td>
										<td><input name="queryParams.ename" class="am-form-field"/></td>
										<td class="query_title" style="width:80px;">描述：</td>
										<td><input name="queryParams.remark" class="am-form-field"/></td>
										<td colspan="2">
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="dictListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="10%" field="type">类型</th>
									<th width="8%" field="code">代码</th>
									<th width="13%" field="name">中文名称</th>
									<th width="8%" field="ename">英文名称</th>
									<th width="13%" formatter="formatterParent">父信息</th>
									<th width="4%" field="isuse" formatter="formatterIsuse">有效</th>
									<th width="4%" field="isedit" formatter="formatterIsEdit">编辑</th>
									<th width="4%" field="sort">排序</th>
									<th width="14%" field="createTime">创建时间</th>
									<th width="15%" formatter="formatterAction">操作</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editDictModal">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">新增</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<table id="edit_dict_table" class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td width="100" class="table_title">ID：</td>
			        		<td><input name="dict.id" id="dictId" placeholder="ID,自动生成，不可编辑" readonly="readonly" class="am-form-field" style="width:90%" required/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">父ID：</td>
			        		<td><input name="dict.pcode" id="pcode" placeholder="父ID" readonly="readonly" class="am-form-field" style="width:90%"/></td>
			        	</tr>
			        	<tr>
			        		<td width="100" class="table_title">类型：</td>
			        		<td><input name="dict.type" id="type" placeholder="类型" class="am-form-field" style="width:90%" required/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">代码：</td>
			        		<td><input name="dict.code" id="code" placeholder="代码" class="am-form-field" style="width:90%" required/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">中文名称：</td>
			        		<td><input name="dict.name" id="name" placeholder="中文名称" class="am-form-field" style="width:90%"/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">英文名称：</td>
			        		<td><input name="dict.ename" id="ename" placeholder="英文名称" class="am-form-field" style="width:90%"/></td>
			        	</tr>
			        	<tr>
			        		<td valign="top" class="table_title"><div style="margin-top: 5px;">描述：</div></td>
			        		<td valign="top"> 
			        			<textarea rows="" cols="" name="dict.remark" id="remark" placeholder="描述" style="width:90%;height:50px;margin-top: 5px;" class="am-form-field"></textarea> 
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
