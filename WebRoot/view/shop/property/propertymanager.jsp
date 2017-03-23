<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>属性管理</title>
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
						<strong class="am-text-primary am-text-lg">属性管理</strong> / <small>属性的新增、删除、修改、查看</small>
					</div>
				</div>
				<hr>
				<div class="am-g">

					<div class="am-u-sm-12">
						<div class="am-panel am-panel-primary">
							<!-- <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}">查询条件<span class="am-icon-chevron-down am-fr"></span></div> -->
							<div class="am-panel-bd am-collapse am-in frame-search-panel"id="collapse-panel-1">
								<table id="from_query" class="frame-query-table" border="0" bordercolor="black">
									<tr>
										<td style="width:100px;">属性名称：</td>
										<td style="width:200px;"><input name="queryParams.propName" class="am-form-field"/></td>
										<td style="width:100px;">属性编码：</td>
										<td style="width:200px;"><input name="queryParams.propCode" class="am-form-field"/></td>
										<td>
											<button type="button" id="addPropertyBtn" class="am-btn am-btn-primary frame-search-button">新增</button>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="propertyListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="15%" field="propName">属性名称</th>
									<th width="10%" field="propCode">属性编码</th>
									<th width="10%" field="propOrder">属性排序</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-2">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">新增属性</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
	    		<form class="am-form" id="ue-form">
		        <div align="center">
		        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td width="100" class="table_title">属性名称：</td>
			        		<td><input name="property.propName" id="propName" placeholder="属性名称" class="am-form-field" style="width:90%" minlength="3" required/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">属性编码：</td>
			        		<td>
			        			<input name="property.propCode" id="propCode" placeholder="属性编码" class="am-form-field" style="width:90%" required/>
			        			<input name="property.id" id="propId" type="hidden"/>	
			        			<input name="property.flag" id="propFlag" type="hidden"/>	
			        		</td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">属性排序：</td>
			        		<td><input name="property.propOrder" id="propOrder" placeholder="属性排序" class="am-form-field" style="width:90%"/></td>
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
	
	<div class="am-cf">
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">属性与属性值配置</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="admin-content">
		    	<div class="admin-content-body">
		      		<div class="am-g">
		      			<form id="form1" method="post">
		      				<input type="hidden" name="queryParams.propertyId" id="propertyId" value=""/>
		      				<input type="hidden" name="queryParams.checkPropValueIds" id="checkPropValueIds" value=""/>
		      				<div style="width: 100%;overflow: auto;">
				            <table class="am-table am-table-bordered am-table-striped am-table-hover" id="propValueTable">
				              <thead>
					              <tr>
					                <th field="index">序号</th>
					                <th field="checkbox">选择</th>
									<th field="valName">属性值名称</th>
					              </tr>
				              </thead>
				            </table>
				            </div>
				            <div class="am-u-sm-12">
								<div class="am-cf">共<span id="rowCount1"></span>条记录<div id="page1" class="am-fr"></div></div>
							</div>
			            	<div align="center" style="display: ;">
			            		<button type="button" class="am-btn am-btn-success" id="saveBtn1"><span class="am-icon-save"></span> 保存</button>
								<button type="button" class="am-btn am-btn-default" id="closeBtn1"><span class="am-icon-undo"></span> 取消</button>
			            	</div>
			            </form>
		      		</div>
		    	</div>
		  	</div>
		</div>
	</div>

							<!--<div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}">查询条件<span class="am-icon-chevron-down am-fr"></span></div>
							<div class="am-panel-bd am-collapse am-in frame-search-panel"id="collapse-panel-1">
								<table id="from_query" class="frame-query-table" border="0" bordercolor="black">
									<tr>
										<td style="width:100px;">属性名称：</td>
										<td style="width:200px;"><input name="queryParams.propName" class="am-form-field"/></td>
										<td style="width:100px;">属性编码：</td>
										<td style="width:200px;"><input name="queryParams.propCode" class="am-form-field"/></td>
										<td>
											<button type="button" id="addPropertyBtn" class="am-btn am-btn-primary frame-search-button">新增</button>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="propertyListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="15%" field="propName">属性名称</th>
									<th width="10%" field="propCode">属性编码</th>
									<th width="10%" field="propOrder">属性排序</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-2">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">新增属性</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
	    		<form action="" class="am-form" id="ue-form">
		        <div align="center">
		        	<table class="frame-modal-table" border="0" bordercolor="black">
			        	<tr>
			        		<td width="100" class="table_title">属性名称：</td>
			        		<td><input name="property.propName" id="propName" placeholder="属性名称" class="am-form-field" style="width:90%" minlength="3" required/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">属性编码：</td>
			        		<td>
			        			<input name="property.propCode" id="propCode" placeholder="属性编码" class="am-form-field" style="width:90%" required/>
			        			<input name="property.id" id="propId" type="hidden"/>	
			        			<input name="property.flag" id="propFlag" type="hidden"/>	
			        		</td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">属性排序：</td>
			        		<td><input name="property.propOrder" id="propOrder" placeholder="属性排序" class="am-form-field" style="width:90%"/></td>
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
-->
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/property_propertymanager.js"></script>
</html>

