<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>图片管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<!-- content start -->
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">图片管理</strong> / <small>图片管理</small>
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
										<td style="width:100px;">图片名称：</td>
										<td style="width:200px;"><input name="queryParams.imgName" id="imgName" class="am-form-field"/></td>
										<td style="width:100px;">&nbsp;图片描述：</td>
										<td style="width:200px;"><input name="queryParams.remark" id="remark" class="am-form-field"/></td>
										<td>
											&nbsp;<button type="button" id="openImageUploadBtn" class="am-btn am-btn-primary frame-search-button">图片上传</button>
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
						<!-- <table class="am-table am-table-striped am-table-hover table-main" id="bankListTable"> -->
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="imgListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="15%" field="imgName">图片名称</th>
									<th width="15%" field="imgSize" formatter="formatterImgSize">文件大小</th>
									<th width="18%" field="imgType">图片类型</th>
									<th width="18%" field="createTime">上传时间</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
		 	<div class="am-modal-hd"><span id="title">查看缩略图</span>
		    	<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
		    </div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<img id="nofull-img" alt="" src="" width="100%" height="300">
	           	</div>
	    	</div>
		</div>
	</div>
	<img id="full-img" alt="" src="" width="100%" height="100%" style="display: none;">
	
	<!-- 图片上传组件引用 -->
	<%-- <jsp:include page="/view/shop/system/imageUpload.jsp">
		<jsp:param value="false" name="showSelectTab"/>
	</jsp:include> --%>
</body>
<script type="text/javascript">
//打开图片上传窗口
$("#openImageUploadBtn").click(function() {
	//打开图片上传的窗口
	showImgUploadModal({showSelectTab:true});
});

//选择图片的回调函数
var selectImg = function(obj) {
	$('#imgName').val(obj.id);
	$('#remark').val(obj.src);
};

//上传按钮调用完成之后回调函数
var uploadAfter = function() {
	console.log("调用扩展");
	query();
};

$(function() {
	query();
});

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/admin/imageUpload!query.action";
	pageData(url, "imgListTable", data);
};

var formatterImgSize = function(value, row) {
	return  Math.round(value/1024)+"KB";
};

var formatterAction = function(value, row) {
	var imgSrc = "http://192.168.0.207:9081/"+row["imgPath"]+"/"+row["fileName"];
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='showImgWin(\""+ imgSrc+ "\")'><span class='am-icon-search'></span>缩略图查看</a>";
	html += "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showFullImgWin(\""+ imgSrc+ "\")'><span class='am-icon-search'></span>全屏查看</a>";
	html += "</div>";
	return html;
};

var showImgWin = function(imgSrc) {
	$("#nofull-img").attr("src",imgSrc);
	showModal("doc-modal-1",500,400);
};

var showFullImgWin = function(imgSrc) {
	$("#full-img").attr("src",imgSrc);
	$("#full-img").css("display","");
	$("#full-img").click();
};

var fullscreen = $.AMUI.fullscreen;
$('#full-img').on('click', function () {
	if (fullscreen.enabled) {
		fullscreen.request(this);
	}
}).on(fullscreen.raw.fullscreenchange, function () {
	$("#full-img").css("display",(fullscreen.isFullscreen ? "" : "none"));
}); 
</script>
</html>
