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
    <form id="af" method="post" action="${ctx}/mobile/admin/AuditIngFiles.do">
      <div data-role="controlgroup"  data-type="horizontal">
        <label for="file_title_like">标题搜索</label>
        <input type="text" name="file_title_like" id="file_title_like" value="${file_title_like}" maxlength="30" data-theme="b"/>
        <label for="file_no">文件编号</label>
        <input type="text" name="file_no" id="file_no" value="${file_no}" maxlength="10" data-theme="b"/>
        <select name="map_file_status" id="map_file_status" data-native-menu="false" data-theme="b">
          <option value="">文件状态</option>
          <option value="0,1,2,3">请选择</option>
          <option value="0" <c:if test="${af.map.map_file_status eq 0}">selected</c:if>>未提交</option>
          <option value="1" <c:if test="${af.map.map_file_status eq 1}">selected</c:if>>审批中</option>
          <option value="2" <c:if test="${af.map.map_file_status eq 2}">selected</c:if>>已审批</option>
        </select>
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="pagelimit" id="pagelimit"/>
        <input type="hidden" name="forward" id="forward"/>
        <input type="hidden" id="mod_id" name="mod_id" value="${af.map.mod_id}"/>
        <input id="suButton" name="suButton" type="submit" data-role="button" data-icon="search" data-theme="b" data-inline="true" value="搜索"/>
      </div>
    </form>
    <div class="content-primary">
      <ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <li data-role="list-divider"> 文件编号：${fn:escapeXml(cur.file_no)} <span class="ui-li-count"><strong>
            <fmt:formatDate value="${cur.submit_datetime}" pattern="yyyy-MM-dd HH:mm" />
            </strong></span> </li>
          <li> 文件标题：<span style="font-size: 12px; font-weight: normal;">${fn:escapeXml(cur.file_title)}</span> </li>
          <li>
            <div data-role="controlgroup"  data-type="horizontal"> 文件状态：<span style="font-size: 12px; font-weight: normal;">
              <c:choose>
                <c:when test="${cur.file_status eq 0}"><span style="color:#000;font-weight:bold;">未提交</span></c:when>
                <c:when test="${cur.file_status eq 1}"><span style="color:#00F;font-weight:bold;">审批中</span></c:when>
                <c:when test="${cur.file_status eq 2}"><span style="color:#00CC66;font-weight:bold;">已审批</span></c:when>
              </c:choose>
              </span> <a href="AuditIngFiles.do?method=sendSms&id=${cur.id}" data-role="button" data-icon="plus" data-theme="b" data-inline="true">催办</a> </div>
          </li>
          <li> 当前审批人：<span style="font-size: 12px; font-weight: normal;">${fn:escapeXml(cur.map.cur_audit_user_name)}</span> </li>
        </c:forEach>
      </ul>
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
		$("#mod_id").val("${mod_id}");
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
		$("#mod_id").val("${mod_id}");
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