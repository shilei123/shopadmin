<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>常见管理</title>
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
						<strong class="am-text-primary am-text-lg">常见问题管理</strong> / <small>常见问题的查看、增加、编辑与删除</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<div class="am-panel am-panel-primary">
							<div class="am-panel-bd am-collapse am-in frame-search-panel"id="collapse-panel-1">
								<table id="from_query" class="frame-query-table" border="0" bordercolor="black">
									<tr>
										<td style="width:100px;">类型：</td>
										<td style="width:200px;">
											<select name="queryParams.faqType" id="faqType" style="height:32px; width:157px;"></select>
										</td>
										<td style="width:100px;">分类：</td>
										<td style="width:200px;">
											<select name="queryParams.category" id="category" style="height:32px; width:157px;"></select>
										</td>
										<td style="width:100px;">时间：</td>
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
									<tr>
										<td style="width:100px;">是否热点：</td>
										<td style="width:200px;">
											<div>
												<select name="queryParams.hotQuestion" id="hotQuestion" style="height:32px; width:157px;">
													<!-- <option value="">-请选择-</option>
													<option value="1">是</option>
													<option value="0">否</option> -->
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td style="width:100px;">标题：</td>
										<td style="width:200px;">
											<div>
												<input type="text" name="queryParams.faqTitle" id="faqTitle" style="height:32px; width:157px;"/>
											</div>
										</td>
										<td>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="contentListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="10%" field="faqTitle">标题</th>
									<th width="10%" field="faqType">类型</th>
									<th width="10%" field="faqCategory">分类</th>
  									<th width="10%" field="hotQuestion">是否热点</th>
  									<th width="10%" field="createTime">时间</th>
									<th width="5%" field="order_">排序</th>
									<!-- <th width="5%" field="parentFaqId">父id</th> -->
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
		        <div align="center">
		        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td width="100" class="table_title">类型：</td>
			        		<td><input id="faqType2" class="am-form-field" style="width:90%" disabled="disabled"/></td>
			        	</tr>
			        	<tr>
			        		<td width="100" class="table_title">分类：</td>
			        		<td><input id="category2" class="am-form-field" style="width:90%" disabled="disabled"/></td>
			        	</tr>
			        	<tr>
			        		<td width="100" class="table_title">是否热点：</td>
			        		<td><input id="hotQuestion2" class="am-form-field" style="width:90%" disabled="disabled"/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">排序：</td>
			        		<td><input id="order2" class="am-form-field" style="width:90%" disabled="disabled"/></td>
			        	</tr>
			        	<tr>
			        		<td valign="top" class="table_title"><div style="margin-top: 5px;">内容：</div></td>
			        		<td valign="top"> 
			        			<textarea rows="" cols="" id="faqContent2" style="width:90%;height:100px;margin-top: 5px;" class="am-form-field" disabled="disabled"></textarea> 
			        		</td>
			        	</tr>
		       	 	</table>
		       	 	<br>
		       	 	<div align="center">
						<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span>关闭</button>
					</div>
	           	</div>
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/comment_faqmanager.js"></script>
</html>