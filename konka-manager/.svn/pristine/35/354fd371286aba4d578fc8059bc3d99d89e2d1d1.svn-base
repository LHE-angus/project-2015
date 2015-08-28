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
.btnImgBg {
	width:52px;
	height:18px;
	line-height:18px;
	background:url(${ctx}/images/selectEntpMainButtonBg.jpg) no-repeat;
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
  <html-el:form action="/manager/JxcKonkaOrderRegister.do">
    <html-el:hidden property="method" value="chooseTradeIndex" />
    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" class="TabTitle">
      <tr>
        <td align="left" nowrap="nowrap">
        	进货单号：<html-el:text property="trade_index_like" />
        	&nbsp;进货日期：<html-el:text property="add_date_gt" styleId="add_date_gt" size="8" maxlength="8" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:80px;" disabled="${readonly}" />
       				 -&nbsp;<html-el:text property="add_date_lt" styleId="add_date_lt" size="8" maxlength="8" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:80px;" disabled="${readonly}" />
        &nbsp;<input name="submit" type="submit" style="cursor:pointer;" value="查 询" /></td>
      </tr>
    </table>
  </html-el:form>
  <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" class="Tab">
    <tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs1">
        <td width="20%" title="${cur.trade_index}" nowrap="nowrap"><c:out value="${cur.trade_index}" /></td>
        <td width="10%"><input name="button" type="button" class="btnImgBg" style="cursor:pointer" id="${cur.trade_index}" value="选 择" onclick="returnTradeIndex(this.id);" />
        </td>
        <c:if test="${vs1.count mod 3 eq 0}">
          <c:out value="</tr>" escapeXml="false" />
          <c:out value="<tr>" escapeXml="false" />
        </c:if>
      </c:forEach>
    </tr>
  </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcKonkaOrderRegister.do">
    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "chooseTradeIndex");
            pager.addHiddenInputs("trade_index_like", "${fn:escapeXml(af.map.trade_index_like)}");
            pager.addHiddenInputs("add_date_gt", "${fn:escapeXml(af.map.add_date_gt)}");
            pager.addHiddenInputs("add_date_lt", "${fn:escapeXml(af.map.add_date_lt)}");
            document.write(pager.toString());
            </script></td>
      </tr>
    </table>
  </form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
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

function returnTradeIndex(val){
	var api = frameElement.api, W = api.opener;
	W.document.getElementById("r_trade_index").value = val;
	W.SetRTradeIndexDetails(val);
	api.close();
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>