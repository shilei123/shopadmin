<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>广告管理</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="am-cf ">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">广告管理</strong> / <small>广告的增加、修改、查看、删除</small>
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
										<td class="query_title" >广告名称：</td>
										<td><input name="queryParams.Name" class="am-form-field"/></td>
										<td class="query_title">广告类型：</td>
										<td>
										<select id="linkkind" name="queryParams.linkkind" data-am-selected="{btnWidth: '164px'}">
										</select>
										</td>
										<td class="query_title">广告开始时间：</td>
					        			<td valign="top">
					        			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
										  <input type="text" name="queryParams.startRegTime" id="startRegTime" class="am-form-field">
										  <span class="am-input-group-btn am-datepicker-add-on">
										    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
										  </span>
										</div>
										</td>
										<td class="query_title">广告结束时间：</td>
										<td>
										<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
										  <input type="text" name="queryParams.endRegTime" id="endRegTime" class="am-form-field">
										  <span class="am-input-group-btn am-datepicker-add-on">
										    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
										  </span>
										</div>
					        			</td>
									</tr>
									<tr>
										<td colspan="6" align="center">
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
						<table class="am-table am-table-bordered am-table-striped am-table-hover" id="advertiseListTable">
							<thead>
								<tr>
									<th width="2%" field="index"></th>
									<th width="10%" field="name">广告名称</th>
									<th width="6%" field="linkkind">广告类型</th>
									<th width="6%" field="ordernumb">显示顺序</th>
									<th width="6%" field="type" formatter="formatterType">广告位置</th>
									<th width="6%" field="isuse">是否启用</th>
									<th width="8%"  field="kind">类型</th>
									<th width="12%" field="startTime">广告开始时间</th>
									<th width="12%" field="endTime">广告结束时间</th>
									<th width="15%" formatter="formatterAction">操作</th>
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
	
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="editAdvertiseModal">
		<div class="am-modal-dialog">
	    	<div class="am-modal-hd"><span id="title">新增</span>
	      		<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    	</div>
	    	<hr>
	    	<div class="am-modal-bd frame-am-modal-bd">
		        <div align="center">
		        	<table id="edit_adv_table" class="frame-modal-table" border="0" bordercolor="black">
		        		<tr>
			  				<td width="100" class="table_title">广告名称:</td>
			  				<td colspan="3">
			  					<input type="hidden" name="advertise.id" id="id" /> 
			  					<input name="advertise.name" id="name" style="width: 83.5%;" class="am-form-field" required="true"/> 
			  				</td>
			  			</tr>
			  			<tr>
			        		<td>活动开始时间：</td>
					   		<td valign="top">
							    <div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
								   <input type="text" name="advertise.startTime" id="starttime" class="am-form-field" style="width: 120px">
							       <span class="am-input-group-btn am-datepicker-add-on">
								   <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
								</span>
								</div>
							</td>
							<td>活动结束时间：</td>
							<td>
								<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
							       <input type="text" name="advertise.endTime" id="endtime" class="am-form-field" style="width: 120px">
								   <span class="am-input-group-btn am-datepicker-add-on">
								   <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
								</span>
						    	</div>
	        				</td>
			        	</tr>
			  		<!-- 	<tr>
			  				<td class="table_title">图片预览:</td>
			  				<td colspan="3"><img id="imgurlPr" width="500" height="100" /> </td>
			  			</tr>
			  			<tr>
			  				<td class="table_title">广告图片:</td>
			  				<td colspan="3">
			  					<input type="file" style='width: 200px;' name="imgurl" id="imgurl" class="am-form-field" required="true"/> 
			  				</td>
			  			</tr> -->
			  			<tr>
			  				<td class="table_title">广告类型:</td>
			  				<td colspan="3">
			  					<select name="advertise.linkkind" id="linkkinds" onchange="changeLinkkind();" data-am-selected="{btnWidth: '163px'}">
			  					</select> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td class="table_title">广告对象:</td>
			  				<td width="300px">
			  					<input type="hidden" name="advertise.imglink" id="imglink" />
			  					<input id="imglinkLabel" readonly="readonly" style="width: 97%;" class="am-form-field" required="true"/> 
			  				</td>
			  				<td>
			  					<a href="#" class="easyui-linkbutton" onclick="showQueryGoodsWin();return false;" id="goodsBtn">选择商品</a>
			  					<a href="#" class="easyui-linkbutton" onclick="showQueryEventsWin();return false;" style="display: none;" id="eventBtn">选择活动</a>
			  					<a href="#" class="easyui-linkbutton" onclick="showQueryCateInfoWin();return false;" style="display: none;" id="cateBtn">选择分类</a>
			  				</td>
			  			</tr>
			  			
			  			<tr>
			  				<td class="table_title">显示顺序:</td>
			  				<td colspan="1"><input name="advertise.ordernumb" id="ordernumb" class="am-form-field" data-options="increment:1" required="true" /> </td>
			  				<td class="table_title">所属分组:</td>
			  				<td colspan="1">
			  					<input class="am-form-field" name="advertise.kind" id="kind" style="width: 163px"/>
			  				</td>
			  			</tr>
			  				
			  			<tr>
			  				<td  class="table_title">广告位置:</td>
			  				<td colspan="1">
			  					<select name="advertise.type" id="advType"  data-am-selected="{btnWidth: '162px'}">
			  					</select> 
			  				</td>
			  				<td class="table_title">是否启用:</td>
			  				<td colspan="1">
			  					<select name="advertise.isuse" id="isuse" data-am-selected="{btnWidth: '162px'}">
			  					</select>
			  				</td>
			  			</tr>
			  			
			  			<tr>
			  				<td class="table_title">广告介绍:</td>
			  				<td valign="top" colspan="3">
			  					<textarea name="advertise.memo" id="memo" rows="5" placeholder="广告介绍" style="width:94%;height:150px;margin-top: 5px;" class="am-form-field" ></textarea>
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
<script type="text/javascript" src="${path }/view/js/advertise.js"></script>
</html>