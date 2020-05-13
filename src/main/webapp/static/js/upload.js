var buttonStr = '<a class="btn  file_del" href="javascript:void(0);">删除</a>';
var upload = {
	/**
     * upload
     */
    uploadInit: function (id, limitSize,uploadFlag,uploadCount) {
      // 生成btn和progress
//      if(uploadFlag == true){
//    	  $('#' + id).before('<input id="'+id+'_btn" type="button" value="上传文件">');
//      }else{
//    	  buttonStr = '<a class="btn file_download" href="javascript:void(0);">下载</a>';
//      }
    insertFiles();
      $('#' + id).after('<div id="'+id+'_progress" class="progressbar" style="display: none;"><div class="bar" style="width: 0%;"></div></div>');
      var upload_btn = $('#'+id+'_btn');
      var progress = $('#'+id+'_progress');
      var progressBar = progress.find('.bar');
      $('#' + id).fileupload({
        dataType: 'json',
        add: function (e, data) {
          if (limitSize) {
            var fileSize = data.files[0].size;
            console.log('file size:' + fileSize);
            if (fileSize > limitSize * 1024 * 1024) {
              layer.alert('上传文件不能大于'+limitSize+'MB');
              return;
            }
          }
          upload_btn.val('上传中……').prop('disabled', true);
          progress.show();
          progressBar.css('width', '0%');
          data.submit();
        },
        done: function (e, data) {
          upload_btn.val('上传完成').prop('disabled', false);
          progress.hide();
          progressBar.css('width', '100%');
          setTimeout(function(){
            upload_btn.val('上传文件');
          }, 2000);
          appendFile(data.result.data);
        },
        progressall: function (e, data) {
          var progress = parseInt(data.loaded / data.total * 100, 10);
          progressBar.css('width', progress + '%');
        }
      });
      upload_btn.click(function () {
        return $('#' + id).click();
      });
      
      function appendFile(dataResult) {
    	  if(uploadCount=='0'){
    		  $('#' + id+'_btn').css('display','none');
    	  }
    	  $('#' + id).parent().find('.file_items').append('<p class="file-item" data-path="'+dataResult.attachmentUrl+'"  data-name="'+dataResult.attachmentName+'"><i></i>'+dataResult.attachmentName+' &nbsp;&nbsp;&nbsp;'+buttonStr+'</p>');
    	  $(".file_del").on("click", function() {
    		  this.parentElement.remove();
    		  if(uploadCount=='0'){
        		  $('#' + id+'_btn').css('display','block');
        	  }
    		  dataRebind();
    	  });
    	  
          dataRebind();

      }
      
      function dataRebind() {
        var paths = new Array();
        var names = new Array();
        
        var files = new Array();
        var tmpFileInfo = {};
        
        $('#' + id).parent().find('.file_items').find('.file-item').each(function () {
          
          tmpFileInfo = {'name':$(this).data('name'),'path':$(this).data('path')};
          files.push(tmpFileInfo);
        });
        $('#' + id).parent().find('.attachments').val(JSON.stringify(files));
      }
      
      function insertFiles() {
    	  
        var paths = $('#' + id).parent().find('.attachments').val();
        if (paths === undefined) {
        	console.error('未找到'+id+'下class attachments 的容器');
        	return;
        }
        if (paths === '') {
          return;
        } else {
          paths = JSON.parse(paths);
        }
        if(uploadCount=='0' && paths.length > 0){
  		  $('#' + id+'_btn').css('display','none');
  	  }
        if (paths.length > 0) {
          for (var i=0; i<paths.length; i++) {
            var filePath = paths[i];
            $('#' + id).parent().find('.file_items').append('<p class="file-item" data-path="'+filePath.path+'" data-name="'+filePath.name+'"><i></i>'+filePath.name+' &nbsp;&nbsp;&nbsp;'+buttonStr+'</p>');
          }

          $(".file_del").on("click", function() {
        	  this.parentElement.remove();
        	  if(uploadCount=='0'){
        		  $('#' + id+'_btn').css('display','block');
        	  }
        	  dataRebind();
          });
          $(".file_download").on("click", function() {
    		  
    		  downloadFile(this);
    	  });
        }
      }
      
      function downloadFile(data){
    	  var download_file_name = $(data.parentElement).data('name');
    	  var download_file_path = $(data.parentElement).data('path');
    	  
  		$.ajax({
  			type : "POST",
  			url : ctx + "/uploadFileMsg/isExist",
  			data : {
  				attachmentUrl: download_file_path
  			},
  			success : function(data) {
  				if(data.data.flag == '1'){
  	  				top.location.href = ctx + '/uploadFileMsg/fileDownload?attachmentUrl=' + download_file_path + '&attachmentName=' + download_file_name;
  	  			}else{
  	  				alert( data.msg);
  	  			}
  			}
  		});
    	  
    	  
      }
    }
}
