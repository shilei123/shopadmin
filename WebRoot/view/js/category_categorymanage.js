$(function() {
	$("#ul_category_tree").tree({
		onClick: categoryClick
	});
	initCategoryTree();
	
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
		    		url:  path_ + "/view/category/category!saveCategory.action",
		    		onSubmit: function() {
		    			return checkForm();
		    		},
		    		success:function(data) {
		    			showAlert("操作成功！");
		    			initCategoryTree();
		    			$("#category_detail_table input").each(function(i,n){n.value = "";});
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
					url:  path_ + "/view/category/category!updateCategory.action",
					onSubmit: function() {
						return checkForm();
					},
					success:function(data) {
						showAlert("操作成功！");
						initCategoryTree();
						$("#category_detail_table input").each(function(i,n){n.value = "";});
						closeParamsModal("doc-modal-add");
					}
				});
			}
		});
		$("#form1").submit();
	});
});

function initCategoryTree() {
	$.ajax( {
		type : "POST",
		url : path_ + "/view/category/category!categoryTree.action",
		dataType : "json",
		success : function(json) {
			console.log(json);
			var root = json.trees[0];
			root.state = "open";
			$("#ul_category_tree").tree("loadData", json.trees);
		}
	});
}

var categoryClick = function(node) {
	$("#category_detail_table input").each(function(i,n){n.value = "";});
	var obj = getCategoryInfo(node);
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
	var obj = getCategoryInfo(node);
	$("#category_detail_table input").each(function(i,n){n.value = "";});
	$("#inp2-parentId").val(obj.categoryId);
	$("inp2-levels").val(parseInt(obj.levels)+1);
	showModal("doc-modal-add", 600, 400);
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
	var obj = getCategoryInfo(node);
	$("#inp2-id").val(obj.categoryId);
	$("#inp2-cateName").val(obj.categoryName);
	$("#inp2-cateOrder").val(obj.cateOrder);
	$("#inp2-memo").val(obj.memo);
	$("#inp2-levels").val(obj.levels);
	$("#inp2-parentId").val(obj.parentId);
	$("#inp2-logo").val(obj.logo);
	$("#inp2-url").val(obj.url);
	$("#inp2-isuse").val(obj.isuse);
	showModal("doc-modal-add", 600, 400);
});

$("#delCategoryBtn").click(function() {
	var node = $("#ul_category_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个类别！");
		return;
	}
	var msg = getCategoryInfo(node);
	if(msg.categoryLevel=="0") {
		showAlert("该目录不允许删除！");
		return;
	}
	showConfirm("确认删除？",function() {
		$.ajax( {
			type : "POST",
			url : path_ + "/view/category/category!delCategory.action",
			dataType : "json",
			data: {"category.id": $("#inp_categoryId").val()},
			success : function(json) {
				if(json.msg==null || json.msg=="null") {
					$("#category_detail_table input").each(function(i,n){n.value = "";});
					initCategoryTree();//初始化菜单
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
<<<<<<< HEAD
});

$("#openCategoryParamsBtn").click(function() {
	var node = $("#ul_category_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个类别！");
		return;
	}
	if(node.children!=null){
		showAlert("请选择一个具体的商品！");
		return;
	}
	var obj = getCategoryInfo(node);
	$('#categoryParamsFrame').attr('src', path_ + '/view/shop/category/category_property.jsp?categoryId='+obj.categoryId);
	showModal("doc-modal-1", 600, 450);
});

var closeParamsModal = function(id) {
	$('#'+id).modal('close');
};

var checkForm = function() {
	var valid = $('#form1').validate();
	return true;
};

var getCategoryInfo = function(node){
	var obj = new Object();
	obj.categoryId = node.pkId;
	obj.categoryName = node.text;
	obj.parentId = node.parentId;
	obj.levels = node.levels;
	if(node.attributes!=null){
		obj.memo = node.attributes.memo;
		obj.cateOrder = node.attributes.cateOrder;
		obj.logo = node.attributes.logo;
		obj.url = node.attributes.url;
		obj.isuse = node.attributes.isuse;
	}
	var pnode = $('#ul_category_tree').tree('getParent',node.target);
	if(pnode!=null){
		obj.parentId = pnode.pkId;
	}
	return obj;
}
=======
>>>>>>> refs/remotes/origin/yangchaowen
