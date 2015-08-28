<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盘存管理&gt;盘存管理</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：盘存管理 &gt; 盘存管理</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcPcInfo">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="keySeq" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">盘存记录信息</th>
        </tr>
        <tr>
          <td width="15%" align="right" nowrap="nowrap" class="title_item">所属系统：</td>
          <td width="85%" align="left">
            <c:if test="${af.map.own_sys eq 0}">非家电下乡</c:if>
            <c:if test="${af.map.own_sys eq 1}">家电下乡</c:if>
          </td>
        </tr>
        <tr>
          <td width="15%" align="right" nowrap="nowrap" class="title_item">产品类型：</td>
          <td width="85%" align="left">${fn:escapeXml(af.map.pd_type_name)}</td>
        </tr>
        <tr>
          <td align="right"  nowrap="nowrap" class="title_item">产品品牌：</td>
          <td align="left">${fn:escapeXml(af.map.brand_name)}</td>
        </tr>
        <tr id="jdxx">
          <td align="right" nowrap="nowrap" class="title_item">产品型号：</td>
          <td align="left">${fn:escapeXml(af.map.pd_name)}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">盘存数量：</td>
          <td>${af.map.pc_num}</td>
        </tr>
        <tr id="pc_result_tr">
          <td nowrap="nowrap" class="title_item">盘存结果：</td>
          <td>${fn:escapeXml(af.map.pc_result)}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">详细信息：</td>
          <td>${fn:escapeXml(af.map.pc_desc)}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">盘存日期：</td>
          <td>
            <fmt:formatDate value="${af.map.pc_date}" pattern="yyyy-MM-dd"/>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">备注：</td>
          <td>${fn:escapeXml(af.map.remark)}</td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <input class="bgButtonBack" type="submit" name="pc_back" value="返 回" onclick="history.back();return false;" />
          </td>
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
