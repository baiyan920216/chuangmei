$(document).ready(function() {
	init();
	
});

//参数
var query = {
		page: 1,
		rows: 15,
		type: "",
		title: "",
		order:"new"
};

function init(){
	query.page = 1;
	queryContent(1);
}
function queryContent(pageNum){
	if(undefined!=pageNum){
		query.page = pageNum;
	}else{
		query.page = 1;
	}
	query.type = $("#query_type").val();
	query.title = $("#query_title").val();
	createContent();
}

function showEdit(id){
	var editPath = ctx+'/views/toContentEdit';
	if(undefined != id){
		editPath += '?id='+id;
	}
	window.location.href = editPath;
		
}

function showContent(id){
	var editPath = ctx+'/views/showContent';
	if(undefined != id){
		editPath += '?id='+id;
	}
	window.location.href = editPath;
	
}

function createContent(){
	var path = ctx+"/content/list";
	commonAjax(path,query,function(data){
		if(data.success){
			var pageStr = new Array; 
			$.each(data.rows,function(index,row){
				pageStr.push('<tr>');
				pageStr.push('<td>'+(index+1)+'</td>');
				pageStr.push('<td><a href="#">'+row.title+'</a></td>');
				pageStr.push('<td>'+rMap[row.type]+'</td>');
				pageStr.push('<td class="am-hide-sm-only">'+row.writer+'</td>');
				pageStr.push('<td class="am-hide-sm-only">'+row.createtime+'</td>');
				pageStr.push(' <td>');
				pageStr.push('<div class="am-btn-toolbar">');
				pageStr.push(' <div class="am-btn-group am-btn-group-xs">');
				pageStr.push('  <button onclick="showEdit('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 编辑</button>');
				pageStr.push('  <button onclick="showContent('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-play"></span> 预览</button>');
//				pageStr.push('  <button onclick="delContent('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-danger "><span class="am-icon-trash-o"></span> 删除</button>');
				pageStr.push(' </div>');
				pageStr.push(' </div>');
				pageStr.push(' </td>');
				pageStr.push(' </tr>');
			});
			$("#pagelist").html(pageStr.join(""));
			
			var page_options = {
					id:"content_page",
					pageCount:data.pageCount,
					current:data.pageNumber,
					callback:"queryContent"
			}
			pages(page_options);
		}else{
			showMsg(data.msg);
		}
	});
}

function showMsg(msg){
	$("#alert-msg").html(msg);
	$("#my-msg").modal();
}

function delContent(id){
	var delPath = ctx+'/content/del';
	var depParam = {
			id:id
	}
	showConfirm("确认删除该条记录吗？",function(){
		commonAjax(delPath,depParam,function(data){
			if(data.success){
				queryContent();
			}
			showMsg(data.msg);
		});
	});
}

