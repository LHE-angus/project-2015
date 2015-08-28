<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<table width="100%" border="0" cellspacing="5" cellpadding="0"
	class="table table-striped table-bordered table-hover">
	<tr>
		<td align="center" width="120" rowspan="2">部门</td>
		<td align="center" rowspan="2">实际零售额</td>
		<td align="center" rowspan="2">同期零售额</td>
		<td align="center" rowspan="2">增长率</td>
		<td align="center" rowspan="2">累计零售额</td>
		<td align="center" colspan="7">各渠道零售额</td>
	</tr>
	<tr>
		<td align="center">国美</td>
		<td align="center">苏宁</td>
		<td align="center">其他连锁</td>
		<td align="center">超市渠道</td>
		<td align="center">城市客户</td>
		<td align="center">县乡客户</td>
		<td align="center">其他客户</td>
	</tr>
	<c:set var="s_month_retail_money_task" value="0"></c:set>
	<c:set var="s_month_retail_money" value="0"></c:set>
	<c:set var="s_pre_year_retail_money" value="0"></c:set>
	<c:set var="s_month_retail_money_tbzf" value="0"></c:set>
	<c:set var="s_month_retail_money_bz" value="0"></c:set>
	<c:set var="s_month_retail_money_task_lj" value="0"></c:set>
	<c:set var="s_retail_money_lj" value="0"></c:set>
	<c:set var="s_fgs_month_retail_money" value="0"></c:set>

	<c:set var="s_month_retail_money_gm" value="0"></c:set>
	<c:set var="s_month_retail_money_sn" value="0"></c:set>
	<c:set var="s_month_retail_money_qtls" value="0"></c:set>
	<c:set var="s_month_retail_money_csqd" value="0"></c:set>
	<c:set var="s_month_retail_money_cskh" value="0"></c:set>
	<c:set var="s_month_retail_money_xxkh" value="0"></c:set>
	<c:set var="s_month_retail_money_qtkh" value="0"></c:set>

	<c:forEach items="${fgsList}" var="cur" varStatus="vs">
		<c:set var="s_fgs_month_retail_money"
			value="${s_fgs_month_retail_money + cur.month_retail_money}"></c:set>
	</c:forEach>

	<c:forEach items="${fgsList}" var="cur" varStatus="vs">
		<c:set var="s_month_retail_money_task"
			value="${s_month_retail_money_task + cur.month_retail_money_task}"></c:set>
		<c:set var="s_month_retail_money"
			value="${s_month_retail_money + cur.month_retail_money}"></c:set>
		<c:set var="s_pre_year_retail_money"
			value="${s_pre_year_retail_money + cur.pre_year_retail_money}"></c:set>
		<c:set var="s_month_retail_money_bz"
			value="${s_month_retail_money_bz + cur.month_retail_money_bz}"></c:set>
		<c:set var="s_month_retail_money_task_lj"
			value="${s_month_retail_money_task_lj + cur.map.month_retail_money_task_lj}"></c:set>
		<c:set var="s_retail_money_lj"
			value="${s_retail_money_lj + cur.map.retail_money_lj}"></c:set>
		<c:set var="s_month_retail_money_gm"
			value="${s_month_retail_money_gm + cur.map.month_retail_money_gm}"></c:set>
		<c:set var="s_month_retail_money_sn"
			value="${s_month_retail_money_sn + cur.map.month_retail_money_sn}"></c:set>
		<c:set var="s_month_retail_money_qtls"
			value="${s_month_retail_money_qtls + cur.map.month_retail_money_qtls}"></c:set>
		<c:set var="s_month_retail_money_csqd"
			value="${s_month_retail_money_csqd + cur.map.month_retail_money_csqd}"></c:set>
		<c:set var="s_month_retail_money_cskh"
			value="${s_month_retail_money_cskh + cur.map.month_retail_money_cskh}"></c:set>
		<c:set var="s_month_retail_money_xxkh"
			value="${s_month_retail_money_xxkh + cur.map.month_retail_money_xxkh}"></c:set>
		<c:set var="s_month_retail_money_qtkh"
			value="${s_month_retail_money_qtkh + cur.map.month_retail_money_qtkh}"></c:set>
		<tr>
			<td align="center">${cur.map.name }</td>

			<td align="right" style="color: red;"><fmt:formatNumber
					value="${cur.month_retail_money}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber
					value="${cur.pre_year_retail_money}" pattern="0.00" /></td>

			<td align="right"><c:if
					test="${cur.pre_year_retail_money gt 0 or cur.pre_year_retail_money lt 0 }">
					<c:if test="${cur.month_retail_money gt cur.pre_year_retail_money}">
						<fmt:formatNumber
							value="${(cur.month_retail_money-cur.pre_year_retail_money)*100/cur.pre_year_retail_money}"
							pattern="0.00" />%
		        			<font color="green">↑</font>
					</c:if>
					<c:if test="${cur.month_retail_money lt cur.pre_year_retail_money}">
						<fmt:formatNumber
							value="${(cur.month_retail_money-cur.pre_year_retail_money)*100/cur.pre_year_retail_money}"
							pattern="0.00" />%
		        			<font color="red">↓</font>
					</c:if>
				</c:if> <c:if
					test="${!(cur.month_retail_money gt 0 or cur.pre_year_retail_money lt 0) }">
		        		-
		        	</c:if></td>

			<td align="right" style="color: red;"><fmt:formatNumber
					value="${cur.map.retail_money_lj}" pattern="0.00" /></td>

			<td align="right"><fmt:formatNumber
					value="${cur.map.month_retail_money_gm}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber
					value="${cur.map.month_retail_money_sn}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber
					value="${cur.map.month_retail_money_qtls}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber
					value="${cur.map.month_retail_money_csqd}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber
					value="${cur.map.month_retail_money_cskh}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber
					value="${cur.map.month_retail_money_xxkh}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber
					value="${cur.map.month_retail_money_qtkh}" pattern="0.00" /></td>
		</tr>
	</c:forEach>
	<tr>
		<td align="center">合计</td>
		<td align="right" style="color: blue;"><fmt:formatNumber
				value="${s_month_retail_money}" pattern="0.00" /></td>

		<td align="right"><fmt:formatNumber
				value="${s_pre_year_retail_money}" pattern="0.00" /></td>

		<td align="center"><c:if
				test="${s_pre_year_retail_money gt 0 or s_pre_year_retail_money lt 0 }">
				<c:if test="${s_month_retail_money gt s_pre_year_retail_money}">
					<fmt:formatNumber
						value="${(s_month_retail_money-s_pre_year_retail_money)*100/s_pre_year_retail_money}"
						pattern="0.00" />%
		        			<font color="green">↑</font>
				</c:if>
				<c:if test="${s_month_retail_money lt s_pre_year_retail_money}">
					<fmt:formatNumber
						value="${(s_month_retail_money-s_pre_year_retail_money)*100/s_pre_year_retail_money}"
						pattern="0.00" />%
		        			<font color="red">↓</font>
				</c:if>
			</c:if> <c:if
				test="${!(s_pre_year_retail_money gt 0 or s_pre_year_retail_money lt 0) }">
		        		-
		        	</c:if></td>

		<td align="right" style="color: red;"><fmt:formatNumber
				value="${s_retail_money_lj}" pattern="0.00" /></td>

		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money_gm}" pattern="0.00" /></td>
		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money_sn}" pattern="0.00" /></td>
		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money_qtls}" pattern="0.00" /></td>
		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money_csqd}" pattern="0.00" /></td>
		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money_cskh}" pattern="0.00" /></td>
		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money_xxkh}" pattern="0.00" /></td>
		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money_qtkh}" pattern="0.00" /></td>
	</tr>
</table>
