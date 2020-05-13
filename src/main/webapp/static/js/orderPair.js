$(document).ready(function() {
	init();
	
});

//参数
var query = {
		page: 1,
		rows: 15
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
	query.query_name = $("#query_name").val();
	query.query_buyernum = $("#query_buyernum").val();
	query.query_alipay = $("#query_alipay").val();
	query.query_endtime = $("#query_endtime").val();
	createContent();
}

function toOrderPair(id){
	var editPath = ctx+'/views/toOrderPair';
	if(undefined != id){
		editPath += '?id='+id;
	}
	window.location.href = editPath;
		
}

var editIndex;
function andOrder(){
	//页面层
	var editPath = ctx+'/toAddOrder';
	editIndex = layer.open({
        type: 2,
        title: '添加收款订单',
        shadeClose: true,
        area: ['400px','200px'],
        content: editPath,
        scrollbar: false
    });
}

function delOrder(id){
	
	var delPath = ctx+'/order/delOrder';
	var depParam = {
			id:id
	}
	showConfirm("确认删除该条记录吗？",function(){
		commonAjax(delPath,depParam,function(data){
			if(data.success){
				createContent();
			}
			showMsg(data.msg);
		});
	});
}

function createContent(){
	//已上传付款凭证
	var path = ctx+"/order/orderPairSellerList?status=3";
	commonAjax(path,query,function(data){
		if(data.success){
			var pageStr = new Array; 
			$.each(data.rows,function(index,row){
				pageStr.push('<tr>');
				pageStr.push('<td>'+(index+1)+'</td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.money)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.receivemoney)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.orderenddate)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyeruname)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyername)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyertel)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyeralipay)+'</a></td>');
				pageStr.push('<td><a href="#">'+row.statusDes+'</a></td>');
				pageStr.push(' <td>');
				pageStr.push('<div class="am-btn-toolbar">');
				pageStr.push(' <div class="am-btn-group am-btn-group-xs">');
				if(row.status == "3"){
					//预约中状态，显示预约通过按钮
					pageStr.push('  <button onclick="toOrderPair('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 配对</button>');
				}
				if(row.money == 0){
					//预约中状态，显示预约通过按钮
					pageStr.push('  <button onclick="delOrder('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-trash-o"></span> 删除</button>');
				}
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


