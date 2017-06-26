<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String showSelectTab = request.getParameter("showSelectTab");
	String disSelectPicTab = "";
	if(showSelectTab!=null && "true".equals(showSelectTab)) {
		disSelectPicTab = "";
	} else {
		disSelectPicTab = "none";
	}
%>
<<script type="text/javascript">
<!--
var showSelectTab = "<%=showSelectTab %>";
//-->
</script>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="img_upload_modal">
	<div class="am-modal-dialog">
    	<div class="am-modal-hd"><span id="title">图片空间</span>
      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    	</div>
    	<hr>
    	<div class="am-modal-bd frame-am-modal-bd">
    		<div class="am-tabs" data-am-tabs>
			  <ul class="am-tabs-nav am-nav am-nav-tabs">
			    <li class="am-active"><a href="#tab1">上传新图片</a></li>
			    <li style="display: <%=disSelectPicTab %>;"><a href="#tab2">选择图片</a></li>
			  </ul>
			  <div class="am-tabs-bd">
			    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
			    	<p><input type="file" id="img" name="img" /></p>
   					<input type="button" id="imgUploadBtn" value="上传" /><i id="imgUploadLoading" class="am-icon-spinner am-icon-pulse" style="display: none;"></i>
			    </div>
			    <div class="am-tab-panel am-fade" id="tab2" style="display: <%=disSelectPicTab %>;">
			    	<div>
			     		<select>
			     			<option type="1">按时间从晚到早</option>
			     		</select>
			     		<!-- <a href='javascript:void(0)' id="imageUploadRefresh"><span class='am-icon-refresh'></span>刷新</a> -->
			     		<table id="from_query_img_upload" class="frame-query-table"></table>
			     	</div>
			     	<div>
				     	<table cellpadding="0" cellspacing="0" border="0" id="imageUploadResultTable"></table>
			     	</div>
					<div class="am-cf">共<span id="rowCountImgUpload">0</span>条记录<div id="pageImgUpload" class="am-fr"></div></div>
			    </div>
			  </div>
			</div>
    	</div>
	</div>
</div>
<script type="text/javascript" src="${path }/view/js/system_imageUpload.js"></script>