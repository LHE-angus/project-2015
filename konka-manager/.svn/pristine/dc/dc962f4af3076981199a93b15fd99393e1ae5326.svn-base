<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>打印</title>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="printTabTop">
  <tr>
    <td colspan="7" align="center" style="font-weight:bold;font-size: 18px;"> ${af.map.sell_date_eq}${customer_name}补贴统计</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tbody>
        <tr>
          <th width="5%" align="center" nowrap="nowrap">序号</th>
          <th width="10%" align="center" nowrap="nowrap">产品唯一编码</th>
          <th nowrap="nowrap" align="center">产品型号</th>
          <th width="10%" align="center" nowrap="nowrap">销售单号</th>
          <th width="10%" nowrap="nowrap" align="center">销售企业</th>
          <th width="6%" nowrap="nowrap" align="center">单价（元）</th>
          <th width="6%" nowrap="nowrap" align="center">补贴金额（元）</th>
          <th width="6%" nowrap="nowrap" align="center">购买人</th>
          <th width="8%" nowrap="nowrap" align="center">联系电话</th>
          <th width="8%" nowrap="nowrap" align="center">销售时间</th>
        </tr>
        <c:if test="${not empty entityList}">
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_unique_code)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_name)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.sn)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.shop_name)}</td>
            <td align="center" nowrap="nowrap"><span class="kz-price-12">
              <fmt:formatNumber value="${cur.map.pd_price}" type="currency" />
              </span></td>
            <td align="center" nowrap="nowrap"><span class="kz-price-12">
              <fmt:formatNumber value="${cur.map.pd_allowance_money}" type="currency" />
              </span></td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.customer_name)}</td>
            <td align="center" nowrap="nowrap">
              <c:if test="${not empty cur.map.customer_tel}">${cur.map.customer_tel}</c:if>
              <c:if test="${empty cur.map.customer_tel}"><span style="color:gray;">未填写</span></c:if>
              </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.sell_date}" pattern="yyyy-MM-dd" /></td>
          </tr>
        </c:forEach>
        <tr>
        <td colspan="6" align="left">合计</td>
        <td align="center" > <fmt:formatNumber value="${total_allowance_money}" type="currency" /></td>
        <td colspan="3" align="left">&nbsp;</td>
        </tr>
        </c:if>
        <c:if test="${empty entityList}">
        <tr><td height="30" align="center" nowrap="nowrap" colspan="10"><font color="red">无数据</font></td></tr>
        </c:if>
      </tbody>
    </table>
<c:if test="${not empty entityList}">
<div align="center">
  <input name="button" type="button" class="bgButtonPrint" value="打印" onclick="this.style.display='none';window.print();"/>&nbsp;&nbsp;
  <input name="button" type="button" class="bgButton" value="关闭" style="cursor: pointer; " onclick="window.close();" />
</div>
</c:if>
<c:if test="${empty entityList}">
  <div align="center">
  	<input name="button" type="button" class="bgButton" value="关闭" onclick="window.close();" />
  </div>
</c:if>
<jsp:include page="/__analytics.jsp" />
</body>
</html>