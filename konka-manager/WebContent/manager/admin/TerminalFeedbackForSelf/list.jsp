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
        <td>当前位置：我的意见反馈</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/admin/TerminalFeedbackForSelf">
      <html-el:hidden property="method" styleId="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><input class="but2" type="button" name="button"  value="新增" onClick="location.href='TerminalFeedbackForSelf.do?method=add&mod_id=${af.map.mod_id}';return false;" />
            <c:if test="${role_id_eq_10}"><a href="TerminalFeedback.do?method=list&mod_id=${af.map.mod_id}">进入管理</a></c:if></td>
          <td align="right"><html-el:select property="message_type" styleId="message_type" onchange="this.form.submit();">
              <html-el:option value="">== 全部类别 ==</html-el:option>
              <html-el:optionsCollection name="mobileCategoryList" value="id"  label="name"/>
            </html-el:select></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="TerminalFeedbackForSelf.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="8%" nowrap="nowrap" align="center">时间</td>
          <td nowrap="nowrap" align="center">内容</td>
          <td width="8%" nowrap="nowrap" align="center">回复</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap"> ${vs.count}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
              <td align="left" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, 'TerminalFeedbackForSelf.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">[${cur.map.c_name}] <span title="${cur.content}">${fnx:abbreviate(cur.content, 50 * 2, '...')}</span> </span></td>
              <td align="center" nowrap="nowrap"><c:choose>
                  <c:when test="${fn:length(cur.konkaMobileTerminalFbBackList) eq 0}"><span style="color:#F00;">暂未回复</span></c:when>
                  <c:when test="${fn:length(cur.konkaMobileTerminalFbBackList) gt 0}"><span style="color:green;">已回复（${fn:length(cur.konkaMobileTerminalFbBackList)}）</span></c:when>
                </c:choose></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="TerminalFeedbackForSelf.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("message_type","${af.map.message_type}");
							document.write(pager.toString());
						</script> 
            </div></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>