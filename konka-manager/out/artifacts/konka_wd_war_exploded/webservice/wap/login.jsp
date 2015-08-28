<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/login.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/epp/mobile/js/pptBox.js"></script> 
<style type="text/css">
.wap_logo{padding: 46px 0px 20px;}
</style> 
</head>
<body><div class="top_class"><span class="lspan"><a href="<c:url value='/webservice/wap/Index.do?'/>"><img style="margin-left:-5px;margin-top:-10px;" alt="触网" src="${ctx}/styles/epp/mobile/images/wap_logo_k.png" width="40" height="39" /></a></span><h3>用户登录</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>触网</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="new-tbl-type">
	<a href="<c:url value='/webservice/wap/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
	<a href="<c:url value='/webservice/wap/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
	<a href="<c:url value='/webservice/wap/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
	<a href="<c:url value='/webservice/wap/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content"> 
<div class="mainbox">
<div class="maincont">  
<form action="${ctx }/webservice/wap/login.do?method=login" method="post" id="login_form">
<br><p style="font-size:22px;text-align:center;">登录触网直销平台</p><br>
<!--  div class="wap_logo"><img alt="康佳触网直销平台" src="${ctx}/styles/epp/mobile/images/wap_logo.gif" /></div-->
<div class="loginli"><input type="text" name="user_name" id="user_name" value="${user_name }"  onkeyup="FtoH(this);"  onfocus="if(value=='用户名') {value=''}" onblur="if (value=='') {value='用户名'}" class="input_user"  /></div>
<div class="loginli"><input type="password" name="password" id="password" type="text" value="${password }" onkeyup="FtoH(this);" class="input_password"  /></div>
<div class="loginli2"><input type="button" id="btn_sub" class="but_login" value="进入系统" /></div>
<div class="loginli3">
<table style="width:100%">
<tr>
<td width="57%">&nbsp;&nbsp;<input name="is_remember" type="checkbox" id="is_remember" value="1" checked /><label for="is_remember">记住密码</label></td>
<td width="43%"> </td>
</tr>
</table>
</div></form>
<div class="loginli4">康佳集团 版权所有 KONKA 2015.All Rights Reserved</div>
</div>
</div>
</div>  
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#user_name").attr("datatype", "Require").attr("msg", "请输入用户名！");
	$("#password" ).attr("datatype", "Require").attr("msg", "请填写密码！");
	$("#user_name").focus(function() {
	    //获得焦点时，如果值为默认值，则设置为空
	    if (this.value == vdefault) {
	        this.value = "";
	        this.style.color='#333';
	    }
	}).blur(function() {
	    //失去焦点时，如果值为空，则设置为默认值
	    if (this.value == "") {
	        this.value = vdefault;
	        this.style.color='#999999';
	    }
	    
	  	//begin 转化全角为半角
		$("#user_name").val($.trim(DBC2SBC(this.value)));
		//end 
	});

	$("#password").focus(function() {
	    //获得焦点时，如果值为默认值，则设置为空
	    if (this.value == vdefault) {
	        this.value = "";
	        this.style.color='#333';
	    }
	}).blur(function() {
	    //失去焦点时，如果值为空，则设置为默认值
	    if (this.value == "") {
	        this.value = vdefault;
	        this.style.color='#999999';
	    }
	    
	  	//begin 转化全角为半角
		$("#password").val($.trim(DBC2SBC(this.value)));
		//end 
	});
	
	$(document).delegate("#btn_sub", "click", function(){
		if($("#user_name").val()=='' ||$("#user_name").val()=='用户名'){
			alert('请输入用户名');
		}else if($("#password").val()==''){
			alert('请输入密码');
		}else{  
			$("#login_form").submit();
		}
	});
	
	// 记住密码回显
	if ("true" == "${not empty is_remember}") {
		$("#is_remember")[0].checked = true;
	}
	
	document.onkeydown = function(e){
           var ev = document.all ? window.event : e;    
           if(ev.keyCode==13) {  
        	 	if($("#user_name").val()=='' ||$("#user_name").val()=='用户名'){
	       			alert('请输入用户名');
	       		}else if($("#password").val()==''){
	       			alert('请输入密码');
	       		}else{
	       			$("#btn_sub").attr("disabled", true);
	       			$("#login_form").submit();
	       		}
           }
	}
});

//全角转换成半角
function DBC2SBC(str){
    var result = '';
    for (i=0 ; i<str.length; i++){
        code = str.charCodeAt(i);//获取当前字符的unicode编码
        if (code >= 65281 && code <= 65373){//在这个unicode编码范围中的是所有的英文字母已及各种字符
   			result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
  		}else if (code == 12288){//空格
   			result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
  		}else{
   			result += str.charAt(i);
  		}
   }
   return result;
}
function FtoH(obj){ 
	var str=obj.value; 
	var result="";
	for (var i = 0; i < str.length; i++){ 
		if (str.charCodeAt(i)==12288){ 
			result+= String.fromCharCode(str.charCodeAt(i)-12256); 
			continue;
		}
		if (str.charCodeAt(i)>65280 && str.charCodeAt(i)<65375){
			result+= String.fromCharCode(str.charCodeAt(i)-65248); 
		}else{ 
			result+= String.fromCharCode(str.charCodeAt(i)); 
		}
	}
	obj.value=result; 
} 

function exit(){
	location.href = "${ctx}/webservice/wap/login.do?method=logout";
} 
function showNav(){  
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
} 
//]]></script>                                          
</body>
</html>
