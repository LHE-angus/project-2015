<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div data-role="page">
	<div data-role="header" data-position="inline" data-theme="b">
    <h1>历史上报数据</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a>
  </div>
	<div data-role="content">
	<form id="af" method="post" action="${ctx}/mobile/admin/HistoryReport.do">
		   	<div data-role="controlgroup"  data-type="horizontal">
			<select name="type_id" id="type_id" data-native-menu="false">
				<option value="">上报类型</option>
       	 		<option value="1" <c:if test='${type_id eq 1}'>selected</c:if>>销售数据</option>
       	 		<option value="2" <c:if test='${type_id eq 2}'>selected</c:if>>退货数据</option>
       	 		<option value="3" <c:if test='${type_id eq 3}'>selected</c:if>>终端物料</option>
       	 		<option value="4" <c:if test='${type_id eq 4}'>selected</c:if>>竞品数据</option>
       	 		<option value="5" <c:if test='${type_id eq 5}'>selected</c:if>>样机数据</option>
       	 		<option value="6" <c:if test='${type_id eq 6}'>selected</c:if>>物料数据</option>
			</select>
		   	<input type="hidden" name="page" id="page"/>
		   	<input type="hidden" name="pagelimit" id="pagelimit"/>
		   	<input type="hidden" name="forward" id="forward"/>
			<input type="hidden" id="mod_id" name="mod_id" value="712050"/>
			<input id="suButton" name="suButton" type="submit" data-role="button" data-icon="search" data-theme="b" data-inline="true" value="搜索" />
			</div>
      </form>
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
				<span class="ui-li-count"><strong><fmt:formatDate value="${cur.report_time}" pattern="yyyy-MM-dd HH:mm" /></strong></span>
				<br/><br/>
			</li>
			<li>
			<span style="font-size: 12px; font-weight: normal;">
			${cur.content}
			</span>
			</li>
			</c:forEach>
		</ul>
	</div>
	</div>
  	<div data-role="footer" class="ui-bar" data-position="fixed" data-theme="b">
    	<div data-role="controlgroup"  data-type="horizontal">
			<a id="goback" onclick="goback();" data-role="button" data-icon="arrow-l" data-theme="b" data-inline="true">上页</a>
			<a id="goforward" onclick="goforward();" data-role="button" data-icon="arrow-r" data-theme="b" data-inline="true">下页</a>
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
		$("#type_id").val("${type_id}");
		$("#mod_id").val("712050");
		$("#page").val("${page}");
		$("#forward").val("-1");
		$("#pagelimit").val("${pagelimit}");
		$("#suButton").submit();}
};

function goforward() {
	if(goPage(null,'${page}',1,'${pagelimit}')){
		$("#type_id").val("${type_id}");
		$("#mod_id").val("712050");
		$("#page").val("${page}");
		$("#forward").val("1");
		$("#pagelimit").val("${pagelimit}");
		$("#suButton").submit();
	}
};

</script>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>