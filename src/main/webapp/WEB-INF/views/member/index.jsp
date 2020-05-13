<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
<script type="text/javascript"src="${ctx}/static/js/upload/jquery.ui.widget.js"></script>
<script type="text/javascript"src="${ctx}/static/js/upload/jquery.iframe-transport.js"></script>
<script type="text/javascript"src="${ctx}/static/js/upload/jquery.fileupload.js"></script>
<script type="text/javascript" src="${ctx}/static/js/upload.js"></script>
<style type="text/css">
.hidden {
  display: none !important;
}
</style>
</head>
<body>


<article class="am-article">
  <div class="am-article-hd">
    <div style="text-align: center">
    <h1 class="am-article-title">${content.title }</h1>
    
     <c:if test="${content.showpic!=null&&content.showpic!=''}">
		  <img src="${content.showpic }"  class="am-img-thumbnail" id="preview" width="300px">
     </c:if>
    </div>
    <div style="text-align: right">
    <p class="am-article-meta">${content.writer }&nbsp;&nbsp;${content.createtime }</p>
    </div>
    <div style="text-align: right">
    <p class="am-article-meta">预约时间：${content.starttime }&nbsp;~&nbsp;${content.endtime }</p>
    </div>
  </div>

  <div class="am-article-bd">
    <p class="am-article-lead">${content.des }</p>
    ${content.content }
  </div>
</article>

<%-- ${orderStatus == null } --%>
</body>
</html>