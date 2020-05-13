<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>创美家园——为草根创业者而生</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <script type="text/javascript" src="${ctx }/static/js/jquery-1.11.1.min.js"></script>
  <link rel="alternate icon" type="image/png" href="${ctx }/static/assets/i/favicon.png">
  <link rel="stylesheet" href="${ctx }/static/assets/css/amazeui.min.css"/>
  <script type="text/javascript" src="${ctx }/static/js/commonsFG.js"></script>
  <script type="text/javascript" src="${ctx }/static/assets/js/amazeui.js"></script>
  <script type="text/javascript">
  function addfavorite()
  {
	  var url = window.location;
	    var title = document.title;
	    var ua = navigator.userAgent.toLowerCase();
	    if (ua.indexOf("360se") > -1) {
	        alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
	    }
	    else if (ua.indexOf("msie 8") > -1) {
	        window.external.AddToFavoritesBar(url, title); //IE8
	    }
	    else if (document.all) {
	  try{
	   window.external.addFavorite(url, title);
	  }catch(e){
	   alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
	  }
	    }
	    else if (window.sidebar) {
	    	alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
	    }
	    else {
	  alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
	    }
  } 
  
  function downloadApp(){
		$("#download_app").modal();
	}
  </script>
</head>
<body>

<div class="am-margin" style="text-align: center;position:relative;">

<div class="am-fl" style="position:absolute; right:0; top:0; margin-right: 50px;font-weight: bold;">
<a href="${ctx }/toSigin">免费注册</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
<a target="_blank" href="${ctx }/gywm">关于我们</a>
<br />
<a href="${ctx }/toLogin">股东登陆</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
<a target="_blank" href="${ctx }/app_download">app下载</a>
<%-- <a href="${ctx }/toLogin">客服登陆</a> --%>
<br />
<a href="javascript:addfavorite()">加入收藏</a>&nbsp;&nbsp;
<a href="#">QQ群3335676</a>
</div>

<img alt="" src="http://7xj8ka.com2.z0.glb.qiniucdn.com/i-top.jpg" />

<div data-am-widget="slider" class="am-slider am-slider-default" data-am-slider='{}'>
  <ul class="am-slides">
    <li>
      <img src="http://7xj8ka.com2.z0.glb.qiniucdn.com/s-02.jpg">
    </li>
    <li>
      <img src="http://7xj8ka.com2.z0.glb.qiniucdn.com/s-03.jpg">
    </li>
    <li>
      <img src="http://7xj8ka.com2.z0.glb.qiniucdn.com/s-01.jpg">
    </li>
  </ul>
</div>

</div>


<div class="am-modal am-modal-alert" style="width: 360px;" tabindex="-1" id="download_app">
      <img  src="http://7xj8ka.com2.z0.glb.qiniucdn.com/download_app.jpg">
</div>
</body>
</html>