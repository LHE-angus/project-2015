<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户端登录2——康佳渠道管理系统</title>
<!--<link href="${ctx}/styles/login.css" rel="stylesheet" type="text/css" />-->
<link rel="stylesheet" type="text/css" href="${ctx}/styles/customer/login/css/global.css" />
<style type="text/css">
html {
	overflow: hidden;
}
</style>
</head>
<body style="font-family:Microsoft YAHEI;">
<div style="width:100%;height:80px;background-color:#FFF;">
  <div style="width:1030px; background-color: #FFF; z-index: 99; height: 80px; margin: 0 auto;">
    <div class="top"><img src="${ctx}/styles/customer/login/images/07.jpg" width="496" height="34" /></div>
  </div>
</div>
<div style="margin:0 auto; width:1030px; background-color: #FFF;">
  <div class="bj">
    <div class="dd">
      <html-el:form action="/login" method="post">
        <html-el:hidden property="method" value="login" />
        <html-el:hidden property="url" value="${url}" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tdTab">
          <tr>
            <td width="79%">用户名：</td>
            <td width="21%">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2"><html-el:text property="user_name" styleId="user_name" value="${user_name}" size="30" maxlength="32" tabindex="1" styleClass="webinput" /></td>
          </tr>
          <tr>
            <td>密码：</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2"><html-el:password property="password" value="${password}" styleId="password" styleClass="webinput" size="30" maxlength="32" tabindex="2" /></td>
          </tr>
          <c:if test="${isEnabledCode eq '1'}">
            <tr id="tr_verificationCode">
              <td width="79%">验证码：</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2"><input type="text" name="verificationCode" id="verificationCode" class="webinput" maxlength="4" style="width:80px;" />
                <img src="${ctx}/images/VerificationCode.jpg" width="60" height="21" src="${ctx}/images/VerificationCode.jpg" style="border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /></td>
            </tr>
          </c:if>
          <tr>
            <td style="padding-top:5px"><label>
                <input type="checkbox" name="is_remember" value="1" id="is_remember" style="vertical-align:middle;padding:0;margin:0 3px 3px 0 " />
                两周内保存我的信息</label></td>
            <td>&nbsp;</td>
          </tr>
        </table>
        <html-el:button styleId="btn_submit" property="btn_submit" styleClass="websub" value="登 录" style="cursor:pointer;" />
      </html-el:form>
    </div>
    <div class="denglu">© 康佳集团 版权所有 KONKA 2013.All Rights Reserved  联系电话：4008873200</div>
  </div>
</div>
<!--<div class="clear"></div>--> 

<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	browserRedirect();
	$("#user_name").attr("dataType", "Require").attr("msg", "请填写您的用户名！");
	$("#password").attr("dataType", "Require").attr("msg", "请填写您的密码！");
	if ("${isEnabledCode}" == "1") {
		$("#verificationCode" ).attr("dataType" ,"Custom" ).attr("msg", "验证码必须是一个4位的数字").attr("regexp", "\\d{4}");
	} 

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			this.form.submit();
		}
	});
//	$(document.forms[0]).submit(function(){
//		return Validator.Validate(this, 1);
//	})
	
	if ("true" == "${not empty (is_remember)}") {
		$("#is_remember")[0].checked = true;
	}
}); 
function browserRedirect() {  
    var sUserAgent = navigator.userAgent.toLowerCase();  
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";  
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";  
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";  
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";  
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";  
    var bIsAndroid = sUserAgent.match(/android/i) == "android";  
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";  
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    if (bIsAndroid){
        window.location.href="${ctx}/mobile";
    } 
}  
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
