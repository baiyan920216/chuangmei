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
	  <li><a href="${ctx }/views/toOrderManagePage">创单管理</a></li>
	  <li class="am-active">查看创单信息</li>
	</ol>
	
 <form id="contentForm" class="am-form"  onsubmit="return false">
    <div class="am-g am-margin-top">
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
          ${editData.money }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             预约时间
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.orderdate }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             打款截止时间
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.orderenddate }
        </div>
     </div>
     
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             买方用户名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyeruname }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             买方姓名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyername }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             买方电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyertel }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             买方推荐人姓名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyersuportname }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             买方推荐人电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.buyersuporttel }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             创单状态
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.statusDes }
        </div>
     </div>
     <div class="am-margin">
<table class="am-table">
    <thead>
        <tr>
            <th>付款编号</th>
            <th>收款支付宝</th>
            <th>用户名</th>
            <th>用户姓名</th>
            <th>金额</th>
            <th>状态</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${orderpayList }" var="pay">
        <tr>
        	<td>${pay.id }</td>
            <td>${pay.selleralipay }</td>
            <td>${pay.selleruname }</td>
            <td>${pay.sellername }</td>
            <td>${pay.paymoney }</td>
            <td>${pay.statusDes }</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>
     
		<button type="button" onclick="goBack()" class="am-btn am-btn-primary am-btn-xs">返回</button>
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
var proofPic = '${editData.proof }';
if(proofPic!=''){
	var pimg = JSON.parse(proofPic)[0];
	$("#preview").attr("src",pimg.path);
}


function goBack(){
	location.href="${ctx }/views/toOrderManagePage";
}
</script>
</body>
</html>