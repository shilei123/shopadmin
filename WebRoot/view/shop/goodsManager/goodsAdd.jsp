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
	String cateId = request.getParameter("cateId")==null?"":request.getParameter("cateId");
	String cateName = request.getParameter("cateName")==null?"":new String(request.getParameter("cateName").getBytes("ISO-8859-1"),"UTF-8");
%>
<script type="text/javascript">
	var goodsId = "<%=goodsId %>";
	var cateId = "<%=cateId %>";
	var cateName = "<%=cateName %>";
</script>
</head>
<body>
	<input type="hidden" id="cateId" value="<%=cateId %>"/>
	<input type="hidden" id="goodsId" value="<%=goodsId %>"/>
	<!-- content start -->
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">商品录入</strong> / <small>商品信息编辑</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<!-- <div class="am-panel am-panel-primary"> -->
						<table class="frame-modal-table" border="0">
							<tr>
								<td class="table_title frame-required"><span>*</span>商品分类：</td>
								<td valign="bottom">
									<span id="cateName"><%=cateName %></span>&nbsp;
									<button class="am-btn am-btn-warning am-btn-xs am-round" 
									onclick="window.location.href='${path}/view/shop/goodsManager/goodsTypeSelect.jsp'">编辑</button>
								</td>
							</tr>
							<tr>
								<td class="table_title">商品品牌：</td>
								<td><input type="hidden" name="goods.brandId" id="brandId" value=""/></td>
							</tr>
							<tr>
								<td class="table_title frame-required"><span>*</span>商品标题：</td>
								<td><input name="goods.title" id="title" placeholder="商品标题" class="am-form-field" style="width:600px;"/></td>
							</tr>
							<tr>
								<td class="table_title">商品副标题：</td>
								<td><input name="goods.subTitle" id="subTitle" placeholder="商品副标题" class="am-form-field" style="width:600px;"/></td>
							</tr>
							<tr>
								<td class="table_title frame-required" valign="top"><span>*</span><div style="margin-top: 9px;display: inline-block;">无库存销售：</div></td>
								<td>
									<label class="am-radio am-success" style="display: inline-block;margin-bottom:5px;"><input type="radio" name="goods.emptyStore" id="emptyStore1" value="<%=EmptyStoreEnum.Y.getCode() %>" data-am-ucheck/>是</label>
									<label class="am-radio am-success" style="display: inline-block;margin-top: 11px;margin-bottom:5px;"><input type="radio" name="goods.emptyStore" id="emptyStore2" value="<%=EmptyStoreEnum.N.getCode() %>" data-am-ucheck/>否</label>
								</td>
							</tr>
							<tr>
								<td class="table_title"><div style="margin-bottom: 12px;display: inline-block;">商品属性：</div></td>
								<td><div id="propertyDiv" style="margin-bottom: 10px;"></div></td>
							</tr>
							<tr style="display:none;">
								<td class="table_title" valign="top"><span id="propValSpan"></span>商品属性值：</td>
								<td>
									<table style="margin-bottom: 10px;">
										<tr>
											<td valign="middle">
												<table id="propertyEditTable"><thead><tr></tr></thead><tbody><tr></tr></tbody></table>
											</td>
											<td valign="middle">
												<div id="addPropertyDiv"><img src="${path}/images/add2.png"/></div><!-- <a href="javascript:void(0);" id="testbtn">test</a> -->
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="display: none;">
								<td class="table_title frame-required" valign="top"><span id="kcpzSpan">*</span>库存配置：</td>
								<td valign="top">
									<div id="goodsChildDiv">
										<table id="goodsChildTable">
											<thead>
												<tr>
													<th>商品属性</th>
													<th><span style="color: red;">*</span>采购价</th>
													<th><span style="color: red;">*</span>市场价</th>
													<th><span style="color: red;">*</span>销售价</th>
													<th><span style="color: red;">*</span>促销价</th>
													<th><span style="color: red;">*</span>库存</th>
													<th>商品货号</th>
													<th>操作</th>
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
								<td class="table_title frame-required"><span>*</span>采购价：</td>
								<td><input name="goods.purchasePrice" id="purchasePrice" placeholder="采购价" class="am-form-field" style="width:auto;"/></td>
							</tr>
							<tr>
								<td class="table_title frame-required"><span>*</span>市场价：</td>
								<td><input name="goods.marketPrice" id="marketPrice" placeholder="市场价" class="am-form-field" style="width:auto;"/></td>
							</tr>
							<tr>
								<td class="table_title frame-required"><span>*</span>销售价：</td>
								<td><input name="goods.salePrice" id="salePrice" placeholder="销售价" class="am-form-field" style="width:auto;"/></td>
							</tr>
							<tr>
								<td class="table_title frame-required"><span>*</span>促销价：</td>
								<td><input name="goods.promotionPrice" id="promotionPrice" placeholder="促销价" class="am-form-field" style="width:auto;"/></td>
							</tr>
							<tr>
								<td class="table_title frame-required"><span>*</span>商品库存：</td>
								<td><input name="goods.availableNum" id="availableNum" placeholder="商品库存" class="am-form-field" style="width:auto;"/></td>
							</tr>
							<tr>
								<td class="table_title">商品货号：</td>
								<td><input name="goods.goodsNo" id="goodsNo" placeholder="商品货号" class="am-form-field" style="width:auto;"/></td>
							</tr>
						</table>
						<table class="frame-modal-table" border="0" style="margin-top:5px;">
							<tr>
								<td class="table_title frame-required" valign="top">
									<input id="imgPosition" style="width: 1px;height: 0px;border: none;"/>
									<span>*</span>商品图片：
								</td>
								<td>
									<div class="imgDiv">
										<div id="close1" class="closediv">
											<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-close' style="width: auto;"></span>
										</div>
										<img id="img1" alt="" src="" class="img">
										<input type="hidden" name="imgIdHidden"/>
									</div>
									<div class="imgDiv">
										<div id="close2" class="closediv">
											<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-close' style="width: auto;"></span>
										</div>
										<img id="img2" alt="" src="" class="img">
										<input type="hidden" name="imgIdHidden"/>
									</div>
									<div class="imgDiv">
										<div id="close3" class="closediv">
											<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-close' style="width: auto;"></span>
										</div>
										<img id="img3" alt="" src="" class="img">
										<input type="hidden" name="imgIdHidden"/>
									</div>
									<div class="imgDiv">
										<div id="close4" class="closediv">
											<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-close' style="width: auto;"></span>
										</div>
										<img id="img4" alt="" src="" class="img">
										<input type="hidden" name="imgIdHidden"/>
									</div>
									<div class="imgDiv">
										<div id="close5" class="closediv">
											<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-close' style="width: auto;"></span>
										</div>
										<img id="img5" alt="" src="" class="img">
										<input type="hidden" name="imgIdHidden"/>
									</div>
								</td>
							</tr>
							<tr>
								<td class="table_title" valign="top"><div style="margin-top: 13px;">商品参数：</div></td>
								<td>
									<div style="margin-top: 9px;" id="paramsDiv">
										<div style="padding: 1px;">
											参数名：<input class="am-form-field" name="paramName" style="width:150px;display: inline;"/>&nbsp;
											参数值：<input class="am-form-field" name="paramVal" style="width:150px;display: inline;"/>&nbsp;&nbsp;<a 
											class="addGoodsParam" href="javascript:void(0);"><span class='am-icon-plus-square' style="width: auto;color: green;font-size: 18px;" title="添加"></span></a>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="table_title frame-required" valign="top"><span>*</span><div style="margin-top: 10px;display: inline-block;">电脑版描述：</div></td>
								<td>
									<script id="pceditor" name="goods.goodsDetail" type="text/plain" style="width:99%;height:300px;margin-top: 10px;"></script>
								</td>
							</tr>
							<tr>
								<td class="table_title frame-required" valign="top"><span>*</span><div style="margin-top: 10px;display: inline-block;">手机版描述：</div></td>
								<td>
									<script id="mobileeditor" name="" type="text/plain" style="width:99%;height:300px;margin-top: 10px;"></script>
								</td>
							</tr>
							<!-- <tr>
								<td class="table_title frame-required" valign="top"><span>*</span><div style="margin-top: 10px;display: inline-block;">商品描述：</div></td>
								<td>
									<div class="am-tabs" style="margin-top: 10px;" data-am-tabs>
										<ul class="am-tabs-nav am-nav am-nav-tabs">
											<li class="am-active"><a href="#tab1" id="tab1">电脑端</a></li>
											<li><a href="#tab2">手机端</a></li>
										</ul>
									  	<div class="am-tabs-bd">
										    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
										    	<textarea id="pceditor" name="goods.goodsDetail" type="text/plain" style="width:99%;height:300px;"></textarea>
										    </div>
										    <div class="am-tab-panel am-fade" id="tab2">
										    	<p>手机端详情</p>
										    </div>
										</div>
									</div>
								</td>
							</tr> -->
							<tr>
								<td class="table_title frame-required" valign="top"><span>*</span><div style="margin-top: 9px;display: inline-block;">运费：</div></td>
								<td>
									<label class="am-radio am-success"><input type="radio" name="goods.freightType" id="freightType1" value="<%=FreightTypeEnum.BIZ.getCode() %>" data-am-ucheck/>卖家承担运费</label>
									<label class="am-radio am-success"><input type="radio" name="goods.freightType" id="freightType2" value="<%=FreightTypeEnum.BUYER.getCode() %>" data-am-ucheck/>买家承担运费</label>
									<input type="hidden" id="freightId">
								</td>
							</tr>
							<tr>
								<td class="table_title frame-required" valign="top"><span>*</span><div style="margin-top: 9px;display: inline-block;">商品发布：</div></td>
								<td>
									<label class="am-radio am-success"><input type="radio" name="goods.publishType" id="publishType1" value="<%=PublishTypeEnum.IN_STORE.getCode() %>" data-am-ucheck/>放入仓库</label>
									<label class="am-radio am-success"><input type="radio" name="goods.publishType" id="publishType2" value="<%=PublishTypeEnum.PUBLISH.getCode() %>" data-am-ucheck/>立即发布</label>
									<label class="am-radio am-success" style="display: inline-block;"><input type="radio" name="goods.publishType" id="publishType3" value="<%=PublishTypeEnum.TIMER_PUBLISH.getCode() %>" data-am-ucheck/>发布时间</label>
									<input class="am-form-field" style="display: inline-block;width: 150px;margin-top: -15px;" id="publishTime"></div>
								</td>
							</tr>
							<tr>
								<td class="table_title frame-required" valign="top"><span>*</span><div style="margin-top: 9px;display: inline-block;">虚拟商品：</div></td>
								<td>
									<label class="am-radio am-success" style="display: inline-block;"><input type="radio" name="goods.virtual" id="virtual1" value="<%=VirtualEnum.Y.getCode() %>" data-am-ucheck/>是</label>
									<label class="am-radio am-success" style="display: inline-block;margin-top: 11px;"><input type="radio" name="goods.virtual" id="virtual12" value="<%=VirtualEnum.N.getCode() %>" data-am-ucheck/>否</label>
								</td>
							</tr>
						</table>
						<div class="bottomToolbar">
							<button type="button" class="am-btn am-btn-success" id="saveBtn"><span class="am-icon-save"></span> 提交</button>
							<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
						</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- content end -->
</body>
<script type="text/javascript" src="${path }/view/js/goodsManager_goodsAdd.js"></script>
<script type="text/javascript" src="${path }/view/js/goodsManager_goodsAdd_img.js"></script>
</html>