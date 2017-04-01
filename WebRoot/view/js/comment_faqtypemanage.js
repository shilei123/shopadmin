$(function() {
	$("#ul_category_tree").tree({
		onClick: faqTypeClick 
	});
	initFaqTypeTree();
	
	//新增窗口的取消按钮
	$("#cancelBtn").click(function() {
		closeParamsModal("doc-modal-add");
	});
	
	//新增窗口的保存按钮
	$("#saveCategoryBtn").click(function() {
		var node = $("#ul_category_tree").tree("getSelected");
		if(node==null){ return; }
		$("#form1").validate({
	        submitHandler:function(form){
	            $('#form1').form('submit', {
		    		url:  path_ + "/view/faqType/faqType!saveFaqType.action",
		    		onSubmit: function() {
		    			return checkForm();
		    		},
		    		success:function(data) {
		    			showAlert("操作成功！");
		    			initFaqTypeTree();
		    			$("#faqType_detail_table input").each(function(i,n){n.value = "";});
		    			closeParamsModal("doc-modal-add");
		    		}
		    	});
	        }
	    });
		$("#form1").submit();
	});
	
	//编辑窗口的保存按钮
	$("#editSaveCategoryBtn").click(function() {
		var node = $("#ul_category_tree").tree("getSelected");
		if(node==null){ return; }
		$("#form1").validate({
			submitHandler:function(form){
				$('#form1').form('submit', {
					url:  path_ + "/view/faqType/faqType!updateFaqType.action",
					onSubmit: function() {
						return checkForm();
					},
					success:function(data) {
						showAlert("操作成功！");
						initFaqTypeTree();
						$("#faqType_detail_table input").each(function(i,n){n.value = "";});
						closeParamsModal("doc-modal-add");
					}
				});
			}
		});
		$("#form1").submit();
	});
});

function initFaqTypeTree() {
	$.ajax( {
		type : "POST",
		url : path_ + "/view/faqType/faqType!initFaqTypeTree.action",
		dataType : "json",
		success : function(json) {
			console.log(json.trees);
			var root = json.trees[0];
			root.state = "open";
			$("#ul_category_tree").tree("loadData", json.trees);
		}
	});
}

var faqTypeClick  = function(node) {
	$("#faqType_detail_table input").each(function(i,n){n.value = "";});
	var obj = getFaqTypeInfo(node);
	for(var key in obj){
		var inp = document.getElementById("inp_"+key);
		if(inp != null){
			inp.value = obj[key];
		}
	}
};

$('#addCategoryBtn').click(function() {
	var node = $("#ul_category_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个分类！");
		return;
	}
	$('#modalTitle-add').text('新增类别');
	$('#editSaveCategoryBtn').attr('style','display:none');
	$('#saveCategoryBtn').attr('style','display:inline');
	var obj = getFaqTypeInfo(node);
	$("#faqType_detail_table input").each(function(i,n){n.value = "";});
	$("#inp2-parentTypeId").val(obj.pkId);
	$("#inp2-typeLevel").val(parseInt(obj.typeLevel)+1);
	showModal("doc-modal-add", 500, 300);
});

$('#editCategoryBtn').click(function() {
	var node = $("#ul_category_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个分类！");
		return;
	}
	$('#modalTitle-add').text('编辑类别');
	$('#editSaveCategoryBtn').attr('style','display:inline');
	$('#saveCategoryBtn').attr('style','display:none');
	var obj = getFaqTypeInfo(node);
	$("#inp2-id").val(obj.pkId);
	$("#inp2-typeName").val(obj.typeName);
	$("#inp2-typeDesc").val(obj.typeDesc);
	$("#inp2-typeCode").val(obj.typeCode);
	$("#inp2-typeOrder").val(obj.typeOrder);
	$("#inp2-parentTypeId").val(obj.pkId);
	$("#inp2-typeLevel").val(parseInt(obj.typeLevel)+1);
	showModal("doc-modal-add", 500, 300);
});

$("#delCategoryBtn").click(function() {
	var node = $("#ul_category_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个类别！");
		return;
	}
	JSON.stringify(node);
	var obj = getFaqTypeInfo(node);
	if(obj.categoryLevel=="0") {
		showAlert("该目录不允许删除！");
		return;
	}
	showConfirm("确认删除？",function() {
		$.ajax( {
			type : "POST",
			url : path_ + "/view/faqType/faqType!delFaqType.action",
			dataType : "json",
			data: {"faqType.id": node.pkId},
			success : function(json) {
				if(json.msg==null || json.msg=="null" || json.msg=="") {
					$("#faqType_detail_table input").each(function(i,n){n.value = "";});
					initFaqTypeTree();
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

var getFaqTypeInfo = function(node){
	var obj = new Object();
	obj.pkId = node.pkId;
	obj.typeName = node.text;
	obj.typeDesc = node.typeDesc;
	obj.typeCode = node.typeCode;
	obj.parentId = node.parentId;
	obj.typeLevel = node.typeLevel;
	if(node.attributes!=null){
		obj.typeOrder = node.attributes.typeOrder;
	}
	/*var pnode = $('#ul_category_tree').tree('getParent',node.target);
	if(pnode!=null){
		obj.parentId = pnode.pkId;
	}*/
	return obj;
}