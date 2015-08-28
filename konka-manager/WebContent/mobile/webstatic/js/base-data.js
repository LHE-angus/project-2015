/**
 * @desc 下面定义通用对象
 */

//主机地址
var host_url = "http://qdgl.konka.com";
//var host_url = "http://127.0.0.1:8080/konka-wd";

// 定义是否启用cookie
var cookie_enable = true; // true-启用，false-不启用

/*
 * @desc 下面定义通用函数
 */

// 根据用户名和密码初始化门店
function store_init(username, userpass){
	$.ajax({
		type: "POST",
		url: host_url + "/MobileList.do?method=GetList01&from_html=1",
		data: { "username":encodeURI(username), "userpass":encodeURI(userpass), "timestamp":new Date().getTime() },
		dataType: "jsonp",
		jsonp: "jsonpcallback",
		error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		success: function(result) {
		  if (result.status == "-1"){
			  $.dialog({
				    content: result.msg,
				    max: false, min: false, fixed: true, lock: true, init: function () { var that = this, i = 6; var fn = function () { that.title(i + '秒后关闭！'); !i && that.close(); i--; }; timer = setInterval(fn, 1000); fn(); }, close: function () { clearInterval(timer); }
			  });
	      } else {
	    	  $("#store_id").empty();
	  		  //$("#store_id").append("<option value=''>-请选择-</option>");
	  		  for(var i = 0; i < result.length; i++)
	  			$("#store_id").append("<option value='" + result[i].id + "'>" + result[i].name + "</option>");
		  }
		}
	});
}


//读取参数
function getQueryString(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
   var r = window.location.search.substr(1).match(reg);
   if (r != null) return unescape(r[2]); return null;
}

//正则表达式：只能输入数字
function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		//if(obj.value.length == 0) obj.value = "0";
	});
}

//正则表达式：只能输入数字
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		//if(obj.value.length == 0) obj.value = "0";
	});
}

//获取某年某月的最后一天,,year=2012,month=5
function getLastDay(year, month){  
	 var dt = new Date(year, month - 1, '01');  
	 dt.setDate(1);  
	 dt.setMonth(dt.getMonth() + 1);  
	 cdt = new Date(dt.getTime()-1000*60*60*24);  
	 return cdt.getDate();
 //alert(cdt.getFullYear()+"年"+(Number(cdt.getMonth())+1)+"月月末日期:"+cdt.getDate()+"日");   
} 