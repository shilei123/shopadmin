<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>评论管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style>
  #vld-tooltip {
    position: absolute;
    z-index: 1000;
    padding: 5px 10px;
    background: #F37B1D;
    min-width: 150px;
    color: #fff;
    transition: all 0.15s;
    box-shadow: 0 0 5px rgba(0,0,0,.15);
    display: ;
  }

  #vld-tooltip:before {
    position: absolute;
    top: -8px;
    left: 50%;
    width: 0;
    height: 0;
    margin-left: -8px;
    content: "";
    border-width: 0 8px 8px;
    border-color: transparent transparent #F37B1D;
    border-style: none inset solid;
  }
</style>
</head>
<body>
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">评论管理</strong> / <small>评论的查看与删除</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">   
						<div class="am-panel am-panel-primary">
							<div class="am-panel-bd am-collapse am-in frame-search-panel"id="collapse-panel-1">
								<table id="from_query" class="frame-query-table" border="0" bordercolor="black">
									<tr>
										<td style="width:100px;">评论类型：</td>
										<td style="width:200px;"><input name="queryParams.type" class="am-form-field"/></td>
										<td style="width:100px;">评论内容：</td>
										<td style="width:200px;"><input name="queryParams.content" class="am-form-field"/></td>
										<td style="width:100px;">评论时间：</td>
										<td>
					        			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
										  <input type="text" name="queryParams.startTime" id="startTime" class="am-form-field">
										  <span class="am-input-group-btn am-datepicker-add-on">
										    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
										  </span>
										</div>
										~
										<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
										  <input type="text" name="queryParams.endTime" id="endTime" class="am-form-field">
										  <span class="am-input-group-btn am-datepicker-add-on">
										    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
										  </span>
										</div>
					        		</td>
									</tr>
										<td style="width:100px;">评论人：</td>
										<td style="width:200px;"><input name="queryParams.commentPeople" class="am-form-field"/></td>
										<td style="width:100px;">用户评分>：</td>
										<td style="width:200px;">
											<div>
												<input name="queryParams.score" class="am-form-field"/>
											</div>
										</td>
										<td>
											<button type="button" id="queryBtn" class="am-btn am-btn-primary frame-search-button">查询</button>
										</td>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="am-g">
					<div class="am-u-sm-12 page-table-main">
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="contentListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="10%" field="type">评论类型</th>
									<th width="48%" field="content">评论内容</th>
									<th width="10%" field="score">评分</th>
									<th width="10%" field="contentPeople">评论人</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-2">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title"></span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
	    		
	    		<!-- <form class="am-form" id="ue-form"> -->
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
	           	<!-- </form> -->
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/comment_commentmanager.js"></script>
</html>