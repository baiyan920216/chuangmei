<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>创业网注册</title>
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
<div class="am-g">
  <div class="am-margin  am-u-lg-4 am-u-md-7 am-u-sm-centered">
<br>
    <form id="siginForm" method="post" class="am-form"  onsubmit="return false">
    <input type="hidden" name="id" value="${editData.id }">
      <input placeholder="密码：" class="validate[required]" data-errormessage-value-missing="请输入密码" type="password" name="upass" id="upass" value="">
      <br>
      <input placeholder="确认密码：" class="validate[required,equals[upass]] "  data-errormessage-value-missing="请确认密码" data-errormessage-pattern-mismatch="两次密码不一致" type="password" name="eupass" id="eupass" value="">
      <br>
      <input placeholder="二级密码：" class="validate[required]" data-errormessage-value-missing="请输入二级密码" type="password" name="spass" id="spass" value="">
      <br>
      <input placeholder="确认二级密码：" class="validate[required,equals[spass]] "  data-errormessage-value-missing="请确认密码" data-errormessage-pattern-mismatch="两次密码不一致" type="password" name="espass" id="espass" value="">
      <br>
      <div class="am-cf">
        <input type="submit"  value="修改密码" class="am-btn am-btn-primary am-btn-sm ">
      </div>
    </form>
  </div>
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
$("#siginForm").validationEngine("attach", {
	promptPosition : "topRight:-100,0",// 提示信息位置：右上，偏移量：x:-100,y:0
	binded : false,// 非即时验证
	autoHidePrompt : true,
	autoHideDelay : 3000,
	showOneMessage : true,
	ajaxFormValidationMethod : 'post',
	maxErrorsPerField : 1,// 单个元素显示错误提示的最大数量，值设为数值。默认 false 表示不限制
	scroll : false, // 提示信息不滚屏
	onValidationComplete : function(form, status) {// 表单提交验证完成时的回调函数,即使验证都通过也不提交表单
		if (status) {// 表单验证通过
			var path = "${ctx}/user/updateUPass";
			var param = $('#siginForm').serialize();
			$.post(path, param, function(data) {
				if(data.success){
					showMsg(data.msg,function(){
					});
				}else{
					showMsg(data.msg);
				}
			});
		}
	}
});

// function sigin(){
// 	var path = "${ctx}/sigin";
// 	var params = $('#siginForm').serialize();
// 	commonAjax(path,params,function(data){
// 		if(data.success){
// 			showMsg(data.msg)
// 		}else{
// 			showMsg(data.msg)
// 		}
// 	});
// }

function showMsg(msg){
	$("#alert-msg").html(msg);
	$("#my-msg").modal();
}
function goHome(){
	window.location.href="${ctx}/toLogin";
}
</script>
</body>
</html>