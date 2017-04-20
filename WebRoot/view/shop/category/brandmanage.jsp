<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>品牌管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style>
  #vld-tooltip {
    position: absolute;
    z-index: 1000;
    padding: 5px 10px;
    background: #F37B1D;
    min-width: 150px;
    color: #fff;
    transition: all 0.15s;
    box-shadow: 0 0 5px rgba(0,0,0,.15);
    display: ;
  }

  #vld-tooltip:before {
    position: absolute;
    top: -8px;
    left: 50%;
    width: 0;
    height: 0;
    margin-left: -8px;
    content: "";
    border-width: 0 8px 8px;
    border-color: transparent transparent #F37B1D;
    border-style: none inset solid;
  }
</style>
</head>
<body>
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">品牌管理</strong> / <small>品牌的新增、删除、修改、查看</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<div class="am-panel am-panel-primary">
							<div class="am-panel-bd am-collapse am-in frame-search-panel"id="collapse-panel-1">
								<table id="from_query" class="frame-query-table" border="0" bordercolor="black">
									<tr>
										<td style="width:100px;">品牌名称：</td>
										<td style="width:200px;"><input name="queryParams.brandName" class="am-form-field"/></td>
										<td style="width:100px;">品牌编码：</td>
										<td style="width:200px;"><input name="queryParams.brandCode" class="am-form-field"/></td>
										<td>
											<button type="button" id="addBrandBtn" class="am-btn am-btn-primary frame-search-button">新增</button>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="brandListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="15%" field="brandName">品牌名称</th>
									<th width="15%" field="brandCode">品牌编码</th>
									<th width="10%" field="brandLogo">品牌logo</th>
									<th width="15%" field="createTime">创建时间</th>
									<th width="40%" formatter="formatterAction">操作</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="am-u-sm-12">
						<div class="am-cf">共<span id="rowCount"></span>条记录<div id="page" class="am-fr"></div></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">新增品牌</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
	    		<form class="am-form" id="ue-form">
		        <div align="center">
		        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td width="100" class="table_title">品牌名称：</td>
			        		<td>
			        			<div class="frame-div-padd10">
			        				<input name="brand.brandName" id="brandName" placeholder="品牌名称" class="am-form-field" style="width:90%" minlength="3" required/>
			        			</div>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">品牌编码：</td>
			        		<td>
			        			<div class="frame-div-padd10">
				        			<input name="brand.brandCode" id="brandCode" placeholder="品牌编码" class="am-form-field" style="width:90%" required/>
				        			<input name="brand.id" id="brandId" type="hidden"/>	
				        			<input name="brand.flag" id="brandFlag" type="hidden"/>
				        		</div>	
			        		</td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">品牌logo：</td>
			        		<td>
			        			<div class="frame-div-padd10" style="display: inline-flex;">
			        				<input name="brand.brandLogo" id="brandLogo" class="am-form-field" style="width:80%"/>
			        				<button style='width:50px;'>上传</button>
			        			</div>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">品牌简介：</td>
			        		<td>
			        			<div class="frame-div-padd10">
			        				<!-- <input name="brand.brandDesc" id="brandDesc" class="am-form-field" style="width:90%"/> -->
			        				<textarea rows="" cols="" id="brandDesc" style="width:90%;height:100px;margin-top: 5px;" class="am-form-field"></textarea> 
			        			</div>
			        		</td>
			        	</tr>
		       	 	</table>
		       	 	<div align="center" id="errorMsg" style="color: red;margin-top: 5px;margin-bottom: 10px;">&nbsp;</div>
		       	 	<div align="center">
						<button type="button" class="am-btn am-btn-success" id="saveBtn"><span class="am-icon-save"></span> 保存</button>
						<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
					</div>
	           	</div>
	           	</form>
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/category_brandmanage.js"></script>
</html>
