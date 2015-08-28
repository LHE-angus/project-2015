<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳进销存系统</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<c:if test="${not empty af.map.shopIsNotBindKonkaR3}" var="notBind">
<body>
<div align="center" style="padding-top: 100px;">
<h1 style="font-weight: bold;font-size: 20px;">对不起，您不是康佳的代理商或者经销商！不能使用康佳进销存。</h1>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</c:if>
<c:if test="${not notBind}">
<frameset rows="64,*" cols="*" border="0" frameborder="0" framespacing="0">
  <frame src="IndexFrames.do?method=top&clickHrefName=leftJxcSellBill&keySeq=${af.map.keySeq}" name="indexTopFrame" scrolling="no" noresize="noresize" id="indexTopFrame" title="indexTopFrame" />
  <frame src="IndexFrames.do?method=main&toCustomerFrameJsp=mainFrameCustom&keySeq=${af.map.keySeq}" name="indexMainFrame" id="indexMainFrame" title="indexMainFrame" />
</frameset>
<script type="text/javascript">//<![CDATA[
if(self != top){
	top.location = self.location;
}
//]]></script>
</c:if>
</html>