<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询反馈</title>
<style>
body { font-family: 宋体; font-size:12px;}
</style>
</head>
<body>
<%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/admin/TerminalFeedbackForSelf">
      <html-el:hidden property="method" styleId="method" value="listonmobile" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="5">
        <tr>
          <td><input class="but2" type="button" name="button"  value="新增" onClick="location.href='TerminalFeedbackForSelf.do?method=addonmobile&mod_id=${af.map.mod_id}';return false;" />
            <c:if test="${role_id_eq_10}"><a href="TerminalFeedback.do?method=list&mod_id=${af.map.mod_id}">进入管理</a></c:if></td>
          <td align="right"><html-el:select property="message_type" styleId="message_type" onchange="this.form.submit();">
              <html-el:option value="">== 全部类别 ==</html-el:option>
              <html-el:optionsCollection name="mobileCategoryList" value="id"  label="name"/>
            </html-el:select></td>
        </tr>
      </table>
    </html-el:form>
  <form id="listForm" name="listForm" method="post" action="TerminalFeedbackForSelf.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="3" class="rtable2">
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="left" nowrap="nowrap" style="padding-top:20px;border-bottom:1px solid #ccc;"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
              <td align="right" nowrap="nowrap" style="padding-top:20px;border-bottom:1px solid #ccc;"><c:choose>
                  <c:when test="${fn:length(cur.konkaMobileTerminalFbBackList) eq 0}"><span style="color:#F00;">暂未回复</span></c:when>
                  <c:when test="${fn:length(cur.konkaMobileTerminalFbBackList) gt 0}"><span style="color:green;">已回复（${fn:length(cur.konkaMobileTerminalFbBackList)}）</span></c:when>
                </c:choose></td>
            </tr>
            <tr>
              <td align="left" colspan="2" style="word-break:break-all;"><span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, 'TerminalFeedbackForSelf.do', 'viewonmobile','id=${cur.id}&'+$('#bottomPageForm').serialize())">[${cur.map.c_name}] <span title="${cur.content}">${fnx:abbreviate(cur.content, 50 * 2, '...')}</span> </span></td>
            </tr>
          </c:forEach>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="TerminalFeedbackForSelf.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="80"><div style="text-align:left; padding-right:5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "listonmobile");
							pager.addHiddenInputs("message_type","${af.map.message_type}");
							document.write(pager.toString());
						</script> 
            </div></td>
        </tr>
      </table>
    </form>
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