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
    <html-el:form action="/admin/KonkaR3Shop.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          	<strong class="fb">客户名称：</strong>
          	<html-el:text property="customer_name_like" size="20" maxlength="20" styleId="customer_name_like" />
          </td>
          <td>
          	<strong class="fb">分公司所在地：</strong>
          	<html-el:text property="branch_area_name_like" size="20" maxlength="20" styleId="branch_area_name_like" />
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
          <td nowrap="nowrap" align="left" >客户名称</td>
          <td width="5%" align="center" nowrap="nowrap">客户群类型</td>
          <td nowrap="nowrap" width="10%">R3编码</td>
         <!--  <td nowrap="nowrap" width="10%">经办名称</td> -->
          <td nowrap="nowrap" width="10%">分公司所在地名称</td>
          <td nowrap="nowrap" width="10%">合并客户编码</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.customer_name}</td>
            <td align="center" nowrap="nowrap">${cur.customer_type}</td>
            <td align="left">${fn:escapeXml(cur.r3_code)}</td>
             <!--
            <td align="left" >${fn:escapeXml(cur.handle_name)}</td>
            -->
            <td align="left" >${fn:escapeXml(cur.branch_area_name)}</td>
            <td align="left" >${fn:escapeXml(cur.customer_code)}</td>
            <td align="center" nowrap="nowrap"><a href="${ctx}/manager/admin/JxcKonkaOrderRegister.do?method=add&mod_id=${af.map.mod_id}&cust_id=${cur.id}"><span style="cursor:pointer;" class="fblue">下单</span></a>&nbsp;<a href="${ctx}/manager/admin/JxcKonkaOrderRegister.do?method=list&mod_id=${af.map.mod_id}&cust_id=${cur.id}"><span style="cursor:pointer;" class="fblue">历史记录</span></a></td>
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