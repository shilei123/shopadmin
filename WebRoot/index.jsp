<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <script type="text/javascript" src="<%=path %>/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/ajaxfileupload.js"></script>
    <script type="text/javascript">
        $(function () {
            $(":button").click(function () {
                if ($("#file1").val().length > 0) {
                    ajaxFileUpload();
                }
                else {
                    alert("请选择图片");
                }
            });
        });
        
        function ajaxFileUpload() {
            $.ajaxFileUpload({
          		url: '<%=path %>/upload.do', //用于文件上传的服务器端请求地址
           		secureuri: false, //一般设置为false
          	 	fileElementId: 'file1', //文件上传空间的id属性  <input type="file" id="file" name="file" />
              	dataType: 'json', //返回值类型 一般设置为json
         		success: function (data, status) { //服务器成功响应处理函数
               		alert(data.msg);
       			}, error: function (data, status, e) {//服务器响应失败处理函数
              		alert(e);
        		}
			});
            return false;
		}
    </script>
</head>
<body>
    <p><input type="file" id="file1" name="file" /></p>
    <input type="button" value="上传" />
    <p><img id="img1" alt="上传成功啦" src="" /></p>
</body>
</html>
