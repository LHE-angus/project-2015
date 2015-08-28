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
<body><form action="${ctx }/epp/weixin/Skip.do?method=bind" method="post" id="login_form">
<input type="hidden" name="openid" id="openid" value="${snsToken.openid }" />
<input type="hidden" name="access_token" id="access_token" value="${snsToken.access_token }" />
<input type="hidden" name="refresh_token" id="refresh_token" value="${snsToken.refresh_token }" />
<input type="hidden" name="expires_in" id="expires_in" value="${snsToken.expires_in }" />
<div class="wap_logo"><img alt="开心猫内部购机优惠平台" src="${ctx}/styles/epp/mobile/images/wap_logo.gif" /></div>
<div class="loginli" id="bind_msg">用户绑定 </div>
<div class="loginli"><input type="text" name="user_name" id="user_name" value="用户名" onfocus="if(value=='用户名') {value='';}" onblur="if (value=='') {value='用户名';}" class="input_user"  /></div>
<div class="loginli"><input type="password" name="password" id="password" type="text" value="" class="input_password"  /></div>
<div class="loginli2"><input type="button" id="btn_sub" class="but_login" value="绑定" /></div>
<div class="loginli3"> </div>
</form>
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
			$("#btn_sub").attr("disabled", true);
			$("#login_form").submit();
		}
	});	 
	
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
