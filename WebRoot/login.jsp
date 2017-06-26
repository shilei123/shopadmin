<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
  	<jsp:include page="/include/common.jsp"></jsp:include>
  	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<title>${title }</title>
  	<meta name="description" content="这是一个 index 页面">
  	<meta name="keywords" content="index">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<meta name="renderer" content="webkit">
  	<meta http-equiv="Cache-Control" content="no-siteapp" />
  	<link rel="stylesheet" href="${path }/AmazeUI-2.7.2/assets/css/login/app.css">
  	<style type="text/css">
  		.login-font {
  			font-size: 20px;
  			font-weight: bold;
  		}
  	</style>
	<script type="text/javascript">
	$(function(){
		
	});
	
	function login(){
		var userid = document.getElementById("email").value;
		var pwd = document.getElementById("password").value;
		//var verifyCode = document.getElementById("verifyCode").value;
		var msg = document.getElementById("div_msg");
		if(userid=="" || pwd==""/*  || verifyCode=="" */ ){
			msg.innerHTML = "用户名密码不能为空";
		}else{
			msg.innerHTML = "&nbsp;";
			var data = "&user.UId="+userid+"&user.UPwd="+pwd/* +"&verifyCode="+verifyCode */;
			$.ajax({
				type: "POST",
				url: "login.action",
				data: data,
				dataType: "json",
				success: function(json) {
					if(json.success) {
						window.location.href = "${path}/view/home.jsp";
					} else {
						msg.innerText = json.msg;
					}
				},error: function(json) {
					msg.innerHTML = "登录验证异常";
				}
			});
		}
	}
	</script>
</head>
<body data-type="login">
  <div class="am-g myapp-login">
	<div class="myapp-login-logo-block tpl-login-max">
		<div class="myapp-login-logo-text">
			<div class="myapp-login-logo-text">
				<span>${title }</span> 
			</div>
		</div>
		<div class="login-font">
			<i>管理员登录 </i><!--  or <span> Sign Up</span> -->
		</div>
		<div id="div_msg" style="color: red; padding-left: 85px;margin-top: 0px;">&nbsp;</div>
		<div class="am-u-sm-10 login-am-center">
			<form class="am-form">
				<fieldset>
					<div class="am-form-group">
						<input type="text" id="email" class="" id="doc-ipt-email-1" placeholder="输入用户名" value="admin">
					</div>
					<div class="am-form-group">
						<input type="password" class="" id="password" placeholder="请输入密码" value="123456">
					</div>
					<p><button type="button" class="am-btn am-btn-default" onclick="login()">登录</button></p>
				</fieldset>
			</form>
		</div>
	</div>
</div>
<script src="${path }/AmazeUI-2.7.2/assets/js/app.js"></script>
</body>
</html>