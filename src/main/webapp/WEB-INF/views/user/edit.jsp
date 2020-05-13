<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
</head>
<body>

<div class="am-margin">
	
	<ol class="am-breadcrumb">
	  <li><a href="${ctx }/views/toUserPage">用户管理</a></li>
	  <li class="am-active">编辑</li>
	</ol>
	
 <form id="userForm" class="am-form"  onsubmit="return false">
 <input type="hidden" id="id" name="id" value="${editData.id }">
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              用户名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="uname" name="uname" value="${editData.uname}"
           placeholder="请输入用户名" type="text"
            class="am-input-sm  validate[required]" 
            data-errormessage-value-missing="请填写用户名" />
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              密码：
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="upass" name="upass" value=""
           placeholder="除新增用户外，如果不修改密码不需要填写" type="text"
            class="am-input-sm " />
        </div>
     </div>
     
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              昵称
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="nikename" name="nikename" value="${editData.nikename}"
           placeholder="请输入昵称" type="text"/>
        </div>
     </div>
     
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              性别
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
        <c:if test="${editData.sex == '男'}">
        	<label class="am-radio">
		      <input type="radio" name="sex" value="男" data-am-ucheck checked>
		      男
		    </label>
		    <label class="am-radio">
		      <input type="radio" name="sex" value="女" data-am-ucheck>
		      女
		    </label>
        </c:if>
         <c:if test="${editData.sex == '女'}">
        	<label class="am-radio">
		      <input type="radio" name="sex" value="男" data-am-ucheck >
		      男
		    </label>
		    <label class="am-radio">
		      <input type="radio" name="sex" value="女" data-am-ucheck checked>
		      女
		    </label>
        </c:if>
         <c:if test="${editData.sex == '' || editData.sex == null }">
        	<label class="am-radio">
		      <input type="radio" name="sex" value="男" data-am-ucheck >
		      男
		    </label>
		    <label class="am-radio">
		      <input type="radio" name="sex" value="女" data-am-ucheck>
		      女
		    </label>
        </c:if>
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="tel" name="tel" value="${editData.tel}"
           placeholder="请输入昵称" type="text"/>
        </div>
     </div>
     
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              角色
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
        <select id="query_role" name="roletype" >
		        <option value="">请选择</option>
		        <c:forEach items="${rList }" var="r">
		        <c:if test="${r.id == editData.roletype}">
		        	<option value="${r.id}" selected>${r.name}</option>
		        </c:if>
		        <c:if test="${r.id != editData.roletype}">
		        	<option value="${r.id}" >${r.name}</option>
		        </c:if>
		        </c:forEach>
		      </select>
        </div>
     </div>
		<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
		<button type="button" onclick="resetForm()"  class="am-btn am-btn-primary am-btn-xs">重置</button>
</form>
		</div>
		
		
		
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
<script type="text/plain" id="upload_uqe"></script>
<script type="text/javascript">
//将表单提交及其表单项的验证绑定到jquery validation engine
$("#userForm").validationEngine("attach", {
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
			var path = "${ctx}/user/save";
			var param = $('#userForm').serialize();
			$.post(path, param, function(data) {
				if(data.success){
					showMsg(data.msg,function(){
						window.location.href = ctx+"/views/toUserPage";
					})
				}else{
					showMsg(data.msg)
				}
			});
		}
	}
});

function resetForm(){
	document.getElementById("userForm").reset();
}
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