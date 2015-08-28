<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<div style="text-align: right; margin-right: 20px;">单位：万元</div>
<table class="table table-striped table-bordered table-hover"
	style="width: 100%; font-size: 12px; line-height: 22px;">
	
	<tr style="">
		<td align="center" width="60">区域</td>
		<td align="center">目标销售额</td>
		<td align="center">实际销售额</td>
		<td align="center">同期销售额</td>
		<td align="center">完成率</td>
		<td align="center">增长率</td>
		<td align="center">实际销售额占比</td>
		<td align="center">累计目标额</td>
		<td align="center">累计销售额</td>
		<td align="center">累计完成率</td>
	</tr>
	<c:set var="s_month_customer_sale" value="0"></c:set>
	<c:set var="s_month_settle_money" value="0"></c:set>
	<c:set var="s_pre_year_settle_money" value="0"></c:set>
	<c:set var="s_month_settle_money_tbzf" value="0"></c:set>
	<c:set var="s_month_settle_money_bz" value="0"></c:set>
	<c:set var="s_customer_sale_lj" value="0"></c:set>
	<c:set var="s_settle_money_lj" value="0"></c:set>
	<c:set var="s_fgs_month_settle_money" value="0"></c:set>
	<c:forEach items="${areaList}" var="cur" varStatus="vs">
		<c:set var="s_fgs_month_settle_money"
			value="${s_fgs_month_settle_money + cur.month_settle_money}"></c:set>
	</c:forEach>
	<c:forEach items="${areaList}" var="cur" varStatus="vs">
		<c:set var="s_month_customer_sale"
			value="${s_month_customer_sale + cur.month_customer_sale}"></c:set>
		<c:set var="s_month_settle_money"
			value="${s_month_settle_money + cur.month_settle_money}"></c:set>
		<c:set var="s_pre_year_settle_money"
			value="${s_pre_year_settle_money + cur.pre_year_settle_money}"></c:set>
		<c:set var="s_month_settle_money_bz"
			value="${s_month_settle_money_bz + cur.month_settle_money_bz}"></c:set>
		<c:set var="s_customer_sale_lj"
			value="${s_customer_sale_lj + cur.map.customer_sale_lj}"></c:set>
		<c:set var="s_settle_money_lj"
			value="${s_settle_money_lj + cur.map.settle_money_lj}"></c:set>
		<tr style="">
			<td align="center">${cur.map.name }</td>
			<td align="right" style="color: blue;"><fmt:formatNumber
					value="${cur.month_customer_sale}" pattern="0.00" /></td>
			<td align="right" style="color: red;"><fmt:formatNumber
					value="${cur.month_settle_money}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber
					value="${cur.pre_year_settle_money}" pattern="0.00" /></td>
			<td align="center"><c:if
					test="${cur.month_customer_sale gt 0 or cur.month_customer_sale lt 0 }">
					<fmt:formatNumber
						value="${cur.month_settle_money*100/cur.month_customer_sale}"
						pattern="0.00" />%
		        	</c:if> <c:if
					test="${!(cur.month_customer_sale gt 0 or cur.month_customer_sale lt 0) }">
		        		-
		        	</c:if></td>
			<td align="right"><c:if
					test="${cur.pre_year_settle_money gt 0 or cur.pre_year_settle_money lt 0 }">
					<c:if test="${cur.month_settle_money gt cur.pre_year_settle_money}">
						<fmt:formatNumber
							value="${(cur.month_settle_money-cur.pre_year_settle_money)*100/cur.pre_year_settle_money}"
							pattern="0.00" />%
		        			<font color="green">↑</font>
					</c:if>
					<c:if test="${cur.month_settle_money lt cur.pre_year_settle_money}">
						<fmt:formatNumber
							value="${(cur.month_settle_money-cur.pre_year_settle_money)*100/cur.pre_year_settle_money}"
							pattern="0.00" />%
		        			<font color="red">↓</font>
					</c:if>
				</c:if> <c:if
					test="${!(cur.month_settle_money gt 0 or cur.pre_year_settle_money lt 0) }">
		        		-
		        	</c:if></td>
			<td align="right"><c:if
					test="${s_fgs_month_settle_money gt 0 or s_fgs_month_settle_money lt 0 }">
					<fmt:formatNumber
						value="${cur.month_settle_money*100/s_fgs_month_settle_money}"
						pattern="0.00" />%
		        	</c:if> <c:if
					test="${!(s_fgs_month_settle_money gt 0 or s_fgs_month_settle_money lt 0) }">
		        		-
		        	</c:if></td>
			<td align="right" style="color: blue;"><fmt:formatNumber
					value="${cur.map.customer_sale_lj}" pattern="0.00" /></td>
			<td align="right" style="color: red;"><fmt:formatNumber
					value="${cur.map.settle_money_lj}" pattern="0.00" /></td>
			<td align="center"><c:if
					test="${cur.map.customer_sale_lj gt 0 or cur.map.customer_sale_lj lt 0 }">
					<fmt:formatNumber
						value="${cur.map.settle_money_lj*100/cur.map.customer_sale_lj}"
						pattern="0.00" />%
		        	</c:if> <c:if
					test="${!(cur.map.customer_sale_lj gt 0 or cur.map.customer_sale_lj lt 0) }">
		        		-
		        	</c:if></td>
		</tr>
	</c:forEach>
	<tr style="">
		<td align="center">合计</td>
		<td align="right" style="color: blue;"><fmt:formatNumber
				value="${s_month_customer_sale}" pattern="0.00" /></td>
		<td align="right" style="color: red;"><fmt:formatNumber
				value="${s_month_settle_money}" pattern="0.00" /></td>
		<td align="right"><fmt:formatNumber
				value="${s_pre_year_settle_money}" pattern="0.00" /></td>
		<td align="center"><c:if
				test="${s_month_customer_sale gt 0 or s_month_customer_sale lt 0 }">
				<fmt:formatNumber
					value="${s_month_settle_money*100/s_month_customer_sale}"
					pattern="0.00" />%
		        	</c:if> <c:if
				test="${!(s_month_customer_sale gt 0 or s_month_customer_sale lt 0) }">
		        		-
		        	</c:if></td>
		<td align="right"><c:if
				test="${s_pre_year_settle_money gt 0 or s_pre_year_settle_money lt 0 }">
				<c:if test="${s_month_settle_money gt s_pre_year_settle_money}">
					<fmt:formatNumber
						value="${(s_month_settle_money-s_pre_year_settle_money)*100/s_pre_year_settle_money}"
						pattern="0.00" />%
		        			<font color="green">↑</font>
				</c:if>
				<c:if test="${s_month_settle_money lt s_pre_year_settle_money}">
					<fmt:formatNumber
						value="${(s_month_settle_money-s_pre_year_settle_money)*100/s_pre_year_settle_money}"
						pattern="0.00" />%
		        			<font color="red">↓</font>
				</c:if>
			</c:if> <c:if
				test="${!(s_pre_year_settle_money gt 0 or s_pre_year_settle_money lt 0) }">
		        		-
		        	</c:if></td>
		<td align="right">100%</td>
		<td align="right" style="color: blue;"><fmt:formatNumber
				value="${s_customer_sale_lj}" pattern="0.00" /></td>
		<td align="right" style="color: red;"><fmt:formatNumber
				value="${s_settle_money_lj}" pattern="0.00" /></td>
		<td align="center"><c:if
				test="${s_customer_sale_lj gt 0 or s_customer_sale_lj lt 0 }">
				<fmt:formatNumber
					value="${s_settle_money_lj*100/s_customer_sale_lj}" pattern="0.00" />%
		        	</c:if> <c:if
				test="${!(s_customer_sale_lj gt 0 or s_customer_sale_lj lt 0) }">
		        		-
		        	</c:if></td>
	</tr>
</table>
