<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品录入</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style type="text/css">

</style>
</head>
<body>
	<!-- content start -->
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">商品录入</strong> / <small>选择分类</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<div class="am-panel am-panel-primary">
							<table class="frame-modal-table" border="0" style="margin-top: 10px;">
								<tr>
									<td style="width: 25%;text-align: center;">
										<select id="firstCategory" size="2" style="width: 90%;height:300px;overflow: auto;cursor: pointer;">
										</select>
									</td>
									<td style="width: 25%;text-align: center;">
										<select id="secondCategory" size="2" style="width: 90%;height:300px;overflow: auto;cursor: pointer;">
										</select>
									</td>
									<td style="width: 25%;text-align: center;">
										<select id="threeCategory" size="2" style="width: 90%;height:300px;overflow: auto;cursor: pointer;">
										</select>
									</td>
									<td style="width: 25%;text-align: center;">
										<select id="fourCategory" size="2" style="width: 90%;height:300px;overflow: auto;cursor: pointer;">
										</select>
									</td>
								</tr>
							</table>
							<div align="left" style="margin-top: 10px;margin-left: 20px;">
								<input type="hidden" id="selectValue"/>
								<span id="prefixTitle">请选择的商品类别</span>
								<span id="selectText"></span>
							</div>
							<div align="center" style="margin-bottom: 10px;">
								<button type="button" class="am-btn am-btn-primary am-btn-xl" id="nextBtn"><span class="am-icon-arrow-right"></span> 下一步 </button>
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
	queryCategory($("#firstCategory"),"");
});

$("#nextBtn").click(function() {
	var first = $("#firstCategory option:selected").val();
	if(first==undefined || first.length==0) {
		showTopTips('请选择分类', 'firstCategory');
		$("#firstCategory").focus();
		return;
	}
	$("#selectValue").val(first);
	
	var second = $("#secondCategory option:selected").val();
	if(second==undefined || second.length==0) {
		showTopTips('请选择分类', 'secondCategory');
		$("#secondCategory").focus();
		return;
	}
	$("#selectValue").val(second);
	
	var three = $("#threeCategory option:selected").val();
	if(three==undefined || three.length==0) {
		showTopTips('请选择分类', 'threeCategory');
		$("#threeCategory").focus();
		return;
	}
	$("#selectValue").val(three);
	
	var four = $("#fourCategory option:selected").val();
	if(four!=undefined && four.length>0) {
		$("#selectValue").val(four);
	}
	
	window.location.href = path_ + "/view/shop/goods/goodsAdd.jsp?selectValue=" + $("#selectValue").val() + "&selectText=" + $("#selectText").text();
});

$("#firstCategory").change(function() {
	queryCategory($("#secondCategory"),this.value);
});

$("#secondCategory").change(function() {
	queryCategory($("#threeCategory"),this.value);
});

$("#threeCategory").change(function() {
	queryCategory($("#fourCategory"),this.value);
});

$("#fourCategory").change(function() {
});

var queryCategory = function(selectObj, cateId) {
	$.ajax({
		type : "POST",
		url:  path_ + "/view/category/category!queryGategory.action",
		data : {"category.id":cateId},
		dataType : "json",
		success : function(json) {
			if(json!=null) {
				buildSelectOptions(selectObj, json.categorys);
				buildSelectText();
			}
		},
		error : function(e) {
			//showAlert("操作失败！");
		}
	});
};

var buildSelectOptions = function(selectObj, jsonList) {
	selectObj.empty();
	if(jsonList.length==0)	return;
	/* var html = "<option value='-1'>-请选择-</option>"; */
	var html = "";
	$(jsonList).each(function(index) {
		var row = jsonList[index];
		html += "<option value='" + row.id + "'>" + row.cateName + "</option>";
	});
	selectObj.append(html);
};

var buildSelectText = function() {
	var allText = "";
	var first = $("#firstCategory option:selected").text();
	if(first!=undefined && first.length>0) {
		allText += first;
	}
	var second = $("#secondCategory option:selected").text();
	if(second!=undefined && second.length>0) {
		allText += " > " + second;
	}
	
	var three = $("#threeCategory option:selected").text();
	if(three!=undefined && three.length>0) {
		allText += " > " + three;
	}
	
	var four = $("#fourCategory option:selected").text();
	if(four!=undefined && four.length>0) {
		allText += " > " + four;
	}
	if(allText.length>0) {
		$("#prefixTitle").html("您当前选择的商品类别是：");
		$("#selectText").html("<strong>" + allText + "</strong>");
	}
};
</script>
</html>