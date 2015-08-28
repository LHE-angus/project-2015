<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择关联品牌</title>
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
	background:url(${ctx}/styles/admin/images/selectEntpMainButtonBg.jpg) no-repeat;
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
  <html-el:form action="/admin/EntpShopSearch5W">
    <html-el:hidden property="method" value="listBaseBrandInfo" />
    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" class="TabTitle">
      <tr>
        <td width="15%" align="right" nowrap="nowrap">品牌名称：</td>
        <td align="left"><html-el:text property="brand_name_like" style="border:1px solid #ccc;height:16px;line-height:16px;width:60%" /></td>
        <td width="10%"><input name="submit" type="submit" style="cursor:pointer" class="btnImgBg" value="查 询" /></td>
      </tr>
    </table>
  </html-el:form>
  <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" class="Tab">
    <tr>
      <c:forEach var="cur" items="${baseBrandInfoList}" varStatus="vs1">
      	<td width="15%" align="left" nowrap="nowrap" title="${cur.brand_name}"><html-el:text property="${cur.brand_id}" value="${cur.brand_name},${cur.brand_id}" styleId="${cur.brand_id}" style="display:none;" />
         ${cur.brand_name}</td>
        <td width="10%"><input name="button" type="button" class="btnImgBg addBrandBtn" style="cursor:pointer" id="${cur.brand_name}_${cur.brand_id}" onclick="window.returnValue=document.getElementById('${cur.brand_id}').value;window.close();" value="选 择" />
        </td>
        <c:if test="${vs1.count mod 4 eq 0}">
          <c:out value="</tr>" escapeXml="false" />
          <c:out value="<tr>" escapeXml="false" />
        </c:if>
      </c:forEach>
    </tr>
  </table></div>
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
