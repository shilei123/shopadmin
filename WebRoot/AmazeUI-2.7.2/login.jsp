<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>系统登录</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
  <script type="text/javascript">
$(function(){
	
});

function login(){
	var userid = document.getElementById("TxtUserName").value;
	var pwd = document.getElementById("TxtPassword").value;
	var verifyCode = document.getElementById("verifyCode").value;
	var msg = document.getElementById("div_msg");
	if(userid=="" || pwd=="" || verifyCode==""){
		msg.innerText = "用户名密码验证码不能为空";
	}else{
		msg.innerText = " ";
		var data = "&user.UId="+userid+"&user.UPwd="+pwd+"&verifyCode="+verifyCode;
		$.ajax({
			type: "POST",
			url: "login.action",
			data: data,
			dataType: "json",
			success: function(json){
				if(json.success){
					window.location.href = "view/index2.jsp";
				}else{
					msg.innerText = json.msg;
				}
			}
			,error: function(json){
				alert("登录验证异常");
			}
		});
	}
}
function initApp() {
	try {
		window.parent.userOut(true);
	} catch(e){}
}
</script>
</head>
<body>
<div class="header">
  <div class="am-g">
    <h1>物流系统</h1>
  </div>
  <hr />
</div>
<div class="am-g">
  <div class="am-u-lg-2 am-u-md-8 am-u-sm-centered">
    <h3>系统登录</h3>
    <hr>
 
    <div class="am-form">
      <label for="email">用户名:</label>
      <input type="email" name="" id="email" value="">
      <br>
      <label for="password">密码:</label>
      <input type="password" name="" id="password" value="">
      <br>
      <br />
      <div class="am-cf">
        <input type="button" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl" onclick="document.location.href='index.jsp'">
        <input type="button" name="" value="清空" class="am-btn am-btn-default am-btn-sm am-fr">
      </div>
    </div>
    
    
    <!-- <ul class="am-nav am-nav-pills">
    <li class="am-active"><a href="#">首页</a></li>
    <li><a href="#">项目</a></li>
    <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
            菜单 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
            <li class="am-dropdown-header">Header</li>
            <li><a href="#">1. 一行代码，简单快捷</a></li>
            <li class="am-active"><a href="#">2. 网址不变且唯一</a></li>
            <li><a href="#">3. 内容实时同步更新</a></li>
            <li class="am-disabled"><a href="#">4. 云端跨平台适配</a></li>
            <li class="am-divider"></li>
            <li><a href="#">5. 专属的一键拨叫</a></li>
          </ul>
    </li>
</ul> -->
  </div>
</div>
</body>
</html>
