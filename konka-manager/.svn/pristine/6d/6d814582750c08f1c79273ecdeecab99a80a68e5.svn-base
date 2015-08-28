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
		<td nowrap="nowrap" align="center" width="8%">分公司</td>
		<td nowrap="nowrap" align="center" width="6%">客户类型</td>
		<td nowrap="nowrap" align="center" width="10%">客户R3编码</td>
		<td nowrap="nowrap" align="center" width="6%">R3分类</td>
		<td nowrap="nowrap" align="center" width="20%">客户名称</td>
		<td nowrap="nowrap" align="center" width="6%">业务员</td>
		<td nowrap="nowrap" align="center" width="8%">加盟时间</td>
		<td nowrap="nowrap" align="center" width="8%">加盟年限</td>
		<td nowrap="nowrap" align="center" width="8%">上年结算额</td>
		<td nowrap="nowrap" align="center" width="8%">本年结算额</td>
		<td nowrap="nowrap" align="center" width="4%">增长率</td>
		<td nowrap="nowrap" align="center" width="4%">贡献率</td>
	</tr>
	<tbody>
		<c:forEach var="cur" items="${entityList1}" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap">${(af.map.pager.currentPage
				- 1) * af.map.pager.pageSize + vs.count}</td>
				<td align="center">${fn:escapeXml(cur.map.fgs_name)}</td>
				<td align="center">${fn:escapeXml(cur.map.c_name)} <c:if
					test="${empty cur.map.c_name}">
					<span style="color: #ccc;">未指定</span>
				</c:if></td>
				<td align="center">${fn:escapeXml(cur.r3_code)}</td>
				<td align="center">
				<c:if test="${cur.is_sdf eq 0}">售达方</c:if>
				<c:if test="${cur.is_sdf eq 1}">送达方</c:if>
				</td>
				<td align="left">${fn:escapeXml(cur.customer_name)}</td>
				<td align="center">${fn:escapeXml(cur.map.ywy_name)}</td>
				<td align="center"><fmt:formatDate value="${cur.join_date}"
					pattern="yyyy-MM-dd"></fmt:formatDate></td>
				<td align="center">${fn:escapeXml(cur.join_date_type) }</td>
				<!-- 加盟年限 -->
				<td align="right"><c:if
					test="${cur.pre_annual_settle_money ne null && cur.pre_annual_settle_money ne 0}">
					<fmt:formatNumber value="${cur.pre_annual_settle_money}"
						type="currency" pattern="0.00"></fmt:formatNumber>
				</c:if> <c:if test="${cur.pre_annual_settle_money eq null}">0.00</c:if></td>
				<td align="right"><c:if
					test="${cur.annual_settle_money ne null && cur.annual_settle_money ne 0}">
					<fmt:formatNumber value="${cur.annual_settle_money}"
						type="currency" pattern="0.00"></fmt:formatNumber>
				</c:if> <c:if test="${cur.annual_settle_money eq null}">0.00</c:if></td>
				<td align="right"><c:if
					test="${cur.annual_settle_money_tbzf ne null && cur.annual_settle_money_tbzf ne 0}">
					<fmt:formatNumber value="${(cur.annual_settle_money_tbzf) / 100}"
						type="percent" pattern="0.00%"></fmt:formatNumber>
				</c:if> <c:if test="${cur.annual_settle_money_tbzf eq null}">0.00%</c:if></td>
				<td align="right"><c:if
					test="${cur.annual_settle_money_bz ne null && cur.annual_settle_money_bz ne 0}">
					<fmt:formatNumber value="${(cur.annual_settle_money_bz / 100)}"
						type="currency" pattern="0.00%"></fmt:formatNumber>
				</c:if> <c:if test="${cur.annual_settle_money_bz eq null}">0.00%</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>