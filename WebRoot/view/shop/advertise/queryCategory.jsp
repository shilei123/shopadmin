<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>类别选择</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf ">
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-cf am-padding am-padding-bottom-0">
        		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">类别选择</strong> / <small>商品类别的选择</small></div>
      		</div>
      		<hr>
	      	<div class="am-g">
		      	<div class="am-u-sm-12">
					<div class="am-btn-toolbar frame-div-paddbottom20">
			            <div class="am-btn-group am-btn-group-sm">
			              <button type="button" class="am-btn am-btn-primary" id="saveBtn"><span class=am-icon-search></span> 选择</button>
			              <button type="button" class="am-btn am-btn-success" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
			            </div>
		          	</div>
				</div>
		  	</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<table class="frame-query-table">
						<tr>
							<td width="20%" valign="top">
								<div class="am-panel am-panel-default">
							  		<div class="am-panel-hd">商品类别列表</div>
						  			<div class="am-panel-bd frame-no-padding" id="CategoryTreeContent">
			       						<ul id="ul_category_tree"></ul>
						  			</div>
								</div>
							</td>
							<td>&nbsp;</td>
							<td>
								<div class="am-panel am-panel-default">
							  		<div class="am-panel-hd">商品类别信息</div>
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
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_memo" class="am-form-label">类别描述：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_memo" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_cateOrder" class="am-form-label">排序序号：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_cateOrder" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_logo" class="am-form-label">类别图标：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_logo" style="width: 300px;" disabled="disabled"/>
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_url" class="am-form-label">导航链接：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_url" style="width: 120px;" disabled="disabled"/>
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_isuse" class="am-form-label">是否有效：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<!-- <input type="text" id="inp_isuse" style="width: 120px;" disabled="disabled"/> -->
								        				<select name="category.isuse" id="inp_isuse" disabled="disabled">
								        					<option value="">--请选择--</option>
								        					<option value="1">有效</option>
								        					<option value="0">无效</option>
								        				</select>
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
<script type="text/javascript" src="${path }/view/js/queryCategocy.js"></script>
</div>
</body>
</html>

