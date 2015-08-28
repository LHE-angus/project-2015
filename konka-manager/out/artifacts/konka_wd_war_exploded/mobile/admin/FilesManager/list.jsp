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
  <div data-role="header" data-position="inline">
    <h1>文件管理</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a> <a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a> </div>
  <div data-role="content">
    <form id="af" method="post" action="${ctx}/mobile/admin/FilesManager.do">
      <input type="hidden" name="method" value="list">
      <div data-role="controlgroup"  data-type="horizontal">
        <label for="keyword">文件标题：</label>
        <input type="text" name="file_title_like" id="file_title_like" value="${af.map.file_title_like}" maxlength="30" data-theme="b"/>
        <label for="code_like">文件编号：</label>
        <input type="text" name="file_no" id="file_no" value="${af.map.file_no}" maxlength="10" data-theme="b"/>
        <select name="map_file_status" id="map_file_status" data-native-menu="false" data-theme="b">
          <option value="">文件状态：</option>
          <option value="0,1,2">请选择</option>
          <option value="0" <c:if test='${map_file_status eq 0}'>selected</c:if>>未提交</option>
          <option value="1" <c:if test='${map_file_status eq 1}'>selected</c:if>>审批中</option>
          <option value="2" <c:if test='${map_file_status eq 2}'>selected</c:if>>已审批</option>
        </select>
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="pagelimit" id="pagelimit"/>
        <input type="hidden" name="forward" id="forward"/>
        <input id="suButton" name="suButton" type="submit" data-role="button" data-icon="search" data-theme="b" data-inline="true" value="搜索" />
      </div>
    </form>
    <div class="content-primary">
      <form id="mForm" method="post" action="/konka-wd/mobile/admin/FilesManager.do">
        <input type="hidden" name="method" value="delete">
        <input type="hidden" name="id" value="${id}">
        <ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <li data-role="list-divider">标题：《${cur.file_title}》 <span class="ui-li-count"><strong>
              <fmt:formatDate value="${cur.submit_datetime}" pattern="yyyy-MM-dd HH:mm" />
              </strong> </li>
            <li><a href="FilesManager.do?method=view&id=${cur.id}">文件编号：<span style="font-size: 12px; font-weight: normal;">
              <c:out value="${cur.file_no}"/>
              </span></a></li>
            <li> 文件状态：<span style="font-size: 12px; font-weight: normal;">
              <c:choose>
                <c:when test="${cur.file_status eq 0}"><span style="color:#000;font-weight:bold;">未审批</span></c:when>
                <c:when test="${cur.file_status eq 1}"><span style="color:#00F;font-weight:bold;">审批中</span></c:when>
                <c:when test="${cur.file_status eq 2}"><span style="color:#00CC66;font-weight:bold;">已审批</span></c:when>
              </c:choose>
              </span>
              <c:if test ="${cur.file_from eq 1 }">
                <c:if test="${cur.file_status eq 0 or cur.file_status eq -3}">
                  <div data-role="controlgroup"  data-type="horizontal"> <a href="FilesManager.do?method=delete&id=${cur.id}" data-role="button" data-icon="delete" data-theme="b" data-inline="true">删除</a> <a href="FilesManager.do?method=edit&id=${cur.id}" data-role="button" data-icon="plus" data-theme="b" data-inline="true">修改</a> </div>
                </c:if>
              </c:if>
            </li>
          </c:forEach>
        </ul>
      </form>
    </div>
  </div>
  <div data-role="footer" class="ui-bar" data-position="fixed">
    <div data-role="controlgroup"  data-type="horizontal"> <a id="goback" name="goback" onclick="goback();" data-role="button" data-icon="arrow-l" data-theme="b" data-inline="true">上页</a> <a id="goforward" name="goforward" onclick="goforward();" data-role="button" data-icon="arrow-r" data-theme="b" data-inline="true">下页</a> </div>
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
		$("#file_title_like").val("${file_title_like}");
		$("#file_no").val("${file_no}");
		$("#map_file_status").val("${map_file_status}");
		$("#st_submit_datetime").val("${st_submit_datetime}");
		$("#en_submit_datetime").val("${en_submit_datetime}");
		$("#page").val("${page}");
		$("#forward").val("-1");
		$("#pagelimit").val("${pagelimit}");
		$("#suButton").submit();}
};

function goforward() {
	if(goPage(null,'${page}',1,'${pagelimit}')){
		$("#file_title_like").val("${file_title_like}");
		$("#file_no").val("${file_no}");
		$("#map_file_status").val("${map_file_status}");
		$("#st_submit_datetime").val("${st_submit_datetime}");
		$("#en_submit_datetime").val("${en_submit_datetime}");
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
