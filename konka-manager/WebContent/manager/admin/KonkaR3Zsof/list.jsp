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
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaZles23ResultInfo.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          	<strong class="fb">订单号：</strong>
          	<html-el:text property="vbeln" size="20" maxlength="20" styleId="vbeln" />
          </td>
          <td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <td nowrap="nowrap" align="left" >销售组织</td>
          <td nowrap="nowrap" align="left" >会计年度</td>
          <td nowrap="nowrap" align="left" >会计期间</td>
          <td nowrap="nowrap" align="left" >销售和分销凭证号</td>
          <td nowrap="nowrap" align="left" >交货单号</td>
          <td nowrap="nowrap" align="left" >交货日期</td>
          <td nowrap="nowrap" align="left" >收货日期</td>
          <td nowrap="nowrap" align="left" >创建日期</td>
          <td nowrap="nowrap" align="left" >客户编号1</td>
          <td nowrap="nowrap" align="left" >名称</td>
          <td nowrap="nowrap" align="left" >名称1</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="left" nowrap="nowrap">${cur.vkorg}</td>
            <td align="left" nowrap="nowrap">${cur.gjahr}</td>
            <td align="left" nowrap="nowrap">${cur.monat}</td>
            <td align="left" nowrap="nowrap">${cur.vbeln}</td>
            <td align="left" nowrap="nowrap">${cur.vbedl}</td>
            <td align="left" nowrap="nowrap">${cur.erdat}</td>
            <td align="left" nowrap="nowrap">${cur.lfdat}</td>
            <td align="left" nowrap="nowrap">${cur.shdat}</td>
            <td align="left" nowrap="nowrap">${cur.kunnr}</td>
            <td align="left" nowrap="nowrap">${cur.vtext}</td>
            <td align="left" nowrap="nowrap">${cur.name1}</td>
          </tr>
        </c:forEach>
      </table>
    <br />
    
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<jsp:include page="/__analytics.jsp" />
</body>
</html>