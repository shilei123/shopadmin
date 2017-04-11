<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
  <jsp:include page="/include/common.jsp"></jsp:include>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>${title }</title>
  <meta name="description" content="SQ">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
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
  	.am-tabs-bd .am-tab-panel {
  		padding: 0px 0px 0px;
  	}
  	body {
  		font-size: 14px;
  	}
</style>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，暂不支持。 请升级浏览器 以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header" id="frameheader">
  <div class="am-topbar-brand">
    <strong>${title }</strong><!--  <small>后台管理</small> -->
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li>
      	<a href="javascript:;"> 
      		<span class="am-icon-calendar-check-o"></span> <%=framework.util.DateUtils.toDate(framework.util.DateUtils.getDate()) %> <%=framework.util.DateUtils.getCurrWeekDay() %> 
      	</a>
      </li>
      <!-- <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li> -->
      <li><a href="javascript:;"><span class="am-icon-shopping-bag"></span> 商城首页</a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> ${user.UName}<span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <%--<li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 修改密码</a></li>--%>
          <li><a href="javascript:void(0);" onclick="adminLoginOut();return false;"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <%--<li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>--%>
	</ul>
  </div>
</header>

<div class="am-cf admin-main" id="admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list" id="all-menu">
        <%--<li class="am-panel"><a href="index.jsp"><span class="am-icon-home"></span>首页</a></li>--%>
        <!-- <li class="admin-parent"> -->
        <%-- <li class="am-panel">
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
        <li class="am-panel"><a href="admin-form.html"><span class="am-icon-pencil-square-o"></span> 表单</a></li>--%>
        <!-- <li class="am-panel"><a href="#"><span class="am-icon-sign-out"></span> 注销</a></li> -->
      </ul>

    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <!-- <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>一些常用模块</small></div>
      </div> -->
	  <div class="am-tabs" data-am-tabs id="doc-tab-demo-1" style="margin-left:0xp;">
		  <ul class="am-tabs-nav am-nav am-nav-tabs">
		    <li class="am-active"><a href="#tab1">欢迎</a></li>
		  </ul>
		
		  <div class="am-tabs-bd am-tabs-bd-ofv" style="margin-left:0xp;">
		    <div class="am-tab-panel am-fade am-in am-active" style="margin-left:0xp;" id="tab1">
		    	<!-- <div style="margin-left: 10px;margin-top: 10px;">
			    	<p>开发框架：Spring3 + Hibernate3 + Struts2 + JQuery(jquery validate) + easyui1.4 + AmazeUI2.7.2 + layui(laypage-v1.3)</p>
			    	<p>开发环境：Tomcat7 + Jdk1.7 </p>
			    	<p>数据库：Oracle11g r2</p>
			    	<p>数据源：阿里巴巴 Druid </p>
		    	 </div> -->
		    	 <img src="home.png"/>
		    </div>
		  </div>
		</div>
    </div>
  </div>
  <!-- content end -->
</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<footer id="footer">
  <hr>
  <div style="text-align: center;"><%=application.getInitParameter("copyright") %> </div>
  <hr>
</footer>

<!-- 提示框 -->
<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示！</div>
    <div class="am-modal-bd" id="alertMsg">
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>

<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示！</div>
    <div class="am-modal-bd" id="confirmMsg">
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
    </div>
  </div>
</div>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<!-- <script src="../js/jquery-1.8.0.min.js"></script> -->
<!--<![endif]-->
<script src="${basePath }/AmazeUI-2.7.2/assets/js/amazeui.min.js"></script>
<%-- <script src="${basePath }/AmazeUI-2.7.2/assets/js/app.js"></script> --%>
<script>
	var tabCounter = 2;
    var $tab = $('#doc-tab-demo-1');
    var $nav = $tab.find('.am-tabs-nav');
    var $bd = $tab.find('.am-tabs-bd');
    	
    var contentHeight = 500;
  	$(function() {
		$tab.tabs({noSwipe: 1}); //禁用触控
		/*$tab.find('a').on('opened.tabs.amui', function(e) {
		  console.log('[%s] 选项卡打开了', $(this).text());
		});*/
		menuInit();
		resizeHeight();
	 	$(window).on('resize',function(){
			resizeHeight();
		});
	});
	
	var resizeHeight = function() {
		var $menu = $('#admin-main');
   		var $foot = $('#footer');
   		var $frameheader = $('#frameheader');
		
		var height = getTotalHeight();
		//var width = getTotalWidth();
		var headheight = $frameheader.height();
		//var menuHeight = $menu.height();
		var footHeight = $foot.height();
		var navHeight = $nav.height();
		//contentHeight = menuHeight-footHeight-navHeight-10;
		contentHeight = height-headheight-footHeight-navHeight-2;
		//alert(contentHeight);
		$bd.height(contentHeight); 
		$("#tab1").height(contentHeight);  //欢迎页的高度
		//$menu.height(menuHeight-footHeight);
		$menu.height(height-headheight-footHeight);
		//setTimeout(resizeHeight, 500);
	};
	
	var getTotalHeight = function() {
		if($.browser.msie) {
			return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;
		} else {
			return self.innerHeight;
		}
	};
	
	var getTotalWidth = function() {
		if($.browser.msie) {
			return document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth;
		} else {
			return self.innerWidth;
		}
	};
  	
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
    var selectLastTab = function() {
    	var index = $nav.children('li').length;
		$tab.tabs('open', index > 0 ? index - 1 : index + 1);
    };
	
  	//关闭tab
    var closeTab = function(menuId) {
    	var $menu = $("#"+menuId);
    	var $item = $menu.closest('li');
		var index = $nav.children('li').index($item);
      
		$item.remove();
		$bd.find('.am-tab-panel').eq(index).remove();

		$tab.tabs('open', index > 0 ? index - 1 : index + 1);
		$tab.tabs('refresh');
		//selectLastTab();
    };
    
	var addTab = function(menuId,menuName,menuUrl) {
		var nav = '<li><span class="am-icon-close" id="'+menuId+'"></span>' +
        	'<a href="#tab'+tabCounter+'"> ' + menuName + '</a></li>';
      	var content = '<div class="am-tab-panel" style="margin-left:0xp;" id="tab'+tabCounter+'"><iframe src="'+menuUrl+'" scrolling="o" frameborder="" allowfullscreen mozallowfullscreen webkitallowfullscreen style="width:100%; height:'+(contentHeight-5)+'px;overflow: hidden;"/></div>';

      	$nav.append(nav);
      	$bd.append(content);
      	tabCounter++;
      	$tab.tabs('refresh');
    };
    
  	var addMenu = function(menuId,menuName,menuUrl) {
  		menuUrl += menuUrl.indexOf("?")>0?"&tabId="+menuId:"?tabId="+menuId;
  		
  		var $menu = $("#"+menuId);
  		if($menu.length==0) {
  			addTab(menuId,menuName,menuUrl);
	      	selectLastTab();
  			return;
  		}
	    var $item = $menu.closest('li');
	  	var index = $nav.children('li').index($item);
	  	if(index < 0) {
			addTab(menuId,menuName,menuUrl);
	      	selectLastTab();
	  	} else {
	  		$tab.tabs('open', index);
	  		$tab.tabs('refresh');
	  	}
	};
  	
  	//初始化菜单
  	var menuInit = function() {
		$.ajax({type: "POST",url: "${path }/view/menuTree.action",dataType: "json",error:function(){/* alert("加载菜单异常"); */},
			success: function(json){
				var root = json.trees[0];
		  		var html = buildMenus(root.children, false);
		  		$('#all-menu').append(html);
			}
		});
	};
  	
  	var buildMenus = function(list,addUL) {
		addUL = addUL==null?true:addUL;
		var html = '';
		for(var i=0;i<list.length;i++){
			html += buildMenu(list[i],i);
		}
		return html;
	};
  	
  	var menuMap = {};
	var buildMenu = function(data,i) {
       	var html = '<li class="am-panel">';
        html += '<a class="am-cf" data-am-collapse="{parent: \'#all-menu\',target: \'#nav'+i+'\'}"><span class="am-icon-th-list"></span>&nbsp;'+data.text+' <span class="am-icon-angle-right am-fr am-margin-right"></span></a>';
       	
        if(data.children!=null && data.children.length>0){
	        html += '<ul class="am-list am-collapse admin-sidebar-sub" id="nav'+i+'">';
        	for(var j=0;j<data.children.length;j++){
				var childmenu = data.children[j];
				if(childmenu.attributes.openMethod!=null && childmenu.attributes.openMethod=='open') {
					html += '<li><a href="javascript:window.open(\'${basePath }'+childmenu.attributes.url+'\');"><span class="am-icon-chevron-right"></span>&nbsp;&nbsp;'+childmenu.text+'</a></li>';
				} else {
					html += '<li><a href="javascript:addMenu(\''+childmenu.id+'\',\''+childmenu.text+'\',\'${basePath }'+childmenu.attributes.url+'\');"><span class="am-icon-chevron-right"></span>&nbsp;&nbsp;'+childmenu.text+'</a></li>';
				}
			}
        	html += '</ul>';
		}
        
        html += '</li>';
        
		menuMap[data.id] = data;
		return html;
	};
	
    var adminLoginOut = function() {
    	showConfirm("确定退出？？",function() {
   			$.ajax({
				type: "POST",
   			  	url: "${path }/userOut.action",
   			  	dataType: "text",
   			  	success: function(json){
   			  		window.location = "${path }/login.jsp";
   			  	}
   			  	,error: function(a){/*alert("退出异常");*/}
			});
		});
	};
    
    var showMsg_ = function(msg) {
    	layer.alert(msg,{icon: 1,shade: 0.1});
    };
    
    var showAlert_ = function(msg) {
    	layer.alert(msg,{icon: 7,shade: 0.1});
    	/* $("#alertMsg").text(msg);
    	$('#my-alert').modal("open"); */
    };
    
    var showConfirm_ = function(msg,callbackfun) {
    	//询问框
    	layer.confirm(msg, {
    		icon: 2,btn: ['确认','取消'] //按钮
    	}, function(){
    		callbackfun();
    	}, function(){
    	 
    	});
    	/* $("#confirmMsg").text(msg);
    	var $confirm = $('#my-confirm');
        var confirm = $confirm.data('amui.modal');
        if (confirm) {
    		confirm.options.onConfirm =  callbackfun;
    		confirm.options.onCancel =  function() {};
    		confirm.toggle(this);
        } else {
        	$('#my-confirm').modal({
    			relatedTarget: this,
    			onConfirm: callbackfun,
    			onCancel: function() {}
    		});
        }  */
    };
</script>
</body>
</html>
