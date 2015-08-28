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
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oarcont" id="body_oarcont">
		<div>
		</div>
		<div class="rtabcont1" >
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="rtable2">
				<tr class="tabtt1">
					<th width="3%" nowrap="nowrap">序号</th>
					<th width="12%" nowrap="nowrap">单据编号</th>
					<th width="6%" nowrap="nowrap">盘点前数量</th>
					<th width="8%" nowrap="nowrap">盘点前金额</th>
					<th width="6%" nowrap="nowrap">盘点后数量</th>
					<th width="8%" nowrap="nowrap">盘点后金额</th>
					<th width="8%" nowrap="nowrap">业务类型</th>
					<th width="12%" nowrap="nowrap" >盘点类型</th>
					<th width="12%" nowrap="nowrap">差异原因</th>
					<th width="8%" nowrap="nowrap">盘点时间</th>

					<th width="8%" nowrap="nowrap">操作时间</th>
				</tr>
				<c:forEach items="${entityList}" var="cur" varStatus="vs">
					<tr>
						<td align="center" nowrap="nowrap" >${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
						<td align="center" nowrap="nowrap">${cur.bill_sn}</td>
						<td align="right">${cur.stocks}</td>
						<td align="right"><fmt:formatNumber value="${cur.money}" type="currency"/> </td>
						<td align="right">${cur.ver_stocks}</td>
						<td align="right"><fmt:formatNumber value="${cur.ver_money}" type="currency"/> </td>
						<td align="center" nowrap="nowrap">
							<c:if test="${cur.trade_type eq 30}"><span style="color:#CD0000;">盘亏</span></c:if>
							<c:if test="${cur.trade_type eq 31}"><span style="color:#009900;">盘盈</span></c:if>
							<c:if test="${cur.trade_type eq 0}">库实相符</c:if>
						</td>
						<td align="center" nowrap="nowrap" >
							<c:if test="${cur.type eq 1}" ><font color="green" >人工盘点</font></c:if>
							<c:if test="${cur.type ne 1}" ><font color="orange" >系统盘点</font></c:if>
						</td>
						<td align="center" nowrap="nowrap">${cur.memo}</td>
						<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.opr_date}" pattern="yyyy/MM/dd HH:mm" /></td>
						<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy/MM/dd HH:mm" /></td>
					</tr>
				</c:forEach>

			</table>


		</div>
	</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/rowEffect.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.cs.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/validator.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
	<script type="text/javascript">//<![CDATA[
$(document).ready(function() {
}
//]]></script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>