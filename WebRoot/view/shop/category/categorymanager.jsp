<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>类别管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf ">
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-cf am-padding am-padding-bottom-0">
        		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">商品类别管理</strong> / <small>商品类别新增、修改、删除</small></div>
      		</div>
      		<hr>
	      	<div class="am-g">
		      	<div class="am-u-sm-12">
					<div class="am-btn-toolbar frame-div-paddbottom20">
			            <div class="am-btn-group am-btn-group-sm">
			              <button type="button" class="am-btn am-btn-primary" id="addCategoryBtn"><span class="am-icon-plus"></span> 新增</button>
			              <button type="button" class="am-btn am-btn-success" id="editCategoryBtn"><span class="am-icon-edit"></span> 编辑</button>
			              <button type="button" class="am-btn am-btn-danger" id="delCategoryBtn"><span class="am-icon-trash-o"></span> 删除</button> 
			              <button type="button" class="am-btn am-btn-primary" id="openCategoryParamsBtn"><span class="am-icon-cog"></span> 类别属性配置</button> 
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
  	
  	<script type="text/javascript" src="${path }/view/js/category_categorymanage.js"></script>
  	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-add">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="modalTitle-add"></span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
				<form id="form1" method="post">
	    		<table border="0" bordercolor="red" align="center" id="category_detail_table">
	    			<tr>
						<td width="150">
							<div class="frame-div-padd10">
		        				<label for="cateName" class="am-form-label">类别名称：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="hidden" id="inp2-id" name="category.id" style="width:300px;"/>
		        				<input type="hidden" id="inp2-parentId" name="category.parentId" style="width:300px;"/>
		        				<input type="hidden" id="inp2-levels" name="category.levels" style="width:300px;"/>
		        				<input type="text" name="category.cateName" id="inp2-cateName" style="width:300px;" placeholder="请输入类别名称"/>
		        			</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="frame-div-padd10">
		        				<label for="category.cateOrder" class="am-form-label">排序序号：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="text" name="category.cateOrder" id="inp2-cateOrder" style="width: 300px;" placeholder="请输入排序序号">
		        			</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="frame-div-padd10">
		        				<label for="memo" class="am-form-label">类别描述：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="text" name="category.memo" id="inp2-memo" style="width: 300px;" placeholder="请输入类别描述"/>
		        				
		        			</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="frame-div-padd10">
		        				<label for="logo" class="am-form-label">类别图标：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="text" name="category.logo" id="inp2-logo" style="width: 300px;"/>
		        				<button>上传</button>
		        			</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="frame-div-padd10">
		        				<label for="url" class="am-form-label">导航链接：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="text" name="category.url" id="inp2-url" style="width: 120px;"/>
		        			</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="frame-div-padd10">
		        				<label for="isuse" class="am-form-label">是否有效：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<select name="category.isuse" id="inp2-isuse">
		        					<option value="">--请选择--</option>
		        					<option value="1">有效</option>
		        					<option value="0">无效</option>
		        				</select>
		        			</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="am-btn am-btn-success" id="saveCategoryBtn"><span class="am-icon-save"></span> 保存</button>
							<button type="button" class="am-btn am-btn-success" id="editSaveCategoryBtn" style="display:none"><span class="am-icon-save"></span> 保存</button>
							<button type="button" class="am-btn am-btn-default" id="cancelBtn"><span class="am-icon-close"></span> 取消</button>
						</td>
					</tr>
	    		</table>
	    		</form>
	    	</div>
		</div>
	</div>
  	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="modalTitle">类别配置</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
	      		<iframe id="categoryParamsFrame" scrolling="no" frameborder="0" style="width:100%; height: 360px;overflow: hidden;overflow-x:hidden;overflow-y:hidden;"></iframe>
	    	</div>
		</div>
	</div>
</div>
</body>
</html>
