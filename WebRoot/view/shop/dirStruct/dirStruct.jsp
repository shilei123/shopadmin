<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>目录结构管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf ">
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-cf am-padding am-padding-bottom-0">
        		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">目录结构管理</strong> / <small>目录结构的新增、编辑、删除</small></div>
      		</div>
      		<hr>
	      	<div class="am-g">
		      	<div class="am-u-sm-12">
					<div class="am-btn-toolbar frame-div-paddbottom20">
			            <div class="am-btn-group am-btn-group-sm">
			              <button type="button" class="am-btn am-btn-primary" id="addDirectoryBtn"><span class="am-icon-plus"></span> 新增</button>
			              <button type="button" class="am-btn am-btn-success" id="editDirectoryBtn"><span class="am-icon-edit"></span> 编辑</button>
			              <button type="button" class="am-btn am-btn-danger" id="delDirectoryBtn"><span class="am-icon-trash-o"></span> 删除</button> 
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
							  		<div class="am-panel-hd">目录结构列表</div>
						  			<div class="am-panel-bd frame-no-padding" id="CategoryTreeContent">
			       						<ul id="ul_directory_tree"></ul>
						  			</div>
								</div>
							</td>
							<td>&nbsp;</td>
							<td>
								<div class="am-panel am-panel-default">
							  		<div class="am-panel-hd">目录结构信息</div>
							  		<div class="am-panel-bd frame-no-padding">
							  			<table class="frame-query-table">
											<tr>
												<td width="150">
													<div class="frame-div-padd10">
								        				<label for="inp_directoryName" class="am-form-label">栏目名称：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text"  id="inp_dirName" style="width: 300px;" disabled="disabled">
								        				<input type="hidden"  id="inp_dirId" style="width: 300px;" disabled="disabled">
								        				<input type="hidden"  id="inp_level" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
											<tr>
												<td width="150">
													<div class="frame-div-padd10">
								        				<label for="inp_parentName" class="am-form-label">上级栏目：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text"  id="inp_parentName" style="width:300px;" disabled="disabled"/>
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_order" class="am-form-label">排序序号：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text"  id="inp_order" style="width: 300px;" disabled="disabled">
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
								        				<select name="directory.isuse" id="inp_isuse_" disabled="disabled" >
								        					<option value="-1">--请选择--</option>
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
  	
  	<script type="text/javascript" src="${path }/view/js/dirStruct_dirStruct.js"></script>
  	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-add">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="modalTitle-add"></span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
				<form id="form1" method="post">
	    		<table border="0" bordercolor="red" align="center" id="directory_detail_table">
	    			<tr>
						<td width="150">
							<div class="frame-div-padd10">
		        				<label for="directoryName" class="am-form-label">栏目名称：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="hidden"  id="inp2_id" name="directory.id" style="width:300px;"/>
		        				<input type="hidden"  id="inp2_levels" name="directory.levels" style="width:300px;"/>
		        				<input type="text"  name="directory.directoryName" id="inp2_directoryName" style="width:300px;" placeholder="请输入栏目名称"/>
		        			</div>
						</td>
					</tr>
					<tr>
						<td width="150">
							<div class="frame-div-padd10">
		        				<label for="inp2_parentName" class="am-form-label">上级栏目：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<select name="directory.parentDirectoryId" id="inp2_parentName">
		        			</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="frame-div-padd10">
		        				<label for="directory.cateOrder" class="am-form-label">排序序号：</label>
		        			</div>
						</td>
						<td>
							<div class="frame-div-padd10">
		        				<input type="text"  name="directory.cateOrder" id="inp2_cateOrder" style="width: 300px;" placeholder="请输入排序序号">
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
		        				<select name="directory.isuse" id="inp2_isuse">
		        				</select>
		        			</div>
						</td>
					</tr>
	    		</table>
	    		<div align="center" id="errorMsg" style="color: red;margin-top: 5px;margin-bottom: 10px;">&nbsp;</div>
	    		<div align="center">
						<button type="button" class="am-btn am-btn-success" id="saveDirectoryBtn"><span class="am-icon-save"></span> 保存</button>
						<button type="button" class="am-btn am-btn-default" id="cancelBtn"><span class="am-icon-close"></span> 取消</button>
				</div>
	    		</form>
	    	</div>
		</div>
	</div>
</body>
</html>

