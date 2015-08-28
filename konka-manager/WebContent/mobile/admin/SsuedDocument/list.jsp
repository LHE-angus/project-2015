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
    <h1>下发文件</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a> <a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a> </div>
  <div data-role="content">
    <form id="af" method="post" action="${ctx}/mobile/admin/SsuedDocument.do">
      <div data-role="controlgroup"  data-type="horizontal">
        <label for="title">主题搜索</label>
        <input type="text" name="title" id="title" value="${title}" maxlength="30" data-theme="b"/>
        <label for="file_code">文件编号</label>
        <input type="text" name="file_code" id="file_code" value="${file_code}" maxlength="10" data-theme="b"/>
        <select name="mod_type" id="mod_type" data-native-menu="false" data-theme="b">
          <option value="">文件大类</option>
          <option value="notice" <c:if test="${af.map.mod_type eq 'notice'}">selected</c:if>>通知公告</option>
          <option value="file" <c:if test="${af.map.mod_type eq 'file'}">selected</c:if>>下发文件</option>
          <option value="expense" <c:if test="${af.map.mod_type eq 'expense'}">selected</c:if>>费用申请</option>
        </select>
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="pagelimit" id="pagelimit"/>
        <input type="hidden" name="forward" id="forward"/>
        <input type="hidden" id="mod_id" name="mod_id" value="720500"/>
        <input id="suButton" name="suButton" type="submit" data-role="button" data-icon="search" data-theme="b" data-inline="true" value="搜索"/>
      </div>
    </form>
    <div class="content-primary">
      <ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <li data-role="list-divider">编号：${cur.file_code} <span class="ui-li-count">
            <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm" />
            </span> </li>
          <li>
            <c:choose>
              <c:when test="${cur.mod_type eq 'notice' }"> <a href="SsuedDocument.do?method=view&id=${cur.id}&mod_id=720500"><b>标题：</b>${cur.title}</a></c:when>
              <c:when test="${cur.mod_type eq 'file' or  cur.mod_type eq 'expense'}"> <a href="SsuedDocument.do?method=viewFile&id=${cur.id}&mod_id=720500"><b>标题：</b>${cur.title}</a></c:when>
              <c:otherwise></c:otherwise>
            </c:choose>
          </li>
          <li>
            <p><b>文件大类：</b>
              <c:out value="${cur.mod_name}">
              </c:out>
            </p>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <div data-role="footer" class="ui-bar" data-position="fixed">
    <div data-role="controlgroup"  data-type="horizontal"> <a id="goback" name="goback" onclick="goback();" data-role="button" data-icon="arrow-l" data-theme="b" data-inline="true">上页</a> <a id="goforward" name="goforward" onclick="goforward();" data-role="button" data-icon="arrow-r" data-theme="b" data-inline="true">下页</a> </div>
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
		$("#title").val("${title}");
		$("#file_code").val("${file_code}");
		$("#mod_type").val("${af.map.mod_type}");
		$("#mod_id").val("720500");
		$("#page").val("${page}");
		$("#forward").val("-1");
		$("#pagelimit").val("${pagelimit}");
		$("#suButton").submit();}
};

function goforward() {
	if(goPage(null,'${page}',1,'${pagelimit}')){
		$("#title").val("${title}");
		$("#file_code").val("${file_code}");
		$("#mod_type").val("${af.map.mod_type}");
		$("#mod_id").val("720500");
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