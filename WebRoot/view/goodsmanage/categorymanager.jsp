<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品类别管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<form id="form1" method="post">
<div class="am-cf ">
  	<!-- content start -->
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
			              <button type="button" class="am-btn am-btn-primary" id="addAgencyBtn"><span class="am-icon-plus"></span> 新增</button>
			              <button type="button" class="am-btn am-btn-success" id="saveAgencyBtn"><span class="am-icon-save"></span> 保存</button>
			              <button type="button" class="am-btn am-btn-danger" id="delAgencyBtn"><span class="am-icon-trash-o"></span> 删除</button> 
			              <button type="button" class="am-btn am-btn-primary" id="openAgencyParamsBtn"><span class="am-icon-cog"></span> 机构参数配置</button> 
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
						  			<div class="am-panel-bd frame-no-padding" id="agencyTreeContent">
			       						<ul id="ul_agency_tree"></ul>
						  			</div>
								</div>
							</td>
							<td>&nbsp;</td>
							<td>
								<div class="am-panel am-panel-default">
							  		<div class="am-panel-hd">商品类别信息</div>
							  		<div class="am-panel-bd frame-no-padding">
							  			<table class="frame-query-table" id="agency_detail_table"><!-- border="1" bordercolor="red"> -->
											<tr>
												<td width="150">
													<div class="frame-div-padd10">
								        				<label for="inp_parentAgencyName" class="am-form-label">上级商品类别：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_parentAgencyName" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_parentShortName" class="am-form-label">上级机构简称：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_parentShortName" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_agencyName" class="am-form-label">机构名称：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" name="tree.agencyName" id="inp_agencyName" autocomplete="off" style="width: 300px;" placeholder="请输入机构名称" required>
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_shortName" class="am-form-label">机构简称：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" name="tree.shortName" id="inp_shortName" autocomplete="off" style="width: 300px;" placeholder="请输入机构简称" required>
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
								        				<input type="text" name="tree.order" id="inp_order" autocomplete="off" style="width: 120px;" placeholder="请输入排序序号" required class="number"/>
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_sts" class="am-form-label">机构状态：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<select id="inp_sts" name="tree.sts" required>
								        					<option value=""></option>
								        					<option value="1">启用</option>
								        					<option value="0">停用</option>
								        				</select>
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_initFlag" class="am-form-label">初始化网体：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<select id="inp_initFlag" name="tree.initFlag" disabled="disabled">
								        					<option value=""></option>
								        					<option value="1">已初始化</option>
								        					<option value="0">未初始化</option>
								        				</select>
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_agencyId" class="am-form-label">机构编码：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
														<input type="hidden" name="tree.id" id="inp_id">
														<input type="hidden" name="tree.parentAgencyId" id="inp_parentAgencyId">
								        				<input type="text" id="inp_agencyId" style="width: 300px;" disabled="disabled">
								        			</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frame-div-padd10">
								        				<label for="inp_agencyPath" class="am-form-label">机构全路径：</label>
								        			</div>
												</td>
												<td>
													<div class="frame-div-padd10">
								        				<input type="text" id="inp_agencyPath" style="width: 300px;" disabled="disabled">
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
  	<!-- content end -->
  	
  	<script type="text/javascript" src="${path }/view/js/goodsmanage_categorymanage.js"></script>
  	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="modalTitle">机构参数配置</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
	      		<iframe id="agencyParamsFrame" scrolling="no" frameborder="0" style="width:100%; height: 360px;overflow: hidden;overflow-x:hidden;overflow-y:hidden;"></iframe>
	    	</div>
		</div>
	</div>
</div>
</form>
</body>
</html>