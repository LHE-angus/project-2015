<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/touch/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/touch/css/epp.css" />  
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/slider.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<!-- top start -->
<div class="topbox">
<jsp:include page="/touch/__inc/top.jsp" flush="true" />
<jsp:include page="/touch/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/touch/__inc/nav.jsp" flush="true" />
<!-- top end -->   
<!-- first end --> 

<!-- second start -->
<div class="maincont margintop10">
<div id="content" style="margin:20px 10px 20px 10px; min-height:450px;">
 ${entity.content }
</div> 
</div> 
<jsp:include page="../__inc/footer.jsp" flush="true" /> 
</body>
</html>