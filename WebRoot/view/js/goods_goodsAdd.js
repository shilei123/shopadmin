//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('pceditor');
//重置编辑器
var resetEditor = function() {
	if(ue){
   		ue.setContent("");
        ue.reset();
        clearLocalData();
	}
};
//清空编辑器草稿箱
var clearLocalData = function() {
	UE.getEditor('editor').execCommand("clearlocaldata");
};
//判断编辑器是否有内容
var hasContent = function() {
	return UE.getEditor('editor').hasContents();
};

//加载layui的laydate组件
layui.use('laydate', function(){});
$("#publishTime").click(function() {
	layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
});

$(function() {
	//查询类别下的品牌
	//查询类别属性属性值
	queryCatePropPropVal();
});

//var cateInfo = new Array(); //类别列表
var propInfo = new Array(); //属性列表
var valInfo = new Array(); //属性值列表
var cppInfo = new Array(); //类别-属性-属性值关系列表
//查询类别属性属性值
var queryCatePropPropVal = function() {
	$.ajax({
		type : "POST",
		url : path_ + "/view/catePropPropval/catePropPropval!queryByCateId.action",
		dataType : "json",
		data: "cateId="+cateId,
		success : function(json){
			$(json.list).each(function(index) {
				var row = json.list[index];
				//pushJson(cateInfo, "cateId", row.cateId, {"cateId":row.cateId,"cateName":row.cateName,"cateOrder":row.cateOrder});
				pushJson(propInfo, "propId", row.propId, {"cateId":row.cateId,"propId":row.propId,"propName":row.propName,"propCode":row.propCode,"propOrder":row.propOrder});
				pushJson(valInfo, "valId", row.valId, {"cateId":row.cateId,"propId":row.propId,"valId":row.valId,"valName":row.valName,"valCode":row.valCode,"valOrder":row.valOrder});
				cppInfo.push({"cppId":row.cppId,"cateId":row.cateId,"propId":row.propId,"valId":row.valId});
			});
			
			//渲染商品属性
			$(propInfo).each(function(index) {
				var property = propInfo[index];
				$("<div style='display: inline-block;margin-right:10px;'><input type='checkbox' name='propertyCheck' onclick='buildGoodsChildTable(this);' " +
						"value='"+property.propName+"_"+property.propId+"'/>&nbsp;"+property.propName+"</div>").appendTo("#propertyDiv");
			});
		},
		error: function(e){
			//showAlert("请检查该类别是否有子类别！");
		} 
	});
};

$("#addProperty").click(function() {
	//查询类别属性属性值
	var selectList = $("select[name='select_']");
	var selectValue = "";
	var selectId = "";
	var selectLabel = "";
	var m = "";
	var br = "<br/>";
	for (var i = 0; i < selectList.length; i++) {
		var sel = selectList[i];
		var cppId = sel.value;
		var valName = sel.options[sel.selectedIndex].text;
		var propName = $("#propertyEditTable thead tr th").eq(i).text();
		
		selectId += cppId+"_";
		selectLabel += "<strong>"+propName+"</strong>："+valName+br;
		selectValue += m + cppId+"_"+valName+"_"+propName;
		
		m = ",";
		if(i==selectList.length-2) {
			br = "";
		}
	}
	
	if($("#"+selectId).length==0) {
		var trHtml = "<tr>";
		trHtml += "<td style='text-align:left;'><input type='hidden' name='goodsChilds' id='"+selectId+"' value='"+selectValue+"'/><div style='margin-top:5px;margin-bottom:5px;'>"+selectLabel+"</div></td>";
		trHtml += "<td>&nbsp;<input name='' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "<td>&nbsp;<input name='' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "<td>&nbsp;<input name='' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "<td>&nbsp;<input name='' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "<td>&nbsp;<input name='' style=\"width:80px;\"/>&nbsp;</td>";
		trHtml += "<td>&nbsp;删除&nbsp;</td>";
		trHtml += "</tr>";
		$("#goodsChildTable tbody").prepend(trHtml);
	}
});

var buildGoodsChildTable = function(thisobj) {
	$("#propertyEditTable thead tr").empty();
	$("#propertyEditTable tbody tr").empty();
	var objs = $("input[name='propertyCheck']");
	for(var i=0; i<objs.length; i++) {
		var obj = $(objs[i]);
		var checked = obj.is(":checked");
		var vals = obj.val().split("_");
		var propName = vals[0];
		var propId = vals[1];
		if(checked) {
			if($("#select_"+propId).length==0) {
				$("#propertyEditTable thead tr").append("<th style='text-align:center;'><input type='hidden' id='th_"+propId+"'/>"+propName+"</th>");
				$("#propertyEditTable tbody tr").append("<td align=\"center\">&nbsp;"+buildPropvalSelect(propId)+"&nbsp;</td>");
			}
		} else {
			if($("#"+propId).length>0) {
				$("#"+propId).parent().remove();
				$("#th_"+propId).parent().remove();
			}
		}
	}
	/*var obj = $(thisobj);
	var checked = obj.is(":checked");
	var vals = obj.val().split("_");
	if(checked) {
		if($("#"+vals[1]).length==0) {
			$("#propertyEditTable thead tr").append("<th style='text-align:center;'><input type='hidden' id='th_"+vals[1]+"'/>"+vals[0]+"</th>");
			$("#propertyEditTable tbody tr").append("<td align=\"center\">&nbsp;<select id='"+vals[1]+"' style=\"width:60px;\"><option>AAA</option></select>&nbsp;</td>");
		}
	} else {
		if($("#"+vals[1]).length>0) {
			$("#"+vals[1]).parent().remove();
			$("#th_"+vals[1]).parent().remove();
		}
	}*/
};

//查询属性值并构建select下拉框
var buildPropvalSelect = function(propId) {
	$("#select_"+propId).empty();
	var options = "";
	$(valInfo).each(function(index) {
		var v = valInfo[index];
		if(v.propId==propId) {
			options += "<option value='"+queryCpp(v.cateId, v.propId, v.valId)+"'>"+v.valName+"</option>";
		}
	});
	return "<select name='select_' id='select_"+propId+"' style=\"width:auto;\" onchange='console.log(this.value);'>"+options+"</select>"
}

//查询类别-属性-属性值的关系ID
var queryCpp = function(cateId, propId, valId) {
	for(var i = 0; i < cppInfo.length; i++) {
		var cpp = cppInfo[i];
		if(cpp.cateId==cateId && cpp.propId==propId && cpp.valId==valId) {
			return cpp.cppId;
		}
	}
};

//保存按钮
$("#saveBtn").click(function() {
	/* if(!checkRequiredField("goodsName")) {
		return;
	}; */
	
	//验证图片
	var imgSrcCount = 0;
	$(".img").each(function(index) {
		if($(this).attr("src") == undefined || $(this).attr("src").length==0) {
			imgSrcCount++;
		}
	}); 
	if(imgSrcCount==$(".img").length) {
		layer.msg("请选择商品图片", {offset: 't'});
		$("#imgPosition").focus();
		$("#imgPosition").blur();
		return;
	}
});

var checkRequiredField = function(fieldId,msg) {
	if(!msg) {
		msg = $("#"+fieldId).attr("placeholder")+"必填";
	}
	var fieldValue = $("#"+fieldId).val();
	if(fieldValue.length==0) {
		layer.msg(msg, {offset: 't'});
		$("#"+fieldId).focus();
		return false;
	};
	return true;
};

$(".addGoodsAttr").click(function() {
	addGoodsAttribute();
});

var addGoodsAttribute = function() {
	$('<div style="padding: 1px;">属性名：<input class="am-form-field" name="attrName" style="width:150px;display: inline-block;"/>&nbsp;&nbsp;属性值：<input class="am-form-field" name="attrValue" style="width:150px;display: inline-block;"/>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="javascript:removeGoodsAttribute(this);"><span class="am-icon-minus-square" style="width: auto;color:red;font-size: 18px;" title="删除"></span></a><!--&nbsp;&nbsp;<a href="javascript:void(0);" onclick="javascript:addGoodsAttribute();">添加</a>--></div>').appendTo("#shuxingDiv");
}

var removeGoodsAttribute = function(but) {
	var elem = but.parentNode;
	var elemParent = elem.parentNode
	elemParent.removeChild(elem);
}