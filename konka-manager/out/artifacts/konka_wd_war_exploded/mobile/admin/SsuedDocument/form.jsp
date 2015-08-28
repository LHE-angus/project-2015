<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>零售通-康佳电器</title>
<link rel="stylesheet" href="${ctx}/mobile/themes/konka.min.css" />
<link rel="stylesheet" href="${ctx}/commons/styles/jquery.mobile.structure-1.0.min.css" />
<link href="${ctx}/styles/mobiscroll-1.5.2.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/commons/styles/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.mobile-1.0.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.form.js" ></script>
<script src="${ctx}/commons/scripts/mobiscroll-1.5.2.js" ></script>
<script src="${ctx}/commons/scripts/jquery.autocomplete.js" ></script>
</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline">
    <h1>公告通知</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a> <a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a> </div>
  <div data-role="fieldcontain">
    <ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
      <li data-role="list-divider">公告通知</li>
      <li>
        <p>文件编号：${af.map.file_no}</p>
      </li>
      <li>
        <p>文件标题：
          <c:out value="${af.map.title}"/>
        </p>
      </li>
      <li>下发范围</li>
      <c:choose>
        <c:when test="${af.map.receive_type eq 0}">
          <li style="font-size:12px;padding-bottom:5px;padding-left:30px;">全部用户</li>
        </c:when>
        <c:when test="${af.map.receive_type eq 1}">
          <c:if test="${not empty af.map.user_names}">
            <li style="font-size:12px">下发用户：</li>
            <li style="font-size:12px;padding-bottom:5px;">${af.map.user_names}</li>
          </c:if>
          <c:if test="${not empty af.map.dept_names}">
            <li style="font-size:12px">下发部门：</li>
            <li style="font-size:12px">${af.map.dept_names}</li>
          </c:if>
        </c:when>
      </c:choose>
      <li>
        <p>公文类别：
          <c:choose>
            <c:when test="${af.map.doc_type eq 0}">公告信息</c:when>
            <c:when test="${af.map.doc_type eq 1}">公文下发</c:when>
          </c:choose>
        </p>
      </li>
      <li>
        <p>公文内容：${af.map.content}</p>
      </li>
      <li>
        <p>拟稿人：${af.map.draft_man}</p>
      </li>
    </ul>
    <input type="hidden" id="mod_id" name="mod_id" value="${af.map.mod_id}"/>
  </div>
  <div data-role="footer" class="ui-bar" data-position="fixed">
    <div data-role="controlgroup"  data-type="horizontal">
     <a href="SsuedDocument.do?method=list&mod_id=${af.map.mod_id}" data-role="button" data-icon="back" data-theme="b" data-inline="true">返回</a>
      </div>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>