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
<link rel="stylesheet" href="${ctx}/m/jqm/jquery.mobile-1.3.1.min.css" />
<link href="${ctx}/styles/mobiscroll-1.5.2.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/m/jqm/jquery-1.9.1.min.js"></script>
<script src="${ctx}/m/jqm/jquery.mobile-1.3.1.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.form.js" ></script>
<script src="${ctx}/commons/scripts/mobiscroll-1.5.2.js" ></script>
</head>

<body>
<div class="theme-preview">
  <div data-role="header" data-position="inline" data-position="fixed">
    <h1>康佳渠道管理系统</h1>
    <a onclick="localhere();" data-icon="arrow-d" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">定位</a> <a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a>
  </div>
  <div class="ui-body ui-body-a">
    <div data-role="fieldcontain">
      <nav> 
        <ul data-role="listview" data-inset="true" data-theme="b" data-dividertheme="b">
				<li data-role="list-divider">零售通</li>
    <c:forEach items="${sysModuleList}" var="cur">
    	<c:set var="url" value="${cur.mod_url}" />
     	<logic-el:match name="url" value="ajax=false">
     		<li><a href="${url}&mod_id=${cur.mod_id}" data-ajax="false">${cur.mod_name}</a></li>
     	</logic-el:match>
     	<logic-el:notMatch name="url" value="ajax=false">
     		<li><a href="${url}?mod_id=${cur.mod_id}">${cur.mod_name}</a></li>
     	</logic-el:notMatch>     	
    </c:forEach>
    </ul>
    <br/>
		<div class="content-primary"> 
			<ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="b">
				<li data-role="list-divider">业务员进销存</li>
				<li><a href="${ctx}/m/admin/SalesReport.do">销售数据</a></li>
				<li><a href="${ctx}/m/admin/SalesReports.do">退货数据</a></li>
				<li><a href="${ctx}/m/admin/KonkaMobileThingsUseReport.do">物料终端</a></li>
				<li><a href="${ctx}/m/admin/CompeteReport.do">竞品数据</a></li>
				<li><a href="${ctx}/m/admin/HistoryReport.do">历史数据</a></li>
				<li><a href="${ctx}/m/admin/RestReport.do">休息上报</a></li>
				<li><a href="${ctx}/m/admin/TerminalFeedbackReport.do">意见反馈</a></li>
			</div>
      </nav>
    </div>
  </div>
</div>

<script type="text/javascript">
function setOnlyInter(obj) {
	var v = obj.value.replace(/[^\d]/gi,'');
	if(v == 0){
		obj.value = '0';
	}else{
		obj.value = v;
	}
}

function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?-]/gi,'');
	if(v == 0){
		obj.value = '0';
	}else{
		obj.value = v;
	}
}

function logout() {
	location.href="${ctx}/mobile/login.do?method=logout";
}


function selectItem(li) {
	findValue(li);
}

function localhere(){
	$.mobile.loadingMessageTextVisible = true;
	$.mobile.showPageLoadingMsg("a","定位中...");
}

function formatItem(row) {
	return row[0] + " (id: " + row[1] + ")";
}

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
<jsp:include page="/__analytics.jsp" />
</body>
</html>