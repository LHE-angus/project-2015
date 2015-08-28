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
    <h1>拜访记录</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a>
  </div>
	<div data-role="content">
      <form id="af" method="post" action="${ctx}/mobile/admin/VisitRecords.do">
		   	<div data-role="controlgroup"  data-type="horizontal">
		   	<label for="keyword">业务员</label>
		   	<input type="text" name="ywy_name_like" id="ywy_name_like" value="${ywy_name_like}" maxlength="30" data-theme="b"/>
		   	<label for="keyword">网点名</label>
		   	<input type="text" name="shop_name_like" id="shop_name_like" value="${shop_name_like}" maxlength="30" data-theme="b"/>
		   	<select name="shop_develop_status" id="shop_develop_status" data-native-menu="false" data-theme="b">
				<option value="">开拓状态</option>
	            <option value="0" <c:if test='${shop_develop_status eq 0}'>selected</c:if>>待开拓</option>
	            <option value="1" <c:if test='${shop_develop_status eq 1}'>selected</c:if>>正在开拓</option>
	            <option value="2" <c:if test='${shop_develop_status eq 2}'>selected</c:if>>已开拓</option>
			</select>
			<select name="shop_visit_status" id="shop_visit_status" data-native-menu="false" data-theme="b">
				<option value="">拜访状态</option>
                <option value="1" <c:if test='${shop_visit_status eq 1}'>selected</c:if>>拜访中</option>
                <option value="2" <c:if test='${shop_visit_status eq 2}'>selected</c:if>>拜访完成</option>
			</select>
		   	<input type="hidden" name="page" id="page"/>
		   	<input type="hidden" name="pagelimit" id="pagelimit"/>
		   	<input type="hidden" name="forward" id="forward"/>
				<input id="suButton" name="suButton" type="submit" data-role="button" data-icon="search" data-theme="b" data-inline="true" value="搜索" />
			</div>
      </form>
		<div class="content-primary"> 
			<ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
				<c:forEach var="cur" items="${entityList}">
				<li data-role="list-divider">网点名称: <c:out value="${cur.shop_name}"></c:out></li>
				<li style="line-height:18px;;">
				<p><b>拜访次数:</b> <c:out value="${cur.map.visit_count}"/></p>
				<p><b>业务员:</b> <c:out value="${cur.user_name}"/></p>
				<p><b>开拓状态:</b> <c:choose>
				<c:when test="${cur.develop_status eq 0}"><span style="color: green;">待开拓</span></c:when>
				<c:when test="${cur.develop_status eq 1}"><span style="color: green;">正在开拓</span></c:when>
				<c:when test="${cur.develop_status eq 2}"><span style="color: #F00;">已开拓</span></c:when>
				</c:choose>
				</p>
				<p><b>拜访状态:</b> <c:choose>
				<c:when test="${cur.map.visit_status eq 0}"><span style="color: green;">未拜访</span></c:when>
				<c:when test="${cur.map.visit_status eq 1}"><span style="color: green;">拜访中</span></c:when>
				<c:when test="${cur.map.visit_status eq 2}"><span style="color: #F00;">拜访完成</span></c:when>
				</c:choose></p></li>
				<c:forEach var="child" items="${cur.vList}" varStatus="vs">
				<c:if test="${vs.count eq 1}">
				<div data-role="collapsible"  data-collapsed="true" data-theme="b" data-content-theme="d"  class="r-srcs">
				<h3>拜访历史</h3>
				</c:if>
				<table><tr><td>
				<p><b>拜访人:</b> <c:out value="${child.user_name}"/></p></td><td>
				<p><b>拜访时间:</b> <fmt:formatDate value="${child.visit_date }" pattern="yyyy-MM-dd"/></p></td></tr>
				<tr>
				<td>
				<p><b>拜访笔记:</b> <c:out value="${child.visit_note }"/></p>
				</td></tr></table>
          		<c:if test="${vs.last eq true}">
				</div>
				</c:if>
				</c:forEach>
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