<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>创美家园登陆</title>
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

  <link rel="stylesheet" href="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/css/validationEngine.jquery.css"/>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-jacy-1.01.js"></script>
</head>
<body>
<header data-am-widget="header" class="am-header am-header-default ">
  <h1 class="am-header-title">
    <a href="#title-link">创美家园-登录</a>
  </h1>
</header>
  <div class="am-margin  am-u-lg-4 am-u-md-7 am-u-sm-centered">
    <br>
    <form id="loginForm" method="post" class="am-form" onsubmit="return false">
      <input placeholder="用户名：" class="validate[required]" data-errormessage-value-missing="请输入用户名" type="text" name="uname" id="uname" value="">
      <br>
      <input placeholder="密码：" class="validate[required]" data-errormessage-value-missing="请输入密码" type="password" name="upass" id="upass" value="">
      <br>
      <div class="am-cf">
        <input type="submit"  value="登 录" class="am-btn am-btn-primary am-btn-block">
        <input type="button" onclick="window.location.href='${ctx }/toSigin'" value="注册" class="am-btn am-btn-primary am-btn-block">
        <input type="button" onclick="window.location.href='${ctx }/toForget'" value="忘记密码" class="am-btn am-btn-primary am-btn-block">
      </div>
    </form>

  </div>



<div class="am-modal am-modal-alert" tabindex="-1" id="my-msg">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示</div>
    <div class="am-modal-bd" id="alert-msg">

    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>
<script type="text/javascript">


//将表单提交及其表单项的验证绑定到jquery validation engine
$("#loginForm").validationEngine("attach", {
	promptPosition : "topRight:-100,0",// 提示信息位置：右上，偏移量：x:-100,y:0
	binded : false,// 非即时验证
	autoHidePrompt : true,
	autoHideDelay : 3000,
	showOneMessage : true,
	ajaxFormValidationMethod : 'post',
	maxErrorsPerField : 1,// 单个元素显示错误提示的最大数量，值设为数值。默认 false 表示不限制
	scroll : false, // 提示信息不滚屏
	onValidationComplete : function(form, status) {// 表单提交验证完成时的回调函数,即使验证都通过也不提交表单

// 		var startDate = new Date('2018.02.08');
// 	    var endDate = new Date('2018.03.03');
// 	    var nowDate = new Date();
// 	    if(nowDate>=startDate && nowDate <= endDate){
// 	    	showMsg("2018.02.08-2018.03.02为假期，系统将停止使用，请于2018.03.03后登录使用");
// 	    	return ;
// 	    }

		if (status) {// 表单验证通过
			var path = "${ctx}/login";
			var param = $('#loginForm').serialize();
			$.post(path, param, function(data) {
				if(data.success){
						window.location.href = "${ctx}"+data.data;
				}else{
					showMsg(data.msg);
				}
			});
		}
	}
});


// function login(){
// 	var path = "${ctx}/login";
// 	var params = $('#loginForm').serialize();
// 	commonAjax(path,params,function(data){
// 		if(data.success){
// 			window.location.href = "${ctx}"+data.data;
// 		}else{
// 			showMsg(data.msg)
// 		}
// 	});
// }

function showMsg(msg){
	$("#alert-msg").html(msg);
	$("#my-msg").modal();
}
</script>
</body>
</html>