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
    <html-el:form action="/spgl/EcUserForFgs.do">
      <html-el:hidden property="method" value="listCustomer" />
      <html-el:hidden property="fgs_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
            <strong class="fb">客户名称：</strong>
          	<html-el:text property="customer_name_like" size="15" maxlength="40" styleId="customer_name_like" />
          	&nbsp;<strong class="fb">R3CODE：</strong>
          	<html-el:text property="r3_code" size="10" maxlength="20" styleId="r3_code" />
          	&nbsp;<strong class="fb">真实姓名：</strong>
          	<html-el:text property="real_name_like" size="15" maxlength="40" styleId="real_name_like" />
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
          <td nowrap="nowrap" align="left" >客户</td>
          <td nowrap="nowrap" align="left" >R3CODE</td>
          <td width="10%" nowrap="nowrap" align="center" >登录名</td>
          <td width="5%" align="center" nowrap="nowrap">真实姓名</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.map.customer_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.r3_code}</td>
            <td align="center" nowrap="nowrap">${cur.user_name}</td>
            <td align="center" nowrap="nowrap">${cur.real_name}</td>
            <td align="center" nowrap="nowrap"><a href="#" id="sel" onclick="selectCust('${cur.id}','${cur.cust_id}','${cur.real_name}')">选择</a></td>
          </tr>
        </c:forEach>
      </table>
    <br />
    
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
function selectCust(c_id,cust_id,real_name){
	window.returnValue = { 'c_id' : c_id, 'cust_id' : cust_id,'real_name':real_name};
	if (window.opener && window.opener != null) window.opener.returnValue = {'c_id' : c_id, 'cust_id' : cust_id,'real_name':real_name};
	window.close();
}
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>