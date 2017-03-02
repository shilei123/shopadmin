var showAlert = function(msg) {
	//console.log(window.top);
	if(window.parent!=null) {
		window.parent.showAlert_(msg);
	} else if(window.top!=null) {
		window.top.showAlert_(msg);
	}
};
var showConfirm = function(msg,callbackfun) {
	if(window.parent!=null) {
		window.parent.showConfirm_(msg,callbackfun);
	} else if(window.top!=null) {
		window.top.showConfirm_(msg,callbackfun);
	}
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