var index = parent.layer.getFrameIndex(window.name); 
$(function() {
	$("#ul_category_tree").tree({
		onClick: categoryClick
	});
	initCategoryTree();
});

function initCategoryTree() {
	$.ajax( {
		type : "POST",
		url : path_ + "/view/shop/category/category!categoryTree.action",
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

$("#saveBtn").click(function(){
	var node = $("#ul_category_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个类别！");
		return;
	}
	if(node.children!=null){
		showAlert("请选择一个具体的商品！");
		return;
	}
	var inp_categoryId = $("#inp_categoryId").val();
	var inp_categoryName = $("#inp_categoryName").val();
	selectCategocy(inp_categoryId,inp_categoryName);
});


var selectCategocy = function(CategocyId, name) {
		var obj = {CategocyId:CategocyId,name:name};
		parent.selectCategocy(obj);
		parent.layer.close(index);
};

$("#closeBtn").click(function(){
	parent.layer.close(index);
});

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
