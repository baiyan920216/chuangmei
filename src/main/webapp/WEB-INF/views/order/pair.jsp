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
	  <li><a href="${ctx }/views/toOrderPairPage">创单匹配</a></li>
	  <li class="am-active">匹配</li>
	</ol>
已匹配记录
<input type="hidden" id="id" name="id" value="${id }"> 
 <div class="am-list-news-bd">
		<table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-id">编号</th>
                <th class="table-title">打款金额</th>
                <th class="table-type">付款人用户名</th>
                <th class="table-type">付款人姓名</th>
                <th class="table-type">匹配时间</th>
                <th class="table-type">付款人支付宝</th>
                <th class="table-type">操作</th>
              </tr>
          </thead>
          <tbody id="pagelist1">
          </tbody>
        </table>
	</div>
</div>
<!--  <form id="contentForm" class="am-form"  onsubmit="return false"> -->
<%--  	<input type="hidden" id="id" name="id" value="${id }"> --%>
     
<!-- 	<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button> -->
<!--  </form> -->

 <div class="am-list-news-bd">
		<table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-id">编号</th>
                <th class="table-title">金额</th>
                <th class="table-type">返现时间</th>
                <th class="table-type">用户名</th>
                <th class="table-type">姓名</th>
                <th class="table-type">电话</th>
                <th class="table-type">支付宝</th>
                <th class="table-type">创单状态</th>
              </tr>
          </thead>
          <tbody id="pagelist">
          </tbody>
        </table>
	</div>
	<div class="am-list-news-bd" id="content_page">
	</div>
 

<!-- 付款方列表 -->
		

		
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
</div>
<script type="text/javascript" src="${ctx }/static/js/pair.js"></script>
<script type="text/javascript" src="${ctx }/static/layer/layer.js"></script>
<script type="text/javascript">
//将表单提交及其表单项的验证绑定到jquery validation engine
$("#contentForm").validationEngine("attach", {
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
			var path = "${ctx}/content/save";
			var param = $('#contentForm').serialize();
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

function showMsg(msg){
	$("#alert-msg").html(msg);
	$("#my-msg").modal();
}
</script>
</body>
</html>