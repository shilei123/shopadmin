<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	String selectValue = request.getParameter("selectValue");
	String selectText = new String(request.getParameter("selectText").getBytes("ISO-8859-1"),"UTF-8");
%>
<script type="text/javascript">
	var cateId = "<%=selectValue %>";
</script>
</head>
<body>
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
										<%=selectText %>&nbsp;<button class="am-btn am-btn-warning am-btn-xs am-round" onclick="window.location.href='${path}/view/shop/goods/goodsTypeSelect.jsp'">编辑</button>
										</input type="hidden" id="cateId" value="<%=selectValue %>"/>
									</td>
								</tr>
								<tr>
									<td class="table_title">商品品牌：</td>
									<td>饰品配件</td>
								</tr>
								<tr>
									<td class="table_title frame-required"><span>*</span>商品名称：</td>
									<td><input name="bankInfo.bankName" id="goodsName" placeholder="商品名称" class="am-form-field" style="width:500px;"/></td>
								</tr>
								<tr>
									<td class="table_title">商品副标题：</td>
									<td><input name="bankInfo.bankName" id="goodsName1" placeholder="商品副标题" class="am-form-field" style="width:600px;"/></td>
								</tr>
								<tr>
									<td class="table_title">商品属性：</td>
									<td><div id="propertyDiv"></div></td>
								</tr>
								<tr>
									<td class="table_title"></td>
									<td>
										<table style="margin-bottom: 5px;">
											<tr>
												<td valign="middle">
													<div id="propertyEditDiv" style="border: 0px;border-color: red;display: inline-block;">
														<table border="1" bordercolor="#CCCCCC" id="propertyEditTable">
															<thead>
																<tr style="height: 25px;background-color: #F5F5F5;">
																</tr>
															</thead>
															<tbody>
																<tr style="height: 30px;">
																</tr>
															</tbody>
														</table>
													</div>
												</td>
												<td valign="middle">
													<div id="addProperty" style="border: 1px solid #CCCCCC;width:52px;height:52px;position:relative;cursor:pointer;margin-left: 1px;">
														<img src="${path}/images/add2.png" style="position: absolute;left:0; right:0; top:0; bottom:0;margin: auto;"/>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="table_title" valign="top">库存配置：</td>
									<td>
										<div id="goodsChildDiv">
											<table border="1" bordercolor="#CCCCCC" id="goodsChildTable">
												<thead>
													<tr style="height: 25px;background-color: #F5F5F5;">
														<th>子商品</th>
														<th>价格</th>
														<th>市场价</th>
														<th>成本价</th>
														<th>库存</th>
														<th>商品货号</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<!-- <tr>
														<td>&nbsp;&nbsp;</td>
														<td>&nbsp;<input style="width:80px;"/>&nbsp;</td>
														<td>&nbsp;<input style="width:80px;"/>&nbsp;</td>
														<td>&nbsp;<input style="width:80px;"/>&nbsp;</td>
														<td>&nbsp;<input style="width:80px;"/>&nbsp;</td>
														<td>&nbsp;<input style="width:80px;"/>&nbsp;</td>
														<td>&nbsp;删除&nbsp;</td>
													</tr> -->
												</tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
							<table class="frame-modal-table" border="0" id="propListTable">
								<!-- <tr>
									<td class="table_title">颜色：</td>
									<td>
									</td>
								</tr>
								<tr>
									<td class="table_title">重量：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="重量" class="am-form-field" style="width:%"/></td>
								</tr>
								<tr>
									<td class="table_title">食品含量：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="食品含量" class="am-form-field" style="width:100%"/></td>
								</tr> -->
							</table>
							<table class="frame-modal-table" border="0">
								<tr>
									<td class="table_title frame-required"><span>*</span>商品价格：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="商品价格" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title frame-required"><span>*</span>市场价：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="市场价" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title frame-required"><span>*</span>成本价：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="成本价" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title frame-required"><span>*</span>商品库存：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="商品库存" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title">商品货号：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="商品货号" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title frame-required" valign="top">
										<input id="imgPosition" style="width: 1px;height: 0px;border: none;"/>
										<span>*</span>商品图片：
									</td>
									<td>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
											<div id="close1" class="closediv">
												<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-close' style="width: auto;"></span>
											</div>
											<img id="img1" alt="" src="" class="img">
											<input type="hidden" name="imgIdHidden"/>
										</div>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
											<div id="close2" class="closediv">
												<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-close' style="width: auto;"></span>
											</div>
											<img id="img2" alt="" src="" class="img">
											<input type="hidden" name="imgIdHidden"/>
										</div>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
											<div id="close3" class="closediv">
												<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-close' style="width: auto;"></span>
											</div>
											<img id="img3" alt="" src="" class="img">
											<input type="hidden" name="imgIdHidden"/>
										</div>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
											<div id="close4" class="closediv">
												<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-close' style="width: auto;"></span>
											</div>
											<img id="img4" alt="" src="" class="img">
											<input type="hidden" name="imgIdHidden"/>
										</div>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
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
										<div style="margin-top: 10px;" id="shuxingDiv">
											<div style="padding: 1px;">
												参数名：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												参数值：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;&nbsp;<a 
												class="addGoodsAttr" href="javascript:void(0);"><span class='am-icon-plus-square' style="width: auto;color: green;font-size: 18px;" title="添加"></span></a>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td class="table_title" valign="top"><div style="margin-top: 10px;">商品描述：</div></td>
									<td>
										<div class="am-tabs" style="margin-top: 10px;" data-am-tabs>
											<ul class="am-tabs-nav am-nav am-nav-tabs">
												<li class="am-active"><a href="#tab1">电脑端</a></li>
												<li><a href="#tab2">手机端</a></li>
											</ul>
										  	<div class="am-tabs-bd">
											    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
											    	<script id="pceditor" name="goods.detail" type="text/plain" style="width:99%;height:300px;"></script>
											    </div>
											    <div class="am-tab-panel am-fade" id="tab2">
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    </div>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td class="table_title" valign="top"><div style="margin-top: 9px;">运费：</div></td>
									<td>
										<div>
											<label class="am-radio am-success"><input type="radio" name="aa" id="ra1" data-am-ucheck/>卖家承担运费</label>
											<label class="am-radio am-success"><input type="radio" name="aa" id="ra2" data-am-ucheck/>买家承担运费</label>
										</div>
									</td>
								</tr>
								<tr>
									<td class="table_title" valign="top"><div style="margin-top: 9px;">商品发布：</div></td>
									<td>
										<div>
											<label class="am-radio am-success"><input type="radio" name="a" id="r3" data-am-ucheck/>放入仓库</label>
											<label class="am-radio am-success"><input type="radio" name="a" id="r1" data-am-ucheck/>立即发布</label>
											<label class="am-radio am-success" style="display: inline-block;"><input type="radio" name="a" id="r2" data-am-ucheck/>发布时间</label>
											<input class="am-form-field" style="display: inline-block;width: 150px;margin-top: -15px;" id="publishTime"></div>
										</div>
									</td>
								</tr>
							</table>
							<div align="center" style="margin-bottom: 10px;margin-top: 10px;">
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
<script type="text/javascript" src="${path }/view/js/goods_goodsAdd.js"></script>
<script type="text/javascript" src="${path }/view/js/goods_goodsAdd_img.js"></script>
</html>