<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>会员等级设置</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">会员等级设置</strong> / <small>会员等级的查看、新增、编辑、删除</small>
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
										<td class="query_title" style="width:100px;">会员等级：</td>
										<td width="200px">
											<select id="gradeType" name="queryParams.gradeType" data-am-selected="{btnWidth: '164px'}">
											</select>
										</td>
										<td align="left">
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="userGradeListTable">
							<thead>
								<tr>
									<th width="2%"  field="index"></th>
									<th width="20%" field="userGrade">会员等级</th>
									<th width="20%" field="needIntegral">所需积分</th>
									<th width="20%" field="isuse">是否有效</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editGradeModal">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">新增</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<table id="edit_grade_table" class="frame-modal-table" border="0" bordercolor="black">
		        		<input type="hidden" name="grade.id" id="gradeId"/>
			        	<tr>
			        		<td class="table_title">会员等级：</td>
			        		<td>
			        		<select placeholder="请选择" name="grade.userGrade" id="gradeTypeModal" data-am-selected="{btnWidth: '152px'}">
			        		</td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">所需积分：</td>
			        		<td><input name="grade.needIntegral" id="needInt" placeholder="所需积分" class="am-form-field" style="width:152px"/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">是否启用：</td>
			        		<td>
			        		<select placeholder="请选择" name="grade.isuse" id="isuse" data-am-selected="{btnWidth: '152px'}">
			        		</td>
			        	</tr>
			        	<tr>
		        		<td valign="top" class="table_title"><div style="margin-top: 5px;">备注：</div></td>
		        		<td valign="top" colspan="3"> 
		        			<textarea name="grade.remark" id="remark" placeholder="备注" style="width:96%;height:100px;margin-top: 5px;" class="am-form-field"></textarea> 
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
<script type="text/javascript" src="${path }/view/js/userManagement_userGrade.js"></script>
</html>