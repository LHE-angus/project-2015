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
<html-el:form action="Email.do" method="post">
	<input type="hidden" name="method" value="save" />
	<input type="hidden" name="pwdId" value="${af.map.pwdId}" />
	<input type="hidden" name="id_card_no" value="${af.map.id_card_no}" />
	<table width="100%" border="0" cellpadding="5" cellspacing="0" class="loginTab" style="width:100%;">
	  	<tr>
	    	<th valign="middle" align="center" colspan="2">修改密保邮箱</th>
	  	</tr>
	  	<tr>
	    	<td colspan="2">&nbsp;</td>
	  	</tr>
	  	<tr>
		    <td width="35%" align="right">身份证：</td>
		    <td><span class="b">${af.map.id_card_no}</span></td>
	  	</tr>
	  	<tr>
		    <td align="right">原邮箱：</td>
		    <td>
		    	<input type="text" name="old_email" id="old_email" size="20" maxlength="40" class="input" style="width:62%;" placeholder="请填写原邮箱地址" /><br />
	    		<span class="note">必须为8位以上字母和数字的组合。</span>
	    	</td>
	  	</tr>
	  	<tr>
		    <td align="right">新邮箱：</td>
		    <td><input type="text" name="new_email" id="new_email" size="20" maxlength="40" class="input" style="width:62%;" placeholder="请填写新邮箱地址" /></td>
	  	</tr>
	  	<tr>
		    <td align="right">密　码：</td>
		    <td><input type="password" name="pwd" id="pwd" size="20" maxlength="40" class="input" style="width:62%;" placeholder="请填写密码" /></td>
	  	</tr>
	  	<tr>
		    <td align="right">验证码：</td>
		    <td valign="middle">
		    	<input type="text" name="verificationCode" style="width:67px;" class="input" id="verificationCode" size="5" maxlength="4" />
		    	&nbsp;
		    	<img src="${ctx}/images/VerificationCode.jpg" width="60" height="21" style="border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="this.src += '?' + new Date().getTime();" />
			</td>
	  	</tr>
	  	<tr>
		    <td colspan="2" align="center">
		    	<html-el:button property="btn_submit" styleId="btn_submit" value=" 提 交 " styleClass="updateBTN" />
		    	<input name="returnBtn" type="button" value=" 返 回 " onclick="history.back();" />
		    </td>
	  	</tr>
	  	<tr>
	    	<td colspan="2" align="left">&nbsp;</td>
	  	</tr>
	</table>
</html-el:form>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#old_email").attr("dataType", "Require").attr("msg", "请填写原邮箱！");
	$("#new_email").attr("dataType", "Require").attr("msg", "请填写新邮箱！");
	$("#pwd").attr("dataType", "Require").attr("msg", "请填写密码！");
	//$("#repeat").attr("datatype","Repeat").attr("to", "new_pwd").attr("msg","两次输入的密码不一致！");
	$("#verificationCode" ).attr("dataType" ,"Custom" ).attr("msg", "验证码必须是一个4位的数字").attr("regexp", "\\d{4}");
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			this.form.submit();
		}
	});
}); 
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>