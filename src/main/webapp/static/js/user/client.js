$(document).ready(function() {
	init();
	
});
//参数
var query = {
		page: 1,
		rows: 15,
		uname: "",
		name: "",
		alipay: "",
		tel: "",
		order: "time"
};
function init(){
	query.page = 1;
	queryUser(1);
}

function queryUser(pageNum){
	if(undefined!=pageNum){
		query.page = pageNum;
	}else{
		query.page = 1;
	}
	query.uname = $("#query_uname").val();
	query.name = $("#query_name").val();
	query.alipay = $("#query_alipay").val();
	query.tel = $("#query_tel").val();
	createQuery();
}

function lockUser(id){
	var editPath = ctx+'/user/switchLock';
	if(undefined != id){
		editPath += '?id='+id+'&locked=1';
	}
	switchLock(editPath);
}

function unlockUser(id){
	var editPath = ctx+'/user/switchLock';
	if(undefined != id){
		editPath += '?id='+id+'&locked=0';
	}
	switchLock(editPath);
}
function cancelUser(id){
	var editPath = ctx+'/user/switchLock';
	if(undefined != id){
		editPath += '?id='+id+'&locked=2';
	}
	switchLock(editPath);
}



function switchLock(path){
	var query = {};
	commonAjax(path,query,function(data){
		if(data.success){
			showMsg(data.msg);
			createQuery();
		}else{
			showMsg(data.msg);
		}
	});
}

function resetPass(uid){
	debugger
	var path = ctx+'/resetPass';
	var query = {uid:uid};
	commonAjax(path,query,function(data){
		if(data.success){
			showMsg(data.msg);
		}else{
			showMsg(data.msg);
		}
	});
}

function switchType(type){
	query.order = type;
	createQuery();
}

var editIndex;
function addCoin(uid){
	//页面层
	var editPath = ctx+'/toAddCoin';
	if(undefined != uid){
		editPath += '?uid='+uid;
	}
	editIndex = layer.open({
        type: 2,
        title: '分配创币',
        shadeClose: true,
        area: ['400px','200px'],
        content: editPath,
        scrollbar: false
    });
}

function createQuery(){
	var path = ctx+"/user/clientList";
	commonAjax(path,query,function(data){
		if(data.success){
			var pageStr = new Array; 
			$.each(data.rows,function(index,row){
				pageStr.push('<tr>');
				pageStr.push('<td>'+(index+1)+'</td>');
				pageStr.push('<td><a href="#" >'+convertNullToStr(row.uname)+'</a></td>');
				pageStr.push('<td><a href="#" >'+convertNullToStr(row.name)+'</a></td>');
				pageStr.push('<td><a href="#" >'+convertNullToStr(row.tel)+'</a></td>');
				pageStr.push('<td><a href="#" >'+convertNullToStr(row.alipay)+'</a></td>');
				pageStr.push('<td><a href="#" >'+convertNullToStr(row.supportid)+'</a></td>');
				pageStr.push('<td><a href="#" >'+rMap[row.roletype]+'</a></td>');
				pageStr.push('<td><a href="#" >'+convertNullToStr(row.isLock)+'</a></td>');
				pageStr.push('<td><a href="#" >'+convertNullToStr(row.activetime)+'</a></td>');
				pageStr.push('<td><a href="#" >'+convertNullToStr(row.address)+'</a></td>');
				pageStr.push(' <td>');
				pageStr.push('<div class="am-btn-toolbar">');
				pageStr.push(' <div class="am-btn-group am-btn-group-xs">');
				if(row.locked==0){
					pageStr.push('  <button onclick="cancelUser('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span>取消激活</button>');
					pageStr.push('  <button onclick="lockUser('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 锁定</button>');
					pageStr.push('  <button onclick="addCoin('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 分配创币</button>');
				}else if(row.locked==1){
					pageStr.push('  <button onclick="unlockUser('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 解锁</button>');
				}else if(row.locked==2){
					pageStr.push('  <button onclick="unlockUser('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 激活</button>');
				}
				pageStr.push('  <button onclick="resetPass('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-danger "><span class="am-icon-trash-o"></span> 重置密码</button>');
				pageStr.push('  <button onclick="delUser('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-danger "><span class="am-icon-trash-o"></span> 删除</button>');
				
				pageStr.push(' </div>');
				pageStr.push(' </div>');
				pageStr.push(' </td>');
				pageStr.push(' </tr>');
			});
			$("#pagelist").html(pageStr.join(""));
			
			var page_options = {
					id:"user_page",
					pageCount:data.pageCount,
					current:data.pageNumber,
					callback:"queryUser"
			};
			pages(page_options);
			
			//
			$("#desc_code").html(data.code);
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
