<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开心猫 - ${entity.title }</title>
<link href="${ctx }/member/EcVoteMain/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/member/EcVoteMain/css/index.css" rel="stylesheet" type="text/css" /> 
</head>
<body>  
<div class="top2" onclick="location.href='?';" >
</div>
<div class="main">
<div class="description_cont" style="margin-top:15px;text-align:center;">
${entity.content }  
</div>
<div style="margin:20px;"  class="description_cont" style="text-align:center;">
<div class="description_tit">投票记录</div>
<c:set var="days" value="0" />
<c:set var="cdays" value="" /> 
<c:set var="c" value="0" />
<table width="80%" border="0" cellpadding="0" cellspacing="0" >
     <c:forEach items="${entity.ecVoteRecordList}" var="cur" varStatus="vs">
     <c:set var="c" value="${c+1 }" />
     	<c:if test="${cdays ne cur.map.days  }"> 
     	 <c:set var="c" value="${1 }" />
		<c:set var="cdays" value="${cur.map.days}" />
		<c:set var="days" value="${days+1 }" />
		<tr align="left" ><td colspan="3">  <c:if test="${cdays eq 0 }"> 今 天  </c:if><c:if test="${cdays ne 0 }"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy年MM月dd日"></fmt:formatDate></c:if></td> </tr>
		<tr align="center"><td>序号</td><td>投票人</td><td>时间</td></tr>
		</c:if>
     
     <tr align="center"><td>${c}</td><td>${cur.user_name}</td><td><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td></tr>
     </c:forEach>
</table>
</div>
</div>
</body>
</html>