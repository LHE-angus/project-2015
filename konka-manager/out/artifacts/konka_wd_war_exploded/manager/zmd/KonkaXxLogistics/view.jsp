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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxLogistics">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="logistics_id" value="${af.map.logistics_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
          <td width="85%">${fn:escapeXml(dept_name)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">产品型号：</td>
          <td width="85%"><c:out value="${af.map.md_name}" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">发货地区：</td>
          <td width="85%"><c:out value="${p_name_s}" />
            <c:if test="${empty p_name_s}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">收货地区：</td>
          <td width="85%"><c:out value="${p_name_e}" />
            <c:if test="${empty p_name_e}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">物流费用：</td>
          <td width="85%"><span class="kz-price">
            <fmt:formatNumber type="currency" value="${af.map.fee}" />
            </span></td>
        </tr>
        <tr>
          <td colspan="2"><input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
