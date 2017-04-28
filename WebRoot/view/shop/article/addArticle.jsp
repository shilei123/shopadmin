<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%
String articleId = request.getParameter("articleId")==null?"":request.getParameter("articleId");
//request.setAttribute("noticeId", noticeId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>文章发布</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"href="assets/i/app-icon72x72@2x.png">
<script type="text/javascript" src="${path }/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${path }/ueditor/ueditor.all.js"></script>
<style type="text/css">
tr {
	height: 50px;
}
.img {
	width: 100px;
	height: 100px; 
	border:0px solid #D1DEEA;
	background-image: url('../../../images/add.png')
}
.imgDiv {
	display: inline-block;cursor: pointer;position: relative;
}
.closediv {
	position: absolute;
	bottom: 0;	
	right: 0;
	left: 0;
	display: none;
	background-color: #606060;
	width:95%;
	margin-left: 0px;
	margin-bottom: 1px;
	text-align: center;
}

.closediv>span {
	color: white;
}

</style>
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">文章发布</strong> / <small>文章的发布</small>
					</div>
					<div align="left" id="errorMsg" style="margin-left:350px;color: red;">&nbsp;</div>
				</div>
				<hr>
				<div style="margin: 0 auto;">
					<input type="hidden" id="articleId" name="queryParams.id" placeholder="文章ID" class="am-form-field" value="<%=articleId %>"/>
  					<table class="frame-query-table" border="0" bordercolor="black" style="width: 850px;margin-left: 20px;">
					    <tr>
					    	<td style="width: 140px;" class="table_title"><span style="color:red;">*</span>&nbsp;文章标题：</td>
							<td style="width: 500px;">
								<input name="art.articleTitle" id="articleTitle" minlength="50" class="am-form-field" style="width: 90%;"/>
							</td>
							<td style="width: 120px;" class="table_title"><span style="color:red;">*</span>&nbsp;作者：</td>
							<td style="width: 500px;">
								<input name="art.author" id="author" minlength="50" class="am-form-field" style="width: 90%;"/>
							</td>
					   	</tr>
					    <tr>
					  	    <td style="width: 120px;" class="table_title">&nbsp;&nbsp;摘要：</td>
			  				<td valign="top" colspan="3">
			  					<textarea name="atr.abstracts" id="abstracts" rows="5" placeholder="摘要" style="width:96%;height:75px;margin-top: 5px;" class="am-form-field" ></textarea>
			  				</td>
					    </tr>
					   	<tr>
					   		<td style="width: 100px;" class="table_title"><span style="color:red;">*</span>&nbsp;分类：</td>
					    	<td style="width: 200px;">
								<select name="art.articleType" id="articleType" data-am-selected="{btnWidth: '152px'}">
							</td>
							<td style="width: 140px;" class="table_title"><span style="color:red;">*</span>&nbsp;文章目录：</td>
					    	<td style="width: 200px;">
								<select name="art.directoryType" id="directoryType" data-am-selected="{btnWidth: '152px'}">
							</td>
					   	</tr>
					   	<tr>
							<td style="width: 100px;" class="table_title"><span style="color:red;">*</span>&nbsp;来源：</td>
							<td style="width: 500px;">
								<input name="art.source" id="source" minlength="50" class="am-form-field" style="width: 90%;"/>
							</td>
							<td style="width: 120px;" class="table_title">URL路径：</td>
							<td style="width: 500px;">
								<input name="art.url" id="url" minlength="200" class="am-form-field" style="width: 90%;"/>
							</td>
					   	</tr>
					   	<tr>
					   		<td style="width: 120px;" class="table_title">引导图：</td>
							<td style="width: 120px;">
								<div class="imgDiv">
			        			<div id="close1" class="closediv">
											<!-- <span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp; -->
											<span class='am-icon-close' style="width: auto;">删除</span>
								</div>
			        			 <img id="img1" alt="" src="" class="img">
								<input type="hidden" id="imgIdHidden" class="am-form-field" name="logistics.url" style="width:90%"/>
			        			<div class="imgDiv">
							</td>
 -->					   	</tr>
						<tr>
					     	<td colspan="4"><textarea class="am-validate" name="myue" id="memo" onchange="change()" style="height: 280px;width: 98%;"></textarea></td>
					    </tr>
					    <tr>
					    	<td colspan="4">
							    <div class="am-form-group">
								    <label class="am-u-sm-3 am-form-label"><span style="color:red;">*</span>&nbsp;是否允许评论：</label>
								    <div class="am-u-sm-9">
								    	<input type="hidden" name="atr.iscontent" id="iscontent"/>
										<label class="am-radio-inline am-danger">
											<input id="iscontenty"  type="radio" name="iscontent" checked="checked" data-am-ucheck value="1" /> 是
										</label>
										<label class="am-radio-inline am-danger">
											<input id="iscontentn"  type="radio" name="iscontent" data-am-ucheck value="0"/> 否
										</label>
									</div>
								</div>
							</td>
					    </tr>
						<tr>
					     	<td colspan="4">
					     		<div align="center">
								    <button class="am-btn am-btn-secondary" type="button" id="saveBtn">保存</button>
								    <button class="am-btn am-btn-secondary" type="button" id="closeBtn" style="display:<%=StringUtils.isBlank(articleId)?"":"none" %>">重置</button>
								    <button class="am-btn am-btn-secondary" type="button" id="cancelBtn" style="display:<%=StringUtils.isBlank(articleId)?"none":"" %>">取消</button>
								</div>
					     	</td>
					    </tr>
			  		</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/article_addArticle.js"></script>
</html>