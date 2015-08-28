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
</head>

<body>
<div data-role="page">
	<div data-role="header" data-position="inline">
    <h1>客户管理</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a>
  </div>
	<div data-role="content">
      <form id="af" method="post" action="${ctx}/mobile/admin/CustomerManage.do">
		   	<div data-role="controlgroup"  data-type="horizontal">
		   	<label for="keyword">模糊搜索</label>
		   	<input type="text" name="keyword" id="keyword" value="${keyword}" maxlength="30" data-theme="b"/>
		   	<label for="code_like">R3编码</label>
		   	<input type="text" name="code_like" id="code_like" value="${code_like}" maxlength="30" data-theme="b"/>
			<select name="is_match" id="is_match" data-native-menu="false" data-theme="b">
				<option value="">匹配状态</option>
       	 		<option value="0" <c:if test='${is_match eq 0}'>selected</c:if>>未匹配</option>
       	 		<option value="1" <c:if test='${is_match eq 1}'>selected</c:if>>已匹配</option>
			</select>
		   	<input type="hidden" name="page" id="page"/>
		   	<input type="hidden" name="pagelimit" id="pagelimit"/>
		   	<input type="hidden" name="forward" id="forward"/>
				<input id="suButton" name="suButton" type="submit" data-role="button" data-icon="search" data-theme="b" data-inline="true" value="搜索" />
			</div>
      </form>
		<div class="content-primary"> 
			<ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
				<c:forEach var="cur" items="${entityList}" varStatus="vs">
				<li data-role="list-divider">客户名称：<c:out value="${cur.customer_name}"></c:out>
				<c:if test="${cur.is_match eq 0}"><span class="ui-li-count">未匹配</span></c:if>
				<c:if test="${cur.is_match eq 1}"><span class="ui-li-count">已匹配</span></c:if>
				</li>
				<li><c:if test="${cur.is_match eq 1}">
				<a href="CustomerManage.do?method=view&id=${cur.id}">
					<p style="padding-bottom:5px;padding-top:5px;">R3编码：<strong><c:out value="${cur.r3_code}"></c:out></strong></p>
					<p>合并编码：<strong><c:out value="${cur.merge_code_2010}"></c:out></strong></p>
				</a>
				</c:if>
				<c:if test="${cur.is_match eq 0}">
					<p style="padding-bottom:5px;padding-top:5px;">R3编码：<strong><c:out value="${cur.r3_code}"></c:out></strong></p>
					<p>合并编码：<strong><c:out value="${cur.merge_code_2010}"></c:out></strong></p>
				</c:if>
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
		$("#keyword").val("${keyword}");
		$("#code_like").val("${code_like}");
		$("#is_match").val("${is_match}");
		$("#page").val("${page}");
		$("#forward").val("-1");
		$("#pagelimit").val("${pagelimit}");
		$("#suButton").submit();}
};

function goforward() {
	if(goPage(null,'${page}',1,'${pagelimit}')){
		$("#keyword").val("${keyword}");
		$("#code_like").val("${code_like}");
		$("#is_match").val("${is_match}");
		$("#page").val("${page}");
		$("#forward").val("1");
		$("#pagelimit").val("${pagelimit}");
		$("#suButton").submit();}
};
</script>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>