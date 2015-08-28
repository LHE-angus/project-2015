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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/JoinInfoAudit">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table>
        <tr>
          <td width="80%" nowrap="nowrap">
            &nbsp;<strong class="fb">标题：</strong>
            <html-el:text property="title" size="30" style="width:90px;" maxlength="50" styleId="title" styleClass="webinput" />
            
			&nbsp;<strong class="fb">网点名称：</strong>
			<html-el:text property="shop_name_like" size="30" style="width:90px;" maxlength="50" styleId="shop_name_like" styleClass="webinput" />
			
            &nbsp;&nbsp;
            <html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="JoinInfoAudit.do?method=delete">
      <div style="text-align: left">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      </div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center">序号</td>
          <td nowrap="nowrap" align="center">标题</td>
          <td nowrap="nowrap" align="center">网点名称</td>
          <td width="70" align="center" >是否同意</td>
          <td width="80" nowrap="nowrap" align="center" >操作时间</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count} </td>
            <td align="left" nowrap="nowrap"><a href="${ctx}/manager/admin/JoinInfoManager.do?method=view&id=${cur.article_id}&mod_id=${af.map.mod_id}" title="查看加盟信息">${fn:escapeXml(cur.map.title)}</a> </td>
            <td align="left" nowrap="nowrap"><a href="${ctx}/manager/admin/KonkaEntpShop.do?method=view&id=${cur.shop_id}&mod_id=${af.map.mod_id}" title="查看网点信息">${fn:escapeXml(cur.map.shop_name)}</a></td>
            <td align="center" nowrap="nowrap"><font color="green" >同意</font></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.last_view_date}" pattern="yyyy-MM-dd" /></td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JoinInfoAudit.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");							
			pager.addHiddenInputs("shop_name_like", "${fn:escapeXml(af.map.shop_name_like)}");							
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
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
