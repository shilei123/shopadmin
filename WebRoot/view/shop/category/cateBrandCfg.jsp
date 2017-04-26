<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String categoryId = request.getParameter("categoryId");
request.setAttribute("categoryId", categoryId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>类别品牌配置</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf">
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-g">
      			<form id="form1" method="post">
      				<input type="hidden" name="queryParams.cateId" id="categoryId" value="${categoryId }"/>
      				<input type="hidden" name="queryParams.checkBrandIds" id="checkPropIds" value=""/>
      				<div style="width: 100%; height:295px; overflow: auto;">
		            <table class="am-table am-table-bordered am-table-striped am-table-hover" id="categoryParamsTable">
		              <thead>
			              <tr>
			                <th field="index">序号</th>
			                <th field="checkbox">选择</th>
							<th field="brandName">品牌名称</th>
			              </tr>
		              </thead>
		            </table>
		            </div>
		            <div class="am-u-sm-12">
						<div class="am-cf">共<span id="rowCount"></span>条记录<div id="page" class="am-fr"></div></div>
					</div>
	            	<div align="center" style="">
	            		<button type="button" class="am-btn am-btn-success" id="saveBtn"><span class="am-icon-save"></span> 保存</button>
						<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
	            	</div>
	            </form>
      		</div>
    	</div>
  	</div>
</div>
</body>
<script type="text/javascript">
	$(function() {
		queryPropCateCheck();
	});
	var queryPropCateCheck = function(){
		$.ajax({
			url : "${path }/view/shop/category/cateBrand!queryCateBrandCheck.action",
			type : 'POST',
			data : formGetCurrentJsp("form1"),
			dataType: "json",
			success : function(data) {
				writeHidden(data);
				query();//分页
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert('e:' + XMLHttpRequest.responseText);
			}
		});
	};
	//写到hidden
	var writeHidden = function(data){
		var checkBrandIds = "";
		for(i in data.listCheck){
			checkBrandIds += data.listCheck[i].brandId;
			checkBrandIds += ",";
		};
		$("#checkPropIds").val(checkBrandIds);
	};
	
	var query = function() {
		var data = formGetCurrentJsp("form1");
		var url = "${path }/view/shop/category/brand!queryBrandList.action";
		pageDataCurrentJsp(url, "categoryParamsTable", data);
	};
	
	var createTrCurrentJsp = function(data, targetId) {
		var tableTh = $("#"+targetId+" thead tr th");
		if(data.rows != null && data.rows == 0 && tableTh != null && tableTh.length > 0) {
			return "<tr><td colspan='"+tableTh.length+"' style='text-align:center;height:50px;vertical-align: middle;'>&nbsp;暂无记录!</td></tr>";
		}
		var html = "";
		$(data.rows).each(function(index) {
			var row = data.rows[index];
			var index2 = index+1;
			html += "<tr >";
			$(tableTh).each(function(i) {
				var fieldName = $(tableTh[i]).attr("field");
				var formatter = $(tableTh[i]).attr("formatter");
				var wid = $(tableTh[i]).attr("width");
				html += "<td width='" + wid + "'>";
				if(fieldName==undefined && formatter==undefined) { //字段名为空，则返回空列
					html += "&nbsp;";
				} else {
					if(fieldName=="index") {//字段名为index,返回序号列
						html += "<div style='text-align: center;'>" + index2 + "</div>";
					} else if(fieldName=="checkbox"){
						if(checkProp(row)){
							html += " <input name='property' id='checkbox" + index2 + "' onclick='checkIt(\""+ row.id + "\",\"checkbox"+ index2 + "\")' type='checkbox' value='" + row.id + "' checked='checked'/> ";
						}else{
							html += " <input name='property' id='checkbox" + index2 + "' onclick='checkIt(\""+ row.id + "\",\"checkbox"+ index2 + "\")' type='checkbox' value='" + row.id + "'/> ";
						};
					} else{
						if(formatter==undefined) { //格式化为空，则返回文本列
							html += getColumnValue(row[fieldName]);
						} else { //格式化不为空，则调用格式化函数
							html += window[formatter](getColumnValueCurrentJsp(row[fieldName]), row);
						};
					};
				}
				html += "</td>";
			});
			html += "</tr>";
		});
		html += "";
		return html;
	};
	var checkProp = function(row){
		var checkPropIds = $("#checkPropIds").val();
		var arr = checkPropIds.split(",");
		for(x in arr){
			if(row.id==arr[x]){
				return true;
			}
		};
		return false;
	};
	//check或者uncheck在修改hidden
	var checkIt = function(propId, checkboxId){
		var checkPropIds = $("#checkPropIds").val();
		var arr = checkPropIds.split(",");
		var checkPropIdsNew = "";
		var isCheck = $("#"+checkboxId).attr('checked');
		if(isCheck=="checked"){
			checkPropIdsNew += propId;
			checkPropIdsNew += ",";
		}
		for(x in arr){
			if(arr[x]!="" && arr[x]!=null && arr[x]!=propId){
				//arr.splice(x,1);
				checkPropIdsNew += arr[x];
				checkPropIdsNew += ",";
			}
		};
		$("#checkPropIds").val(checkPropIdsNew);
	};
	
	var getColumnValueCurrentJsp = function(fieldValue) {
		if(fieldValue==null)
			return "&nbsp;";
		return fieldValue;
	};
	
	function pageDataCurrentJsp(url, targetId, params, currPageNum, rowCount, page) {
		params["page"] = (currPageNum || 1);
		rowCount = rowCount || "rowCount";
		page = page || "page";
		$.ajax({
			url : url,
			type : 'POST',
			data : params,
			dataType: "json",
			success : function(data) {
				//console.log(data);
				$('#'+targetId+' tbody').empty();
				var html = createTrCurrentJsp(data, targetId);
				$("#"+targetId).append(html);
				$("#"+rowCount).text(data.total);
				//显示分页
				var pages = Math.ceil(data.total / data.rowCount);
				laypage({
					cont : page, //容器。值支持id名、原生dom对象，jquery对象。
					pages : pages, //通过后台拿到的总页数
					curr : currPageNum || 1, //当前页
					groups : 5, //连续显示分页数
					jump : function(obj, first) { //触发分页后的回调
						if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr//first一个Boolean，检测页面是否初始加载
							pageDataCurrentJsp(url, targetId, params, obj.curr, rowCount, page);
						};
					}
				});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert('e:' + XMLHttpRequest.responseText);
			}
		});
	};
	
	function formGetCurrentJsp(formId){
		var obj = new Object();
		$("#"+ formId +" :input").each(function(i, n){
			if(n.name!=null && n.name!=''){
				obj[n.name] = n.value;
			}else if(n.id!=null && n.id!=''){
				obj[n.id] = n.value;
			}
		});
		return obj;
	}
	
	//保存类别-属性配置
	$("#saveBtn").click(function() {
		$("#form1").validate({
			submitHandler:function(form) {
	            $("#form1").form('submit', {
		    		url:  "${path }/view/shop/category/cateBrand!saveCateBrand.action",
		    		success:function(data) {
		    			window.parent.showAlert("操作成功！");
		    			closeCurrentWin();
		    		}
		    	});
	        }
	    }); 
		$("#form1").submit();
	});
	
	//关闭窗口
	var closeCurrentWin = function() {
		window.parent.closeParamsModal("doc-modal-1");
	};
	$("#closeBtn").click(closeCurrentWin);
</script>
</html>