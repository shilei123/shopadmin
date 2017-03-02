<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>SQ物流平台</title>
  <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <jsp:include page="/include/common.jsp"></jsp:include>
  <link rel="icon" type="image/png" href="${basePath }/AmazeUI-2.7.2/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${basePath }/AmazeUI-2.7.2/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${basePath }/AmazeUI-2.7.2/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="${basePath }/AmazeUI-2.7.2/assets/css/admin.css">
  <style type="text/css">
	.am-tabs-nav li {
		position: relative;
		z-index: 1;
	}

  	.am-tabs-nav .am-icon-close {
	    position: absolute;
	    top: 0;
	    right: 10px;
	    color: #888;
	    cursor: pointer;
	    z-index: 100;
  	}

  	.am-tabs-nav .am-icon-close:hover {
    	color: #333;
  	}

  	.am-tabs-nav .am-icon-close ~ a {
    	padding-right: 25px!important;
  	}
</style>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">
  <div class="am-topbar-brand">
    <strong>SQ</strong> <small>物流平台</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list" id="all-menu">
        <li class="am-panel"><a href="index.jsp"><span class="am-icon-home"></span> 首页</a></li>
        <!-- <li class="admin-parent"> -->
        <li class="am-panel">
          <a class="am-cf" data-am-collapse="{parent: '#all-menu',target: '#collapse-nav'}"><span class="am-icon-file"></span> 页面模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub" id="collapse-nav">
            <li><a href="admin-user.html" class="am-cf"><span class="am-icon-check"></span> 个人资料<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li><a href="admin-help.html"><span class="am-icon-puzzle-piece"></span> 帮助页</a></li>
            <li><a href="admin-gallery.html"><span class="am-icon-th"></span> 相册页面<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
            <li><a href="admin-log.html"><span class="am-icon-calendar"></span> 系统日志</a></li>
            <li><a href="admin-404.html"><span class="am-icon-bug"></span> 404</a></li>
          </ul>
        </li>
        <li class="am-panel">
        	<a class="am-cf" data-am-collapse="{parent: '#all-menu',target: '#table-nav'}"><span class="am-icon-table"></span> 表格 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        	<ul class="am-list am-collapse admin-sidebar-sub" id="table-nav"><!-- 要想默认就打开，加入am-in -->
		        <li><a href="javascript:addMenu('tjry','添加人员');"><i class="am-icon-user am-margin-left-sm"></i> 添加人员 </a></li>
		        <li><a href="javascript:addMenu('rylb','人员列表')"><i class="am-icon-user am-margin-left-sm"></i> 人员列表 </a></li>
		    </ul>
        </li>
        <li class="am-panel"><a href="admin-form.html"><span class="am-icon-pencil-square-o"></span> 表单</a></li>
        <li class="am-panel"><a href="#"><span class="am-icon-sign-out"></span> 注销</a></li>
      </ul>

      <!-- <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-bookmark"></span> 公告</p>
          <p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
        </div>
      </div> -->

      <!-- <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-tag"></span> wiki</p>
          <p>Welcome to the Amaze UI wiki!</p>
        </div>
      </div> -->
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <!-- <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>一些常用模块</small></div>
      </div> -->
	  <div class="am-tabs" data-am-tabs id="doc-tab-demo-1">
		  <ul class="am-tabs-nav am-nav am-nav-tabs">
		    <li class="am-active"><a href="#tab1">欢迎</a></li>
		  </ul>
		
		  <div class="am-tabs-bd am-tabs-bd-ofv">
		    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
		     	 置身人群中<br>你只需要被淹没 享受 沉默<br>退到人群后<br>你只需给予双手 微笑 等候
		    </div>
		  </div>
		</div>
		<br />
		<button type="button" class="am-btn am-btn-primary js-append-tab">插入 Tab</button>
		<button type="button" onclick="addMenu('aaaaa','人员管理')">人员管理</button>
		<button type="button"  onclick="addMenu('bbbbb','机构管理')">机构管理</button>
		
		<!-- <ul class="am-list admin-sidebar-list" id="collapase-nav-1">
		  <li  class="am-panel">
		    <a data-am-collapse="{parent: '#collapase-nav-1'}" href="#/"><i class="am-icon-home am-margin-left-sm"></i> 首页</a>
		  </li>
		
		  <li class="am-panel">
		    <a data-am-collapse="{parent: '#collapase-nav-1', target: '#user-nav'}">
		        <i class="am-icon-users am-margin-left-sm"></i> 人员管理 <i class="am-icon-angle-right am-fr am-margin-right"></i>
		    </a>
		    <ul class="am-list am-collapse admin-sidebar-sub" id="user-nav">
		        <li><a href="#/userAdd"><i class="am-icon-user am-margin-left-sm"></i> 添加人员 </a></li>
		        <li><a href="#/userList/0"><i class="am-icon-user am-margin-left-sm"></i> 人员列表 </a></li>
		    </ul>
		  </li>
		
		  <li class="am-panel">
		    <a data-am-collapse="{parent: '#collapase-nav-1', target: '#company-nav'}">
		        <i class="am-icon-table am-margin-left-sm"></i> 单位（部门）管理 <i class="am-icon-angle-right am-fr am-margin-right"></i>
		    </a>
		    <ul class="am-list am-collapse admin-sidebar-sub" id="company-nav">
		        <li><a href="#/companyAdd"><span class="am-icon-table am-margin-left-sm"></span> 添加单位（部门） </a></li>
		        <li><a href="#/companyList/0"><span class="am-icon-table am-margin-left-sm"></span> 单位（部门）列表 </a></li>
		    </ul>
		  </li>
		
		  <li class="am-panel">
		    <a data-am-collapse="{parent: '#collapase-nav-1', target: '#role-nav'}">
		        <i class="am-icon-table am-margin-left-sm"></i> 角色管理 <i class="am-icon-angle-right am-fr am-margin-right"></i>
		    </a>
		    <ul class="am-list am-collapse admin-sidebar-sub" id="role-nav">
		        <li><a href="#/roleAdd"><span class="am-icon-table am-margin-left-sm"></span> 添加角色 </a></li>
		        <li><a href="#/roleList/0"><span class="am-icon-table am-margin-left-sm"></span> 角色列表 </a></li>
		    </ul>
		  </li>
		</ul> -->
    </div>

    <footer class="admin-content-footer">
      <hr>
      <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
    </footer>
  </div>
  <!-- content end -->

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<!-- <script src="../js/jquery-1.8.0.min.js"></script> -->
<!--<![endif]-->
<script src="${basePath }/AmazeUI-2.7.2/assets/js/amazeui.min.js"></script>
<script src="${basePath }/AmazeUI-2.7.2/assets/js/app.js"></script>
<script>
	var tabCounter = 2;
    var $tab = $('#doc-tab-demo-1');
    var $nav = $tab.find('.am-tabs-nav');
    var $bd = $tab.find('.am-tabs-bd');
    
  	$(function() {
		$tab.tabs({noSwipe: 1}); //禁用触控
		/*$tab.find('a').on('opened.tabs.amui', function(e) {
		  console.log('[%s] 选项卡打开了', $(this).text());
		});*/
	});
	
	// 动态添加标签页（测试）
	$('.js-append-tab').on('click', function() {
      addTab("menuId"+tabCounter,"测试tab"+tabCounter);
      //选中组后一个
      var index = $nav.children('li').length;
      $tab.tabs('open', index > 0 ? index - 1 : index + 1);
    });
  	
  	//移除标签页
    $nav.on('click', '.am-icon-close', function() {
      var $item = $(this).closest('li');
      var index = $nav.children('li').index($item);
      
      $item.remove();
      $bd.find('.am-tab-panel').eq(index).remove();

      $tab.tabs('open', index > 0 ? index - 1 : index + 1);
      $tab.tabs('refresh');
    });
    
    //选中最后一个tab
    var  selectLastTab = function() {
    	var index = $nav.children('li').length;
		$tab.tabs('open', index > 0 ? index - 1 : index + 1);
    }
	
	function addTab(menuId,menuName) {
		var nav = '<li><span class="am-icon-close" id="'+menuId+'"></span>' +
        	'<a href="#tab'+tabCounter+'"> ' + menuName + '</a></li>';
      	var content = '<div class="am-tab-panel" id="tab'+tabCounter+'">' + menuName + '<iframe src="index.jsp"/></div>';

      	$nav.append(nav);
      	$bd.append(content);
      	tabCounter++;
      	$tab.tabs('refresh');
    }
    
  	function addMenu(menuId,menuName) {
  		var $menu = $("#"+menuId);
  		if($menu.length==0) {
  			addTab(menuId,menuName);
	      	selectLastTab();
  			return;
  		}
	    var $item = $menu.closest('li');
	  	var index = $nav.children('li').index($item);
	  	if(index < 0) {
			addTab(menuId,menuName);
	      	selectLastTab();
	  	} else {
	  		$tab.tabs('open', index);
	  		$tab.tabs('refresh');
	  	}
	}
</script>
</body>
</html>