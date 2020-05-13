function commonAjax(path,params,func){
	$.ajax({
		type:"post",
		async:false,
		url:path, 
		data:params,
		dataType:"json",
		success:function(data) {
			func(data);
		}
	});
}

function showConfirm(msg,okCallback){
	if(confirm("确认删除该条记录吗？")){
		okCallback();
	}
}
/*
 * 转换null为""
 */
function convertNullToStr(obj){
	if(obj === undefined || obj === null || obj === ""){
		return "";
	}else{
		return obj;
	}
}

function pages(options){
	if(options.pageCount==null||options.pageCount<1){return;}
	var pageHtml = new Array;
		pageHtml.push('<ul data-am-widget="pagination" class="am-pagination am-pagination-select">');
		pageHtml.push('<li class="am-pagination-prev ">');
		pageHtml.push(' <a href="javascript:'+options.callback+'('+1+')" class="">首页</a>');
		pageHtml.push(' </li>');
		pageHtml.push(' <li class="am-pagination-select">');
		pageHtml.push('    <select onchange="'+options.callback+'(this.value)">');
		for (var i = 1; i <= options.pageCount; i++) {
			if(i==options.current){
			pageHtml.push('     <option value="'+i+'" class="" selected>'+i+' / '+options.pageCount+'</option>');
			}else{
			pageHtml.push('     <option value="'+i+'" class="">'+i+' / '+options.pageCount+'</option>');
			}
		}
		pageHtml.push('    </select>');
		pageHtml.push('  </li>');
		pageHtml.push('  <li class="am-pagination-next ">');
		pageHtml.push('    <a href="javascript:'+options.callback+'('+options.pageCount+')" class="">末页</a>');
		pageHtml.push('  </li>');
		pageHtml.push('</ul>');
		$("#"+options.id).html(pageHtml.join(""));
	}

//获取地址栏的参数数组
function getUrlParams()
{
    var search = window.location.search ; 
    // 写入数据字典
    var tmparray = search.substr(1,search.length).split("&");
    var paramsArray = new Array; 
    if( tmparray != null)
    {
        for(var i = 0;i<tmparray.length;i++)
        {
            var reg = /[=|^==]/;    // 用=进行拆分，但不包括==
            var set1 = tmparray[i].replace(reg,'&');
            var tmpStr2 = set1.split('&');
            var array = new Array ; 
            array[tmpStr2[0]] = tmpStr2[1] ; 
            paramsArray.push(array);
        }
    }
    // 将参数数组进行返回
    return paramsArray ;     
}

// 根据参数名称获取参数值
function getParamValue(name)
{
    var paramsArray = getUrlParams();
    if(paramsArray != null)
    {
        for(var i = 0 ; i < paramsArray.length ; i ++ )
        {
            for(var  j in paramsArray[i] )
            {
                if( j == name )
                {
                    return paramsArray[i][j] ; 
                }
            }
        }
    }
    return null ; 
}
