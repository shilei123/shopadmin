var showMsg = function(msg) {
	if(window.parent.parent!=null) {
		window.parent.parent.showMsg_(msg);
	} else if(window.parent!=null) { 
		window.top.showMsg_(msg);
	} else if(window.top!=null) {
		window.top.showMsg_(msg);
	}
};
var showAlert = function(msg) {
	//console.log(window.top);
	if(window.parent.parent!=null) {
		window.parent.parent.showAlert_(msg);
	} else if(window.parent!=null) { 
		window.top.showAlert_(msg);
	} else if(window.top!=null) {
		window.top.showAlert_(msg);
	}
};
var showConfirm = function(msg,callbackfun) {
	if(window.parent.parent!=null) {
		window.parent.parent.showConfirm_(msg,callbackfun);
	} else if(window.parent!=null) {
		window.parent.showConfirm_(msg,callbackfun);
	} else if(window.top!=null) {
		window.top.showConfirm_(msg,callbackfun);
	}
};
var openTab = function(tabId, tabName, tabUrl) {
	if(window.parent.parent!=null) {
		window.parent.parent.addMenu(tabId, tabName, tabUrl);
	} else if(window.parent!=null) { 
		window.top.addMenu(tabId, tabName, tabUrl);
	} else if(window.top!=null) {
		window.top.addMenu(tabId, tabName, tabUrl);
	}
};
var closeTab = function(tabId) {
	if(window.parent.parent!=null) {
		window.parent.parent.closeTab(tabId);
	} else if(window.parent!=null) { 
		window.top.closeTab(tabId);
	} else if(window.top!=null) {
		window.top.closeTab(tabId);
	}
};

var closeThisTab = function() {
	if(window.parent.parent!=null) {
		window.parent.parent.closeTab(thisTabId);
	} else if(window.parent!=null) { 
		window.top.closeTab(thisTabId);
	} else if(window.top!=null) {
		window.top.closeTab(thisTabId);
	}
};
var showLoading = function() {
	/*var index = layer.load(2, {
    	shade: [0.1,'#fff'] //0.1透明度的白色背景
    });*/
	//加载层-默认风格
	layer.load();
	//加载层-风格2
	//layer.load(1);
	//加载层-风格3
	//layer.load(2);
	//加载层-风格4
	/*layer.msg('加载中', {
	  icon: 16
	  ,shade: 0.01
	});*/
};

var closeLoading = function() {
	layer.closeAll('loading');
};

var showLayerModal = function(title, url, w, h) {
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title: title,
	  area: [w+'px', h+'px'],
	  fixed: true, //不固定
	  maxmin: false,
	  shadeClose: false,
	  shade: 0,
	  content: [url, 'no']
	});
};

var showLayerMsg = function(msg) {
	layer.msg(msg, {offset: 't'});
};

var showTopTips = function(msg,targetId) {
	layer.tips(msg, '#'+targetId, {
		  tips: [1, '#F39B12']
	});
};
var showRightTips = function(msg,targetId) {
	layer.tips(msg, '#'+targetId);
};
var showLeftTips = function(msg,targetId) {
	layer.tips(msg, '#'+targetId, {
		tips: [4, '#F39B12']
	});
};
var showDownTips = function(msg,targetId) {
	layer.tips(msg, '#'+targetId, {
		tips: [3, '#F39B12']
	});
};

var showModal = function(id, w, h) {
	var $confirm = $('#'+id);
    var confirm = $confirm.data('amui.modal');
    if (confirm) {
		confirm.options.width =  w;
		confirm.options.height =  h;
		confirm.toggle(this);
    } else {
    	$confirm.modal({closeViaDimmer : 0,dimmer : true,width : w,height : h});
    } 
};

var closeModal = function(id) {
	$('#'+id).modal("close");
};

var formatterFlag = function(value, row){
	return value=="0"?"有效":"无效";
};

var showAttachUploadModal = function(params) {
	if(params==undefined) {
		params = {showSelectTab:true,uploadAfterClose:false}; //默认显示查询tab,uploadAfterClose暂时不用留作扩展
	}
	var url = path_+'/view/shop/system/attachUpload4layer.jsp?showSelectTab='+params.showSelectTab;
	showLayerModal('附件上传',url, 750, 450);
}

var showImgUploadModal = function(params) {
	if(params==undefined) {
		params = {showSelectTab:true,uploadAfterClose:false};//默认显示查询tab,uploadAfterClose暂时不用留作扩展
	}
	var url = path_+'/view/shop/system/imageUpload4layer.jsp?showSelectTab='+params.showSelectTab;
	showLayerModal('图片上传',url, 700, 450);
}

var pushString = function(arr, val) {
	if(arr == undefined || arr==null)
		return;
	var exists = false;
	for(var i = 0; i < arr.length; i++) {
		if(arr[i]==val) {
			exists = true;
			break;
		}
	}
	if(!exists)
		arr.push(val);
};

var pushJson = function(arr, key, val, jsonobj) {
	if(arr == undefined || arr==null)
		return;
	var exists = false;
	for(var i = 0; i < arr.length; i++) {
		var obj = arr[i];
		if(obj[key]==val) {
			exists = true;
			break;
		}
	}
	if(!exists)
		arr.push(jsonobj);
};