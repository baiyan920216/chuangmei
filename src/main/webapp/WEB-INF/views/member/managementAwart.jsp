<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <style type="text/css">
  	.am-table>thead>tr>th, .am-table>tbody>tr>th, .am-table>tfoot>tr>th, .am-table>thead>tr>td, .am-table>tbody>tr>td, .am-table>tfoot>tr>td {padding:0}
  </style>
</head>
<body>
<div class="am-margin">
<h3 class="am-article-title">管理奖</h3>
<h5>余额:${money }</h5><button type="button" onclick="toOrderpayDesc()" class="am-btn am-btn-primary am-btn-xs">提现</button>
<table class="am-table">
    <thead>
        <tr>
            <th>用户名</th>
            <th>姓名</th>
            <th>金额</th>
            <th>类型</th>
            <th>时间</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${data }" var="data">
        <tr>
            <td>${data.subuseruname }</td>
            <td>${data.subuname }</td>
            <td>${data.money }</td>
            <td>${data.flag }</td>
            <td>${data.finishtime }</td>
            
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>

<script type="text/javascript">

function toOrderpayDesc() {debugger
	var money = ${money};
	var roleType = ${roleType};
	
	var tixianjine = 0;
	
	if(roleType == 1){
		//黄金会员
		tixianjine = -500;
	}else if(roleType == 2){
		//白金会员
		tixianjine = -1500;
	}
	if(tixianjine != 0){
		if((money + tixianjine)>=100){
			//可以提现
			commonAjax(ctx+"/order/tixian",{money:tixianjine},function(data){
				if(data.success){
					showMsg(data.msg);
					location.reload() ;
				}else{
					showMsg(data.msg);
				}
			});
		}else{
			showMsg("请保证提现后，余额大于100！");
		}
	}
	
}
</script>
</body>
</html>