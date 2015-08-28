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
  <html-el:form action="/admin/PeShop">
   <html-el:hidden property="method" value="listPeProdUser" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
       <tr class="tabtt1">
        <td width="15%" align="left" nowrap="nowrap"><strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;业务员名称：</strong></td>
        <td width="60%" align="left"><html-el:text property="user_name_like" style="border:1px solid #ccc;height:16px;line-height:16px;width:60%" /></td>
        <td width="10%"><input class="but1" type="submit" name="Submit" value="搜索" /></td>
      </tr>
    </table>
  </html-el:form>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
 <c:forEach var="cur" items="${entityList}" varStatus="vs">
      <tr>
        <td width="20%" align="left" nowrap="nowrap" title="${cur.user_name}"><html-el:text property="${cur.id}" value="${cur.user_name},${cur.id}" styleId="${cur.id}" style="display:none;" />
          ${cur.user_name}</td>
        <td width="10%"><input name="button" type="button" class="but6" style="cursor:pointer"  value="" onclick="window.returnValue=document.getElementById('${cur.id}').value;window.close();" />
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PeShop.do">
     <table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "listPeProdUser");
            pager.addHiddenInputs("user_name_like", "${fn:escapeXml(af.map.user_name_like)}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$(".Tab tr").mouseover(function(){  
		$(this).addClass("over");
	}).mouseout(function(){
		$(this).removeClass("over");
	})
	$(".Tab tr:even").addClass("alteven");
	$(".Tab tr:odd").addClass("altodd");
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
