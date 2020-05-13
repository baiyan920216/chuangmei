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
<h3 class="am-article-title">创美币记录</h3>
<h5>余额:${sumcoin }</h5>
<table class="am-table">
    <thead>
        <tr>
            <th>类型</th>
            <th>数量</th>
            
            <th>备注</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${cList }" var="data">
        <tr>
        	<td>${data.type }</td>
        	<td>${data.coin }</td>
            <td>${data.remark }</td>
            
            
            
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<script type="text/javascript" src="${ctx }/static/layer/layer.js"></script>
<script type="text/javascript">

// var editIndex;
// function addCoin(uid){
// 	//页面层
// 	var editPath = ctx+'/toUserAddCoin';
// 	if(undefined != uid){
// 		editPath += '?uid='+uid;
// 	}
// 	editIndex = layer.open({
//         type: 2,
//         title: '分配创币',
//         shadeClose: true,
//         area: ['400px','200px'],
//         content: editPath,
//         scrollbar: false
//     });
// }

// function toOrderpayDesc() {debugger
// 	var sumcoin = ${sumcoin};
// 	var roleType = ${roleType};
	
// 	if(tixianjine != 0){
// 		if((money + tixianjine)>=100){
// 			//可以提现
// 			commonAjax(ctx+"/order/tixian",{money:tixianjine},function(data){
// 				if(data.success){
// 					showMsg(data.msg);
// 					location.reload() ;
// 				}else{
// 					showMsg(data.msg);
// 				}
// 			});
// 		}else{
// 			showMsg("请保证提现后，余额大于100！");
// 		}
// 	}
	
// }
</script>
</body>
</html>