var createTr = function(data, targetId) {
	var tableTh = $("#"+targetId+" thead tr th");
	
	//如果没有记录
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
			var w = $(tableTh[i]).attr("width");
			
			html += "<td width='"+w+"'>";
			if(fieldName==undefined && formatter==undefined) { //字段名为空，则返回空列
				html += "&nbsp;";
			} else {
				if(fieldName=="index") {//字段名为index,返回序号列
					html += "<div style='text-align: center;'>" + index2 + "</div>";
				} else{
					if(formatter==undefined) { //格式化为空，则返回文本列
						html += getColumnValue(row[fieldName]);
					} else { //格式化不为空，则调用格式化函数
						html += window[formatter](getColumnValue(row[fieldName]), row);
					}
				}
			}
			html += "</td>";
		});
		html += "</tr>";
	});
	html += "";
	return html;
};

var getColumnValue = function(fieldValue) {
	if(fieldValue==null)
		return "&nbsp;";
	return fieldValue;
};

function pageData(url, targetId, params, currPageNum, rowCount, page) {
	params["page"] = (currPageNum || 1);
	rowCount = rowCount || 'rowCount';
	page = page || 'page';
	//console.log("rowCount, page:"+rowCount+":"+ page);
	$.ajax({
		url : url,
		type : 'POST',
		/*data : {"page" : (currPageNum || 1)},*/
		data : params,
		dataType: "json",
		success : function(data) {
			//console.log(data);
			$('#'+targetId+' tbody').empty();
			var html = createTr(data, targetId);
			$("#"+targetId).append(html);
			$("#"+rowCount).text(data.total);
			//显示分页
			var pages = Math.ceil(data.total / data.rowCount);
			laypage({
				cont : page, //容器。值支持id名、原生dom对象，jquery对象。
				pages : pages, //通过后台拿到的总页数
				curr : currPageNum || 1, //当前页
				groups : 5, //连续显示分页数
				//skin: 'yahei',//皮肤
				//skip: true, //是否开启跳页
				jump : function(obj, first) { //触发分页后的回调
					if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr//first一个Boolean，检测页面是否初始加载
						pageData(url, targetId, params, obj.curr, rowCount, page);
					}
				}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert('e:' + XMLHttpRequest.responseText);
		}
	});
};

function formGet(formId){
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