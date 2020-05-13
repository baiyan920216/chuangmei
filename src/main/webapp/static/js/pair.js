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
	createContent1();
}
function queryContent(pageNum){
	if(undefined!=pageNum){
		query.page = pageNum;
	}else{
		query.page = 1;
	}
	createContent();
}

var editIndex;
function toAddPair(sellerorderId){
	//页面层
	var editPath = ctx+'/toPayMoney';
	if(undefined != sellerorderId){
		editPath += '?id='+$("#id").val()+'&buyerorderid='+sellerorderId;
	}
	editIndex = layer.open({
        type: 2,
        title: '添加收款信息',
        shadeClose: true,
        area: ['400px','200px'],
        content: editPath,
        scrollbar: false
    });
}

function delOrderPair(id){
	
	var delPath = ctx+'/order/delOrderPair';
	var depParam = {
			id:id
	}
	showConfirm("确认删除该条记录吗？",function(){
		commonAjax(delPath,depParam,function(data){
			if(data.success){
				createContent1();
			}
			showMsg(data.msg);
		});
	});
}

function createContent1(){
	//已配对记录
	var path = ctx+"/order/orderPairList?sellerorderid="+$("#id").val();
	commonAjax(path,query,function(data){
		if(data.success){
			var pageStr = new Array; 
			$.each(data.data,function(index,row){
				pageStr.push('<tr>');
				pageStr.push('<td>'+(index+1)+'</td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.paymoney)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyeruname)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyername)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.pairtime)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyeralipay)+'</a></td>');
				pageStr.push(' <td>');
				pageStr.push('<div class="am-btn-toolbar">');
				pageStr.push(' <div class="am-btn-group am-btn-group-xs">');
				pageStr.push('  <button onclick="delOrderPair('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-trash-o"></span> 删除配对</button>');
				pageStr.push(' </div>');
				pageStr.push(' </div>');
				pageStr.push(' </td>');
				pageStr.push(' </tr>');
			});
			$("#pagelist1").html(pageStr.join(""));
			
		}else{
			showMsg(data.msg);
		}
	});
}

function createContent(){
	//通过预约匹配中
	var path = ctx+"/order/orderPairSellerList?status=1";
	commonAjax(path,query,function(data){
		if(data.success){
			var pageStr = new Array; 
			$.each(data.rows,function(index,row){
				pageStr.push('<tr>');
				pageStr.push('<td>'+(index+1)+'</td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.money)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.orderenddate)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyeruname)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyername)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyertel)+'</a></td>');
				pageStr.push('<td><a href="#">'+convertNullToStr(row.buyeralipay)+'</a></td>');
				pageStr.push('<td><a href="#">'+row.statusDes+'</a></td>');
				pageStr.push(' <td>');
				pageStr.push('<div class="am-btn-toolbar">');
				pageStr.push(' <div class="am-btn-group am-btn-group-xs">');
				pageStr.push('  <button onclick="toAddPair('+row.id+')" class="am-btn am-btn-default am-btn-xs am-text-firstly"><span class="am-icon-pencil-square-o"></span> 添加配对</button>');
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


