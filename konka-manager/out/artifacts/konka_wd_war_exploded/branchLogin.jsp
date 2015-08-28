<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>康佳渠道管理系统</title>
<link href="${ctx}/styles/manager/login.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#ie6-warning {
	background:rgb(255,255,225) url("http://img.eincy.com/ie6-kill.gif") no-repeat scroll 3px center;
	position:absolute;
	top:0;
	left:0;
	font-size:12px;
	color:#333;
	width:97%;
	padding: 2px 15px 2px 23px;
	text-align:center;
}
#ie6-warning a {
	text-decoration:none;
}
</style>
</head>
<body>
<!--[if lte IE 6]> 
<div id="ie6-warning">您正在使用 Internet Explorer 6，在本页面的显示效果可能有差异。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器： <a href="http://www.mozillaonline.com/">Firefox</a>
</div> 
<script type="text/javascript"> 
function position_fixed(el, eltop, elleft){ 
// check if this is IE6 
if(!window.XMLHttpRequest) 
window.onscroll = function(){ 
el.style.top = (document.documentElement.scrollTop + eltop)+"px"; 
el.style.left = (document.documentElement.scrollLeft + elleft)+"px"; 
} 
else el.style.position = "fixed"; 
} 
position_fixed(document.getElementById("ie6-warning"),0, 0); 
</script> 
<![endif]-->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
    <td height="340" align="center" valign="bottom"><!--<p><img src="styles/manager/default/images/logo.png" width="393" height="45" /></p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>-->
      </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="20%">&nbsp;</td>
    <td><html-el:form action="/BranchLogin.do" method="post">
        <input type="hidden" name="method" id="login" value="login" />
        <input type="hidden" name="returnUrl" value="${af.map.returnUrl}" />
        <p>
          <label>用户名：</label>
          <html-el:text property="user_name" styleId="user_name" value="${user_name}" size="18" maxlength="32" style="width:146px;" tabindex="1" styleClass="login" />
        </p>
        <p>
          <label>密　码：</label>
          <html-el:password property="pass_word" styleId="pass_word" styleClass="login" size="18" maxlength="32" style="width:146px;" tabindex="2" />
        </p>
          <p id="tr_verificationCode">
            <label>验证码：</label>
            <html-el:text property="verificationCode" styleId="verificationCode" styleClass="code" size="4" maxlength="4" tabindex="3" />
            <span><img src="${ctx}/images/VerificationCode.jpg" width="69" style="height:21px;vertical-align:middle;border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /></span> </p>
        <p>
          <input type="checkbox" id="is_remember" name="is_remember" value="1" />
              <label for="is_remember"> 记住我的登录状态</label>
        </p>
        <div class="login_bar" style="width: 75px;height: 30px;margin:0 auto;padding-left:0px;">
          <input name="button" type="submit" class="sub" id="button" value=" " tabindex="4" />
        </div>
      </html-el:form></td>
    <td width="20%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="100">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div id="login_footer"> Copyright &copy; 2010 　 Inc. All Rights Reserved. </div></td>
    <td>&nbsp;</td>
  </tr>
</table>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	if ("" != "${is_remember}") {
		$("#is_remember")[0].rchecked = true;
	}
	
	$("#user_name" ).attr("dataType", "Require").attr("msg", "请填写您的用户名！");
	$("#pass_word"  ).attr("dataType", "Require").attr("msg", "请填写您的密码！");
	$("#verificationCode" ).attr("dataType" ,"Custom" ).attr("msg", "验证码必须是一个4位的数字").attr("regexp", "\\d{4}");
	
	$(document.forms[0]).submit(function(){
		return Validator.Validate(this, 1);
	})
});
//]]></script>
</body>
</html>