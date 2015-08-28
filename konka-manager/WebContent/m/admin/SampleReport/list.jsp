<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>零售通-康佳电器</title>
</head>
<body>
<div class="theme-preview">
  <div data-role="header" data-position="inline">
    <h1>零售通-康佳电器</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
    <a href="${ctx}/mobile/login.do?method=logout" data-icon="delete" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div><form action="#" method="get">
    <div data-role="fieldcontain">
      <nav>
        <ul data-role="listview" data-inset="true" data-theme="b" data-dividertheme="b">
          <li><a href="docs/pages/index.html">销售数据上报</a></li>
          <li><a href="docs/toolbars/index.html">退货数据上报</a></li>
          <li><a href="docs/buttons/index.html">样机数据上报</a></li>
          <li><a href="docs/content/index.html">终端物料上报</a></li>
          <li><a href="docs/forms/index.html">竞品数据上报</a></li>
          <li><a href="docs/forms/index.html">历史上报记录</a></li>
          <li><a href="docs/api/globalconfig.html">休息上报</a></li>
          <li><a href="docs/api/events.html">意见反馈</a></li>
        </ul>
      </nav>
    </div>
    <div class="ui-body ui-body-b">
    <fieldset class="ui-grid-a">
      <div class="ui-block-a">
        <button type="button" data-theme="b" onclick="this.form.reset();">重填</button>
      </div>
      <div class="ui-block-b">
        <button type="submit" data-theme="b">提交</button>
      </div>
    </fieldset>
    </div>
    </form>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>