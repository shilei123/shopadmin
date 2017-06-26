<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
</head>
<body>
    <p><input type="text" id="filename" name="filename" /></p>
    <input type="button" id="transmit" value="选择" />
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); 
//给父页面传值
$('#transmit').click(function(){
    parent.$('#bankName').val($("#filename").val());
    parent.layer.close(index);
});
</script>
</html>
