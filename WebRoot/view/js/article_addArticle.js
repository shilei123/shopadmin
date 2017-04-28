var directoryTypeHtml = "";
var articleTypeHtml = "";
var ue = UE.getEditor("memo");
function setContent(content) {
	ue.addListener("ready", function() {
    	// editor准备好之后才可以使用
    	ue.setContent(content);
    });
}
 
$(function(){
	findArticleType();
	findDirectoryType();
	initArticleInfo();
});

var initArticleInfo = function() {
	var articleId = $("#articleId").val();
	if(articleId != "") {
		//根据id查询文章
		$.ajax({
			 url : path_ + "/view/shop/article/article!queryArticle.action",
			 data : {"article.id" :articleId},
			 dataType: "json",
			 type : 'POST',
			 success : function(data){
				console.log(data);
				setType();
				$("#articleId").val(data.article.id);
				$("#articleTitle").val(data.article.articleTitle);
				$("#author").val(data.article.author);
				$("#articleType").val(data.article.articleType)
				$("#directoryType").val(data.article.directoryId)
				$("#source").val(data.article.source);
			    $("#url").val(data.article.url);
			    $("#abstracts").val(data.article.abstracts);
			    $("#imgIdHidden").val(data.article.picture);
				$("#img1").attr("src",data.article.picture);
			    var iscontent = data.article.iscontent;
			    if(iscontent == "0"){
			    	$("#iscontenty").removeAttr("checked");
			    	$("#iscontentn").attr("checked","checked");
			    }
			    setContent(data.article.memo);
			 }
		});
	} 
};

//重置select框
var setType = function(){
	 var directoryType = $("#directoryType");
		directoryType.empty();
		directoryType.html(directoryTypeHtml);
	var articleType = $("#articleType");
		articleType.empty();
		articleType.html(articleTypeHtml);
}


var findDirectoryType = function(){
	var directoryType = $("#directoryType");
	directoryType.empty();
		$.ajax({
			url :path_ + "/view/shop/article/article!queryDirectoryType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				console.log(data);
				var html = "<option value='-1'>-请选择-</option>";
				$(data.directoryTypeList).each(function(index) {
					var dirType = data.directoryTypeList[index];
					var levels = data.directoryTypeList[index].level;
					if(levels != "0"){
						html += "<option value='" + dirType.id + "'>" + dirType.dirName + "</option>";
					}
				});
				directoryTypeHtml = html;
				directoryType.append(html);
			}
		});
}

var findArticleType = function() {
	var articleType = $("#articleType");
	articleType.empty();
		$.ajax({
			url :path_ + "/view/shop/article/article!queryArticleType.action",
			type : 'POST',
			data : null,
			dataType: "json",
			success : function(data) {
				var html = "<option value='-1'>-请选择-</option>";
				$(data.articleTypeList).each(function(index) {
					var artType = data.articleTypeList[index];
					html += "<option value='" + artType.code + "'>" + artType.name + "</option>";
				});
				articleTypeHtml = html;
				articleType.append(html);
			}
		});
};

//打开图片上传的窗口
$("#img1").click(function() {
		showImgUploadModal();
});

//选择图片的回调函数
var selectImg = function(obj) {
		if($("#img1"+obj.id).length==0) {
			var imgObj = $('#img1');
			imgObj.attr("src", obj.src);
			currentImgId = null;
			//给隐藏域赋值
			imgObj.parent().children("input:eq(0)").val(obj.src);
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
	$("#imgIdHidden").val('');
	$("#img1").attr("src","");
	$(this).hide();
});

//表单验证
var checkBankSumbit = function() {
	var articleTitle = $("#articleTitle").val();
	var author = $("#author").val();
	var source = $("#source").val();
	var articleType = $("#articleType").val();
	var directoryType = $("#directoryType").val();
	
	if(articleTitle == null || articleTitle=="") {
		$("#errorMsg").html("文章标题不能为空！");
		$("#articleTitle").focus();
		return false;
	}
	if(author == null || author=="" ) {
		$("#errorMsg").html("作者不能为空！");
		$("#author").focus();
		return false;
	}
	if(source == null || source=="" ) {
		$("#errorMsg").html("来源不能为空！");
		$("#source").focus();
		return false;
	}
	if(articleType==null || articleType == "-1"){
		$("#errorMsg").html("分类不能为空！");
		$("#articleType").focus();
		return false;
	}
	if(directoryType==null || directoryType == "-1"){
		$("#errorMsg").html("文章目录不能为空！");
		$("#directoryType").focus();
		return false;
	}
	if(articleTitle.length > 50){
		$("#errorMsg").html("文章标题不能过长！");
		$("#articleTitle").focus();
		return false;
	}
	if(ue.hasContents() == false){
		$("#errorMsg").html("内容不能为空！");
		$("#memo").focus();
		return false;
	}
	if(!checkRequiredRadio("iscontent", "请选择是否允许评论")){
		return false;
	}
	$("#errorMsg").html("&nbsp;");
	return true;
};

//保存
$("#saveBtn").click(function() {
	//表单验证
	if(!checkBankSumbit()) {
		return;
	}
	var articleId = $("#articleId").val();
	var articleTitle = $("#articleTitle").val();
	var author = $("#author").val();
	var articleType = $("#articleType").val();
	var directoryId = $("#directoryType").val();
	var source = $("#source").val();
	var url = $("#url").val();
	var abstracts = $("#abstracts").val();
	var iscontent = $("#iscontent").val();
	var imgIdHidden = $("#imgIdHidden").val();
	var memo = ue.getContent();
	var data = {
		"article.id" : articleId,
		"article.articleTitle" : articleTitle,
		"article.author" : author,
		"article.articleType" : articleType,
		"article.directoryId" : directoryId,
		"article.source" : source,
		"article.url" : url,
		"article.abstracts" : abstracts,
		"article.iscontent" : iscontent,
		"article.memo": memo,
		"article.picture": imgIdHidden,
	};
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/article/article!save.action",
		data : data,
		dataType : "json",
		success : function(data) {
				showAlert("操作成功");
				window.location.href=path_ + "/view/shop/article/article.jsp";
		}
	});
});

//验证必填
var checkRequiredRadio = function(radioName, errormsg) {
	var rv = $("input[name='" + radioName + "']:checked").val();
	if(rv==undefined || rv==null || rv=="") {
		$("#errormsg").html(errormsg);
		return false;
	}
	$("#"+radioName).val(rv);
	return true;
};

//重置
$("#closeBtn").click(function(){
	refresh();
});
//取消
$("#cancelBtn").click(function(){
	window.location.href=path_ + "/view/shop/article/article.jsp";
});

function refresh(){
	 window.location.reload();//刷新当前页面.
};