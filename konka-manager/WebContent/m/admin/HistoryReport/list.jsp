<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>零售通-康佳电器</title>
<link rel="stylesheet" href="${ctx}/m/jqm/jquery.mobile-1.3.1.min.css" />
<script src="${ctx}/m/jqm/jquery-1.9.1.min.js"></script>
<script src="${ctx}/m/jqm/jquery.mobile-1.3.1.min.js"></script>
</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline" data-theme="b">
    <h1>历史上报数据</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a> <a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a> </div>
  <div data-role="content" data-theme="b">
    <div class="content-primary">
      <ul data-role="listview" data-inset="true">
        <c:forEach var="cur" items="${baseList}" varStatus="vs">
          <li data-role="list-divider">
            <c:choose>
              <c:when test="${cur.type_id eq 1}">销售数据</c:when>
              <c:when test="${cur.type_id eq 2}">退货数据</c:when>
              <c:when test="${cur.type_id eq 3}">终端物料</c:when>
              <c:when test="${cur.type_id eq 4}">竞品数据</c:when>
            </c:choose>
            <span class="ui-li-count">
            <fmt:formatDate value="${cur.report_time}" pattern="yyyy-MM-dd HH:mm" />
            </strong></span> </li>
          <li> <span style="font-size: 12px; font-weight: normal;"> ${cur.content} </span> </li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <div data-role="footer" class="ui-bar" data-position="fixed" data-theme="b">
    <div data-role="controlgroup"  data-type="horizontal"> <a onclick="goPage(location.href,null,'${page}',-1,'${pagelimit}');" data-role="button" data-icon="arrow-l" data-theme="b" data-inline="true">上页</a> <a onclick="goPage(location.href,null,'${page}',1,'${pagelimit}');" data-role="button" data-icon="arrow-r" data-theme="b" data-inline="true">下页</a> </div>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>