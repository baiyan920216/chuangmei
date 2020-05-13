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

function exportExcel(){
	
	var path = ctx+"/order/exportExcel";
	top.location.href = path;
	
//	showMsg("导出");
}

function queryContent(pageNum){
	if(undefined!=pageNum){
		query.page = pageNum;
	}else{
		query.page = 1;
	}
	query.query_name = $("#query_name").val();
	query.query_buyernum = $("#query_buyernum").val();
	query.query_alipay = $("#query_alipay").val();
	query.query_endtime = $("#query_endtime").val();
	createContent();
}

function orderPass(id){
	var editPath = ctx+'/order/switchOrderStatus';
	if(undefined != id){
		editPath += '?id='+id + '&status=' + 1;
	}
	switchStatus(editPath);
		
}


function cancelPass(id){
	var editPath = ctx+'/order/switchOrderStatus';
	if(undefined != id){
		editPath += '?id='+id + '&status=' + 0;
	}
	switchStatus(editPath);
		
}

function payMoney(id){
	var editPath = ctx+'/order/switchOrderStatus';
	if(undefined != id){
		editPath += '?id='+id + '&status=' + 3;
	}
	switchStatus(editPath);
	
}


function switchStatus(path){
	var query = {};
	commonAjax(path,query,function(data){
		if(data.success){
			showMsg(data.msg);
			createContent();
		}else{
			showMsg(data.msg);
		}
	});
}

function showOrder(id){
	var editPath = ctx+'/views/showOrder';
	if(undefined != id){
		editPath += '?id='+id;
	}
	window.location.href = editPath;
	
}

function createContent(){
	var path = ctx+"/order/list";
	commonAjax(path,query,function(data){
		if(data.success){
			var pageStr = new Array; 
			$.each(data.rows,function(index,row){
				pageStr.push('<tr>');
				pageStr.push('<td>'+(index+1)+'</td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.money)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.orderdate)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.orderenddate)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyeruname)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyername)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyertel)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyeraddress)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyersuportname)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyersuporttel)+'</a></td>');
				pageStr.push('<td><a href="#">'+row.statusDes+'</a></td>');
				pageStr.push(' <td>');
				pageStr.push('<div class="am-btn-toolbar">');
				pageStr.push(' <div class="am-btn-group am-btn-group-xs">');
				if(row.status == "0"){
					//预约中状态，显示预约通过按钮
					pageStr.push('  <button onclick="orderPass('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 通过预约</button>');
				}else if(row.status == "1"){
					//预约成功状态，取消按钮
					pageStr.push('  <button onclick="cancelPass('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 取消预约</button>');
				}
				
				pageStr.push('  <button onclick="showOrder('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-play"></span> 查看</button>');
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


