<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>500</title>
  <meta name="description" content="这是一个500页面">
  <meta name="keywords" content="500">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
 <!--  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" /> -->
  <link rel="stylesheet" href="${path }/AmazeUI-2.7.2/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="${path }/AmazeUI-2.7.2/assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<div class="am-cf ">
  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">500</strong> / <small>That’s an error</small></div>
      </div>
      <hr>
      <div class="am-g">
        <div class="am-u-sm-12">
          <h2 class="am-text-center am-text-xxxl am-margin-top-lg">500. Not Found</h2>
          <p class="am-text-center">没有找到你要的页面</p>
        <pre class="page-500">
          .----.
       _.'__    `.
   .--($)($$)---/#\
 .' @          /###\
 :         ,   #####
  `-..__.-' _.-\###/
        `;_:    `"'
      .'"""""`.
     /,  ya ,\\
    //  500!  \\
    `-._______.-'
    ___`. | .'___
   (______|______)
        </pre>
        </div>
      </div>
    </div>
  </div>
  <!-- content end -->
</div>
<script src="${path }//js/jquery-1.8.0.min.js"></script>
<script src="${path }/AmazeUI-2.7.2/assets/js/amazeui.min.js"></script>
<script src="${path }/AmazeUI-2.7.2/assets/js/app.js"></script>
</body>
</html>
