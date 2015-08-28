<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登录 - 康佳进销存</title>
</head>
<body>
<div>
  <html-el:form action="/login">
    <html-el:hidden property="method" value="login" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="loginform">
      <tr>
        <td width="40%" height="30" align="right">用户名：</td>
        <td width="60%" height="30" colspan="2"><html-el:text property="username" value="${username}" maxlength="40" style="width:140px;" /></td>
      </tr>
      <tr>
        <td height="30" align="right">密　码：</td>
        <td height="30" colspan="2"><html-el:password property="pass" value="${pass}" maxlength="40" style="width:140px;" /></td>
      </tr>
      <tr>
        <td height="30" align="right">&nbsp;</td>
        <td height="30" colspan="2"><html-el:submit property="submit" value="登陆" />
          &nbsp;
          <html-el:reset property="reset" value="重置"></html-el:reset></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("input[name='username']").attr("datatype","Require").attr("msg","请填写您的用户名！");
	$("input[name='pass']").attr("datatype","Require").attr("msg","请填写您的密码！");
	
	$("form:first").submit(function(){
		var flag = Validator.Validate(this, 1);
		if(!flag){
			return false;
		}
		return flag;
	});
})
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
