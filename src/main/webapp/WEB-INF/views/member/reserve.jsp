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
<br>
<c:if test="${locked == 2 }">
<span style="color: red;">账户未激活不能预约</span></c:if>
<c:if test="${locked == 0 }">
<c:if test="${orderStatus == 0 }">
<span style="color: red;">预约申请成功！</span>
</c:if>
<c:if test="${orderStatus == 1 }">

	<span style="color: red;">预约成功！</span>
</c:if>
<c:if test="${orderStatus == null || orderStatus >= 3 }">
<input type="button" onclick="toOrder('${content.id }')" value="立即预约" class="am-btn am-btn-primary am-btn-block">
</c:if>

</c:if>

<script type="text/javascript">

	function toOrder(cid){
		if(cid == ""){
			showMsg("暂无预约信息");
		}else{
			//调用后台预约接口
			var param = {
					cid:cid
			};
			console.log(param);
			var path = ctx+"/order/save";
			commonAjax(path,param,function(data){
				if(data.success){
					showMsg(data.msg);
					location.reload() ;
				}else{
					showMsg(data.msg);
				}
			});
		}
	}
</script>
</body>
</html>