<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
</head>
<body>

<div class="am-margin">
	
	<ol class="am-breadcrumb">
	  <li><a href="${ctx }/views/toContentPage">图文管理</a></li>
	  <li class="am-active">编辑</li>
	</ol>
	
 <form id="contentForm" class="am-form"  onsubmit="return false">
 <input type="hidden" id="id" name="id" value="${editData.id }">
    <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              角色类型
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <select id="query_role" name="type" disabled="disabled">
		        <option value="">请选择角色</option>
		        <c:forEach items="${rList }" var="r">
		        <c:if test="${r.id == editData.type}">
		        	<option value="${r.id}" selected>${r.name}</option>
		        </c:if>
		        <c:if test="${r.id != editData.type}">
		        	<option value="${r.id}" >${r.name}</option>
		        </c:if>
		        </c:forEach>
		  </select>
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              标题
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="title" name="title" value="${editData.title}"
           placeholder="标题" type="text"
            class="am-input-sm  validate[required]" 
            data-errormessage-value-missing="请填写文章标题">
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              作者
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          <input id="writer" name="writer" value="${editData.writer}" placeholder="作者信息" type="text" class="am-input-sm">
        </div>
     </div>
     
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             预约开始时间
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
           <input type="text" name="starttime" style="height: 39px;" class="am-input-sm validate[required] Wdate" id="outdate" readonly="readonly" 
				data-errormessage-value-missing="请选择预约开始时间" value='${editData.starttime }'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             预约结束时间
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
           <input type="text" name="endtime" style="height: 39px;" class="am-input-sm validate[required] Wdate" id="outdate" readonly="readonly" 
				data-errormessage-value-missing="请选择预约结束时间" value='${editData.endtime }'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
              摘要图
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
        <c:if test="${editData.showpic!=null&&editData.showpic!=''}">
		  <img src="${editData.showpic }"  class="am-img-thumbnail" id="preview" width="300px">
        </c:if>
        <c:if test="${editData.showpic==null||editData.showpic==''}">
		  <img src="${ctx }/static/image/nopic.gif"  class="am-img-thumbnail" id="preview" width="300px">
        </c:if>
          <button type="button" onclick="upImage();" class="am-btn am-btn-danger am-btn-sm">
    		<i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
          <input type="hidden" id="showpic" name="showpic" class="width-85" value="${editData.showpic }" />
        </div>
     </div>
     <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-12 am-u-md-2 am-text-right admin-form-text">
              摘要
            </div>
            <div class="am-u-sm-12 am-u-md-10">
          <textarea name="des" id="des" rows="3" placeholder="文章摘要信息">${editData.des }</textarea>
        </div>
     </div>
          
      <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-12 am-u-md-2 am-text-right admin-form-text">
              文章内容
            </div>
            <div class="am-u-sm-12 am-u-md-10">
      	<script id="content" class=""  name="content" type="text/plain" >${editData.content }</script>
       </div>
     </div>
     
     
     
     
		<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
		<button type="button" onclick="resetForm()"  class="am-btn am-btn-primary am-btn-xs">重置</button>
</form>
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
<script type="text/plain" id="upload_uqe"></script>
	<script type="text/javascript">

		//实例化编辑器
      var _editor = UE.getEditor('upload_uqe',
      {
        autoHeightEnabled:false
      });
      _editor.ready(function (){
     
    	  _editor.hide();//隐藏编辑器
     
    		//监听图片上传
    		_editor.addListener('beforeInsertImage', function (t,arg)
    		{
				$("#showpic").attr("value", arg[0].src);      //侦听图片上传 
                $("#preview").attr("src", arg[0].src);          
    		});
      });
    
    
</script>

<script type="text/javascript">
//将表单提交及其表单项的验证绑定到jquery validation engine
$("#contentForm").validationEngine("attach", {
	promptPosition : "topRight:-100,0",// 提示信息位置：右上，偏移量：x:-100,y:0
	binded : false,// 非即时验证
	autoHidePrompt : true,
	autoHideDelay : 3000,
	showOneMessage : true,
	ajaxFormValidationMethod : 'post',
	maxErrorsPerField : 1,// 单个元素显示错误提示的最大数量，值设为数值。默认 false 表示不限制
	scroll : false, // 提示信息不滚屏
	onValidationComplete : function(form, status) {// 表单提交验证完成时的回调函数,即使验证都通过也不提交表单
		if (status) {// 表单验证通过
			var path = "${ctx}/content/save";
			var param = $('#contentForm').serialize();
			$.post(path, param, function(data) {
				if(data.success){
					showMsg(data.msg)
				}else{
					showMsg(data.msg)
				}
			});
		}
	}
});
init();
function init(){
	var id='${editData.id}';
	if(id!=''){
		$("#type").val('${editData.type}');
	}
}

var ue = UE.getEditor('content');

function resetForm(){
	document.getElementById("contentForm").reset();
	ue.reset();
}
		
function upImage() {
	var myImage = _editor.getDialog("insertimage");
	myImage.open();
}
		
function showMsg(msg){
	$("#alert-msg").html(msg);
	$("#my-msg").modal();
}
</script>
</body>
</html>