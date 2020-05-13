<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/static/assets/js/amazeui.js"></script>
<title></title>
</head>
<body>

	<div data-am-widget="titlebar" class="am-titlebar am-titlebar-default">
	  <h2 class="am-titlebar-title ">图文管理</h2>
	</div>
<form action="">
	<div class="am-g">
		 <div class="am-u-sm-12 am-u-md-6">
        <div class="am-btn-toolbar">
          <div class="am-btn-group am-btn-group-xs">
<!--             <button type="button" class="am-btn am-btn-default" onclick="showEdit()"><span class="am-icon-plus"></span> 新增</button> -->
<!--             <button type="button" class="am-btn am-btn-default" onclick="queryContent()"><span class="am-icon-search"></span> 查询</button> -->
<!--             <button type="reset" class="am-btn am-btn-default"><span class="am-icon-recycle"></span> 重置</button> -->
          </div>
        </div>
      </div>
	</div>
	
	<div class="am-g">
		 <div class="am-u-sm-12 am-u-md-6">
        <div class="am-btn-toolbar">
          <div class="am-btn-group am-btn-group-xs">
         
<!--         <select id="query_role" name="roletype" > -->
<!-- 		        <option value="">请选择角色</option> -->
<%-- 		        <c:forEach items="${rList }" var="r"> --%>
<%-- 		        <c:if test="${r.id == editData.roletype}"> --%>
<%-- 		        	<option value="${r.id}" selected>${r.name}</option> --%>
<%-- 		        </c:if> --%>
<%-- 		        <c:if test="${r.id != editData.roletype}"> --%>
<%-- 		        	<option value="${r.id}" >${r.name}</option> --%>
<%-- 		        </c:if> --%>
<%-- 		        </c:forEach> --%>
<!-- 		  </select> -->
            
<!--             <input type="text" id="query_title" class="" style="width:150px;" placeholder="文章标题"> -->
            </div>
            </div>
            </div>
            </div>
</form>
	<div class="am-list-news-bd">
		<table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-id">ID</th>
                <th class="table-title">标题</th>
                <th class="table-type">类别</th>
                <th class="table-author am-hide-sm-only">作者</th>
                <th class="table-date am-hide-sm-only">修改日期</th>
                <th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody id="pagelist">
          </tbody>
        </table>
	</div>
	<div class="am-list-news-bd" id="content_page">
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
var role_str = '${rMap}';
var rMap = JSON.parse(role_str);
</script>
<script type="text/javascript" src="${ctx }/static/js/content/content.js"></script>
</body>
</html>