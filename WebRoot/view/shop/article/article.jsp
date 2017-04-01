<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>文章管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">文章管理</strong> / <small>文章管理的查看、新增、编辑、删除</small>
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
										<td class="query_title" >文章标题：</td>
										<td><input name="queryParams.articleTitle" class="am-form-field"/></td>
										<td class="query_title" >作者：</td>
										<td><input name="queryParams.author" class="am-form-field"/></td>
										<td class="query_title">创建时间：</td>
					        			<td valign="top">
						        			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
												  <input type="text" name="queryParams.startRegTime" id="startRegTime" class="am-form-field">
												  <span class="am-input-group-btn am-datepicker-add-on">
												    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
												  </span>
												</div>
												~
												<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
												  <input type="text" name="queryParams.endRegTime" id="endRegTime" class="am-form-field">
												  <span class="am-input-group-btn am-datepicker-add-on">
												    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
												  </span>
											</div>
					        			</td>
										</tr>
										<tr>
										<td class="query_title" >来源：</td>
										<td><input name="queryParams.source" class="am-form-field"/></td>
										<td class="query_title" style="width:100px;">分类：</td>
										<td>
											<select id="articleType" name="queryParams.articleType" data-am-selected="{btnWidth: '164px'}">
											</select>
										</td>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="articleListTable">
							<thead>
								<tr>
									<th width="2%"  field="index"></th>
									<th width="30%" field="articleTitle" formatter="formatterArticleTitle">文章标题</th>
									<th width="15%" field="author">作者</th>
									<th width="15%" field="source">来源</th>
									<th width="10%" field="articleType">类型</th>
									<th width="15%" field="createTime">创建时间</th>
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
</body>
<script type="text/javascript" src="${path }/view/js/article.js"></script>
</html>