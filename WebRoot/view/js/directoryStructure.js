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
		var node = $("#ul_directory_tree").tree("getSelected");
		if(node==null){ return; }
		$("#inp2_parentName").removeAttr('disabled');
		$("#form1").validate({
	        submitHandler:function(form){
	            $('#form1').form('submit', {
		    		url:  path_ + "/view/shop/directoryStructure/directoryStructure!save.action",
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

var findDirectoryType = function() {
	var inp2_parentName = $("#inp2_parentName");
	inp2_parentName.empty();
		$.ajax({
			url :path_ + "/view/shop/directoryStructure/directoryStructure!queryDirectoryType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				var html = "<option value='-1'>-请选择-</option>";
				$(data.directoryTypeList).each(function(index) {
					var dirType = data.directoryTypeList[index];
					html += "<option value='" + dirType.id + "'>" + dirType.directoryName + "</option>";
				});
				panentNameHtml = html;
				inp2_parentName.append(html);
			}
		});
};

var findIsuseType = function() {
	var inp2_isuse = $("#inp2_isuse");
	inp2_isuse.empty();
	var html = "<option value=''>-请选择-</option>";
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
		url : path_ + "/view/shop/directoryStructure/directoryStructure!queryDirectoryStructure.action",
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
	$("#inp2_parentName").val(obj.directoryId);
	var a = $("#inp2_parentName").val();
	$("#directory_detail_table input").each(function(i,n){n.value = "";});
	$("#inp2_levels").val(parseInt(obj.levels)+1);
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
	$("#inp2_id").val(obj.directoryId);
	$("#inp2_directoryName").val(obj.directoryName);
	$("#inp2_cateOrder").val(obj.cateOrder);
	$("#inp2_levels").val(obj.levels);
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
	if(msg.levels=="0") {
		showAlert("该目录不允许删除！");
		return;
	}
	showConfirm("确认删除？",function() {
		$.ajax( {
			type : "POST",
			url : path_ + "/view/shop/directoryStructure/directoryStructure!delete.action",
			dataType : "json",
			data: {"directory.id": $("#inp_directoryId").val()},
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
	obj.directoryId = node.PkId;
	obj.directoryName = node.text;
	obj.parentId = node.parentDirectoryId;
	obj.parentName = node.parentName;
	obj.levels = node.levels;
	if(node.attributes!=null){
		obj.cateOrder = node.attributes.cateOrder;
		obj.isuse = node.attributes.isuse;
	}
	var pnode = $('#ul_directory_tree').tree('getParent',node.target);
	if(pnode!=null){
		obj.parentId = pnode.PkId;
	}
	return obj;
}