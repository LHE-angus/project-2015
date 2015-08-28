<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/login.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
</head>
<body><form action="${ctx }/wap/login.do?method=login" method="post" id="login_form">
<div class="wap_logo"><img alt="开心猫内部购机优惠平台" src="${ctx}/styles/epp/mobile/images/wap_logo.gif" /></div>
<div class="loginli"><input type="text" name="user_name" id="user_name" value="${user_name }" onfocus="if(value=='用户名') {value=''}" onblur="if (value=='') {value='用户名'}" class="input_user"  /></div>
<div class="loginli"><input type="password" name="password" id="password" type="text" value="${password }"  class="input_password"  /></div>
<div class="loginli2"><input type="button" id="btn_sub" class="but_login" value="进入系统" /></div>
<div class="loginli3">
<table style="width:100%">
<tr>
<td width="57%">&nbsp;&nbsp;<input name="is_remember" type="checkbox" id="is_remember" value="1"  /><label for="is_remember">记住密码</label></td>
<td width="43%"> </td>
</tr>
</table>
</div></form>
<div class="loginli4">康佳集团 版权所有 KONKA 2013.All Rights Reserved</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#user_name").attr("datatype", "Require").attr("msg", "请输入用户名！");
	$("#password" ).attr("datatype", "Require").attr("msg", "请填写密码！");
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
 
//]]></script>                                          
</body>
</html>
