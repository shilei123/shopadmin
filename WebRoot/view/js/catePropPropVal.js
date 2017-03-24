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
	if(node.children!=null){
		showAlert("请选择一个子类别！");
		return;
	}
	//是最后一级，拼html
	$.ajax({
		type : "POST",
		//url : path_ + "/view/catePropPropval/catePropPropval!queryList.action",
		dataType : "json",
		data: {"category.id": node.pkId},
		success : function(json){
			var html = "";
			html += "<tr><td></td></tr>";
			/*if(json.msg==null || json.msg=="null") {
				$("#category_detail_table input").each(function(i,n){n.value = "";});
				initCategoryTree();//初始化菜单
				showAlert("删除成功");
			} else {
				showAlert(json.msg);
			}*/
			
		},
		error: function(e){
			showAlert("请检查该类别是否有子类别！");
		} 
	});
};

/*var getCategoryInfo = function(node){
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
}*/


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
});

var closeParamsModal = function(id) {
	$('#'+id).modal('close');
};