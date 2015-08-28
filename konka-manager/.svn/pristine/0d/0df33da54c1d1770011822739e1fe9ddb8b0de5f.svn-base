<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户端登录——康佳渠道管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/wage/default.css" />
<style type="text/css">
input,textarea,select,file,button{font-family:Microsoft Yahei;font-size:12px;}
</style>
</head>
<body>
<html-el:form action="/login" method="post">
	<html-el:hidden property="method" value="login" />
	<table width="100%" border="0" cellpadding="5" cellspacing="0" class="loginTab" style="width:100%;">
	  <tr>
	    <th valign="middle" align="center" colspan="2">康佳分公司员工工资查询系统</th>
	  </tr>
	  <tr>
	    <td colspan="2">&nbsp;</td>
	  </tr>
	  <tr>
	    <td width="40%" align="right">身份证：</td>
	    <td><html-el:text property="id_card_no" styleId="id_card_no" styleClass="input" size="20" maxlength="18" style="width:62%" /></td>
	  </tr>
	  <tr>
	    <td align="right">密　码：</td>
	    <td><html-el:password property="pwd" styleId="pwd" styleClass="input" size="20" maxlength="40" style="width:62%;" /></td>
	  </tr>
	  <tr>
	    <td align="right">验证码：</td>
	    <td valign="middle"><input type="text" name="verificationCode" style="width:67px;" class="input" id="verificationCode" size="5" maxlength="4" />
	    &nbsp;
	    <img src="${ctx}/images/VerificationCode.jpg" width="60" height="21" style="border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="this.src += '?' + new Date().getTime();" /></td>
	  </tr>
	  <tr>
	    <td colspan="2" align="center"><html-el:button property="btn_submit" styleId="btn_submit" value=" 登 录 " styleClass="loginBtn" />&nbsp;<a href="#" id="t1">忘记密码？</a></td>
	  </tr>
	  <tr>
	    <td colspan="2" align="left">注：首次登陆请使用默认密码"888888"，登录后必须修改您的密码。</td>
	  </tr>
	</table>
</html-el:form>
<div class="bottom">康佳集团 版权所有 KONKA 2010.All Rights Reserved</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#id_card_no").attr("dataType", "Require").attr("msg", "请填写身份证号！");
	$("#pwd").attr("dataType", "Require").attr("msg", "请填写密码！");
	if ("${isEnabledCode}" == "1") {
		$("#verificationCode" ).attr("dataType" ,"Custom" ).attr("msg", "验证码必须是一个4位的数字").attr("regexp", "\\d{4}");
	}

	$("#t1").click(function(){
		alert("请联系管理员，联系电话0755-26608866-6303！");
	});
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			$(document.forms[0]).submit();
		};
	});
//	$(document.forms[0]).submit(function(){
//		return Validator.Validate(this, 1);
//	});
}); 
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>