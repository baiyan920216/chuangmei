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
<h3 class="am-article-title" style="text-align: center;">造梦阶段</h3>
<table class="am-table">
    <thead>
        <tr>
            <th>付款编码</th>
            <th>打款时间</th>
            <th>状态</th>
            <th>金额</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${orderpayList }" var="pay">
        <tr>
        	<td>${pay.id }</td>
            <td>${pay.beginpaydate }</td>
            <td>${pay.statusDes }</td>
            <td>${pay.paymoney }</td>
            <td><button type="button" onclick="toUpProof('${pay.id }')" class="am-btn am-btn-primary am-btn-xs">查看详情</button></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>

<script type="text/javascript">
	function toUpProof(id){
		var editPath = ctx+'/member/toUpProof';
		if(undefined != id){
			editPath += '?id='+id;
		}
		window.location.href = editPath;
	}
</script>
</body>
</html>