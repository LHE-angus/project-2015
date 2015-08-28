<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body>
<div style="overflow-x: auto; height: 340px;">
<table width="100%" border="1" cellpadding="0" cellspacing="1"
	class="rtable2">
	<tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="4%">序号</td>
        <td nowrap="nowrap" align="center" width="4%">年度</td>
        <td nowrap="nowrap" align="center" width="4%">月度</td>
        <td nowrap="nowrap" align="center" width="10%">分公司</td>
        <td nowrap="nowrap" align="center" width="19%">客户名称</td>
        <td nowrap="nowrap" align="center" width="10%">客户R3编码</td>
        <td nowrap="nowrap" align="center" width="10%">客户类型</td>
        <td nowrap="nowrap" align="center" width="10%">R3分类</td>
        <td nowrap="nowrap" align="center" width="12%">业务员名称</td>
        <td nowrap="nowrap" align="center" width="8%">网点数</td>
        <td nowrap="nowrap" align="center" width="8%">门店数</td>
        <td nowrap="nowrap" align="center" width="10%">销售额</td>
        <td nowrap="nowrap" align="center" width="10%">平均单价</td>
        <td nowrap="nowrap" align="center" width="10%">结算平均单价</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList1}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="center">${fn:escapeXml(cur.map.year)}</td>
            <td align="center">${fn:escapeXml(cur.map.month) }</td>
            <td align="center">${fn:escapeXml(cur.map.fgs_name)}</td>
            <td align="left">${fn:escapeXml(cur.customer_name)}</td>
            <td align="center">${fn:escapeXml(cur.r3_code)}</td>
            <td align="center">
            	${fn:escapeXml(cur.map.c_name)}
            	<c:if test="${empty cur.map.c_name}"><span style="color:#ccc;">未指定</span></c:if>
            	</td>
            <td align="center">
            	<c:if test="${cur.is_sdf eq 0 }">售达方</c:if>
            	<c:if test="${cur.is_sdf eq 1 }">送达方</c:if>
            </td>
            <td align="center">${fn:escapeXml(cur.map.ywy_name)}</td>
            <td align="right">${fn:escapeXml(cur.agent_num)}</td>
            <td align="right">${fn:escapeXml(cur.store_num)}</td>
            <td align="right"><fmt:formatNumber value="${cur.month_retail_money}" type="currency"/></td>
            <td align="right">
            	<c:if test="${not empty cur.month_retail_num && cur.month_retail_num gt 0 }">
            		<fmt:formatNumber value="${(cur.month_retail_money / cur.month_retail_num) }" type="currency"/>
            	</c:if>
            </td>
            <td align="right">
            	<c:if test="${not empty cur.month_settle_num && cur.month_settle_num gt 0 }">
            		<fmt:formatNumber value="${(cur.month_settle_money / cur.month_settle_num) }" type="currency"/>
            	</c:if>
            </td>
          </tr>
        </c:forEach>
      </tbody>
</table>
</div>
</body>
</html>