<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>物流设置</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style type="text/css">
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
						<strong class="am-text-primary am-text-lg">物流设置</strong> / <small>快递服务商的新增、编辑、查看、删除</small>
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
										<td class="query_title" width="100px">简称：</td>
										<td width="200px"><input name="queryParams.forShort" class="am-form-field"/></td>
					        			<td class="query_title" width="100px">快递类型：</td>
										<td width="200px">
											<select id="type2" name="queryParams.type" data-am-selected="{btnWidth: '164px'}">
											</select>
										</td>
					        			<td>
											<button type="button" id="addBtn" class="am-btn am-btn-primary frame-search-button">新增</button>
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="logisticsListTable">
							<thead>
									<tr>
										<th width="2%"   field="index"></th>
										<th width="10%"  field="forShort">简称</th>
										<th width="10%"  field="ename">英文</th>
										<th width="10%"  field="fullName">全称</th>
										<th width="10%"  field="code">代码</th>
										<th width="10%"  field="expressType">快递类型</th>
										<th width="8%"   field="isuse">是否默认</th>
										<th width="20%"  formatter="formatterAction">操作</th>
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
	<!-- content end -->
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editeLogisticsModal">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">新增</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<table id="logistics_table" class="frame-modal-table" border="0" bordercolor="black">
		        		<input type="hidden" name="logistics.id" id="logisticsId"/>
			        	<tr>
			        		<td class="table_title">快递类型：</td>
			        		<td>
			        		<select placeholder="请选择" name="logistics.expressType" id="type" data-am-selected="{btnWidth: '152px'}">
			        		</td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">代码：</td>
			        		<td><input name="logistics.code" id="code" placeholder="代码" class="am-form-field" style="width:90%" required /></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">简称：</td>
			        		<td><input name="logistics.forShort" id="forShort" placeholder="简称" class="am-form-field" style="width:90%" required/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">英文名称：</td>
			        		<td><input name="logistics.ename" id="ename" placeholder="英文名称" class="am-form-field" style="width:90%" required/></td>
			        	</tr>
			        	<tr>
			        		<td class="table_title">全称：</td>
			        		<td><input name="logistics.fullName" id="fullName" placeholder="全称" class="am-form-field" style="width:90%" required/></td>
			        	</tr>
			        	 <tr>
							<td class="table_title">LOGO：</td>
			        		<td>
			        			<div class="imgDiv">
			        			<div id="close1" class="closediv">
											<!-- <span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
											<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp; -->
											<span class='am-icon-close' style="width: auto;">删除</span>
								</div>
			        			 <img id="img1" alt="" src="" class="img">
								<input type="hidden" id="imgIdHidden" name="logistics.url"/>
			        			<div class="imgDiv">
			        		</td>
			        	</tr> 
		       	 	</table>
		       	 	<div align="center" id="errorMsg" style="color: red;margin-top: 5px;margin-bottom: 10px;">&nbsp;</div>
		       	 	<div align="center">
						<button type="button" class="am-btn am-btn-success" id="saveBtn"><span class="am-icon-save"></span> 保存</button>
						<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
					</div>
	           	</div>
	    	</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/view/js/logisticsSetting_logisticsSetting.js"></script>
</html>