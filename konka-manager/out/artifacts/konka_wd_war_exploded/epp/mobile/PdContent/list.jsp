<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />   
<style>
img, object {width: 100%;}
</style>
</head>
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3><c:if test="${af.map.type eq 1 }">图文详情</c:if><c:if test="${af.map.type eq 2 }">规格参数</c:if></h3></div>
	<div id="content">
		<div class="mainbox">
			<div class="maincont"> 
				<c:out value="${kbpc.content}" escapeXml="false" /> 
		   		<div class="clear"></div>
		    </div>
			<div class="clear"></div>
		</div>
	</div> 
</body>
</html>