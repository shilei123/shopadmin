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
									<td>
										<select>
										</select>
									</td>
									<td>
										<select>
										</select>
									</td>
									<td>
										<select>
										</select>
									</td>
									<td>
										<select>
										</select>
									</td>
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