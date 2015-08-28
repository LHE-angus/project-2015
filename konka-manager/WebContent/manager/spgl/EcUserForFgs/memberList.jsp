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
        <td nowrap="nowrap">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcUserForFgs.do">
      <html-el:hidden property="method" value="memberList" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td style="height:25px;" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">姓名：</strong>
            <html-el:text property="user_name_like" styleId="user_name_like" style="width:160px;" />&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">工卡号：</strong>
            <html-el:text property="card_no_like" styleId="card_no_like" style="width:160px;" />
              <input class="but1" type="submit" name="Submit" value="搜索" />
         </td>
         <td>
             <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" />
         </td>
         </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="overflow-x:auto; ">
    <form id="listForm" name="listForm" method="post" action="EcUserForFgs.do">
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tr1">
            <td width="5%" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">用户名</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">真实姓名</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">级别</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">工卡号</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">手机/电话</font></td>            
            <td width="10%" nowrap="nowrap" ><font class="blue">注册时间</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td  nowrap="nowrap"  align="center">${af.map.pager.pageSize*(af.map.pager.currentPage-1)+vs.count}</td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.user_name}" /></td>
               <td  nowrap="nowrap" align="left"><c:out value="${cur.real_name}" /></td>
                 <td  nowrap="nowrap" align="left"><c:out value="${cur.map.leve}" /></td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.card_no}" /></td>
             <td  nowrap="nowrap" align="left"><c:out value="${cur.link_phone}" /></td>
              <td  nowrap="nowrap" align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
        </tbody>
      </table>
    </form>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcUserForFgs.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "memberList");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("name_like", "${fn:escapeXml(af.map.name_like)}");
	            pager.addHiddenInputs("is_act", "${af.map.is_act}");
	            pager.addHiddenInputs("is_del", "${af.map.is_del}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            pager.addHiddenInputs("user_type", "${af.map.user_type}");
	            pager.addHiddenInputs("card_level_name", "${af.map.card_level_name}");
	            pager.addHiddenInputs("card_no_like", "${fn:escapeXml(af.map.card_no_like)}");
	            pager.addHiddenInputs("department_like", "${fn:escapeXml(af.map.department_like)}");
	            pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
				pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
				pager.addHiddenInputs("card_sender", "${af.map.card_sender}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
