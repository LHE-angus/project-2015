<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width: 100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcThApply.do"
	enctype="multipart/form-data">
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1"
		class="tableClass">
        <tr>
          <th colspan="2">退货申请信息</th>
        </tr>
        <tr>
          <td width="10%" class="title_item">退货仓库：</td>
          <td>${fn:escapeXml(af.map.store_name)}</td>
        </tr>
        <tr>
          <td class="title_item">产品类别：</td>
          <td>${fn:escapeXml(af.map.pd_type_name)}</td>
        </tr>
        <tr>
          <td  class="title_item">退货产品类型：</td>
          <td><c:if test="${af.map.th_type eq 0}">残次品</c:if>
            <c:if test="${af.map.th_type eq 1}">正品</c:if></td>
        </tr>
        <tr>
          <td class="title_item">型号：</td>
          <td>${fn:escapeXml(af.map.pd_name)}</td>
        </tr>
        <tr>
          <td class="title_item">产品单价：</td>
          <td>${fn:escapeXml(af.map.price)}</td>
        </tr>
        <tr>
          <td class="title_item">退货数量：</td>
          <td>${af.map.th_count}</td>
        </tr>
        <tr>
          <td class="title_item">退货日期：</td>
          <td><fmt:formatDate value="${af.map.in_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
          <td class="title_item">退货原因：</td>
          <td>${fn:escapeXml(af.map.th_reason)}</td>
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
          <td colspan="2" align="center"><html-el:button property="back" value=" 返 回 " onclick="history.back();" styleClass="bgButtonBack" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
