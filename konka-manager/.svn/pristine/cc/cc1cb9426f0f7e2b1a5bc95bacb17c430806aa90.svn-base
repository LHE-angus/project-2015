<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<title>选择属性类别</title>
<style type="text/css">
body {
	background:#ffffff;
}
body, table, td, tr, th {
	font-family:"宋体", arial;
	font-size:12px;
	color:#000;
	line-height:20px;
}
img {
	vertical-align:middle;
	border:0
}
.btnImgBg {
	width:52px;
	height:18px;
	line-height:18px;
	background:url(${ctx}/styles/images/selectEntpMainButtonBg.jpg) no-repeat;
	text-align:center;
	border:none;
}
.inputText {
	border:1px solid #ccc;
	height:16px;
	line-height:16px;
	text-align:center;
}
table.TabTitle {
	border:1px solid #ccc;
	border-bottom:none;
	border-left:none;
}
table.TabTitle td {
	border-bottom:1px solid #ccc;
	border-left:1px solid #ccc;
	border-collapse:collapse;
	background:#F1F7FC;
	padding:6px 6px;
}
table.Tab {
	border:none;
}
table.Tab td {
	border-collapse:collapse;
	padding:5px 6px;
}
table.Tab td.next {
	background:#DBE9F7;
}
table.Tab td.page {
	text-align:right;
	background:#fff;
	padding-right:20px;
}
tr.alteven td {
	background: #ecf6fc;
}
tr.altodd td {
	background: #DBE9F7;
}
tr.over td {
	background: #BDD3E8;
}
table {
	table-layout: fixed;
}
</style>
</head>
<body>
<div align="center">
  <html-el:form action="/manager/KonkaXxPdProp.do">
    <html-el:hidden property="method" value="listBasePropCategory" />
    <html-el:hidden property="cls_id" value="${af.map.cls_id}" />
    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" class="TabTitle">
      <tr>
        <td width="15%" align="right" nowrap="nowrap">类别名称：</td>
        <td align="left"><html-el:text property="category_name_like" style="border:1px solid #ccc;height:16px;line-height:16px;width:60%" /></td>
        <td width="10%"><input name="submit" type="submit" style="cursor:pointer" class="btnImgBg" value="查 询" /></td>
      </tr>
    </table>
  </html-el:form>
  <table width="750"border="0" cellspacing="0" cellpadding="0" class="rtable2">
    <tr>
      <c:forEach var="cur" items="${entpList}" varStatus="vs1">
        <td width="20%" align="left" nowrap="nowrap" title="${cur.category_name}"><html-el:text property="${cur.category_id}" value="${cur.category_name},${cur.category_id}" styleId="${cur.category_id}" style="display:none;" />
          ${cur.category_name}</td>
        <td width="10%"><input name="button" type="button" class="btnImgBg" style="cursor:pointer" id="button" value="选 择" onclick="window.returnValue=document.getElementById('${cur.category_id}').value;window.close();" /></td>
        <c:if test="${vs1.count mod 3 eq 0}">
          <c:out value="</tr>" escapeXml="false" />
          <c:out value="<tr>" escapeXml="false" />
        </c:if>
      </c:forEach>
    </tr>
  </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxPdProp.do?method=listBasePropCategory">
    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "listBasePropCategory");
            pager.addHiddenInputs("category_name_like", "${fn:escapeXml(af.map.category_name_like)}");
            pager.addHiddenInputs("cls_id", "${fn:escapeXml(af.map.cls_id)}");
            document.write(pager.toString());
            </script></td>
      </tr>
    </table>
  </form>
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
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
