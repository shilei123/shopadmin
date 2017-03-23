<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>附件管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<!-- content start -->
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">附件管理</strong> / <small>附件管理</small>
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
										<td style="width:100px;">附件名称：</td>
										<td style="width:200px;"><input name="queryParams.attachName" id="attachName" class="am-form-field"/></td>
										<td style="width:100px;">&nbsp;附件描述：</td>
										<td style="width:200px;"><input name="queryParams.remark" id="remark" class="am-form-field"/></td>
										<td>
											&nbsp;<button type="button" id="openAttachUploadBtn" class="am-btn am-btn-primary frame-search-button">附件上传</button>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="attachListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="15%" field="attachName">附件名称</th>
									<th width="15%" field="attachSize" formatter="formatterAttachSize">附件大小</th>
									<th width="18%" field="kindName">附件类型</th>
									<th width="18%" field="createTime">上传时间</th>
									<th width="15%" formatter="formatterAction">操作</th>
								</tr>
							</thead>
						</table>
					</div>
					<a target="_bank"></a>
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
	
	<!-- 附件上传组件引用 -->
	<%-- <jsp:include page="/view/shop/system/attachUpload.jsp">
		<jsp:param value="false" name="showSelectTab"/>
	</jsp:include> --%>
</body>
<script type="text/javascript">
//打开图片上传窗口
$("#openAttachUploadBtn").click(function() {
	//打开附件上传的窗口
	showAttachUploadModal({showSelectTab:false});
});

//选择附件的回调函数
var selectAttach = function(attachId, attachSrc) {
	console.log("点击了自定义的："+attachId+","+attachSrc);
	$('#attachName').val(attachId);
	$('#remark').val(attachSrc);
};

//上传按钮调用完成之后回调函数
var uploadAfter = function() {
	console.log("调用扩展");
	query();
};

//以下是本页的js
$(function() {
	query();
});

//绑定搜索按钮的分页
$('#queryBtn').click(function() {
	query();
});

var query = function() {
	var data = formGet("from_query");
	var url = path_ + "/view/shop/admin/attachUpload!query.action";
	pageData(url, "attachListTable", data);
};

var formatterAttachSize = function(value, row) {
	return  Math.round(value/1024)+"KB";
};

var formatterAction = function(value, row) {
	//console.log(row.attachName);
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='"+(attachServer_ + "/"+row["attachPath"]+"/"+row["fileName"])+"' target='_bank' download='"+row.attachName+"'><span class='am-icon-download'></span>下载</a>";
	html += "</div>";
	return html;
};

var showImgWin = function(imgSrc) {
	/* $("#nofull-img").attr("src",imgSrc);
	showModal("doc-modal-1",500,400); */
};
</script>
</html>
