/*
 * 数值添加千分位
 * author Angus
 * add_date 2014-07-24 
 */
function commafy(num){
	   num = num+"";
	   if(/^.*\..*$/.test(num)){
	      var pointIndex =num.lastIndexOf(".");
	      var intPart = num.substring(0,pointIndex);
	      var pointPart =num.substring(pointIndex+1,num.length);
	      intPart = intPart +"";
	      var re =/(-?\d+)(\d{3})/
	      while(re.test(intPart)){
	          intPart =intPart.replace(re,"$1,$2")
	       }
	      num = intPart+"."+pointPart;
	   }else{
	      num = num +"";
	      var re =/(-?\d+)(\d{3})/
	      while(re.test(num)){
	          num =num.replace(re,"$1,$2")
	       }
	   }
	   return num;
}

//格式化
function formatCurrency(num,flag) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
	if(1==flag){
		return (((sign)?'':'-') + num + '.' + cents);
	}else{
		return (((sign)?'':'-') + num);
	}
}

/**
 * 点击跳转页面，先验证权限
 * @param msg
 * @param page
 * @param method
 * @param queryString
 * @return
 */
function doNeedMethod(msg, page, method, mod,queryString,per) {
	if(msg != null) {
		$.messager.confirm('温馨提示', msg, function(r){  
			if (!r){  
				return false;
			}  
		});
	}
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
	var str = "";
	if(per!=null){
		str = "&"+ $("#"+per).serialize();
	}
	//$.post("${ctx}/manager/admin/CsAjax.do?method=checkMod&mod_id=" + mod + "&per=" + per,function(result){
		
	location.href = page  + "method=" + method + "&mod_id=" +mod + "&" + encodeURI(queryString) + encodeURI(str);
		
	//},'json');
}

/**
 * 单纯跳转页面
 * @param msg
 * @param page
 * @param method
 * @param mod
 * @param queryString
 * @param per
 * @return
 */
function gotoPage(msg, page, method,queryString) {
	if(msg != null) {
		$.messager.confirm('温馨提示', msg, function(r){  
			if (!r){  
				return false;
			}  
		});
	}
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
	var str = "";
	if(queryString != null){
		str = "&" + queryString;
	}
	location.href = page  + "?" + encodeURI(queryString);
}


/**
 * 获取地址栏中的参数
 * @param name
 * @return
 */
function GetQueryString(name)
{
	var href = window.location.search;    //地址栏信息
	var arr = href.substr(1).split('&');   //根据‘&’字符拆分参数
	var res = '';
	jQuery.each(arr,function(index,da){
		var temp = da.split('=');
		if(temp[0]==name){
			res = temp[1];
		}
	});
	return res;
}

/**
 * 获取指定的参数的值
 * @param name
 * @returns {String}
 */
function GetFormString(source,name)
{
	var str = $("#"+source).val();    //隐藏域的值
	var arr = str.split('&');   //根据‘&’字符拆分参数
	var res = '';
	jQuery.each(arr,function(index,da){
		var temp = da.split('=');
		if(temp[0]==name){
			res = temp[1];
		}
	});
	return res;
}

/**
 * 校验时弹出提示，并将光标返回到未填写内容
 * @param msg
 * @param id
 * @return
 */
function getFocus(msg,id){
	$.messager.alert('温馨提示',msg,'info',function(r){
		$("#"+id).focus();
	});
}