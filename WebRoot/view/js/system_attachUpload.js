$(function() {
	initAttachUploadInfo(); //初始化
});

var initAttachUploadInfo = function() {
	queryKinds();//查询附件类型
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
		var data = formGet("from_query_attach_upload");
		data["rows"] = "6"; //每页显示6条
		var url = path_ + "/view/shop/admin/attachUpload!query.action";
		pageData(url, "attachUploadResultTable", data, 1,"rowCountAttachUpload", "pageAttachUpload");
	}
};

var formatterAttachSize = function(value, row) {
	return  Math.round(value/1024)+"KB";
};

var formatterAttachAction = function(value, row) {
	var attachSrc = attachServer_ + "/"+row["attachPath"]+"/"+row["fileName"];
	var html = "<div class=\"am-btn-group am-btn-group-xs\">";
	html += "<a href='javascript:void(0)' onclick='selectAttach(\""+ row.id + "\",\""+attachSrc+"\")'><span class='am-icon-search'></span>选择</a>";
	html += "</div>";
	return html;
};

var queryExtend = function() {};
var selectAttach = function(attachId, attachSrc) {console.log(attachId+":"+attachSrc);};

$("#attachUploadBtn").click(function () {
	if ($("#kind").val().length == 0) {
		showAlert("请选择附件类型");
        return;
	}
    if ($("#attach").val().length == 0) {
        showAlert("请选择文件");
        return;
    }
    fileUpload();
});

var fileUpload = function() {
	$("#attachUploadLoading").ajaxStart(function(){
        $(this).show();
    })//开始上传文件时显示一个图片
    .ajaxComplete(function(){
        $(this).hide();
        //console.log("hide");
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
 			queryExtend();
		}, error: function (data, status, e) {//服务器响应失败处理函数
      		//alert(e);
		}
	});
	return false;
};

var showAttachUploadModal = function(w,h) {
	queryAttachs();
	w = w || 700;
	h = h || 450;
	showModal("attach_upload_modal",w,h);
}

/*$("#attachUploadRefresh").click(function() {
	queryAttachs();
});*/