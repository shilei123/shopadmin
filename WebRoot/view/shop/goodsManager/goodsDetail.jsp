<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sunchin.shop.admin.dict.PublishTypeEnum"%>
<%@ page import="com.sunchin.shop.admin.dict.FreightTypeEnum"%>
<%@ page import="com.sunchin.shop.admin.dict.EmptyStoreEnum"%>
<%@ page import="com.sunchin.shop.admin.dict.VirtualEnum"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品录入</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet" href="goodsAdd.css" />
<%
	String goodsId = request.getParameter("goodsId")==null?"":request.getParameter("goodsId");
%>
<script type="text/javascript">
	var goodsId = "<%=goodsId %>";
</script>
<style type="text/css">
.am-tabs-bd .am-tab-panel {
	padding: 0px 5px 0px;
}
</style>
</head>
<body>
	<!-- content start -->
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">商品查看</strong> / <small>商品信息查看</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<!-- <div class="am-panel am-panel-primary"> -->
						<table class="frame-modal-table" border="0">
							<tr>
								<td class="table_title">商品分类：</td>
								<td><span id="cateName"></span></td>
							</tr>
							<tr>
								<td class="table_title">商品品牌：</td>
								<td><span id="brandId"></span></td>
							</tr>
							<tr>
								<td class="table_title">商品标题：</td>
								<td><span id="title"></span></td>
							</tr>
							<tr>
								<td class="table_title">商品副标题：</td>
								<td><span id="subTitle"></span></td>
							</tr>
							<tr>
								<td class="table_title">无库存销售：</td>
								<td>
									<span id="emptyStore"></span>
									<input type="hidden" id="emptyStore1" value="<%=EmptyStoreEnum.Y.getCode() %>"/>
									<input type="hidden" id="emptyStore2" value="<%=EmptyStoreEnum.N.getCode() %>"/>
								</td>
							</tr>
							<tr>
								<td class="table_title" valign="top"><span id="kcpzSpan">库存配置：</span></td>
								<td valign="top">
									<div id="goodsChildDiv">
										<table id="goodsChildTable">
											<thead>
												<tr>
													<th>商品属性</th>
													<th>采购价</th>
													<th>市场价</th>
													<th>销售价</th>
													<th>促销价</th>
													<th>库存</th>
													<th style="width: 100px;">商品货号</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</td>
							</tr>
						</table>
						<table class="frame-modal-table" id="goodsPriceTable">
							<tr>
								<td class="table_title">采购价：</td>
								<td><span id="purchasePrice"></span></td>
							</tr>
							<tr>
								<td class="table_title">市场价：</td>
								<td><span id="marketPrice"></span></td>
							</tr>
							<tr>
								<td class="table_title">销售价：</td>
								<td><span id="salePrice"></span></td>
							</tr>
							<tr>
								<td class="table_title">促销价：</td>
								<td><span id="promotionPrice"></span></td>
							</tr>
							<tr>
								<td class="table_title">商品库存：</td>
								<td><span id="availableNum"></span></td>
							</tr>
							<tr>
								<td class="table_title">商品货号：</td>
								<td><span id="goodsNo"></span></td>
							</tr>
						</table>
						<table class="frame-modal-table" border="0" style="margin-top:5px;">
							<tr>
								<td class="table_title" valign="top">商品图片：</td>
								<td><div style="margin-top: 5px;" id="imagesDiv"></div></td>
							</tr>
							<tr>
								<td class="table_title" valign="top"><div style="margin-top: 13px;">商品参数：</div></td>
								<td>
									<div style="margin-top: 10px;" id="paramsDiv"></div>
								</td>
							</tr>
							<tr>
								<td class="table_title" valign="top"><div style="margin-top: 10px;display: inline-block;">商品描述：</div></td>
								<td>
									<div class="am-tabs" style="margin-top: 10px;" data-am-tabs>
										<ul class="am-tabs-nav am-nav am-nav-tabs">
											<li class="am-active"><a href="#tab1" id="tab1">电脑版描述</a></li>
											<li><a href="#tab2">手机版描述</a></li>
										</ul>
									  	<div class="am-tabs-bd">
										    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
										    	<div id="pcdetail" style="width:99%;height:auto;margin-top: 10px;overflow: auto;"></div>
										    </div>
										    <div class="am-tab-panel am-fade" id="tab2">
										    	<div id="mobileeditor" style="width:99%;height:auto;margin-top: 10px;overflow: auto;"></div>
										    </div>
										</div>
									</div>
								</td>
							</tr>
							<!-- <tr>
								<td class="table_title frame-required" valign="top"><div style="margin-top: 10px;display: inline-block;">电脑版描述：</div></td>
								<td>
									<div id="pcdetail" style="width:99%;height:auto;margin-top: 10px;overflow: auto;"></div>
								</td>
							</tr>
							<tr>
								<td class="table_title frame-required" valign="top"><div style="margin-top: 10px;display: inline-block;">手机版描述：</div></td>
								<td>
									<div id="mobileeditor" style="width:99%;height:auto;margin-top: 10px;overflow: auto;"></div>
								</td>
							</tr> -->
							<tr>
								<td class="table_title">运费：</td>
								<td>
									<span id="freightType"></span>
									<input type="hidden" id="freightType1" value="<%=FreightTypeEnum.BIZ.getCode() %>"/>
									<input type="hidden" id="freightType2" value="<%=FreightTypeEnum.BUYER.getCode() %>"/>
								</td>
							</tr>
							<tr>
								<td class="table_title">商品发布：</td>
								<td>
									<span id="publishType"></span>&nbsp;<span id="publishTime"></span>
									<input type="hidden" id="publishType1" value="<%=PublishTypeEnum.IN_STORE.getCode() %>" />
									<input type="hidden" id="publishType2" value="<%=PublishTypeEnum.PUBLISH.getCode() %>" />
									<input type="hidden" id="publishType3" value="<%=PublishTypeEnum.TIMER_PUBLISH.getCode() %>" />
								</td>
							</tr>
							<tr>
								<td class="table_title">虚拟商品：</td>
								<td>
									<span id="virtual"></span>
									<input type="hidden"id="virtual1" value="<%=VirtualEnum.Y.getCode() %>" />
									<input type="hidden" id="virtual2" value="<%=VirtualEnum.N.getCode() %>" />
								</td>
							</tr>
						</table>
						<div class="bottomToolbar">
							<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 关闭</button>
						</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- content end -->
</body>
<script type="text/javascript" src="${path }/view/js/goodsManager_goodsDetail.js"></script>
<script type="text/javascript" src="${path }/view/js/goodsManager_goodsAdd_img.js"></script>
</html>