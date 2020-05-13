// 图片上传默认参数，
var defaults = {
	fileSize: 5, // 单位MB
	exts: ['jpeg', 'png']
};
function uploadInit(file_input_id, callback) {
	$('#' + file_input_id).change(function(){
		var file = $('#' + file_input_id).prop('files')[0];
	    if (file) {
	    	// 验证图片格式
	        if (!isAllowType(defaults.exts, file.type)) {
	        	msg.info('图片格式不正确，支持jpg和png格式');
	        	return;
	        }
	        // 验证图片大小
	        if (file.size > defaults.fileSize * 1024 * 1024) {
	        	msg.info('请选择小于' + defaults.fileSize + 'M的图片');
	        	return;
	        }
	        // 自动上传
	        uploadFile(file_input_id, callback);
	    }
	});
   
}

function uploadFile(file_input_id, callback) {
    var fd = new FormData();
    var url = ctx + '/uploadFileMsg/uploadFile';
//    var url = 'http://sport.c4a.com.cn/sports-union-weixin/uploadFileMsg/uploadFile';
    fd.append("uploadFile", $('#' + file_input_id).prop('files')[0]);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", function(evt){
    	var data = JSON.parse(evt.target.responseText);
        var file = data.data;
        callback(file);
    }, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", url);
    xhr.send(fd);

}
 
function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        $('#progress').css('width', percentComplete.toString() + '%');
    } else {
        document.getElementById('progressNumber').innerHTML = 'unable to compute';
    }
}
 
function uploadComplete(evt) {
    /* This event is raised when the server send back a response */
    var data = JSON.parse(evt.target.responseText);
    var file = data.data;
    callback(file);
}
 
function uploadFailed(evt) {
    msg.error('上传文件发生错误！');
}
 
function uploadCanceled(evt) {
	msg.info('上传被取消！');
}

function isAllowType(types, type) {
	var ext = type.split('/')[1];
	var flag = false;
	for (var i=0; i<types.length; i++) {
		if (ext == types[i]) {
			flag = true;
		}
	}
	return flag;
}