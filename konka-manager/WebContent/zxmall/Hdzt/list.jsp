<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>康佳</title>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/base.css"/>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/index.css"/> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main">
<div class="w1200">
	 <div id="content" style="margin:0 auto; min-height:450px;align:center;width:1160px;"> 
	 <c:if test="${empty entity}"><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><div id="huodong" align="center"><b>抱歉，本期暂无活动 </b></div></c:if>
	 <c:if test="${!empty entity}">${entity.content }</c:if>
	 </div>
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" /> 
</body>
</html>