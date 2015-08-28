<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>${naviString}</title>
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div align="center" style="padding-top: 100px;">
<h1 style="font-weight: bold;font-size: 20px;">对不起，您不是康佳的代理商或者经销商！不能使用康佳进销存。</h1>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
