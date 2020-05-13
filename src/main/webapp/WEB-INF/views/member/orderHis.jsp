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
<h3 class="am-article-title" style="text-align: center;">造梦记录</h3>
<table class="am-table">
    <thead>
        <tr>
            <th>编号</th>
            <th>日期</th>
            <th>订单金额</th>
            <th>状态</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${orderList }" var="data">
        <tr>
        	<td>${data.id }</td>
            <td>${data.orderdate }</td>
            <td>${data.money }</td>
            <td>${data.statusDes }</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>