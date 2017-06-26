var imageUploadShowWidth = 115;
var imageUploadShowHeight = 115;
var imageUploadTdHeight = 120;

var queryImages = function() {
	//如果显示图片选择tab，则查询图片
	if(showSelectTab!=null && showSelectTab=="true") { 
		var data = formGet("from_query_img_upload");
		var url = path_ + "/view/shop/admin/imageUpload!query.action";
		queryImagesPageData(url, "imageUploadResultTable", data);
	}
};

var queryExtend = function() {};
var selectImg = function(imgId, imgSrc) {};

function queryImagesPageData(url, targetId, params, currPageNum, rowCount, page) {
	params["page"] = (currPageNum || 1);
	rowCount = rowCount || 'rowCountImgUpload';
	page = page || 'pageImgUpload';
	$.ajax({
		url : url,
		type : 'POST',
		/*data : {"page" : (currPageNum || 1)},*/
		data : params,
		dataType: "json",
		success : function(data) {
			$('#'+targetId+' tbody').empty();
			var html = createImageTable(data, targetId);
			$("#"+targetId).append(html);
			$("#"+rowCount).text(data.total);
			//显示分页
			var pages = Math.ceil(data.total / data.rowCount);
			laypage({
				cont : page, //容器。值支持id名、原生dom对象，jquery对象。
				pages : pages, //通过后台拿到的总页数
				curr : currPageNum || 1, //当前页
				groups : 5, //连续显示分页数
				//skin: 'yahei',//皮肤
				//skip: true, //是否开启跳页
				jump : function(obj, first) { //触发分页后的回调
					if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr//first一个Boolean，检测页面是否初始加载
						queryImagesPageData(url, targetId, params, obj.curr, rowCount, page);
					}
				}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert('e:' + XMLHttpRequest.responseText);
		}
	});
};

var createImageTable = function(data, targetId) {
	//如果没有记录
	/*if(data.rows != null && data.rows == 0) {
		return "<tr><td colspan='5' style='text-align:center;height:50px;vertical-align: middle;'>&nbsp;暂无记录!</td></tr>";
	}*/
	
	var html = "<tr>";
	$(data.rows).each(function(index) {
		var row = data.rows[index];
		var imgSrc = imageServer_ + "/"+row["imgPath"]+"/"+row["fileName"];
		
		html += "<td style=\"width: "+imageUploadTdHeight+"px;cursor:pointer;\" onclick=\"selectImg('"+row["id"]+"','"+imgSrc+"')\">";
		html += "	<img alt=\""+row["imgName"]+"\" title=\""+row["imgName"]+"\" id=\""+row["id"]+"\" src=\""+imgSrc+"\" " +
				"width=\""+imageUploadShowWidth+"\" height=\""+imageUploadShowHeight+"\">";
		html += "</td>";
		
		if((index+1)%5==0) {
			html += "</tr>";
			html += "<tr>";
		}
	});
	html += "</tr>";
	//console.log(html);
	return html;
};

$("#imgUploadBtn").click(function () {
    if ($("#img").val().length > 0) {
    	fileUpload();
    } else {
        alert("请选择图片");
    }
});

var fileUpload = function() {
	$("#imgUploadLoading").ajaxStart(function(){
        $(this).show();
    })//开始上传文件时显示一个图片
    .ajaxComplete(function(){
        $(this).hide();
        //console.log("hide");
    });//文件上传完成将图片隐藏起来
	
    $.ajaxFileUpload({
  		url: path_ + '/view/shop/admin/imageUpload!upload.action', //用于文件上传的服务器端请求地址
   		secureuri: false, //一般设置为false
  	 	fileElementId: 'img', //文件上传空间的id属性  <input type="file" id="file" name="file" />
      	dataType: 'json', //返回值类型 一般设置为json
 		success: function (data, status) { //服务器成功响应处理函数
       		//alert(data.imgResult.id);
 			queryImages();
 			queryExtend();
		}, error: function (data, status, e) {//服务器响应失败处理函数
      		//alert(e);
		}
	});
	return false;
};

var showImgUploadModal = function(w,h) {
	queryImages();
	w = w || 700;
	h = h || 450;
	showModal("img_upload_modal",w,h);
}

/*$("#imageUploadRefresh").click(function() {
	queryImages();
});*/