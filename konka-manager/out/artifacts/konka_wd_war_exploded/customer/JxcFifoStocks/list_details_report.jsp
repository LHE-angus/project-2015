<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="MSThemeCompatible" content="no" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
<div>
	<table width="100%" border="1" cellspacing="0" cellpadding="0">
		<tr class="tabtt1">
			<td width="5%" align="center">序号</td>
			<td nowrap="nowrap" align="center" width="8%">分公司</td>
			<td nowrap="nowrap" align="center" width="8%">R3编码</td>
			<td nowrap="nowrap" align="center" width="8%">客户名称</td>
			<td nowrap="nowrap" align="center" width="8%">进货批次</td>
			<td nowrap="nowrap" align="center" width="8%">产品型号</td>
			<td nowrap="nowrap" align="center" width="6%">数量</td>
			<td nowrap="nowrap" align="center" width="6%">状态</td>
			<td nowrap="nowrap" align="center" width="8%">进货成本</td>
			<td nowrap="nowrap" align="center" width="6%">进货时间</td>
			<td nowrap="nowrap" align="center" width="6%">库龄(天)</td>
			<td nowrap="nowrap" align="center" width="6%">进货仓库</td>
			<td nowrap="nowrap" align="center" width="6%">进货类型</td>
			<!-- 					<td nowrap="nowrap" align="center" width="6%">销售数量</td> -->
			<td nowrap="nowrap" align="center" width="8%">销售金额</td>
			<td nowrap="nowrap" align="center" width="8%">销售时间</td>
			<td nowrap="nowrap" align="center" width="6%">出货仓库</td>
			<td nowrap="nowrap" align="center" width="6%">出货类型</td>

		</tr>
		<tbody>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td align="center"
					nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
				<td align="center" nowrap="nowrap">${cur.subcomp_name}</td>
				<td align="center" nowrap="nowrap">${cur.r3_code}</td>
				<td align="center" nowrap="nowrap">${cur.customer_name}</td>

					<%--<td nowrap="nowrap" align="center">${cur.stock_in_batch}</td>--%>
				<td nowrap="nowrap" align="center">${cur.goods_model}</td>
				<td nowrap="nowrap" align="center">${cur.stock_in_num}</td>
				<td nowrap="nowrap" align="center">
					<c:if test="${cur.stock_state eq 10}">
						<font color="#006400">入仓</font>
					</c:if>
					<c:if test="${cur.stock_state eq 20}">
						<font color="blue">出仓</font>
					</c:if>
					<c:if test="${cur.stock_state eq 30}">
						<font color="#ff8c00">负卖</font>
					</c:if>
				</td>

				<td nowrap="nowrap" align="right"><font color="red">
					<fmt:formatNumber value=" ${cur.stock_in_price}"
									  pattern="00.00"/>
				</font></td>
				<td nowrap="nowrap" align="center">

					<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd"/>
					<fmt:formatDate
							value="${cur.stock_in_opr_date}" pattern="yyyy-MM-dd"/></td>

				<td align="right" nowrap="nowrap">
					<c:if test="${cur.stock_state eq 10  && not empty cur.stock_in_opr_date }">
						<c:set var="interval" value="${now_date.time - cur.stock_in_opr_date.time}"/>
						<fmt:formatNumber value="${interval/1000/60/60/24}" pattern="0"></fmt:formatNumber>
					</c:if>
					<c:if test="${cur.stock_state ne 10}"> -- </c:if>
				</td>

				<td nowrap="nowrap" align="center">${cur.map.stock_in_store_name}</td>
				<td>
					<c:choose>
						<c:when test="${cur.stock_in_type eq 10 }">地采</c:when>
						<c:when test="${cur.stock_in_type eq 20 }">集采</c:when>
						<c:when test="${cur.stock_in_type eq 30 }">初始化</c:when>
						<c:when test="${cur.stock_in_type eq 40 }">客户端采购</c:when>
						<c:when test="${cur.stock_in_type eq 50 }">盘盈</c:when>
						<c:when test="${cur.stock_in_type eq 60 }">零售通退货</c:when>
						<c:when test="${cur.stock_in_type eq 70 }">零售通销售无效化</c:when>
						<c:when test="${cur.stock_in_type eq 80 }">库存调拨（入）</c:when>
						<c:when test="${cur.stock_in_type eq 90 }">库存转仓（入）</c:when>
						<c:when test="${cur.stock_in_type eq 100 }">零售通导入（负数）</c:when>
						<c:when test="${cur.stock_in_type eq 110 }">销售退货</c:when>
						<c:when test="${cur.stock_in_type eq 120 }">分销退货</c:when>
					</c:choose>
				</td>
					<%-- 						<td nowrap="nowrap" align="center">${cur.stock_out_num}</td> --%>
				<td nowrap="nowrap" align="right">
					<font color="red">
						<fmt:formatNumber value="${cur.stock_out_price}"
										  pattern="00.00"/>
					</font>
				</td>
				<td nowrap="nowrap" align="center"><fmt:formatDate
						value="${cur.stock_out_opr_date}" pattern="yyyy-MM-dd"/></td>
				<td nowrap="nowrap" align="center">${cur.map.stock_out_store_name}</td>
				<td>
					<c:choose>
						<c:when test="${cur.stock_out_type eq 510 }">地采退货</c:when>
						<c:when test="${cur.stock_out_type eq 520 }">集采退货</c:when>
						<c:when test="${cur.stock_out_type eq 530 }">零售通销售</c:when>
						<c:when test="${cur.stock_out_type eq 540 }">分销</c:when>
						<c:when test="${cur.stock_out_type eq 550 }">专卖店销售</c:when>
						<c:when test="${cur.stock_out_type eq 560 }">盘亏</c:when>
						<c:when test="${cur.stock_out_type eq 570 }">库存调拨（出）</c:when>
						<c:when test="${cur.stock_out_type eq 580 }">库存转仓（出）</c:when>
						<c:when test="${cur.stock_out_type eq 590 }">产品初始化（负）</c:when>
						<c:when test="${cur.stock_out_type eq 600 }">零售通导入</c:when>
						<c:when test="${cur.stock_out_type eq 610 }">客户端销售</c:when>
						<c:when test="${cur.stock_out_type eq 620 }">采购退货</c:when>

					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>
