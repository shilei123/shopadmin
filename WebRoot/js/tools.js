function openFullWin(url){   
	window.open(url,'','left=0,top=0,width='+screen.width+',height='+screen.height
		+',toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=yes');
} 
function openCenterWin(url,w,h){   
	l = (screen.width-w)/2;
	t = (screen.height-h)/2;
	window.open(url,'','left='+l+',top='+t+',width='+w+',height='+h);
} 
function winClose(){$.messager.confirm("提示", "确认关闭当前页面?", function(r){if(r){
	if(window.parent==null || window.opener!=null){
		window.close();
	}else if(window.parent.removeCurrSelectTab!=null){
		window.parent.removeCurrSelectTab();
	}else if(window.parent.closeCurrSelectWin!=null){
		window.parent.closeCurrSelectWin();
	}else{
		window.close();
	}
}});}
function winReload(){$.messager.confirm("提示", "确认刷新当前页面?", function(r){if(r){window.location.reload();}});}
function alert(msg){$.messager.alert("提示",msg);}	
function show(msg){$.messager.show({showType: 'show',title:"提示",msg:msg,timeout:3000});}	
function confirm(title, msg, fn){$.messager.confirm(title, msg, fn);}
//字符串 replaceAll 继承实现
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {   
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {   
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);   
    } else {   
        return this.replace(reallyDo, replaceWith);   
    }   
};

//工作流选项
//var workflowOptionsData = [{key:'启动', value:'start'},{key:'推送', value:'submit'},{key:'驳回', value:'back'},{key:'终止', value:'end'},{key:'会签', value:'join'},{key:'转办', value:'transfer'},{key:'办结', value:'finish'},{key:' 跨环节退回', value:'backTo'}];
var workflowOptionsData = [{key:'启动', value:'start'},{key:'通过', value:'submit'},{key:'退回', value:'back'},{key:'终止', value:'end'},{key:'跨环节退回', value:'backTo'},{key:'办结', value:'finish'}];
		
/*表单参数获取*/
function formParams(id) {
	var params = "";
	var isValid = true;/*是否通过校验*/
	$("#" + id + " :input").each(function(i, inp) {
		var obj = $(inp);
		if(inp.name!=null && inp.name!=''){
			if (obj.attr('required') != null || obj.attr('validType') != null) {
				if (!obj.validatebox("isValid")) {
					isValid = false;
				}
			}
			params += "&" + inp.name + "=" + encodeURIComponent(inp.value);
		}
	});
	return isValid ? params : null;
}

/*表单提交*/
function formSubmit(form, successFn) {
	var form1 = typeof form=='string'? $('#'+form):$(form) ;/*如果为元素则直接调用，也可以为id*/
	var url = form1.attr("action");
	var params = formParams(form1.attr('id'));/*获取参数*/
	if (params == null) {return false;}/*校验不通过*/
	var dataType = "json";//返回类型
	if (form1.attr("dataType") != null && form1.attr("dataType") != "") {dataType = form1.attr("dataType");}/*配置了返回类型*/
	$.ajax( {/*开始请求*/
		type: "POST",	url: url,	data: params, dataType: dataType,
		success: successFn,	error: function(e) {e.form = form1;formSubmitError(e);}
	});
}

function formSet(formId, data){
	$("#"+ formId +" :input").each(function(i, n){
		if(n.name!=null && n.name!=''){
			var ns = n.name.split(".");
			var v1 = data[ns[ns.length-1]];
			if(n.tagName=='SELECT'){
				//setSelectValue(n,v1);
				$(n).val([v1]);
			}else{
				n.value = data==''? (n.defaultValue==null?"":n.defaultValue) : v1==null?"":v1;
				if(n.formatter!=null){
					n.value = eval(n.formatter).call(this,n.value);
				}
			}
		}
	});
}
/*重置表达*/
function formReset(fromId){
	$("#"+fromId+" :input").val([""]);
}

/*easyui 自动赋值  未完成*/
function easyuiFormSet(id, data){
	$("#"+id+" :input[class^='easyui']").each(function(i,n){
		var ns = n.id.split(".");
		var v1 = data[ns[ns.length-1]];
		if(n.className=='easyui-datebox datebox-f combo-f'){
			$(n).datebox("setValue",v1);
		}
	});
}

function setSelectValue(sel,value){
	value = value==null?sel.getAttribute("defaultValue"):value;
	var ops = sel.options;
	for(var i=0; i<ops.length ;i++){
		if(ops[i].value == value){
			sel.selectedIndex = i;
		}
	}
}

function setSelectText(sel,value){
	var ops = sel.options;
	for(var i=0; i<ops.length ;i++){
		if(ops[i].innerText == value){
			sel.selectedIndex = i;
		}
	}
}

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

/*
 * 将表单内的name不为空的表单元素转换为对象
 * 多个name相同的用值用数组保存
 * checkbox radio仅获取checked的值
 */
function formGetJson(id){
	var fields = $("#"+id+" :input[name]");//.serializeArray();
	var data = new Object();
	fields.each(function(i,n){
		var node = $(n);
		if((node.attr('type')=="radio" || node.attr('type')=="checkbox")  && !n.checked){
			
		}else{
			if(data[n.name]==null){
				data[n.name] = n.value;
			}else if(data[n.name] instanceof Array){
				data[n.name].push(n.value);
			}else{
				var tmp = data[n.name];
				data[n.name] = [tmp, n.value];
			}
		}
	});
	return data;
}

/*
 * 将对象按name赋值给表单元素
 * 多个name相同的元素按数组循序赋值
 *  checkbox radio select按value匹配checkbox
 */
function formSetJson(id, data){
	var nodes = {};
	$("#"+ id +" :input[name]").each(function(i, n){
		if(nodes[n.name]==null){
			nodes[n.name] = n;
		}else if(nodes[n.name] instanceof Array){
			nodes[n.name].push(n);
		}else{
			var node = nodes[n.name];
			nodes[n.name] = [node, n];
		}
	});
	
	var node = null;
	for(var key in nodes){
		node = $(nodes[key]);
		if(data[key]==null){
			node.val("");
		}else{
			node.val((data[key] instanceof Array)?data[key]:[data[key]]);
		}
	}
	
	$("#"+ id +" :input[easyuiType]").each(function(i, n){
		var node = $(n);
		var val = data[node.attr('comboname')];
		if(val!=null ){
			var key = n.name;
			node[node.attr('easyuiType')]('setValue', val);
		}
	});
}


var easyui_head_pattern = /easyui-(\w+)/ig;
function getEasyuiName(obj){
	var name = null;
	obj.className.replace(easyui_head_pattern, function(a, b, c){
		name = b; return false;
	}); 
	return name;
}
/*表单提交发生异常时统一调用函数*/
function formSubmitError(error) {/*alert(error.responseText);*/alert("调用发生异常!!!");}

/*校验*/
$.extend($.fn.validatebox.defaults.rules,{number: {validator: function(value, param){return /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value); },message: '错误的数字'}}); 
$.extend($.fn.validatebox.defaults.rules,{mobile: {validator: function(value, param){return /^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[7-8])[0-9]{8}$/.test(value);   },message: '错误的手机号码'}});
$.extend($.fn.validatebox.defaults.rules,{phone: {validator: function(value, param){return /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/.test(value);}, message: '错误的电话号码'}});  
$.extend($.fn.validatebox.defaults.rules,{ip: { validator: function(value, param){return /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/.test(value);}, message: '错误的IP地址'}});
$.extend($.fn.validatebox.defaults.rules,{integer: {validator: function(value, param){return /^[0-9]*$/.test(value); },message: '错误的数字'}});
$.extend($.fn.validatebox.defaults.rules,{minLength: {validator: function(value, param){return value.length>=param; },message: '最小长度{0}'}});
$.extend($.fn.validatebox.defaults.rules,{maxLength: {validator: function(value, param){return value.length<=param; },message: '最大长度{0}'}});
$.extend($.fn.validatebox.defaults.rules,{equals: {validator: function(value, param){return value==$('#'+param[0]).val(); },message: '{1}'}});
$.extend($.fn.validatebox.defaults.rules,{ajax: {validator: function(value, param){
	var success = false;
	$.ajax( {
		type: "POST",	url: param[0].url+value,	data: param[0].data, dataType: param[0].dataType==null?'text':param[0].dataType, async:false,
		success: function(json){
			if(param.length>1 && param[1]!=null){
				success = param[1].apply(this, [json]);
			}else{
				success = json=='true';
			}
		},	error: function(e) {success=false;}
	});
	return success; 
},message: ''}});
$.extend($.fn.validatebox.defaults.rules,{datagridIsSave: {validator: function(value, param){
	try{
		var id = param[0];
		var el = $('#'+id);
		var tabId = el.attr('datagridId');
		var msg = $('#'+tabId+"_message");
		var tab = $('#'+tabId);
		
		try{
			//是否必填
	   		if(el.data().validatebox.options.required){
	   			if(tab.datagrid('getData').rows.length==0){
	   				msg.html("*必填,至少添加一行");
	   				msg.focus();
	   				return false;
	   			}
	   		}
		}catch(e){
			msg.html("*必填,至少添加一行");
			msg.focus();
			return false;
		}
   		
		if(tab.attr("lastIndex")!=null){
			msg.html("*必填,数据表格未编辑完成");
			msg.focus();
			return false;
		}else{
			msg.html("");
		}
		return true; 
	}catch(e){return false;}
},message: null}});


//datagrid formatter
var formatterDate = function(val, data, index){
	return val==null?"":val.replace("T"," ").substr(0,10);
}
var formatterDatetime = function(val, data, index){
	return val==null?"":val.replace("T"," ");
}
var formatterFlag = function(val, data, index){
	return val=="1"?"是":"否";
}
var formatterMb = function(val, data, index){
	if(val==null || val==""){return '';}
	var v = parseInt(val)/1024;
	if(v>1024){
		v=v/1024
		return v.toFixed(2)+"m";
	}
	return v.toFixed(2)+"k";
}
var formatterUTC = function(val, data, index){
	if(val==null || val==""){return '';}
	var d = new Date(val);
	return d.getYear()+'-'+addDateZero(d.getMonth())+'-'+addDateZero(d.getDate())+' '
	+addDateZero(d.getHours())+':'+addDateZero(d.getMinutes())+':'+addDateZero(d.getSeconds());
}
function addDateZero(v){
	return v<10?'0'+v:v;
}

function parseDate(str){
	if(str.length==10){
		var ds = str.split("-");
		var date = new Date(parseInt(ds[0]),parseInt(ds[1]),parseInt(ds[2]));
		return date;
	}
	return null;
}

//表格使用 多选Combobox  修复bug
$.extend($.fn.datagrid.defaults.editors, { 
	dataGridMultipleCombobox: {   
		init: function(container, options){   
			var combo = $('<input type="text" >').appendTo(container);
            combo.combobox(options || {});
            return combo; 
		},   
		destroy : function(jq) {
        	$(jq).combobox("destroy");  
        },  
		getValue: function(target){
            var opts = $(target).combobox('options');
            if (opts.multiple){
                return $(target).combobox('getValues').join(opts.separator);
            } else {
                return $(target).combobox('getValue');
            }  
		},   
		setValue: function(target, value){
			var opts = $(target).combobox('options');
			value = value==null ?"":value;
            if (opts.multiple){
                if (value == ''){
                    $(target).combobox('clear');
                } else {
                    $(target).combobox('setValues', value.split(opts.separator));
                }
            } else {
                $(target).combobox('setValue', value);
            }
		}
	}
});

function validateForm(id){
	var isVali = true;
	$("#"+id+" :input[required],#"+id+" :input[validType]").each(function(i, inp) {
		var css = inp.className;
		if (!$(inp).validatebox("isValid")) {
			isVali = false;
		}		
	});	
	return isVali;
}
function validateDatagrid(id){
	var vali = true;
	var tab = $("#"+id);
	var rows = tab.datagrid("getRows");
	for(var i=0;i<rows.length;i++){
		if(!tab.datagrid("validateRow",i)){
			vali = false;			
		}else{
			tab.datagrid('endEdit', i);
		}
	}
	return vali;
}

//页面加载等待页面
var height = window.screen.height;// - 250;
var width = window.screen.width;
var leftW = 300;
if (width > 1200) {leftW = 500;} else if (width > 1000) {leftW = 350;} else {leftW = 100;}
leftW = leftW-120;
var _html = "<div id='loading' style='position:absolute;left:0;width:100%;height:"
		+ height
		+ "px;top:0;background:#E0ECFF;opacity:0.8;filter:alpha(opacity=95);'>\ <div style='position:absolute;	cursor1:wait;left:"
		+ leftW
		+ "px;top:200px;width:auto;height:16px;padding:12px 5px 10px 30px;\ background:#fff url("+basePath+"/js/jquery-easyui-1.4/themes/gray/images/loading.gif) no-repeat scroll 5px 10px;border:2px solid #ccc;color:#000;'>\ 正在加载，请等待...\ </div></div>";
window.onload = function(){ 	
	var _mask = document.getElementById('loading'); 	
	_mask.parentNode.removeChild(_mask);
}	  
document.write(_html);

function filedown(fileid, paramName){
	if(window.parent!=null){
		window.parent.filedown(fileid, paramName);
	}
} 

//文件上传
var fileUploadHtml = '<div id="div_file_upload" modal="true" draggable="false" class="easyui-dialog" title="文件上传" style="width:460px; height: 240px;" buttons="#div_file_upload_buttons">'
+'	<iframe id="iframe_file_upload"  style="width: 100%; height: 100%;" frameborder="0"></iframe>'
+'<div align="center" id="div_file_upload_buttons">'
+'<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">'
+'<tr><td align="center">'
+'<a href="#" class="easyui-linkbutton" onclick="$(\'#iframe_file_upload\')[0].contentWindow.fileSubmit(); return false;">提交</a>'
+'<a href="#" class="easyui-linkbutton" onclick="$(\'#div_file_upload\').dialog(\'close\'); return false;">关闭</a>'
+'</td></tr>'
+'</table>'
+'</div>'
+'</div>';
var fileUploadCallbackFn = null;
// parms in {fileSize:0,	dirName:'',fileType:'',fileTypeStr:'',fileCount:'',showBut: true}
function showFileUpload(params, callbackFn){
	fileUploadCallbackFn = callbackFn;
	params = params==null?{}:params;
	var url = basePath+"/view/public/fileupload.jsp";     var bz = "?";
	for(var key in params){
		url += params[key]==null?"":(bz+key+"="+(params[key]));  bz = "&";
	}
	if($('#div_file_upload').length==0){
		$(document.body).after(fileUploadHtml);
		$("#div_file_upload_buttons a").linkbutton({});
		$("#iframe_file_upload").attr('src', url);
		$('#div_file_upload').dialog({title:'文件上传', width:440, height:200 });//onClose:fileuploadResult_4d1fb735e9074e6b8a3e22ca3a292164
	}else{
		$("#iframe_file_upload").attr('src', url);
		$('#div_file_upload').dialog("open");
	}
}
function closeFileUpload(){
	$("#div_file_upload").dialog("close");
}
function fileuploadResult_4d1fb735e9074e6b8a3e22ca3a292164(ids,files){
	if(fileUploadCallbackFn!=null){
		fileUploadCallbackFn.apply(this,[ids,files]);
	}
}

function CopyObject(obj){   
    var objClone;   
    if (obj.constructor == Object){   
        objClone = new obj.constructor();    
    }else{   
        objClone = new obj.constructor(obj.valueOf());    
    }   
    for(var key in obj){   
        if ( objClone[key] != obj[key] ){    
            if ( typeof(obj[key]) == 'object' ){    
                objClone[key] = obj[key].Clone();   
            }else{   
                objClone[key] = obj[key];   
            }   
        }   
    }   
    objClone.toString = obj.toString;   
    objClone.valueOf = obj.valueOf;   
    return objClone;    
} 

function CopyArray(arr,begin, end){  
	var newArr = [];
	begin = begin==null?0:begin;
	end = end==null?arr.length:end;
	end = end>arr.length?arr.length:end;
	for(var i=begin;i<end;i++){
		newArr.push(arr[i]);
	}
	return newArr;
}

/*数据源获取*/
function datasource(id, params){
	var url = basePath+'/view/public/datasource.action';
	if(params==null){
		params = {};
	}
	params["source.id"] = id;
	var result = false;
	$.ajax( {
		type: "POST",url: url,data: params, dataType: 'json', async:false,	error: function(e) {return false;},
		success: function(json){result = json;}
	});
	return result;
}


Date.prototype.toString = function(){
	  var y = this.getFullYear();
	  var m = this.getMonth()+1;
	  var d = this.getDate();
    return y+'-'+(m<10?'0'+m:m)+'-'+(d<10?'0'+d:d);
};

Date.prototype.set = function(str){
	  var arr = str.split('-');
	  this.setYear(parseInt(arr[0]));
	  this.setMonth(parseInt(arr[1])-1);
	  this.setDate(parseInt(arr[2]));
    return this;
};

Date.prototype.formatter = function(r){
	if(r=='y'){
		this.setMonth(0);	this.setDate(1);
	}else if(r=='m'){
		this.setDate(1);
	}else if(r=='d'){
		
	}else if(r=='w'){
		var w = (this.getDay()==0?7:this.getDay())-1;
		this.setDate(this.getDate()-w);
	}else if(r=='q'){
		var ms = [0,1,2,0,1,2,0,1,2,0,1,2];
		this.setDate(1);
		this.setMonth(this.getMonth()-ms[this.getMonth()]);	
	}
	return this;
}

Date.prototype.add = function(r,num){
	if(num==null && num==0){return this;}
	this.formatter(r);
	if(r=='y'){
		this.setYear(this.getYear()+num);
	}else if(r=='m'){
		this.setMonth(this.getMonth()+num);
	}else if(r=='d'){
		this.setDate(this.getDate()+num);
	}else if(r=='w'){
		this.setDate(this.getDate()+num*7);
	}else if(r=='q'){
		this.setMonth(this.getMonth()+num*3);	
	}
	this.setDate(this.getDate()-1);
	return this;
}


function getEditForm(id,pojoName){
	var param = {};
	pojoName = (pojoName!=null && pojoName!="")?pojoName+".":"";
	$('#'+id+' :input[name]').each(function(i,n){
		param[pojoName+n.name] = n.value;
	});
	return param;
}

function setEditForm(id, data, pojoName){
	var val = (pojoName!=null && pojoName!="")?data[pojoName]:data;
	$('#'+id+' :input[name]').each(function(i,n){
		if(val[n.name]!=null){$(n).val([val[n.name]]);}
	});
}

//解决validatebox不能去除的问题， $('#id').validatebox('remove');  $('#id').validatebox('reduce');  $('#id').combobox({required:false});  
$.extend($.fn.validatebox.methods, {    
    remove: function(jq, newposition){    
        return jq.each(function(){    
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');  
        });    
    },  
    reduce: function(jq, newposition){    
        return jq.each(function(){    
           var opt = $(this).data().validatebox.options;  
           $(this).addClass("validatebox-text").validatebox(opt);  
        });    
    }     
}); 


function getTotalHeight(){
	 if($.browser.msie){
	  return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;
	 }else{
	  return self.innerHeight;
	 }
	}
	function getTotalWidth (){
	 if($.browser.msie){
	  return document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth;
	 }else{
	  return self.innerWidth;
	 }
	}
	
	
function objectAddKey(data, key){
	if(data==null){return {};}
	var k = (key==null||key=="")?"":key+".";
	var result = {};
	for(var i in data){
		result[k+i] = data[i];
	}
	return result;
}