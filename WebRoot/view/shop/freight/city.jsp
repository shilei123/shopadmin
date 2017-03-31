<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
</head>
<body>
<ul id="J_CityList">
	<style type="text/css">
em.zt {
	font-size: 0;
	line-height: 0;
	width: 0;
	height: 0;
	display: inline-block;
	padding: 0;
	border: 4px solid;
	border-color: #333 transparent transparent transparent; /
	border-style: solid dashed dashed dashed;
}
</style>
<li>
	<div class=" dcity clearfix">
		<div class="province-list" style="width: 600px;">
<div class="ecity" style="width: 140px;margin-right:0px;">
<span class="gareas"> <input type="checkbox" class="J_Province" id="J_Province_11" value="11" /> 
<label for="J_Province_11">北京市</label> 
<span class="check_num" />
</span><em class="zt trigger"></em>
<div class="citys">
		<span class="areas">
		<input type="checkbox" class="J_City" id="J_City_1101" value="1101" />
		<label for="J_City_1101">市辖区</label>
		</span> 
		<span class="areas">
		<input type="checkbox" class="J_City" id="J_City_1102" value="1102" />
		<label for="J_City_1102">县</label>
		</span> 
	</div>
	</span>
<div class="btns">
	<button class="J_Submit" type="button">确定</button>
	<button class="J_Cancel" type="button">取消</button>
</div>
</div>
	</li>
</ul>
<input type="hidden" id="filename">
<input type="hidden" id="file">
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); 
//给父页面传值
$('.J_Submit').click(function(){
	var str="";
	var aaa="";
	$('input[type="checkbox"]:checked').each(function(){ 
	str += $(this).val()+",";
	});
	
	$('input[type="checkbox"]:checked').each(function(){
        if( $(this).prop("checked") == true ) {
        	aaa += $(this).next().text()+',';
        } else {
            console.log("1");
        }
    });
	$("#filename").val(str);
	$("#file").val(aaa);
    parent.$('#divts').html($("#file").val());
    parent.$('#cityName').val($("#filename").val());
    parent.layer.close(index);
});

$('.J_Cancel').click(function(){
	 parent.layer.close(index);
});
</script>
</html>
