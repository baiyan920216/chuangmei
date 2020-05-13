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
<meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="${ctx }/static/assets/css/amazeui.min.css">
  <link rel="stylesheet" href="${ctx }/static/assets/css/app.css">
  
  <script type="text/javascript" src="${ctx }/static/js/commonsFG.js"></script>
<%--   <script type="text/javascript" src="${ctx }/static/js/jquery.js"></script> --%>
<script src="${ctx}/static/js/jQuery/jQuery-2.1.4.min.js"></script>
<script src="${ctx}/static/js/jQuery/jquery.serializejson.js"></script>
  
  <link rel="stylesheet" href="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/css/validationEngine.jquery.css"/>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-jacy-1.01.js"></script>
  
<script type="text/javascript">
	var ctx = '${ctx}';
</script>
<sitemesh:write property='head' />
</head>
<body>
<!-- Header -->
<header data-am-widget="header" class="am-header am-header-default ">
  <h1 class="am-header-title">
    <a href="#title-link">创美家园
    </a>
  </h1>
</header>

<!-- Menu -->
<nav data-am-widget="menu" class="am-menu  am-menu-offcanvas1" data-am-menu-offcanvas>
  <a href="javascript: void(0)" class="am-menu-toggle">
    <i class="am-menu-toggle-icon am-icon-bars"></i>
  </a>
  <div class="am-offcanvas">
    <div class="am-offcanvas-bar">
      <ul class="am-menu-nav sm-block-grid-1">
      <li class="">
          <a href="${ctx}/member/index">首页</a>
        </li>
        <li class="">
          <a href="${ctx}/member/toReserve">预约创造</a>
        </li>
        <li class="">
          <a href="${ctx}/member/toUserOrderHispage">造梦记录</a>
        </li>
        <li class="">
          <a href="${ctx}/member/tofukuanpage">造梦阶段</a>
        </li>
        <li class="">
          <a href="${ctx}/member/toshoukuanpage">圆梦阶段</a>
        </li>
        <li class="">
          <a href="${ctx}/member/toorderpayHispage"> 圆梦记录</a>
        </li>
       <li class="">
          <a href="${ctx}/member/toManagementAwartpage">  管理奖项</a>
        </li>
        <li class="">
          <a href="${ctx}/member/toGivenCoinpage">创美币转赠</a>
        </li>
        <li class="">
          <a href="${ctx}/member/coinHis">创美币记录</a>
        </li>
         <li class="">
          <a href="${ctx}/member/userInfo">个人信息</a>
        </li>
         <li class="">
          <a href="${ctx}/member/toUpdateUPass">修改密码</a>
        </li>
        <li class="">
          <a href="${ctx}/logout">退出登录</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="am-modal am-modal-alert" tabindex="-1" id="my-msg">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示</div>
    <div class="am-modal-bd" id="alert-msg">
      
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
    </div>
  </div>
</div>

<sitemesh:write property='body' />
<script src="${ctx }/static/assets/js/amazeui.min.js"></script>
<script type="text/javascript">

function showMsg(msg,callback){
	$("#alert-msg").html(msg);
	$("#my-msg").modal({
			relatedTarget: this,
	        onConfirm: function(options) {
	        	if(undefined!=callback){
	        		callback();
	        	}
	        }
	});
}
</script>
</body>
</html>
