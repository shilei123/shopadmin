<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String agencyId = request.getParameter("agencyId");
request.setAttribute("agencyId", agencyId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>机构参数配置</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
<div class="am-cf">
	<!-- content start -->
	<div class="admin-content">
    	<div class="admin-content-body">
      		<div class="am-g">
      			<form id="form1" method="post">
      				<input type="hidden" name="tree.agencyId" id="agencyId" value="${agencyId }"/>
      				<input type="hidden" name="agencyParamsBean.withdraw_switch" id="withdraw_switch" value=""/>
      				<div style="width: 100%;height: 310px;overflow: auto;">
		            <table class="am-table am-table-bordered am-table-striped am-table-hover" id="agencyParamsTable">
		              <thead>
			              <tr>
			                <th width="10%" field="index">序号</th>
							<th>参数名称</th>
			                <th>参数值</th>
			              </tr>
			              
		              </thead>
		            </table>
		            </div>
	            	<div align="center" style="display: ;">
	            		<button type="button" class="am-btn am-btn-success" id="saveAgencyParamsBtn"><span class="am-icon-save"></span> 保存</button>
						<button type="button" class="am-btn am-btn-default" id="closeAgencyParamsBtn"><span class="am-icon-undo"></span> 取消</button>
	            	</div>
	            </form>
      		</div>
    	</div>
  	</div>
	<!-- content end -->
</div>
</body>
<script type="text/javascript">
	$(function() {
		query();//分页
	});
	
	//绑定搜索按钮的分页
	$('#queryBtn').click(function(){
		query();
	});
	
	var query = function() {
		var data = formGet("from_query");
		data["tree.agencyId"] = "${agencyId}";
		var url = "${path }/view/agency/agency!queryAgencyParams.action";
		$.ajax( {
			type: "POST",	
			url: url,	
			data: data, dataType: "json",
			success: function(json){
				$('#agencyParamsTable tbody').empty();
				var index = 1;
				var html = "";
				$(json.agencyParams).each(function(i) {
					var row = json.agencyParams[i];
					html += "<tr>";
					html += "<td style='width:200px;'>"+index+"</td>";
					html += "<td style='width:300px;'>"+row["paramDesc"]+"</td>";
					if(row["paramName"]=="withdraw_switch") { //提现开关
						$("#withdraw_switch").val(row["paramVal"]);
						html += "<td>";
						if(row["paramVal"]=="1") {
							html += "<label class='am-radio am-secondary'>";
							html += "<input type='radio' name='withdraw_switch_radio' value='1' checked>打开";
							html += "</label>";
							html += "<label class='am-radio am-secondary'>";
							html += "<input type='radio' name='withdraw_switch_radio' value='0'>关闭";
							html += "</label>";
						} else {
							html += "<label class='am-radio am-secondary'>";
							html += "<input type='radio' name='withdraw_switch_radio' value='1'>打开";
							html += "</label>";
							html += "<label class='am-radio am-secondary'>";
							html += "<input type='radio' name='withdraw_switch_radio' value='0' checked>关闭";
							html += "</label>";
						}
						html += "</td>";	
					} else {
						html += "<td><input type='text' name='agencyParamsBean."+row["paramName"]+"' id='"+row["paramName"]+"' value='"+row["paramVal"]+"' required/></td>";
					}
					html += "</tr>";
					index++;
				});
				
				$("#agencyParamsTable").append(html);
			},	
			error: function(e) {
				//alert("查询异常");
			}
		});
	};

	$("#saveAgencyParamsBtn").click(function() {
		
		var rv = $("input[name='withdraw_switch_radio']:checked").val();
		if(rv!=undefined && rv!=null && rv!="") {
			$("#withdraw_switch").val(rv);
		}
		
		$("#form1").validate({
			//debug:true,
	        submitHandler:function(form) {
	            $('#form1').form('submit', {
		    		url:  "${path }/view/agency/agency!saveAgencyParams.action",
		    		success:function(data) {
		    			window.parent.showAlert("操作成功！");
		    			closeCurrentWin();
		    		}
		    	});
	        }
	    });
		$("#form1").submit();
	});
	
	var closeCurrentWin = function() {
		window.parent.closeParamsModal();
	};
	
	$("#closeAgencyParamsBtn").click(closeCurrentWin);
</script>
</html>