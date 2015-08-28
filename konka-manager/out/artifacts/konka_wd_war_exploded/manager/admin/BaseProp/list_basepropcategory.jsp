<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
<base target="_self"></base>
</head>
<body>
<div class="oarcont" align="left">
  <div class="rtabcont1">
  <html-el:form action="/admin/BaseProp">
    <html-el:hidden property="method" value="listBasePropCategory" />
    <html-el:hidden property="cls_id" value="${af.map.cls_id}" /> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
       <tr class="tabtt1">
        <td width="15%" align="left" nowrap="nowrap"><strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;类别名称：</strong></td>
        <td width="60%" align="left"><html-el:text property="category_name_like" style="border:1px solid #ccc;height:16px;line-height:16px;width:60%" /></td>
        <td width="10%"><input class="but1" type="submit" name="Submit" value="搜索" /></td>
      </tr>
    </table>
  </html-el:form>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
 <c:forEach var="cur" items="${entpList}" varStatus="vs">
      <tr>
        <td width="20%" align="left" nowrap="nowrap" title="${cur.category_name}"><html-el:text property="${cur.category_id}" value="${cur.category_name},${cur.category_id}" styleId="${cur.category_id}" style="display:none;" />
          ${cur.category_name}</td>
        <td width="10%"><input name="button" type="button" class="but6" style="cursor:pointer" id="select" value="" onclick="window.parent.returnValue=document.getElementById('${cur.category_id}').value;window.parent.close();;" />
        </td>
       </tr>
        <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
      </c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
     </c:forEach>
  </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="BaseProp.do">
     <table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "listBasePropCategory");
            pager.addHiddenInputs("category_name_like", "${af.map.category_name_like}");
            pager.addHiddenInputs("cls_id", "${fn:escapeXml(af.map.cls_id)}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
