<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退换货管理 &gt; 换货申请</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：退换货管理 &gt; 换货申请</div>
  <div class="rtabcont1">
    <html-el:form action="/JxchhApply">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">查看盘存记录</th>
        </tr>
        <tr>
          <td width="15%"  nowrap="nowrap"  class="title_item">产品类型：</td>
          <td width="85%" align="left">${fn:escapeXml(af.map.pd_type_name)}</td>
        </tr>
        <tr>
          <td  class="title_item">产品品牌：</td>
          <td align="left">${fn:escapeXml(af.map.brand_name)}</td>
        </tr>
        <tr id="jdxx">
          <td class="title_item">产品型号：</td>
          <td align="left">${fn:escapeXml(af.map.pd_name)}</td>
        </tr>
        <tr>
          <td class="title_item">换货产品类型： </td>
          <td><c:choose>
              <c:when test="${af.map.hh_type eq 0}">残次品 </c:when>
              <c:when test="${af.map.hh_type eq 1}">正品</c:when>
            </c:choose></td>
        </tr>
        <tr>
          <td class="title_item">产品单价：</td>
          <td>${fn:escapeXml(af.map.price)}</td>
        </tr>
        <tr>
          <td class="title_item">换货数量：</td>
          <td>${fn:escapeXml(af.map.hh_count)}</td>
        </tr>
        <tr>
          <td class="title_item">换货日期：</td>
          <td><fmt:formatDate value="${af.map.in_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
          <td class="title_item">换货原因：</td>
          <td>${fn:escapeXml(af.map.hh_reason)}</td>
        </tr>
        <tr>
          <td class="title_item">备注：</td>
          <td>${fn:escapeXml(af.map.remark)}</td>
        </tr>
        <tr>
          <td class="title_item">审核状态：</td>
          <td><c:choose>
              <c:when test="${af.map.is_audit eq 0}">未审批</c:when>
              <c:when test="${af.map.is_audit eq -1}"><span style="color:#F00;">未通过</span></c:when>
              <c:when test="${af.map.is_audit eq 1}"><span style="color:#060;">已通过</span></c:when>
            </c:choose></td>
        </tr>
        <c:if test="${not empty af.map.approval_date}">
          <tr>
            <td class="title_item">审批时间：</td>
            <td><fmt:formatDate value="${af.map.approval_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
        </c:if>
        <c:if test="${af.map.is_audit ne 0}">
          <tr>
            <td class="title_item">审批意见：</td>
            <td>${fn:escapeXml(af.map.audit_reason)}</td>
          </tr>
        </c:if>
        <tr>
          <td colspan="2" align="center"><html-el:button property="back" styleClass="bgButtonBack" value="返 回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
