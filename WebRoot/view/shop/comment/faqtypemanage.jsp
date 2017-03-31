<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>分类管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf ">
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-cf am-padding am-padding-bottom-0">
        		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">常见问题分类管理</strong> / <small>常见问题分类的查询、新增、修改与删除</small></div>
      		</div>
      		<hr>
	      	<div class="am-g">
		      	<div class="am-u-sm-12">
					<div class="am-btn-toolbar frame-div-paddbottom20">
			            <div class="am-btn-group am-btn-group-sm">
			              <button type="button" class="am-btn am-btn-primary" id="addCategoryBtn"><span class="am-icon-plus"></span> 新增</button>
			              <button type="button" class="am-btn am-btn-success" id="editCategoryBtn"><span class="am-icon-edit"></span> 编辑</button>
			              <button type="button" class="am-btn am-btn-danger" id="delCategoryBtn"><span class="am-icon-trash-o"></span> 删除</button> 
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
							  		<div class="am-panel-hd">常见问题分类列表</div>
						  			<div class="am-panel-bd frame-no-padding" id="CategoryTreeContent">
			       						<ul id="ul_category_tree"></ul>
						  			</div>
								</div>
							</td>
							<td>&nbsp;</td>
							<td>
								<div class="am-panel am-panel-default">
							  		<div class="am-panel-hd">常见问题分类信息</div>
							  		<div class="am-panel-bd frame-no-padding">
							  			<table class="frame-query-table">
											<tr>
												<td width="150">
													<div class="frame-div-padd10">
								        				<label for="inp_typeName" class="am-form-label">分类名称：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_typeName" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_typeDesc" class="am-form-label">分类描述：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_typeDesc" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_typeCode" class="am-form-label">分类编码：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_typeCode" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_typeOrder" class="am-form-label">排序序号：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_typeOrder" style="width: 300px;" disabled="disabled">
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
  	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-add">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="modalTitle-add"></span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
				<form id="form1" method="post">
	    		<table border="0" bordercolor="red" align="center" id="faqType_detail_table">
	    			<tr>
						<td width="150" align="center">
							<div class="frame-div-padd10">
		        				<label for="inp2-typeName" class="am-form-label">分类名称：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="hidden" id="inp2-id" name="faqType.id" style="width:300px;"/>
		        				<input type="hidden" id="inp2-parentTypeId" name="faqType.parentTypeId" style="width:300px;"/>
		        				<input type="hidden" id="inp2-typeLevel" name="faqType.typeLevel" style="width:300px;"/>
		        				<input type="text" name="faqType.typeName" id="inp2-typeName" style="width:300px;" placeholder="请输入分类名称"/>
		        			</div>
						</td>
					</tr>
					<tr>
						<td align="center">
							<div class="frame-div-padd10">
		        				<label for="inp2-typeOrder" class="am-form-label">排序序号：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="text" name="faqType.typeOrder" id="inp2-typeOrder" style="width: 300px;" placeholder="请输入排序序号">
		        			</div>
						</td>
					</tr>
					<tr>
						<td align="center">
							<div class="frame-div-padd10">
		        				<label for="inp2-typeDesc" class="am-form-label">分类描述：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="text" name="faqType.typeDesc" id="inp2-typeDesc" style="width: 300px;" placeholder="请输入分类描述"/>
		        				
		        			</div>
						</td>
					</tr>
					<tr>
						<td align="center">
							<div class="frame-div-padd10">
		        				<label for="inp2-typeCode" class="am-form-label">分类编码：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="text" name="faqType.typeCode" id="inp2-typeCode" style="width: 300px;" placeholder="请输入分类编码"/>
		        				
		        			</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<hr>
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
</div>
<script type="text/javascript" src="${path }/view/js/comment_faqtypemanage.js"></script>
</body>
</html>

