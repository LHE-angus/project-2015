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
<style type="text/css">
body {font-family: 'microsoft yahei',Verdana,Arial,Helvetica,sans-serif;}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
</head>
<body> 
<div class="mainbox">
<div class="maincont">  
    <div class="membertab3" id="div_info">
     <p style="margin-left:20px;margin-top:15px;font-size:16px;line-height:36px;"> 
     	${ecUser.real_name }您好，
     	<c:if test="${is_act eq 0 }"> 您的资料已经填写完成，审核通过,请重新登录系统。 </c:if>
     	<c:if test="${is_act eq 1 }"> 您的资料没有填写完整 ，请完善 >> <a href="${ctx }/wap/center/RegUser.do?">用户资料</a> </c:if>
     	<c:if test="${is_act eq 2 }"> 您的资料已经填写完成，等待审核。。。审核结果稍后将发送到您登记的邮箱<br/></c:if>
     	<c:if test="${is_act eq 3 }"> 您的资料审核不通过，请重新完善 >> <a href="${ctx }/wap/center/RegUser.do?method=edit">用户资料</a> </c:if>
     </p>
    </div>
</div>
</div> 
</body>   
</html>