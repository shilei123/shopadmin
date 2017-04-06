var panentNameHtml = "";
var IsuseHtml = "";
$(function() {
	$("#ul_directory_tree").tree({
		onClick: directoryClick
	});
	initDirectoryTree();
	findDirectoryType();
	findIsuseType();

	//取消按钮
	$("#cancelBtn").click(function() {
		closeParamsModal("doc-modal-add");
	});

	//保存按钮
	$("#saveDirectoryBtn").click(function() {
		//表单验证
		if(!checkBankSumbit()) {
			return;
		}
		var node = $("#ul_directory_tree").tree("getSelected");
		if(node==null){ return; }
		$("#inp2_parentName").removeAttr('disabled');
		$("#form1").validate({
	        submitHandler:function(form){
	            $('#form1').form('submit', {
		    		url:  path_ + "/view/shop/dirStruct/dirStruct!save.action",
		    		onSubmit: function() {
		    			return checkForm();
		    		},
		    		success:function(data) {
		    			showAlert("操作成功！");
		    			initDirectoryTree();
		    			$("#directory_detail_table input").each(function(i,n){n.value = "";});
		    			closeParamsModal("doc-modal-add");
		    		}
		    	});
	        }
	    });
		$("#form1").submit();
	});
});

//表单验证
var checkBankSumbit = function() {
	var inp2_directoryName = $("#inp2_directoryName").val();
	var inp2_parentName = $("#inp2_parentName").val();
	var inp2_cateOrder = $("#inp2_cateOrder").val();
	var inp2_isuse = $("#inp2_isuse").val();
	
	if(inp2_directoryName == null || inp2_directoryName=="") {
		$("#errorMsg").html("栏目名称不能为空！");
		$("#inp2_directoryName").focus();
		return false;
	}
	
	if(inp2_parentName==null || inp2_parentName == "-1"){
		$("#errorMsg").html("上级栏目不能为空！");
		$("#inp2_parentName").focus();
		return false;
	}
	if(inp2_cateOrder == null || inp2_cateOrder=="" ) {
		$("#errorMsg").html("排序序号不能为空！");
		$("#inp2_cateOrder").focus();
		return false;
	}
	if(inp2_isuse==null || inp2_isuse == "-1"){
		$("#errorMsg").html("是否有效不能为空！");
		$("#inp2_isuse").focus();
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};

var findDirectoryType = function() {
	var inp2_parentName = $("#inp2_parentName");
	inp2_parentName.empty();
		$.ajax({
			url :path_ + "/view/shop/dirStruct/dirStruct!queryDirType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				var html = "<option value='-1'>-请选择-</option>";
				$(data.directoryTypeList).each(function(index) {
					var dirType = data.directoryTypeList[index];
					html += "<option value='" + dirType.id + "'>" + dirType.dirName + "</option>";
				});
				panentNameHtml = html;
				inp2_parentName.append(html);
			}
		});
};

var findIsuseType = function() {
	var inp2_isuse = $("#inp2_isuse");
	inp2_isuse.empty();
	var html = "<option value='-1'>-请选择-</option>";
		html += "<option value='1'>有效</option>";
		html += "<option value='0'>无效</option>";
		IsuseHtml = html;
	inp2_isuse.append(html);
};

var setParentNameType =  function(){
	var inp2_parentName = $("#inp2_parentName");
	inp2_parentName.empty();
	inp2_parentName.append(panentNameHtml);
}

var setIsuseType = function(){
	var inp2_isuse = $("#inp2_isuse");
	inp2_isuse.empty();
	inp2_isuse.append(IsuseHtml);
};

function initDirectoryTree() {
	$.ajax( {
		type : "POST",
		url : path_ + "/view/shop/dirStruct/dirStruct!queryDirStruct.action",
		dataType : "json",
		success : function(json) {
			var root = json.directoryList[0];
			root.state = "open";
			$("#ul_directory_tree").tree("loadData", json.directoryList);
		}
	});
}

var directoryClick = function(node) {
	$("#directory_detail_table input").each(function(i,n){n.value = "";});
	var obj = getDirectoryInfo(node);
	for(var key in obj){
		var inp = document.getElementById("inp_"+key);
		if(inp != null){
			inp.value = obj[key];
		}
	}
};

$('#addDirectoryBtn').click(function() {
	var node = $("#ul_directory_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个分类！");
		return;
	}
	$('#modalTitle-add').text('新增栏目');
	var obj = getDirectoryInfo(node);
	setParentNameType();
	setIsuseType();
	$("#inp2_isuse").val('1');
	$("#inp2_parentName").attr('disabled','disabled');
	$("#inp2_parentName").val(obj.dirId);
	var a = $("#inp2_parentName").val();
	$("#directory_detail_table input").each(function(i,n){n.value = "";});
	$("#inp2_levels").val(parseInt(obj.level)+1);
	showModal("doc-modal-add", 500, 320);
});

$('#editDirectoryBtn').click(function() {
	var node = $("#ul_directory_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个分类！");
		return;
	}
	$('#modalTitle-add').text('编辑栏目');
	var obj = getDirectoryInfo(node);
	setParentNameType();
	setIsuseType();
	$("#inp2_parentName").removeAttr('disabled');
	$("#inp2_parentName").val(obj.parentId);
	$("#inp2_id").val(obj.dirId);
	$("#inp2_directoryName").val(obj.dirName);
	$("#inp2_cateOrder").val(obj.order);
	$("#inp2_levels").val(obj.level);
	$("#inp2_isuse").val(obj.isuse);
	showModal("doc-modal-add", 500, 320);
});

$("#delDirectoryBtn").click(function() {
	var node = $("#ul_directory_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个类别！");
		return;
	}
	var msg = getDirectoryInfo(node);
	if(msg.level=="0") {
		showAlert("该目录不允许删除！");
		return;
	}
	showConfirm("确认删除？",function() {
		$.ajax( {
			type : "POST",
			url : path_ + "/view/shop/dirStruct/dirStruct!delete.action",
			dataType : "json",
			data: {"directory.id": $("#inp_dirId").val()},
			success : function(json) {
				if(json.msg==null || json.msg=="null") {
					$("#directory_detail_table input").each(function(i,n){n.value = "";});
					initDirectoryTree();//初始化菜单
					showAlert("删除成功");
				} else {
					showAlert(json.msg);
				}
			},
			error: function(e) {
				showAlert("删除失败，请检查该类别是否有子类别！");
			} 
		});
	});
});

var closeParamsModal = function(id) {
	$('#'+id).modal('close');
};

var checkForm = function() {
	var valid = $('#form1').validate();
	return true;
};

var getDirectoryInfo = function(node){
	var obj = new Object();
	obj.dirId = node.PkId;
	obj.dirName = node.text;
	obj.parentId = node.parentDirId;
	obj.parentName = node.parentName;
	obj.level = node.level;
	if(node.attributes!=null){
		obj.order = node.attributes.order;
		obj.isuse = node.attributes.isuse;
	}
	var pnode = $('#ul_directory_tree').tree('getParent',node.target);
	if(pnode!=null){
		obj.parentId = pnode.PkId;
	}
	return obj;
}