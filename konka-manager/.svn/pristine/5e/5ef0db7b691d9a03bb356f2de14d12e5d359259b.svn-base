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
    <html-el:hidden property="method" value="chooseAllPdModel" />
    <html-el:hidden property="pd_type" />
    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" class="TabTitle">
      <tr>
        <td width="10%">产品编码：</td>
        <td width="15%"><html-el:text property="pd_id" styleId="pd_id" style="border:1px solid #ccc;height:16px;line-height:16px;width:90%" /></td>
        <td width="10%" align="right" nowrap="nowrap">生产企业：</td>
        <td align="left" width="15%"><html-el:text property="entp_name_like" style="border:1px solid #ccc;height:16px;line-height:16px;width:90%" /></td>
        <td width="10%" align="right" nowrap="nowrap">型号名称：</td>
        <td align="left" width="25%"><html-el:text property="md_name_like" style="border:1px solid #ccc;height:16px;line-height:16px;width:90%" /></td>
        <td width="10%"><input name="submit" type="submit" class="bgButton" value="查 询" /></td>
      </tr>
    </table>
  </html-el:form>
  <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" class="Tab">
    <tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs1">
        <td title="企业名称：【${cur.map.entp_name}】" nowrap="nowrap" align="left">
          <html-el:text property="${cur.pd_id}" value="${cur.md_name},${cur.pd_id},${cur.pd_big_type},${cur.brand_id},${cur.map.brand_name}" styleId="${cur.pd_id}" style="display:none;" />
         产品编码:[${cur.pd_id}] ${cur.md_name}
        </td>
        <td width="15%"><input name="button" type="button" class="btnImgBg" style="cursor:pointer" id="button" onclick="window.returnValue=document.getElementById('${cur.pd_id}').value;window.close();" /></td>
        <c:if test="${vs1.count mod 2 eq 0}">
          <c:out value="</tr>" escapeXml="false" />
          <c:out value="<tr>" escapeXml="false" />
        </c:if>
      </c:forEach>
    </tr>
  </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcPd.do">
    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "chooseAllPdModel");
            pager.addHiddenInputs("md_name_like", "${fn:escapeXml(af.map.md_name_like)}");
            pager.addHiddenInputs("entp_name_like", "${fn:escapeXml(af.map.entp_name_like)}");
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
	$("#pd_id").focus(setOnlyInteger);
	$(".Tab tr:even").addClass("alteven");
	$(".Tab tr:odd").addClass("altodd");
})

function setOnlyInteger() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "").bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\d+)?|\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value='';this.o_value=this.value;}
		if(this.value.length == 0) this.value = '';
	});
}
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>