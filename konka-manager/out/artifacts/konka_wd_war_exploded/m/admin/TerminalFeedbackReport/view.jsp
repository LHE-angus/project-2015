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
    <h1>咨询反馈</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div>
    <div data-role="fieldcontain">
        <ul data-role="listview" data-inset="true" data-theme="b" data-dividertheme="b">
           <c:forEach items="${feedBackList}" var="cur" varStatus="vs">
          <li>
          ID：
          <span id="sequence">${cur.id}</span>
          </li>
          <li>
          留言内容：
          <span id="question">${cur.content}</span>
          </li>
          <li>
          回复：
          <span id="answer">${cur.map.fb_content}</span>
          </li>
          </c:forEach>
        </ul>
    </div>
  	<div data-role="footer" class="ui-bar" data-position="fixed" data-theme="b">
    	<div data-role="controlgroup"  data-type="horizontal">
			<a onclick="goPage(location.href,'old','${page}',-1,'${pagelimit}');" data-role="button" data-icon="arrow-l" data-theme="b" data-inline="true">上条</a>
			<a onclick="goPage(location.href,'old','${page}',1,'${pagelimit}');" data-role="button" data-icon="arrow-r" data-theme="b" data-inline="true">下条</a>
			<a href="TerminalFeedbackReport.do" data-role="button" data-icon="info" data-theme="b" data-inline="true">最新</a>
			<a href="TerminalFeedbackReport.do?method=delete&id=${id}" data-role="button" data-icon="minus" data-theme="b" data-inline="true">删除</a>
			<a href="TerminalFeedbackReport.do?method=add" data-role="button" data-icon="plus" data-theme="b" data-inline="true">填写</a>
		</div>
	</div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>