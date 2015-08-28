<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>开心猫 - 会员中心登录验证</title>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
</head>
<body>
<html-el:form action="/Skip" >
<html-el:hidden property="method" styleId="method" value="save" /> 
<html-el:hidden property="mod_id" value="${mod_id}" /> 
<p style="color:#fc7200;margin-left:20px;margin-top:15px;font-size:22px;">会员中心登录验证</p>
<table style="100%" >
	<tr>
		<td width="140" align="right" height="45">请输入会员卡号：</td>
		<td align="left"><input class="input_txt" style="width:120px;" name="user_name" type="text" id="user_name" maxlength="50" value="${user_name}" /></td>
	</tr> 
	<tr>
		<td align="right" height="45">登陆密码：</td>
		<td align="left"><input class="input_txt" style="width:120px;"   name="password" type="password" id="pwd" maxlength="50" value="${password}" /></td>
	</tr> 
</table>
        
<p style="margin-left:120px;margin-top:15px;font-size:16px;">  <input type="button" name="Submit4" id="btn_submit" value=" 确 认 " /></p> 
</html-el:form>
</body> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){   		
$("#user_name").attr("dataType", "Require").attr("msg", "请输入会员卡号！");  
$("#password").attr("dataType", "Require").attr("msg", "请输入密码！");  		
$("#btn_submit").click(function(){ 
	var isSubmit = Validator.Validate(this.form,3);
	if (isSubmit) {
		$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
		this.form.submit();
	}
});
});

//]]></script>
</html>
