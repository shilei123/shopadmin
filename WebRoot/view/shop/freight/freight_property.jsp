<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String freightId = request.getParameter("freightId")==null?"":request.getParameter("freightId");
request.setAttribute("freightId", freightId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>运费设置</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf">
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-g">
      			<div align="center">
		        	<table id="edit_fre_table" class="frame-modal-table" border="0" bordercolor="black">
		        		<input type="hidden" name="freight.id" id="freightId" value="${freightId}"/>
			        	<tr>
			        		<td width="100px" class="table_title">模板名称：</td>
			        		<td><input name="freight.templateName" maxlength="50" id="templateName" placeholder="模板名称" class="am-form-field" style="width:200px"/></td>
			        	</tr>
			        	<tr>
				        	<td width="100px" class="table_title">计价方式：</td>
				        	<td>
				        	<input id="piece" name="rad_valuation"  value="0" type="radio"/><label for="piece">按件数</label>
			        		<input id="weight" name="rad_valuation" value="1" type="radio"/><label for="weight">按重量</label>
			        		<input id="volume" name="rad_valuation" value="2" type="radio"/><label for="volume">按体积</label>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td style="padding-left:30px;" colspan="2">运送方式：除指定地区外，其余地区的运费采用"默认运费"</td>
						</tr>
						<tr>
							<td style="padding-left:130px;" colspan="2">
								<div class="postage-tpl" id="py">
									<p class="trans-line">
										<input id="mail"  onclick="showOrHideCheckBox('mail')"  type="checkbox" value="0" name="transportMode"> <label for="mail">平邮</label>
									</p>
								</div>
							</td>
		        		</tr>
		        		<tr id="mails" style="display: none;">
		        			<td colspan="4">
								<div>
									<table width="100%" id="frePy" style="border:1px solid #ccc;">
										<thead>
										<tr>
											<td bgcolor="#F5FFFA">默认运费
											<input name="inp_initialInt" 	type="text"  value="1" maxlength="10" style="width: 50px"><span class="span_init">件内,</span>
											<input name="inp_initialPrice"  type="text"  value=""  maxlength="10" style="width: 50px">元,每增加
											<input name="inp_stackInt" 		type="text"  value="1" maxlength="10" style="width: 50px"><span class="span_stack">件,增加运费</span>
											<input name="inp_stackPrice"	type="text"  value=""  maxlength="10" style="width: 50px">元
											<input type="hidden" name="inp_userFreightId"/>
											</td>
										</tr>
										</thead>
										<tbody>
										<tr>
											<td>
											<table width="100%" id="frePyCheckTable" border="1" bordercolor="#ccc" border frame=hsides >
											<thead>
											<tr bgcolor="F8F8FF">
											<th width="40%" style="text-align: center;">运送到</th>
											<th width="10%" style="text-align: center;"><span class="span_initialInt">首件数(件)</span></th>
											<th width="10%" style="text-align: center;">首费(元)</th>
											<th width="10%" style="text-align: center;"><span class="span_stackInt">续件数(件)</span></th>
											<th width="10%" style="text-align: center;">续费(元)</th>
											<th width="10%" style="text-align: center;">操作</th>
											</tr>
											</thead>
											<tbody>
											</tbody>
											</table>
											</td>
										</tr>
										</tbody> 
										<tr>
											<td>
											<div style="padding-left: 10px; padding-top: 10px;">
											<a href="#" onclick="pop('py')">为制定城市地区设置运费</a>
											</div>
											</td>
										</tr>
									</table>
								</div>
							</td>
		        		</tr>
		        		<tr>
			        		<td style="padding-left:130px;" colspan="2">
				        		<div class="postage-tpl" id="kd">
									<p class="trans-line">
										<input id="expres" onclick="showOrHideCheckBox('expres')" type="checkbox" value="1" name="transportMode"> <label for="expres">快递</label>
									</p>
								</div>
								</td>
		        		</tr>
		        		<tr id="express" style="display: none;">
		        			<td colspan="4">
								<div>
									<table width="100%" id="freKd" style="border:1px solid #ccc;">
										<thead>
										<tr>
											<td bgcolor="#F5FFFA">默认运费
											<input name="inp_initialInt" 	type="text"  value="1" maxlength="10" style="width: 50px"><span class="span_init">件内,</span>
											<input name="inp_initialPrice"  type="text"  value=""  maxlength="10" style="width: 50px">元,每增加
											<input name="inp_stackInt" 		type="text"  value="1" maxlength="10" style="width: 50px"><span class="span_stack">件,增加运费</span>
											<input name="inp_stackPrice"	type="text"  value=""  maxlength="10" style="width: 50px">元
											<input type="hidden" name="inp_userFreightId"/>
											</td>
										</tr>
										</thead>
										<tbody>
										<tr>
											<td>
											<table width="100%" id="freKdCheckTable" border="1" bordercolor="#ccc" border frame=hsides >
											<thead>
											<tr bgcolor="F8F8FF">
											<th width="40%" style="text-align: center;">运送到</th>
											<th width="10%" style="text-align: center;"><span class="span_initialInt">首件数(件)</span></th>
											<th width="10%" style="text-align: center;">首费(元)</th>
											<th width="10%" style="text-align: center;"><span class="span_stackInt">续件数(件)</span></th>
											<th width="10%" style="text-align: center;">续费(元)</th>
											<th width="10%" style="text-align: center;">操作</th>
											</tr>
											</thead>
											<tbody>
											</tbody>
											</table>
											</td>
										</tr>
										</tbody> 
										<tr>
											<td>
											<div style="padding-left: 10px; padding-top: 10px;">
											<a href="#" onclick="pop('kd')">为制定城市地区设置运费</a>
											</div>
											</td>
										</tr>
									</table>
								</div>
							</td>
		        		</tr>
		        		<tr>
			        		<td style="padding-left:130px;" colspan="2">
				        		<div class="postage-tpl" id="es">
									<p class="trans-line">
										<input id="ems" onclick="showOrHideCheckBox('ems')" type="checkbox" value="2" name="transportMode"> <label for="ems">EMS</label>
									</p>
								</div>
			        		</td>
		        		</tr>
		        		<tr id="emss" style="display: none;">
		        			<td colspan="4">
								<div>
									<table width="100%" id="freEs" style="border:1px solid #ccc;">
										<thead>
										<tr>
											<td bgcolor="#F5FFFA">默认运费
											<input name="inp_initialInt" 	type="text"  value="1" maxlength="10" style="width: 50px"><span class="span_init">件内,</span>
											<input name="inp_initialPrice"  type="text"  value=""  maxlength="10" style="width: 50px">元,每增加
											<input name="inp_stackInt" 		type="text"  value="1" maxlength="10" style="width: 50px"><span class="span_stack">件,增加运费</span>
											<input name="inp_stackPrice"	type="text"  value=""  maxlength="10" style="width: 50px">元
											<input type="hidden" name="inp_userFreightId"/>
											</td>
										</tr>
										</thead>
										<tbody>
										<tr>
											<td>
											<table width="100%" id="freEsCheckTable" border="1" bordercolor="#ccc" border frame=hsides >
											<thead>
											<tr bgcolor="F8F8FF">
											<th width="40%" style="text-align: center;">运送到</th>
											<th width="10%" style="text-align: center;"><span class="span_initialInt">首件数(件)</span></th>
											<th width="10%" style="text-align: center;">首费(元)</th>
											<th width="10%" style="text-align: center;"><span class="span_stackInt">续件数(件)</span></th>
											<th width="10%" style="text-align: center;">续费(元)</th>
											<th width="10%" style="text-align: center;">操作</th>
											</tr>
											</thead>
											<tbody>
											</tbody>
											</table>
											</td>
										</tr>
										</tbody> 
										<tr>
											<td>
											<div style="padding-left: 10px; padding-top: 10px;">
											<a href="#" onclick="pop('es')">为制定城市地区设置运费</a>
											</div>
											</td>
										</tr>
									</table>
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
      		</div>
    	</div>
  	</div>
</div>
</body>
<script type="text/javascript" src="${path }/view/js/freight_freightProperty.js"></script>
</html>