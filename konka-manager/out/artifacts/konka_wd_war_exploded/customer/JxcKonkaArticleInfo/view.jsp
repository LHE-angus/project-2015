<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <h2 align="center" style="font-size:20px; margin:20px auto;"><strong class="fb">${entity.title}</strong></h2>
  <div style="text-align:center;"> 浏览次数：${entity.view_counts}次  &nbsp; &nbsp; 发布时间：
    <fmt:formatDate value="${entity.pub_date}" pattern="yyyy-MM-dd HH:mm:ss" />
  </div>
  <c:if test="${not empty entity.img_path}">
    <div style="text-align:center;"> <img src="${ctx}/${fn:substringBefore(entity.img_path, '.')}_240.jpg" title="${entity.img_desc}" />
      <c:if test="${not empty entity.img_desc}"> <br />
        <c:out value="${entity.img_desc}" />
      </c:if>
    </div>
  </c:if>
  <div class="rtabcont2">
    <c:out value="${entity.content}" escapeXml="false" />
  </div>
  <c:if test="${not empty attachmentList}">
    <div class="rtabcont2">
      <c:forEach var="cur" items="${attachmentList}" varStatus="vs"> ${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a><br />
      </c:forEach>
    </div>
  </c:if>
</div>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
