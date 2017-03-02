function Alert(msg,fn){
	$.messager.alert("提示",msg,fn);
}

function toParams(params){
	return {parameter: JSON.stringify(params)};
}
function formGetParams(f){
	return toParams(formGetObject(f));
}
function formGetObject(f){
	var obj = {};
	$(':input[name]', f).each(function(i,n){
		obj[n.name] = n.value;
	});
	delete obj["parameter"];
	return obj;
}
function setFormParams(f){
	var pid = f.attr("parameterId");
	if(pid==null || pid==""){
		pid = "params_"+new Date().getTime();
		f.append('<input name="parameter" id="'+pid+'" type="hidden" />');
		f.attr("parameterId", pid);
	}
	$('#'+pid).val(JSON.stringify(formGetObject(f)));
}

var initDatagridParams = function(param){
	var parameter = {page:param.page, rows:param.rows};
	var fid = $(this).attr('_formId');
	if(fid!=null && fid!=''){
		var fobj = formGetObject($('#'+fid));
		for(var k in fobj){
			parameter[k] = fobj[k];
		}
	}
	param["parameter"] = JSON.stringify(parameter);
	return true;
};


function formatterFlag(val, data, i){
	return val==null?"":val=="1"?"启用":"<span style='color:red;'>禁用</span>";
}
function formatterFlagYx(val, data, i){
	return val==null?"":val=="1"?"有效":"<span style='color:red;'>无效</span>";
}

function loadSelect(jq, list, value, text, def){
	list = list==null?[]:list;
	var html = def==null?"":"<option value=''>"+(def==""?"--请选择--":def)+"</option>";;
	for(var i=0;i<list.length;i++){
		var row = list[i];
		html += "<option value=\""+(row[value]==null?"":row[value])+"\">"+(row[text]==null?'':row[text])+"</option>";
	}
	jq.html(html);
}