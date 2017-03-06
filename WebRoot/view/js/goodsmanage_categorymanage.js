$(function() {
	$("#ul_agency_tree").tree({
		//onDblClick: agencyClick
		onClick: agencyClick
	});
	initAgencyTree();
});

function initAgencyTree() {
	$.ajax( {
		type : "POST",
		url : path_ + "/view/agency/agency!agencyTree.action",
		dataType : "json",
		success : function(json) {
			var root = json.trees[0];
			root.state = "open";
			$("#ul_agency_tree").tree("loadData", json.trees);
		}
	});
}

var agencyClick = function(node) {
	$("#agency_detail_table input").each(function(i,n){n.value = "";});
	var msg = getAgencyInfo(node);
	for(var key in msg){
		var inp = document.getElementById("inp_"+key);
		if(inp != null){
			inp.value = msg[key];
		}
	}
	//$("#inp_initFlag").val(msg["initFlag"]);
};

$('#addAgencyBtn').click(function() {
	var node = $("#ul_agency_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个机构！");
		return;
	}
	var msg = getAgencyInfo(node);
	$("#agency_detail_table input").each(function(i,n){n.value = "";});
	$("#inp_sts").val("1");
	$("#inp_initFlag").val("0");
	$("#inp_parentAgencyId").val(msg.agencyId);
	$("#inp_parentAgencyName").val(msg.agencyName);
	$("#inp_parentShortName").val(msg.shortName);
	//document.getElementById("inp_parentAgencyPath").value = msg.agencyPath;
});

$("#saveAgencyBtn").click(function(json) {
	/*$('#form1').form('submit', {
		url:  path_ + "/view/agency/agency!saveAgency.action",
		onSubmit: function() {
			return checkForm();
		},
		success:function(data) {
			showAlert("操作成功！");
			initAgencyTree();
		}
	});*/
	var node = $("#ul_agency_tree").tree("getSelected");
	if(node==null){ return; }
	$("#form1").validate({
		//debug:true,
        submitHandler:function(form){
            $('#form1').form('submit', {
	    		url:  path_ + "/view/agency/agency!saveAgency.action",
	    		onSubmit: function() {
	    			return checkForm();
	    		},
	    		success:function(data) {
	    			showAlert("操作成功！");
	    			initAgencyTree();
	    			$("#agency_detail_table input").each(function(i,n){n.value = "";});
	    		}
	    	});
            //form.submit();
        }    
    });
	$("#form1").submit();
});

$("#delAgencyBtn").click(function() {
	var node = $("#ul_agency_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个机构！");
		return;
	}
	var msg = getAgencyInfo(node);
	if(msg.agencyLevel=="0") {
		showAlert("该机构不允许删除！");
		return;
	}
	showConfirm("确认删除？",function() {
		$.ajax( {
			type : "POST",
			url : path_ + "/view/agency/agency!delete.action",
			dataType : "json",
			data: {"tree.id": $("#inp_id").val(),"tree.agencyId": $("#inp_agencyId").val()},
			success : function(json) {
				if(json.msg==null || json.msg=="null") {
					$("#agency_detail_table input").each(function(i,n){n.value = "";});
					initAgencyTree();//初始化菜单
					showAlert("删除成功");
				} else {
					showAlert(json.msg);
				}
			},
			error: function(e) {
				//alert(e);
				showAlert("删除失败，请检查该机构是否有子机构！");
			} 
		});
	});
});

$("#openAgencyParamsBtn").click(function() {
	var node = $("#ul_agency_tree").tree("getSelected");
	if(node==null){
		showAlert("请选择一个机构！");
		return;
	}
	var msg = getAgencyInfo(node);
	$('#agencyParamsFrame').attr('src', path_ + '/view/agency/agencyParamCfg.jsp?agencyId='+msg.agencyId);
	showModal("doc-modal-1", 600, 450);
});

var closeParamsModal = function() {
	$('#doc-modal-1').modal('close');
};

var checkForm = function() {
	/*var valid = $('#form1').validate();
	console.log("valid");
	console.log(valid);*/
	return true;
};

var getAgencyInfo = function(node){
	var obj = new Object();
	obj.agencyId = node.id;
	obj.agencyName = node.text;
	if(node.attributes!=null){
		obj.agencyPath = node.attributes.agencyPath;
		obj.id = node.attributes.agencyPkId;
		obj.order = node.attributes.order;
		obj.agencyLevel = node.attributes.agencyLevel;
		obj.shortName = node.attributes.shortName;
		obj.sts = node.attributes.sts;
		obj.initFlag = node.attributes.initFlag;
	}
	var pnode = $('#ul_agency_tree').tree('getParent',node.target); 
	if(pnode!=null){
		obj.parentAgencyId = pnode.id;
		obj.parentAgencyName = pnode.text;
		obj.parentShortName = pnode.attributes.shortName;
		obj.parentAgencyPath = pnode.attributes.agencyPath;
	}
	return obj;
}