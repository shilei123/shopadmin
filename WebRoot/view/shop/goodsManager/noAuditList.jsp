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
										<td><input name="queryParams.title" class="am-form-field"/></td>
										<td class="query_title" style="width:80px;">&nbsp;商品货号：</td>
										<td><input name="queryParams.goodsNo" class="am-form-field"/></td>
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
</body>
<script type="text/javascript" src="${path }/view/shop/goodsManager/goodsManager_formatter.js"></script>
<script type="text/javascript" src="${path }/view/js/goodsManager_noAuditList.js"></script>
</html>