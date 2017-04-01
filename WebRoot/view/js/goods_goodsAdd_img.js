var currentImgId = null;
$(".img").click(function() {
	currentImgId  = this.id;
	if($('#'+this.id).attr("src").length==0) {
		showImgUploadModal();
	} else {
		//预览图片
		//。。。。。。。。。。。。
	}
});

//选择图片的回调函数
var selectImg = function(obj) {
	if(currentImgId != null) {
		if($("#imgId"+obj.id).length==0) {
			var imgObj = $('#'+currentImgId);
			imgObj.attr("src", obj.src);
			currentImgId = null;
			//给隐藏域赋值
			imgObj.parent().children("input:eq(0)").val(obj.id);
		}
	}
};

$(".imgDiv").mouseover(function(){
	var src = $(this).children("img:eq(0)").attr("src");
	if(src!=undefined && src.length>0) {
		$(this).children("div:eq(0)").show();
	}
}).mouseout(function(){
	$(this).children("div:eq(0)").hide();
});

//删除
$(".am-icon-close").click(function(){
	//alert($(this).parent().parent().children("img:eq(0)").attr("src"));
	$(this).parent().next().attr("src","");
	$(this).parent().next().next().val("");
	$(this).hide();
});

//右移
$(".am-icon-arrow-right").click(function(){
	var self = $(this).parent().next();
	var next = $(this).parent().parent().next().children("img:eq(0)");
	var selfsrc = self.attr("src");
	var nextsrc = next.attr("src");
	
	//左右都不为空的情况才进行src的交换和imgId的交换
	if(selfsrc!=undefined && selfsrc!="" && nextsrc!=undefined && nextsrc!="") {
		//交换src
		self.attr("src",nextsrc);
		next.attr("src",selfsrc); 
		
		//交换imgId
		var selfHidden = $(this).parent().parent().children("input:eq(0)");
		var nextHidden = $(this).parent().parent().next().children("input:eq(0)");
		var selfHiddenVal = selfHidden.val();
		var nextHiddenVal = nextHidden.val();
		selfHidden.val(nextHiddenVal);
		nextHidden.val(selfHiddenVal);
	}
});

//左移
$(".am-icon-arrow-left").click(function(){
	var self = $(this).parent().next();
	var prev = $(this).parent().parent().prev().children("img:eq(0)");
	var selfsrc = self.attr("src");
	var prevsrc = prev.attr("src");

	//左右都不为空的情况才进行src的交换和imgId的交换
	if(selfsrc!=undefined && selfsrc!="" && prevsrc!=undefined && prevsrc!="") {
		//交换src
		self.attr("src",prevsrc);
		prev.attr("src",selfsrc); 
		
		//交换imgId
		var selfHidden = $(this).parent().parent().children("input:eq(0)");
		var prevHidden = $(this).parent().parent().prev().children("input:eq(0)");
		var selfHiddenVal = selfHidden.val();
		var prevHiddenVal = prevHidden.val();
		selfHidden.val(prevHiddenVal);
		prevHidden.val(selfHiddenVal);
	}
});