<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>员工绑定</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <script type="text/javascript" src="${ctx }/static/js/jquery-1.11.1.min.js"></script>
  <link rel="alternate icon" type="image/png" href="${ctx }/static/assets/i/favicon.png">
  <link rel="stylesheet" href="${ctx }/static/assets/css/amazeui.min.css"/>
  <script type="text/javascript" src="${ctx }/static/js/commonsFG.js"></script>
  <script type="text/javascript" src="${ctx }/static/assets/js/amazeui.js"></script>
  
  <link rel="stylesheet" href="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/css/validationEngine.jquery.css"/>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/static/jquery-validation-engine-ciaoca-2.6.2/js/jquery.validationEngine-jacy-1.01.js"></script>
</head>
<body>
<header data-am-widget="header" class="am-header am-header-default ">
  <h1 class="am-header-title">
    <a href="#title-link">员工绑定</a>
  </h1>
</header>
<div class="am-g">
  <div class="am-margin  am-u-lg-4 am-u-md-7 am-u-sm-centered">
<br>
    <form id="selectForm" method="post" class="am-form"  onsubmit="return false">
      <input type="hidden" value="${openid}" name="openid">
      <%--<select id="company" name="cid"  class="validate[required]  " data-errormessage-value-missing="请选择公司">--%>
		        <%--<option value="">请选择公司</option>--%>
		        <%--<c:forEach items="${list }" var="r">--%>
		        	<%--<option value="${r.id}" >${r.name}</option>--%>
		        <%--</c:forEach>--%>
		  <%--</select>--%>
		  <%--</br>--%>
      <%--<select id="system" name="sid"  class="validate[required]  " data-errormessage-value-missing="请选择系统">--%>

      <%--</select>--%>
      </br>
      <input placeholder="手机号" class="validate[required]  " data-errormessage-value-missing="请输入手机号" type="number" name="tel" id="tel" value="">
      <br /><br />
      <div class="am-cf">
        <%--<input type="submit"  value="取消" class="am-btn am-btn-primary am-btn-sm ">--%>
        <input type="button" onclick="submitAdd()" value="确定" style="padding-left: 10px;" class="am-btn am-btn-primary am-btn-sm ">
      </div>
    </form>
  </div>
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
<script type="text/javascript">

    $('#company').change(function() {
//        var selectedId = $(this).children('option:selected').val();//这就是selected的值
//        loadSystem(selectedId);

    });
//    function loadSystem(cid) {
//        var list = SystemSelectData(cid);
//        $("#system").empty();
//        $("#system").append(
//            "<option value='' selected='selected' >请选择系统</option>");
//        if (null != list && list.length > 0) {
//            for (var i = 0; i < list.length; i++) {
//                var htmlstr = "";
//                htmlstr += ("<option value='");
//                htmlstr += (list[i].id);
//                htmlstr += ("' ");
//                htmlstr += (">");
//                htmlstr += (list[i].sysname + "</option>");
//                $("#system").append(htmlstr);
//            }
//        }
//    }

    <%--// //系统下拉列表数据查询--%>
    <%--function SystemSelectData(cid) {--%>
        <%--var allList = '';--%>
        <%--$.ajax({--%>
            <%--cache : false,--%>
            <%--type : "get",--%>
            <%--url : "${ctx}/system/getSystemByCid",--%>
            <%--data : {--%>
                <%--cid : cid--%>
            <%--},--%>
            <%--async : false,--%>
            <%--error : function(request) {--%>

            <%--},--%>
            <%--success : function(dataDepart) {--%>

                <%--allList = dataDepart;--%>
            <%--}--%>
        <%--});--%>
        <%--return allList;--%>
    <%--}--%>
    function submitAdd() {
//        if($("#company").val() == "" || $("#company").val() == null){
//            showMsg("请选择公司");
//            return;
//        }
//        if($("#system").val() == "" || $("#system").val() == null){
//            showMsg("请选择系统");
//            return;
//        }
        if($("#tel").val().trim() == ""){
            showMsg("请填写手机号");
            return;
        }
        if($("#tel").val().trim().length < 11){
            showMsg("请填写正确手机号");
            return;
        }
        var path = "${ctx}/person/add";
        var param = $('#selectForm').serialize();
        $.post(path, param, function(data) {
            if(data.success){
                showMsg(data.msg)
                setTimeout(after,1000);
            }else{
                showMsg(data.msg)
            }
        });
    }
    function after() {
        WeixinJSBridge.invoke('closeWindow',{},function(res){});
    }
    function showMsg(msg){
        $("#alert-msg").html(msg);
        $("#my-msg").modal();
    }
</script>
</body>
</html>