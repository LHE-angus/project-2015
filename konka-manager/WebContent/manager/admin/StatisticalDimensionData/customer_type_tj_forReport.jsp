<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>测试</title>
</head>
<body>
<div style="overflow-x: auto; height: 340px;">
<table width="100%" border="1" cellpadding="0" cellspacing="1"
	class="rtable2">
	<tr class="tabtt1">
		<td rowspan="2" nowrap="nowrap" align="center" width="4%">序号</td>
		<td rowspan="2" nowrap="nowrap" align="center" width="8%">客户类型</td>
		<td colspan="3" nowrap="nowrap" align="center" width="24%">上一年度(${year_pre})</td>
		<td colspan="3" nowrap="nowrap" align="center" width="24%">本年度(${year_now})</td>
		<td colspan="5" nowrap="nowrap" align="center" width="40%">(${year_now})结算区间(万元)</td>
	</tr>
	<tr class="tabtt1">
		<td nowrap="nowrap" align="center" width="8%">客户总数</td>
		<td nowrap="nowrap" align="center" width="8%">新增无结算</td>
		<td nowrap="nowrap" align="center" width="8%">新增已结算</td>
		<td nowrap="nowrap" align="center" width="8%">客户总数</td>
		<td nowrap="nowrap" align="center" width="8%">新增无结算</td>
		<td nowrap="nowrap" align="center" width="8%">新增已结算</td>
		<td nowrap="nowrap" align="center" width="8%">≥500</td>
		<td nowrap="nowrap" align="center" width="8%">200~501</td>
		<td nowrap="nowrap" align="center" width="8%">100~201</td>
		<td nowrap="nowrap" align="center" width="8%">0~101</td>
		<td nowrap="nowrap" align="center" width="8%">≤0</td>
	</tr>
	<tbody>
        <c:forEach var="cur" items="${entityList1}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.map.c_name)}</td>
            <c:if test="${year_pre ge 2012}">
	            <td align="right">${fn:escapeXml(cur.map.customer_num_pre)}</td>
	            <td align="right">${fn:escapeXml(cur.map.new_without_settle_pre)}</td>
	            <td align="right">${fn:escapeXml(cur.map.new_with_settle_pre)}</td>
            </c:if>
            <c:if test="${year_pre lt 2012}">
	            <td align="center"><font color="grey">无数据</font></td>
	            <td align="center"><font color="grey">无数据</font></td>
	            <td align="center"><font color="grey">无数据</font></td>
            </c:if>
            <td align="right">${fn:escapeXml(cur.map.customer_num_now)}</td>
            <td align="right">${fn:escapeXml(cur.map.new_without_settle_now)}</td>
            <td align="right">${fn:escapeXml(cur.map.new_with_settle_now)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_805)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_804)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_803)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_802)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_801)}</td>
           
          </tr>
        </c:forEach>
      </tbody>
</table>
</div>
</body>
</html>