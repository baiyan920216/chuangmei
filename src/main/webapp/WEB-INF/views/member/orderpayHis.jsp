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
<h3 class="am-article-title" style="text-align: center;">圆梦记录</h3>
<table class="am-table">
    <thead>
        <tr>
            <th>收款编码</th>
            <th>收款时间</th>
            <th>金额</th>
            <th>状态</th>
            <th>详情</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${orderpayList }" var="pay">
        <tr>
        	<td>${pay.id }</td>
            <td>${pay.beginpaydate }</td>
            <td>${pay.paymoney }</td>
            <td>${pay.statusDes }</td>
            <td>
            	<button type="button" onclick="toOrderpayDesc('${pay.id }')" class="am-btn am-btn-primary am-btn-xs">查看</button>
            </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>

<script type="text/javascript">

function toOrderpayDesc(orderpayid) {

	var editPath = ctx + '/member/toorderpayOrverDescPage';
	if (undefined != orderpayid) {
		editPath += '?id=' + orderpayid;
	}
	window.location.href = editPath;
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