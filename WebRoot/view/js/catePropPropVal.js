$(function() {
	$("#ul_category_tree").tree({
		onClick: categoryClick
	});
	initCategoryTree();
});

function initCategoryTree() {
	$.ajax( {
		type : "POST",
		url : path_ + "/view/catePropPropval/catePropPropval!queryCategoryTree.action",
		dataType : "json",
		success : function(json) {
			//console.log(json);
			var root = json.trees[0];
			root.state = "open";
			$("#ul_category_tree").tree("loadData", json.trees);
		}
	});
}

var categoryClick = function(node) {
	//不是最后一级
	if(node.children!=null && node.children!=undefined){
		showAlert("请选择一个子类别！");
		return;
	}
	//是最后一级，拼html
	$.ajax({
		type : "POST",
		url : path_ + "/view/catePropPropval/catePropPropval!queryListByCateId.action",
		dataType : "json",
		data: {"cateId": node.pkId},
		success : function(json){
			//console.log(json.list);
			//console.log(json.listCheck);
			$("#propPropValTable").html("");
			var html = "";
			for(x in json.list){
				html += "<tr><td width='10%'  style=\"padding: 5px;\">" + json.list[x].propName + "</td><td style='padding='5px';'>";
				var vals = json.list[x].vals;
				for(y in vals){
					if(vals[y].valName!=null && vals[y].valName!=undefined && vals[y].valName!=""){
						if(checkPropPropVal(json.listCheck, vals[y].propPropvalId)){
							html += "<input name='chooseVal' class='" + vals[y].propPropvalId + "' type='checkbox' style=\"vertical-align: middle;margin: 0px\" checked='checked'/>" + vals[y].valName + "&nbsp;&nbsp;&nbsp;&nbsp;";
						}else{
							html += "<input name='chooseVal' class='" + vals[y].propPropvalId + "' type='checkbox' style=\"vertical-align: middle;margin: 0px\"/>" + vals[y].valName + "&nbsp;&nbsp;&nbsp;&nbsp;";
						}
					}
				}
				html += "</td>";
			}
			html += "<tr><td colspan='2' style=\"text-align: center;\"><button onclick='saveIt(\"" + node.pkId + "\");' class='am-btn am-btn-success'>保存</button></td></tr>"
			$("#propPropValTable").append(html);
		},
		error: function(e){
			showAlert("请检查该类别是否有子类别！");
		} 
	});
};

var checkPropPropVal = function(listCheck, propPropvalId){
	for(x in listCheck){
		if(listCheck[x].proppropvalId==propPropvalId){
			return true;
		}
	};
	return false;
};

//数据库查到的propPropValId写到hidden
/*var writeHidden = function(listCheck){
	$("#propPropValIds").val("");
	var checkIds = "";
	for(i in listCheck){
		checkIds += listCheck[i].proppropvalId;
		checkIds += ",";
	};
	$("#propPropValIds").val(checkIds);
};*/

//check或者uncheck在修改hidden
/*var checkIt = function(propPropValId){
	var checkIds = $("#propPropValIds").val();
	var arr = checkIds.split(",");
	var checkIdsNew = "";
	var isCheck = $("#"+propPropValId).attr('checked');
	if(isCheck=="checked"){
		checkIdsNew += propValueId;
		checkIdsNew += ",";
	}
	with no chang
	for(x in arr){
		if(arr[x]!="" && arr[x]!=null && arr[x]!=propValueId){
			checkPropValueIdsNew += arr[x];
			checkPropValueIdsNew += ",";
		}
	};
	$("#propPropValIds").val(checkPropValueIdsNew);
};*/

var saveIt = function(cateId) {
	var temp = "";
	$('input:checkbox[name=chooseVal]:checked').each(function(i){
		if(0==i){
			temp = $(this).attr("class");
		}else{
			temp += (","+$(this).attr("class"));
	   	}
	});
	/*$("#propPropValIds").val(temp);*/
	
	var data = {"propPropValIds": temp, "cateId": cateId};
	$.ajax({
		type : "POST",
		url : path_ + "/view/catePropPropval/catePropPropval!saveCatePropPropVal.action",
		data : data,
		dataType : "json",
		success : function(json) {
			showAlert("操作成功");
		},
		error : function(e) {
			showAlert("操作失败");
		}
	});
};
