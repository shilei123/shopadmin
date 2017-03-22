<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品录入</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<!-- content start -->
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">商品录入</strong> / <small>商品录入</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<div class="am-panel am-panel-primary">
							<table class="frame-modal-table" border="0">
								<tr>
									<td class="table_title">商品分类：</td>
									<td>饰品配件</td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品品牌：</td>
									<td>饰品配件</td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品名称：</td>
									<td><input name="bankInfo.bankName" id="goodsName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"><div id="goodsNameError"></div></td>
								</tr>
								<tr>
									<td class="table_title">颜色：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">重量：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">食品含量：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品货号：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品属性：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品价格：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">市场价：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">成本价：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品库存：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品货号：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品图片：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品描述：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">运费：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
								<tr>
									<td class="table_title">商品发布：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="银行名称" class="am-form-field" style="width:100%"/></td>
									<td class="table_error_info"></td>
								</tr>
							</table>
							<div align="center" style="margin-bottom: 10px;margin-top: 10px;">
								<button type="button" class="am-btn am-btn-success" id="saveBtn"><span class="am-icon-save"></span> 提交</button>
								<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- content end -->
</body>
<script type="text/javascript">
$(function() {
});

$("#saveBtn").click(function() {
	var goodsName = $("#goodsName").val();
	if(goodsName.length==0) {
		$("#goodsNameError").text("商品名称必填");
		$("#goodsName").focus();
	};
});
</script>
</html>