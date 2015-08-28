<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>康佳进销存系统</title>
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){
	  <c:if test="${isLocal}">
	   location.href = "Frames.do?goToLeftMethod=leftJxcSellBill&clickHrefName=xsdj&toCustomerFrameJsp=mainFrameCustom&keySeq=${JXC_TEST_KEY_VALUE}";
	  </c:if>
	  <c:if test="${not isLocal}">
	  location.href = "Frames.do?goToLeftMethod=leftJxcSellBill&clickHrefName=xsdj&toCustomerFrameJsp=mainFrameCustom&keySeq=${af.map.keySeq}";
	  </c:if>
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>