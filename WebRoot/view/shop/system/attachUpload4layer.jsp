<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String showSelectTab = request.getParameter("showSelectTab");
	String disSelectAttachTab = "";
	if(showSelectTab!=null && "true".equals(showSelectTab)) {
		disSelectAttachTab = "";
	} else {
		disSelectAttachTab = "none";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>附件管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript">
<!--
var showSelectTab = "<%=showSelectTab %>";
//-->
</script>
</head>
<body>
	<!-- content start -->
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-g">
					<div class="am-u-sm-12">
						<div class="am-tabs" style="margin-top: 10px;" data-am-tabs>
							<ul class="am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1">上传新附件</a></li>
								<li style="display: <%=disSelectAttachTab %>;"><a href="#tab2">选择附件</a></li>
							</ul>
						  	<div class="am-tabs-bd">
							    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
							    	<p>附件类型：<select id="kind" name="kind"></select></p>
							    	<p><input type="file" id="attach" name="attach" /></p>
				   					<input type="button" id="attachUploadBtn" value="上传" />
							    </div>
							    <div class="am-tab-panel am-fade" id="tab2" style="display: <%=disSelectAttachTab %>;">
							    	<div>
							     		<select>
							     			<option type="1">按时间从晚到早</option>
							     		</select>
							     		<table id="from_query_attach_upload" class="frame-query-table"></table>
							     	</div>
							     	<div>
								     	<table class="am-table am-table-bordered am-table-striped am-table-hover" id="attachUploadResultTable">
								     		<thead>
												<tr>
													<th width="2%" field="index"></th>
													<th width="40%" field="attachName">附件名称</th>
													<th width="10%" field="attachSize" formatter="formatterAttachSize">附件大小</th>
													<th width="10%" field="kindName">附件类型</th>
													<th width="20%" field="createTime">上传时间</th>
													<th width="10%" formatter="formatterAttachAction">操作</th>
												</tr>
											</thead>
								     	</table>
							     	</div>
									<div class="am-cf">共<span id="rowCountAttachUpload"></span>条记录<div id="pageAttachUpload" class="am-fr"></div></div>
							    </div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- content end -->
</body>
<script type="text/javascript" src="${path }/view/js/system_attachUpload4layer.js"></script>