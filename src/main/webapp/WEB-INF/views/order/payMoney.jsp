<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<link rel="icon" type="image/png" href="assets/i/favicon.png">
  
  
  <link rel="stylesheet" href="${ctx }/static/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="${ctx }/static/assets/css/admin.css">
  <script type="text/javascript" src="${ctx }/static/js/jquery.js"></script>
  
  <link rel="stylesheet" href="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/css/validationEngine.jquery.css"/>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-jacy-1.01.js"></script>
  <script type="text/javascript" src="${ctx }/static/layer/layer.js"></script>
  <script src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	var ctx = '${ctx}';
</script>
</head>
<body>

	<div class="am-margin">

		<form id="questionForm" onsubmit="return false">
			<input type="hidden" id="id" name="id"
				value="${id }">
				<input type="hidden" id="buyerorderid" name="buyerorderid"
				value="${buyerorderid }">
				
			<div class="am-g am-margin-top">
		       <div class="am-u-sm-4 am-u-md-2 am-text-right">
		             充值金额
		        </div>
		        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
		           <input id="payMoney" name="payMoney" type="number" class="validate[required]" data-errormessage-value-missing="请填写支付金额"  value="" />
		        </div>
		     </div>
			<div class="am-g am-margin-top">
		       <div class="am-u-sm-4 am-u-md-2 am-text-right">
		             支付开始时间
		        </div>
		        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
		           <input type="text" name="beginpaydate"  class="validate[required] Wdate" id="beginpaydate" readonly="readonly" 
						data-errormessage-value-missing="请选择支付开始时间" value=''
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
		        </div>
		     </div>
			<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
		</form>
	</div>



	<div class="am-modal am-modal-alert" tabindex="-1" id="my-msg">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">提示</div>
			<div class="am-modal-bd" id="alert-msg"></div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-confirm>确定</span>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//将表单提交及其表单项的验证绑定到jquery validation engine
		$("#questionForm").validationEngine(
				"attach",
				{
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
							var path = "${ctx}/order/saveOrderPay";
							var param = $('#questionForm').serialize();
							$.post(path, param, function(data) {
								if (data.success) {
									
									layer.alert(data.msg, function(index){
										parent.queryContent();
										parent.createContent1();
									    layer.close(index);
									    parent.layer.close(parent.editIndex);
									});      
								} else {
									layer.alert(data.msg);
								}
							});
						}
					}
				});

		function showMsg(msg, callback) {
			$("#alert-msg").html(msg);
			$("#my-msg").modal({
				relatedTarget : this,
				onConfirm : function(options) {
					if (undefined != callback) {
						callback();
					}
				}
			});
		}
	</script>
</body>
</html>