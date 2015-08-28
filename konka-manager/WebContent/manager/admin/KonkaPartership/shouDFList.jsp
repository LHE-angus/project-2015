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
<base target="_self" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaPartership.do">
      <html-el:hidden property="method" value="shouDFList" />
      <html-el:hidden property="id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          	<strong class="fb">R3编码：</strong>
          	<html-el:text property="code_like" size="20" maxlength="40" styleId="code_like" />
          </td>
          <td>
          	<strong class="fb">客户名称：</strong>
          	<html-el:text property="customer_name_like" size="20" maxlength="40" styleId="customer_name_like" />
          </td>
          
          <td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" width="10%">R3编码</td>
          <td nowrap="nowrap" width="10%">客户名称</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left">${fn:escapeXml(cur.r3_code)}</td>
            <td align="left">${fn:escapeXml(cur.customer_name)}</td>
            <td align="center" nowrap="nowrap"><a href="#" id="sel" onclick="selectCust('${cur.r3_code}')">选择</a></td>
          </tr>
        </c:forEach>
      </table>
    <br />
    
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
function selectCust(r3_code){
	window.returnValue = {'r3_code' : r3_code};
	if (window.opener && window.opener != null) window.opener.returnValue = {'r3_code' : r3_code};
	window.close();
}
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>