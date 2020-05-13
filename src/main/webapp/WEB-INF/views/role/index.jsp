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
	  <h2 class="am-titlebar-title ">角色管理</h2>
	</div>

	<div class="am-g">
		 <div class="am-u-sm-12 am-u-md-6">
        <div class="am-btn-toolbar">
          <div class="am-btn-group am-btn-group-xs">
            <button type="button" class="am-btn am-btn-default" onclick="showEdit()"><span class="am-icon-plus"></span> 新增</button>
            <button type="button" class="am-btn am-btn-default" onclick="createQuestion()"><span class="am-icon-search"></span> 查询</button>
          </div>
        </div>
      </div>
	</div>

	<div class="am-list-news-bd">
		<table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-id">ID</th>
                <th class="table-title">角色名</th>
                <th class="table-date am-hide-sm-only">备注</th>
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

<div class="am-modal am-modal-no-btn" tabindex="-1" id="menu-dialog">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">角色授权
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    <div class="am-modal-bd">
    <input type="text" id="roleId" style="display:none;"/>
     <ul id="module_auth_tree" class="ztree"></ul>
     <button type="button" class="am-btn am-btn-default" onclick="saveAuth()"><span class=""></span> 保存</button>
    </div>
  </div>
</div>

<script type="text/javascript" src="${ctx }/static/js/role/role.js"></script>

</body>
</html>