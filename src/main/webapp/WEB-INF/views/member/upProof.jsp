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
</head>
<body>
<div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             编号
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.id }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             交易金额
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.paymoney }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方支付宝
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.selleralipay }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方用户名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.selleruname }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方姓名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.sellername }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.sellertel }
        </div>
     </div>
     
     
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方推荐人姓名
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.sellersupportname }
        </div>
     </div>
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             收款方推荐人电话
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
          ${editData.sellersupporttel }
        </div>
     </div>
     
     
     <div class="am-g am-margin-top">
       <div class="am-u-sm-4 am-u-md-2 am-text-right">
             付款凭证
        </div>
        <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
<input id="pss_upload" 
					type="file" name="file" accept="image/jpg,image/jpeg,image/png" data-url="${ctx }/uploadFileMsg/fileUpload">
					<input id="pss_hidden" class="attachments" type="hidden"
					name="proofPic"
					value='${editData.proof }'>
					<div class="file_items">
        </div>
     </div>
     <br />
     <br />
<input type="button" onclick="toProof('${editData.id }')" value="上传付款凭证" class="am-btn am-btn-primary am-btn-block">
<input type="button" onclick="goBack()" value="返回" class="am-btn am-btn-primary am-btn-block">
</div>
<script type="text/javascript">
function goBack(){
	window.location.href="${ctx}/member/tofukuanpage";
}
$(document).ready(function() {

	 upload.uploadInit('pss_upload',30,true);//大小不能超过30M 
});
function toProof(orderid){
	if($("#pss_hidden").val() == '' || $("#pss_hidden").val() == '[]' ){
		showMsg("请上传付款凭证");
		return;
	}
	
	if(undefined != orderid ){
		var query = {
				orderid:orderid,
				proofPic: $("#pss_hidden").val()
		};
		var editPath = ctx+'/order/iploadProof';
		commonAjax(editPath,query,function(data){
			if(data.success){
				showMsg(data.msg);
				window.location.href="${ctx}/member/tofukuanpage";
// 				location.reload() ;
//				createQuery();
			}else{
				showMsg(data.msg);
			}
		});
	}
}
</script>
</body>
</html>