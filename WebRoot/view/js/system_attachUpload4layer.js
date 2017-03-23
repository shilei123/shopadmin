var index = parent.layer.getFrameIndex(window.name); 
var uploadAfterClose = false;

$(function() {
	initAttachUploadInfo(); //初始化
});

var initAttachUploadInfo = function() {
	queryKinds();//查询附件类型
	queryAttachs();
};

//查询附件类型
var queryKinds = function() {
	$.ajax({
		type : "POST",
		url : path_ + "/view/shop/admin/dict!queryDictByType.action",
		data : {"dict.type":"ATTACH_TYPE"},
		dataType : "json",
		success : function(json) {
			var kind = $("#kind");
			kind.empty();
			var html = "<option value=''>-请选择-</option>";
			$(json.dicts).each(function(index) {
				var row =  json.dicts[index];
				html += "<option value='" + row.code + "'>" + row.name + "</option>";
			});
			kind.append(html);
		},
		error : function(e) {
			//showAlert("操作失败！");
		}
	});
};

var queryAttachs = function() {
	//如果显示图片选择tab，则查询图片
	if(showSelectTab!=null && showSelectTab=="true") { 
		uploadAfterClose = false; //上传后不要关闭窗口
		var data = formGet("from_query_attach_upload");
		data["rows"] = "6"; //每页显示6条
		var url = path_ + "/view/shop/admin/attachUpload!query.action";
		pageData(url, "attachUploadResultTable", data, 1,"rowCountAttachUpload", "pageAttachUpload");
	} else {
		uploadAfterClose = true; //上传后关闭窗口
	}
};

var formatterAttachSize = function(value, row) {
	return  Math.round(value/1024)+"KB";
};

var formatterAttachAction = function(value, row) {
	var attachSrc = attachServer_ + "/"+row["attachPath"]+"/"+row["fileName"];
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='selectAttach_(\""+ row.id + "\",\""+attachSrc+"\")'><span class='am-icon-search'></span>选择</a>";
	html += "</div>";
	return html;
};

var uploadAfter_ = function() {
	//console.log(parent.uploadAfter);
	if(parent.uploadAfter!=undefined) {
		parent.uploadAfter();
	}
};

var selectAttach_ = function(id, src) {
	if(parent.selectAttach!=undefined) {
		var obj = {id:id,src:src};
		parent.selectAttach(obj);
		parent.layer.close(index);
	}
};

$("#attachUploadBtn").click(function () {
	if ($("#kind").val().length == 0) {
		showAlert("请选择附件类型");
        return;
	}
    if ($("#attach").val().length == 0) {
    	showAlert("请选择文件",{icon: 1,shade: 0.1});
        return;
    }
    fileUpload();
});

var fileUpload = function() {
	$("#attachUploadBtn").ajaxStart(function(){
		showLoading();
    })//开始上传文件时显示一个图片
    .ajaxComplete(function(){
    	closeLoading();
    });//文件上传完成将图片隐藏起来
	
    $.ajaxFileUpload({
  		url: path_ + '/view/shop/admin/attachUpload!upload.action', //用于文件上传的服务器端请求地址
  		data : {
            "attachVO.kind" : $("#kind").val()
        },
   		secureuri: false, //一般设置为false
  	 	fileElementId: 'attach', //文件上传空间的id属性  <input type="file" id="file" name="file" />
      	dataType: 'JSON', //返回值类型 一般设置为json
 		success: function (data, status) { //服务器成功响应处理函数
       		//alert(data.imgResult.id);
 			queryAttachs();
 			uploadAfter_();
 			if(uploadAfterClose) {
 				showMsg("上传成功！");
 				parent.layer.close(index);
 			}
		}, error: function (data, status, e) {//服务器响应失败处理函数
      		//alert(e);
		}
	});
	return false;
};