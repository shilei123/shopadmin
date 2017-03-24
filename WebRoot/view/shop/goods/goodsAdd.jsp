<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品录入</title>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${path }/ueditor/lang/zh-cn/zh-cn.js"></script>
<%
	String selectValue = request.getParameter("selectValue");
	String selectText = new String(request.getParameter("selectText").getBytes("ISO-8859-1"),"UTF-8");
%>
<style type="text/css">
.img {
	width: 100px;
	height: 100px; 
	border:0px solid #D1DEEA;
	background-image: url('${path}/images/add.png')
}
.closediv {
	position: absolute;
	bottom: 0;	
	right: 0;
	left: 0;
	display: none;
	background-color: #606060;
	width:100%;
	margin-left: 0px;
	margin-bottom: 1px;
	text-align: left;
}
.closediv>span {
	color: white;
}
.closediv:first-child {
	padding-left: 3px;
}
</style>
</head>
<body>
	<!-- content start -->
	<div class="am-cf ">
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">商品录入</strong> / <small>商品信息编辑</small>
					</div>
				</div>
				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<!-- <div class="am-panel am-panel-primary"> -->
							<table class="frame-modal-table" border="0">
								<tr>
									<td class="table_title frame-required"><span>*</span>商品分类：</td>
									<td valign="bottom">
										<%=selectText %>&nbsp;<button class="am-btn am-btn-warning am-btn-xs am-round" onclick="window.location.href='${path}/view/shop/goods/goodsTypeSelect.jsp'">编辑</button>
										</input type="hidden" value="<%=selectValue %>"/>
									</td>
								</tr>
								<tr>
									<td class="table_title">商品品牌：</td>
									<td>饰品配件</td>
								</tr>
								<tr>
									<td class="table_title frame-required"><span>*</span>商品名称：</td>
									<td><input name="bankInfo.bankName" id="goodsName" placeholder="商品名称" class="am-form-field" style="width:500px;"/></td>
								</tr>
								<tr>
									<td class="table_title">商品副标题：</td>
									<td><input name="bankInfo.bankName" id="goodsName1" placeholder="商品副标题" class="am-form-field" style="width:600px;"/></td>
								</tr>
								<!-- <tr>
									<td class="table_title">颜色：</td>
									<td>
									</td>
								</tr>
								<tr>
									<td class="table_title">重量：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="重量" class="am-form-field" style="width:%"/></td>
								</tr>
								<tr>
									<td class="table_title">食品含量：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="食品含量" class="am-form-field" style="width:100%"/></td>
								</tr> -->
								<tr>
									<td class="table_title frame-required"><span>*</span>商品价格：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="商品价格" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title frame-required"><span>*</span>市场价：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="市场价" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title frame-required"><span>*</span>成本价：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="成本价" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title frame-required"><span>*</span>商品库存：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="商品库存" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title">商品货号：</td>
									<td><input name="bankInfo.bankName" id="bankName" placeholder="商品货号" class="am-form-field" style="width:auto;"/></td>
								</tr>
								<tr>
									<td class="table_title frame-required" valign="top"><span>*</span>商品图片：</td>
									<td>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
											<div id="close1" class="closediv">
												<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-close' style="width: auto;"></span>
											</div>
											<img id="img1" alt="" src="" class="img">
											<input type="hidden" name="imgIdHidden"/>										
										</div>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
											<div id="close2" class="closediv">
												<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-close' style="width: auto;"></span>
											</div>
											<img id="img2" alt="" src="" class="img">
											<input type="hidden" name="imgIdHidden"/>
										</div>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
											<div id="close3" class="closediv">
												<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-close' style="width: auto;"></span>
											</div>
											<img id="img3" alt="" src="" class="img">
											<input type="hidden" name="imgIdHidden"/>
										</div>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
											<div id="close4" class="closediv">
												<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-close' style="width: auto;"></span>
											</div>
											<img id="img4" alt="" src="" class="img">
											<input type="hidden" name="imgIdHidden"/>
										</div>
										<div class="imgDiv" style="display: inline-block;cursor: pointer;position: relative;">
											<div id="close5" class="closediv">
												<span class='am-icon-arrow-left' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-arrow-right' style="width: 33%;"></span>&nbsp;
												<span class='am-icon-close' style="width: auto;"></span>
											</div>
											<img id="img5" alt="" src="" class="img">
											<input type="hidden" name="imgIdHidden"/>
										</div>
									</td>
								</tr>
								<tr>
									<td class="table_title" valign="top"><div style="margin-top: 13px;">商品属性：</div></td>
									<td>
										<div style="margin-top: 10px;">
											<div style="padding: 1px;">
												属性名：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												属性值：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												<a>清空</a>&nbsp;&nbsp;<a>添加</a>
											</div>
											<div style="padding: 1px;">
												属性名：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												属性值：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												<a>清空</a>&nbsp;&nbsp;<a>添加</a>
											</div>
											<div style="padding: 1px;">
												属性名：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												属性值：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												<a>清空</a>&nbsp;&nbsp;<a>添加</a>
											</div>
											<div style="padding: 1px;">
												属性名：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												属性值：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												<a>清空</a>&nbsp;&nbsp;<a>添加</a>
											</div>
											<div style="padding: 1px;">
												属性名：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												属性值：<input class="am-form-field" style="width:150px;display: inline;"/>&nbsp;
												<a>清空</a>&nbsp;&nbsp;<a>添加</a>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td class="table_title" valign="top"><div style="margin-top: 10px;">商品描述：</div></td>
									<td>
										<div class="am-tabs" style="margin-top: 10px;" data-am-tabs>
											<ul class="am-tabs-nav am-nav am-nav-tabs">
												<li class="am-active"><a href="#tab1">电脑端</a></li>
												<li><a href="#tab2">手机端</a></li>
											</ul>
										  	<div class="am-tabs-bd">
											    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
											    	<script id="pceditor" name="goods.detail" type="text/plain" style="width:99%;height:300px;"></script>
											    </div>
											    <div class="am-tab-panel am-fade" id="tab2">
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    	<p>手机端手机端手机端</p>
											    </div>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td class="table_title" valign="top"><div style="margin-top: 10px;">运费：</div></td>
									<td>
										<div style="margin-top: 10px;">
											<div style="margin-bottom: 5px;"><input type="radio" name="aa" id="ra1"/><label for="ra1" style="font-weight: normal;">卖家承担运费</label></div>
											<input type="radio" name="aa" id="ra2"/><label for="ra2" style="font-weight: normal;">买家承担运费</label></div>
										</div>
									</td>
								</tr>
								<tr>
									<td class="table_title" valign="top"><div style="margin-top: 10px;">商品发布：</div></td>
									<td>
										<div style="margin-top: 10px;">
											<div style="margin-bottom: 5px;"><input type="radio" name="a" id="r3"/><label for="r3" style="font-weight: normal;">放入仓库</label></div>
											<div style="margin-bottom: 5px;"><input type="radio" name="a" id="r1"/><label for="r1" style="font-weight: normal;">立即发布</label></div>
											<div><input type="radio" name="a" id="r2"/><label for="r2" style="font-weight: normal;">发布时间</label>&nbsp;<input type="text" /></div>
										</div>
									</td>
								</tr>
							</table>
							<div align="center" style="margin-bottom: 10px;margin-top: 10px;">
								<button type="button" class="am-btn am-btn-success" id="saveBtn"><span class="am-icon-save"></span> 提交</button>
								<button type="button" class="am-btn am-btn-default" id="closeBtn"><span class="am-icon-undo"></span> 取消</button>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- content end -->
</body>
<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('pceditor');
    //重置编辑器
    var resetEditor = function() {
        if(ue){
            ue.setContent("");
            ue.reset();
            clearLocalData();
        }
    };
	//清空编辑器草稿箱
    var clearLocalData = function() {
        UE.getEditor('editor').execCommand("clearlocaldata");
    };
    //判断编辑器是否有内容
    var hasContent = function() {
        return UE.getEditor('editor').hasContents();
    };
</script>
<script type="text/javascript">
$(function() {
	//查询类别下的品牌
	
	//查询类型下的属性
	
	//查询类别下的属性值
	
	/*如果有属性，隐藏价格和库存输入框
	      如果没有属性，显示价格和库存输入框
	*/
});

//查询品牌
var queryPinpai = function() {
	
};

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

$("#saveBtn").click(function() {
	if(!checkRequiredField("goodsName")) {
		return;
	};
});

var checkRequiredField = function(fieldId,msg) {
	if(!msg) {
		msg = $("#"+fieldId).attr("placeholder")+"必填";
	}
	var fieldValue = $("#"+fieldId).val();
	if(fieldValue.length==0) {
		layer.msg(msg, {offset: 't'});
		$("#"+fieldId).focus();
		return false;
	};
	return true;
};
</script>
</html>