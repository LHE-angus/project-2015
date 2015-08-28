<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>康佳渠道管理系统</title>
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/admin-index/css/css.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/admin-index/css/global.css" />
</head>
<c:if test="${gotoUserPos eq 1}">
<c:redirect url="/customer/manager/JxcUserPos.do"/>
</c:if>
<body style="font-family:Microsoft Yahei;"> 
<div style="margin:10px 10px;display:${count eq 0 ? 'none' : ''}">
	您好，您有<a href="${ctx}/customer/manager/JSubSellConfirm.do?mod_id=104020000" style="font-weight:bold;color:#f00;">&nbsp;${count}&nbsp;</a>条分销确认信息没有确认，请尽快处理！
</div>
<h1 style="margin:140px auto;font-style:italic;text-align:center;color:#F5A9A9;font-size:20px;font-family:微软雅黑,宋体;">欢迎您登录康佳客户端</h1>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(function(){
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
