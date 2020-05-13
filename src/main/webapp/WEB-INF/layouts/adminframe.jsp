<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>创美家园</title>
<meta name="description" content="创业网后台主页">
<meta name="keywords" content="index">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
	href="${ctx }/static/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${ctx }/static/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="创美家园" />
<link rel="stylesheet" href="${ctx }/static/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="${ctx }/static/assets/css/admin.css">

<script type="text/javascript" src="${ctx }/static/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/static/js/commonsFG.js"></script>

<link rel="stylesheet" href="${ctx}/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" >
<script src="${ctx}/static/zTree_v3/js/jquery.ztree.all-3.5.js"></script>



<script src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" charset="utf-8"
	src="${ctx}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${ctx}/static/ueditor/ueditor.all.js">
	
</script>

<link rel="stylesheet" href="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/css/validationEngine.jquery.css"/>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-jacy-1.01.js"></script>


<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="${ctx}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	var ctx = '${ctx}';
	function toggleCol(id){
		$('#'+id).collapse('toggle');
	}
</script>
<sitemesh:write property='head' />
</head>
<body>
	<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，创业网 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

	<header class="am-topbar admin-header">
	
		<div class="am-topbar-brand">
			<strong>创美家园</strong> <small>一起创业</small>
		</div>

		<div class="am-topbar-collapse" id="topbar-collapse">

			<ul
				class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
				<li class="am-dropdown" data-am-dropdown><a
					class="am-dropdown-toggle" data-am-dropdown-toggle
					href="javascript:;"> <span class="am-icon-users"></span> 管理员 <span
						class="am-icon-caret-down"></span>
				</a>
					<ul class="am-dropdown-content">
						<li><a href="javascript:logout()"><span class="am-icon-power-off"></span>
								退出</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</header>

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
			<div class="am-offcanvas-bar admin-offcanvas-bar">
			<ul  class="am-list admin-sidebar-list">
					<li><a href="${ctx }/index"><span class="am-icon-home"></span> 首页</a></li>
					<li><a href="${ctx }/views/toContentPage" class="am-cf"><span class="am-icon-file"></span> 信息管理</a></li>
					<li><a href="${ctx }/views/toRolePage"><span class="am-icon-file"></span> 角色管理</a></li>
<%-- 					<li><a href="${ctx }/views/toSellerPage"><span class="am-icon-file"></span> 卖方信息管理</a></li> --%>
					<li><a href="${ctx }/views/toClientUserPage"><span class="am-icon-file"></span> 会员信息管理</a></li>
					<li><a href="${ctx }/views/toOrderManagePage"><span class="am-icon-file"></span> 会员预约信息</a></li>
					<li><a href="${ctx }/views/toOrderPairPage"><span class="am-icon-file"></span> 创单匹配</a></li>
					<li><a href="${ctx }/views/toUserTreePage"><span class="am-icon-file"></span> 会员架构</a></li>
				</ul>
				
			  <div class="am-panel am-panel-default admin-sidebar-panel">
		        <div class="am-panel-bd">
		          <p><span class="am-icon-bookmark"></span> 公告</p>
		          <p>时光静好，与君语；细水流年，与君同。</p>
		        </div>
		      </div>
			</div>
		</div>
		<!-- sidebar end -->

		<!-- content start -->
		<div class="admin-content">
		<sitemesh:write property='body' />
		</div>
		<!-- content end -->

	</div>

	<a class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
		data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

	<footer>
		<hr>
		<p class="am-padding-left">© 创美家园.</p>
	</footer>

	<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="${ctx }/static/assets/js/polyfill/rem.min.js"></script>
<script src="${ctx }/static/assets/js/polyfill/respond.min.js"></script>
<script src="${ctx }/static/assets/js/amazeui.legacy.js"></script>
<![endif]-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="${ctx }/static/assets/js/jquery.min.js"></script>
	<script src="${ctx }/static/assets/js/amazeui.min.js"></script>
	<!--<![endif]-->
	<script src="${ctx }/static/assets/js/app.js"></script>
	<script type="text/javascript">
		function logout(){
			window.location.href = ctx+"/logout";
		}
		
		function createMenu(){
			var roletype = '${sessionScope.web_logon_user.roletype}';
			if(roletype<=1){
				return ;
			}
			var path = ctx+"/role/menuList";
			var param = {};
			commonAjax(path,param,function(data){
				if(data.success){
					var pageStr = new Array; 
					$.each(data.data,function(index,row){
						pageStr.push('<li><a href="'+ctx+row.uri+'" class="am-cf">'+row.name+'</a></li>');
					});
					
					$("#client_menu").html(pageStr.join(""));
				}else{
					showMsg(data.msg);
				}
			});
		}
		createMenu();
	</script>
</body>
</html>
