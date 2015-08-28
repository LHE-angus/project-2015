<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
         <td>当前位置：${naviString}</td>
        <!--  <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /><span id="span_help" style="cursor:pointer;">帮助</span> </td> --> 
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/chengduoa/SelfEventCenter">
      <html-el:hidden property="method" value="list" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <form id="listForm" name="listForm" method="post" action="SelfEventCenter.do?method=delete">
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
         <tr class="tabtt1">
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td nowrap="nowrap">标题</td>
          <td width="12%" nowrap="nowrap" align="center">事务类型</td>
          <td width="18%" nowrap="nowrap">信息来源</td>
          <td width="12%" align="center" nowrap="nowrap">发起时间</td>
          <td width="8%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <tbody>
          <c:set var="ids" value="0" />
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr id="tr_${cur.id}">
              <td align="center" nowrap="nowrap" onclick="">${vs.count}</td>
              <td align="left">${cur.eventiltle}</td>
              <td align="center">${cur.eventType}</td>
              <td>${cur.fromPerson}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.enterDate}" pattern="yyyy-MM-dd HH:mm" /></td>
              <!-- 后台自动判定是否需要走流程 -->
              <td align="center" nowrap="nowrap">${cur.eventDo}</td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
        </tbody>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function() {
		$("#span_help").click(function(){
		    $("#cvtooltipClose").cvtooltip({
		       panel: "#body_oarcont",
		        direction: 1,                
		       width: 420,
		       left: 320,
		       top: 5,
		       speed: 500,
		       delay: 10000
		    });
		 });	   
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
