$(document).ready(function() {
	init();
	
});

function init(){
	createQuestion();
}

function showEdit(id){
	var editPath = ctx+'/views/toRoleEdit';
	if(undefined != id){
		editPath += '?id='+id;
	}
	window.location.href = editPath;
		
}

function createQuestion(){
	var path = ctx+"/role/rolelist";
	var query = {};
	commonAjax(path,query,function(data){
		if(data.success){
			var pageStr = new Array; 
			$.each(data.data,function(index,row){
				pageStr.push('<tr>');
				pageStr.push('<td>'+(index+1)+'</td>');
				pageStr.push('<td><a href="#" >'+row.name+'</a></td>');
				pageStr.push('<td class="am-hide-sm-only">'+row.remark+'</td>');
				pageStr.push(' <td>');
				pageStr.push('<div class="am-btn-toolbar">');
				pageStr.push(' <div class="am-btn-group am-btn-group-xs">');
				
				if(row.id==0 || row.id==1 || row.id==2){
					pageStr.push('初始角色，不可操作！');
				}else{
					pageStr.push('  <button onclick="showEdit('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 编辑</button>');
//					pageStr.push('  <button onclick=accredit('+row.id+',"'+row.auths+'") class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 授权</button>');
					pageStr.push('  <button onclick="delQuesion('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-danger "><span class="am-icon-trash-o"></span> 删除</button>');
				}
				
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

function saveAuth(){
	var roleId = $("#roleId").val();
	var treeObj = $.fn.zTree.getZTreeObj("module_auth_tree");
	var nodes = treeObj.getCheckedNodes(true);
	
	var len = nodes.length;
	var menuIds = "";
	for (var i = 0; i < len; i++) {
		menuIds = menuIds + "," + nodes[i].id;
	}
	if (menuIds.length > 0) {
		menuIds = menuIds.substring(1);
	}
	
	var param = {
			id:roleId,
			auths:menuIds
	};
	console.log(param);
	var path = ctx+"/role/saveAuths";
	commonAjax(path,param,function(data){
		if(data.success){
			showMsg(data.msg);
			createQuestion();
			$("#menu-dialog").modal();
		}else{
			showMsg(data.msg);
		}
	});
}

function accredit(id,checked){
	//加载菜单数据
	var path = ctx+"/role/menuAll";
	var query = {};
	var checkedAry = [];
	if(checked!=null){
		checkedAry = (checked+"").split(",");
	}
	$("#roleId").val(id);
	
	commonAjax(path,query,function(data){
		if(data.success){
			var modules = data.data;
			
			for(var i in modules){
				modules[i].open = true;
				for(var j in checkedAry){
					if(checkedAry[j] == modules[i].id){
						modules[i].checked = true;
					}
				}
			}
			
			//声明ztree初始化参数，配置id，pid，并设置点击事件
			var setting = {
				data: {
					key: {
						name: "name"
					},
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pid"
					}
				},
				check: {
					enable: true,
					autoCheckTrigger: true
				}
			};
			$.fn.zTree.init($("#module_auth_tree"), setting, modules);
			
		}else{
			showMsg(data.msg);
		}
	});
	$("#menu-dialog").modal();
}

function showMsg(msg){
	$("#alert-msg").html(msg);
	$("#my-msg").modal();
}

function delQuesion(id){
	var delPath = ctx+'/role/del';
	var depParam = {
			id:id
	}
	showConfirm("确认删除该条记录吗？",function(){
		commonAjax(delPath,depParam,function(data){
			if(data.success){
				createQuestion();
			}
			showMsg(data.msg);
		});
	});
}

