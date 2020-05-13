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
	  <li>卖方信息</li>
	</ol>
	
 <form id="sellerForm" class="am-form"  onsubmit="return false">
 <input type="hidden" id="id" name="id" value="${editData.id }">
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              卖方编号
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="sellernum" name="sellernum" value="${editData.sellernum}"
           placeholder=" 卖方编号" type="text"
            class="am-input-sm  validate[required]" 
            data-errormessage-value-missing="请填写 卖方编号">
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              卖方姓名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="sellername" name="sellername" value="${editData.sellername}" placeholder="卖方姓名" type="text" class="am-input-sm  validate[required]"
          data-errormessage-value-missing="请填写卖方姓名" >
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              卖方电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="sellertel" name="sellertel" value="${editData.sellertel}" placeholder="卖方电话" type="text" class="am-input-sm  validate[required]"
          data-errormessage-value-missing="请填写卖方电话" >
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              卖方支付宝帐号
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="selleralipay" name="selleralipay" value="${editData.selleralipay}" placeholder="卖方支付宝帐号" type="text" class="am-input-sm  validate[required]"
          data-errormessage-value-missing="请填写卖方支付宝帐号" >
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              卖方推荐人
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="sellersupportname" name="sellersupportname" value="${editData.sellersupportname}" placeholder="卖方推荐人" type="text" class="am-input-sm  validate[required]"
          data-errormessage-value-missing="请填写卖方推荐人" >
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              卖方推荐人电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="sellersuporttel" name="sellersuporttel" value="${editData.sellersuporttel}" placeholder="卖方推荐人电话" type="text" class="am-input-sm  validate[required]"
          data-errormessage-value-missing="请填写卖方推荐人电话" >
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
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>

<script type="text/javascript">
//将表单提交及其表单项的验证绑定到jquery validation engine
$("#sellerForm").validationEngine("attach", {
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
			var path = "${ctx}/seller/save";
			var param = $('#sellerForm').serialize();
			$.post(path, param, function(data) {
				if(data.success){
					showMsg(data.msg)
				}else{
					showMsg(data.msg)
				}
			});
		}
	}
});

function resetForm(){
	document.getElementById("contentForm").reset();
	ue.reset();
}
		
function showMsg(msg){
	$("#alert-msg").html(msg);
	$("#my-msg").modal();
}
</script>
</body>
</html>