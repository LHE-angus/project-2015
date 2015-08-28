<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>

<table width="100%" border="0" cellspacing="5" cellpadding="0"
	class="table table-striped table-bordered table-hover">

	<th align="center" width="10%">区域</th>
	<th align="center" width="10%">目标零售额</th>
	<th align="center" width="10%">实际零售额</th>
	<!--  th align="center">同期零售额</th>-->
	<th align="center" width="10%">完成率</th>
	<!--<th align="center">增长率</th>-->
	<!--<th align="center">实际零售额占比</th>-->
	<th align="center" width="10%">累计目标额</th>
	<th align="center" width="10%">累计零售额</th>
	<th align="center" width="10%">累计完成率</th>

	<c:set var="s_month_retail_money_task" value="0"></c:set>
	<c:set var="s_month_retail_money" value="0"></c:set>
	<c:set var="s_pre_month_retail_money" value="0"></c:set>
	<c:set var="s_month_retail_money_tbzf" value="0"></c:set>
	<c:set var="s_month_retail_money_bz" value="0"></c:set>
	<c:set var="s_month_retail_money_task_lj" value="0"></c:set>
	<c:set var="s_retail_money_lj" value="0"></c:set>
	<c:set var="s_fgs_month_retail_money" value="0"></c:set>
	<c:forEach items="${areaList}" var="cur" varStatus="vs">
		<c:set var="s_fgs_month_retail_money"
			value="${s_fgs_month_retail_money + cur.month_retail_money}"></c:set>
	</c:forEach>
	<c:forEach items="${areaList}" var="cur" varStatus="vs">
		<c:set var="s_month_retail_money_task" value="${s_month_retail_money_task + cur.month_retail_money_task}"></c:set>
		<c:set var="s_month_retail_money" value="${s_month_retail_money + cur.month_retail_money}"></c:set>
		<c:set var="s_pre_month_retail_money" value="${s_pre_month_retail_money + cur.pre_month_retail_money}"></c:set>
		<c:set var="s_month_retail_money_bz" value="${s_month_retail_money_bz + cur.month_retail_money_bz}"></c:set>
		<c:set var="s_month_retail_money_task_lj" value="${s_month_retail_money_task_lj + cur.map.month_retail_money_task_lj}"></c:set>
		<c:set var="s_retail_money_lj" value="${s_retail_money_lj + cur.map.retail_money_lj}"></c:set>
		<tr>
			<td align="center">${cur.map.name }</td>
			<td align="right"><fmt:formatNumber 	value="${cur.month_retail_money_task}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber value="${cur.month_retail_money}" pattern="0.00" /></td>
			<!--  td align="right"><fmt:formatNumber
					value="${cur.pre_month_retail_money}" pattern="0.00" /></td>-->
			<td align="center"><c:if
					test="${cur.month_retail_money_task gt 0 or cur.month_retail_money_task lt 0 }">
					<fmt:formatNumber
						value="${cur.month_retail_money*100/cur.month_retail_money_task}"
						pattern="0.00" />%
		        	</c:if> <c:if
					test="${!(cur.month_retail_money_task gt 0 or cur.month_retail_money_task lt 0) }">
		        		-
		        	</c:if></td>
			<!--<td align="right"><c:if
					test="${cur.pre_month_retail_money gt 0 or cur.pre_month_retail_money lt 0 }">
					<c:if
						test="${cur.month_retail_money gt cur.pre_month_retail_money}">
						<fmt:formatNumber
							value="${(cur.month_retail_money-cur.pre_month_retail_money)*100/cur.pre_month_retail_money}"
							pattern="0.00" />%
		        			<font color="green">↑</font>
					</c:if>
					<c:if
						test="${cur.month_retail_money lt cur.pre_month_retail_money}">
						<fmt:formatNumber
							value="${(cur.month_retail_money-cur.pre_month_retail_money)*100/cur.pre_month_retail_money}"
							pattern="0.00" />%
		        			<font color="red">↓</font>
					</c:if>
				</c:if> <c:if
					test="${!(cur.month_retail_money gt 0 or cur.pre_month_retail_money lt 0) }">
		        		-
		        	</c:if></td>-->
			<!--  <td align="right"><c:if
					test="${s_fgs_month_retail_money gt 0 or s_fgs_month_retail_money lt 0 }">
					<fmt:formatNumber
						value="${cur.month_retail_money*100/s_fgs_month_retail_money}"
						pattern="0.00" />%
		        	</c:if> <c:if
					test="${!(s_fgs_month_retail_money gt 0 or s_fgs_month_retail_money lt 0) }">
		        		-
		        	</c:if></td>-->
			  <td align="right" ><fmt:formatNumber
					value="${cur.map.month_retail_money_task_lj}" pattern="0.00" /></td>
			<td align="right"><fmt:formatNumber
					value="${cur.map.retail_money_lj}" pattern="0.00" /></td>
			<td align="center"><c:if
					test="${cur.map.month_retail_money_task_lj gt 0 or cur.map.month_retail_money_task_lj lt 0 }">
					<fmt:formatNumber
						value="${cur.map.retail_money_lj*100/cur.map.month_retail_money_task_lj}"
						pattern="0.00" />%
		        	</c:if> <c:if
					test="${!(cur.map.month_retail_money_task_lj gt 0 or cur.map.month_retail_money_task_lj lt 0) }">
		        		-
		        	</c:if></td>
		</tr>
	</c:forEach>
	<tr>
		<td align="center">合计</td>
		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money_task}" pattern="0.00" /></td>
		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money}" pattern="0.00" /></td>
		<!-- td align="right"><fmt:formatNumber
				value="${s_pre_month_retail_money}" pattern="0.00" /></td> -->
		<td align="center"><c:if
				test="${s_month_retail_money_task gt 0 or s_month_retail_money_task lt 0 }">
				<fmt:formatNumber
					value="${s_month_retail_money*100/s_month_retail_money_task}"
					pattern="0.00" />%
		        	</c:if> <c:if
				test="${!(s_month_retail_money_task gt 0 or s_month_retail_money_task lt 0) }">
		        		-
		        	</c:if></td>
		<!-- <td align="center"><c:if
				test="${s_pre_month_retail_money gt 0 or s_pre_month_retail_money lt 0 }">
				<c:if test="${s_month_retail_money gt s_pre_month_retail_money}">
					<fmt:formatNumber
						value="${(s_month_retail_money-s_pre_month_retail_money)*100/s_pre_month_retail_money}"
						pattern="0.00" />%
		        			<font color="green">↑</font>
				</c:if>
				<c:if test="${s_month_retail_money lt s_pre_month_retail_money}">
					<fmt:formatNumber
						value="${(s_month_retail_money-s_pre_month_retail_money)*100/s_pre_month_retail_money}"
						pattern="0.00" />%
		        			<font color="red">↓</font>
				</c:if>
			</c:if> <c:if
				test="${!(s_pre_month_retail_money gt 0 or s_pre_month_retail_money lt 0) }">
		        		-
		        	</c:if></td> -->
		<!-- <td align="right">100%</td> -->
		<td align="right"><fmt:formatNumber
				value="${s_month_retail_money_task_lj}" pattern="0.00" /></td>
		<td align="right"><fmt:formatNumber value="${s_retail_money_lj}"
				pattern="0.00" /></td>
		<td align="center"><c:if
				test="${s_month_retail_money_task_lj gt 0 or s_month_retail_money_task_lj lt 0 }">
				<fmt:formatNumber
					value="${s_retail_money_lj*100/s_month_retail_money_task_lj}"
					pattern="0.00" />%
		        	</c:if> <c:if
				test="${!(s_month_retail_money_task_lj gt 0 or s_month_retail_money_task_lj lt 0) }">
		        		-
		        	</c:if></td>
	</tr>
</table>
