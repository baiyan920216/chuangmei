$(document).ready(function() {
	init();
	
});

function init(){
	createQuery();
}

function showEdit(id){
	var editPath = ctx+'/views/toUserEdit';
	if(undefined != id){
		editPath += '?id='+id;
	}
	window.location.href = editPath;
		
}
function createQuery(){
	var path = ctx+"/user/list";
	var query = {};
	commonAjax(path,query,function(data){
		if(data.success){
			var pageStr = new Array; 
			$.each(data.data,function(index,row){
				pageStr.push('<tr>');
				pageStr.push('<td>'+(index+1)+'</td>');
				pageStr.push('<td><a href="#" >'+row.uname+'</a></td>');
				pageStr.push('<td><a href="#" >'+row.nikename+'</a></td>');
				pageStr.push('<td><a href="#" >'+row.sex+'</a></td>');
				pageStr.push('<td><a href="#" >'+row.tel+'</a></td>');
				pageStr.push('<td><a href="#" >'+rMap[row.roletype]+'</a></td>');
				pageStr.push(' <td>');
				pageStr.push('<div class="am-btn-toolbar">');
				pageStr.push(' <div class="am-btn-group am-btn-group-xs">');
				
				pageStr.push('  <button onclick="showEdit('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 编辑</button>');
				pageStr.push('  <button onclick="delUser('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-danger "><span class="am-icon-trash-o"></span> 删除</button>');
				
				pageStr.push(' </div>');
				pageStr.push(' </div>');
				pageStr.push(' </td>');
				pageStr.push(' </tr>');
			});
			$("#pagelist").html(pageStr.join(""));
		}else{
			showMsg(data.msg);
		}
	});
}


function showMsg(msg){
	$("#alert-msg").html(msg);
	$("#my-msg").modal();
}

function delUser(id){
	var delPath = ctx+'/user/del';
	var depParam = {
			id:id
	}
	showConfirm("确认删除该条记录吗？",function(){
		commonAjax(delPath,depParam,function(data){
			if(data.success){
				createQuery();
			}
			showMsg(data.msg);
		});
	});
}
