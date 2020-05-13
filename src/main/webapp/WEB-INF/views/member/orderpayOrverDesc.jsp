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
	
	 <div class="am-u-sm-4 am-u-md-2 am-text-right">
             编号
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.id }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             交易金额
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.paymoney }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方支付宝
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.selleralipay }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方用户名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.selleruname }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方姓名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.sellername }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.sellertel }
        </div>
     </div>
     
     
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方推荐人姓名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.sellersupportname }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方推荐人电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.sellersupporttel }
        </div>
     </div>
     
     
     
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             付款方用户名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyeruname }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             付款方姓名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyername }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             付款方支付宝
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyeralipay }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             付款方推荐人姓名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyersupportname }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             付款方推荐人电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyersupporttel }
        </div>
     </div>
     
	
	
	<div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             付款凭证
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <img id="proof" src="">
        </div>
     </div>
     
    <br> 
<input type="button" onclick="goBack()" value="返回" class="am-btn am-btn-primary am-btn-block">
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

var imgpath = JSON.parse('${editData.proof }');

$("#proof").attr("src",imgpath[0].path);



function showMsg(msg){
	$("#alert-msg").html(msg);
	$("#my-msg").modal();
}
function goBack(){
	window.location.href="${ctx}/member/toorderpayHispage";
}
function shoukuan(orderpayid){
	
	if(undefined != orderpayid ){
		var query = {
				orderpayid:orderpayid
		};
		var editPath = ctx+'/order/shoukuan';
		commonAjax(editPath,query,function(data){
			if(data.success){
				showMsg(data.msg);
				location.reload() ;
//				createQuery();
			}else{
				showMsg(data.msg);
			}
		});
	}
}
</script>
</body>
</html>