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
<html-el:form action="Search.do" method="post">
	<input type="hidden" name="method" value="list" />
	<input type="hidden" name="id" value="${af.map.pwdId}" />
	<input type="hidden" name="id_card_no" value="${af.map.id_card_no}" />
	<table width="100%" border="0" cellpadding="5" cellspacing="0" class="loginTab" style="width:100%;">
	  	<tr>
	    	<th valign="middle" align="center" colspan="2">修改密保邮箱</th>
	  	</tr>
	  	<tr>
	    	<td colspan="2" align="center"><span class="success">恭喜您，修改成功！</span></td>
	  	</tr>
	  	<tr>
		    <td width="35%" align="right">身份证号：</td>
		    <td><span class="b">${af.map.id_card_no}</span></td>
	  	</tr>
	  	<tr>
		    <td align="right">密保邮箱：</td>
		    <td>${af.map.email}（新）</td>
	  	</tr>
	  	<tr>
	    	<td align="left" colspan="2"><span style="color:#F00;font-weight:700;">　　请务必牢记您的密保邮箱，在您忘记密码时，您可以通过密保邮箱找回您的密码。</span></td>
	  	</tr>
	  	<tr>
	    	<td colspan="2" align="center">
	    		<input type="button" name="btn_submit" id="btn_submit" class="loginBtn" value=" 马上查询工资 " />
	    		<input type="button" name="returnBtn" value=" 返回 " onclick="history.back();" />
	    	</td>
	  	</tr>
	  	<tr>
	    	<td colspan="2" align="left">&nbsp;</td>
	  	</tr>
	</table>
</html-el:form>
<div class="bottom">康佳集团 版权所有 KONKA 2010.All Rights Reserved</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_submit").click(function(){
		this.form.submit();
	});
}); 
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>