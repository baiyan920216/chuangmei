<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
</head>
<body>

<div class="am-margin">
	
<article class="am-article">
  <div class="am-article-hd">
    <div style="text-align: center">
    <h1 class="am-article-title">${editData.title }</h1>
    
     <c:if test="${editData.showpic!=null&&editData.showpic!=''}">
		  <img src="${editData.showpic }"  class="am-img-thumbnail" id="preview" width="300px">
     </c:if>
    </div>
    <div style="text-align: right">
    <p class="am-article-meta">${editData.writer }&nbsp;&nbsp;${editData.createtime }</p>
    </div>
  </div>

  <div class="am-article-bd">
    <p class="am-article-lead">${editData.des }</p>
    ${editData.content }
  </div>
</article>
	
	
</div>
</body>
</html>