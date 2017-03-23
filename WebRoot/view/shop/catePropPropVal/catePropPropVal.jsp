<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>类别属性属性值管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf ">
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-cf am-padding am-padding-bottom-0">
        		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">类别属性属性值管理</strong> / <small>类别与属性属性值的配置</small></div>
      		</div>
      		<hr>
			<div class="am-g">
				<div class="am-u-sm-12">
					<table class="frame-query-table">
						<tr>
							<td width="20%" valign="top">
								<div class="am-panel am-panel-default">
							  		<div class="am-panel-hd">类别列表</div>
						  			<div class="am-panel-bd frame-no-padding" id="CategoryTreeContent">
			       						<ul id="ul_category_tree"></ul>
						  			</div>
								</div>
							</td>
							<td>&nbsp;</td>
							<td>
								<div class="am-panel am-panel-default">
							  		<div class="am-panel-hd">属性和属性值信息</div>
							  		<div class="am-panel-bd frame-no-padding">
							  			<table class="frame-query-table">
											<tr>
												<td width="150">
													<div class="frame-div-padd10">
								        				<label for="inp_categoryName" class="am-form-label">类别名称：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_categoryName" style="width: 300px;" disabled="disabled">
								        				<input type="hidden" id="inp_categoryId" style="width: 300px;" disabled="disabled">
								        				<input type="hidden" id="inp_levels" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
										</table>
							  		</div>
								</div>
							</td>
						</tr>
					</table>
        		</div>
      		</div>
    	</div>
  	</div>
  	<script type="text/javascript" src="${path }/view/js/category_categorymanage.js"></script>
</div>
</body>
</html>