<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/static/assets/js/amazeui.js"></script>

 <link rel="stylesheet" type="text/css" href="${ctx }/static/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
<%-- <script type="text/javascript" src="${ctx }/static/zTree_v3/js/jquery-1.4.4.min.js"></script> --%>
<script type="text/javascript" src="${ctx }/static/zTree_v3/js/jquery.ztree.core-3.5.js"></script>

<title></title>
</head>
<body>

	<div data-am-widget="titlebar" class="am-titlebar am-titlebar-default">
	  <h2 class="am-titlebar-title ">会员管理</h2>
	</div>
<div>
	<ul id="tree" class="ztree" style="width:560px; overflow:auto;"></ul>
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

<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示</div>
    <div class="am-modal-bd" id="confirm-msg">
      	确认要删除这条记录吗？
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
    </div>
  </div>
</div>
<script type="text/javascript">

             var zTree;
             var setting = {
                 view: {
                     dblClickExpand: false,
                     showLine: true,
                     selectedMulti: false
                 },
                
                 data: {
                	 key:{
                    	 name:"treeNode"
                     },
                     simpleData: {
                         enable:true,
                         idKey: "uname",
                         pIdKey: "supportid",
                         rootPId: ""
                     }
                 },
                 callback: {
                     beforeClick: function(treeId, treeNode) {
                         var zTree = $.fn.zTree.getZTreeObj("tree");
                         if (treeNode.isParent) {
                             zTree.expandNode(treeNode);
                             return false;
                         } else {
                             return true;
                         }
                     }
                 }
             };
             
             $(document).ready(function(){
                 var t = $("#tree");
                 
                 /**
                  * zTree 初始化方法：$.fn.zTree.init(t, setting, zNodes)
                  * t:用于展现 zTree 的 DOM 容器
                  * setting:zTree 的配置数据
                  * zNodes:zTree 的节点数据
                  * 
                  */
                  var rMap ;
                  var path = '${ctx }/user/tree';
                  commonAjax(path,{},function(data){
              		if(data.success){
              			rMap = data.data
              		}
              	});
                  
                 t = $.fn.zTree.init(t, setting, rMap);
             });
</script>
<%-- <script type="text/javascript" src="${ctx }/static/js/user/client.js"></script> --%>

</body>
</html>