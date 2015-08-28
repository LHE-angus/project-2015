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
<title>协同办公-康佳电器</title>
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
<div data-role="page" class="type-interior">
  <div data-role="header" data-position="inline">
    <h1>待办文件</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a> <a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a> </div>
  <div data-role="content">
    <div class="content-primary">
      <ul data-role="listview" data-inset="true" id="listCon" name="listCon" data-theme="b" data-divider-theme="a">
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <li data-role="list-divider">《${cur.eventiltle}》<span class="ui-li-count"><strong>
            <fmt:formatDate value="${cur.enterDate}" pattern="yyyy-MM-dd HH:mm" />
            </strong></li>
          <li>类型：${cur.eventType}</li>
          <li>发件人：${cur.fromPerson}</li>
          <a href="SelfEventCenter.do?method=view&id=${cur.id}" data-role="button" data-icon="plus" data-theme="b" data-inline="true">审批</a>
        </c:forEach>
      </ul>
    </div>
  </div>
  <div data-role="footer" class="ui-bar" data-position="fixed">
    <div data-role="controlgroup"  data-type="horizontal"> <a onclick="goPage(location.href,null,'${page}',-1,'${pagelimit}');" data-role="button" data-icon="arrow-l" data-theme="b" data-inline="true">上页</a> <a onclick="goPage(location.href,null,'${page}',1,'${pagelimit}');" data-role="button" data-icon="arrow-r" data-theme="b" data-inline="true">下页</a> </div>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
<script type="text/javascript">
function goPage(url,method,page,forward,pagelimit){
	if(page == 1 && forward == -1)
		alert("已到首页！");
	else if(page == pagelimit && forward == 1)
		alert("已到尾页！");
	else{
		url = url.split('?')[0];
		if(method == null)
			location.href = url + "?page=" + page + "&forward=" + forward;
		else
			location.href = url + "?method=" + method + "&page=" + page + "&forward=" + forward;
	}
}
</script>
</html>
