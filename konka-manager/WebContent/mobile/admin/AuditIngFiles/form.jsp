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
    <h1>文件查询</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a> <a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a> </div>
  <div data-role="content">
    <div class="content-primary">
      <ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
        <li data-role="list-divider"> 催办： </li>
        <li> 短信内容：<span style="font-size: 12px; font-weight: normal;">${msgContent}</span> </li>
        <li> 结果：<span style="font-size: 12px; font-weight: normal;">${msg}</span> </li>
      </ul>
    </div>
  </div>
  <div data-role="footer" class="ui-bar" data-position="fixed">
    <div data-role="controlgroup"  data-type="horizontal"> <a onclick="javascript:history.back(-1);" data-role="button" data-icon="back" data-theme="b" data-inline="true">返回</a> </div>
  </div>
  <script type="text/javascript">
$(document).ready(function(){
});

function logout() {
	location.href="${ctx}/mobile/login.do?method=logout";
}

</script> 
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>