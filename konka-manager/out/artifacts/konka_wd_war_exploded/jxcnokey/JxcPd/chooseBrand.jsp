<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择项目</title>
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
.bgButton {background:url(${ctx}/styles/jxc/images/butbg1.gif) repeat-x;border:1px #c4c4c4 solid;font:normal 12px/20px "宋体";color:#333;padding: 0px 1px 0px 1px !important;padding: 0px 1px 0px 1px;letter-spacing: 1px;font-size:12px; cursor:pointer;}
.btnImgBg {
	width:67px;
	height:24px;
	background:url(${ctx}/styles/jxc/images/choose.gif) no-repeat;
	font:normal 12px/20px "宋体";
	text-align:left;
	color:#333;
	padding-left:27px;
	border:0;
	cursor:pointer;
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
  <html-el:form action="/JxcPd">
    <html-el:hidden property="method" value="chooseBrand" />
    <html-el:hidden property="pd_type" />
    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" class="TabTitle">
      <tr>
        <td width="10%" align="right" nowrap="nowrap">品牌名称：</td>
        <td align="left" width="35%"><html-el:text property="brand_name_like" style="border:1px solid #ccc;height:16px;line-height:16px;width:90%" /></td>
        <td width="10%"><input name="submit" type="submit" class="bgButton" value="查 询" /></td>
      </tr>
    </table>
  </html-el:form>
  <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" class="Tab">
    <tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs1">
        <td width="20%" title="${cur.brand_name}" nowrap="nowrap"><html-el:text property="${cur.brand_id}" value="${cur.brand_name},${cur.brand_id}" styleId="${cur.brand_id}" style="display:none;" />
          ${cur.brand_name}</td>
        <td width="10%"><input name="button" type="button" class="btnImgBg" style="cursor:pointer" id="button" onclick="window.returnValue=document.getElementById('${cur.brand_id}').value;window.close();" />
        </td>
        <c:if test="${vs1.count mod 3 eq 0}">
          <c:out value="</tr>" escapeXml="false" />
          <c:out value="<tr>" escapeXml="false" />
        </c:if>
      </c:forEach>
    </tr>
    <c:if test="${empty entityList}">
	    <tr>
	    	<td align="center">
	    		<span style="color:#F00;font-size:13px;">抱歉！没有查询到品牌结果，点击<a href="${ctx}/client/manager/JxcPd.do?method=chooseBrand&pd_type=0" style="color:#0066FF;text-decoration:none">这儿</a>查询全部品牌试试？</span>
	    	</td>
	    </tr>
    </c:if>
  </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcPd.do">
    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "chooseBrand");
            pager.addHiddenInputs("brand_name", "${fn:escapeXml(af.map.brand_name)}");
            pager.addHiddenInputs("pd_type", "${af.map.pd_type}");
            document.write(pager.toString());
            </script></td>
      </tr>
    </table>
  </form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//alert("${af.map.pd_type}");
	$(".Tab tr").mouseover(function(){  
		$(this).addClass("over");
	}).mouseout(function(){
		$(this).removeClass("over");
	})
	$(".Tab tr:even").addClass("alteven");
	$(".Tab tr:odd").addClass("altodd");
})
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>