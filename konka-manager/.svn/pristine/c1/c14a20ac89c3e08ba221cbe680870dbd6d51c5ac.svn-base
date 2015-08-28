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
<script src="${ctx}/commons/scripts/jquery.autocomplete.js" ></script>
</head>

<body>
<div data-role="page">
<script src="${ctx}/commons/scripts/mobiscroll-1.5.2.js" ></script>
	<div data-role="header" data-position="inline">
    <h1>工作日志</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a>
  </div>
	<div data-role="content">
		<form id="af" method="post" action="${ctx}/mobile/admin/SxOperLog.do">
		   	<div data-role="controlgroup"  data-type="horizontal">
			   	<input type="hidden" name="page" id="page"/>
			   	<input type="hidden" name="pagelimit" id="pagelimit"/>
			   	<input type="hidden" name="forward" id="forward"/>
			</div>
      </form>
	<div class="content-primary"> 
		<ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
			<c:forEach var="cur" items="${baseList}" varStatus="vs">
			<li data-role="list-divider">
				<span><strong><fmt:formatDate value="${cur.oper_time}" pattern="yyyy-MM-dd HH:mm:ss" /></strong></span>
			</li>
			<li>
				操作IP：<span style="font-size: 12px; font-weight: normal;"><c:out value="${cur.oper_ip}"></c:out></span>
			</li>
			<li>
				操作方式：<span style="font-size: 12px; font-weight: normal;"><c:out value="${cur.oper_type}"></c:out></span>
			</li>
			<li>
			</li>
			</c:forEach>
		</ul>
	</div>
	</div>
  	<div data-role="footer" class="ui-bar" data-position="fixed">
    	<div data-role="controlgroup"  data-type="horizontal">
    		<a id="goback" name="goback" onclick="goback();" data-role="button" data-icon="arrow-l" data-theme="b" data-inline="true">上页</a>
			<a id="goforward" name="goforward" onclick="goforward();" data-role="button" data-icon="arrow-r" data-theme="b" data-inline="true">下页</a>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function(){
});

function goPage(method,page,forward,pagelimit){
	if(page == 1 && forward == -1){
		alert("已到首页！");
		return false;
	}
	else if(page == pagelimit && forward == 1){
		alert("已到尾页！");
		return false;
	}
	else if(pagelimit == 0){
		alert("无翻页信息！");
		return false;
	}
	else
		return true;
}

function logout() {
	location.href="${ctx}/mobile/login.do?method=logout";
}

function goback() {
	if(goPage(null,'${page}',-1,'${pagelimit}')){
		$("#page").val("${page}");
		$("#forward").val("-1");
		$("#pagelimit").val("${pagelimit}");
		$("#af").submit();}
};

function goforward() {
	if(goPage(null,'${page}',1,'${pagelimit}')){
		$("#page").val("${page}");
		$("#forward").val("1");
		$("#pagelimit").val("${pagelimit}");
		$("#af").submit();}
};
</script>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>